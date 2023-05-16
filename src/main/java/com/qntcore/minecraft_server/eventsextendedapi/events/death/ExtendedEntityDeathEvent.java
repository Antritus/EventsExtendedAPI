package com.qntcore.minecraft_server.eventsextendedapi.events.death;

import com.qntcore.minecraft_server.eventsextendedapi.events.death.entity.EntityDeathByEntityEvent;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;


public class ExtendedEntityDeathEvent extends EntityEvent {
	private static final HandlerList HANDLERS = new HandlerList();


	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}


	protected EntityDamageEvent damageEvent;
	protected EntityDeathEvent deathEvent;
	public ExtendedEntityDeathEvent(EntityDamageEvent damageEvent, EntityDeathEvent deathEvent) {
		super(deathEvent.getEntity());
		this.deathEvent = deathEvent;
		this.damageEvent = damageEvent;
	}

	public List<ItemStack> getDrops() {
		return deathEvent.getDrops();
	}

	public int getDroppedXP() {
		return deathEvent.getDroppedExp();
	}

	public EntityDamageEvent.DamageCause getDamageCause() {
		return damageEvent.getCause();
	}
	public void setDrops(List<ItemStack> items){
		deathEvent.getDrops().clear();
		deathEvent.getDrops().addAll(items);
	}
	public void setDroppedXp(int x){
		deathEvent.setDroppedExp(x);
	}
}
