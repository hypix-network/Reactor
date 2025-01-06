package dev.hypix.reactor.protocol.handler.play;

import org.tinylog.Logger;

import dev.hypix.reactor.protocol.PlayerConnectionImpl;
import dev.hypix.reactor.protocol.outbound.play.PacketOutLoginPlay;

public final class PlayHandler {

    public static void startPlay(final PlayerConnectionImpl connection) {
        connection.sendPacket(new PacketOutLoginPlay());
        Logger.info("SEXO");
    }

    public static void registerHandlers() {

    }
}
