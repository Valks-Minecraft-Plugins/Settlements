package com.settlements.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import com.settlements.utils.UtilsItem;

public class InventoryTownhall implements Listener {
	@EventHandler
	private void inventoryInteractEvent(InventoryClickEvent e) {
		if (!e.getView().getTitle().equalsIgnoreCase("Townhall"))
			return;
		
		e.setCancelled(true);
		
		switch (e.getSlot()) {
		case 0:
			Player p = (Player) e.getInventory().getHolder();
			e.getWhoClicked().sendMessage(p.getName());
			break;
		default:
			break;
		}
	}
	
	public static Inventory townhallInventory(Player p) {
		Inventory inv = Bukkit.createInventory(p, 9, "Townhall");
		inv.setItem(0, UtilsItem.item("Owner", "The founder of the settlement.", Material.FEATHER));
		
		return inv;
	}
}
