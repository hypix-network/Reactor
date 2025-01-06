package dev.hypix.reactor.internal.entity;

import java.util.UUID;

import dev.hypix.reactor.api.entity.EntityCreator;
import dev.hypix.reactor.api.entity.player.Player;
import dev.hypix.reactor.api.entity.player.connection.PlayerConnection;

public final class EntityCreatorImpl implements EntityCreator {

    @Override
    public Player createPlayer(String name, UUID uuid, PlayerConnection connection) {
        return new ReactorPlayer(connection, name, uuid);
    }
}
