package dev.hypix.reactor.api.util.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;

/*
 * A collection with expected size, but don't throw ArrayOutOfBoundsException
 * if you try add more elements.
 */
public class ExpectedSizeCollection<T> implements Collection<T> {

    protected final T[] array;
    protected int addedValues = 0;

    @SuppressWarnings("unchecked")
    public ExpectedSizeCollection(int size) {
        this.array = (T[]) new Object[size];
    }
    
    public ExpectedSizeCollection(T[] array) {
        this.array = array;
    }

    @Override
    public int size() {
        return addedValues;
    }

    @Override
    public boolean isEmpty() {
        return addedValues == 0;
    }

    @Override
    public boolean contains(final Object o) {
        for (final T object : array) {
            if (o.equals(object)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ExpectedSizeIterator<T>(this);
    }

    @Override
    public Object[] toArray() {
        return array;
    }

    @Override
    @SuppressWarnings("all")
    public <T> T[] toArray(final T[] a) {
        return (T[]) Arrays.copyOf(array, a.length, a.getClass());
    }

    @Override
    public boolean add(final T e) {
        if (e == null) {
            return false;
        }
        final int size = array.length;
        for (int i = 0; i < size; i++) {
            if (array[i] == null) {
                array[i] = e;
                addedValues++;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(final Object o) {
        if (o == null) {
            return false;
        }
        final int size = array.length;
        for (int i = 0; i < size; i++) {
            if (o.equals(array[i])) {
                array[i] = null;
                addedValues--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        boolean addAll = true;
        for (T object : c) {
            if (!add(object)) {
                addAll = false;
            }
        }
        return addAll;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        boolean removeAll = true;
        for (Object object : c) {
            if (!remove(object)) {
                removeAll = false;
            }
        }
        return removeAll;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return containsAll(c);
    }

    @Override
    public void clear() {
        final int size = array.length;
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
    }

    public T[] getArray() {
        return array;
    }

    private static final class ExpectedSizeIterator<T> implements Iterator<T> {
        private final T[] array;
        private final ExpectedSizeCollection<T> collection;
        
        private ExpectedSizeIterator(ExpectedSizeCollection<T> collection) {
            this.array = collection.array;
            this.collection = collection;
        }
        private int index = 0;
        private T next;

        @Override
        public boolean hasNext() {
            final int mainArrayLength = array.length;

            if (this.index == mainArrayLength) {
                return false;
            }

            do {
                final T iteratedObject = array[index++];
                if (iteratedObject != null) {
                    this.next = iteratedObject;
                    return true;
                }
            } while (this.index < mainArrayLength);

            return false;
        }

        @Override
        public T next() {
            return this.next;
        }

        @Override
        public void remove() {
            if (array[index] != null) {
                collection.addedValues--;
            }
        }

        @Override
        public void forEachRemaining(final Consumer<? super T> action) {
            final int length = array.length;

            while (this.index < length) {
                final T iteratedObject = array[this.index++];
                if (iteratedObject != null) {
                    action.accept(iteratedObject);
                }
            }
        }
    }
}