package rx.internal.util.unsafe;

abstract class SpmcArrayQueueProducerIndexCacheField<E> extends SpmcArrayQueueMidPad<E> {
    private volatile long producerIndexCache;
    public SpmcArrayQueueProducerIndexCacheField(int i2) {
        super(i2);
    }
    protected final long lvProducerIndexCache() {
        return this.producerIndexCache;
    }
    protected final void svProducerIndexCache(long j2) {
        this.producerIndexCache = j2;
    }
}
