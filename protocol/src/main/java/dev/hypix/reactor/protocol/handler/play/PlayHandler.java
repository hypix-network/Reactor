package dev.hypix.reactor.protocol.handler.play;

import dev.hypix.reactor.api.Reactor;
import dev.hypix.reactor.api.world.WorldManager;
import dev.hypix.reactor.protocol.PlayerConnectionImpl;
import dev.hypix.reactor.protocol.outbound.play.PacketOutLoginPlay;

public final class PlayHandler {

    public static void startPlay(final PlayerConnectionImpl connection) {
        final WorldManager worldManager = Reactor.getServer().getWorldManager();

        connection.sendPacket(new PacketOutLoginPlay(
            connection,
            worldManager.getDefaultWorld()
        ));
    }

    public static void registerHandlers() {

    }
}
