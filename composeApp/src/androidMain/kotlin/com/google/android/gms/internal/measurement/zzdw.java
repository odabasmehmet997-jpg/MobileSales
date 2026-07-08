package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@20.1.1 */
final class zzdw extends zzdt {
    final Bundle zza;
    final Activity zzb;
    final zzed zzc;

    
    zzdw(final zzed zzed, final Bundle bundle, final Activity activity) {
        super(zzed.zza(), true);
        zzc = zzed;
        zza = bundle;
        zzb = activity;
    }

    
    public void zza() throws RemoteException {
        final Bundle bundle;
        if (null != this.zza) {
            bundle = new Bundle();
            if (zza.containsKey("com.google.app_measurement.screen_service")) {
                final Object obj = zza.get("com.google.app_measurement.screen_service");
                if (obj instanceof Bundle) {
                    bundle.putBundle("com.google.app_measurement.screen_service", (Bundle) obj);
                }
            }
        } else {
            bundle = null;
        }
        Preconditions.checkNotNull(zzc.zza().zzj).onActivityCreated(ObjectWrapper.wrap(zzb), bundle, zzi);
    }
}
