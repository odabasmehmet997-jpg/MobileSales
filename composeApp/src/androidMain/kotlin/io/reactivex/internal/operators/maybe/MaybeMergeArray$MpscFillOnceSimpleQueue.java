package io.reactivex.internal.operators.maybe;

import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;



final class MaybeMergeArrayMpscFillOnceSimpleQueue<T> extends AtomicReferenceArray<T> implements MaybeMergeArraySimpleQueueWithConsumerIndex<T> {
    private static final long serialVersionUID = -7969063454040569579L;
    int consumerIndex;
    final AtomicInteger producerIndex;

    MaybeMergeArrayMpscFillOnceSimpleQueue(int i2) {
        super(i2);
        this.producerIndex = new AtomicInteger();
    }

    @Override // io.reactivex.internal.fuseable.SimpleQueue
    public boolean offer(T t) {
        ObjectHelper.requireNonNull(t, "value is null");
        int andIncrement = this.producerIndex.getAndIncrement();
        if (andIncrement >= length()) {
            return false;
        }
        lazySet(andIncrement, t);
        return true;
    }

    public boolean offer(T t, T t2) {
        throw new UnsupportedOperationException();
    }

    @Override // io.reactivex.internal.operators.maybe.MaybeMergeArraySimpleQueueWithConsumerIndex, java.util.Queue, io.reactivex.internal.fuseable.SimpleQueue
    public T poll() {
        int i2 = this.consumerIndex;
        if (i2 == length()) {
            return null;
        }
        AtomicInteger atomicInteger = this.producerIndex;
        do {
            T t = get(i2);
            if (null != t) {
                this.consumerIndex = i2 + 1;
                lazySet(i2, null);
                return t;
            }
        } while (atomicInteger.get() != i2);
        return null;
    }

    @Override // io.reactivex.internal.operators.maybe.MaybeMergeArraySimpleQueueWithConsumerIndex
    public T peek() {
        int i2 = this.consumerIndex;
        if (i2 == length()) {
            return null;
        }
        return get(i2);
    }

    @Override // io.reactivex.internal.operators.maybe.MaybeMergeArraySimpleQueueWithConsumerIndex
    public void drop() {
        int i2 = this.consumerIndex;
        lazySet(i2, null);
        this.consumerIndex = i2 + 1;
    }

    @Override // io.reactivex.internal.fuseable.SimpleQueue
    public boolean isEmpty() {
        return this.consumerIndex == producerIndex();
    }

    @Override // io.reactivex.internal.fuseable.SimpleQueue
    public void clear() {
        while (null != this.poll() && !isEmpty()) {
        }
    }

    @Override // io.reactivex.internal.operators.maybe.MaybeMergeArraySimpleQueueWithConsumerIndex
    public int consumerIndex() {
        return this.consumerIndex;
    }

    @Override // io.reactivex.internal.operators.maybe.MaybeMergeArraySimpleQueueWithConsumerIndex
    public int producerIndex() {
        return this.producerIndex.get();
    }
}
