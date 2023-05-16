package com.qntcore.minecraft_server.eventsextendedapi.armor;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

public enum ArmorSlot {
	HEAD(5,"helmet", "head", "skull"),
	CHEST(6,"chestplate", "elytra"),
	LEGS(7,"leggings"),
	BOOTS(8,"boots")
	;
	private final int rawId;
	private final String[] endings;
	ArmorSlot(int rawId, String... endings) {
		this.rawId = rawId;
		this.endings = endings;
	}

	public int getRawId() {
		return rawId;
	}
	@Nullable
	public static ItemStack get(Player player, ArmorSlot slot){
		switch (slot){
			case HEAD:
				return player.getInventory().getHelmet();
			case CHEST:
				return player.getInventory().getChestplate();
			case LEGS:
				return player.getInventory().getLeggings();
			case BOOTS:
				return player.getInventory().getBoots();
		}
		return null;
	}

	@Nullable
	public static ArmorSlot matches(ItemStack itemStack){
		if (itemStack == null){
			return null;
		}
		for (ArmorSlot slot : values()){
			for (String ending : slot.endings) {
				if (itemStack.getType().name().toLowerCase().endsWith(ending)){
					return slot;
				}
			}
		}
		return null;
	}
}
