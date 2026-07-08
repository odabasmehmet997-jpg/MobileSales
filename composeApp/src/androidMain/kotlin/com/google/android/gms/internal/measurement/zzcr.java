package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzcr extends zzdt {
    final Activity zza;
    final String zzb;
    final String zzc;
    final zzee zzd;

    
    zzcr(zzee zzee, Activity activity, String str, String str2) {
        super(zzee, true);
        this.zzd = zzee;
        this.zza = activity;
        this.zzb = str;
        this.zzc = str2;
    }

    
    public void zza() throws RemoteException {
        Preconditions.checkNotNull(this.zzd.zzj).setCurrentScreen(ObjectWrapper.wrap(this.zza), this.zzb, this.zzc, this.zzh);
    }
}
