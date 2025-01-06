package dev.hypix.reactor.protocol.outbound.login;

import dev.hypix.reactor.api.chat.ChatComponent;
import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.util.buffer.DataSize;
import dev.hypix.reactor.api.util.buffer.ExpectedSizeBuffer;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutLoginDisconnect implements PacketOutbound {

    private final byte[] data;

    public PacketOutLoginDisconnect(ChatComponent component) {
        data = fromJson(component.toJson());
    }

    public PacketOutLoginDisconnect(ChatComponent[] components) {
        data = components.length == 1
            ? fromJson(components[0].toJson()) 
            : fromJson(ChatComponent.toJson(components));
    }

    private static byte[] fromJson(final String json) {
        final ExpectedSizeBuffer data = new ExpectedSizeBuffer(DataSize.string(json));
        data.writeString(json);
        return data.buffer;
    }

    @Override
    public byte[] write() {
        return data;
    }

    @Override
    public int getId() {
        return IdPacketOutbound.LOGIN_DISCONNECT;
    }   
}
