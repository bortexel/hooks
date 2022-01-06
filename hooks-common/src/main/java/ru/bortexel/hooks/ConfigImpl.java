package ru.bortexel.hooks;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Properties;

public class ConfigImpl extends Properties implements Config {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    static ConfigImpl loadConfig(File file) throws IOException {
        if (!file.exists()) file.createNewFile();
        return loadConfig(new FileInputStream(file));
    }

    static ConfigImpl loadConfig(InputStream inputStream) throws IOException {
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        ConfigImpl config = new ConfigImpl();
        config.load(reader);
        return config;
    }

    @Override
    public String getApiUrl() {
        return this.getProperty("api.url", "https://api.bortexel.ru/v3");
    }

    @Override
    public Optional<String> getApiToken() {
        return Optional.ofNullable(this.getProperty("api.token"));
    }
}
