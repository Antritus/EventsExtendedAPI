package com.qntcore.minecraft_server.eventsextendedapi.events.block;

import lombok.Getter;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.event.Cancellable;
import org.bukkit.event.player.PlayerInteractEvent;

public class RespawnAnchorExplodePrepareEvent extends ExtendedBlockExplosionPrepareEvent implements Cancellable {
	@Getter
	final private RespawnAnchor respawnAnchor;
	@Getter
	final private PlayerInteractEvent event;
	public RespawnAnchorExplodePrepareEvent(PlayerInteractEvent event, Block theBlock, RespawnAnchor respawnAnchor) {
		super(theBlock);
		this.respawnAnchor = respawnAnchor;
		this.event = event;
	}

	@Override
	public boolean isCancelled() {
		return event.isCancelled();
	}

	@Override
	public void setCancelled(boolean cancel) {
		event.setCancelled(cancel);
	}
}
