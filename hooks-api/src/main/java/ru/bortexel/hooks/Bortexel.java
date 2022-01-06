package ru.bortexel.hooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruscalworld.bortexel4j.Bortexel4J;

public class Bortexel {
    static final Logger LOGGER = LoggerFactory.getLogger("Bortexel");
    private static Bortexel instance;

    private final Bortexel4J apiClient;
    private final Config config;

    public Bortexel(Config config, Bortexel4J apiClient) {
        this.config = config;
        this.apiClient = apiClient;
    }

    public static boolean isAvailable() {
        return instance != null;
    }

    public static Bortexel getInstance() {
        return instance;
    }

    static void init(Config config, Bortexel4J client) {
        instance = new Bortexel(config, client);
    }

    public static Bortexel4J getApiClient() {
        return getInstance().apiClient;
    }

    public static Config getConfig() {
        return getInstance().config;
    }
}
