package dev.hypix.reactor.protocol.outbound.configuration.registry;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.entity.wolf.WolfType;
import dev.hypix.reactor.api.nbt.type.NBTFastWrite;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutWolfTypeRegistry implements PacketOutbound {

    public byte[] write() {
        final int amountWolfs = WolfType.ALL.length;
        final FriendlyBuffer buffer = new FriendlyBuffer(amountWolfs * 64, 64);

        buffer.writeString("minecraft:wolf_variant");
        buffer.writeVarInt(amountWolfs);

        for (final WolfType wolfType : WolfType.ALL) {
            buffer.writeString(wolfType.getId());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite();
            nbt.addString("wild_texture", wolfType.getWildTexture());
            nbt.addString("tame_texture", wolfType.getTameTexture());
            nbt.addString("angry_texture", wolfType.getAngryTexture());
            nbt.addString("biomes", wolfType.getBiome());
            buffer.writeNBT(nbt);
        }

        return buffer.compress();
    }

    @Override
    public int getId() {
        return IdPacketOutbound.CONFIG_REGISTRY_DATA;
    }
}
