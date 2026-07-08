package io.reactivex.processors;

import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

final class SerializedProcessor<T> extends FlowableProcessor<T> {
    final FlowableProcessor<T> actual;
    volatile boolean done;
    boolean emitting;
    AppendOnlyLinkedArrayList<Object> queue;
    SerializedProcessor(final FlowableProcessor<T> flowableProcessor) {
        actual = flowableProcessor;
    }
    protected void subscribeActual(final Subscriber<? super T> subscriber) {
        actual.subscribe(subscriber);
    }
    public void onSubscribe(final Subscription subscription) {
        boolean z = true;
        if (!done) {
            synchronized (this) {
                if (!this.done) {
                    if (this.emitting) {
                        AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.queue;
                        if (null == appendOnlyLinkedArrayList) {
                            appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                            this.queue = appendOnlyLinkedArrayList;
                        }
                        appendOnlyLinkedArrayList.add(NotificationLite.subscription(subscription));
                        return;
                    }
                    this.emitting = true;
                    z = false;
                }
            }
        }
        if (z) {
            subscription.cancel();
        } else {
            actual.onSubscribe(subscription);
            this.emitLoop();
        }
    }
    public void onNext(final Object t) {
        if (done) {
            return;
        }
        synchronized (this) {
            try {
                if (done) {
                    return;
                }
                if (emitting) {
                    AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = queue;
                    if (null == appendOnlyLinkedArrayList) {
                        appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                        queue = appendOnlyLinkedArrayList;
                    }
                    appendOnlyLinkedArrayList.add(NotificationLite.next(t));
                    return;
                }
                emitting = true;
                actual.onNext(t);
                this.emitLoop();
            } catch (final Throwable th) {
                throw th;
            }
        }
    }
    public void onError(final Throwable th) {
        if (done) {
            RxJavaPlugins.onError(th);
            return;
        }
        synchronized (this) {
            try {
                boolean z = true;
                if (!done) {
                    done = true;
                    if (emitting) {
                        AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = queue;
                        if (null == appendOnlyLinkedArrayList) {
                            appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                            queue = appendOnlyLinkedArrayList;
                        }
                        appendOnlyLinkedArrayList.setFirst(NotificationLite.error(th));
                        return;
                    }
                    emitting = true;
                    z = false;
                }
                if (z) {
                    RxJavaPlugins.onError(th);
                } else {
                    actual.onError(th);
                }
            } catch (final Throwable th2) {
                throw th2;
            }
        }
    }
    public void onComplete() {
        if (done) {
            return;
        }
        synchronized (this) {
            try {
                if (done) {
                    return;
                }
                done = true;
                if (emitting) {
                    AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = queue;
                    if (null == appendOnlyLinkedArrayList) {
                        appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                        queue = appendOnlyLinkedArrayList;
                    }
                    appendOnlyLinkedArrayList.add(NotificationLite.complete());
                    return;
                }
                emitting = true;
                actual.onComplete();
            } catch (final Throwable th) {
                throw th;
            }
        }
    }
    void emitLoop() {
        AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList;
        while (true) {
            synchronized (this) {
                try {
                    appendOnlyLinkedArrayList = queue;
                    if (null == appendOnlyLinkedArrayList) {
                        emitting = false;
                        return;
                    }
                    queue = null;
                } catch (final Throwable th) {
                    throw th;
                }
            }
            appendOnlyLinkedArrayList.accept(actual);
        }
    }
}
