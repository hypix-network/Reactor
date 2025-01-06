package dev.hypix.reactor.api.nbt.tag;

import java.util.Arrays;

import dev.hypix.reactor.api.nbt.TagNBT;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;

public final class ByteArrayTag extends TagNBT {

    public final byte[] value;

    public ByteArrayTag(byte[] value, String key) {
        super(key);
        this.value = value;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_BYTE_ARRAY;
    }

    @Override
    public void write(FriendlyBuffer buffer) {
        buffer.writeInt(value.length);
        buffer.writeBytes(value);
    }

    @Override
    public String toString() {
        return Arrays.toString(value);
    }
}
