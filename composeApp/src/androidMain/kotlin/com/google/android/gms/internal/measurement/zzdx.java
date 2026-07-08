package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzdx extends zzdt {
    final Activity zza;
    final zzed zzb;

    
    zzdx(final zzed zzed, final Activity activity) {
        super(zzed.zza(), true);
        zzb = zzed;
        zza = activity;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(zzb.zza().zzj).onActivityStarted(ObjectWrapper.wrap(zza), zzi);
    }
}
