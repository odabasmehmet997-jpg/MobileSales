package rx.internal.util.unsafe;

import java.util.Iterator;


public abstract class ConcurrentCircularArrayQueue<E> extends ConcurrentCircularArrayQueueL0Pad<E> {
    private static final long REF_ARRAY_BASE;
    private static final int REF_ELEMENT_SHIFT;
    protected static final int SPARSE_SHIFT;
    protected final E[] buffer;
    protected final long mask;

    static {
        int iIntValue = Integer.getInteger("sparse.shift", 0).intValue();
        SPARSE_SHIFT = iIntValue;
        int iArrayIndexScale = UnsafeAccess.UNSAFE.arrayIndexScale(Object[].class);
        if (4 == iArrayIndexScale) {
            REF_ELEMENT_SHIFT = iIntValue + 2;
        } else if (8 == iArrayIndexScale) {
            REF_ELEMENT_SHIFT = iIntValue + 3;
        } else {
            throw new IllegalStateException("Unknown pointer size");
        }
        REF_ARRAY_BASE = r1.arrayBaseOffset(Object[].class) + (32 << (REF_ELEMENT_SHIFT - iIntValue));
    }

    public ConcurrentCircularArrayQueue(int i2) {
        int iRoundToPowerOfTwo = Pow2.roundToPowerOfTwo(i2);
        this.mask = iRoundToPowerOfTwo - 1;
        this.buffer = (E[]) new Object[(iRoundToPowerOfTwo << SPARSE_SHIFT) + 64];
    }

    protected final long calcElementOffset(long j2) {
        return calcElementOffset(j2, this.mask);
    }

    protected final long calcElementOffset(long j2, long j3) {
        return REF_ARRAY_BASE + ((j2 & j3) << REF_ELEMENT_SHIFT);
    }

    protected final void spElement(E[] eArr, long j2, E e2) {
        UnsafeAccess.UNSAFE.putObject(eArr, j2, e2);
    }

    protected final void soElement(E[] eArr, long j2, E e2) {
        UnsafeAccess.UNSAFE.putOrderedObject(eArr, j2, e2);
    }

    protected final E lpElement(E[] eArr, long j2) {
        return (E) UnsafeAccess.UNSAFE.getObject(eArr, j2);
    }

    protected final E lvElement(long j2) {
        return lvElement(this.buffer, j2);
    }

    protected final E lvElement(E[] eArr, long j2) {
        return (E) UnsafeAccess.UNSAFE.getObjectVolatile(eArr, j2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }
}
