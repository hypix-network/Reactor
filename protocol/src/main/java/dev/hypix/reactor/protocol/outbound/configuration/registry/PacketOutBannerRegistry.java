package dev.hypix.reactor.protocol.outbound.configuration.registry;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.nbt.type.NBTFastWrite;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;
import dev.hypix.reactor.api.world.block.Banner;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutBannerRegistry implements PacketOutbound {

    @Override
    public byte[] write() {
        final int amount = Banner.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 48, 16);

        buffer.writeString("minecraft:banner_pattern");
        buffer.writeVarInt(amount);
    
        for (final Banner banner : Banner.ALL) {
            buffer.writeMCId(banner.getAssetId());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite();
            nbt.addString("asset_id", banner.getAssetId());
            nbt.addString("translation_key", banner.getTranslationKey());
            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    @Override
    public int getId() {
        return IdPacketOutbound.CONFIG_REGISTRY_DATA;
    }
}
