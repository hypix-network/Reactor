package dev.hypix.reactor.protocol.handler.configuration;

import org.tinylog.Logger;

import dev.hypix.reactor.protocol.ConnectionState;
import dev.hypix.reactor.protocol.PlayerConnectionImpl;
import dev.hypix.reactor.protocol.handler.PacketHandler;
import dev.hypix.reactor.protocol.handler.play.PlayHandler;
import dev.hypix.reactor.protocol.inbound.IdPacketInbound;
import dev.hypix.reactor.protocol.inbound.PacketInData;

final class ClientAcknowledgeFinish implements PacketHandler {

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        connection.state = ConnectionState.PLAY;
        PlayHandler.startPlay(connection);

        Logger.info("test");
    }

    @Override
    public int packetId() {
        return IdPacketInbound.CONFIG_ACKNOWLEDGE_FINISH_CONFIG;
    }
}