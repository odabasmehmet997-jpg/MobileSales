package org.simpleframework.xml.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;


public class WeakCache<T> implements Cache<T> {
    private final WeakCache<T>.SegmentList list;

    public WeakCache() {
        this(10);
    }

    public WeakCache(final int i2) {
        list = new SegmentList(i2);
    }

    
    public boolean isEmpty() {
        final Iterator<WeakCache<T>.Segment> it = list.iterator();
        while (it.hasNext()) {
            if (!it.next().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    
    public void cache(final Object obj, final T t) {
        this.map(obj).cache(obj, t);
    }

    
    public T take(final Object obj) {
        return this.map(obj).take(obj);
    }

    
    public T fetch(final Object obj) {
        return this.map(obj).fetch(obj);
    }

    
    public boolean contains(final Object obj) {
        return this.map(obj).contains(obj);
    }

    private WeakCache<T>.Segment map(final Object obj) {
        return list.get(obj);
    }

    private class SegmentList implements Iterable<WeakCache<T>.Segment> {
        private final List<WeakCache<T>.Segment> list = new ArrayList();
        private final int size;

        public SegmentList(final int i2) {
            size = i2;
            this.create(i2);
        }

        @Override // java.lang.Iterable
        public Iterator<WeakCache<T>.Segment> iterator() {
            return list.iterator();
        }

        public WeakCache<T>.Segment get(final Object obj) {
            final int segment = this.segment(obj);
            if (segment < size) {
                return list.get(segment);
            }
            return null;
        }

        private void create(int i2) {
            while (true) {
                final int i3 = i2 - 1;
                if (0 >= i2) {
                    return;
                }
                list.add(new Segment());
                i2 = i3;
            }
        }

        private int segment(final Object obj) {
            return Math.abs(obj.hashCode() % size);
        }
    }

    private class Segment extends WeakHashMap<Object, T> {
        private Segment() {
        }

        public synchronized void cache(final Object obj, final T t) {
            this.put(obj, t);
        }

        public synchronized T fetch(final Object obj) {
            return this.get(obj);
        }

        public synchronized T take(final Object obj) {
            return this.remove(obj);
        }

        public synchronized boolean contains(final Object obj) {
            return this.containsKey(obj);
        }
    }
}
