package com.qntcore.minecraft_server.eventsextendedapi.events.blockbreak;

import lombok.Getter;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtendedBlockBreakEvent extends PlayerEvent implements Cancellable {
	private static final HandlerList HANDLERS = new HandlerList();

	public static HandlerList getHandlerList() {
		return HANDLERS;
	}

	@Override
	public HandlerList getHandlers() {
		return HANDLERS;
	}

	protected BlockBreakEvent e;
	@Getter
	private final Block block;
	@Getter
	private final ToolType toolType;
	@Getter
	private final ItemStack tool;
	@Getter
	private final boolean hasTool;
	@Getter
	private final GameMode gameMode;
	@Getter
	private final Location location;
	@Getter
	private final List<ItemStack> drops = null;
	public ExtendedBlockBreakEvent(BlockBreakEvent e, Block theBlock, Player player) {
		super(player);
		this.e = e;
		this.block = theBlock;
		this.tool = player.getInventory().getItemInMainHand();
		this.hasTool = this.tool.getType() != Material.AIR;
		this.gameMode = player.getGameMode();
		this.location = block.getLocation();
		toolType = ToolType.getToolType(
				player.getInventory().getItemInMainHand());
//		for(Object obj : block.getDrops(tool, player).toArray()){
//		drops.add((ItemStack) obj);
//		}
	}

	@Override
	public boolean isCancelled() {
		return e.isCancelled();
	}

	@Override
	public void setCancelled(boolean cancel) {
		e.setCancelled(cancel);
	}

	public int getExpToDrop() {
		return e.getExpToDrop();
	}

	public void setExpToDrop(int exp){
		e.setExpToDrop(exp);
	}


	public boolean dropItems(){
		return e.isDropItems();
	}
	public void setDropItems(boolean drop){
		e.setDropItems(drop);
	}
	public void setDrops(List<ItemStack> items){
//		this.drops.clear();
//		this.drops.addAll(items);
	}


	public enum ToolType {
		HAND,
		ITEM,
		BLOCK,
		SWORD("NETHERITE_SWORD","DIAMOND_SWORD","IRON_SWORD","GOLDEN_SWORD","STONE_SWORD","WOODEN_SWORD"),
		PICKAXE("NETHERITE_PICKAXE", "DIAMOND_PICKAXE", "IRON_PICKAXE", "GOLDEN_PICKAXE", "STONE_PICKAXE", "WOODEN_PICKAXE"),
		AXE("NETHERITE_AXE", "DIAMOND_AXE", "IRON_AXE", "GOLDEN_AXE", "STONE_AXE", "WOODEN_AXE"),
		HOE("NETHERITE_HOE", "DIAMOND_HOE", "IRON_HOE", "GOLDEN_HOE", "STONE_HOE", "WOODEN_HOE"),
		SHEARS("SHEARS"),
		UNKNOWN,
		;

		private final Material[] materials;

		ToolType(String... materials){
			List<Material> mats = new ArrayList<>();
			for (String material : materials){
				try {
					Field field = Material.class.getDeclaredField(material);
					field.setAccessible(true);
					mats.add((Material) field.get(null));
				} catch (NoSuchFieldException e) {
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
			this.materials = mats.toArray(new Material[1]);
		}
		public static ToolType getToolType(ItemStack itemStack){
			if (itemStack.getType() == Material.AIR){
				return ToolType.HAND;
			}
			if (itemStack.getType().isBlock()){
				return ToolType.BLOCK;
			}
			for (ToolType toolType1 : ToolType.values()){
				for (Material material : toolType1.materials){
					if (material == itemStack.getType()){
						return toolType1;
					}
				}
			}
			return ToolType.ITEM;
		}
	}
}
