package com.qntcore.minecraft_server.eventsextendedapi.events.combat.death.explosion;

import com.qntcore.minecraft_server.eventsextendedapi.events.combat.death.entity.CombatDeathByEntityEvent;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class CombatDeathByRespawnAnchor extends CombatDeathByEntityEvent {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	@Getter
	private final Location respawnAnchorLocation;
	@Getter
	private final int respawnAnchorCharge;
	public CombatDeathByRespawnAnchor(Entity who, Entity attacker, Location respawnAnchor, int charge) {
		super(who, attacker);
		this.respawnAnchorLocation = respawnAnchor;
		this.respawnAnchorCharge = charge;
	}
}
