package dev.hypix.reactor.protocol.inbound.login;

import java.util.UUID;

import dev.hypix.reactor.protocol.inbound.PacketInData;
import dev.hypix.reactor.protocol.inbound.PacketInbound;

public final class PacketInLoginStart implements PacketInbound {

    public String username;
    public UUID uuid;

    @Override
    public void read(PacketInData data) {
        this.username = data.readString(16);
        this.uuid = new UUID(data.buffer.readLong(), data.buffer.readLong());
    }
}
