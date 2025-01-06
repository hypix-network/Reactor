package dev.hypix.reactor.protocol.inbound;

public final class IdPacketInbound {

    public static final int
        HANDSHAKE_STATUS = 0x0,
        HANDSHAKE_PING = 0x1;
    
    public static final int
        LOGIN_START = 0x0,
        LOGIN_ACKNOWLEDGED = 0x03;

    public static final int
        CONFIG_CLIENT_INFO = 0x0,
        CONFIG_CUSTOM_PAYLOAD = 0x02,
        CONFIG_ACKNOWLEDGE_FINISH_CONFIG = 0x03,
        CONFIG_SELECT_KNOWN_DATA_PACK = 0x07;
}
