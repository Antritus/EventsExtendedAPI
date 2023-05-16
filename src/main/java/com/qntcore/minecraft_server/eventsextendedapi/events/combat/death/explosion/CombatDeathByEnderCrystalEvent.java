package com.qntcore.minecraft_server.eventsextendedapi.events.combat.death.explosion;

import com.qntcore.minecraft_server.eventsextendedapi.events.combat.death.entity.CombatDeathByEntityEvent;
import lombok.Getter;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;

public class CombatDeathByEnderCrystalEvent extends CombatDeathByEntityEvent {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	@Getter
	private final EnderCrystal enderCrystal;
	public CombatDeathByEnderCrystalEvent(Entity who, Entity attacker, EnderCrystal enderCrystal) {
		super(who, attacker);
		this.enderCrystal = enderCrystal;
	}
}
