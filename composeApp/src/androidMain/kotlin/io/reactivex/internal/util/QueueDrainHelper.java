package io.reactivex.internal.util;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

public final class QueueDrainHelper {
    private QueueDrainHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, U> void drainLoop(final SimplePlainQueue<T> simplePlainQueue, final Observer<? super U> observer, final boolean z, final Disposable disposable, final ObservableQueueDrain<T, U> observableQueueDrain) {
        final int i2 = 1;
        while (!QueueDrainHelper.checkTerminated(observableQueueDrain.done(), simplePlainQueue.isEmpty(), observer, z, simplePlainQueue, disposable, observableQueueDrain)) {
            while (true) {
                final boolean done = observableQueueDrain.done();
                final T poll = simplePlainQueue.poll();
                final boolean z2 = null == poll;
                if (QueueDrainHelper.checkTerminated(done, z2, observer, z, simplePlainQueue, disposable, observableQueueDrain)) {
                    return;
                }
                if (z2) {
                    break;
                } else {
                    observableQueueDrain.accept(observer, poll);
                }
            }
        }
    }

    public static <T, U> boolean checkTerminated(final boolean z, final boolean z2, final Observer<?> observer, final boolean z3, final SimpleQueue<?> simpleQueue, final Disposable disposable, final ObservableQueueDrain<T, U> observableQueueDrain) {
        if (observableQueueDrain.cancelled()) {
            simpleQueue.clear();
            disposable.dispose();
            return true;
        }
        if (!z) {
            return false;
        }
        if (z3) {
            if (!z2) {
                return false;
            }
            if (null != disposable) {
                disposable.dispose();
            }
            final Throwable error = observableQueueDrain.error();
            if (null != error) {
                observer.onError(error);
            } else {
                observer.onComplete();
            }
            return true;
        }
        final Throwable error2 = observableQueueDrain.error();
        if (null != error2) {
            simpleQueue.clear();
            if (null != disposable) {
                disposable.dispose();
            }
            observer.onError(error2);
            return true;
        }
        if (!z2) {
            return false;
        }
        if (null != disposable) {
            disposable.dispose();
        }
        observer.onComplete();
        return true;
    }

    public static <T> SimpleQueue<T> createQueue(final int i2) {
        if (0 > i2) {
            return new SpscLinkedArrayQueue(-i2);
        }
        return new SpscArrayQueue(i2);
    }

    public static void request(final Subscription subscription, final int i2) {
        subscription.request(0 > i2 ? LocationRequestCompat.PASSIVE_INTERVAL : i2);
    }

    public static <T> boolean postCompleteRequest(final long j2, final Subscriber<? super T> subscriber, final Queue<T> queue, final AtomicLong atomicLong, final BooleanSupplier booleanSupplier) {
        long j3;
        do {
            j3 = atomicLong.get();
        } while (!atomicLong.compareAndSet(j3, BackpressureHelper.addCap(LocationRequestCompat.PASSIVE_INTERVAL & j3, j2) | (j3 & Long.MIN_VALUE)));
        if (Long.MIN_VALUE != j3) {
            return false;
        }
        QueueDrainHelper.postCompleteDrain(j2 | Long.MIN_VALUE, subscriber, queue, atomicLong, booleanSupplier);
        return true;
    }

    public static boolean isCancelled(final BooleanSupplier booleanSupplier) {
        try {
            return booleanSupplier.getAsBoolean();
        } catch (final Throwable th) {
            Exceptions.throwIfFatal(th);
            return true;
        }
    }

    static <T> boolean postCompleteDrain(long j2, final Subscriber<? super T> subscriber, final Queue<T> queue, final AtomicLong atomicLong, final BooleanSupplier booleanSupplier) {
        long j3 = j2 & Long.MIN_VALUE;
        while (true) {
            if (j3 != j2) {
                if (QueueDrainHelper.isCancelled(booleanSupplier)) {
                    return true;
                }
                final T poll = queue.poll();
                if (null == poll) {
                    subscriber.onComplete();
                    return true;
                }
                subscriber.onNext(poll);
                j3++;
            } else {
                if (QueueDrainHelper.isCancelled(booleanSupplier)) {
                    return true;
                }
                if (queue.isEmpty()) {
                    subscriber.onComplete();
                    return true;
                }
                j2 = atomicLong.get();
                if (j2 == j3) {
                    final long addAndGet = atomicLong.addAndGet(-(j3 & LocationRequestCompat.PASSIVE_INTERVAL));
                    if (0 == (LocationRequestCompat.PASSIVE_INTERVAL & addAndGet)) {
                        return false;
                    }
                    j2 = addAndGet;
                    j3 = addAndGet & Long.MIN_VALUE;
                } else {
                    continue;
                }
            }
        }
    }

    public static <T> void postComplete(final Subscriber<? super T> subscriber, final Queue<T> queue, final AtomicLong atomicLong, final BooleanSupplier booleanSupplier) {
        long j2;
        long j3;
        if (queue.isEmpty()) {
            subscriber.onComplete();
            return;
        }
        if (QueueDrainHelper.postCompleteDrain(atomicLong.get(), subscriber, queue, atomicLong, booleanSupplier)) {
            return;
        }
        do {
            j2 = atomicLong.get();
            if (0 != (j2 & Long.MIN_VALUE)) {
                return;
            } else {
                j3 = j2 | Long.MIN_VALUE;
            }
        } while (!atomicLong.compareAndSet(j2, j3));
        if (0 != j2) {
            QueueDrainHelper.postCompleteDrain(j3, subscriber, queue, atomicLong, booleanSupplier);
        }
    }
}
