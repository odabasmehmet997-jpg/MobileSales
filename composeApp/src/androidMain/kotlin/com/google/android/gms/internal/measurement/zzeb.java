package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzeb extends zzdt {
    final Activity zza;
    final zzbz zzb;
    final zzed zzc;

    
    zzeb(zzed zzed, Activity activity, zzbz zzbz) {
        super(zzed.zza(), true);
        this.zzc = zzed;
        this.zza = activity;
        this.zzb = zzbz;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(this.zzc.zza().zzj).onActivitySaveInstanceState(ObjectWrapper.wrap(this.zza), this.zzb, this.zzi);
    }
}
