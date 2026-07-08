package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.MethodInvocation;

final class zace {
    final MethodInvocation zaa;
    final int zab;
    final long zac;
    final int zad;

    zace(MethodInvocation methodInvocation, int i2, long j2, int i3) {
        this.zaa = methodInvocation;
        this.zab = i2;
        this.zac = j2;
        this.zad = i3;
    }

    @Override
    public String toString() {
        return "MethodInvocation: " + this.zaa + ", Priority: " + this.zab + ", Delay: " + this.zac + ", RetryCount: " + this.zad;
    }
}
