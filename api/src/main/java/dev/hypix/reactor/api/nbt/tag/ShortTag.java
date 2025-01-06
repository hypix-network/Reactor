package dev.hypix.reactor.api.nbt.tag;

import dev.hypix.reactor.api.nbt.NumericTag;
import dev.hypix.reactor.api.nbt.TagNBT;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;

public final class ShortTag extends NumericTag {

    private final short value;

    public ShortTag(short value, String key) {
        super(key);
        this.value = value;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_SHORT;
    }

    public byte toByte() {
        return (byte)value;
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
        buffer.writeShort(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
