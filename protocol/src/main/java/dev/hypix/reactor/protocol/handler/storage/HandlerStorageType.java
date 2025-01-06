package dev.hypix.reactor.protocol.handler.storage;

import dev.hypix.reactor.protocol.PlayerConnectionImpl;
import dev.hypix.reactor.protocol.inbound.PacketInData;

public interface HandlerStorageType {
    void handle(final PlayerConnectionImpl connection, final int packetId, final PacketInData data);    
}