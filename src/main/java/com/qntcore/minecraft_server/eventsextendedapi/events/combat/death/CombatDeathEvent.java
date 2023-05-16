package com.qntcore.minecraft_server.eventsextendedapi.events.combat.death;

import com.qntcore.minecraft_server.eventsextendedapi.events.combat.CombatEvent;
import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

public class CombatDeathEvent extends CombatEvent {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	@Getter
	final private EntityDamageEvent.DamageCause cause;
	public CombatDeathEvent(Entity who, EntityDamageEvent.DamageCause cause) {
		super(who);
		this.cause = cause;
	}
}
