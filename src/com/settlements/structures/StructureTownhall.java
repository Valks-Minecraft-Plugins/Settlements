package com.settlements.structures;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.settlements.utils.UtilsItem;

public class StructureTownhall extends Structure {
	public StructureTownhall(Player p, String name) {
		super(p, name);
		super.getInventory().setItem(0, UtilsItem.item("Owner", "The founder of the settlement.", Material.FEATHER));
		super.getInventory().setItem(1, UtilsItem.item("Upgrade", "Upgrade the townhall.", Material.BRICKS));
	}
}
