package dev.hypix.reactor.protocol.outbound.configuration.registry;

import java.util.List;

import dev.hypix.reactor.api.Reactor;
import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.nbt.type.NBTFastWrite;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;
import dev.hypix.reactor.api.world.World;
import dev.hypix.reactor.api.world.data.WorldType;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutDimensionRegistry implements PacketOutbound {

    @Override
    public byte[] write() {
        final List<World> worlds = Reactor.getServer().getWorldManager().getWorlds();
        final int amount = worlds.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 18, 8);

        buffer.writeString("minecraft:dimension_type");
        buffer.writeVarInt(amount);
    
        for (final World world : worlds) {
            buffer.writeString(world.identifier());
            buffer.writeBoolean(true);

            final WorldType worldType = world.getWorldType();
            final NBTFastWrite nbt = new NBTFastWrite(15);

            nbt.addBoolean("has_skylight", worldType.hasSkyLight());
            nbt.addBoolean("has_ceiling", worldType.hasCeiling());
            nbt.addBoolean("ultrawarm", worldType.ultraWarm());
            nbt.addBoolean("natural", worldType.natural());
            nbt.addDouble("coordinate_scale", worldType.coordinateScale());
            nbt.addBoolean("bed_works", worldType.bedWorks());
            nbt.addBoolean("respawn_anchor_works", worldType.respawnAnchorWorks());
            nbt.addInt("min_y", worldType.minY());
            nbt.addInt("height", worldType.height());
            nbt.addInt("logical_height", worldType.logicalHeight());
            nbt.addString("infiniburn", worldType.infiniburn());
            nbt.addString("effects", worldType.effects());
            nbt.addFloat("ambient_light", worldType.ambientLight());
            nbt.addBoolean("piglin_safe", worldType.piglinSafe());
            nbt.addBoolean("has_raids", worldType.hasRaids());

            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    @Override
    public int getId() {
        return IdPacketOutbound.CONFIG_REGISTRY_DATA;
    }
}