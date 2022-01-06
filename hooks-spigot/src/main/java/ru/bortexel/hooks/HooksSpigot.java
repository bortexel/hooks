package ru.bortexel.hooks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class HooksSpigot extends JavaPlugin {
    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        try {
            // noinspection ResultOfMethodCallIgnored
            this.getDataFolder().mkdirs();
            File configFile = this.getDataFolder().toPath().resolve("config.properties").toFile();
            Hooks.init(configFile);
        } catch (Exception e) {
            Bortexel.LOGGER.error("Failed to initialize Bortexel hooks", e);
            pluginManager.disablePlugin(this);

            Bortexel.LOGGER.info("Shutting down due to Bortexel hooks initialization failure");
            Bukkit.shutdown();
        }
    }

    @Override
    public void onDisable() {
        Hooks.shutdown();
    }
}
