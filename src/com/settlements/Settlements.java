package com.settlements;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.settlements.configs.LoadPlayerFiles;
import com.settlements.listeners.CreateSettlement;
import com.settlements.listeners.UpgradeSettlement;
import com.settlements.structures.Inventories;

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
		pm.registerEvents(new UpgradeSettlement(), this);
		
		pm.registerEvents(new Inventories(), this);
	}
}
