package io.reactivex.internal.operators.single;

import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;



final class SingleTimeoutTimeoutMainObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Runnable, Disposable {
    private static final long serialVersionUID = 37497744973048446L;
    final SingleObserver<? super T> downstream;
    final TimeoutFallbackObserver<T> fallback;
    SingleSource<? extends T> other;
    final AtomicReference<Disposable> task = new AtomicReference<>();
    final long timeout;
    final TimeUnit unit;

    static final class TimeoutFallbackObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T> {
        private static final long serialVersionUID = 2071387740092105509L;
        final SingleObserver<? super T> downstream;

        TimeoutFallbackObserver(SingleObserver<? super T> singleObserver) {
            this.downstream = singleObserver;
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }
    }

    SingleTimeoutTimeoutMainObserver(SingleObserver<? super T> singleObserver, SingleSource<? extends T> singleSource, long j2, TimeUnit timeUnit) {
        this.downstream = singleObserver;
        this.other = singleSource;
        this.timeout = j2;
        this.unit = timeUnit;
        if (null != singleSource) {
            this.fallback = new TimeoutFallbackObserver<>(singleObserver);
        } else {
            this.fallback = null;
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        Disposable disposable = get();
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper == disposable || !compareAndSet(disposable, disposableHelper)) {
            return;
        }
        if (null != disposable) {
            disposable.dispose();
        }
        SingleSource<? extends T> singleSource = this.other;
        if (null == singleSource) {
            this.downstream.onError(new TimeoutException(ExceptionHelper.timeoutMessage(this.timeout, this.unit)));
        } else {
            this.other = null;
            singleSource.subscribe(this.fallback);
        }
    }

    @Override // io.reactivex.SingleObserver
    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    @Override // io.reactivex.SingleObserver
    public void onSuccess(T t) {
        Disposable disposable = get();
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper == disposable || !compareAndSet(disposable, disposableHelper)) {
            return;
        }
        DisposableHelper.dispose(this.task);
        this.downstream.onSuccess(t);
    }

    @Override // io.reactivex.SingleObserver
    public void onError(Throwable th) {
        Disposable disposable = get();
        final DisposableHelper disposableHelper = DisposableHelper.DISPOSED;
        if (disposableHelper != disposable && compareAndSet(disposable, disposableHelper)) {
            DisposableHelper.dispose(this.task);
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        DisposableHelper.dispose(this);
        DisposableHelper.dispose(this.task);
        TimeoutFallbackObserver<T> timeoutFallbackObserver = this.fallback;
        if (null != timeoutFallbackObserver) {
            DisposableHelper.dispose(timeoutFallbackObserver);
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(get());
    }
}
