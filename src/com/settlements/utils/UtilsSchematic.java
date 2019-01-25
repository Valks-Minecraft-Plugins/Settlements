package com.settlements.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import com.settlements.Settlements;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;

public class UtilsSchematic {
	Clipboard clipboard;
	
	public UtilsSchematic(String filename) {
		File file = new File(Settlements.plugin.getDataFolder().toString() + "\\schematics\\" + filename + ".schem");
		
		ClipboardFormat format = ClipboardFormats.findByFile(file);
		try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
		    clipboard = reader.read();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void pasteSchematicV2(Location loc, boolean ignoreAir) {
		List<UtilsSchemBlock> blocks = getSchematicData(loc, ignoreAir);
		
		EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(BukkitAdapter.adapt(loc.getWorld()), -1);
		
		new BukkitRunnable() {
			int counter = 0;
			
			@Override
			public void run() {
				UtilsSchemBlock schemBlock = blocks.get(counter);
				
				try {
					BlockVector3 position = schemBlock.getBlockVector3().subtract(clipboard.getOrigin()).add(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
					editSession.setBlock(position, schemBlock.getBlockState());
					editSession.flushSession();
				} catch (MaxChangedBlocksException e) {
					e.printStackTrace();
				}
				
				counter++;
				
				if (counter >= blocks.size()) {
					cancel();
				}
			}
		}.runTaskTimer(Settlements.plugin, 10, 1);
	}
	
	private List<UtilsSchemBlock> getSchematicData(Location loc, boolean ignoreAir) {
		List<UtilsSchemBlock> blocks = new ArrayList<UtilsSchemBlock>();
		
		for (BlockVector3 v : clipboard.getRegion()) {
			if (clipboard.getBlock(v).getBlockType() != BukkitAdapter.asBlockType(Material.AIR) || !ignoreAir) {
				blocks.add(new UtilsSchemBlock(v, clipboard.getBlock(v)));
			}
		}
		
		return blocks;
	}
	
	public void pasteSchematic(Location loc) {
		try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(BukkitAdapter.adapt(Bukkit.getWorld("world")), -1)) {
			
			Operation operation = new ClipboardHolder(clipboard)
			        .createPaste(editSession)
			        .to(BlockVector3.at(loc.getX(), loc.getY(), loc.getZ()))
			        .ignoreAirBlocks(false)
			        .build();
		    Operations.completeBlindly(operation);
		}
	}
}
