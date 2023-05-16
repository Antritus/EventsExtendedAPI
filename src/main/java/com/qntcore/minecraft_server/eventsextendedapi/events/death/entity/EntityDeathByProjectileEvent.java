package com.qntcore.minecraft_server.eventsextendedapi.events.death.entity;


import org.bukkit.entity.Projectile;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathByProjectileEvent extends EntityDeathByEntityEvent {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	public EntityDeathByProjectileEvent(EntityDamageByEntityEvent damageEvent, EntityDeathEvent deathEvent){
		super(damageEvent, deathEvent);
	}
	public Projectile getProjectile(){
		return (Projectile) ((EntityDamageByEntityEvent) damageEvent).getDamager();
	}
}
