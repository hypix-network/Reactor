package dev.hypix.reactor.protocol.outbound.configuration;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.util.buffer.DataSize;
import dev.hypix.reactor.api.util.buffer.ExpectedSizeBuffer;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutPluginMessage implements PacketOutbound {

    private final String channel;
    private final byte[] data;

    @Override
    public byte[] write() {
        final ExpectedSizeBuffer eBuffer = new ExpectedSizeBuffer(DataSize.string(channel) + DataSize.prefixBytes(data));
        eBuffer.writeString(channel);
        eBuffer.writeVarInt(data.length);
        eBuffer.writeBytes(data);
        return eBuffer.buffer;
    }

    @Override
    public int getId() {
        return IdPacketOutbound.CONFIG_PLUGIN_MESSAGE;
    }
}