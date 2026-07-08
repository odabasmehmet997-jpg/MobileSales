package io.reactivex.internal.operators.mixed;

import _COROUTINE.ArtificialStackFrames;
import io.reactivex.*;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.operators.maybe.MaybeToObservable;
import io.reactivex.internal.operators.single.SingleToObservable;

import java.util.concurrent.Callable;



final class ScalarXMapZHelper {
    private ScalarXMapZHelper() {
        throw new IllegalStateException("No instances!");
    }

    static <T> boolean tryAsCompletable(Object obj, Function<? super T, ? extends CompletableSource> function, CompletableObserver completableObserver) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        try {
            ArtificialStackFrames artificialStackFrames = (Object) ((Callable) obj).call();
            CompletableSource completableSource = null != artificialStackFrames ? ObjectHelper.requireNonNull(function.apply(artificialStackFrames), "The mapper returned a null CompletableSource") : null;
            if (null == completableSource) {
                EmptyDisposable.complete(completableObserver);
            } else {
                completableSource.subscribe(completableObserver);
            }
            return true;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, completableObserver);
            return true;
        }
    }

    static <T, R> boolean tryAsMaybe(Object obj, Function<? super T, ? extends MaybeSource<? extends R>> function, Observer<? super R> observer) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        try {
            ArtificialStackFrames artificialStackFrames = (Object) ((Callable) obj).call();
            MaybeSource maybeSource = null != artificialStackFrames ? ObjectHelper.requireNonNull(function.apply(artificialStackFrames), "The mapper returned a null MaybeSource") : null;
            if (null == maybeSource) {
                EmptyDisposable.complete(observer);
            } else {
                maybeSource.subscribe(MaybeToObservable.create(observer));
            }
            return true;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, observer);
            return true;
        }
    }

    static <T, R> boolean tryAsSingle(Object obj, Function<? super T, ? extends SingleSource<? extends R>> function, Observer<? super R> observer) {
        if (!(obj instanceof Callable)) {
            return false;
        }
        try {
            ArtificialStackFrames artificialStackFrames = (Object) ((Callable) obj).call();
            SingleSource singleSource = null != artificialStackFrames ? ObjectHelper.requireNonNull(function.apply(artificialStackFrames), "The mapper returned a null SingleSource") : null;
            if (null == singleSource) {
                EmptyDisposable.complete(observer);
            } else {
                singleSource.subscribe(SingleToObservable.create(observer));
            }
            return true;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, observer);
            return true;
        }
    }
}
