package dev.hypix.reactor.protocol.handler.handshake;

import dev.hypix.reactor.api.Reactor;
import dev.hypix.reactor.protocol.ConnectionState;
import dev.hypix.reactor.protocol.PlayerConnectionImpl;
import dev.hypix.reactor.protocol.handler.PacketHandler;
import dev.hypix.reactor.protocol.inbound.IdPacketInbound;
import dev.hypix.reactor.protocol.inbound.PacketInData;
import dev.hypix.reactor.protocol.inbound.handshake.PacketInStatus;
import dev.hypix.reactor.protocol.outbound.handshake.PacketOutStatus;

public final class HandshakeHandler implements PacketHandler {

    private static final int
        STATUS = 1,
        LOGIN = 2,
        TRANSFER = 3;

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        if (data.buffer.readableBytes() == 0) { // Status Request
            connection.sendPacket(new PacketOutStatus(Reactor.getServer().getConfig().motd()));
            return;
        }

        final PacketInStatus packetInHandshake = new PacketInStatus();
        packetInHandshake.read(data);

        switch (packetInHandshake.state) {
            case STATUS:
                connection.state = ConnectionState.HANDSHAKE;
                break;
            case LOGIN:
                connection.state = ConnectionState.LOGIN;
                break;
            case TRANSFER:
                // TODO: Que se conecte networkManager.channel.connect()
                connection.getChannel().close();
                break;
            default:
                break;
        }
    }

    @Override
    public int packetId() {
        return IdPacketInbound.HANDSHAKE_STATUS;
    }

    public static void registerHandlers() {
        ConnectionState.HANDSHAKE.add(new HandshakeHandler(), new PingHandler());
    }
}
