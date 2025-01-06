package dev.hypix.reactor.api.entity.player.connection;

public interface PacketOutbound {
    byte[] write();
    int getId();

    default boolean isInstant() {
        return false;
    }
}
