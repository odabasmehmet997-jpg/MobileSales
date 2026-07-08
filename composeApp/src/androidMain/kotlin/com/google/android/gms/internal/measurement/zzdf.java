package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzdf extends zzdt {
    final String zza;
    final String zzb;
    final boolean zzc;
    final zzbz zzd;
    final zzee zze;

    
    zzdf(zzee zzee, String str, String str2, boolean z, zzbz zzbz) {
        super(zzee, true);
        this.zze = zzee;
        this.zza = str;
        this.zzb = str2;
        this.zzc = z;
        this.zzd = zzbz;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(this.zze.zzj).getUserProperties(this.zza, this.zzb, this.zzc, this.zzd);
    }

    
    public void zzb() {
        this.zzd.zzd(null);
    }
}
