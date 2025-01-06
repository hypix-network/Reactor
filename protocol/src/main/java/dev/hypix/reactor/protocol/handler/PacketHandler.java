package dev.hypix.reactor.protocol.handler;

import dev.hypix.reactor.protocol.PlayerConnectionImpl;
import dev.hypix.reactor.protocol.inbound.PacketInData;

public interface PacketHandler {
    void handle(final PlayerConnectionImpl connection, final int packetId, final PacketInData data);
    int packetId();
}
