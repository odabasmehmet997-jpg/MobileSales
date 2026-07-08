package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzcv extends zzdt {
    final zzee zza;

    
    zzcv(zzee zzee) {
        super(zzee, true);
        this.zza = zzee;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(this.zza.zzj).resetAnalyticsData(this.zzh);
    }
}
