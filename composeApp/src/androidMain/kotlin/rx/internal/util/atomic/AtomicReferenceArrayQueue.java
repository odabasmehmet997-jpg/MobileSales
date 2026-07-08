package rx.internal.util.atomic;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.internal.util.unsafe.Pow2;

abstract class AtomicReferenceArrayQueue<E> extends AbstractQueue<E> {
    protected final AtomicReferenceArray<E> buffer;
    protected final int mask;
    protected final int calcElementOffset(long j2, int i2) {
        return ((int) j2) & i2;
    }
    public AtomicReferenceArrayQueue(int i2) {
        int iRoundToPowerOfTwo = Pow2.roundToPowerOfTwo(i2);
        this.mask = iRoundToPowerOfTwo - 1;
        this.buffer = new AtomicReferenceArray<>(iRoundToPowerOfTwo);
    }
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }
    protected final int calcElementOffset(long j2) {
        return this.mask & ((int) j2);
    }
    protected final E lvElement(AtomicReferenceArray<E> atomicReferenceArray, int i2) {
        return atomicReferenceArray.get(i2);
    }
    protected final void soElement(AtomicReferenceArray<E> atomicReferenceArray, int i2, E e2) {
        atomicReferenceArray.lazySet(i2, e2);
    }
    protected final E lvElement(int i2) {
        return lvElement(this.buffer, i2);
    }
}
