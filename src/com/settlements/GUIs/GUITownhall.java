package com.settlements.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.settlements.utils.UtilsItem;

public class GUITownhall {
	Inventory inv;
	
	public GUITownhall(Player p) {
		inv = Bukkit.createInventory(p, 18, "Townhall");
		
		inv.setItem(0, UtilsItem.item("Owner", "The founder of the settlement.", Material.FEATHER));
		inv.setItem(1, UtilsItem.item("Upgrade", "Upgrade the townhall.", Material.BRICKS));
		
		inv.setItem(2, UtilsItem.item("Storage", "Upgrade the Storage", Material.CHEST));
		inv.setItem(3, UtilsItem.item("Mines", "Upgrade the Mines", Material.WOODEN_PICKAXE));
		inv.setItem(4, UtilsItem.item("Black Smith", "Upgrade the Blacksmith", Material.WOODEN_SWORD));
		inv.setItem(5, UtilsItem.item("Stable", "Upgrade the Stable", Material.HAY_BLOCK));
		inv.setItem(6, UtilsItem.item("Farm", "Upgrade the Farm", Material.WOODEN_HOE));
		inv.setItem(7, UtilsItem.item("Bakery", "Upgrade the Bakery", Material.BREAD));
		inv.setItem(8, UtilsItem.item("Defenses", "Upgrade the Defenses", Material.OAK_FENCE));
		inv.setItem(9, UtilsItem.item("Market", "Upgrade the Market", Material.BOWL));
		inv.setItem(10, UtilsItem.item("Brewery", "Upgrade the Brewery", Material.POTION));
		inv.setItem(11, UtilsItem.item("Black Market", "Upgrade the Black Market", Material.BLAZE_POWDER));
	}
	
	public Inventory getInventory() {
		return inv;
	}
}
