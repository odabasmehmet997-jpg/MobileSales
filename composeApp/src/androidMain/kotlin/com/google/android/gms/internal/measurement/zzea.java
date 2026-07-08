package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzea extends zzdt {
    final Activity zza;
    final zzed zzb;

    
    zzea(zzed zzed, Activity activity) {
        super(zzed.zza(), true);
        this.zzb = zzed;
        this.zza = activity;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(this.zzb.zza().zzj).onActivityStopped(ObjectWrapper.wrap(this.zza), this.zzi);
    }
}
