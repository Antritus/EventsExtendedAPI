package com.qntcore.minecraft_server.eventsextendedapi.events.death;

import org.bukkit.entity.Entity;

public interface IEntityAttackEvent {
	Entity getAttacker();
}
