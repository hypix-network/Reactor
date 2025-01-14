package dev.hypix.reactor.api.entity.player;

import java.util.UUID;

import dev.hypix.reactor.api.chat.ChatMode;
import dev.hypix.reactor.api.command.CommandSender;
import dev.hypix.reactor.api.entity.Entity;
import dev.hypix.reactor.api.entity.EntityType;
import dev.hypix.reactor.api.entity.EntityUtils;
import dev.hypix.reactor.api.entity.player.connection.PlayerConnection;
import dev.hypix.reactor.api.entity.player.data.Gamemode;
import dev.hypix.reactor.api.entity.player.data.PlayerSkin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public abstract class Player implements Entity, CommandSender {

    private final int id = EntityUtils.getNext();
    private final PlayerSkin skin = new PlayerSkin();

    private final PlayerConnection connection;
    private final String name;
    private final UUID uuid;

    private Gamemode gamemode = Gamemode.SURVIVAL;
    private int health = 20;

    private String clientInfo;

    private String locale;
    private byte viewDistance;
    private ChatMode chatMode;
    private boolean chatColors;
    private int mainHand;
    private boolean textFiltering;
    private boolean serverListings;

    @Override
    public EntityType getType() {
        return EntityType.PLAYER;
    }
}