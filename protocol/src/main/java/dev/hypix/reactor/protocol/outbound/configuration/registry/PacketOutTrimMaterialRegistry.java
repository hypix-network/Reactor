package dev.hypix.reactor.protocol.outbound.configuration.registry;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.item.armor.TrimMaterial;
import dev.hypix.reactor.api.nbt.type.NBTFastWrite;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutTrimMaterialRegistry implements PacketOutbound {

    @Override
    public byte[] write() {
        final int amount = TrimMaterial.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 128, 24);

        buffer.writeString("minecraft:trim_material");
        buffer.writeVarInt(amount);
    
        for (final TrimMaterial material : TrimMaterial.ALL) {
            buffer.writeMCId(material.getAssetName());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite();
            nbt.addString("asset_name", material.getAssetName());
            nbt.addString("ingredient", material.getIngredient());
            nbt.addFloat("item_model_index", (float)material.getModelIndex());
            nbt.addString("description", material.getDescription());

            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    @Override
    public int getId() {
        return IdPacketOutbound.CONFIG_REGISTRY_DATA;
    }
}