package dev.hypix.reactor.protocol.handler.configuration;

import dev.hypix.reactor.api.Reactor;
import dev.hypix.reactor.protocol.ConnectionState;
import dev.hypix.reactor.protocol.PlayerConnectionImpl;
import dev.hypix.reactor.protocol.outbound.configuration.PacketOutFeatureFlags;
import dev.hypix.reactor.protocol.outbound.configuration.PacketOutPluginMessage;
import dev.hypix.reactor.protocol.outbound.configuration.PacketOutSelectKnownPacks;

public final class ConfigurationHandler {
    private static final PacketOutPluginMessage BRAND = new PacketOutPluginMessage("minecraft:brand", "reactor".getBytes());
    private static final PacketOutSelectKnownPacks PACK_MINECRAFT_CORE = new PacketOutSelectKnownPacks("minecraft", "core", Reactor.getServer().getVersion());
    private static final PacketOutFeatureFlags FLAGS = new PacketOutFeatureFlags("minecraft:vanilla");

    public static void startConfiguration(final PlayerConnectionImpl connection) {
        connection.sendPackets(BRAND, FLAGS, PACK_MINECRAFT_CORE);
    }

    public static void registerHandlers() {
        ConnectionState.CONFIGURATION.add(
            new ClientInfoHandler(),
            new ClientKnownPackHandler(),
            new ClientAcknowledgeFinish()
        );
    }
}
