package com.qntcore.minecraft_server.eventsextendedapi.events.movement;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Event used for detecting when player jumps.
 * This is not triggered using climbing ladders
 * This is not triggered using potion effects (except jump boost)
 * @author antritus
 * @since 06/07/2023
 */
public class PlayerJumpEvent extends PlayerMoveEvent {
	private static final HandlerList HANDLERS = new HandlerList();
	public PlayerJumpEvent(@NotNull Player player, @NotNull Location from, @NotNull Location to) {
		super(player, from, to);
	}

	public static @NotNull HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public @NotNull HandlerList getHandlers() {
		return HANDLERS;
	}

	@NotNull
	@Override
	public Location getTo() {
		return Objects.requireNonNull(super.getTo());
	}
}
