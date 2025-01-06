package dev.hypix.reactor.protocol.inbound.configuration;

import dev.hypix.reactor.api.chat.ChatMode;
import dev.hypix.reactor.api.entity.player.Player;
import dev.hypix.reactor.protocol.inbound.PacketInData;
import dev.hypix.reactor.protocol.inbound.PacketInbound;

public final class PacketInClientInformation implements PacketInbound {

    private final Player player;

    public PacketInClientInformation(Player player) {
        this.player = player;
    }

    @Override
    public void read(final PacketInData data) {
        player.setLocale(data.readString(16));
        player.setViewDistance(data.buffer.readByte());
        player.setChatMode(ChatMode.byId(data.readVarInt()));
        player.setChatColors(data.buffer.readBoolean());
        player.getSkin().fromBitmask(data.buffer.readUnsignedByte());
        player.setMainHand(data.readVarInt());
        player.setTextFiltering(data.buffer.readBoolean());
        player.setServerListings(data.buffer.readBoolean());
    }
}