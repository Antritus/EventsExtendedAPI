package com.qntcore.minecraft_server.eventsextendedapi.internal;

import com.qntcore.minecraft_server.eventsextendedapi.EventsExtendedAPI;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static boolean isEnabled = false;

	@Override
	public void onEnable() {
		getLogger().info("Enabled Events Extended API!");
		getLogger().info("This plugin is not required to be used as a normal plugin, as it can be set enabled using com.qntcore.minecraft_server.EventsExtendedAPI#enablePlugin(JavaPlugin plugin) in other plugins!");
		EventsExtendedAPI.enablePlugin(this);
		getLogger().info("Enabled!");
		isEnabled = true;
	}

	@Override
	public void onDisable() {
		getLogger().info("Disabled Events Extended API!");
	}
}