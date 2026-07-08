package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzgz extends ContentObserver {
    final zzha zza;

    
    zzgz(zzha zzha, Handler handler) {
        super(null);
        this.zza = zzha;
    }

    public void onChange(boolean z) {
        this.zza.zzf();
    }
}
