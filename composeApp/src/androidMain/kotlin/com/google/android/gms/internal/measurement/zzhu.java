package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
public abstract class zzhu {
    public static final int r8clinit = 0;
    private static final Object zzd = new Object();
    private static volatile zzhs zze;
    private static final AtomicReference zzg = new AtomicReference();
    private static final zzhw zzh = new zzhw(zzhl.zza, null);
    private static final AtomicInteger zzi = new AtomicInteger();
    final zzhr zza;
    final String zzb;
    private final Object zzj;
    private final int zzk = -1;
    private volatile Object zzl;
    private final boolean zzm;

    zzhu(zzhr zzhr, String str, Object obj, boolean z, zzht zzht) {
        if (null != zzhr.zzb) {
            this.zza = zzhr;
            this.zzb = str;
            this.zzj = obj;
            this.zzm = true;
            return;
        }
        throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
    }

    static void zzd() {
        zzi.incrementAndGet();
    }

    public static void zze(android.content.Context r3) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhu.zze(android.content.Context):void");
    }

    
    public abstract Object zza(Object obj);

    public final java.lang.Object zzb() {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhu.zzb():java.lang.Object");
    }

    public final String zzc() {
        String str = this.zza.zzd;
        return this.zzb;
    }
}
