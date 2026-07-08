package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;



final class MaybeZipArrayZipCoordinator<T, R> extends AtomicInteger implements Disposable {
    private static final long serialVersionUID = -5556924161382950569L;
    final MaybeObserver<? super R> downstream;
    final MaybeZipArrayZipMaybeObserver<T>[] observers;
    final Object[] values;
    final Function<? super Object[], ? extends R> zipper;

    MaybeZipArrayZipCoordinator(MaybeObserver<? super R> maybeObserver, int i2, Function<? super Object[], ? extends R> function) {
        super(i2);
        this.downstream = maybeObserver;
        this.zipper = function;
        MaybeZipArrayZipMaybeObserver<T>[] maybeZipArrayZipMaybeObserverArr = new MaybeZipArrayZipMaybeObserver[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            maybeZipArrayZipMaybeObserverArr[i3] = new MaybeZipArrayZipMaybeObserver<>(this, i3);
        }
        this.observers = maybeZipArrayZipMaybeObserverArr;
        this.values = new Object[i2];
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return 0 >= this.get();
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        if (0 < this.getAndSet(0)) {
            for (MaybeZipArrayZipMaybeObserver<T> maybeZipArrayZipMaybeObserver : this.observers) {
                maybeZipArrayZipMaybeObserver.dispose();
            }
        }
    }

    void innerSuccess(T t, int i2) {
        this.values[i2] = t;
        if (0 == this.decrementAndGet()) {
            try {
                this.downstream.onSuccess(ObjectHelper.requireNonNull(this.zipper.apply(this.values), "The zipper returned a null value"));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }
    }

    void disposeExcept(int i2) {
        MaybeZipArrayZipMaybeObserver<T>[] maybeZipArrayZipMaybeObserverArr = this.observers;
        int length = maybeZipArrayZipMaybeObserverArr.length;
        for (int i3 = 0; i3 < i2; i3++) {
            maybeZipArrayZipMaybeObserverArr[i3].dispose();
        }
        while (true) {
            i2++;
            if (i2 >= length) {
                return;
            } else {
                maybeZipArrayZipMaybeObserverArr[i2].dispose();
            }
        }
    }

    void innerError(Throwable th, int i2) {
        if (0 < this.getAndSet(0)) {
            disposeExcept(i2);
            this.downstream.onError(th);
        } else {
            RxJavaPlugins.onError(th);
        }
    }

    void innerComplete(int i2) {
        if (0 < this.getAndSet(0)) {
            disposeExcept(i2);
            this.downstream.onComplete();
        }
    }
}
