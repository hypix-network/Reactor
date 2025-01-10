package dev.hypix.reactor.api.nbt.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

import dev.hypix.reactor.api.nbt.TagNBT;

public final class NBTCollection implements Collection<TagNBT> {

    private static final int DEFAULT_SIZE = 16;

    private final int increaseThreshold;
    private TagNBT[] array;
    private int size = 0;

    public NBTCollection() {
        this.increaseThreshold = 5;
        this.array = new TagNBT[DEFAULT_SIZE];
    }

    /*
     * Play with this 2 values can increase the performance heavily.
     * 
     * @param expectedTags - The size of internal array
     * @param increaseThreshold - The amount that the internal array should increase when the limit is exceeded
     */
    public NBTCollection(int initialSize, int increaseThreshold) {
        this.increaseThreshold = increaseThreshold;
        this.array = new TagNBT[initialSize];
    }

    public NBTCollection(TagNBT[] array, int increaseThreshold) {
        this.array = array;
        this.increaseThreshold = increaseThreshold;
    }

    public TagNBT get(final String key) {
        for (final TagNBT nbt : array) {
            if (nbt == null) {
                return null;
            }
            if (nbt.key.equals(key)) {
                return nbt;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(final Object o) {
        if (!(o instanceof TagNBT tagNBT) || size == 0) {
            return false;
        }
        for (final TagNBT tag : array) {
            if (tag == null) {
                return false;
            }
            if (tag.getId() == tagNBT.getId() && tag.key.equals(tagNBT.key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<TagNBT> iterator() {
        return new NBTIterator(this);
    }

    public TagNBT[] getArray() {
        return array;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, array.length);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) Arrays.copyOf(array, a.length, a.getClass());
    }

    @Override
    public boolean add(final TagNBT e) {
        if (size+1 == array.length) {
            final TagNBT[] copy = new TagNBT[array.length + increaseThreshold];
            System.arraycopy(array, 0, copy, 0, array.length);
            array = copy;
        }
        array[size] = e;
        return true;
    }

    public boolean addOrSet(final TagNBT e) {
        for (int i = 0; i < array.length; i++) {
            final TagNBT tag = array[i];
            if (tag == null) {
                continue;
            }
            if (tag.getId() == e.getId() && tag.key.equals(e.key)) {
                array[i] = e;
                return true;
            }
        }
        if (size+1 == array.length) {
            final TagNBT[] copy = new TagNBT[array.length + increaseThreshold];
            System.arraycopy(array, 0, copy, 0, array.length);
            array = copy;
        }
        array[size++] = e;
        return true;
    }

    public boolean remove(final String key) {
        if (size == 0) {
            return false;
        }
        final int arrayLength = array.length;
        for (int i = 0; i < arrayLength; i++) {
            final TagNBT tag = array[i];
            if (tag == null) {
                return false;
            }
            if (tag.key.equals(key)) {
                array[i] = null;
                size--;
                orderArray();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(final Object o) {
        if (!(o instanceof TagNBT tagNBT) || size == 0) {
            return false;
        }
        final int arrayLength = array.length;
        for (int i = 0; i < arrayLength; i++) {
            final TagNBT tag = array[i];
            if (tag == null) {
                return false;
            }
            if (tag.getId() == tagNBT.getId() && tag.key.equals(tagNBT.key)) {
                array[i] = null;
                size--;
                orderArray();
                return true;
            }
        }
        return false;
    }

    private void orderArray() {
        final TagNBT[] copy = new TagNBT[size];
        for (int i = 0; i < array.length; i++) {
            final TagNBT tag = array[i];
            if (tag != null) {
                copy[i] = tag;
            }
        }
        array = copy;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (final Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends TagNBT> c) {
        for (final TagNBT tag : c) {
            add(tag);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean found = false;
        final int arrayLength = array.length;
        for (final Object object : c) {
            if (!(object instanceof TagNBT tagNBT)) {
                continue;
            }
            for (int i = 0; i < arrayLength; i++) {
                final TagNBT tag = array[i];
                if (tag != null && tag.getId() == tagNBT.getId() && tag.key.equals(tagNBT.key)) {
                    found = true;
                    array[i] = null;
                    size--;
                    break;
                }
            }
        }
        if (!found) {
            return false;
        }
        orderArray();
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    @Override
    public void clear() {
        array = new TagNBT[DEFAULT_SIZE];
        size = 0;
    }

    private static final class NBTIterator implements Iterator<TagNBT> {
        private final TagNBT[] array;
        private final NBTCollection collection;
        
        private NBTIterator(NBTCollection collection) {
            this.array = collection.array;
            this.collection = collection;
        }
        private int index = 0;
        private TagNBT next;

        @Override
        public boolean hasNext() {
            final int mainArrayLength = array.length;

            if (this.index == mainArrayLength) {
                return false;
            }

            do {
                final TagNBT iteratedObject = array[index++];
                if (iteratedObject != null) {
                    this.next = iteratedObject;
                    return true;
                }
            } while (this.index < mainArrayLength);

            return false;
        }

        @Override
        public TagNBT next() {
            return this.next;
        }

        @Override
        public void remove() {
            if (array[index] != null) {
                collection.size--;
                array[index] = null;
            }
        }

        @Override
        public void forEachRemaining(final Consumer<? super TagNBT> action) {
            final int length = array.length;
            while (this.index < length) {
                final TagNBT iteratedObject = array[this.index++];
                if (iteratedObject != null) {
                    action.accept(iteratedObject);
                }
            }
        }
    }
}
