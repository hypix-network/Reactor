package dev.hypix.reactor.protocol.handler.configuration;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.protocol.PlayerConnectionImpl;
import dev.hypix.reactor.protocol.handler.PacketHandler;
import dev.hypix.reactor.protocol.inbound.IdPacketInbound;
import dev.hypix.reactor.protocol.inbound.PacketInData;
import dev.hypix.reactor.protocol.outbound.configuration.PacketOutPluginMessage;
import dev.hypix.reactor.protocol.outbound.configuration.PacketOutSelectKnownPacks;

public final class ClientCustomPayloadHandler implements PacketHandler {
    private static final PacketOutbound
        BRAND = new PacketOutPluginMessage("minecraft:brand", "reactor".getBytes()),
        PACK_MINECRAFT_CORE = new PacketOutSelectKnownPacks("minecraft", "core", "1.0.0");

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        connection.sendPackets(BRAND, PACK_MINECRAFT_CORE);
    }

    @Override
    public int packetId() {
        return IdPacketInbound.CONFIG_CUSTOM_PAYLOAD;
    }
}
