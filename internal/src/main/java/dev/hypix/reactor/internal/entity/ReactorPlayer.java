package dev.hypix.reactor.internal.entity;

import java.util.UUID;

import dev.hypix.reactor.api.chat.ChatComponent;
import dev.hypix.reactor.api.entity.player.Player;
import dev.hypix.reactor.api.entity.player.connection.PlayerConnection;

public final class ReactorPlayer extends Player {

    public ReactorPlayer(PlayerConnection connection, String name, UUID uuid) {
        super(connection, name, uuid);
    }

    @Override
    public void send(ChatComponent[] components) {

    }

    @Override
    public void send(ChatComponent component) {

    }    
}