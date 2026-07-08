package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.NotificationLite;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;


public final class BlockingObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
    public static final Object TERMINATED = new Object();
    private static final long serialVersionUID = -4875965440900746268L;
    final Queue<Object> queue;

    public BlockingObserver(final Queue<Object> queue) {
        this.queue = queue;
    }

    public void onSubscribe(final Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    public void onNext(final Object t) {
        queue.offer(NotificationLite.next(t));
    }

    public void onError(final Throwable th) {
        queue.offer(NotificationLite.error(th));
    }

    public void onComplete() {
        queue.offer(NotificationLite.complete());
    }

    public void dispose() {
        if (DisposableHelper.dispose(this)) {
            queue.offer(BlockingObserver.TERMINATED);
        }
    }

    public boolean isDisposed() {
        return DisposableHelper.DISPOSED == get();
    }
}
