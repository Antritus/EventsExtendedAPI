package com.qntcore.minecraft_server.eventsextendedapi.events.combat.death.explosion;

import com.qntcore.minecraft_server.eventsextendedapi.events.combat.death.entity.CombatDeathByEntityEvent;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class CombatDeathByBedEvent extends CombatDeathByEntityEvent {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	@Getter
	private final Location bedLocation;
	public CombatDeathByBedEvent(Entity who, Entity attacker, Location location) {
		super(who, attacker);
		bedLocation = location;
	}
}
