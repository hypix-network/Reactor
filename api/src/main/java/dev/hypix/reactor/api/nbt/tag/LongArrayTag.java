package dev.hypix.reactor.api.nbt.tag;

import java.util.Arrays;

import dev.hypix.reactor.api.nbt.TagNBT;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;

public final class LongArrayTag extends TagNBT {

    public final long[] value;

    public LongArrayTag(long[] value, String key) {
        super(key);
        this.value = value;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_LONG_ARRAY;
    }

    @Override
    public void write(FriendlyBuffer buffer) {
        buffer.writeInt(value.length);
        for (final long longValue : value) {
            buffer.writeLong(longValue);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(value);
    }
}
