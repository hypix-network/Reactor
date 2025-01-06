package dev.hypix.reactor.api.nbt.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import dev.hypix.reactor.api.nbt.NBT;
import dev.hypix.reactor.api.nbt.TagNBT;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;

public final class NBTWriter {

    private static final FriendlyBuffer newBuffer(final NBT nbt) {
        return new FriendlyBuffer(nbt.getTags().size() * 16, 32);
    }

    public static byte[] writeTags(final NBT nbt) {
        final FriendlyBuffer buffer = newBuffer(nbt);
        nbt.writeTags(buffer);
        return buffer.compress();
    }

    public static byte[] writeNBT(final NBT nbt) {
        final FriendlyBuffer buffer = newBuffer(nbt);
        writeNBT(nbt, buffer);
        return buffer.compress();
    }
    public static void writeNBT(final NBT nbt, final FriendlyBuffer buffer) {  
        buffer.writeByte(TagNBT.TAG_COMPOUND);
        buffer.writeShort(0); // Root name

        nbt.writeTags(buffer);

        buffer.writeByte(TagNBT.TAG_END);
    }

    public static String toString(final NBT nbt) {
        final StringBuilder sb = new StringBuilder();
        sb.append('{');

        int i = 0;
        for (final TagNBT tag : nbt.getTags()) {
            if (i != 0) {
                sb.append(',');
            }
            if (tag.key.isEmpty()) {
                sb.append('"');
            } else {
                sb.append(tag.key);
            }
            sb.append(':');
            sb.append(tag.toString());
            i++;
        }
        sb.append('}');
        return sb.toString();
    }

    public static void writeFile(final Path path, final NBT nbt) throws IOException {
        final FriendlyBuffer buffer = newBuffer(nbt);
        writeNBT(nbt, buffer);
        Files.newOutputStream(path).write(buffer.compress());
    }
}
