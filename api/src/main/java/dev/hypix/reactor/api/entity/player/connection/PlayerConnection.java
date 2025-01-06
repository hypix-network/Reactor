package dev.hypix.reactor.api.entity.player.connection;

import dev.hypix.reactor.api.chat.ChatComponent;
import dev.hypix.reactor.api.entity.player.Player;

public interface PlayerConnection {
    public void disconnect(final ChatComponent[] reason);
    public void sendPacket(final PacketOutbound packet);
    public void sendPackets(final PacketOutbound... packets);

    public Player getPlayer();
}
