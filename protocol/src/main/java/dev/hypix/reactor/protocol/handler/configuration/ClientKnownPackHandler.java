package dev.hypix.reactor.protocol.handler.configuration;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.protocol.PlayerConnectionImpl;
import dev.hypix.reactor.protocol.handler.PacketHandler;
import dev.hypix.reactor.protocol.inbound.IdPacketInbound;
import dev.hypix.reactor.protocol.inbound.PacketInData;
import dev.hypix.reactor.protocol.inbound.configuration.PacketInSelectKnownPack;
import dev.hypix.reactor.protocol.outbound.configuration.PacketOutFinishConfiguration;
import dev.hypix.reactor.protocol.outbound.configuration.registry.PacketOutBannerRegistry;
import dev.hypix.reactor.protocol.outbound.configuration.registry.PacketOutBiomeRegistry;
import dev.hypix.reactor.protocol.outbound.configuration.registry.PacketOutChatRegistry;
import dev.hypix.reactor.protocol.outbound.configuration.registry.PacketOutDamageTypeRegistry;
import dev.hypix.reactor.protocol.outbound.configuration.registry.PacketOutDimensionRegistry;
import dev.hypix.reactor.protocol.outbound.configuration.registry.PacketOutPaintingTypeRegistry;
import dev.hypix.reactor.protocol.outbound.configuration.registry.PacketOutTrimMaterialRegistry;
import dev.hypix.reactor.protocol.outbound.configuration.registry.PacketOutTrimPatternRegistry;
import dev.hypix.reactor.protocol.outbound.configuration.registry.PacketOutWolfTypeRegistry;

final class ClientKnownPackHandler implements PacketHandler {

    private static final PacketOutFinishConfiguration FINISH_CONFIGURATION = new PacketOutFinishConfiguration();

    private static final PacketOutbound
        WOLF_TYPE = new PacketOutWolfTypeRegistry(),
        PAINTING = new PacketOutPaintingTypeRegistry(),
        TRIM_PATTERN = new PacketOutTrimPatternRegistry(),
        TRIM_MATERIAL = new PacketOutTrimMaterialRegistry(),
        BANNER = new PacketOutBannerRegistry(),
        BIOME = new PacketOutBiomeRegistry(),
        DAMAGE = new PacketOutDamageTypeRegistry(),
        CHAT = new PacketOutChatRegistry(),
        DIMENSION_TYPE = new PacketOutDimensionRegistry();

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        final PacketInSelectKnownPack knownPack = new PacketInSelectKnownPack();
        knownPack.read(data);

        if (connection.isFirstConfig) {
            connection.sendPackets(
                WOLF_TYPE,
                PAINTING,
                TRIM_PATTERN,
                TRIM_MATERIAL,
                BANNER,
                BIOME,
                DAMAGE,
                CHAT,
                DIMENSION_TYPE
            );
        }

        connection.sendPacket(FINISH_CONFIGURATION);
    }

    @Override
    public int packetId() {
        return IdPacketInbound.CONFIG_SELECT_KNOWN_DATA_PACK;
    }
}
