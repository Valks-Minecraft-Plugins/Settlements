package com.settlements.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class UpgradeSettlement implements Listener {
	@EventHandler
	private void playerInteractEvent(PlayerInteractEvent e) {
		if (e.getClickedBlock() == null)
			return;
		if (e.getClickedBlock().getType() != Material.EMERALD_BLOCK)
			return;
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		if (e.getClickedBlock().getMetadata("Owner") == null)
			return;
		if (e.getClickedBlock().getMetadata("Owner").get(0).asString() != e.getPlayer().getName())
			return;

		Inventory inv = Bukkit.createInventory(null, 9, "Settlement");
		inv.setItem(0, new ItemStack(Material.FEATHER));

		e.getPlayer().openInventory(inv);
	}

	@EventHandler
	private void inventoryInteractEvent(InventoryClickEvent e) {
		if (!e.getView().getTitle().equalsIgnoreCase("Settlement"))
			return;
		e.setCancelled(true);
		switch (e.getSlot()) {
		case 0:
			e.getWhoClicked().sendMessage("Works");
			break;
		default:
			break;
		}
	}
}
