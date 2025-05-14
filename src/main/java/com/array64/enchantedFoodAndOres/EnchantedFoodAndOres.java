package com.array64.enchantedFoodAndOres;

import com.array64.enchantedFoodAndOres.events.BlockDropListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnchantedFoodAndOres extends JavaPlugin {

    static JavaPlugin instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        instance = this;
        getServer().getPluginManager().registerEvents(new BlockDropListener(), this);
    }
    public static JavaPlugin getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
