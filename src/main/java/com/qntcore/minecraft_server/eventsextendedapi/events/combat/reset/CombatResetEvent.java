package com.qntcore.minecraft_server.eventsextendedapi.events.combat.reset;

import com.qntcore.minecraft_server.eventsextendedapi.events.combat.CombatEvent;
import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class CombatResetEvent extends CombatEvent {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	@Getter
	private final ResetReason resetReason;
	public CombatResetEvent(Entity who, ResetReason resetReason) {
		super(who);
		this.resetReason = resetReason;
	}

	public enum ResetReason {
		ATTACK,
		MANUAL,
		UNKNOWN
	}
}
