package ru.bortexel.hooks;

import java.util.Optional;

public interface Config {
    String getApiUrl();
    Optional<String> getApiToken();
}
