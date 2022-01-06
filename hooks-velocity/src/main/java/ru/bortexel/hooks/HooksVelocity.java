package ru.bortexel.hooks;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import org.slf4j.Logger;

@Plugin(
        id = "hooks-velocity",
        name = "Hooks",
        version = BuildConstants.VERSION,
        description = "Collection of hooks for Bortexel server extensions",
        url = "https://bortexel.ru",
        authors = { "RuscalWorld" }
)
public class HooksVelocity {

    @Inject
    private Logger logger;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
    }
}
