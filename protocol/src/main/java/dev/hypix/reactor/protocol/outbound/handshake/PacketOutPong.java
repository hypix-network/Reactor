package dev.hypix.reactor.protocol.outbound.handshake;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.util.buffer.DataSize;
import dev.hypix.reactor.api.util.buffer.ExpectedSizeBuffer;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutPong implements PacketOutbound {

    private final long timestamp;

    public PacketOutPong(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer data = new ExpectedSizeBuffer(DataSize.LONG);
        data.writeLong(timestamp);
        return data.buffer;
    }

    @Override
    public int getId() {
        return IdPacketOutbound.HANDSHAKE_PONG;
    }
}
