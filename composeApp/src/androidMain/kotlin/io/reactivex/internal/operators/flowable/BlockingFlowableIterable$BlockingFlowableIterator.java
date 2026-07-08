package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BlockingHelper;
import io.reactivex.internal.util.ExceptionHelper;
import org.reactivestreams.Subscription;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



final class BlockingFlowableIterableBlockingFlowableIterator<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Iterator<T>, Runnable, Disposable {
    private static final long serialVersionUID = 6695226475494099826L;
    final long batchSize;
    final Condition condition;
    volatile boolean done;
    volatile Throwable error;
    final long limit;
    final Lock lock;
    long produced;
    final SpscArrayQueue<T> queue;

    BlockingFlowableIterableBlockingFlowableIterator(int i2) {
        this.queue = new SpscArrayQueue<>(i2);
        this.batchSize = i2;
        this.limit = i2 - (i2 >> 2);
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        this.condition = reentrantLock.newCondition();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        while (!isDisposed()) {
            boolean z = this.done;
            boolean isEmpty = this.queue.isEmpty();
            if (z) {
                Throwable th = this.error;
                if (null != th) {
                    throw ExceptionHelper.wrapOrThrow(th);
                }
                if (isEmpty) {
                    return false;
                }
            }
            if (!isEmpty) {
                return true;
            }
            BlockingHelper.verifyNonBlocking();
            this.lock.lock();
            while (!this.done && this.queue.isEmpty() && !isDisposed()) {
                try {
                    try {
                        this.condition.await();
                    } catch (InterruptedException e2) {
                        run();
                        throw ExceptionHelper.wrapOrThrow(e2);
                    }
                } finally {
                    this.lock.unlock();
                }
            }
        }
        Throwable th2 = this.error;
        if (null == th2) {
            return false;
        }
        throw ExceptionHelper.wrapOrThrow(th2);
    }

    @Override // java.util.Iterator
    public T next() {
        if (hasNext()) {
            T poll = this.queue.poll();
            long j2 = this.produced + 1;
            if (j2 == this.limit) {
                this.produced = 0L;
                get().request(j2);
            } else {
                this.produced = j2;
            }
            return poll;
        }
        throw new NoSuchElementException();
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        SubscriptionHelper.setOnce(this, subscription, this.batchSize);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        if (!this.queue.offer(t)) {
            SubscriptionHelper.cancel(this);
            onError(new MissingBackpressureException("Queue full?!"));
        } else {
            signalConsumer();
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        this.error = th;
        this.done = true;
        signalConsumer();
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.done = true;
        signalConsumer();
    }

    void signalConsumer() {
        this.lock.lock();
        try {
            this.condition.signalAll();
        } finally {
            this.lock.unlock();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        SubscriptionHelper.cancel(this);
        signalConsumer();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        SubscriptionHelper.cancel(this);
        signalConsumer();
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return SubscriptionHelper.CANCELLED == this.get();
    }
}
