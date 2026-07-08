package com.google.android.gms.internal.gtm;

import android.os.Build;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzvk extends zzvd {
    static final boolean zza;
    static final boolean zzb;
    static final boolean zzc;
    private static final AtomicReference zzd = new AtomicReference();
    private static final AtomicLong zze = new AtomicLong();
    private static final ConcurrentLinkedQueue zzf = new ConcurrentLinkedQueue();

    static {
        String str = Build.FINGERPRINT;
        boolean z = false;
        zza = null == str || "robolectric".equals(str);
        String str2 = Build.HARDWARE;
        zzb = "goldfish".equals(str2) || "ranchu".equals(str2);
        String str3 = Build.TYPE;
        if ("eng".equals(str3) || "userdebug".equals(str3)) {
            z = true;
        }
        zzc = z;
    }
}
