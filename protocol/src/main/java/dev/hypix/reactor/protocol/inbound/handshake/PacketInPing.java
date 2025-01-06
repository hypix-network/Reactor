package dev.hypix.reactor.protocol.inbound.handshake;

import dev.hypix.reactor.protocol.inbound.PacketInData;
import dev.hypix.reactor.protocol.inbound.PacketInbound;

public final class PacketInPing implements PacketInbound {

    public long timestamp;

    @Override
    public void read(PacketInData data) {
        timestamp = data.buffer.readLong();
    }
}
