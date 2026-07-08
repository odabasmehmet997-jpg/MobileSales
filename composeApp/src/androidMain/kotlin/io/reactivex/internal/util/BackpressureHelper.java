package io.reactivex.internal.util;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicLong;


public final class BackpressureHelper {
    public static long addCap(final long j2, final long j3) {
        final long j4 = j2 + j3;
        return 0 > j4 ? LocationRequestCompat.PASSIVE_INTERVAL : j4;
    }
    private BackpressureHelper() {
        throw new IllegalStateException("No instances!");
    }
    public static long multiplyCap(final long j2, final long j3) {
        final long j4 = j2 * j3;
        return (0 == ((j2 | j3) >>> 31) || j4 / j2 == j3) ? j4 : LocationRequestCompat.PASSIVE_INTERVAL;
    }
    public static long add(final AtomicLong atomicLong, final long j2) {
        long j3;
        do {
            j3 = atomicLong.get();
            if (LocationRequestCompat.PASSIVE_INTERVAL == j3) {
                return LocationRequestCompat.PASSIVE_INTERVAL;
            }
        } while (!atomicLong.compareAndSet(j3, BackpressureHelper.addCap(j3, j2)));
        return j3;
    }
    public static long addCancel(final AtomicLong atomicLong, final long j2) {
        long j3;
        do {
            j3 = atomicLong.get();
            if (Long.MIN_VALUE == j3) {
                return Long.MIN_VALUE;
            }
            if (LocationRequestCompat.PASSIVE_INTERVAL == j3) {
                return LocationRequestCompat.PASSIVE_INTERVAL;
            }
        } while (!atomicLong.compareAndSet(j3, BackpressureHelper.addCap(j3, j2)));
        return j3;
    }
    public static long produced(final AtomicLong atomicLong, final long j2) {
        long j3;
        long j4;
        do {
            j3 = atomicLong.get();
            if (LocationRequestCompat.PASSIVE_INTERVAL == j3) {
                return LocationRequestCompat.PASSIVE_INTERVAL;
            }
            j4 = j3 - j2;
            if (0 > j4) {
                RxJavaPlugins.onError(new IllegalStateException("More produced than requested: " + j4));
                j4 = 0L;
            }
        } while (!atomicLong.compareAndSet(j3, j4));
        return j4;
    }
    public static long producedCancel(final AtomicLong atomicLong, final long j2) {
        long j3;
        long j4;
        do {
            j3 = atomicLong.get();
            if (Long.MIN_VALUE == j3) {
                return Long.MIN_VALUE;
            }
            if (LocationRequestCompat.PASSIVE_INTERVAL == j3) {
                return LocationRequestCompat.PASSIVE_INTERVAL;
            }
            j4 = j3 - j2;
            if (0 > j4) {
                RxJavaPlugins.onError(new IllegalStateException("More produced than requested: " + j4));
                j4 = 0L;
            }
        } while (!atomicLong.compareAndSet(j3, j4));
        return j4;
    }
}
