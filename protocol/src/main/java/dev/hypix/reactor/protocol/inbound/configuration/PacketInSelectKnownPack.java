package dev.hypix.reactor.protocol.inbound.configuration;

import dev.hypix.reactor.protocol.inbound.PacketInData;
import dev.hypix.reactor.protocol.inbound.PacketInbound;
import lombok.Getter;

@Getter
public final class PacketInSelectKnownPack implements PacketInbound {
    private Entry[] entries;

    @Override
    public void read(final PacketInData data) {
        final int knownPacks = data.readVarInt();
        entries = new Entry[knownPacks];
        for (int i = 0; i < knownPacks; i++) {
            final String namespace = data.readString();
            final String id = data.readString();
            final String version = data.readString();
            entries[i] = new Entry(namespace, id, version);
        }
    }

    public record Entry(
        String namespace,
        String id,
        String version
    ){}
}
