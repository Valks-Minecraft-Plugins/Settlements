package com.settlements;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.settlements.configs.LoadPlayerFiles;
import com.settlements.listeners.CreateSettlement;
import com.settlements.listeners.InteractWithSettlement;
import com.settlements.listeners.Inventories;

public class Settlements extends JavaPlugin {
	public static Settlements plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		registerEvents(getServer().getPluginManager());
	}
	
	private void registerEvents(PluginManager pm) {
		pm.registerEvents(new CreateSettlement(), this);
		pm.registerEvents(new LoadPlayerFiles(), this);
		pm.registerEvents(new InteractWithSettlement(), this);
		
		pm.registerEvents(new Inventories(), this);
	}
}
