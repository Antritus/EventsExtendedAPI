package com.qntcore.minecraft_server.eventsextendedapi.events.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;

public class ExtendedBlockExplosionPrepareEvent extends BlockEvent {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	public ExtendedBlockExplosionPrepareEvent(Block theBlock) {
		super(theBlock);
	}
}
