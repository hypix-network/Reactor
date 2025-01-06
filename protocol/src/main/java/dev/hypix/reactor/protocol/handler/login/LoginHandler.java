package dev.hypix.reactor.protocol.handler.login;

import org.tinylog.Logger;

import dev.hypix.reactor.api.Reactor;
import dev.hypix.reactor.protocol.ConnectionState;
import dev.hypix.reactor.protocol.PlayerConnectionImpl;
import dev.hypix.reactor.protocol.handler.PacketHandler;
import dev.hypix.reactor.protocol.inbound.IdPacketInbound;
import dev.hypix.reactor.protocol.inbound.PacketInData;
import dev.hypix.reactor.protocol.inbound.login.PacketInLoginStart;
import dev.hypix.reactor.protocol.outbound.login.PacketOutLoginSuccess;

public final class LoginHandler implements PacketHandler {

    @Override
    public void handle(final PlayerConnectionImpl connection, final int packetId, final PacketInData data) {
        final PacketInLoginStart packetInLoginStart = new PacketInLoginStart();
        packetInLoginStart.read(data);

        connection.sendPacket(new PacketOutLoginSuccess(packetInLoginStart.uuid, packetInLoginStart.username));
        connection.setPlayer(Reactor.getServer().getEntityCreator().createPlayer(packetInLoginStart.username, packetInLoginStart.uuid, connection));

        Logger.info("Player {} is trying to login", packetInLoginStart.username);
    }

    @Override
    public int packetId() {
        return IdPacketInbound.LOGIN_START;
    }

    public static void registerHandlers() {
        ConnectionState.LOGIN.add(new LoginHandler(), new AcknowledgedHandler());
    }
}
