package com.array64.enchantedFoodAndOres.events;

import com.array64.enchantedFoodAndOres.EnchantedFoodAndOres;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.concurrent.ThreadLocalRandom;

public class BlockDropListener implements Listener {
    public static NamespacedKey getItemTypeKey() {
        return itemType;
    }

    static NamespacedKey itemType;

    public BlockDropListener() {
        itemType = new NamespacedKey(EnchantedFoodAndOres.getInstance(), "enchanted_type");
    }

    @EventHandler
    public void onBlockDrop(BlockDropItemEvent e) {
        if(ThreadLocalRandom.current().nextDouble() < 0.2d) {
            for(Item i : e.getItems()) {
                enchantDrops(i.getItemStack());
            }
        }
    }
    public void enchantDrops(ItemStack item) {
        String itemName = EnchantedFoodAndOres.getInstance().getConfig().getString("oreName." + item.getType().name());
        ItemMeta meta = item.getItemMeta();
        if(meta != null && CraftingListener.isTieredType(item.getType())) {
            meta.getPersistentDataContainer().set(itemType, PersistentDataType.STRING, item.getType().name());
            if(item.getType() != Material.RAW_IRON && item.getType() != Material.RAW_GOLD)
                meta.setDisplayName(ChatColor.AQUA + itemName);
            else if(item.getType() == Material.RAW_IRON) {
                meta.setDisplayName(ChatColor.AQUA + EnchantedFoodAndOres.getInstance().getConfig().getString("oreName.IRON_INGOT"));
                item.setType(Material.IRON_INGOT);
                meta.getPersistentDataContainer().set(itemType, PersistentDataType.STRING, "IRON_INGOT");
            }
            else {
                meta.setDisplayName(ChatColor.AQUA + EnchantedFoodAndOres.getInstance().getConfig().getString("oreName.GOLD_INGOT"));
                item.setType(Material.GOLD_INGOT);
                meta.getPersistentDataContainer().set(itemType, PersistentDataType.STRING, "GOLD_INGOT");
            }
            meta.setEnchantmentGlintOverride(true);
            item.setItemMeta(meta);
        }
    }

}
