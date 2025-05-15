package com.array64.enchantedFoodAndOres.events;

import com.array64.enchantedFoodAndOres.EnchantedFoodAndOres;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftingListener implements Listener {
    @EventHandler
    public void onCraftItem(CraftItemEvent e) {
        // Allow enchantments on tools and armor
        ItemStack result = e.getInventory().getResult();
        if(result == null) return;
        if(!isOverworldTieredItem(result.getType())) return;

        // Get number of items that are diamonds
        int enchantmentLevel = 0;
        for(ItemStack i : e.getInventory().getMatrix()) {
            if(i == null) continue;
            if(isTieredType(i.getType())
                    && i.getItemMeta().getPersistentDataContainer().has(
                            BlockDropListener.getItemTypeKey()
            )) enchantmentLevel++;
        }
        if(enchantmentLevel == 0) return;

        switch(result.getType().name().split("_")[1]) { // Add enchantments based on type of tool
            case "SWORD" -> result.addEnchantment(Enchantment.SHARPNESS, enchantmentLevel);
            case "SHOVEL", "HOE" -> result.addEnchantment(Enchantment.EFFICIENCY, enchantmentLevel);

            case "AXE" -> {
                result.addEnchantment(Enchantment.EFFICIENCY, enchantmentLevel);
                if(enchantmentLevel == 3)
                    result.addEnchantment(Enchantment.SHARPNESS, 2);
            }
            case "HELMET", "CHESTPLATE", "LEGGINGS", "BOOTS" ->
                result.addEnchantment(Enchantment.PROTECTION, (int) Math.round(((double) enchantmentLevel) / 2d));
            case "PICKAXE" ->
                result.addEnchantment(Enchantment.FORTUNE, enchantmentLevel);
        }
    }

    public boolean isOverworldTieredItem(Material type) {
        String name = type.name();
        return (name.endsWith("_SWORD")
                || name.endsWith("_PICKAXE")
                || name.endsWith("_AXE")
                || name.endsWith("_SHOVEL")
                || name.endsWith("_HOE")
                || name.endsWith("_HELMET")
                || name.endsWith("_CHESTPLATE")
                || name.endsWith("_LEGGINGS")
                || name.endsWith("_BOOTS"))
                && !name.startsWith("NETHERITE_");
    }
    public static boolean isTieredType(Material type) {
        return type == Material.IRON_INGOT
                || type == Material.GOLD_INGOT
                || type == Material.DIAMOND
                || type == Material.RAW_IRON
                || type == Material.RAW_GOLD;
    }
}
