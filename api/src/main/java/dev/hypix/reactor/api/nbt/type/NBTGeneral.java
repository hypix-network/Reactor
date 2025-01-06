package dev.hypix.reactor.api.nbt.type;

import java.util.Collection;
import java.util.Map;

import dev.hypix.reactor.api.nbt.NBT;
import dev.hypix.reactor.api.nbt.TagNBT;
import dev.hypix.reactor.api.nbt.util.NBTWriter;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

/*
 * A nbt for general proposes (add, read, iterate, remove, etc)
 */
public final class NBTGeneral implements NBT {

    private final Map<String, TagNBT> tags = new Object2ObjectOpenHashMap<>();

    @Override
    public TagNBT get(final String key) {
        return tags.get(key);
    }

    @Override
    public void add(final TagNBT nbt) {
        tags.put(nbt.key, nbt);
    }

    @Override
    public boolean addOrSet(final TagNBT nbt) {
        return tags.put(nbt.key, nbt) != null;
    }

    @Override
    public boolean remove(final String key) {
        return tags.remove(key) != null;
    }

    @Override
    public void writeTags(final FriendlyBuffer buffer) {
        final Collection<TagNBT> tags = this.tags.values();
        for (final TagNBT tag : tags) {
            if (tag != null) {
                buffer.writeByte(tag.getId());
                buffer.writeNBTString(tag.key);
                tag.write(buffer);        
            }
        }
    }

    @Override
    public Collection<TagNBT> getTags() {
        return tags.values();
    }


    @Override
    public String toString() {
        return NBTWriter.toString(this);
    }
}