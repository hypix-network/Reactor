package dev.hypix.reactor.protocol.outbound.configuration.registry;

import dev.hypix.reactor.api.chat.ChatType;
import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.nbt.type.NBTFastWrite;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutChatRegistry implements PacketOutbound {

    @Override
    public byte[] write() {
        final int amount = ChatType.ALL.size();
        final FriendlyBuffer buffer = new FriendlyBuffer(amount * 128, 24);

        buffer.writeString("minecraft:chat_type");
        buffer.writeVarInt(amount);

        for (final ChatType chatType : ChatType.ALL) {
            buffer.writeMCId(chatType.getName());
            buffer.writeBoolean(true);

            final NBTFastWrite nbt = new NBTFastWrite();;
            nbt.addCompound("chat", createTag(chatType.getChat()));
            nbt.addCompound("narration", createTag(chatType.getNarration()));
            buffer.writeNBT(nbt);
        }
        return buffer.compress();
    }

    private NBTFastWrite createTag(final ChatType.Data data) {
        final NBTFastWrite nbt = new NBTFastWrite();
        nbt.addString("translation_key", data.translationKey());
        nbt.addStringList("parameters", data.parameters());
        return nbt;
    }

    @Override
    public int getId() {
        return IdPacketOutbound.CONFIG_REGISTRY_DATA;
    }
}