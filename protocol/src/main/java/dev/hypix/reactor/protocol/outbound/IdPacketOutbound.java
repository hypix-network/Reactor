package dev.hypix.reactor.protocol.outbound;

public final class IdPacketOutbound {

    public static final int
        HANDSHAKE_STATUS = 0x00,
        HANDSHAKE_PONG = 0x01;

    public static final int
        LOGIN_DISCONNECT = 0x00,
        LOGIN_SUCCESS = 0x02;

    public static final int
        CONFIG_PLUGIN_MESSAGE = 0x01,
        CONFIG_DISCONNECT = 0x02,
        CONFIG_FINISH = 0x03,
        CONFIG_RESET_CHAT = 0x06,
        CONFIG_REGISTRY_DATA = 0x07,
        CONFIG_FEATURE_FLAGS = 0x0C,
        CONFIG_KNOWN_DATA_PACKS = 0x0E,
        CONFIG_UPDATE_TAGS = 0x0D;

    public static final int 
        PLAY_LOGIN = 0x2B,
        PLAY_DISCONNECT = 0x1D;
}
