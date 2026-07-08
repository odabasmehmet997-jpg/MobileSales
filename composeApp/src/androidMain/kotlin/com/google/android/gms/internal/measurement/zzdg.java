package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzdg extends zzdt {
    final String zza;
    final Object zzb;
    final zzee zzc;

    
    zzdg(zzee zzee, boolean z, int i2, String str, Object obj, Object obj2, Object obj3) {
        super(zzee, false);
        this.zzc = zzee;
        this.zza = str;
        this.zzb = obj;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(this.zzc.zzj).logHealthData(5, this.zza, ObjectWrapper.wrap(this.zzb), ObjectWrapper.wrap(null), ObjectWrapper.wrap(null));
    }
}
