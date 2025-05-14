package com.array64.enchantedFoodAndOres.events;

import com.array64.enchantedFoodAndOres.EnchantedFoodAndOres;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.concurrent.ThreadLocalRandom;

public class BlockDropListener implements Listener {
    @EventHandler
    public void onBlockDrop(BlockDropItemEvent e) {
        double random = ThreadLocalRandom.current().nextDouble();
        if(ThreadLocalRandom.current().nextDouble() < 1d / 20) {
            for(Item i : e.getItems()) {
                enchantDrops(i.getItemStack());
            }
        }
    }
    public void enchantDrops(ItemStack item) {
        String itemName = EnchantedFoodAndOres.getInstance().getConfig().getString("oreName." + item.getType().name());
        ItemMeta meta = item.getItemMeta();
        if(meta != null) {
            meta.setDisplayName(ChatColor.AQUA + itemName);
            meta.setEnchantmentGlintOverride(true);
            item.setItemMeta(meta);
        }
        /* switch(item.getType()) {
            case COAL -> {

            }
            case RAW_IRON -> {

            }
            case RAW_GOLD -> {

            }
            case REDSTONE -> {

            }
            case DIAMOND -> {

            }
            case EMERALD -> {

            }
            case LAPIS_LAZULI -> {

            }
        } */
    }

}
