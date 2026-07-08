package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableBufferBoundaryBufferBoundarySubscriber<T, C extends Collection<? super T>, Open, Close> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = -8466418554264089604L;
    final Function<? super Open, ? extends Publisher<? extends Close>> bufferClose;
    final Publisher<? extends Open> bufferOpen;
    final Callable<C> bufferSupplier;
    volatile boolean cancelled;
    volatile boolean done;
    final Subscriber<? super C> downstream;
    long emitted;
    long index;
    final SpscLinkedArrayQueue<C> queue = new SpscLinkedArrayQueue<>(Flowable.bufferSize());
    final CompositeDisposable subscribers = new CompositeDisposable();
    final AtomicLong requested = new AtomicLong();
    final AtomicReference<Subscription> upstream = new AtomicReference<>();
    Map<Long, C> buffers = new LinkedHashMap();
    final AtomicThrowable errors = new AtomicThrowable();

    FlowableBufferBoundaryBufferBoundarySubscriber(Subscriber<? super C> subscriber, Publisher<? extends Open> publisher, Function<? super Open, ? extends Publisher<? extends Close>> function, Callable<C> callable) {
        this.downstream = subscriber;
        this.bufferSupplier = callable;
        this.bufferOpen = publisher;
        this.bufferClose = function;
    }

    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this.upstream, subscription)) {
            BufferOpenSubscriber bufferOpenSubscriber = new BufferOpenSubscriber(this);
            this.subscribers.add(bufferOpenSubscriber);
            this.bufferOpen.subscribe(bufferOpenSubscriber);
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }

    
    public void onNext(Object t) {
        synchronized (this) {
            try {
                Map<Long, C> map = this.buffers;
                if (null == map) {
                    return;
                }
                Iterator<C> it = map.values().iterator();
                while (it.hasNext()) {
                    it.next().add(t);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    
    public void onError(Throwable th) {
        if (this.errors.addThrowable(th)) {
            this.subscribers.dispose();
            synchronized (this) {
                this.buffers = null;
            }
            this.done = true;
            drain();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    
    public void onComplete() {
        this.subscribers.dispose();
        synchronized (this) {
            try {
                Map<Long, C> map = this.buffers;
                if (null == map) {
                    return;
                }
                Iterator<C> it = map.values().iterator();
                while (it.hasNext()) {
                    this.queue.offer(it.next());
                }
                this.buffers = null;
                this.done = true;
                drain();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
    public void request(long j2) {
        BackpressureHelper.add(this.requested, j2);
        drain();
    }
    public void cancel() {
        if (SubscriptionHelper.cancel(this.upstream)) {
            this.cancelled = true;
            this.subscribers.dispose();
            synchronized (this) {
                this.buffers = null;
            }
            if (0 != this.getAndIncrement()) {
                this.queue.clear();
            }
        }
    }

    void open(Open open) {
        try {
            Collection collection = ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The bufferSupplier returned a null Collection");
            Publisher publisher = ObjectHelper.requireNonNull(this.bufferClose.apply(open), "The bufferClose returned a null Publisher");
            long j2 = this.index;
            this.index = 1 + j2;
            synchronized (this) {
                try {
                    Map<Long, C> map = this.buffers;
                    if (null == map) {
                        return;
                    }
                    map.put(Long.valueOf(j2), collection);
                    FlowableBufferBoundaryBufferCloseSubscriber flowableBufferBoundaryBufferCloseSubscriber = new FlowableBufferBoundaryBufferCloseSubscriber(this, j2);
                    this.subscribers.add(flowableBufferBoundaryBufferCloseSubscriber);
                    publisher.subscribe(flowableBufferBoundaryBufferCloseSubscriber);
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            SubscriptionHelper.cancel(this.upstream);
            onError(th2);
        }
    }

    void openComplete(BufferOpenSubscriber<Open> bufferOpenSubscriber) {
        this.subscribers.delete(bufferOpenSubscriber);
        if (0 == subscribers.size ()) {
            SubscriptionHelper.cancel(this.upstream);
            this.done = true;
            drain();
        }
    }

    void close(Disposable flowableBufferBoundaryBufferCloseSubscriber, long j2) {
        boolean z;
        this.subscribers.delete(flowableBufferBoundaryBufferCloseSubscriber);
        if (0 == subscribers.size ()) {
            SubscriptionHelper.cancel(this.upstream);
            z = true;
        } else {
            z = false;
        }
        synchronized (this) {
            try {
                Map<Long, C> map = this.buffers;
                if (null == map) {
                    return;
                }
                this.queue.offer(map.remove(Long.valueOf(j2)));
                if (z) {
                    this.done = true;
                }
                drain();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void boundaryError(Disposable disposable, Throwable th) {
        SubscriptionHelper.cancel(this.upstream);
        this.subscribers.delete(disposable);
        onError(th);
    }

    void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        long j2 = this.emitted;
        Subscriber<? super C> subscriber = this.downstream;
        SpscLinkedArrayQueue<C> spscLinkedArrayQueue = this.queue;
        int i2 = 1;
        do {
            long j3 = this.requested.get();
            while (j2 != j3) {
                if (this.cancelled) {
                    spscLinkedArrayQueue.clear();
                    return;
                }
                boolean z = this.done;
                if (z && null != errors.get ()) {
                    spscLinkedArrayQueue.clear();
                    subscriber.onError(this.errors.terminate());
                    return;
                }
                C poll = spscLinkedArrayQueue.poll();
                boolean z2 = null == poll;
                if (z && z2) {
                    subscriber.onComplete();
                    return;
                } else {
                    if (z2) {
                        break;
                    }
                    subscriber.onNext(poll);
                    j2++;
                }
            }
            if (j2 == j3) {
                if (this.cancelled) {
                    spscLinkedArrayQueue.clear();
                    return;
                }
                if (this.done) {
                    if (null != errors.get ()) {
                        spscLinkedArrayQueue.clear();
                        subscriber.onError(this.errors.terminate());
                        return;
                    } else if (spscLinkedArrayQueue.isEmpty()) {
                        subscriber.onComplete();
                        return;
                    }
                }
            }
            this.emitted = j2;
            i2 = addAndGet(-i2);
        } while (0 != i2);
    }

    static final class BufferOpenSubscriber<Open> extends AtomicReference<Subscription> implements FlowableSubscriber<Open>, Disposable {
        private static final long serialVersionUID = -8498650778633225126L;
        final FlowableBufferBoundaryBufferBoundarySubscriber<?, ?, Open, ?> parent;

        BufferOpenSubscriber(FlowableBufferBoundaryBufferBoundarySubscriber<?, ?, Open, ?> flowableBufferBoundaryBufferBoundarySubscriber) {
            this.parent = flowableBufferBoundaryBufferBoundarySubscriber;
        }

        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.setOnce(this, subscription, LocationRequestCompat.PASSIVE_INTERVAL);
        }

        
        public void onNext(Object open) {
            this.parent.open((Open) open);
        }

        
        public void onError(Throwable th) {
            lazySet(SubscriptionHelper.CANCELLED);
            this.parent.boundaryError(this, th);
        }

        @Override
        public <T> Observable<T> observeOn(Scheduler scheduler) {
            return null;
        }


        public void onComplete() {
            lazySet(SubscriptionHelper.CANCELLED);
            this.parent.openComplete(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return SubscriptionHelper.CANCELLED == this.get();
        }
    }
}
