package dev.hypix.reactor.api.config.server;

public record ServerConfig(
    String ip,
    int port,
    String motd,
    int viewDistance,
    int simulationDistance
) {}