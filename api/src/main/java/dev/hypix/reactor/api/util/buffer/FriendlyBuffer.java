package dev.hypix.reactor.api.util.buffer;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;

import dev.hypix.reactor.api.nbt.NBT;
import dev.hypix.reactor.api.nbt.TagNBT;

/*
 * A slow but safe alternative to ExpectedSizeBuffer
 * This alternative don't throw ArrayIndexOutOfBounds if
 * you write more data than the initial size (Automatic resize the buffer) 
 */
public final class FriendlyBuffer {

    private ExpectedSizeBuffer currentBuffer;
    private final int increaseThreshold;

    public FriendlyBuffer(int initialSize) {
        this.currentBuffer = new ExpectedSizeBuffer(initialSize);
        this.increaseThreshold = 16;
    }

    public FriendlyBuffer(int initialSize, int increaseThreshold) {
        this.currentBuffer = new ExpectedSizeBuffer(initialSize);
        this.increaseThreshold = increaseThreshold;
    }

    public void tryResize(final int amountToAdd) {
        if (currentBuffer.index + amountToAdd >= currentBuffer.buffer.length) {
            final int newSize = (int)((currentBuffer.buffer.length + amountToAdd) + increaseThreshold);
            final int currentIndex = currentBuffer.index;

            this.currentBuffer = new ExpectedSizeBuffer(Arrays.copyOf(currentBuffer.buffer, newSize));
            this.currentBuffer.index = currentIndex;
        }
    }

    public void writeVarInt(final int i) {
        tryResize(DataSize.varInt(i));
        currentBuffer.writeVarInt(i);
    }

    public void writeBytes(final byte[] bytes) {
        tryResize(bytes.length);
        currentBuffer.writeBytes(bytes);
    }

    public void writeChars(final char[] chars) {
        tryResize(chars.length * 2);
        currentBuffer.writeChars(chars);
    }

    public void writePrefixBytes(final byte[] bytes) {
        tryResize(bytes.length + DataSize.varInt(bytes.length));
        currentBuffer.writeVarInt(bytes.length);
        currentBuffer.writeBytes(bytes);
    }

    public void writeBoolean(final boolean v) {
        tryResize(DataSize.BOOLEAN);
        currentBuffer.writeBoolean(v);
    }

    public void writeByte(final byte v) {
        tryResize(DataSize.BYTE);
        currentBuffer.writeByte(v);
    }

    public void writeByte(final int v) {
        tryResize(DataSize.BYTE);
        currentBuffer.writeByte(v);
    }

    public void writeShort(final int v) {
        tryResize(DataSize.SHORT);
        currentBuffer.writeShort(v);
    }

    public void writeInt(final int v) {
        tryResize(DataSize.INT);
        currentBuffer.writeInt(v);
    }


    public void writeUUID(final UUID uuid) {
        tryResize(DataSize.UUID);
        currentBuffer.writeUUID(uuid);
    }

    public void writeLong(final long v) {
        tryResize(DataSize.LONG);
        currentBuffer.writeLong(v);
    }

    public void writeFloat(final float v) {
        tryResize(DataSize.FLOAT);
        currentBuffer.writeFloat(v);
    }

    public void writeDouble(final double v) {
        tryResize(DataSize.DOUBLE);
        currentBuffer.writeDouble(v);
    }

    public void writeString(final String string) {
        tryResize(DataSize.string(string));
        currentBuffer.writeString(string);
    }

    public void writeNBTString(final String string) {
        final byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        tryResize(DataSize.SHORT + bytes.length);
        writeShort(bytes.length);
        writeBytes(bytes);
    }

    // Use only for packet proporses, else use NBTWriter.writeNBT
    // (This don't contains the rootname)
    public void writeNBT(final NBT nbt) {
        writeByte(TagNBT.TAG_COMPOUND);
        nbt.writeTags(this);
        writeByte(TagNBT.TAG_END);
    }

    public void writeIdentifier(final String key, final String value) {
        final byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        final byte[] valueBytes = value.getBytes(StandardCharsets.UTF_8);
        tryResize(keyBytes.length + valueBytes.length);

        currentBuffer.writeVarInt(keyBytes.length + valueBytes.length);
        currentBuffer.writeBytes(keyBytes);
        currentBuffer.writeBytes(valueBytes);
    }

    private static final byte[] minecraftId = "minecraft:".getBytes();
    public void writeMCId(final String value) {
        final byte[] valueBytes = value.getBytes(StandardCharsets.UTF_8);
        tryResize(minecraftId.length + valueBytes.length);
        currentBuffer.writeVarInt(minecraftId.length + valueBytes.length);
        currentBuffer.writeBytes(minecraftId);
        currentBuffer.writeBytes(valueBytes);
    }

    public ExpectedSizeBuffer getCurrentBuffer() {
        return currentBuffer;
    }

    public byte[] compress() {
        if (currentBuffer.buffer.length == currentBuffer.index) {
            return currentBuffer.buffer;
        }

        final byte[] compressedBuffer = new byte[currentBuffer.index];
        System.arraycopy(currentBuffer.buffer, 0, compressedBuffer, 0, currentBuffer.index);
        return compressedBuffer;
    }
}
