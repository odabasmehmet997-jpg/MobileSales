package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzdb extends zzdt {
    final zzbz zza;
    final zzee zzb;

    
    zzdb(zzee zzee, zzbz zzbz) {
        super(zzee, true);
        this.zzb = zzee;
        this.zza = zzbz;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(this.zzb.zzj).getCachedAppInstanceId(this.zza);
    }

    
    public void zzb() {
        this.zza.zzd(null);
    }
}
