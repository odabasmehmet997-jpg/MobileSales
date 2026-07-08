package io.reactivex.internal.operators.parallel;

import java.util.concurrent.atomic.AtomicInteger;



final class ParallelReduceFullSlotPair<T> extends AtomicInteger {
    private static final long serialVersionUID = 473971317683868662L;
    T first;
    final AtomicInteger releaseIndex = new AtomicInteger();
    T second;

    ParallelReduceFullSlotPair() {
    }

    int tryAcquireSlot() {
        int i2;
        do {
            i2 = get();
            if (2 <= i2) {
                return -1;
            }
        } while (!compareAndSet(i2, i2 + 1));
        return i2;
    }

    boolean releaseSlot() {
        return 2 == releaseIndex.incrementAndGet ();
    }
}
