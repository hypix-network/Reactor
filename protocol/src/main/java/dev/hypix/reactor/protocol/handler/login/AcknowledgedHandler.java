package dev.hypix.reactor.protocol.handler.login;

import dev.hypix.reactor.protocol.ConnectionState;
import dev.hypix.reactor.protocol.PlayerConnectionImpl;
import dev.hypix.reactor.protocol.handler.PacketHandler;
import dev.hypix.reactor.protocol.inbound.IdPacketInbound;
import dev.hypix.reactor.protocol.inbound.PacketInData;

final class AcknowledgedHandler implements PacketHandler {

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        connection.state = ConnectionState.CONFIGURATION;
        connection.isFirstConfig = false;
    }

    @Override
    public int packetId() {
        return IdPacketInbound.LOGIN_ACKNOWLEDGED;
    }
}