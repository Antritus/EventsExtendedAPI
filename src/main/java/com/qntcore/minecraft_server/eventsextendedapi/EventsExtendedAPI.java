package com.qntcore.minecraft_server.eventsextendedapi;

import com.qntcore.minecraft_server.eventsextendedapi.internal.Main;
import com.qntcore.minecraft_server.eventsextendedapi.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class EventsExtendedAPI {
	public static int minecraftVersion = (Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].split("\\.")[1]+Bukkit.getBukkitVersion().split("-")[0].split("\\.")[2]));
	public static boolean isEnabled = false;
	public JavaPlugin plugin;
	private void enable(JavaPlugin plugin){
		if (Bukkit.getServer().getPluginManager().getPlugin("EventsExtendedAPI") != null){
			if (!(plugin instanceof Main)){
				return;
			}
		}
		if ((Main.isEnabled)){
			return;
		}
		if (isEnabled){
			return;
		}
		this.plugin= plugin;
		isEnabled = true;
		PluginManager manager = plugin.getServer().getPluginManager();
		manager.registerEvents(new InteractListener(), plugin);
		manager.registerEvents(new FishingListener(), plugin);
		manager.registerEvents(new DeathListener(), plugin);
		manager.registerEvents(new BlockBreakListener(), plugin);
		manager.registerEvents(new ArmorChangeListener(), plugin);
		manager.registerEvents(new PlayerJumpListener(), plugin);
		plugin.getLogger().info("Enabled EventsExtendedAPI!");
	}
	@SuppressWarnings("unused")
	public static void enablePlugin(JavaPlugin plugin){
		new EventsExtendedAPI().enable(plugin);
	}
}
