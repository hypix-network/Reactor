package dev.hypix.reactor.protocol.outbound.login;

import java.util.UUID;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.util.buffer.DataSize;
import dev.hypix.reactor.api.util.buffer.ExpectedSizeBuffer;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutLoginSuccess implements PacketOutbound {

    private final UUID uuid;
    private final String username;

    // TODO: Properties

    public PacketOutLoginSuccess(final UUID uuid, final String username) {
        this.uuid = uuid;
        this.username = username;
    }

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer data = new ExpectedSizeBuffer(
            DataSize.UUID +
            DataSize.string(username) +
            DataSize.BYTE
        );

        data.writeUUID(uuid);
        data.writeString(username);
        data.writeVarInt(0); // Properties

        return data.buffer;
    }

    @Override
    public int getId() {
        return IdPacketOutbound.LOGIN_SUCCESS;
    }
}