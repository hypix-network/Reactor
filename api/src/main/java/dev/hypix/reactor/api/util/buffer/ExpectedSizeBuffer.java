package dev.hypix.reactor.api.util.buffer;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/*
 * Unsafe but faster buffer for write data.
 * If you known exactly what is the size of the buffer,
 * use this alternative to improve performance
 */
public final class ExpectedSizeBuffer {

    public static byte[] EMPTY_BUFFER = new byte[0];

    public final byte[] buffer;
    public int index = 0;

    public ExpectedSizeBuffer(int initialSize) {
        this.buffer = new byte[initialSize];
    }

    public ExpectedSizeBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public void writeVarInt(int i) {
        while ((i & -128) != 0) {
            buffer[index++] = (byte)(i & 127 | 128);
            i >>>= 7;
        }
        buffer[index++] = (byte)(i & 127);
    }

    public void writeBytes(final byte[] bytes) {
        System.arraycopy(bytes, 0, buffer, index, bytes.length);
        index += bytes.length;
    }

    public void writeChars(final char[] chars) {
        int x = index;
        for (final char v : chars) {
            buffer[x++] = (byte)(v >>> 8);
            buffer[x++] = (byte)(v);
        }
        index += chars.length * 2;
    }

    public void writeBoolean(final boolean v) {
        buffer[index++] = v ? (byte)1 : 0;
    }

    public void writeByte(final byte v) {
        buffer[index++] = v;
    }

    public void writeByte(final int v) {
        buffer[index++] = (byte)v;
    }

    public void writeShort(final int v) {
        buffer[index++] = (byte)(v >>> 8);
        buffer[index++] = (byte)(v);
    }

    public void writeInt(final int v) {
        buffer[index++] = (byte)(v >>> 24);
        buffer[index++] = (byte)(v >>> 16);
        buffer[index++] = (byte)(v >>> 8);
        buffer[index++] = (byte)(v);
    }

    public void writeUUID(final UUID uuid) {
        writeLong(uuid.getMostSignificantBits());
        writeLong(uuid.getLeastSignificantBits());
    }

    public void writeLong(final long v) {
        buffer[index++] = (byte)(v >>> 56);
        buffer[index++] = (byte)(v >>> 48);
        buffer[index++] = (byte)(v >>> 40);
        buffer[index++] = (byte)(v >>> 32);
        buffer[index++] = (byte)(v >>> 24);
        buffer[index++] = (byte)(v >>> 16);
        buffer[index++] = (byte)(v >>> 8);
        buffer[index++] = (byte)(v);
    }

    public void writeFloat(final float v) {
        final int i = Float.floatToIntBits(v);
        buffer[index++] = (byte) (i >> 24);
        buffer[index++] = (byte) (i >> 16);
        buffer[index++] = (byte) (i >> 8);
        buffer[index++] = (byte) (i);
    }

    public void writeDouble(final double v) {
        final long l = Double.doubleToLongBits(v);
        buffer[index++] = (byte) (l >> 56);
        buffer[index++] = (byte) (l >> 48);
        buffer[index++] = (byte) (l >> 40);
        buffer[index++] = (byte) (l >> 32);
        buffer[index++] = (byte) (l >> 24);
        buffer[index++] = (byte) (l >> 16);
        buffer[index++] = (byte) (l >> 8);
        buffer[index++] = (byte) (l);
    }

    public void writeString(final String string) {
        final byte[] bytes = string.getBytes(StandardCharsets.UTF_8);

        if (bytes.length > 32767) {
            throw new RuntimeException("String too big (was " + string.length() + " bytes encoded, max " + 32767 + ")");
        }
        this.writeVarInt(bytes.length);
        this.writeBytes(bytes);
    }

    public byte[] compress() {
        if (buffer.length == index) {
            return buffer;
        }

        final byte[] compressedBuffer = new byte[index];
        System.arraycopy(buffer, 0, compressedBuffer, 0, index);
        return compressedBuffer;
    }
}
