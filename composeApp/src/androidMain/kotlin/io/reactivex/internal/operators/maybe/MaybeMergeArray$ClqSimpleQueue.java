package io.reactivex.internal.operators.maybe;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;



final class MaybeMergeArrayClqSimpleQueue<T> extends ConcurrentLinkedQueue<T> implements MaybeMergeArraySimpleQueueWithConsumerIndex<T> {
    private static final long serialVersionUID = -4025173261791142821L;
    int consumerIndex;
    final AtomicInteger producerIndex = new AtomicInteger();

    MaybeMergeArrayClqSimpleQueue() {
    }

    public boolean offer(T t, T t2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.concurrent.ConcurrentLinkedQueue, java.util.Queue, io.reactivex.internal.fuseable.SimpleQueue
    public boolean offer(T t) {
        this.producerIndex.getAndIncrement();
        return super.offer(t);
    }

    @Override // java.util.concurrent.ConcurrentLinkedQueue, java.util.Queue, io.reactivex.internal.operators.maybe.MaybeMergeArraySimpleQueueWithConsumerIndex, io.reactivex.internal.fuseable.SimpleQueue
    public T poll() {
        T t = super.poll();
        if (null != t) {
            this.consumerIndex++;
        }
        return t;
    }

    @Override // io.reactivex.internal.operators.maybe.MaybeMergeArraySimpleQueueWithConsumerIndex
    public int consumerIndex() {
        return this.consumerIndex;
    }

    @Override // io.reactivex.internal.operators.maybe.MaybeMergeArraySimpleQueueWithConsumerIndex
    public int producerIndex() {
        return this.producerIndex.get();
    }

    @Override // io.reactivex.internal.operators.maybe.MaybeMergeArraySimpleQueueWithConsumerIndex
    public void drop() {
        poll();
    }
}
