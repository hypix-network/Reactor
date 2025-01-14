package dev.hypix.reactor.internal;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.tinylog.Logger;
import org.yaml.snakeyaml.Yaml;

import dev.hypix.reactor.api.Reactor;
import dev.hypix.reactor.api.ReactorServer;
import dev.hypix.reactor.api.config.FileUtil;
import dev.hypix.reactor.api.config.server.ServerConfig;
import dev.hypix.reactor.internal.config.ServerConfigLoader;
import dev.hypix.reactor.internal.console.Console;
import dev.hypix.reactor.internal.console.ConsoleStart;
import dev.hypix.reactor.internal.tick.MainThread;
import dev.hypix.reactor.protocol.ServerConnection;

public final class ReactorMain {

    public static void main(String[] args) {
        final Console console = startServer();
        if (console != null) {
            console.run();
        }
    }

    private static Console startServer() {
        final ServerConnection serverConnection = new ServerConnection();
        final File mainDirectory = new File("").getAbsoluteFile();
        final ServerConfig config = new ServerConfigLoader(mainDirectory)
            .load(new FileUtil(mainDirectory, new Yaml(), ReactorServer.class.getClassLoader()));

        try {
            Logger.info("Starting server");
            serverConnection.connect(config.ip(), config.port());
        } catch (final Exception e) {
            Logger.error("Error starting the server");
            Logger.error(e);
            return null;
        }

        final Console console = new ConsoleStart().createConsole();
        if (console == null) {
            return null;
        }

        final ScheduledExecutorService mainExecutorService = Executors.newScheduledThreadPool(1);
        final MainThread mainThread = new MainThread(serverConnection, mainExecutorService);
        final ReactorServerImpl server = new ReactorServerImpl(config, mainThread, console);

        Reactor.setServer(server);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> server.onExit()));

        server.getPluginManager().loadPlugins(new File(mainDirectory, "plugins"));
        server.getPluginManager().enablePlugins();

        serverConnection.registerDefaultHandlers();
        return console;
    }
}
