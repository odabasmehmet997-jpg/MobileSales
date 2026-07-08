package rx.internal.operators;

import androidx.core.location.LocationRequestCompat;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Subscriber;
import rx.functions.Func1;
import rx.internal.util.UtilityFunctions;

public final class BackpressureUtils {
    public static long addCap(long j2, long j3) {
        long j4 = j2 + j3;
        return j4 < 0 ? LocationRequestCompat.PASSIVE_INTERVAL : j4;
    }
    private BackpressureUtils() {
        throw new IllegalStateException("No instances!");
    }
    public static long getAndAddRequest(AtomicLong atomicLong, long j2) {
        long j3;
        do {
            j3 = atomicLong.get();
        } while (!atomicLong.compareAndSet(j3, addCap(j3, j2)));
        return j3;
    }
    public static long multiplyCap(long j2, long j3) {
        long j4 = j2 * j3;
        return (((j2 | j3) >>> 31) == 0 || j3 == 0 || j4 / j3 == j2) ? j4 : LocationRequestCompat.PASSIVE_INTERVAL;
    }
    public static <T> void postCompleteDone(AtomicLong atomicLong, Queue<T> queue, Subscriber<? super T> subscriber) {
        postCompleteDone(atomicLong, queue, subscriber, UtilityFunctions.identity());
    }
    public static <T> boolean postCompleteRequest(AtomicLong atomicLong, long j2, Queue<T> queue, Subscriber<? super T> subscriber) {
        return postCompleteRequest(atomicLong, j2, queue, subscriber, UtilityFunctions.identity());
    }
    public static <T, R> void postCompleteDone(AtomicLong atomicLong, Queue<T> queue, Subscriber<? super R> subscriber, Func1<? super T, ? extends R> func1) {
        long j2;
        do {
            j2 = atomicLong.get();
            if ((j2 & Long.MIN_VALUE) != 0) {
                return;
            }
        } while (!atomicLong.compareAndSet(j2, Long.MIN_VALUE | j2));
        if (j2 != 0) {
            postCompleteDrain(atomicLong, queue, subscriber, func1);
        }
    }
    public static <T, R> boolean postCompleteRequest(AtomicLong atomicLong, long j2, Queue<T> queue, Subscriber<? super R> subscriber, Func1<? super T, ? extends R> func1) {
        long j3;
        long j4;
        if (j2 < 0) {
            throw new IllegalArgumentException("n >= 0 required but it was " + j2);
        }
        if (j2 == 0) {
            return (atomicLong.get() & Long.MIN_VALUE) == 0;
        }
        do {
            j3 = atomicLong.get();
            j4 = j3 & Long.MIN_VALUE;
        } while (!atomicLong.compareAndSet(j3, addCap(LocationRequestCompat.PASSIVE_INTERVAL & j3, j2) | j4));
        if (j3 != Long.MIN_VALUE) {
            return j4 == 0;
        }
        postCompleteDrain(atomicLong, queue, subscriber, func1);
        return false;
    }
    static <T, R> void postCompleteDrain(AtomicLong atomicLong, Queue<T> queue, Subscriber<? super R> subscriber, Func1<? super T, ? extends R> func1) {
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.BackpressureUtils.postCompleteDrain(java.util.concurrent.atomic.AtomicLong, java.util.Queue, rx.Subscriber, rx.functions.Func1):void");
    }
    public static long produced(AtomicLong atomicLong, long j2) {
        long j3;
        long j4;
        do {
            j3 = atomicLong.get();
            if (j3 == LocationRequestCompat.PASSIVE_INTERVAL) {
                return LocationRequestCompat.PASSIVE_INTERVAL;
            }
            j4 = j3 - j2;
            if (j4 < 0) {
                throw new IllegalStateException("More produced than requested: " + j4);
            }
        } while (!atomicLong.compareAndSet(j3, j4));
        return j4;
    }
    public static boolean validate(long j2) {
        if (j2 >= 0) {
            return j2 != 0;
        }
        throw new IllegalArgumentException("n >= 0 required but it was " + j2);
    }
}
