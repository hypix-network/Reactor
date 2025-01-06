package dev.hypix.reactor.api.nbt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.tinylog.Logger;

import dev.hypix.reactor.api.nbt.tag.ByteArrayTag;
import dev.hypix.reactor.api.nbt.tag.ByteTag;
import dev.hypix.reactor.api.nbt.tag.CompoundTag;
import dev.hypix.reactor.api.nbt.tag.DoubleTag;
import dev.hypix.reactor.api.nbt.tag.FloatTag;
import dev.hypix.reactor.api.nbt.tag.IntArrayTag;
import dev.hypix.reactor.api.nbt.tag.IntTag;
import dev.hypix.reactor.api.nbt.tag.ListTag;
import dev.hypix.reactor.api.nbt.tag.LongArrayTag;
import dev.hypix.reactor.api.nbt.tag.LongTag;
import dev.hypix.reactor.api.nbt.tag.ShortTag;
import dev.hypix.reactor.api.nbt.tag.StringTag;
import dev.hypix.reactor.api.util.buffer.FriendlyBuffer;

public interface NBT {

    TagNBT get(final String key);
    void add(final TagNBT nbt);
    boolean addOrSet(final TagNBT nbt);
    boolean remove(final String key);

    void writeTags(final FriendlyBuffer buffer);
    Collection<TagNBT> getTags();

    default byte getType(final String key) {
        final TagNBT tag = get(key);
        return tag == null ? 0 : tag.getId();
    }

    default byte getByte(final String key) {
        final TagNBT nbt = get(key);
        return nbt instanceof NumericTag numericTag ? numericTag.toByte() : 0;
    }

    default short getShort(final String key) {
        final TagNBT nbt = get(key);
        return nbt instanceof NumericTag numericTag ? numericTag.toShort() : 0;
    }

    default int getInt(final String key) {
        final TagNBT nbt = get(key);
        return nbt instanceof NumericTag numericTag ? numericTag.toInt() : 0;
    }

    default float getFloat(final String key) {
        final TagNBT nbt = get(key);
        return nbt instanceof NumericTag numericTag ? numericTag.toFloat() : 0F;
    }

    default double getDouble(final String key) {
        final TagNBT nbt = get(key);
        return nbt instanceof NumericTag numericTag ? numericTag.toDouble() : 0D;
    }

    default String getString(final String key) {
        final TagNBT nbt = get(key);
        return nbt instanceof StringTag stringTag ? stringTag.value : null;
    }

    default byte[] getByteArray(final String key) {
        final TagNBT nbt = get(key);
        return nbt instanceof ByteArrayTag byteArrayTag ? byteArrayTag.value : null;
    }

    default int[] getIntArray(final String key) {
        final TagNBT nbt = get(key);
        return nbt instanceof IntArrayTag intArrayTag ? intArrayTag.value : null;
    }

    default long[] getLongArray(final String key) {
        final TagNBT nbt = get(key);
        return nbt instanceof LongArrayTag longArrayTag ? longArrayTag.value : null;
    }

    default CompoundTag getCompound(final String key) {
        final TagNBT nbt = get(key);
        return nbt instanceof CompoundTag compoundTag ? compoundTag : null;
    }

    default ListTag<?> getList(final String key) {
        final TagNBT nbt = get(key);
        return nbt instanceof ListTag<?> listTag ? listTag : null;
    }

    default void addByte(final String key, final byte value) {
        add(new ByteTag(value, key));
    }

    default void addBoolean(final String key, final boolean value) {
        add(new ByteTag((value) ? (byte)1 : 0, key));
    }

    default void addShort(final String key, final short value) {
        add(new ShortTag(value, key));
    }

    default void addLong(final String key, final long value) {
        add(new LongTag(value, key));
    }

    default void addInt(final String key, final int value) {
        add(new IntTag(value, key));
    }

    default void addFloat(final String key, final float value) {
        add(new FloatTag(value, key));
    }

    default void addDouble(final String key, final double value) {
        add(new DoubleTag(value, key));
    }

    default void addIntArray(final String key, final int[] value){
        add(new IntArrayTag(value, key));
    }

    default void addString(final String key, final String value) {
        add(new StringTag(value, key));
    }

    default void addByteArray(final String key, final byte[] value) {
        add(new ByteArrayTag(value, key));
    }

    default void addLongArray(final String key, final long[] value) {
        add(new LongArrayTag(value, key));
    }

    default void addCompound(final String key, final NBT nbt) {
        if (nbt == this) {
            Logger.warn("Trying to add the same NBT to itself");
            return;
        }
        add(new CompoundTag(nbt, key));
    }

    default void addStringList(final String key, final String... values) {
        final List<StringTag> collection = new ArrayList<>(values.length);
        for (final String value : values) {
            collection.add(new StringTag(value, null));
        }
        add(new ListTag<StringTag>(collection, TagNBT.TAG_STRING, key));
    }

    default <T extends TagNBT> void addList(final String key, final List<T> list, final byte listId) {
        add(new ListTag<T>(list, listId, key));
    }
}