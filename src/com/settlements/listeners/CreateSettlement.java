package com.settlements.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;

import com.settlements.Settlements;
import com.settlements.configs.PlayerFiles;
import com.settlements.utils.Hologram;
import com.settlements.utils.Schematic;

public class CreateSettlement implements Listener {
	@EventHandler
	private void blockPlaceEvent(BlockPlaceEvent e) {
		Block b = e.getBlock();
		
		if (b.getType() == Material.EMERALD_BLOCK) {
			Player p = e.getPlayer();
			
			b.setMetadata("Owner", new FixedMetadataValue(Settlements.plugin, p.getName()));
			b.setMetadata("Structure", new FixedMetadataValue(Settlements.plugin, "TOWNHALL"));
			
			PlayerFiles cm = PlayerFiles.getConfig(p);
			FileConfiguration config = cm.getConfig();
			
			Location loc = e.getBlock().getLocation();
			
			// Settlement Origin - Everything centers from the origin with set offsets.
			config.set("settlement.location.x", loc.getBlockX());
			config.set("settlement.location.y", loc.getBlockY());
			config.set("settlement.location.z", loc.getBlockZ());
			
			config.set("settlement.townhall.level", 1); // Has crafting table, enchantment table, etc.
			config.set("settlement.mines.level", 0); // Respawnable Ores
			config.set("settlement.storage.level", 0); // Wood, Stone, Gold, Iron, Diamond, Emerald Storage and Chests, Enderchest?
			config.set("settlement.blacksmith.level", 0); // Weapons and Armor, Furnaces
			config.set("settlement.stable.level", 0); // Sells mounts and allows the player to use a unique special horse and other animals. Animals like chickens drop diamonds etc.
			config.set("settlement.farm.level", 0); // Crops
			config.set("settlement.bakery.level", 0); // Food
			config.set("settlement.defenses.level", 0); // Towers, Walls, Gates, NPCs guarding
			config.set("settlement.market.level", 0); // Sells a little bit of everything.
			config.set("settlement.brewery.level", 0); // Sells potions / potion ingredients. Has brewing stands.
			config.set("settlement.blackmarket.level", 0); // Sells exotic rare items.
			
			cm.saveConfig();
			
			Schematic settlement = new Schematic("townhall_1");
			settlement.pasteSchematicV2(loc);
			
			Hologram hg = new Hologram(loc.add(0.5, 0, 0.5), e.getPlayer().getName() + "'s Settlement");
			hg.setVisible(true);
		}
	}
}
