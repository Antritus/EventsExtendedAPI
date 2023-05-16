package com.qntcore.minecraft_server.eventsextendedapi.events.fishing;

import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PlayerFishEntityEvent extends ExtendedPlayerFishEvent{
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Getter
	final Entity caught;

	public PlayerFishEntityEvent(PlayerFishEvent playerFishEvent, Player player, FishHook hookEntity, EquipmentSlot hand, ItemStack fishingRod, Entity caught) {
		super(playerFishEvent, player, hookEntity, hand, PlayerFishEvent.State.CAUGHT_ENTITY, fishingRod);
		this.caught = caught;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
}
