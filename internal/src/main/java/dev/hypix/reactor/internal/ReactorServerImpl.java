package dev.hypix.reactor.internal;

import org.tinylog.Logger;

import dev.hypix.reactor.api.ReactorServer;
import dev.hypix.reactor.api.config.server.ServerConfig;
import dev.hypix.reactor.api.entity.EntityCreator;
import dev.hypix.reactor.api.scheduler.ServerScheduler;
import dev.hypix.reactor.api.world.WorldManager;
import dev.hypix.reactor.internal.console.Console;
import dev.hypix.reactor.internal.entity.EntityCreatorImpl;
import dev.hypix.reactor.internal.tick.MainThread;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
final class ReactorServerImpl implements ReactorServer {

    private final ServerConfig config;
    private final MainThread mainThread;
    private final Console console;

    private final EntityCreator entityCreator = new EntityCreatorImpl();
    private final WorldManager worldManager = new WorldManager();

    @Override
    public String getVersion() {
        return "1.21.1";
    }

    @Override
    public ServerScheduler getScheduler() {
        return mainThread.getScheduler();
    }

    @Override
    public void stop() {
        Logger.info("Stopping server...");
        System.exit(0);
    }

    void onExit() {
        mainThread.stop(() -> console.stop());
    }
}