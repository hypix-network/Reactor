package dev.hypix.reactor.protocol.outbound.configuration;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.util.buffer.ExpectedSizeBuffer;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutResetChat implements PacketOutbound {
    public static final PacketOutResetChat INSTANCE = new PacketOutResetChat();

    @Override
    public byte[] write() {
        return ExpectedSizeBuffer.EMPTY_BUFFER;
    }
    @Override
    public int getId() {
        return IdPacketOutbound.CONFIG_RESET_CHAT;
    }
}