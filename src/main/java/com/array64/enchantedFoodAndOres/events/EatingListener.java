package com.array64.enchantedFoodAndOres.events;

import com.array64.enchantedFoodAndOres.EnchantedFoodAndOres;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class EatingListener implements Listener {
    @EventHandler
    public void onPlayerItemConsume(PlayerItemConsumeEvent e) {
        if(e.getItem().getItemMeta().getPersistentDataContainer().has(
                BlockDropListener.getItemTypeKey()
        )) {
            String meatName = e.getItem().getItemMeta().getPersistentDataContainer().get(
                    BlockDropListener.getItemTypeKey(),
                    PersistentDataType.STRING
            );
            EnchantedFoodAndOres.getInstance().getLogger().info(meatName);
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, (
                    ((Objects.equals(meatName, "COOKED_BEEF")) || (Objects.equals(meatName, "COOKED_PORKCHOP"))) ? 1200
                            : 900
                    ), 0));
        }
    }

}
