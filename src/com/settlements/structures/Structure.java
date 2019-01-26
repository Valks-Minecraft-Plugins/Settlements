package com.settlements.structures;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Structure {
	private Inventory inv;
	
	public Structure(Player p, String name) {
		this.inv = Bukkit.createInventory(p, 9, name);
	}
	
	public Inventory getInventory() {
		return inv;
	}
}
