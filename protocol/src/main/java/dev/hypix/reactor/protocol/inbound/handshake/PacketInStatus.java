package dev.hypix.reactor.protocol.inbound.handshake;

import dev.hypix.reactor.protocol.inbound.PacketInData;
import dev.hypix.reactor.protocol.inbound.PacketInbound;

public final class PacketInStatus implements PacketInbound {

    public int protocolVersion;
    public String hostname;
    public int port;
    public int state;

    @Override
    public void read(final PacketInData data) {
        protocolVersion = data.readVarInt();
        hostname = data.readString(255);
        port = data.buffer.readUnsignedShort();
        state = data.readVarInt();
    }
}