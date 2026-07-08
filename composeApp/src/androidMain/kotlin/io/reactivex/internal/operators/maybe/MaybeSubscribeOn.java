package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import java.util.concurrent.atomic.AtomicReference;



public final class MaybeSubscribeOn<T> extends AbstractMaybeWithUpstream<T, T> {
    final Scheduler scheduler;

    public MaybeSubscribeOn(final MaybeSource<T> maybeSource, final Scheduler scheduler) {
        super(maybeSource);
        this.scheduler = scheduler;
    }

    @Override // io.reactivex.Maybe
    protected void subscribeActual(final MaybeObserver<? super T> maybeObserver) {
        final SubscribeOnMaybeObserver subscribeOnMaybeObserver = new SubscribeOnMaybeObserver(maybeObserver);
        maybeObserver.onSubscribe(subscribeOnMaybeObserver);
        subscribeOnMaybeObserver.task.replace(scheduler.scheduleDirect(new SubscribeTask(subscribeOnMaybeObserver, source)));
    }

    class SubscribeTask<T>(MaybeObserver<? super T> observer, MaybeSource<T> source) implements Runnable {

            public void run() {
                source.subscribe(observer);
            }
        }

    static final class SubscribeOnMaybeObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 8571289934935992137L;
        final MaybeObserver<? super T> downstream;
        final SequentialDisposable task = new SequentialDisposable();

        SubscribeOnMaybeObserver(final MaybeObserver<? super T> maybeObserver) {
            downstream = maybeObserver;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
            task.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.get());
        }

        @Override // io.reactivex.MaybeObserver
        public void onSubscribe(final Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        @Override // io.reactivex.MaybeObserver
        public void onSuccess(final T t) {
            downstream.onSuccess(t);
        }

        @Override // io.reactivex.MaybeObserver
        public void onError(final Throwable th) {
            downstream.onError(th);
        }

        @Override // io.reactivex.MaybeObserver
        public void onComplete() {
            downstream.onComplete();
        }
    }
}
