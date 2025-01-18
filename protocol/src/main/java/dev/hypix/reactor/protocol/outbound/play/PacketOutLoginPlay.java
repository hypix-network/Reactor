package dev.hypix.reactor.protocol.outbound.play;

import dev.hypix.reactor.api.Reactor;
import dev.hypix.reactor.api.config.server.ServerConfig;
import dev.hypix.reactor.api.entity.player.Player;
import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.entity.player.connection.PlayerConnection;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;
import dev.hypix.reactor.api.world.World;
import dev.hypix.reactor.api.world.data.Gamerule;
import dev.hypix.reactor.api.world.data.WorldType;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class PacketOutLoginPlay implements PacketOutbound {

    private final PlayerConnection connection;
    private final World world;

    @Override
    public byte[] write() {
        final ServerConfig config = Reactor.getServer().getConfig();
        final Gamerule gamerule = world.getGamerule();

        final Player player = connection.getPlayer();
        final FriendlyBuffer buffer = new FriendlyBuffer(50);

        buffer.writeVarInt(player.getId());
        buffer.writeBoolean(gamerule.isHardCore());
        buffer.writeVarInt(WorldType.ALL.length);
        for (final WorldType worldType : WorldType.ALL) {
            buffer.writeString("minecraft:"+worldType.name());
        }
        buffer.writeVarInt(30);
        buffer.writeVarInt(config.viewDistance());
        buffer.writeVarInt(config.simulationDistance());
        buffer.writeBoolean(false);
        buffer.writeBoolean(gamerule.isRespawnScreen());
        buffer.writeBoolean(false);
        buffer.writeVarInt(0);
        buffer.writeString("minecraft:"+"overworld");
        buffer.writeLong(Long.MAX_VALUE);
        buffer.writeByte(player.getGamemode().ordinal());
        buffer.writeByte(-1);
        buffer.writeBoolean(false);
        buffer.writeBoolean(false);
        buffer.writeBoolean(false);
        buffer.writeVarInt(0);
        buffer.writeVarInt(0);
        buffer.writeVarInt(gamerule.getPortalCooldown());
        buffer.writeVarInt(5);
        buffer.writeBoolean(false);
        return buffer.compress();
    }

    @Override
    public int getId() {
        return IdPacketOutbound.PLAY_LOGIN;
    }
}