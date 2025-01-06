package dev.hypix.reactor.protocol.outbound.configuration.registry;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.nbt.type.NBTFastWrite;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;
import dev.hypix.reactor.api.world.block.Painting;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutPaintingTypeRegistry implements PacketOutbound {

    @Override
    public byte[] write() {
        final int amount = Painting.ALL.length;
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 18, 8);

        buffer.writeString("minecraft:painting_variant");
        buffer.writeVarInt(amount);
    
        for (final Painting painting : Painting.ALL) {
            buffer.writeString(painting.getId());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite();
            nbt.addString("asset_id", painting.getId());
            nbt.addInt("height", painting.getHeight());
            nbt.addInt("width", painting.getWidth());
            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    @Override
    public int getId() {
        return IdPacketOutbound.CONFIG_REGISTRY_DATA;
    }
}