package com.qntcore.minecraft_server.eventsextendedapi.events.death.explosion;

import com.qntcore.minecraft_server.eventsextendedapi.events.death.IEntityAttackEvent;
import com.qntcore.minecraft_server.eventsextendedapi.events.death.IExplosiveEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Explosive;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathByEntityExplosionEvent extends EntityDeathByExplosionEvent implements IEntityAttackEvent, IExplosiveEvent {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public EntityDeathByEntityExplosionEvent(EntityDamageEvent damageEvent, EntityDeathEvent event) {
		super(damageEvent, event);
	}
	@Override
	public Explosive getExplosive(){
		return (Explosive) ((EntityDamageByEntityEvent)damageEvent).getDamager();
	}

	@Override
	public Entity getAttacker() {
		return ((EntityDamageByEntityEvent) damageEvent).getDamager();
	}
}
