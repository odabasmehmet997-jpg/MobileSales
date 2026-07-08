package io.reactivex.internal.operators.maybe;

import io.reactivex.internal.fuseable.SimpleQueue;



interface MaybeMergeArraySimpleQueueWithConsumerIndex<T> extends SimpleQueue<T> {
    int consumerIndex();

    void drop();

    T peek();

    @Override // java.util.Queue, io.reactivex.internal.operators.maybe.MaybeMergeArraySimpleQueueWithConsumerIndex, io.reactivex.internal.fuseable.SimpleQueue
    T poll();

    int producerIndex();
}
