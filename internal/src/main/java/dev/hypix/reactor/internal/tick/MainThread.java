package dev.hypix.reactor.internal.tick;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.tinylog.Logger;

import dev.hypix.reactor.api.scheduler.ServerScheduler;
import dev.hypix.reactor.protocol.ServerConnection;

public final class MainThread implements Runnable {

    private final TickScheduler scheduler = new TickScheduler();
    private final ScheduledExecutorService scheduledExecutorService;
    private final ServerConnection serverConnection;

    public MainThread(ServerConnection serverConnection, ScheduledExecutorService scheduledExecutorService) {
        this.serverConnection = serverConnection;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public void start() {
        scheduledExecutorService.scheduleAtFixedRate(this, 0, 50, TimeUnit.MILLISECONDS);
    }

    public void stop(final Runnable onStop) {       
        try {
            serverConnection.shutdown();

            scheduledExecutorService.shutdown();
            scheduledExecutorService.awaitTermination(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            Logger.error(e);
        } finally {
            onStop.run();
        }
    }

    @Override
    public void run() {
        serverConnection.tick();
        scheduler.tick();
    }

    public ServerScheduler getScheduler() {
        return scheduler;
    }
}