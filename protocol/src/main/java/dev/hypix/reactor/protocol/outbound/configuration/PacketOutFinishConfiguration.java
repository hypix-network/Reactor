package dev.hypix.reactor.protocol.outbound.configuration;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.util.buffer.ExpectedSizeBuffer;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutFinishConfiguration implements PacketOutbound {

    @Override
    public byte[] write() {
        return ExpectedSizeBuffer.EMPTY_BUFFER;
    }

    @Override
    public int getId() {
        return IdPacketOutbound.CONFIG_FINISH;
    }
}
