package com.qntcore.minecraft_server.eventsextendedapi.events.fishing;

import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExtendedPlayerFishEvent extends PlayerEvent implements Cancellable {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
	@Getter
	final protected PlayerFishEvent playerFishEvent;
	@Getter
	final private FishHook fishHook;
	@Getter
	final private EquipmentSlot hand;
	@Getter
	final private PlayerFishEvent.State state;

	private boolean isCancelled;

	@Getter
	final private ItemStack fishingRod;
	public ExtendedPlayerFishEvent(PlayerFishEvent playerFishEvent, Player player, FishHook hookEntity, EquipmentSlot hand, PlayerFishEvent.State state, ItemStack fishingRod) {
		super(player);
		this.playerFishEvent = playerFishEvent;
		this.isCancelled = playerFishEvent.isCancelled();
		this.fishHook = hookEntity;
		this.hand = hand;
		this.state = state;
		this.fishingRod = fishingRod;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	@Override
	public boolean isCancelled() {
		this.isCancelled = this.playerFishEvent.isCancelled();
		return isCancelled;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.isCancelled = cancel;
		this.playerFishEvent.setCancelled(cancel);
	}

	public enum ItemType {
		FISH("COD", "SALMON", "TROPICAL_FISH", "PUFFERFISH"),
		TREASURE("BOW", "ENCHANTED_BOOK", "FISHING_ROD", "NAME_TAG", "NAUTILUS_SHELL", "SADDLE"),
		JUNK("LILY_PAD", "BOWL", "FISHING_ROD", "LEATHER", "LEATHER_BOOTS", "ROTTEN_FLESH", "STICK", "STRING", "POTION", "BONE", "INK_SAC", "TRIPWIRE_HOOK", "BAMBOO"),
		UNKNOWN
		;


		private final Material[] materials;
		ItemType(String... materials){
			List<Material> mats = new ArrayList<>();
			for (String material : materials){
				try {
					Field field = Material.class.getDeclaredField(material);
					field.setAccessible(true);
					mats.add((Material) field.get(null));
				} catch (NoSuchFieldException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
			this.materials = mats.toArray(new Material[1]);
		}

		public Material[] getMaterials() {
			return materials;
		}

		public static ItemType getItemType(ItemStack itemStack){
			if (itemStack.getType() == Material.BOW){
				if (itemStack.getItemMeta().hasEnchants()){
					return TREASURE;
				}
				return JUNK;
			}
			for (ItemType itemType : values()){
				for (Material material : itemType.getMaterials()){
					if (material == itemStack.getType()){
						return itemType;
					}
				}
			}
			return UNKNOWN;
		}
	}
}
