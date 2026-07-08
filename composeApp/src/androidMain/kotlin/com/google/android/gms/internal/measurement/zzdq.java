package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzdq extends zzdt {
    final zzdv zza;
    final zzee zzb;

    
    zzdq(zzee zzee, zzdv zzdv) {
        super(zzee, true);
        this.zzb = zzee;
        this.zza = zzdv;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(this.zzb.zzj).unregisterOnMeasurementEventListener(this.zza);
    }
}
