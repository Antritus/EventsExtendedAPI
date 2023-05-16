package com.qntcore.minecraft_server.eventsextendedapi.events.death.entity;

import com.qntcore.minecraft_server.eventsextendedapi.events.death.ExtendedEntityDeathEvent;
import com.qntcore.minecraft_server.eventsextendedapi.events.death.IEntityAttackEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathByEntityEvent extends ExtendedEntityDeathEvent implements IEntityAttackEvent {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public EntityDeathByEntityEvent(EntityDamageByEntityEvent damageEvent, EntityDeathEvent event) {
		super(damageEvent, event);
	}
	public Entity getAttacker() {
		if (((EntityDamageByEntityEvent) damageEvent).getDamager() instanceof Projectile){
			return (Entity) ((Projectile)((EntityDamageByEntityEvent) damageEvent).getDamager()).getShooter();
		} else {
			return ((EntityDamageByEntityEvent) damageEvent).getDamager();
		}
	}
}
