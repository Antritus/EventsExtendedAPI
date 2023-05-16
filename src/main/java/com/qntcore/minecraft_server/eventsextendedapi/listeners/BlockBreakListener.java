package com.qntcore.minecraft_server.eventsextendedapi.listeners;

import com.qntcore.minecraft_server.eventsextendedapi.events.blockbreak.ExtendedBlockBreakEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BlockBreakListener implements Listener {
	ExtendedBlockBreakEvent e;
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBreak(BlockBreakEvent e){
		ExtendedBlockBreakEvent event = new ExtendedBlockBreakEvent(e, e.getBlock(), e.getPlayer());
		Bukkit.getPluginManager().callEvent(event);
		this.e = event;
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onItemDrop(BlockDropItemEvent e){
		List<ItemStack> items = new ArrayList<>();
		for (Item item : e.getItems()){
			ItemStack itemStack = item.getItemStack();
			items.add(itemStack);
		}
		this.e.setDrops(items);
	}
}