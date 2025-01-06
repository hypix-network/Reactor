package dev.hypix.reactor.protocol.outbound.configuration.registry;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.item.armor.TrimPattern;
import dev.hypix.reactor.api.nbt.type.NBTFastWrite;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutTrimPatternRegistry implements PacketOutbound {

    @Override
    public byte[] write() {
        final int amount = TrimPattern.ALL.length;
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 32, 16);

        buffer.writeString("minecraft:trim_pattern");
        buffer.writeVarInt(amount);
    
        for (final TrimPattern pattern : TrimPattern.ALL) {
            buffer.writeString(pattern.getId());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite();
            nbt.addString("asset_id", pattern.getId());
            nbt.addString("template_item", pattern.getItem());
            nbt.addString("description", pattern.getDescription());
            nbt.addBoolean("decal", false);
            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    @Override
    public int getId() {
        return IdPacketOutbound.CONFIG_REGISTRY_DATA;
    }
}