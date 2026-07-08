package io.reactivex.internal.util;

import io.reactivex.Observer;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;

import java.util.concurrent.atomic.AtomicInteger;


public final class HalfSerializer {
    private HalfSerializer() {
        throw new IllegalStateException("No instances!");
    }
    public static <T> void onNext(final Subscriber<? super T> subscriber, final Object t, final AtomicInteger atomicInteger, final AtomicThrowable atomicThrowable) {
        if (0 == atomicInteger.get () && atomicInteger.compareAndSet(0, 1)) {
            subscriber.onNext(t);
            if (0 != atomicInteger.decrementAndGet ()) {
                final Throwable terminate = atomicThrowable.terminate();
                if (null != terminate) {
                    subscriber.onError(terminate);
                } else {
                    subscriber.onComplete();
                }
            }
        }
    }
    public static void onError(final Subscriber<?> subscriber, final Throwable th, final AtomicInteger atomicInteger, final AtomicThrowable atomicThrowable) {
        if (atomicThrowable.addThrowable(th)) {
            if (0 == atomicInteger.getAndIncrement ()) {
                subscriber.onError(atomicThrowable.terminate());
                return;
            }
            return;
        }
        RxJavaPlugins.onError(th);
    }
    public static void onComplete(final Subscriber<?> subscriber, final AtomicInteger atomicInteger, final AtomicThrowable atomicThrowable) {
        if (0 == atomicInteger.getAndIncrement ()) {
            final Throwable terminate = atomicThrowable.terminate();
            if (null != terminate) {
                subscriber.onError(terminate);
            } else {
                subscriber.onComplete();
            }
        }
    }
    public static <T> void onNext(final Observer<? super T> observer, final T t, final AtomicInteger atomicInteger, final AtomicThrowable atomicThrowable) {
        if (0 == atomicInteger.get () && atomicInteger.compareAndSet(0, 1)) {
            observer.onNext(t);
            if (0 != atomicInteger.decrementAndGet ()) {
                final Throwable terminate = atomicThrowable.terminate();
                if (null != terminate) {
                    observer.onError(terminate);
                } else {
                    observer.onComplete();
                }
            }
        }
    }
    public static void onError(final Observer<?> observer, final Throwable th, final AtomicInteger atomicInteger, final AtomicThrowable atomicThrowable) {
        if (atomicThrowable.addThrowable(th)) {
            if (0 == atomicInteger.getAndIncrement ()) {
                observer.onError(atomicThrowable.terminate());
                return;
            }
            return;
        }
        RxJavaPlugins.onError(th);
    }
    public static void onComplete(final Observer<?> observer, final AtomicInteger atomicInteger, final AtomicThrowable atomicThrowable) {
        if (0 == atomicInteger.getAndIncrement ()) {
            final Throwable terminate = atomicThrowable.terminate();
            if (null != terminate) {
                observer.onError(terminate);
            } else {
                observer.onComplete();
            }
        }
    }

}
