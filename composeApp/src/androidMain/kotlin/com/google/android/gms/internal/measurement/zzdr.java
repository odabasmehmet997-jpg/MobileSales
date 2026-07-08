package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzdr extends zzdt {
    final Long zza;
    final String zzb;
    final String zzc;
    final Bundle zzd;
    final boolean zze;
    final boolean zzf;
    final zzee zzg;

    
    zzdr(zzee zzee, Long l, String str, String str2, Bundle bundle, boolean z, boolean z2) {
        super(zzee, true);
        this.zzg = zzee;
        this.zza = l;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = bundle;
        this.zze = z;
        this.zzf = z2;
    }

    
    public void zza() throws RemoteException {
        long longValue;
        Long l = this.zza;
        if (null == l) {
            longValue = this.zzh;
        } else {
            longValue = l.longValue();
        }
        Preconditions.checkNotNull(this.zzg.zzj).logEvent(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, longValue);
    }
}
