package dev.hypix.reactor.api.nbt.tag;

import java.util.Arrays;

import dev.hypix.reactor.api.nbt.TagNBT;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;

public final class IntArrayTag extends TagNBT {

    public final int[] value;

    public IntArrayTag(int[] value, String key) {
        super(key);
        this.value = value;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_INT_ARRAY;
    }

    @Override
    public void write(FriendlyBuffer buffer) {
        buffer.writeInt(value.length);
        for (final int integer : value) {
            buffer.writeInt(integer);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(value);
    }
}
