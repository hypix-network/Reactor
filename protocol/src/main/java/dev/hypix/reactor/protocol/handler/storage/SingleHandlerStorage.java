package dev.hypix.reactor.protocol.handler.storage;

import dev.hypix.reactor.protocol.PlayerConnectionImpl;
import dev.hypix.reactor.protocol.handler.PacketHandler;
import dev.hypix.reactor.protocol.inbound.PacketInData;

public final class SingleHandlerStorage implements HandlerStorageType {

    private final PacketHandler handler;

    public SingleHandlerStorage(PacketHandler handler) {
        this.handler = handler;
    }

    public PacketHandler getHandler() {
        return handler;
    }

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        handler.handle(connection, packetId, data);
    }
}
