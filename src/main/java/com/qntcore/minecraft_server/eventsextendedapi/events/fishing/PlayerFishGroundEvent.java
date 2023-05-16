package com.qntcore.minecraft_server.eventsextendedapi.events.fishing;

import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PlayerFishGroundEvent extends ExtendedPlayerFishEvent{
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}


	public PlayerFishGroundEvent(PlayerFishEvent playerFishEvent, Player player, FishHook hookEntity, EquipmentSlot hand, ItemStack fishingRod) {
		super(playerFishEvent, player, hookEntity, hand, PlayerFishEvent.State.IN_GROUND, fishingRod);
	}
}
