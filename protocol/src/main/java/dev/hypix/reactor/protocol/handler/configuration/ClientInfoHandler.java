package dev.hypix.reactor.protocol.handler.configuration;

import dev.hypix.reactor.protocol.PlayerConnectionImpl;
import dev.hypix.reactor.protocol.handler.PacketHandler;
import dev.hypix.reactor.protocol.inbound.IdPacketInbound;
import dev.hypix.reactor.protocol.inbound.PacketInData;
import dev.hypix.reactor.protocol.inbound.configuration.PacketInClientInformation;

final class ClientInfoHandler implements PacketHandler {

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        new PacketInClientInformation(connection.getPlayer()).read(data);
    }

    @Override
    public int packetId() {
        return IdPacketInbound.CONFIG_CLIENT_INFO;
    }
}