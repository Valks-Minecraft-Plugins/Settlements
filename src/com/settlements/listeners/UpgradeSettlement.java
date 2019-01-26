package com.settlements.listeners;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.MetadataValue;

import com.settlements.structures.StructureTownhall;

public class UpgradeSettlement implements Listener {
	@EventHandler
	private void playerInteractEvent(PlayerInteractEvent e) {
		Block b = e.getClickedBlock();
		
		if (b == null)
			return;
		
		if (b.getType() != Material.EMERALD_BLOCK)
			return;
		
		if (e.getAction() != Action.RIGHT_CLICK_BLOCK)
			return;
		
		List<MetadataValue> metaOwner = b.getMetadata("Owner");
		
		if (metaOwner == null)
			return;
		
		Player p = e.getPlayer();
		
		if (metaOwner.get(0).asString() != p.getName())
			return;
	
		StructureTownhall townhall = new StructureTownhall(p, "Townhall");
		p.openInventory(townhall.getInventory());
	}
}
