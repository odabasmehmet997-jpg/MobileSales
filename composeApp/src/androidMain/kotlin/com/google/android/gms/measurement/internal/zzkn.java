package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
final class zzkn implements Runnable {
    final String zza;
    final String zzb = "_err";
    final Bundle zzc;
    final zzko zzd;

    zzkn(final zzko zzko, final String str, final String str2, final Bundle bundle) {
        zzd = zzko;
        zza = str;
        zzc = bundle;
    }

    public void run() {
        zzd.zza().zzE(Preconditions.checkNotNull(zzd.zza().zzv().zzz(zza, zzb, zzc, "auto", zzd.zza().zzav().currentTimeMillis(), false, true)), zza);
    }
}
