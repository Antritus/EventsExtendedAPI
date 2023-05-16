package com.qntcore.minecraft_server.eventsextendedapi.events.inventory;

import com.qntcore.minecraft_server.eventsextendedapi.armor.ArmorEquipMethod;
import com.qntcore.minecraft_server.eventsextendedapi.armor.ArmorSlot;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

public class ArmorEquipEvent extends PlayerEvent implements Cancellable {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	@Getter
	private final ItemStack newItem;
	@Getter
	private final ItemStack oldItem;
	@Getter
	private final ArmorEquipMethod method;
	@Getter
	private final ArmorSlot armorSlot;
	@Getter
	private final boolean newItemAir;
	@Getter
	private final boolean oldItemAir;
	private boolean isCancelled = false;
	public ArmorEquipEvent(Player who, ArmorEquipMethod method, ArmorSlot slot, ItemStack newItem, ItemStack oldItem) {
		super(who);
		this.method = method;
		this.armorSlot = slot;
		this.newItem = newItem;
		this.oldItem = oldItem;
		this.newItemAir = newItem == null || newItem.getType().isAir();
		this.oldItemAir = oldItem == null || oldItem.getType().isAir();
	}

	/**
	 * This might not work in some cases of this event!
	 * @return is the event cancelled
	 */
	@Override
	public boolean isCancelled() {
		return isCancelled;
	}

	/**
	 * This might not work in some cases of this event!
	 * @param cancel true if you wish to cancel this event
	 */
	@Override
	public void setCancelled(boolean cancel) {
		isCancelled = cancel;
	}
}
