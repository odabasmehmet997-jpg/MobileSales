package io.reactivex.internal.operators.flowable;

import _COROUTINE.ArtificialStackFrames;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.processors.UnicastProcessor;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableGroupJoinGroupJoinSubscription<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements Subscription, FlowableGroupJoinJoinSupport {
    private static final long serialVersionUID = -6071216598687999801L;
    volatile boolean cancelled;
    final Subscriber<? super R> downstream;
    final Function<? super TLeft, ? extends Publisher<TLeftEnd>> leftEnd;
    int leftIndex;
    final BiFunction<? super TLeft, ? super Flowable<TRight>, ? extends R> resultSelector;
    final Function<? super TRight, ? extends Publisher<TRightEnd>> rightEnd;
    int rightIndex;
    static final Integer LEFT_VALUE = 1;
    static final Integer RIGHT_VALUE = 2;
    static final Integer LEFT_CLOSE = 3;
    static final Integer RIGHT_CLOSE = 4;
    final AtomicLong requested = new AtomicLong();
    final CompositeDisposable disposables = new CompositeDisposable();
    final SpscLinkedArrayQueue<Object> queue = new SpscLinkedArrayQueue<>(Flowable.bufferSize());
    final Map<Integer, UnicastProcessor<TRight>> lefts = new LinkedHashMap();
    final Map<Integer, TRight> rights = new LinkedHashMap();
    final AtomicReference<Throwable> error = new AtomicReference<>();
    final AtomicInteger active = new AtomicInteger(2);

    FlowableGroupJoinGroupJoinSubscription(Subscriber<? super R> subscriber, Function<? super TLeft, ? extends Publisher<TLeftEnd>> function, Function<? super TRight, ? extends Publisher<TRightEnd>> function2, BiFunction<? super TLeft, ? super Flowable<TRight>, ? extends R> biFunction) {
        this.downstream = subscriber;
        this.leftEnd = function;
        this.rightEnd = function2;
        this.resultSelector = biFunction;
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        cancelAll();
        if (0 == this.getAndIncrement()) {
            this.queue.clear();
        }
    }

    void cancelAll() {
        this.disposables.dispose();
    }

    void errorAll(Subscriber<?> subscriber) {
        Throwable terminate = ExceptionHelper.terminate(this.error);
        Iterator<UnicastProcessor<TRight>> it = this.lefts.values().iterator();
        while (it.hasNext()) {
            it.next().onError(terminate);
        }
        this.lefts.clear();
        this.rights.clear();
        subscriber.onError(terminate);
    }

    void fail(Throwable th, Subscriber<?> subscriber, SimpleQueue<?> simpleQueue) {
        Exceptions.throwIfFatal(th);
        ExceptionHelper.addThrowable(this.error, th);
        simpleQueue.clear();
        cancelAll();
        errorAll(subscriber);
    }

    void drain() {
        if (0 != this.getAndIncrement()) {
            return;
        }
        SpscLinkedArrayQueue<Object> spscLinkedArrayQueue = this.queue;
        Subscriber<? super R> subscriber = this.downstream;
        int i2 = 1;
        while (!this.cancelled) {
            if (null != error.get ()) {
                spscLinkedArrayQueue.clear();
                cancelAll();
                errorAll(subscriber);
                return;
            }
            boolean z = 0 == active.get ();
            Integer num = (Integer) spscLinkedArrayQueue.poll();
            boolean z2 = null == num;
            if (z && z2) {
                Iterator<UnicastProcessor<TRight>> it = this.lefts.values().iterator();
                while (it.hasNext()) {
                    it.next().onComplete();
                }
                this.lefts.clear();
                this.rights.clear();
                this.disposables.dispose();
                subscriber.onComplete();
                return;
            }
            if (!z2) {
                Object poll = spscLinkedArrayQueue.poll();
                if (LEFT_VALUE == num) {
                    UnicastProcessor create = UnicastProcessor.create();
                    int i3 = this.leftIndex;
                    this.leftIndex = i3 + 1;
                    this.lefts.put(Integer.valueOf(i3), create);
                    try {
                        Publisher publisher = ObjectHelper.requireNonNull(this.leftEnd.apply(poll), "The leftEnd returned a null Publisher");
                        FlowableGroupJoinLeftRightEndSubscriber flowableGroupJoinLeftRightEndSubscriber = new FlowableGroupJoinLeftRightEndSubscriber(this, true, i3);
                        this.disposables.add(flowableGroupJoinLeftRightEndSubscriber);
                        publisher.subscribe(flowableGroupJoinLeftRightEndSubscriber);
                        if (null != error.get ()) {
                            spscLinkedArrayQueue.clear();
                            cancelAll();
                            errorAll(subscriber);
                            return;
                        }
                        try {
                            ArtificialStackFrames artificialStackFrames = (Object) ObjectHelper.requireNonNull(this.resultSelector.apply(poll, create), "The resultSelector returned a null value");
                            if (0 != requested.get ()) {
                                subscriber.onNext(artificialStackFrames);
                                BackpressureHelper.produced(this.requested, 1L);
                                Iterator<TRight> it2 = this.rights.values().iterator();
                                while (it2.hasNext()) {
                                    create.onNext(it2.next());
                                }
                            } else {
                                fail(new MissingBackpressureException("Could not emit value due to lack of requests"), subscriber, spscLinkedArrayQueue);
                                return;
                            }
                        } catch (Throwable th) {
                            fail(th, subscriber, spscLinkedArrayQueue);
                            return;
                        }
                    } catch (Throwable th2) {
                        fail(th2, subscriber, spscLinkedArrayQueue);
                        return;
                    }
                } else if (RIGHT_VALUE == num) {
                    int i4 = this.rightIndex;
                    this.rightIndex = i4 + 1;
                    this.rights.put(Integer.valueOf(i4), poll);
                    try {
                        Publisher publisher2 = ObjectHelper.requireNonNull(this.rightEnd.apply(poll), "The rightEnd returned a null Publisher");
                        FlowableGroupJoinLeftRightEndSubscriber flowableGroupJoinLeftRightEndSubscriber2 = new FlowableGroupJoinLeftRightEndSubscriber(this, false, i4);
                        this.disposables.add(flowableGroupJoinLeftRightEndSubscriber2);
                        publisher2.subscribe(flowableGroupJoinLeftRightEndSubscriber2);
                        if (null != error.get ()) {
                            spscLinkedArrayQueue.clear();
                            cancelAll();
                            errorAll(subscriber);
                            return;
                        } else {
                            Iterator<UnicastProcessor<TRight>> it3 = this.lefts.values().iterator();
                            while (it3.hasNext()) {
                                it3.next().onNext(poll);
                            }
                        }
                    } catch (Throwable th3) {
                        fail(th3, subscriber, spscLinkedArrayQueue);
                        return;
                    }
                } else if (LEFT_CLOSE == num) {
                    FlowableGroupJoinLeftRightEndSubscriber flowableGroupJoinLeftRightEndSubscriber3 = (FlowableGroupJoinLeftRightEndSubscriber) poll;
                    UnicastProcessor<TRight> remove = this.lefts.remove(Integer.valueOf(flowableGroupJoinLeftRightEndSubscriber3.index));
                    this.disposables.remove(flowableGroupJoinLeftRightEndSubscriber3);
                    if (null != remove) {
                        remove.onComplete();
                    }
                } else if (RIGHT_CLOSE == num) {
                    FlowableGroupJoinLeftRightEndSubscriber flowableGroupJoinLeftRightEndSubscriber4 = (FlowableGroupJoinLeftRightEndSubscriber) poll;
                    this.rights.remove(Integer.valueOf(flowableGroupJoinLeftRightEndSubscriber4.index));
                    this.disposables.remove(flowableGroupJoinLeftRightEndSubscriber4);
                }
            } else {
                i2 = addAndGet(-i2);
                if (0 == i2) {
                    return;
                }
            }
        }
        spscLinkedArrayQueue.clear();
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableGroupJoinJoinSupport
    public void innerError(Throwable th) {
        if (ExceptionHelper.addThrowable(this.error, th)) {
            this.active.decrementAndGet();
            drain();
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableGroupJoinJoinSupport
    public void innerComplete(FlowableGroupJoinLeftRightSubscriber flowableGroupJoinLeftRightSubscriber) {
        this.disposables.delete(flowableGroupJoinLeftRightSubscriber);
        this.active.decrementAndGet();
        drain();
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableGroupJoinJoinSupport
    public void innerValue(boolean z, Object obj) {
        synchronized (this) {
            try {
                this.queue.offer(z ? LEFT_VALUE : RIGHT_VALUE, obj);
            } catch (Throwable th) {
                throw th;
            }
        }
        drain();
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableGroupJoinJoinSupport
    public void innerClose(boolean z, FlowableGroupJoinLeftRightEndSubscriber flowableGroupJoinLeftRightEndSubscriber) {
        synchronized (this) {
            try {
                this.queue.offer(z ? LEFT_CLOSE : RIGHT_CLOSE, flowableGroupJoinLeftRightEndSubscriber);
            } catch (Throwable th) {
                throw th;
            }
        }
        drain();
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableGroupJoinJoinSupport
    public void innerCloseError(Throwable th) {
        if (ExceptionHelper.addThrowable(this.error, th)) {
            drain();
        } else {
            RxJavaPlugins.onError(th);
        }
    }
}
