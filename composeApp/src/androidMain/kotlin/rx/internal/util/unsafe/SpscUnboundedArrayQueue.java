package rx.internal.util.unsafe;

import java.util.Iterator;
import sun.misc.Unsafe;


public class SpscUnboundedArrayQueue<E> extends SpscUnboundedArrayQueueConsumerField<E> {
    private static final long C_INDEX_OFFSET;
    private static final long P_INDEX_OFFSET;
    private static final long REF_ARRAY_BASE;
    private static final int REF_ELEMENT_SHIFT;
    static final int MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    private static final Object HAS_NEXT = new Object();

    static {
        Unsafe unsafe = UnsafeAccess.UNSAFE;
        int iArrayIndexScale = unsafe.arrayIndexScale(Object[].class);
        if (4 == iArrayIndexScale) {
            REF_ELEMENT_SHIFT = 2;
        } else if (8 == iArrayIndexScale) {
            REF_ELEMENT_SHIFT = 3;
        } else {
            throw new IllegalStateException("Unknown pointer size");
        }
        REF_ARRAY_BASE = unsafe.arrayBaseOffset(Object[].class);
        try {
            P_INDEX_OFFSET = unsafe.objectFieldOffset(SpscUnboundedArrayQueueProducerFields.class.getDeclaredField("producerIndex"));
            try {
                C_INDEX_OFFSET = unsafe.objectFieldOffset(SpscUnboundedArrayQueueConsumerField.class.getDeclaredField("consumerIndex"));
            } catch (NoSuchFieldException e2) {
                InternalError internalError = new InternalError(e2);
                throw internalError;
            }
        } catch (NoSuchFieldException e3) {
            InternalError internalError2 = new InternalError(e3);
            throw internalError2;
        }
    }

    public SpscUnboundedArrayQueue(int i2) {
        int iRoundToPowerOfTwo = Pow2.roundToPowerOfTwo(i2);
        long j2 = iRoundToPowerOfTwo - 1;
        E[] eArr = (E[]) new Object[iRoundToPowerOfTwo + 1];
        this.producerBuffer = eArr;
        this.producerMask = j2;
        adjustLookAheadStep(iRoundToPowerOfTwo);
        this.consumerBuffer = eArr;
        this.consumerMask = j2;
        this.producerLookAhead = j2 - 1;
        soProducerIndex(0L);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Queue
    public final boolean offer(E e2) {
        if (e2 == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        E[] eArr = this.producerBuffer;
        long j2 = this.producerIndex;
        long j3 = this.producerMask;
        long jCalcWrappedOffset = calcWrappedOffset(j2, j3);
        if (j2 < this.producerLookAhead) {
            return writeToQueue(eArr, e2, j2, jCalcWrappedOffset);
        }
        long j4 = ((long) this.producerLookAheadStep) + j2;
        if (lvElement(eArr, calcWrappedOffset(j4, j3)) == null) {
            this.producerLookAhead = j4 - 1;
            return writeToQueue(eArr, e2, j2, jCalcWrappedOffset);
        }
        if (lvElement(eArr, calcWrappedOffset(1 + j2, j3)) != null) {
            return writeToQueue(eArr, e2, j2, jCalcWrappedOffset);
        }
        resize(eArr, j2, jCalcWrappedOffset, e2, j3);
        return true;
    }

    private boolean writeToQueue(E[] eArr, E e2, long j2, long j3) {
        soElement(eArr, j3, e2);
        soProducerIndex(j2 + 1);
        return true;
    }

    private void resize(E[] eArr, long j2, long j3, E e2, long j4) {
        E[] eArr2 = (E[]) new Object[eArr.length];
        this.producerBuffer = eArr2;
        this.producerLookAhead = (j4 + j2) - 1;
        soElement(eArr2, j3, e2);
        soNext(eArr, eArr2);
        soElement(eArr, j3, HAS_NEXT);
        soProducerIndex(j2 + 1);
    }

    private void soNext(E[] eArr, E[] eArr2) {
        soElement(eArr, calcDirectOffset(eArr.length - 1), eArr2);
    }

    private E[] lvNext(E[] eArr) {
        return (E[]) lvElement(eArr, calcDirectOffset(eArr.length - 1));
    }

    @Override // java.util.Queue
    public final E poll() {
        E[] eArr = this.consumerBuffer;
        long j2 = this.consumerIndex;
        long j3 = this.consumerMask;
        long jCalcWrappedOffset = calcWrappedOffset(j2, j3);
        E e2 = (E) lvElement(eArr, jCalcWrappedOffset);
        boolean z = e2 == HAS_NEXT;
        if (e2 == null || z) {
            if (z) {
                return newBufferPoll(lvNext(eArr), j2, j3);
            }
            return null;
        }
        soElement(eArr, jCalcWrappedOffset, null);
        soConsumerIndex(j2 + 1);
        return e2;
    }

    private E newBufferPoll(E[] eArr, long j2, long j3) {
        this.consumerBuffer = eArr;
        long jCalcWrappedOffset = calcWrappedOffset(j2, j3);
        E e2 = (E) lvElement(eArr, jCalcWrappedOffset);
        if (e2 == null) {
            return null;
        }
        soElement(eArr, jCalcWrappedOffset, null);
        soConsumerIndex(j2 + 1);
        return e2;
    }

    @Override // java.util.Queue
    public final E peek() {
        E[] eArr = this.consumerBuffer;
        long j2 = this.consumerIndex;
        long j3 = this.consumerMask;
        E e2 = (E) lvElement(eArr, calcWrappedOffset(j2, j3));
        return e2 == HAS_NEXT ? newBufferPeek(lvNext(eArr), j2, j3) : e2;
    }

    private E newBufferPeek(E[] eArr, long j2, long j3) {
        this.consumerBuffer = eArr;
        return (E) lvElement(eArr, calcWrappedOffset(j2, j3));
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        long jLvConsumerIndex = lvConsumerIndex();
        while (true) {
            long jLvProducerIndex = lvProducerIndex();
            long jLvConsumerIndex2 = lvConsumerIndex();
            if (jLvConsumerIndex == jLvConsumerIndex2) {
                return (int) (jLvProducerIndex - jLvConsumerIndex2);
            }
            jLvConsumerIndex = jLvConsumerIndex2;
        }
    }

    private void adjustLookAheadStep(int i2) {
        this.producerLookAheadStep = Math.min(i2 / 4, MAX_LOOK_AHEAD_STEP);
    }

    private long lvProducerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, P_INDEX_OFFSET);
    }

    private long lvConsumerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, C_INDEX_OFFSET);
    }

    private void soProducerIndex(long j2) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, P_INDEX_OFFSET, j2);
    }

    private void soConsumerIndex(long j2) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, C_INDEX_OFFSET, j2);
    }

    private static long calcWrappedOffset(long j2, long j3) {
        return calcDirectOffset(j2 & j3);
    }

    private static long calcDirectOffset(long j2) {
        return REF_ARRAY_BASE + (j2 << REF_ELEMENT_SHIFT);
    }

    private static void soElement(Object[] objArr, long j2, Object obj) {
        UnsafeAccess.UNSAFE.putOrderedObject(objArr, j2, obj);
    }

    private static <E> Object lvElement(E[] eArr, long j2) {
        return UnsafeAccess.UNSAFE.getObjectVolatile(eArr, j2);
    }
}
