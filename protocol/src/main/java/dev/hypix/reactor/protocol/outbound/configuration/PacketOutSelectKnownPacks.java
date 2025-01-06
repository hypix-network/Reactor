package dev.hypix.reactor.protocol.outbound.configuration;

import dev.hypix.reactor.api.entity.player.connection.PacketOutbound;
import dev.hypix.reactor.api.util.buffer.DataSize;
import dev.hypix.reactor.api.util.buffer.ExpectedSizeBuffer;
import dev.hypix.reactor.protocol.outbound.IdPacketOutbound;

public final class PacketOutSelectKnownPacks implements PacketOutbound {

    private final Entry[] entries;

    public PacketOutSelectKnownPacks(Entry... entries) {
        this.entries = entries;
    }
    public PacketOutSelectKnownPacks(String namespace, String id, String version) {
        this(new Entry(namespace, id, version));
    }

    @Override
    public byte[] write() {
        int size = DataSize.varInt(entries.length);
        for (final Entry entry : entries) {
            size += entry.getSize();
        }

        final ExpectedSizeBuffer eBuffer = new ExpectedSizeBuffer(size);

        eBuffer.writeVarInt(entries.length);
        for (final Entry entry : entries) {
            eBuffer.writeString(entry.namespace);
            eBuffer.writeString(entry.id);
            eBuffer.writeString(entry.version);
        }
        return eBuffer.buffer;
    }

    @Override
    public int getId() {
        return IdPacketOutbound.CONFIG_KNOWN_DATA_PACKS;
    }

    public static record Entry(
        String namespace,
        String id,
        String version
    ){
        public int getSize() {
            return DataSize.string(namespace) + DataSize.string(id) + DataSize.string(version);
        }
    }
}