package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzdo extends zzdt {
    final zzdu zza;
    final zzee zzb;

    
    zzdo(zzee zzee, zzdu zzdu) {
        super(zzee, true);
        this.zzb = zzee;
        this.zza = zzdu;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(this.zzb.zzj).setEventInterceptor(this.zza);
    }
}
