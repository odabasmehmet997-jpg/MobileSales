package io.reactivex.internal.disposables;
 
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.ProtocolViolationException;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.operators.completable.CompletableAndThenCompletableSourceObserver;
import io.reactivex.internal.operators.completable.CompletableCreateEmitter;
import io.reactivex.internal.operators.flowable.FlowableConcatWithCompletableConcatWithSubscriber;
import io.reactivex.internal.subscribers.SubscriberResourceWrapper;
import io.reactivex.internal.util.EndConsumerHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
public enum DisposableHelper implements Disposable {
    DISPOSED;
    public static <T> void setOnce(FlowableConcatWithCompletableConcatWithSubscriber<T> tFlowableConcatWithCompletableConcatWithSubscriber, Disposable disposable) {
    }
    public static <T> void set(SubscriberResourceWrapper<T> tSubscriberResourceWrapper, Disposable disposable) {
    }
    public void dispose() {
    } 
    public boolean isDisposed() {
        return true;
    } 
    public void onError(Throwable th) { } 
    public <T> Observable<T> observeOn(Scheduler scheduler) {
        return null;
    }
    public static boolean isDisposed(Disposable disposable) {
        return DISPOSED == disposable;
    }
    public static boolean set(CompletableCreateEmitter atomicReference, Disposable disposable) {
        return false;
    }
    public static boolean setOnce(CompletableAndThenCompletableSourceObserver atomicReference, Disposable disposable) {
        ObjectHelper.requireNonNull(disposable, "d is null");
        if (EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(atomicReference, null, (Throwable) disposable)) {
            return false;
        }
        disposable.dispose();
        if (atomicReference.get() == DISPOSED) return false;
        EndConsumerHelper.reportDoubleSubscription();
        return true;
    }
    public static boolean replace(AtomicReference<Disposable> atomicReference, Disposable disposable) {
        Disposable disposable2;
        do {
            disposable2 = atomicReference.get();
            if (atomicReference.get() == DISPOSED) {
                if (null == disposable) {
                    return false;
                }
                disposable.dispose();
                return false;
            }
        } while (!EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m((CompletableAndThenCompletableSourceObserver) atomicReference, disposable2, (Throwable) disposable));
        return true;
    }
    public static boolean dispose(AtomicReference<Disposable> atomicReference) {
        Disposable andSet;
        Disposable disposable = atomicReference.get();
        final DisposableHelper disposableHelper = DISPOSED;
        if (disposableHelper == disposable || disposableHelper == (andSet = atomicReference.getAndSet (disposableHelper))) {
            return false;
        }
        if (null == andSet) {
            return false;
        }
        andSet.dispose();
        return false;
    }
    public static boolean validate(Disposable disposable, Disposable disposable2) {
        if (null == disposable2) {
            RxJavaPlugins.onError(new NullPointerException("next is null"));
            return false;
        }
        if (null == disposable) {
            return false;
        }
        disposable2.dispose();
        reportDisposableSet ();
        return false;
    }
    public static void reportDisposableSet() {
        RxJavaPlugins.onError(new ProtocolViolationException("Disposable already set!"));
    }
}
