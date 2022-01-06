package ru.bortexel.hooks;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import ru.ruscalworld.bortexel4j.Bortexel4J;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Hooks {
    public static void init(File configFile) throws Exception {
        Bortexel.LOGGER.info("Initializing Bortexel hooks");
        ConfigImpl config = ConfigImpl.loadConfig(configFile);
        initClient(config);
    }

    public static void init(InputStream configFile) throws Exception {
        Bortexel.LOGGER.info("Initializing Bortexel hooks");
        ConfigImpl config = ConfigImpl.loadConfig(configFile);
        initClient(config);
    }

    private static void initClient(Config config) {
        Bortexel4J client = Bortexel4J.login(config.getApiToken().orElse(null), config.getApiUrl());
        Bortexel.init(config, client);
    }

    public static void shutdown() {
        if (!Bortexel.isAvailable()) {
            Bortexel.LOGGER.warn("Skipped Bortexel hooks shutdown due to unavailability of API");
            return;
        }

        Bortexel.LOGGER.info("Shutting down Bortexel hooks");

        OkHttpClient httpClient = Bortexel.getApiClient().getHttpClient();
        httpClient.dispatcher().executorService().shutdown();
        httpClient.connectionPool().evictAll();

        try {
            Cache cache = httpClient.cache();
            if (cache != null) cache.close();
        } catch (IOException ignored) { }

        Bortexel.LOGGER.info("Successfully shut down Bortexel hooks");
    }
}
