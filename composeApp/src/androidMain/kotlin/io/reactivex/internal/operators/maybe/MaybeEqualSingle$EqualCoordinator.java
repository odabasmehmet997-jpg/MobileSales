package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeSource;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiPredicate;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;



final class MaybeEqualSingleEqualCoordinator<T> extends AtomicInteger implements Disposable {
    final SingleObserver<? super Boolean> downstream;
    final BiPredicate<? super T, ? super T> isEqual;
    final MaybeEqualSingleEqualObserver<T> observer1;
    final MaybeEqualSingleEqualObserver<T> observer2;

    MaybeEqualSingleEqualCoordinator(final SingleObserver<? super Boolean> singleObserver, final BiPredicate<? super T, ? super T> biPredicate) {
        super(2);
        downstream = singleObserver;
        isEqual = biPredicate;
        observer1 = new MaybeEqualSingleEqualObserver<>(this);
        observer2 = new MaybeEqualSingleEqualObserver<>(this);
    }

    void subscribe(final MaybeSource<? extends T> maybeSource, final MaybeSource<? extends T> maybeSource2) {
        maybeSource.subscribe(observer1);
        maybeSource2.subscribe(observer2);
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        observer1.dispose();
        observer2.dispose();
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        return DisposableHelper.isDisposed(observer1.get());
    }

    void done() {
        if (0 == decrementAndGet()) {
            final Object obj = observer1.value;
            final Object obj2 = observer2.value;
            if (null != obj && null != obj2) {
                try {
                    downstream.onSuccess(Boolean.valueOf(isEqual.test(obj, obj2)));
                    return;
                } catch (final Throwable th) {
                    Exceptions.throwIfFatal(th);
                    downstream.onError(th);
                    return;
                }
            }
            downstream.onSuccess(Boolean.valueOf(null == obj && null == obj2));
        }
    }

    void error(final MaybeEqualSingleEqualObserver<T> maybeEqualSingleEqualObserver, final Throwable th) {
        if (0 < getAndSet(0)) {
            final MaybeEqualSingleEqualObserver<T> maybeEqualSingleEqualObserver2 = observer1;
            if (maybeEqualSingleEqualObserver == maybeEqualSingleEqualObserver2) {
                observer2.dispose();
            } else {
                maybeEqualSingleEqualObserver2.dispose();
            }
            downstream.onError(th);
            return;
        }
        RxJavaPlugins.onError(th);
    }
}
