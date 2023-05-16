package com.qntcore.minecraft_server.eventsextendedapi.events.combat;

import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class CombatEvent extends Event {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Getter
	private Entity entity;
	public CombatEvent(Entity who) {
		entity = who;
	}


	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
}
