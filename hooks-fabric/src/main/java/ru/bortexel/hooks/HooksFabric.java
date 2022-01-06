package ru.bortexel.hooks;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.nio.file.Path;

public final class HooksFabric implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            try {
                Path configDir = FabricLoader.getInstance().getConfigDir();
                // noinspection ResultOfMethodCallIgnored
                configDir.toFile().mkdirs();
                File configFile = configDir.resolve("bortexel.properties").toFile();
                Hooks.init(configFile);
            } catch (Exception e) {
                Bortexel.LOGGER.error("Failed to initialize Bortexel hooks", e);
                Bortexel.LOGGER.info("Shutting down due to Bortexel hooks initialization failure");
                server.shutdown();
            }
        });

        ServerLifecycleEvents.SERVER_STOPPED.register(server -> Hooks.shutdown());
    }
}
