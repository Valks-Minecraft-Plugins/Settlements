package com.settlements.listeners;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.settlements.configs.PlayerFiles;
import com.settlements.utils.UtilsMsc;
import com.settlements.utils.UtilsSchematic;

public class Inventories implements Listener {
	@EventHandler
	private void inventoryInteractEvent(InventoryClickEvent e) {
		String title = e.getView().getTitle();
	
		if (!validStructure(title))
			return;
		
		e.setCancelled(true);
		int slot = e.getSlot();
		Player p = (Player) e.getInventory().getHolder();
		
		PlayerFiles cm = PlayerFiles.getConfig(p);
		FileConfiguration config = cm.getConfig();
		int x = config.getInt("settlement.location.x");
		int y = config.getInt("settlement.location.y");
		int z = config.getInt("settlement.location.z");
	
		switch (title) {
		case "Townhall":
			switch (slot) {
			case 0:
				e.getWhoClicked().sendMessage(p.getName());
				break;
			case 1:				
				int upgradedTownhallLevel = config.getInt("settlement.townhall.level") + 1;
				
				if (upgradedTownhallLevel >= 8) {
					e.getWhoClicked().sendMessage(UtilsMsc.color("&7Reached &fmax &7level."));
					return;
				} else {
					e.getWhoClicked().sendMessage(UtilsMsc.color("&7Upgraded townhall to level &f" + upgradedTownhallLevel + "&7."));
				}
				
				Location loc = new Location(p.getWorld(), x, y, z);
				
				UtilsSchematic settlement = new UtilsSchematic("townhall_" + upgradedTownhallLevel);
				settlement.fastPasteSchematicV2(loc, false);
				
				config.set("settlement.townhall.level", upgradedTownhallLevel);
				cm.saveConfig();
				break;
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
