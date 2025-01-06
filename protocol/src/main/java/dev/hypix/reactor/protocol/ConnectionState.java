package dev.hypix.reactor.protocol;

import dev.hypix.reactor.protocol.handler.PacketHandler;
import dev.hypix.reactor.protocol.handler.PacketHandlerStorage;

public enum ConnectionState {
    HANDSHAKE(2),
    LOGIN(6),
    CONFIGURATION(17),
    PLAY(124);

    public final PacketHandlerStorage handlerStorage;

    private ConnectionState(int amountInboundPackets) {
        this.handlerStorage = new PacketHandlerStorage(amountInboundPackets);
    }

    public void add(final PacketHandler... handlers) {
        for (final PacketHandler handler : handlers) {
            handlerStorage.add(handler);
        }
    }
}