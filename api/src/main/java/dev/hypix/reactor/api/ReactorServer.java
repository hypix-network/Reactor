package dev.hypix.reactor.api;

import dev.hypix.reactor.api.config.server.ServerConfig;
import dev.hypix.reactor.api.entity.EntityCreator;
import dev.hypix.reactor.api.plugin.PluginManager;
import dev.hypix.reactor.api.scheduler.ServerScheduler;
import dev.hypix.reactor.api.world.WorldManager;

public interface ReactorServer {
    ServerConfig getConfig();
    EntityCreator getEntityCreator();
    PluginManager getPluginManager();
    WorldManager getWorldManager();
    ServerScheduler getScheduler();
    String getVersion();
    void stop();
}