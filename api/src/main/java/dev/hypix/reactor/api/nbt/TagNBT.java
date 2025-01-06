package dev.hypix.reactor.api.nbt;

import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;

public abstract class TagNBT {

    public static final byte
        TAG_END = 0,
        TAG_BYTE = 1,
        TAG_SHORT = 2,
        TAG_INT = 3,
        TAG_LONG = 4,
        TAG_FLOAT = 5,
        TAG_DOUBLE = 6,
        TAG_BYTE_ARRAY = 7,
        TAG_STRING = 8,
        TAG_LIST = 9,
        TAG_COMPOUND = 10,
        TAG_INT_ARRAY = 11,
        TAG_LONG_ARRAY = 12;

    public final String key;

    public TagNBT(String key) {
        this.key = (key == null) ? "" : key;
    }

    public abstract byte getId();
    public abstract void write(final FriendlyBuffer buffer);

    @Override
    public final boolean equals(final Object obj) {
        return obj == this || (obj instanceof TagNBT tagNBT)
            && tagNBT.getId() == this.getId()
            && tagNBT.key.equals(this.key);
    }
}
