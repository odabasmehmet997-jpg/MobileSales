package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzcs extends zzdt {
    final Boolean zza;
    final zzee zzb;

    
    zzcs(zzee zzee, Boolean bool) {
        super(zzee, true);
        this.zzb = zzee;
        this.zza = bool;
    }

    
    public void zza() throws RemoteException {
        if (null != zza) {
            Preconditions.checkNotNull(this.zzb.zzj).setMeasurementEnabled(this.zza.booleanValue(), this.zzh);
        } else {
            Preconditions.checkNotNull(this.zzb.zzj).clearMeasurementEnabled(this.zzh);
        }
    }
}
