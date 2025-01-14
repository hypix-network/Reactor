package dev.hypix.reactor.protocol.outbound.configuration.registry;

import dev.hypix.reactor.api.entity.damage.DamageType;
import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.nbt.type.NBTFastWrite;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutDamageTypeRegistry implements PacketOutbound {

    @Override
    public byte[] write() {
        final int amount = DamageType.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 18, 8);

        buffer.writeString("minecraft:damage_type");
        buffer.writeVarInt(amount);
    
        for (final DamageType damageType : DamageType.ALL) {
            buffer.writeMCId(damageType.getName());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite();
            nbt.addString("message_id", damageType.getMessageId());
            nbt.addString("scaling", damageType.getScaling());
            nbt.addFloat("exhaustion", (float)damageType.getExhaustion());
            nbt.addString("effects", "hurt");
            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    @Override
    public int getId() {
        return IdPacketOutbound.CONFIG_REGISTRY_DATA;
    }
}