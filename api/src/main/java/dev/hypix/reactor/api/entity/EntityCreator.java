package dev.hypix.reactor.api.entity;

import java.util.UUID;

import dev.hypix.reactor.api.entity.player.Player;
import dev.hypix.reactor.api.entity.player.connection.PlayerConnection;

public interface EntityCreator {

    Player createPlayer(final String name, final UUID uuid, final PlayerConnection connection);

    // TODO: Add entities
    // NPCBuilder createNPC();
    // WolfBuilder createWolf();
}