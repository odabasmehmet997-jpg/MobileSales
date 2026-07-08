package io.reactivex.internal.operators.single;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;



final class SingleZipArrayZipCoordinator<T, R> extends AtomicInteger implements Disposable {
    private static final long serialVersionUID = -5556924161382950569L;
    final SingleObserver<? super R> downstream;
    final SingleZipArrayZipSingleObserver<T>[] observers;
    final Object[] values;
    final Function<? super Object[], ? extends R> zipper;

    SingleZipArrayZipCoordinator(SingleObserver<? super R> singleObserver, int i2, Function<? super Object[], ? extends R> function) {
        super(i2);
        this.downstream = singleObserver;
        this.zipper = function;
        SingleZipArrayZipSingleObserver<T>[] singleZipArrayZipSingleObserverArr = new SingleZipArrayZipSingleObserver[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            singleZipArrayZipSingleObserverArr[i3] = new SingleZipArrayZipSingleObserver<>(this, i3);
        }
        this.observers = singleZipArrayZipSingleObserverArr;
        this.values = new Object[i2];
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return 0 >= this.get();
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        if (0 < this.getAndSet(0)) {
            for (SingleZipArrayZipSingleObserver<T> singleZipArrayZipSingleObserver : this.observers) {
                singleZipArrayZipSingleObserver.dispose();
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
        SingleZipArrayZipSingleObserver<T>[] singleZipArrayZipSingleObserverArr = this.observers;
        int length = singleZipArrayZipSingleObserverArr.length;
        for (int i3 = 0; i3 < i2; i3++) {
            singleZipArrayZipSingleObserverArr[i3].dispose();
        }
        while (true) {
            i2++;
            if (i2 >= length) {
                return;
            } else {
                singleZipArrayZipSingleObserverArr[i2].dispose();
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
}
