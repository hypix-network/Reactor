package dev.hypix.reactor.protocol.outbound.handshake;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.util.buffer.DataSize;
import dev.hypix.reactor.api.util.buffer.ExpectedSizeBuffer;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutStatus implements PacketOutbound {

    public final ExpectedSizeBuffer outData;

    public PacketOutStatus(final String json) {
        outData = new ExpectedSizeBuffer(DataSize.string(json));
        outData.writeString(json);
    }

    @Override
    public byte[] write() {
        return outData.buffer;
    }

    @Override
    public int getId() {
        return IdPacketOutbound.HANDSHAKE_STATUS;
    }
}
