package io.reactivex.internal.util;

import com.proje.mobilesales.features.sales.view.newadd.T;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.ProtocolViolationException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.operators.completable.CompletableAndThenCompletableSourceObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.processors.AsyncProcessor;
import io.reactivex.processors.BehaviorProcessor;
import io.reactivex.subscribers.DisposableSubscriber;
import org.reactivestreams.Subscription;
import rx.plugins.*;
import rx.subscriptions.RefCountSubscription;
import java.util.concurrent.atomic.AtomicReference;

public final class EndConsumerHelper {
    private EndConsumerHelper() {
        throw new IllegalStateException("No instances!");
    }
    public static boolean validate(final Disposable disposable, final Disposable disposable2, final Class<?> cls) {
        ObjectHelper.requireNonNull(disposable2, "next is null");
        if (null == disposable) {
            return true;
        }
        disposable2.dispose();
        if (DisposableHelper.DISPOSED == disposable) {
            return false;
        }
        EndConsumerHelper.reportDoubleSubscription(cls);
        return false;
    }
    public static boolean setOnce(final AtomicReference<Disposable> atomicReference, final Disposable disposable, final Class<?> cls) {
        ObjectHelper.requireNonNull(disposable, "next is null");
        if (LifecycleKtExternalSyntheticBackportWithForwarding0.m((CompletableAndThenCompletableSourceObserver) atomicReference, null, (Throwable) disposable)) {
            return true;
        }
        disposable.dispose();
        if (DisposableHelper.DISPOSED == atomicReference.get()) {
            return false;
        }
        EndConsumerHelper.reportDoubleSubscription(cls);
        return false;
    }
    public static boolean setOnce(final CompletableAndThenCompletableSourceObserver atomicReference, final Subscription subscription, final Class<? extends DisposableSubscriber> cls) {
        ObjectHelper.requireNonNull(subscription, "next is null");
        if (LifecycleKtExternalSyntheticBackportWithForwarding0.m(atomicReference, null, (Throwable) subscription)) {
            return true;
        }
        subscription.cancel();

        EndConsumerHelper.reportDoubleSubscription(cls);
        return false;
    }
    public static String composeMessage(final String str) {
        return "It is not allowed to subscribe with a(n) " + str + " multiple times. Please create a fresh instance of " + str + " and subscribe that to the target source instead.";
    }
    public static void reportDoubleSubscription(final Class<?> cls) {
        RxJavaPlugins.onError(new ProtocolViolationException(EndConsumerHelper.composeMessage(cls.getName())));
    }
    public static void reportDoubleSubscription() {
    }
    public static boolean setOnce(AtomicReference<Subscription> upstream, Subscription subscription, Class<? extends DisposableSubscriber> aClass) {
        return false;
    }
    public static class LifecycleKtExternalSyntheticBackportWithForwarding0 {
        public static boolean m(CompletableAndThenCompletableSourceObserver atomicReference, Object o, Throwable disposable) {
            return false;
        }
        public static boolean m(AtomicReference<AsyncProcessor.AsyncSubscription[]> subscribers, AsyncProcessor.AsyncSubscription<T>[] asyncSubscriptionArr, AsyncProcessor.AsyncSubscription[] asyncSubscriptionArr2) {
            return false;
        }
        public static boolean m(AtomicReference<Disposable> subscribers, Disposable behaviorSubscriptionArr, BehaviorProcessor.BehaviorSubscription[] behaviorSubscriptionArr2) {
            return false;
        }
        public static boolean m(AtomicReference<AsyncProcessor.AsyncSubscription[]> subscribers, Disposable asyncSubscriptionArr, AsyncProcessor.AsyncSubscription[] asyncSubscriptionArr2) {
            return false;
        }
        public static boolean m(AtomicReference<RefCountSubscription.State> atomicReference, RefCountSubscription.State state, RefCountSubscription.State state1) {
            return false;
        }
        public static void m(AtomicReference<RxJavaErrorHandler> errorHandler, Object o, RxJavaErrorHandler defaultErrorHandler) { }
        public static void m(AtomicReference<RxJavaObservableExecutionHook> observableExecutionHook, Object o, RxJavaObservableExecutionHook instance) { }
        public static void m(AtomicReference<RxJavaCompletableExecutionHook> completableExecutionHook, Object o, RxJavaCompletableExecutionHook rxJavaCompletableExecutionHook) { }
        public static void m(AtomicReference<RxJavaSchedulersHook> schedulersHook, Object o, RxJavaSchedulersHook defaultInstance) { }
        public static void m(AtomicReference<RxJavaSingleExecutionHook> singleExecutionHook, Object o, RxJavaSingleExecutionHook instance) { }

        public static boolean m(AtomicReference<Boolean> eagerComponentsInitializedWith, Object o, Boolean aBoolean) {
          return false;
        }
    }
}
