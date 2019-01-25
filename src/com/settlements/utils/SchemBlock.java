package com.settlements.utils;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BlockState;

public class SchemBlock {
	private final BlockVector3 blockVector3;
	private final BlockState blockState;
	
	public SchemBlock(BlockVector3 blockVector3, BlockState blockState) {
		this.blockVector3 = blockVector3;
		this.blockState = blockState;
	}
	
	public BlockVector3 getBlockVector3() {
		return blockVector3;
	}
	
	public BlockState getBlockState() {
		return blockState;
	}
}
