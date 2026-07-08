package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzct extends zzdt {
    final Bundle zza;
    final zzee zzb;

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(this.zzb.zzj).setConsent(this.zza, this.zzh);
    }
}
