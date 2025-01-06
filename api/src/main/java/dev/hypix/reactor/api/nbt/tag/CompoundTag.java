package dev.hypix.reactor.api.nbt.tag;

import dev.hypix.reactor.api.nbt.NBT;
import dev.hypix.reactor.api.nbt.TagNBT;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;

public final class CompoundTag extends TagNBT {

    private final NBT nbt;

    public CompoundTag(NBT nbt, String key) {
        super(key);
        this.nbt = nbt;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_COMPOUND;
    }

    @Override
    public void write(FriendlyBuffer buffer) {
        nbt.writeTags(buffer);
        buffer.writeByte(TAG_END);
    }

    public NBT getNbt() {
        return nbt;
    }

    @Override
    public String toString() {
        return nbt.toString();
    }
}