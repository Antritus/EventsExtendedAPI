package com.qntcore.minecraft_server.eventsextendedapi.internal;

public class Replace {
	public static void main (String[] args){
		String[] items = new String[]{"NETHERITE_SWORD","DIAMOND_SWORD","IRON_SWORD","GOLD_SWORD","STONE_SWORD","WOODEN_SWORD"};
		String[] replaces = new String[]{"PICKAXE","AXE","HOE"};
		for (String replace : replaces){
			System.out.println("(");
			System.out.println("");
			for (String item : items){
				System.out.print("\"" + item.replace("SWORD", replace)+"\", ");
			}
		}
		System.out.print(")");
	}
}
