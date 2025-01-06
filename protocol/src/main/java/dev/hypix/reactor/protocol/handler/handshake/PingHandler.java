package dev.hypix.reactor.protocol.handler.handshake;

import dev.hypix.reactor.protocol.PlayerConnectionImpl;
import dev.hypix.reactor.protocol.handler.PacketHandler;
import dev.hypix.reactor.protocol.inbound.IdPacketInbound;
import dev.hypix.reactor.protocol.inbound.PacketInData;
import dev.hypix.reactor.protocol.outbound.handshake.PacketOutPong;

final class PingHandler implements PacketHandler {

    @Override
    public void handle(final PlayerConnectionImpl connection, final int packetId, final PacketInData data) {
        connection.sendPacket(new PacketOutPong(data.buffer.readLong()));
        connection.getChannel().close();
    }

    @Override
    public int packetId() {
        return IdPacketInbound.HANDSHAKE_PING;
    }
}