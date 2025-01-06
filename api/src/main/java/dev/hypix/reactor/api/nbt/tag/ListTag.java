package dev.hypix.reactor.api.nbt.tag;

import java.util.Collection;

import dev.hypix.reactor.api.nbt.TagNBT;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;

public final class ListTag<T extends TagNBT> extends TagNBT {

    private final Collection<T> values;
    private final byte listId;

    public ListTag(Collection<T> values, byte listId, String key) {
        super(key);
        this.listId = listId;
        this.values = values;
    }

    @Override
    public byte getId() {
        return TagNBT.TAG_LIST;
    }

    @Override
    public void write(FriendlyBuffer buffer) {
        buffer.writeByte(listId);
        buffer.writeInt(values.size());
        for (final TagNBT tag : values) {
            tag.write(buffer);
        }
    }

    @Override
    public String toString() {
        return values.toString();
    }

    public Collection<T> getValues() {
        return values;
    }

    public byte getListId() {
        return listId;
    }
}
