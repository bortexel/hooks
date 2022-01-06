package ru.bortexel.hooks;

import net.fabricmc.api.DedicatedServerModInitializer;

public class HooksFabric implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        throw new RuntimeException("Fabric version is not implemented yet");
    }
}
