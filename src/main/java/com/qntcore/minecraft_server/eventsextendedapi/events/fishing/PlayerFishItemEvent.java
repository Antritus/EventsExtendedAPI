package com.qntcore.minecraft_server.eventsextendedapi.events.fishing;

import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PlayerFishItemEvent extends ExtendedPlayerFishEvent {

	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Getter
	final ItemStack caughtItemStack;
	@Getter
	final Entity caughtEntity;
	@Getter
	final boolean isTreasure;
	@Getter
	final boolean isJunk;
	@Getter
	final boolean isFish;
	@Getter
	final boolean isFishingReward;
	@Getter
	final ItemType rewardType;
	public PlayerFishItemEvent(PlayerFishEvent playerFishEvent, Player player, FishHook hookEntity, EquipmentSlot hand, ItemStack fishingRod, Entity caughtEntity) {
		super(playerFishEvent, player, hookEntity, hand, PlayerFishEvent.State.CAUGHT_FISH, fishingRod);
		this.caughtEntity = caughtEntity;
		this.caughtItemStack = ((Item) caughtEntity).getItemStack();
		ItemType itemType = ItemType.getItemType(caughtItemStack);
		this.isTreasure = itemType == ItemType.TREASURE;
		this.isJunk = itemType == ItemType.JUNK;
		this.isFish = itemType == ItemType.FISH;
		this.isFishingReward = itemType != ItemType.UNKNOWN;
		this.rewardType = itemType;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
}
