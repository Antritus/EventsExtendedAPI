package com.qntcore.minecraft_server.eventsextendedapi.events.block;

import com.qntcore.minecraft_server.eventsextendedapi.BedData;
import lombok.Getter;
import org.bukkit.block.Block;

public class BedExplodePrepareEvent extends ExtendedBlockExplosionPrepareEvent{
	@Getter
	private final BedData bed;
	public BedExplodePrepareEvent(Block theBlock, BedData bed) {
		super(theBlock);
		this.bed = bed;
	}
}
