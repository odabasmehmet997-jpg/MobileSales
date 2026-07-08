package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.util.QueueDrainHelper;
import java.util.concurrent.atomic.AtomicReference;


public final class InnerQueuedObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
    private static final long serialVersionUID = -5417183359794346637L;
    volatile boolean done;
    int fusionMode;
    final InnerQueuedObserverSupport<T> parent;
    final int prefetch;
    SimpleQueue<T> queue;

    public InnerQueuedObserver(final InnerQueuedObserverSupport<T> innerQueuedObserverSupport, final int i2) {
        parent = innerQueuedObserverSupport;
        prefetch = i2;
    }

    public void onSubscribe(final Disposable disposable) {
        if (DisposableHelper.setOnce(this, disposable)) {
            if (disposable instanceof final QueueDisposable queueDisposable) {
                final int requestFusion = queueDisposable.requestFusion(3);
                if (1 == requestFusion) {
                    fusionMode = 1;
                    queue = queueDisposable;
                    done = true;
                    parent.innerComplete(this);
                    return;
                }
                if (2 == requestFusion) {
                    fusionMode = 2;
                    queue = queueDisposable;
                    return;
                }
            }
            queue = QueueDrainHelper.createQueue(-prefetch);
        }
    }

    public void onNext(final Object t) {
        if (0 == this.fusionMode) {
            parent.innerNext(this, t);
        } else {
            parent.drain();
        }
    }
    public void onError(final Throwable th) {
        parent.innerError(this, th);
    }

    public void onComplete() {
        parent.innerComplete(this);
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return DisposableHelper.isDisposed(this.get());
    }

    public boolean isDone() {
        return done;
    }

    public void setDone() {
        done = true;
    }

    public SimpleQueue<T> queue() {
        return queue;
    }

    public int fusionMode() {
        return fusionMode;
    }
}
