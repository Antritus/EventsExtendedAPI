package com.qntcore.minecraft_server.eventsextendedapi.listeners;

import com.qntcore.minecraft_server.eventsextendedapi.events.movement.PlayerJumpEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.util.Vector;

/**
 * Internal
 * Used to check when player moves if they are jumping or not.
 * @author antritus
 * @since 06/07/2023
 */
public class PlayerJumpListener implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJump(PlayerStatisticIncrementEvent e){
		if (e.getStatistic() != Statistic.JUMP){
			return;
		}
		if (e.getNewValue() < e.getPreviousValue() || e.getPreviousValue()-e.getNewValue() < -1){
			return;
		}
		Location from = e.getPlayer().getLocation();
//		Location to = e.getPlayer().getLocation();
		Location to = e.getPlayer().getLocation().add(e.getPlayer().getVelocity().add(new Vector(0, 0.42, 0)));
//		to.add(e.getPlayer().getVelocity().add(new Vector(0, 0.41999998688697815+(e.getPlayer().hasPotionEffect(PotionEffectType.JUMP) ? (((double) e.getPlayer().getPotionEffect(PotionEffectType.JUMP).getAmplifier() /10)+0.1) : 0), 0)));
		PlayerJumpEvent event = new PlayerJumpEvent(e.getPlayer(), from, to);
		Bukkit.getPluginManager().callEvent(event);
		if (event.isCancelled()){
			e.setCancelled(true);
		}
	}
	//@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	//public void onPlayerJump(PlayerMoveEvent event) {
	//	// The getTo() location is nullable in PlayerMovementEvent
	//	if (event.getTo() == null){
	//		return;
	//	}
//
	//	// Moving up while flying counts as moving upwards and we don't need flying
	//	if (event.getPlayer().isFlying()) {
	//		return;
	//	}
	//	// If player is not moving upwards ignore
	//	if (event.getPlayer().getVelocity().getY() > 0) {
	//		// Not sure if this can happen
	//		// Just check if it happens
	//		if (event.getFrom().getY() > event.getTo().getY()) {
	//			return;
	//		}
	//		/*
	//		 * If player is climbing it counts as going upwards and we don't want that
	//		 */
	//		Material type = event.getFrom().getBlock().getType();
	//		//Check if the server 1.16.5+
	//		try {
	//			Material.valueOf("WEEPING_VINES");
	//			if (type == Material.WEEPING_VINES || type == Material.WEEPING_VINES_PLANT || type == Material.TWISTING_VINES || type == Material.TWISTING_VINES_PLANT) {
	//				return;
	//			}
	//		} catch (IllegalArgumentException ignore) {
	//		}
	//		if (type == Material.LADDER || type == Material.VINE) {
	//			return;
	//		}
	//		// Levitation can trigger speed of jump velocity
	//		if (event.getPlayer().getPotionEffect(PotionEffectType.LEVITATION) != null) {
	//			return;
	//		}
//
	//		double jumpVelocity = 0.41999998688697815;
	//		// jump velocity + jump boost 1 = 0.6199999898672104
	//		if (event.getPlayer().getVelocity().getY() < jumpVelocity){
	//			return;
	//		}
//	//		System.out.println(event.getPlayer().getVelocity().getY());
//	//		System.out.println("OLD "+event.getPlayer().getVelocity().getY());
	//		// Checking for jump boost and applying velocity changes
	//		boolean potion = false;
	//		double difference = 1000;
	//		if (event.getPlayer().hasPotionEffect(PotionEffectType.JUMP)){
//	//			System.out.println("Potion");
	//			potion = true;
	//			int level = event.getPlayer().getPotionEffect(PotionEffectType.JUMP).getAmplifier();
	//			jumpVelocity+=(((level+1)*0.1));
//	//			System.out.println("Calculated (Jump Boost) "+jumpVelocity);
	//			difference = (jumpVelocity-event.getPlayer().getVelocity().getY());
	//		}
	//		if (potion){
	//			if (difference > 0.0000000001){
	//				return;
	//			}
	//		}else {
	//			difference = event.getPlayer().getPlayer().getVelocity().getY()-jumpVelocity;
	//			if (difference !=0){
//	//				System.out.println("Not 0!");
	//				return;
	//			}
 	//		}
//	//		System.out.println("passed");
//	//		System.out.println("Difference: "+difference);
//	//		System.out.println("Passed");
	//		// Sending jump event doe
	//		PlayerJumpEvent jumpEvent = new PlayerJumpEvent(event.getPlayer(), event.getFrom(), event.getTo());
	//		Bukkit.getServer().getPluginManager().callEvent(jumpEvent);
	//		if (jumpEvent.isCancelled()){
	//			event.setCancelled(true);
	//		}
	//	}
	//}
}
