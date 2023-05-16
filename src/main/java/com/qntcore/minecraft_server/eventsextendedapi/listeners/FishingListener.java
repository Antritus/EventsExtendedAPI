package com.qntcore.minecraft_server.eventsextendedapi.listeners;

import com.qntcore.minecraft_server.eventsextendedapi.events.fishing.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class FishingListener implements Listener {
	@EventHandler(priority = EventPriority.HIGH)
	public void onFish(PlayerFishEvent e){
		ItemStack itemStack = null;
		EquipmentSlot hand = null;
		if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.FISHING_ROD){
			itemStack = e.getPlayer().getInventory().getItemInMainHand();
			hand = EquipmentSlot.HAND;
		} else if (e.getPlayer().getInventory().getItemInOffHand().getType() == Material.FISHING_ROD) {
			itemStack = e.getPlayer().getInventory().getItemInOffHand();
			hand = EquipmentSlot.OFF_HAND;
		}
		if (e.getState() == PlayerFishEvent.State.IN_GROUND){
			ExtendedPlayerFishEvent event = new PlayerFishGroundEvent(e, e.getPlayer(), e.getHook(), hand, itemStack);
			Bukkit.getServer().getPluginManager().callEvent(event);
		}
		else if (e.getState() == PlayerFishEvent.State.FAILED_ATTEMPT){
			ExtendedPlayerFishEvent event = new PlayerFishFailEvent(e, e.getPlayer(), e.getHook(), hand, itemStack);
			Bukkit.getServer().getPluginManager().callEvent(event);
		}
		else if (e.getState() == PlayerFishEvent.State.FISHING){
			ExtendedPlayerFishEvent event = new PlayerFishCastFishingRodEvent(e, e.getPlayer(), e.getHook(), hand, itemStack);
			Bukkit.getServer().getPluginManager().callEvent(event);
		}
		else if (e.getState() == PlayerFishEvent.State.CAUGHT_FISH){
			ItemStack caught = ((Item) e.getCaught()).getItemStack();
			ExtendedPlayerFishEvent event = new PlayerFishItemEvent(e, e.getPlayer(), e.getHook(), hand, itemStack, (e).getCaught());
			Bukkit.getServer().getPluginManager().callEvent(event);
		}
		else if (e.getState() == PlayerFishEvent.State.CAUGHT_ENTITY){
			ExtendedPlayerFishEvent event = new PlayerFishEntityEvent(e, e.getPlayer(), e.getHook(), hand, itemStack, (e).getCaught());
			Bukkit.getServer().getPluginManager().callEvent(event);
		}
		else if (e.getState() == PlayerFishEvent.State.BITE){
			ExtendedPlayerFishEvent event = new PlayerFishBiteEvent(e, e.getPlayer(), e.getHook(), hand, itemStack);
			Bukkit.getServer().getPluginManager().callEvent(event);
		}
	}
}