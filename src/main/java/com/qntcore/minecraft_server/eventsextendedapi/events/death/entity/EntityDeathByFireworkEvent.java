package com.qntcore.minecraft_server.eventsextendedapi.events.death.entity;

import lombok.Getter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class EntityDeathByFireworkEvent extends EntityDeathByEntityEvent{
	public EntityDeathByFireworkEvent(EntityDamageByEntityEvent damageEvent, EntityDeathEvent event) {
		super(damageEvent, event);
	}
	public Firework getFirework() {
		return (Firework) ((EntityDamageByEntityEvent) damageEvent).getDamager();
	}
}
