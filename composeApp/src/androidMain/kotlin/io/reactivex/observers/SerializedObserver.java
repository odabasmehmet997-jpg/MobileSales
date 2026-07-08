package io.reactivex.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;

public final class SerializedObserver<T> implements Observer<T>, Disposable {
    final boolean delayError;
    volatile boolean done;
    final Observer<? super T> downstream;
    boolean emitting;
    AppendOnlyLinkedArrayList<Object> queue;
    Disposable upstream;

    public SerializedObserver(final Observer<? super T> observer) {
        this(observer, false);
    }

    public SerializedObserver(final Observer<? super T> observer, final boolean z) {
        downstream = observer;
        delayError = z;
    }

    public void onSubscribe(final Disposable disposable) {
        if (DisposableHelper.validate(upstream, disposable)) {
            upstream = disposable;
            downstream.onSubscribe(this);
        }
    }

    public void dispose() {
        upstream.dispose();
    }
    public boolean isDisposed() {
        return upstream.isDisposed();
    }
    public void onNext(final Object t) {
        if (done) {
            return;
        }
        if (null == t) {
            upstream.dispose();
            this.onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
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
                downstream.onNext(t);
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
                    if (emitting) {
                        done = true;
                        AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = queue;
                        if (null == appendOnlyLinkedArrayList) {
                            appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                            queue = appendOnlyLinkedArrayList;
                        }
                        final Object error = NotificationLite.error(th);
                        if (delayError) {
                            appendOnlyLinkedArrayList.add(error);
                        } else {
                            appendOnlyLinkedArrayList.setFirst(error);
                        }
                        return;
                    }
                    done = true;
                    emitting = true;
                    z = false;
                }
                if (z) {
                    RxJavaPlugins.onError(th);
                } else {
                    downstream.onError(th);
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
                if (emitting) {
                    AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = queue;
                    if (null == appendOnlyLinkedArrayList) {
                        appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                        queue = appendOnlyLinkedArrayList;
                    }
                    appendOnlyLinkedArrayList.add(NotificationLite.complete());
                    return;
                }
                done = true;
                emitting = true;
                downstream.onComplete();
            } catch (final Throwable th) {
                throw th;
            }
        }
    }

    void emitLoop() {
        AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList;
        do {
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
        } while (!appendOnlyLinkedArrayList.accept(downstream));
    }
}
