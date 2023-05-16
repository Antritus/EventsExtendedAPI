package com.qntcore.minecraft_server.eventsextendedapi;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;

public class BedData {
	@Getter
	BlockData blockData;
	@Getter
	boolean occupied = false;
	@Getter
	BlockFace blockFace;
	@Getter
	boolean pillowSide;
	@Getter
	boolean bedData = true;
	public BedData(BlockData blockData){
		this.blockData = blockData;
		occupied = blockData.getAsString().contains("occupied=true");
		pillowSide = blockData.getAsString().contains("part=head");
		if (blockData.getAsString().contains("facing=north")){
			blockFace = BlockFace.NORTH;
		}else if (blockData.getAsString().contains("facing=south")){
			blockFace = BlockFace.SOUTH;
		}else if (blockData.getAsString().contains("facing=east")){
			blockFace = BlockFace.EAST;
		} else if (blockData.getAsString().contains("facing=west")){
			blockFace = BlockFace.WEST;
		} else {
			bedData = false;
		}
	}
}
