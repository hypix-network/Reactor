package dev.hypix.reactor.protocol.outbound.configuration;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.util.buffer.DataSize;
import dev.hypix.reactor.api.util.buffer.ExpectedSizeBuffer;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutFeatureFlags implements PacketOutbound {

    private final String[] identifiers;

    public PacketOutFeatureFlags(String... identifiers) {
        this.identifiers = identifiers;
    }

    @Override
    public byte[] write() {
        int size = DataSize.varInt(identifiers.length);
        for (final String id : identifiers) {
            size += DataSize.string(id);
        }

        final ExpectedSizeBuffer eBuffer = new ExpectedSizeBuffer(size);
        eBuffer.writeVarInt(identifiers.length);
        for (final String id : identifiers) {
            eBuffer.writeString(id);
        }
        return eBuffer.buffer;
    }

    @Override
    public int getId() {
        return IdPacketOutbound.CONFIG_FEATURE_FLAGS;
    }
}
