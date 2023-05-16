package com.qntcore.minecraft_server.eventsextendedapi.events.death.explosion;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EntityDeathByBlockExplosionEvent extends EntityDeathByExplosionEvent{
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public EntityDeathByBlockExplosionEvent(EntityDamageByBlockEvent damageByBlockEvent, EntityDeathEvent event) {
		super(damageByBlockEvent, event);
	}

	public Block getBlock(){
		return ((EntityDamageByBlockEvent) damageEvent).getDamager();
	}
}
