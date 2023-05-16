package com.qntcore.minecraft_server.eventsextendedapi.events.combat.reset;

import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class CombatResetByAttackEvent extends CombatResetEvent{
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	@Getter
	private final Entity attacked;
	public CombatResetByAttackEvent(Entity who, Entity attacked) {
		super(who, ResetReason.ATTACK);
		this.attacked = attacked;
	}
}
