package rx.internal.util.atomic;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.internal.util.unsafe.Pow2;

public final class SpscExactAtomicArrayQueue<T> extends AtomicReferenceArray<T> implements Queue<T> {
    private static final long serialVersionUID = 6210984603741293445L;
    final int capacitySkip;
    final AtomicLong consumerIndex;
    final int mask;
    final AtomicLong producerIndex;
    public SpscExactAtomicArrayQueue(int i2) {
        super(Pow2.roundToPowerOfTwo(i2));
        int length = length();
        this.mask = length - 1;
        this.capacitySkip = length - i2;
        this.producerIndex = new AtomicLong();
        this.consumerIndex = new AtomicLong();
    }
    public boolean offer(T t) {
        t.getClass();
        long j2 = this.producerIndex.get();
        int i2 = this.mask;
        if (get(((int) (((long) this.capacitySkip) + j2)) & i2) != null) {
            return false;
        }
        this.producerIndex.lazySet(j2 + 1);
        lazySet(i2 & ((int) j2), t);
        return true;
    }
    public T poll() {
        long j2 = this.consumerIndex.get();
        int i2 = ((int) j2) & this.mask;
        T t = get(i2);
        if (t == null) {
            return null;
        }
        this.consumerIndex.lazySet(j2 + 1);
        lazySet(i2, null);
        return t;
    }
    public T peek() {
        return get(((int) this.consumerIndex.get()) & this.mask);
    }
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }
    public boolean isEmpty() {
        return this.producerIndex == this.consumerIndex;
    }
    public int size() {
        long j2 = this.consumerIndex.get();
        while (true) {
            long j3 = this.producerIndex.get();
            long j4 = this.consumerIndex.get();
            if (j2 == j4) {
                return (int) (j3 - j4);
            }
            j2 = j4;
        }
    }
    public boolean contains(Object obj) {
        throw new UnsupportedOperationException();
    }
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }
    public <E> E[] toArray(E[] eArr) {
        throw new UnsupportedOperationException();
    }
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }
    public T remove() {
        throw new UnsupportedOperationException();
    }
    public T element() {
        throw new UnsupportedOperationException();
    }
}
