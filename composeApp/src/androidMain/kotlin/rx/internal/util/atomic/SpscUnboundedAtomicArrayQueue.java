package rx.internal.util.atomic;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.internal.util.unsafe.Pow2;
 
public final class SpscUnboundedAtomicArrayQueue<T> implements Queue<T> {
    AtomicReferenceArray<Object> consumerBuffer;
    final AtomicLong consumerIndex;
    int consumerMask;
    AtomicReferenceArray<Object> producerBuffer;
    final AtomicLong producerIndex;
    long producerLookAhead;
    int producerLookAheadStep;
    int producerMask;
    static final int MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    private static final Object HAS_NEXT = new Object();
    private static int calcDirectOffset(int i2) {
        return i2;
    }
    public SpscUnboundedAtomicArrayQueue(int i2) {
        int iRoundToPowerOfTwo = Pow2.roundToPowerOfTwo(Math.max(8, i2));
        int i3 = iRoundToPowerOfTwo - 1;
        this.producerIndex = new AtomicLong();
        this.consumerIndex = new AtomicLong();
        AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(iRoundToPowerOfTwo + 1);
        this.producerBuffer = atomicReferenceArray;
        this.producerMask = i3;
        adjustLookAheadStep(iRoundToPowerOfTwo);
        this.consumerBuffer = atomicReferenceArray;
        this.consumerMask = i3;
        this.producerLookAhead = iRoundToPowerOfTwo - 2;
        soProducerIndex(0L);
    }
    public boolean offer(T t) {
        t.getClass();
        AtomicReferenceArray<Object> atomicReferenceArray = this.producerBuffer;
        long jLpProducerIndex = lpProducerIndex();
        int i2 = this.producerMask;
        int iCalcWrappedOffset = calcWrappedOffset(jLpProducerIndex, i2);
        if (jLpProducerIndex < this.producerLookAhead) {
            return writeToQueue(atomicReferenceArray, t, jLpProducerIndex, iCalcWrappedOffset);
        }
        long j2 = ((long) this.producerLookAheadStep) + jLpProducerIndex;
        if (lvElement(atomicReferenceArray, calcWrappedOffset(j2, i2)) == null) {
            this.producerLookAhead = j2 - 1;
            return writeToQueue(atomicReferenceArray, t, jLpProducerIndex, iCalcWrappedOffset);
        }
        if (lvElement(atomicReferenceArray, calcWrappedOffset(1 + jLpProducerIndex, i2)) != null) {
            return writeToQueue(atomicReferenceArray, t, jLpProducerIndex, iCalcWrappedOffset);
        }
        resize(atomicReferenceArray, jLpProducerIndex, iCalcWrappedOffset, t, i2);
        return true;
    }
    private boolean writeToQueue(AtomicReferenceArray<Object> atomicReferenceArray, T t, long j2, int i2) {
        soProducerIndex(j2 + 1);
        soElement(atomicReferenceArray, i2, t);
        return true;
    }
    private void resize(AtomicReferenceArray<Object> atomicReferenceArray, long j2, int i2, T t, long j3) {
        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
        this.producerBuffer = atomicReferenceArray2;
        this.producerLookAhead = (j3 + j2) - 1;
        soProducerIndex(j2 + 1);
        soElement(atomicReferenceArray2, i2, t);
        soNext(atomicReferenceArray, atomicReferenceArray2);
        soElement(atomicReferenceArray, i2, HAS_NEXT);
    }
    private void soNext(AtomicReferenceArray<Object> atomicReferenceArray, AtomicReferenceArray<Object> atomicReferenceArray2) {
        soElement(atomicReferenceArray, calcDirectOffset(atomicReferenceArray.length() - 1), atomicReferenceArray2);
    }
    private AtomicReferenceArray<Object> lvNext(AtomicReferenceArray<Object> atomicReferenceArray) {
        return (AtomicReferenceArray) lvElement(atomicReferenceArray, calcDirectOffset(atomicReferenceArray.length() - 1));
    }
    public T poll() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.consumerBuffer;
        long jLpConsumerIndex = lpConsumerIndex();
        int i2 = this.consumerMask;
        int iCalcWrappedOffset = calcWrappedOffset(jLpConsumerIndex, i2);
        T t = (T) lvElement(atomicReferenceArray, iCalcWrappedOffset);
        boolean z = t == HAS_NEXT;
        if (t == null || z) {
            if (z) {
                return newBufferPoll(lvNext(atomicReferenceArray), jLpConsumerIndex, i2);
            }
            return null;
        }
        soConsumerIndex(jLpConsumerIndex + 1);
        soElement(atomicReferenceArray, iCalcWrappedOffset, null);
        return t;
    }
    private T newBufferPoll(AtomicReferenceArray<Object> atomicReferenceArray, long j2, int i2) {
        this.consumerBuffer = atomicReferenceArray;
        int iCalcWrappedOffset = calcWrappedOffset(j2, i2);
        T t = (T) lvElement(atomicReferenceArray, iCalcWrappedOffset);
        if (t == null) {
            return null;
        }
        soConsumerIndex(j2 + 1);
        soElement(atomicReferenceArray, iCalcWrappedOffset, null);
        return t;
    } 
    public T peek() {
        AtomicReferenceArray<Object> atomicReferenceArray = this.consumerBuffer;
        long jLpConsumerIndex = lpConsumerIndex();
        int i2 = this.consumerMask;
        T t = (T) lvElement(atomicReferenceArray, calcWrappedOffset(jLpConsumerIndex, i2));
        return t == HAS_NEXT ? newBufferPeek(lvNext(atomicReferenceArray), jLpConsumerIndex, i2) : t;
    } 
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }
    private T newBufferPeek(AtomicReferenceArray<Object> atomicReferenceArray, long j2, int i2) {
        this.consumerBuffer = atomicReferenceArray;
        return (T) lvElement(atomicReferenceArray, calcWrappedOffset(j2, i2));
    } 
    public int size() {
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
    public boolean isEmpty() {
        return lvProducerIndex() == lvConsumerIndex();
    }
    private void adjustLookAheadStep(int i2) {
        this.producerLookAheadStep = Math.min(i2 / 4, MAX_LOOK_AHEAD_STEP);
    }
    private long lvProducerIndex() {
        return this.producerIndex.get();
    }
    private long lvConsumerIndex() {
        return this.consumerIndex.get();
    }
    private long lpProducerIndex() {
        return this.producerIndex.get();
    }
    private long lpConsumerIndex() {
        return this.consumerIndex.get();
    }
    private void soProducerIndex(long j2) {
        this.producerIndex.lazySet(j2);
    }
    private void soConsumerIndex(long j2) {
        this.consumerIndex.lazySet(j2);
    }
    private static int calcWrappedOffset(long j2, int i2) {
        return calcDirectOffset(((int) j2) & i2);
    }
    private static void soElement(AtomicReferenceArray<Object> atomicReferenceArray, int i2, Object obj) {
        atomicReferenceArray.lazySet(i2, obj);
    }
    private static <E> Object lvElement(AtomicReferenceArray<Object> atomicReferenceArray, int i2) {
        return atomicReferenceArray.get(i2);
    } 
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }
    public boolean contains(Object obj) {
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
