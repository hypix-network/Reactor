package dev.hypix.reactor.protocol.outbound.configuration.registry;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.nbt.type.NBTFastWrite;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;
import dev.hypix.reactor.api.world.data.WorldType;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutDimensionRegistry implements PacketOutbound {

    @Override
    public byte[] write() {
        final int amount = WorldType.ALL.length;
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 18, 8);

        buffer.writeString("minecraft:dimension_type");
        buffer.writeVarInt(amount);
    
        for (final WorldType world : WorldType.ALL) {
            buffer.writeMCId(world.name());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite(15);

            nbt.addBoolean("has_skylight", world.hasSkylight());
            nbt.addBoolean("has_ceiling", world.hasCeiling());
            nbt.addBoolean("ultrawarm", world.ultrawarm());
            nbt.addBoolean("natural", world.natural());
            nbt.addDouble("coordinate_scale", world.coordinateScale());
            nbt.addBoolean("bed_works", world.bedWorks());
            nbt.addBoolean("respawn_anchor_works", world.respawnAnchorWorks());
            nbt.addInt("min_y", world.minY());
            nbt.addInt("height", world.height());
            nbt.addInt("logical_height", world.logicalHeight());
            nbt.addString("infiniburn", world.infiniburn());
            nbt.addString("effects", world.effects());
            nbt.addFloat("ambient_light", (float)world.ambientLight());
            nbt.addBoolean("piglin_safe", world.piglinSafe());
            nbt.addBoolean("has_raids", world.hasRaids());

            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    @Override
    public int getId() {
        return IdPacketOutbound.CONFIG_REGISTRY_DATA;
    }
}