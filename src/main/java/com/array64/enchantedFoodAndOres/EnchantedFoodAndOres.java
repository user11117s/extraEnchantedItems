package com.array64.enchantedFoodAndOres;

import com.array64.enchantedFoodAndOres.events.AnimalDropListener;
import com.array64.enchantedFoodAndOres.events.BlockDropListener;
import com.array64.enchantedFoodAndOres.events.CraftingListener;
import com.array64.enchantedFoodAndOres.events.EatingListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnchantedFoodAndOres extends JavaPlugin {

    static JavaPlugin instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        instance = this;
        getServer().getPluginManager().registerEvents(new BlockDropListener(), this);
        getServer().getPluginManager().registerEvents(new CraftingListener(), this);
        getServer().getPluginManager().registerEvents(new EatingListener(), this);
        getServer().getPluginManager().registerEvents(new AnimalDropListener(), this);
    }
    public static JavaPlugin getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
