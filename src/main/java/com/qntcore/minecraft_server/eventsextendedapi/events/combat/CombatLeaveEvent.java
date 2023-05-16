package com.qntcore.minecraft_server.eventsextendedapi.events.combat;

import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class CombatLeaveEvent extends CombatEvent {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	@Getter
	final LeaveReason leaveReason;
	public CombatLeaveEvent(Entity who, LeaveReason leaveReason) {
		super(who);
		this.leaveReason = leaveReason;
	}

	public enum LeaveReason {
		TIME_RAN_OUT,
		DEATH,
		MANUAL,
		UNKNOWN
	}
}
