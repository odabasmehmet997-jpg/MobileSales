package rx.internal.util.unsafe;


public final class SpmcArrayQueue<E> extends SpmcArrayQueueL3Pad<E> {
    public SpmcArrayQueue(int i2) {
        super(i2);
    }

    @Override // java.util.Queue
    public boolean offer(E e2) {
        if (e2 == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        E[] eArr = this.buffer;
        long j2 = this.mask;
        long jLvProducerIndex = lvProducerIndex();
        long jCalcElementOffset = calcElementOffset(jLvProducerIndex);
        if (lvElement(eArr, jCalcElementOffset) != null) {
            if (jLvProducerIndex - lvConsumerIndex() > j2) {
                return false;
            }
            while (lvElement(eArr, jCalcElementOffset) != null) {
            }
        }
        spElement(eArr, jCalcElementOffset, e2);
        soTail(jLvProducerIndex + 1);
        return true;
    }

    @Override // java.util.Queue, rx.internal.util.unsafe.MessagePassingQueue
    public E poll() {
        long jLvConsumerIndex;
        long jLvProducerIndexCache = lvProducerIndexCache();
        do {
            jLvConsumerIndex = lvConsumerIndex();
            if (jLvConsumerIndex >= jLvProducerIndexCache) {
                long jLvProducerIndex = lvProducerIndex();
                if (jLvConsumerIndex >= jLvProducerIndex) {
                    return null;
                }
                svProducerIndexCache(jLvProducerIndex);
            }
        } while (!casHead(jLvConsumerIndex, 1 + jLvConsumerIndex));
        long jCalcElementOffset = calcElementOffset(jLvConsumerIndex);
        E[] eArr = this.buffer;
        E eLpElement = lpElement(eArr, jCalcElementOffset);
        soElement(eArr, jCalcElementOffset, null);
        return eLpElement;
    }

    @Override // java.util.Queue
    public E peek() {
        E eLvElement;
        long jLvProducerIndexCache = lvProducerIndexCache();
        do {
            long jLvConsumerIndex = lvConsumerIndex();
            if (jLvConsumerIndex >= jLvProducerIndexCache) {
                long jLvProducerIndex = lvProducerIndex();
                if (jLvConsumerIndex >= jLvProducerIndex) {
                    return null;
                }
                svProducerIndexCache(jLvProducerIndex);
            }
            eLvElement = lvElement(calcElementOffset(jLvConsumerIndex));
        } while (eLvElement == null);
        return eLvElement;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
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

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return lvConsumerIndex() == lvProducerIndex();
    }
}
