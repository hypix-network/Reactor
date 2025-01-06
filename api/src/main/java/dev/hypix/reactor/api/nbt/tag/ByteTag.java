package dev.hypix.reactor.api.nbt.tag;

import dev.hypix.reactor.api.nbt.NumericTag;
import dev.hypix.reactor.api.nbt.TagNBT;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;

public final class ByteTag extends NumericTag {

    private final byte value;

    public ByteTag(byte value, String key) {
        super(key);
        this.value = value;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_BYTE;
    }

    public byte toByte() {
        return value;
    }
    public short toShort() {
        return value;
    }
    public float toFloat() {
        return value;
    }
    public double toDouble() {
        return value;
    }
    public int toInt() {
        return value;
    }
    public long toLong() {
        return value;
    }

    @Override
    public void write(FriendlyBuffer buffer) {
        buffer.writeByte(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
