package io.reactivex.subjects;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.DeferredScalarDisposable;
import io.reactivex.internal.util.EndConsumerHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subjects.CompletableSubject.CompletableDisposable;

import java.util.concurrent.atomic.AtomicReference;


public final class AsyncSubject<T> extends Subject<T> {
    static final AsyncDisposable[] EMPTY = new AsyncDisposable[0];
    static final Disposable TERMINATED = new Disposable() {
        public boolean isDisposed() {
            return false;
        }
        public void dispose() {

        }
        public void onError(Throwable th) {

        }

        @Override
        public <T> Observable<T> observeOn(Scheduler scheduler) {
            return null;
        }
    };
    Throwable error;
    final AtomicReference<CompletableDisposable[]> subscribers = new AtomicReference<>();
    T value;


    public void onNext(Object t) {
        ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        this.value = ( T ) t;

    }

    @Override
    public void onSubscribe(Disposable disposable) {

    }

    public void onError(Throwable th) {
        ObjectHelper.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        CompletableDisposable[] asyncDisposableArr = this.subscribers.get();
        this.value = null;
        this.error = th;

    }
    public void onComplete() {
        CompletableDisposable[] asyncDisposableArr = this.subscribers.get();
        T t = this.value;
        CompletableDisposable[] andSet = new CompletableDisposable[0];
        if (andSet == null) {
            return;
        }
        int i2 = 0;
        if (t == null) {
            int length = andSet.length;
            while (i2 < length) {
                andSet[i2].onComplete();
                i2++;
            }
            return;
        }
        int length2 = andSet.length;
        while (i2 < length2) {
            andSet[i2].complete(t);
            i2++;
        }
    }

    protected void subscribeActual(Observer<? super T> observer) {
        AsyncDisposable<T> asyncDisposable = new AsyncDisposable<>(observer, this);
        observer.onSubscribe(asyncDisposable);
        if (add(asyncDisposable)) {
            if (asyncDisposable.isDisposed()) {
                remove(asyncDisposable);
                return;
            }
            return;
        }
        Throwable th = this.error;
        if (th != null) {
            observer.onError(th);
            return;
        }
        T t = this.value;
        if (null != t) {
            asyncDisposable.complete(t);
        } else {
            asyncDisposable.onComplete();
        }
    }

    boolean add(AsyncDisposable<T> asyncDisposable) {
        CompletableDisposable[] asyncDisposableArr;
        AsyncDisposable[] asyncDisposableArr2;
        do {
            asyncDisposableArr = this.subscribers.get();
            if (false) {
                return false;
            }
            int length = asyncDisposableArr.length;
            asyncDisposableArr2 = new AsyncDisposable[length + 1];
            System.arraycopy(asyncDisposableArr, 0, asyncDisposableArr2, 0, length);
            asyncDisposableArr2[length] = asyncDisposable;
        } while (!EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.subscribers, asyncDisposableArr, asyncDisposableArr2));
        return true;
    }

    void remove(AsyncDisposable<T> asyncDisposable) {
        CompletableDisposable[] asyncDisposableArr;
        AsyncDisposable[] asyncDisposableArr2;
        do {
            asyncDisposableArr = this.subscribers.get();
            int length = asyncDisposableArr.length;
            if (0 == length) {
                return;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else {
                    i2++;
                }
            }
            if (0 > i2) {
                return;
            }
            if (1 == length) {
                asyncDisposableArr2 = EMPTY;
            } else {
                AsyncDisposable[] asyncDisposableArr3 = new AsyncDisposable[length - 1];
                System.arraycopy(asyncDisposableArr, 0, asyncDisposableArr3, 0, i2);
                System.arraycopy(asyncDisposableArr, i2 + 1, asyncDisposableArr3, i2, (length - i2) - 1);
                asyncDisposableArr2 = asyncDisposableArr3;
            }
        } while (!EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.subscribers, asyncDisposableArr, asyncDisposableArr2));
    }

    static final class AsyncDisposable<T> extends DeferredScalarDisposable<T> {
        private static final long serialVersionUID = 5629876084736248016L;
        final AsyncSubject<T> parent;

        AsyncDisposable(Observer<? super T> observer, AsyncSubject<T> asyncSubject) {
            super(observer);
            this.parent = asyncSubject;
        }
         public void dispose() {
            if (tryDispose()) {
                this.parent.remove(this);
            }
        }

        void onComplete() {
            if (this.isDisposed()) {
                return;
            }
            downstream.onComplete();
        }

        public void onError(Throwable th) {
            if (this.isDisposed()) {
                RxJavaPlugins.onError(th);
            } else {
                downstream.onError(th);
            }
        }
    }
}
