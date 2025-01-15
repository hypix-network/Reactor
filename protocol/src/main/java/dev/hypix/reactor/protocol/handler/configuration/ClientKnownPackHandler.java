package dev.hypix.reactor.protocol.handler.configuration;

import dev.hypix.reactor.protocol.PlayerConnectionImpl;
import dev.hypix.reactor.protocol.handler.PacketHandler;
import dev.hypix.reactor.protocol.inbound.IdPacketInbound;
import dev.hypix.reactor.protocol.inbound.PacketInData;
import dev.hypix.reactor.protocol.inbound.configuration.PacketInSelectKnownPack;
import dev.hypix.reactor.protocol.outbound.CachedPacket;
import dev.hypix.reactor.protocol.outbound.configuration.PacketOutFinishConfiguration;
import dev.hypix.reactor.protocol.outbound.configuration.Registries;

final class ClientKnownPackHandler implements PacketHandler {

    private static final PacketOutFinishConfiguration FINISH_CONFIGURATION = new PacketOutFinishConfiguration();

    private static final CachedPacket
        WOLF_VARIANT = Registries.packet(Registries.wolfVariants()),
        DAMAGE_TYPES = Registries.packet(Registries.damageTypes()),
        TRIM_MATERIAL = Registries.packet(Registries.trimMaterial()),
        TRIM_PATTERN = Registries.packet(Registries.trimPattern()),
        BANNER = Registries.packet(Registries.banner()),
        BIOME = Registries.packet(Registries.biome()),
        PAINTING = Registries.packet(Registries.painting()),
        DIMENSION_TYPE = Registries.packet(Registries.dimensionTypes());

    @Override
    public void handle(PlayerConnectionImpl connection, int packetId, PacketInData data) {
        final PacketInSelectKnownPack knownPack = new PacketInSelectKnownPack();
        knownPack.read(data);

        connection.sendPackets(
            BIOME,
            PAINTING,
            BANNER,
            TRIM_MATERIAL,
            TRIM_PATTERN,
            DAMAGE_TYPES,
            DIMENSION_TYPE,
            WOLF_VARIANT
        );

        connection.sendPacket(FINISH_CONFIGURATION);
    }

    @Override
    public int packetId() {
        return IdPacketInbound.CONFIG_SELECT_KNOWN_DATA_PACK;
    }
}
