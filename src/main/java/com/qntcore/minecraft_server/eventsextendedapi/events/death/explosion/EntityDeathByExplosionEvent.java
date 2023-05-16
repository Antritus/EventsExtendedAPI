package com.qntcore.minecraft_server.eventsextendedapi.events.death.explosion;

import com.qntcore.minecraft_server.eventsextendedapi.events.death.ExtendedEntityDeathEvent;
import lombok.Getter;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;


public class EntityDeathByExplosionEvent extends ExtendedEntityDeathEvent {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}


	@Getter
	final private boolean entityExplosion;
	@Getter
	final private boolean blockExplosion;
	public EntityDeathByExplosionEvent(EntityDamageEvent damageEvent, EntityDeathEvent event) {
		super(damageEvent, event);
		entityExplosion = damageEvent.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION;
		blockExplosion = damageEvent.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION;
	}
}
