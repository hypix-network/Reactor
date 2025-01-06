package dev.hypix.reactor.api.nbt.tag;

import dev.hypix.reactor.api.nbt.TagNBT;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;

public final class StringTag extends TagNBT {

    public String value;

    public StringTag(String value, String key) {
        super(key);
        this.value = value;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_STRING;
    }

    @Override
    public void write(final FriendlyBuffer buffer) {
        buffer.writeNBTString(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
