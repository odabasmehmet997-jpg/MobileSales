package rx.internal.util.atomic;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class SpscAtomicArrayQueue<E> extends AtomicReferenceArrayQueue<E> {
    private static final Integer MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    final AtomicLong consumerIndex;
    final int lookAheadStep;
    final AtomicLong producerIndex;
    long producerLookAhead;
    public  void clear() {
        super.clear();
    }
    public  Iterator iterator() {
        return super.iterator();
    }
    public SpscAtomicArrayQueue(int i2) {
        super(i2);
        this.producerIndex = new AtomicLong();
        this.consumerIndex = new AtomicLong();
        this.lookAheadStep = Math.min(i2 / 4, MAX_LOOK_AHEAD_STEP.intValue());
    }
    public boolean offer(E e2) {
        if (e2 == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        AtomicReferenceArray<E> atomicReferenceArray = this.buffer;
        int i2 = this.mask;
        long j2 = this.producerIndex.get();
        int iCalcElementOffset = calcElementOffset(j2, i2);
        if (j2 >= this.producerLookAhead) {
            long j3 = ((long) this.lookAheadStep) + j2;
            if (lvElement(atomicReferenceArray, calcElementOffset(j3, i2)) == null) {
                this.producerLookAhead = j3;
            } else if (lvElement(atomicReferenceArray, iCalcElementOffset) != null) {
                return false;
            }
        }
        soElement(atomicReferenceArray, iCalcElementOffset, e2);
        soProducerIndex(j2 + 1);
        return true;
    }
    public E poll() {
        long j2 = this.consumerIndex.get();
        int iCalcElementOffset = calcElementOffset(j2);
        AtomicReferenceArray<E> atomicReferenceArray = this.buffer;
        E eLvElement = lvElement(atomicReferenceArray, iCalcElementOffset);
        if (eLvElement == null) {
            return null;
        }
        soElement(atomicReferenceArray, iCalcElementOffset, null);
        soConsumerIndex(j2 + 1);
        return eLvElement;
    }
    public E peek() {
        return lvElement(calcElementOffset(this.consumerIndex.get()));
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
    private void soProducerIndex(long j2) {
        this.producerIndex.lazySet(j2);
    }
    private void soConsumerIndex(long j2) {
        this.consumerIndex.lazySet(j2);
    }
    private long lvConsumerIndex() {
        return this.consumerIndex.get();
    }
    private long lvProducerIndex() {
        return this.producerIndex.get();
    }
}
