package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzdh extends zzdt {
    final Bundle zza;
    final zzbz zzb;
    final zzee zzc;

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(this.zzc.zzj).performAction(this.zza, this.zzb, this.zzh);
    }

    
    public void zzb() {
        this.zzb.zzd(null);
    }
}
