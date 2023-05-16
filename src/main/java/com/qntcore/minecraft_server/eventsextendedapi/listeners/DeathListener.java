package com.qntcore.minecraft_server.eventsextendedapi.listeners;

import com.qntcore.minecraft_server.eventsextendedapi.events.block.ExtendedBlockExplosionPrepareEvent;
import com.qntcore.minecraft_server.eventsextendedapi.events.death.ExtendedEntityDeathEvent;
import com.qntcore.minecraft_server.eventsextendedapi.events.death.entity.EntityDeathByEntityEvent;
import com.qntcore.minecraft_server.eventsextendedapi.events.death.entity.EntityDeathByFireworkEvent;
import com.qntcore.minecraft_server.eventsextendedapi.events.death.entity.EntityDeathByProjectileEvent;
import com.qntcore.minecraft_server.eventsextendedapi.events.death.explosion.EntityDeathByBlockExplosionEvent;
import com.qntcore.minecraft_server.eventsextendedapi.events.death.explosion.EntityDeathByEntityExplosionEvent;
import org.bukkit.Bukkit;
import org.bukkit.block.data.type.TNT;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class DeathListener implements Listener {
	private ExtendedBlockExplosionPrepareEvent latestExplosiveEvent;

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onExplode(ExtendedBlockExplosionPrepareEvent blockExplodeEvent){
		latestExplosiveEvent = blockExplodeEvent;
	}


	@EventHandler(ignoreCancelled = true)
	public void onDeath(EntityDeathEvent e) {
		EntityDamageEvent lastDamageEvent = e.getEntity().getLastDamageCause();
		EntityDamageEvent.DamageCause lastDamageCause = lastDamageEvent.getCause();
		ExtendedEntityDeathEvent callEvent = null;
		if (lastDamageEvent instanceof EntityDamageByEntityEvent) {
			Entity entity = ((EntityDamageByEntityEvent) lastDamageEvent).getDamager();
			if (entity instanceof Explosive && !(entity instanceof TNT)) {
				if (entity instanceof Wither){
					return;
				}
				Explosive explosive = (Explosive) entity;
				entity = null;
				if (explosive instanceof Projectile) {
					entity = (Entity) ((Projectile) explosive).getShooter();
				}
				callEvent = new EntityDeathByEntityExplosionEvent(lastDamageEvent, e);
			} else if (entity instanceof Creeper){
				callEvent = new EntityDeathByEntityExplosionEvent(lastDamageEvent, e);
			} else if (entity instanceof Wither){
				if (lastDamageCause == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION){
					callEvent = new EntityDeathByEntityExplosionEvent(lastDamageEvent, e);
				} else {
					callEvent = new EntityDeathByEntityEvent((EntityDamageByEntityEvent) lastDamageEvent, e);
				}
			} else if (entity instanceof Firework){
				callEvent = new EntityDeathByFireworkEvent((EntityDamageByEntityEvent) lastDamageEvent, e);
			}
			else if (entity instanceof EnderCrystal){
				callEvent = new EntityDeathByEntityExplosionEvent(lastDamageEvent, e);
			}else if (entity instanceof Projectile){
				Projectile projectile = (Projectile) entity;
				entity = (Entity) projectile.getShooter();
				callEvent = new EntityDeathByProjectileEvent((EntityDamageByEntityEvent) lastDamageEvent, e);
			}  else if (entity instanceof TNT){
				callEvent = new EntityDeathByEntityExplosionEvent(lastDamageEvent, e);
			} else {
				callEvent = new EntityDeathByEntityEvent((EntityDamageByEntityEvent) lastDamageEvent, e);
				System.out.println("Updated!");
			}
		} else if (lastDamageEvent instanceof EntityDamageByBlockEvent){
			callEvent = new EntityDeathByBlockExplosionEvent((EntityDamageByBlockEvent) lastDamageEvent, e);
		}

		if (callEvent == null){
			callEvent = new ExtendedEntityDeathEvent(lastDamageEvent, e);
		}
		Bukkit.getServer().getPluginManager().callEvent(callEvent);
	}

	@EventHandler
	public void onDeath(EntityDeathByEntityEvent e){
		System.out.println("Should trigger");
	}
}
