package com.qntcore.minecraft_server.eventsextendedapi.events.combat.death.entity;

import com.qntcore.minecraft_server.eventsextendedapi.events.combat.death.CombatDeathEvent;
import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;

public class CombatDeathByEntityEvent extends CombatDeathEvent {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	@Getter
	private final Entity attacker;
	public CombatDeathByEntityEvent(Entity who, Entity attacker) {
		super(who, EntityDamageEvent.DamageCause.ENTITY_ATTACK);
		this.attacker = attacker;
	}}
