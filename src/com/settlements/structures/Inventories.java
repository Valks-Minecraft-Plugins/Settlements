package com.settlements.structures;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class Inventories implements Listener {
	@EventHandler
	private void inventoryInteractEvent(InventoryClickEvent e) {
		String title = e.getView().getTitle();

		if (!validStructure(title))
			return;
		
		e.setCancelled(true);
		int slot = e.getSlot();
		Player p = (Player) e.getInventory().getHolder();

		switch (title) {
		case "Townhall":
			switch (slot) {
			case 0:
				e.getWhoClicked().sendMessage(p.getName());
				break;
			case 1:
				e.getWhoClicked().sendMessage("Upgraded!");
			default:
				break;
			}
			break;
		default:
			break;
		}
		
		p.closeInventory();
	}

	private boolean validStructure(String title) {
		String[] validStructs = { "Townhall", "Mines" };
		for (String v : validStructs) {
			if (title.equalsIgnoreCase(v))
				return true;
		}
		return false;
	}
}
