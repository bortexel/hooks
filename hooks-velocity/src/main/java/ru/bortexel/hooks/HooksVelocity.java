package ru.bortexel.hooks;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;

import java.io.File;
import java.nio.file.Path;

@Plugin(
        id = "bortexel",
        name = "Hooks",
        version = BuildConstants.VERSION,
        description = "Collection of hooks for Bortexel server extensions",
        url = "https://bortexel.ru",
        authors = { "RuscalWorld" }
)
public final class HooksVelocity {
    private final Path dataFolder;
    private final ProxyServer proxy;

    @Inject
    public HooksVelocity(ProxyServer proxy, @DataDirectory Path dataFolder) {
        this.proxy = proxy;
        this.dataFolder = dataFolder;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        try {
            // noinspection ResultOfMethodCallIgnored
            this.getDataFolder().toFile().mkdirs();
            File configFile = this.getDataFolder().resolve("config.properties").toFile();
            Hooks.init(configFile);
        } catch (Exception e) {
            Bortexel.LOGGER.error("Failed to initialize Bortexel hooks", e);
            Bortexel.LOGGER.info("Shutting down due to Bortexel hooks initialization failure");
            this.getProxy().shutdown();
        }
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        Hooks.shutdown();
    }

    public Path getDataFolder() {
        return dataFolder;
    }

    public ProxyServer getProxy() {
        return proxy;
    }
}
