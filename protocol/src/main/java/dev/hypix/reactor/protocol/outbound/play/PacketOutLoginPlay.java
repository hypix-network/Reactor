package dev.hypix.reactor.protocol.outbound.play;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutLoginPlay implements PacketOutbound {

    @Override
    public byte[] write() {
        final FriendlyBuffer buffer = new FriendlyBuffer(1);
        return buffer.compress();
    }

    @Override
    public int getId() {
        return IdPacketOutbound.PLAY_LOGIN;
    }
}