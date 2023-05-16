package com.qntcore.minecraft_server.eventsextendedapi.listeners;

import com.qntcore.minecraft_server.eventsextendedapi.EventsExtendedAPI;
import com.qntcore.minecraft_server.eventsextendedapi.armor.ArmorEquipMethod;
import com.qntcore.minecraft_server.eventsextendedapi.armor.ArmorSlot;
import com.qntcore.minecraft_server.eventsextendedapi.events.inventory.ArmorEquipEvent;

import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Arnah
 * @since Jul 30, 2015
 */
public class ArmorChangeListener implements Listener{

	private final List<String> blockedMaterials;

	public ArmorChangeListener(){
		// loading config file from resources rather than loading it from enabled plugin
		BufferedReader reader = new BufferedReader(new InputStreamReader(ArmorChangeListener.class.getResourceAsStream("/internal/armorchangeevent.yml")));
		List<String> lines = new ArrayList<>();
		for (Object line : reader.lines().toArray()){
			lines.add(((String)line).replace("  - ", ""));
		}
		blockedMaterials = lines;
	}
	//Event Priority is highest because other plugins might cancel the events before we check.

	@EventHandler(priority =  EventPriority.HIGHEST, ignoreCancelled = true)
	public final void inventoryClick(final InventoryClickEvent e){
		boolean shift = false, numberkey = false;
		if(e.isCancelled()) return;
		if(e.getAction() == InventoryAction.NOTHING) return;// Why does this get called if nothing happens??
		if(e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.SHIFT_RIGHT)){
			shift = true;
		}
		if(e.getClick().equals(ClickType.NUMBER_KEY)){
			numberkey = true;
		}
		if(e.getSlotType() != SlotType.ARMOR && e.getSlotType() != SlotType.QUICKBAR && e.getSlotType() != SlotType.CONTAINER) return;
		if(e.getClickedInventory() != null && !e.getClickedInventory().getType().equals(InventoryType.PLAYER)) return;
		if (!e.getInventory().getType().equals(InventoryType.CRAFTING) && !e.getInventory().getType().equals(InventoryType.PLAYER)) return;
		if(!(e.getWhoClicked() instanceof Player)) return;
		ArmorSlot newArmorType = ArmorSlot.matches(shift ? e.getCurrentItem() : e.getCursor());
		if(!shift && newArmorType != null && e.getRawSlot() != newArmorType.getRawId()){
			// Used for drag and drop checking to make sure you aren't trying to place a helmet in the boots slot.
			return;
		}
		if(shift){
			newArmorType = ArmorSlot.matches(e.getCurrentItem());
			if(newArmorType != null){
				boolean equipping = true;
				if(e.getRawSlot() == newArmorType.getRawId()){
					equipping = false;
				}
				if(newArmorType.equals(ArmorSlot.HEAD) && (equipping ? isAirOrNull(e.getWhoClicked().getInventory().getHelmet()) : !isAirOrNull(e.getWhoClicked().getInventory().getHelmet())) || newArmorType.equals(ArmorSlot.CHEST) && (equipping ? isAirOrNull(e.getWhoClicked().getInventory().getChestplate()) : !isAirOrNull(e.getWhoClicked().getInventory().getChestplate())) || newArmorType.equals(ArmorSlot.LEGS) && (equipping ? isAirOrNull(e.getWhoClicked().getInventory().getLeggings()) : !isAirOrNull(e.getWhoClicked().getInventory().getLeggings())) || newArmorType.equals(ArmorSlot.BOOTS) && (equipping ? isAirOrNull(e.getWhoClicked().getInventory().getBoots()) : !isAirOrNull(e.getWhoClicked().getInventory().getBoots()))){
					ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent((Player) e.getWhoClicked(), ArmorEquipMethod.SHIFT, newArmorType, equipping ? e.getCurrentItem() : null, equipping ? null : e.getCurrentItem());
					Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
					if(armorEquipEvent.isCancelled()){
						e.setCancelled(true);
					}
				}
			}
		}else{
			ItemStack newArmorPiece = e.getCursor();
			ItemStack oldArmorPiece = e.getCurrentItem();
			if(numberkey){
				if(e.getClickedInventory().getType().equals(InventoryType.PLAYER)){// Prevents shit in the 2by2 crafting
					// e.getClickedInventory() == The players inventory
					// e.getHotBarButton() == key people are pressing to equip or unequip the item to or from.
					// e.getRawSlot() == The slot the item is going to.
					// e.getSlot() == Armor slot, can't use e.getRawSlot() as that gives a hotbar slot ;-;
					ItemStack hotbarItem = e.getClickedInventory().getItem(e.getHotbarButton());
					if(!isAirOrNull(hotbarItem)){// Equipping
						newArmorType = ArmorSlot.matches(hotbarItem);
						newArmorPiece = hotbarItem;
						oldArmorPiece = e.getClickedInventory().getItem(e.getSlot());
					}else{// Unequipping
						newArmorType = ArmorSlot.matches(!isAirOrNull(e.getCurrentItem()) ? e.getCurrentItem() : e.getCursor());
					}
				}
			}else{
				if(isAirOrNull(e.getCursor()) && !isAirOrNull(e.getCurrentItem())){// unequip with no new item going into the slot.
					newArmorType = ArmorSlot.matches(e.getCurrentItem());
				}
			}
			if(newArmorType != null && e.getRawSlot() == newArmorType.getRawId()){
				ArmorEquipMethod method =ArmorEquipMethod.PICK_DROP;
				if(e.getAction().equals(InventoryAction.HOTBAR_SWAP) || numberkey) method = ArmorEquipMethod.HOTBAR_SWAP;
				ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent((Player) e.getWhoClicked(), method, newArmorType, newArmorPiece, oldArmorPiece);
				Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
				if(armorEquipEvent.isCancelled()){
					e.setCancelled(true);
				}
			}
		}
	}

	@EventHandler(priority =  EventPriority.HIGHEST)
	public void playerInteractEvent(PlayerInteractEvent e){
		if(e.useItemInHand().equals(Result.DENY))return;
		//
		if(e.getAction() == Action.PHYSICAL) return;
		if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
			Player player = e.getPlayer();
			if(!e.useInteractedBlock().equals(Result.DENY)){
				if(e.getClickedBlock() != null && e.getAction() == Action.RIGHT_CLICK_BLOCK && !player.isSneaking()){// Having both of these checks is useless, might as well do it though.
					// Some blocks have actions when you right click them which stops the client from equipping the armor in hand.
					Material mat = e.getClickedBlock().getType();
					for(String s : blockedMaterials){
						if(mat.name().equalsIgnoreCase(s)) return;
					}
				}
			}
			ArmorSlot newArmorType = ArmorSlot.matches(e.getItem());
			if(newArmorType != null){
				if(newArmorType.equals(ArmorSlot.HEAD) && isAirOrNull(e.getPlayer().getInventory().getHelmet()) ||
						newArmorType.equals(ArmorSlot.CHEST) && isAirOrNull(e.getPlayer().getInventory().getChestplate()) ||
						newArmorType.equals(ArmorSlot.LEGS) && isAirOrNull(e.getPlayer().getInventory().getLeggings()) ||
						newArmorType.equals(ArmorSlot.BOOTS) && isAirOrNull(e.getPlayer().getInventory().getBoots())){
					ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(e.getPlayer(), ArmorEquipMethod.HOTBAR, ArmorSlot.matches(e.getItem()), e.getItem(), null);
					Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
					if(armorEquipEvent.isCancelled()){
						e.setCancelled(true);
						player.updateInventory();
					}
				} else if (EventsExtendedAPI.minecraftVersion > 120){
					// 1.20 will add bedrock feature of right clicking armor piece and allowing swapping places with old armor and hotbar item
					// Added by antritus
					ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(e.getPlayer(), ArmorEquipMethod.HOTBAR, ArmorSlot.matches(e.getItem()), e.getItem(), ArmorSlot.get(player, ArmorSlot.matches(e.getItem())));
					Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
					if (armorEquipEvent.isCancelled()){
						e.setCancelled(true);
						player.updateInventory();
					}
				}
			}
		}
	}

	@EventHandler(priority =  EventPriority.HIGHEST, ignoreCancelled = true)
	public void inventoryDrag(InventoryDragEvent event){
		// getType() seems to always be even.
		// Old Cursor gives the item you are equipping
		// Raw slot is the ArmorType slot
		// Can't replace armor using this method making getCursor() useless.
		ArmorSlot type = ArmorSlot.matches(event.getOldCursor());
		if(event.getRawSlots().isEmpty()) return;// Idk if this will ever happen
		if(type != null && type.getRawId() == event.getRawSlots().stream().findFirst().orElse(0)){
			ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent((Player) event.getWhoClicked(), ArmorEquipMethod.DRAG, type, event.getOldCursor(), null);
			Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
			if(armorEquipEvent.isCancelled()){
				event.setResult(Result.DENY);
				event.setCancelled(true);
			}
		}
		// Debug shit
		/*System.out.println("Slots: " + event.getInventorySlots().toString());
		System.out.println("Raw Slots: " + event.getRawSlots().toString());
		if(event.getCursor() != null){
			System.out.println("Cursor: " + event.getCursor().getType().name());
		}
		if(event.getOldCursor() != null){
			System.out.println("OldCursor: " + event.getOldCursor().getType().name());
		}
		System.out.println("Type: " + event.getType().name());*/
	}

	@EventHandler
	public void itemBreakEvent(PlayerItemBreakEvent e){
		ArmorSlot type = ArmorSlot.matches(e.getBrokenItem());
		if(type != null){
			Player p = e.getPlayer();
			ArmorEquipEvent armorEquipEvent = new ArmorEquipEvent(p, ArmorEquipMethod.BROKE, type, null, e.getBrokenItem());
			Bukkit.getServer().getPluginManager().callEvent(armorEquipEvent);
			if(armorEquipEvent.isCancelled()){
				ItemStack i = e.getBrokenItem().clone();
				i.setAmount(1);
				//  Assuming for 1.13.1+ servers
				Damageable damageable = (Damageable) i.getItemMeta();
				assert damageable != null;
				damageable.setDamage(damageable.getDamage()-1);
				i.setItemMeta((ItemMeta) damageable);
				if(type.equals(ArmorSlot.HEAD)){
					p.getInventory().setHelmet(i);
				}else if(type.equals(ArmorSlot.CHEST)){
					p.getInventory().setChestplate(i);
				}else if(type.equals(ArmorSlot.LEGS)){
					p.getInventory().setLeggings(i);
				}else if(type.equals(ArmorSlot.BOOTS)){
					p.getInventory().setBoots(i);
				}
			}
		}
	}

	@EventHandler
	public void playerDeathEvent(PlayerDeathEvent e){
		Player p = e.getEntity();
		if(e.getKeepInventory()) return;
		for(ItemStack i : p.getInventory().getArmorContents()){
			if(!isAirOrNull(i)){
				Bukkit.getServer().getPluginManager().callEvent(new ArmorEquipEvent(p, ArmorEquipMethod.DEATH, ArmorSlot.matches(i), null, i));
				// No way to cancel a death event.
			}
		}
	}

	/**
	 * A utility method to support versions that use null or air ItemStacks.
	 */
	public static boolean isAirOrNull(ItemStack item){
		return item == null || item.getType().equals(Material.AIR);
	}
}