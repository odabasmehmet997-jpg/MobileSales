package io.reactivex.internal.operators.flowable;

import io.reactivex.BackpressureOverflowStrategy;



enum FlowableOnBackpressureBufferStrategy1 {
    ;
    static final int[] SwitchMapioreactivexBackpressureOverflowStrategy;

    static {
        int[] iArr = new int[BackpressureOverflowStrategy.values().length];
        SwitchMapioreactivexBackpressureOverflowStrategy = iArr;
        try {
            iArr[BackpressureOverflowStrategy.DROP_LATEST.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            SwitchMapioreactivexBackpressureOverflowStrategy[BackpressureOverflowStrategy.DROP_OLDEST.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
    }
}
