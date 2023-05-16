package com.qntcore.minecraft_server.eventsextendedapi.listeners;

import com.qntcore.minecraft_server.eventsextendedapi.BedData;
import com.qntcore.minecraft_server.eventsextendedapi.events.block.BedExplodePrepareEvent;
import com.qntcore.minecraft_server.eventsextendedapi.events.block.ExtendedBlockExplosionPrepareEvent;
import com.qntcore.minecraft_server.eventsextendedapi.events.block.RespawnAnchorExplodePrepareEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {
	@EventHandler (ignoreCancelled = true)
	public void onInteract(PlayerInteractEvent e){
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK){
			Block block = e.getClickedBlock();
			assert block != null;
			if (block.getBlockData() instanceof RespawnAnchor){
				if (block.getWorld().getEnvironment() == World.Environment.NORMAL || block.getWorld().getEnvironment() == World.Environment.THE_END) {
					RespawnAnchor respawnAnchor = (RespawnAnchor) block.getBlockData();
					ExtendedBlockExplosionPrepareEvent event = null;
					if (1 + respawnAnchor.getCharges() == respawnAnchor.getMaximumCharges() + 1 &&
							e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR
							&& e.getPlayer().getInventory().getItemInOffHand().getType() == Material.AIR
							&& !e.getPlayer().isSneaking()) {
						event = new RespawnAnchorExplodePrepareEvent(e, e.getClickedBlock(), respawnAnchor);
					} else if (e.getPlayer().getInventory().getItemInMainHand().getType() != Material.GLOWSTONE &&
							e.getPlayer().getInventory().getItemInOffHand().getType() != Material.GLOWSTONE &&
							respawnAnchor.getCharges() + 1 > 1
							&& !e.getPlayer().isSneaking()
							&& e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR
							&& e.getPlayer().getInventory().getItemInOffHand().getType() == Material.AIR) {
						event = new RespawnAnchorExplodePrepareEvent(e, e.getClickedBlock(), respawnAnchor);
					}
					if (event != null) {
						Bukkit.getServer().getPluginManager().callEvent(event);
					}
				}
				// Bed doesn't seem to work?
			} else if (e.getClickedBlock().getType().toString().endsWith("_BED")){
				BedData bed = new BedData(block.getBlockData());
				ExtendedBlockExplosionPrepareEvent event = null;
				if (block.getWorld().getEnvironment() == World.Environment.NETHER || block.getWorld().getEnvironment() == World.Environment.THE_END){
					if (e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR &&
					e.getPlayer().getInventory().getItemInOffHand().getType() == Material.AIR && e.getPlayer().isSneaking()){
						event = new BedExplodePrepareEvent(e.getClickedBlock(), bed);
					} else if (!e.getPlayer().isSneaking()){
						event = new BedExplodePrepareEvent(e.getClickedBlock(), bed);
					} else if (e.getPlayer().isSneaking() && e.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR &&
					e.getPlayer().getInventory().getItemInOffHand().getType() != Material.AIR){
						event = new BedExplodePrepareEvent(e.getClickedBlock(), bed);
					}
					if (event != null){
						Bukkit.getServer().getPluginManager().callEvent(event);
					}
				}
			}
		}
	}
}
