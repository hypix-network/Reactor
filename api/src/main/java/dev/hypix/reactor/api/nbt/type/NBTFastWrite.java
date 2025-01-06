package dev.hypix.reactor.api.nbt.type;

import java.util.Collection;

import dev.hypix.reactor.api.nbt.NBT;
import dev.hypix.reactor.api.nbt.TagNBT;
import dev.hypix.reactor.api.nbt.util.NBTCollection;
import dev.hypix.reactor.api.nbt.util.NBTWriter;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;

/*
 * Nbt specialized in fast add and iteration
 * 
 * Don't recommended for get, remove or set operations
 */
public final class NBTFastWrite implements NBT {
    
    private final NBTCollection tags;

    public NBTFastWrite() {
        this.tags = new NBTCollection();
    }

    public NBTFastWrite(int defaultSize) {
        this.tags = new NBTCollection(defaultSize, 5);
    }

    public NBTFastWrite(final NBTCollection collection) {
        this.tags = collection;
    }

    @Override
    public TagNBT get(final String key) {
        return tags.get(key);
    }

    @Override
    public void add(final TagNBT nbt) {
        tags.add(nbt);
    }

    @Override
    public void writeTags(final FriendlyBuffer buffer) {
        final TagNBT[] tags = this.tags.getArray();
        for (final TagNBT tag : tags) {
            if (tag != null) {
                buffer.writeByte(tag.getId());
                buffer.writeNBTString(tag.key);
                tag.write(buffer);        
            }
        }
    }

    @Override
    public boolean addOrSet(final TagNBT nbt) {
        return tags.addOrSet(nbt);
    }

    @Override
    public boolean remove(final String key) {
        return tags.remove(key);
    }

    @Override
    public Collection<TagNBT> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        return NBTWriter.toString(this);
    }
}
