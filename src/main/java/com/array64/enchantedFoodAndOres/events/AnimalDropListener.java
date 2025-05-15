package com.array64.enchantedFoodAndOres.events;

import com.array64.enchantedFoodAndOres.EnchantedFoodAndOres;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.concurrent.ThreadLocalRandom;

import com.array64.enchantedFoodAndOres.events.BlockDropListener;

public class AnimalDropListener implements Listener {

    @EventHandler
    public void onAnimalDrop(EntityDeathEvent e) {
        if(ThreadLocalRandom.current().nextDouble() < 0.2d) {
            for(ItemStack i : e.getDrops()) {
                cookDrops(i);
                enchantDrops(i);
            }
        }
    }
    public void cookDrops(ItemStack stack) {
        switch(stack.getType()) {
            case BEEF -> stack.setType(Material.COOKED_BEEF);
            case CHICKEN -> stack.setType(Material.COOKED_CHICKEN);
            case PORKCHOP -> stack.setType(Material.COOKED_PORKCHOP);
            case MUTTON -> stack.setType(Material.COOKED_MUTTON);
        }
    }
    public boolean isEnchantableMeat(Material meat) {
        return meat == Material.BEEF
                || meat == Material.CHICKEN
                || meat == Material.PORKCHOP
                || meat == Material.MUTTON
                || meat == Material.COOKED_BEEF
                || meat == Material.COOKED_CHICKEN
                || meat == Material.COOKED_PORKCHOP
                || meat == Material.COOKED_MUTTON;
    }
    public void enchantDrops(ItemStack item) {
        String itemName = EnchantedFoodAndOres.getInstance().getConfig().getString("meatName." + item.getType().name());
        ItemMeta meta = item.getItemMeta();
        if(meta != null && isEnchantableMeat(item.getType())) {
            meta.getPersistentDataContainer().set(BlockDropListener.getItemTypeKey(), PersistentDataType.STRING, item.getType().name());
            meta.setDisplayName(ChatColor.AQUA + itemName);
            meta.setEnchantmentGlintOverride(true);
            item.setItemMeta(meta);
        }
    }

}
