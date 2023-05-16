package com.qntcore.minecraft_server.eventsextendedapi.events.combat;

import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class CombatEnterEvent extends CombatEvent {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	@Getter
	final Entity attacker;
	@Getter
	final EnterReason enterReason;
	public CombatEnterEvent(Entity who, Entity attacker, EnterReason enterReason) {
		super(who);
		this.attacker = attacker;
		this.enterReason = enterReason;
	}

	public enum EnterReason {
		ATTACK,
		KILL,
		MANUAL,
		UNKNOWN
	}
}
