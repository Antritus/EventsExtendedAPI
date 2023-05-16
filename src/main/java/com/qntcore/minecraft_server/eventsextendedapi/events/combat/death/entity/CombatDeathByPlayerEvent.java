package com.qntcore.minecraft_server.eventsextendedapi.events.combat.death.entity;

import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class CombatDeathByPlayerEvent extends CombatDeathByEntityEvent{
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	@Getter
	private final Player attacker;
	public CombatDeathByPlayerEvent(Entity who, Player attacker) {
		super(who, attacker);
		this.attacker = attacker;
	}
}
