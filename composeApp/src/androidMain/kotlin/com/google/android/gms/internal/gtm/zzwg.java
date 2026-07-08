package com.google.android.gms.internal.gtm;

import java.io.Closeable;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzwg implements Closeable {
    private static final ThreadLocal zza = new zzwf();
    private int zzb;

    public void close() {
        int i2 = this.zzb;
        if (0 < i2) {
            this.zzb = i2 - 1;
            return;
        }
        throw new AssertionError("Mismatched calls to RecursionDepth (possible error in core library)");
    }
}
