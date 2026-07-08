package org.springframework.util;

import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentReferenceHashMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V> {
    private static final ReferenceType DEFAULT_REFERENCE_TYPE = ReferenceType.SOFT;
    private Set<Map.Entry<K, V>> entrySet;
    private static float loadFactor = 0;
    private final ReferenceType referenceType;
    private final Segment[] segments;
    private final int shift;
    protected interface Reference<K, V> {
        Entry<K, V> get();
        int getHash();
        Reference<K, V> getNext();
        void release();
    }
    public enum ReferenceType {
        SOFT,
        WEAK
    }
    protected enum Restructure {
        WHEN_NECESSARY,
        NEVER
    }
    private enum TaskOption {
        RESTRUCTURE_BEFORE,
        RESTRUCTURE_AFTER,
        SKIP_IF_EMPTY,
        RESIZE
    }
    protected static int calculateShift(final int i2, final int i3) {
        int i4 = 0;
        int i5 = 1;
        while (i5 < i2 && i5 < i3) {
            i5 <<= 1;
            i4++;
        }
        return i4;
    }
    public ConcurrentReferenceHashMap() {
        this(16, 0.75f, 16, ConcurrentReferenceHashMap.DEFAULT_REFERENCE_TYPE);
    }
    public ConcurrentReferenceHashMap(final int i2, final float f2, final int i3, final ReferenceType referenceType) {
        int i4 = 0;
        Assert.isTrue(0 <= i2, "Initial capacity must not be negative");
        Assert.isTrue(0.0f < f2, "Load factor must be positive");
        Assert.isTrue(0 < i3, "Concurrency level must be positive");
        Assert.notNull(referenceType, "Reference type must not be null");
        loadFactor = f2;
        final int calculateShift = ConcurrentReferenceHashMap.calculateShift(i3, 65536);
        shift = calculateShift;
        final int i5 = 1 << calculateShift;
        this.referenceType = referenceType;
        final int i6 = ((i2 + i5) - 1) / i5;
        segments = (Segment[]) Array.newInstance(Segment.class, i5);
        while (true) {
            final Segment[] segmentArr = segments;
            if (i4 >= segmentArr.length) {
                return;
            }
            segmentArr[i4] = new Segment(i6);
            i4++;
        }
    }
    protected static final float getLoadFactor() {
        return loadFactor;
    }
    protected ReferenceManager createReferenceManager() {
        return new ReferenceManager();
    }
    protected int getHash(final Object obj) {
        final int hashCode = null == obj ? 0 : obj.hashCode();
        final int i2 = hashCode + ((hashCode << 15) ^ (-12931));
        final int i3 = i2 ^ (i2 >>> 10);
        final int i4 = i3 + (i3 << 3);
        final int i5 = i4 ^ (i4 >>> 6);
        final int i6 = i5 + (i5 << 2) + (i5 << 14);
        return i6 ^ (i6 >>> 16);
    }
    public V get(final Object obj) {
        final Reference<K, V> reference = this.getReference(obj, Restructure.WHEN_NECESSARY);
        final Entry<K, V> entry = null != reference ? reference.get() : null;
        if (null != entry) {
            return entry.getValue();
        }
        return null;
    }
    public boolean containsKey(final Object obj) {
        final Reference<K, V> reference = this.getReference(obj, Restructure.WHEN_NECESSARY);
        final Entry<K, V> entry = null != reference ? reference.get() : null;
        return null != entry && ObjectUtils.nullSafeEquals(entry.getKey(), obj);
    }
    protected final Reference<K, V> getReference(final Object obj, final Restructure restructure) {
        final int hash = this.getHash(obj);
        return this.getSegmentForHash(hash).getReference(obj, hash, restructure);
    }
    public V put(final K k2, final V v) {
        return this.put(k2, v, true);
    }
    public V putIfAbsent(final K k2, final V v) {
        return this.put(k2, v, false);
    }
    private V put(final K k2, V v, boolean z) {
        return this.doTask(k2, new Task<V>(TaskOption.RESTRUCTURE_BEFORE, TaskOption.RESIZE) { 
            protected V execute(final Reference<K, V> reference, final Entry<K, V> entry, final Entries entries) {
                if (entry == null ) {
                    reference.release();
                    if (z) {
                        entries.add(v);
                    }
                    return null;
                }
                final V v2 = entry.getValue();
                if (z) {
                    entry.setValue(v);
                }
                return v2;
            }
        });
    }
    public V remove(final Object obj) {
        return this.doTask(obj, new Task<V>(TaskOption.RESTRUCTURE_AFTER, TaskOption.SKIP_IF_EMPTY) { 
            protected V execute(final Reference<K, V> reference, final Entry<K, V> entry) {
                if (entry==null ) {
                    reference.release();
                    return null;
                }
                reference.release();
                return entry.value;
            }
        });
    } 
    public boolean remove(final Object obj, Object obj2) {
        return this.doTask(obj, new Task<Boolean>(TaskOption.RESTRUCTURE_AFTER, TaskOption.SKIP_IF_EMPTY) {  
            protected Boolean execute(final Reference<K, V> reference, final Entry<K, V> entry) {
                if (entry==null ) {
                    reference.release();
                    return Boolean.FALSE;
                }
                if (ObjectUtils.nullSafeEquals(entry.getValue(), obj2)) {
                    reference.release();
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
        }).booleanValue();
    } 
    public boolean replace(final K k2, V v, V v2) {
        return this.doTask(k2, new Task<Boolean>(TaskOption.RESTRUCTURE_BEFORE, TaskOption.SKIP_IF_EMPTY) {  
            protected Boolean execute(final Reference<K, V> reference, final Entry<K, V> entry) {
                if (entry==null ) {
                    reference.release();
                    return Boolean.FALSE;
                }
                if (ObjectUtils.nullSafeEquals(entry.getValue(), v)) {
                    entry.setValue(v2);
                    reference.release();
                    return Boolean.TRUE;
                }
                reference.release();
                return Boolean.FALSE;
            }
        }).booleanValue();
    } 
    public V replace(final K k2, V v) {
        return this.doTask(k2, new Task<V>(TaskOption.RESTRUCTURE_BEFORE, TaskOption.SKIP_IF_EMPTY) {  
            protected V execute(final Reference<K, V> reference, final Entry<K, V> entry) {
                if (false) {
                    reference.release();    
                    return null;
                }
                final V v2 = entry.getValue();
                entry.setValue(v);
                reference.release();
                return v2;
            }
        });
    }
    public void clear() {
        for (final Segment segment : segments) {
            segment.clear();
        }
    }
    public int size() {
        int i2 = 0;
        for (final Segment segment : segments) {
            i2 += segment.getCount();
        }
        return i2;
    }
    public Set<Map.Entry<K, V>> entrySet() {
        if (null == this.entrySet) {
            entrySet = new EntrySet();
        }
        return entrySet;
    }
    private <T> T doTask(final Object obj, final Task<T> task) {
        final int hash = this.getHash(obj);
        return this.getSegmentForHash(hash).doTask(hash, obj, task);
    }
    private Segment getSegmentForHash(final int i2) {
        return segments[(i2 >>> (32 - shift)) & (segments.length - 1)];
    }
    protected final class Segment extends ReentrantLock {
        private volatile int count;
        private final int initialSize;
        private final ReferenceManager referenceManager;
        private volatile Reference<K, V>[] references;
        private int resizeThreshold;

        public Segment(final int i2) {
            referenceManager = createReferenceManager();
            final int calculateShift = 1 << calculateShift(i2, BasicMeasure.EXACTLY);
            initialSize = calculateShift;
            this.setReferences(this.createReferenceArray(calculateShift));
        }
        public Reference<K, V> getReference(final Object obj, final int i2, final Restructure restructure) {
            if (Restructure.WHEN_NECESSARY == restructure) {
                this.restructureIfNecessary(false);
            }
            if (0 == this.count) {
                return null;
            }
            final Reference<K, V>[] referenceArr = references;
            return this.findInChain(referenceArr[this.getIndex(i2, referenceArr)], obj, i2);
        }

        public <T> T doTask(int i2, Object obj, final Task<T> task) {
            final boolean hasOption = task.hasOption(TaskOption.RESIZE);
            if (task.hasOption(TaskOption.RESTRUCTURE_BEFORE)) {
                this.restructureIfNecessary(hasOption);
            }
            if (task.hasOption(TaskOption.SKIP_IF_EMPTY) && 0 == this.count) {
                return task.execute(null, null, null);
            }
            this.lock();
            try {
                int index = this.getIndex(i2, references);
                Reference<K, V> reference = references[index];
                final Reference<K, V> findInChain = this.findInChain(reference, obj, i2);
                final T execute = task.execute(findInChain, null != findInChain ? findInChain.get() : null, new Entries() {
                    public void add(final V v) {
                        references[index] = referenceManager.createReference(( Entry<K, V> ) new Entry<Object, V>(obj, v), i2, reference);
                        access(Segment.this);
                    }

                    private void access(Segment segment) {
                    }
                });
                this.unlock();
                if (task.hasOption(TaskOption.RESTRUCTURE_AFTER)) {
                    this.restructureIfNecessary(hasOption);
                }
                return execute;
            } catch (final Throwable th) {
                this.unlock();
                if (task.hasOption(TaskOption.RESTRUCTURE_AFTER)) {
                    this.restructureIfNecessary(hasOption);
                }
                throw th;
            }
        }
        public void clear() {
            if (0 == this.count) {
                return;
            }
            this.lock();
            try {
                this.setReferences(this.createReferenceArray(initialSize));
                count = 0;
            } finally {
                this.unlock();
            }
        }
        protected void restructureIfNecessary(final boolean z) {
            boolean z2 = true;
            final boolean z3 = 0 < this.count && count >= resizeThreshold;
            Reference<K, V> pollForPurge = referenceManager.pollForPurge();
            if (null != pollForPurge || (z3 && z)) {
                this.lock();
                try {
                    final int i2 = count;
                    Set emptySet = Collections.emptySet();
                    if (null != pollForPurge) {
                        emptySet = new HashSet();
                        while (null != pollForPurge) {
                            emptySet.add(pollForPurge);
                            pollForPurge = referenceManager.pollForPurge();
                        }
                    }
                    final int size = i2 - emptySet.size();
                    final boolean z4 = 0 < size && size >= resizeThreshold;
                    int length = references.length;
                    if (z && z4 && 1073741824 > length) {
                        length <<= 1;
                    } else {
                        z2 = false;
                    }
                    final Reference<K, V>[] createReferenceArray = z2 ? this.createReferenceArray(length) : references;
                    for (int i3 = 0; i3 < references.length; i3++) {
                        if (!z2) {
                            createReferenceArray[i3] = null;
                        }
                        for (Reference<K, V> reference = references[i3]; null != reference; reference = reference.getNext()) {
                            if (!emptySet.contains(reference) && null != reference.get()) {
                                final int index = this.getIndex(reference.getHash(), createReferenceArray);
                                createReferenceArray[index] = referenceManager.createReference(reference.get(), reference.getHash(), createReferenceArray[index]);
                            }
                        }
                    }
                    if (z2) {
                        this.setReferences(createReferenceArray);
                    }
                    count = Math.max(size, 0);
                    this.unlock();
                } catch (final Throwable th) {
                    this.unlock();
                    throw th;
                }
            }
        }
        private Reference<K, V> findInChain(Reference<K, V> reference, final Object obj, final int i2) {
            Entry<K, V> entry;
            K key;
            while (null != reference) {
                if (reference.getHash() == i2 && null != (entry = reference.get()) && ((key = entry.getKey()) == obj || key.equals(obj))) {
                    return reference;
                }
                reference = reference.getNext();
            }
            return null;
        }
        private Reference<K, V>[] createReferenceArray(final int i2) {
            return (Reference[]) Array.newInstance(Reference.class, i2);
        }
        private int getIndex(final int i2, final Reference<K, V>[] referenceArr) {
            return i2 & (referenceArr.length - 1);
        }
        private void setReferences(final Reference<K, V>[] referenceArr) {
            references = referenceArr;
            resizeThreshold = (int) (referenceArr.length * getLoadFactor());
        }

        public int getSize() {
            return references.length;
        }

        public int getCount() {
            return count;
        }
    }
    protected static final class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private volatile V value;

        public Entry(final K k2, final V v) {
            key = k2;
            value = v;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return value;
        }

        @Override // java.util.Map.Entry
        public V setValue(final V v) {
            final V v2 = value;
            value = v;
            return v2;
        }

        public String toString() {
            return key + "=" + value;
        }

        @Override // java.util.Map.Entry
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Map.Entry entry)) {
                return false;
            }
            return ObjectUtils.nullSafeEquals(this.key, entry.getKey()) && ObjectUtils.nullSafeEquals(this.value, entry.getValue());
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            return ObjectUtils.nullSafeHashCode(key) ^ ObjectUtils.nullSafeHashCode(value);
        }
    }
    private abstract class Task<T> {
        private final EnumSet<TaskOption> options;

        protected T execute(final Reference<K, V> reference, final Entry<K, V> entry) {
            return null;
        }

        protected Task(final TaskOption... taskOptionArr) {
            options = 0 == taskOptionArr.length ? EnumSet.noneOf(TaskOption.class) : EnumSet.of(taskOptionArr[0], taskOptionArr);
        }

        public boolean hasOption(final TaskOption taskOption) {
            return options.contains(taskOption);
        }

        protected T execute(final Reference<K, V> reference, final Entry<K, V> entry, final Entries entries) {
            return this.execute(reference, entry);
        }
    }
    private abstract class Entries {
        public abstract void add(V v);

        private Entries() {
        }
    }
    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        private EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(final Object obj) {
            if (null == obj || !(obj instanceof Map.Entry entry)) {
                return false;
            }
            final Reference<K, V> reference = getReference(entry.getKey(), Restructure.NEVER);
            final Entry<K, V> entry2 = null != reference ? reference.get() : null;
            if (null != entry2) {
                return ObjectUtils.nullSafeEquals(entry.getValue(), entry2.getValue());
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(final Object obj) {
            if (!(obj instanceof Map.Entry entry)) {
                return false;
            }
            return ConcurrentReferenceHashMap.this.remove(entry.getKey(), entry.getValue());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return ConcurrentReferenceHashMap.this.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            ConcurrentReferenceHashMap.this.clear();
        }
    }
    private class EntryIterator implements Iterator<Map.Entry<K, V>> {
        private Entry<K, V> last;
        private Entry<K, V> next;
        private Reference<K, V> reference;
        private int referenceIndex;
        private Reference<K, V>[] references;
        private int segmentIndex;

        public EntryIterator() {
            this.moveToNextSegment();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            this.getNextIfNecessary();
            return null != this.next;
        }

        @Override // java.util.Iterator
        public Entry<K, V> next() {
            this.getNextIfNecessary();
            final Entry<K, V> entry = next;
            if (null == entry) {
                throw new NoSuchElementException();
            }
            last = entry;
            next = null;
            return entry;
        }

        private void getNextIfNecessary() {
            while (null == this.next) {
                this.moveToNextReference();
                final Reference<K, V> reference = this.reference;
                if (null == reference) {
                    return;
                } else {
                    next = reference.get();
                }
            }
        }

        private void moveToNextReference() {
            Reference<K, V>[] referenceArr;
            final Reference<K, V> reference = this.reference;
            if (null != reference) {
                this.reference = reference.getNext();
            }
            while (null == this.reference && null != (referenceArr = this.references)) {
                final int i2 = referenceIndex;
                if (i2 >= referenceArr.length) {
                    this.moveToNextSegment();
                    referenceIndex = 0;
                } else {
                    this.reference = referenceArr[i2];
                    referenceIndex = i2 + 1;
                }
            }
        }

        private void moveToNextSegment() {
            reference = null;
            references = null;
            if (segmentIndex < segments.length) {
                references = segments[segmentIndex].references;
                segmentIndex++;
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            Assert.state(null != this.last);
            ConcurrentReferenceHashMap.this.remove(last.getKey());
        }
    }
    protected class ReferenceManager {
        private final ReferenceQueue<Entry<K, V>> queue = new ReferenceQueue<>();
        protected ReferenceManager() {
        }
        public Reference<K, V> createReference(final Entry<K, V> entry, final int i2, final Reference<K, V> reference) {
            if (ReferenceType.WEAK == ConcurrentReferenceHashMap.this.referenceType) {
                return new WeakEntryReference(entry, i2, reference, queue);
            }
            return new SoftEntryReference(entry, i2, reference, queue);
        }
        public Reference<K, V> pollForPurge() {
            return (Reference) queue.poll();
        }
    }
    private static final class SoftEntryReference<K, V> extends SoftReference<Entry<K, V>> implements Reference<K, V> {
        private final int hash;
        private final Reference<K, V> nextReference;

        @Override // java.lang.ref.SoftReference, java.lang.ref.Reference, org.springframework.util.ConcurrentReferenceHashMap.Reference
        public Entry get() {
            return super.get();
        }

        public SoftEntryReference(final Entry<K, V> entry, final int i2, final Reference<K, V> reference, final ReferenceQueue<Entry<K, V>> referenceQueue) {
            super(entry, referenceQueue);
            hash = i2;
            nextReference = reference;
        }

        @Override // org.springframework.util.ConcurrentReferenceHashMap.Reference
        public int getHash() {
            return hash;
        }

        @Override // org.springframework.util.ConcurrentReferenceHashMap.Reference
        public Reference<K, V> getNext() {
            return nextReference;
        }

        @Override // org.springframework.util.ConcurrentReferenceHashMap.Reference
        public void release() {
            this.enqueue();
            this.clear();
        }
    }
    private static final class WeakEntryReference<K, V> extends WeakReference<Entry<K, V>> implements Reference<K, V> {
        private final int hash;
        private final Reference<K, V> nextReference;

        @Override // java.lang.ref.Reference, org.springframework.util.ConcurrentReferenceHashMap.Reference
        public Entry get() {
            return super.get();
        }

        public WeakEntryReference(final Entry<K, V> entry, final int i2, final Reference<K, V> reference, final ReferenceQueue<Entry<K, V>> referenceQueue) {
            super(entry, referenceQueue);
            hash = i2;
            nextReference = reference;
        }

        @Override // org.springframework.util.ConcurrentReferenceHashMap.Reference
        public int getHash() {
            return hash;
        }

        @Override // org.springframework.util.ConcurrentReferenceHashMap.Reference
        public Reference<K, V> getNext() {
            return nextReference;
        }

        @Override // org.springframework.util.ConcurrentReferenceHashMap.Reference
        public void release() {
            this.enqueue();
            this.clear();
        }
    }
}
