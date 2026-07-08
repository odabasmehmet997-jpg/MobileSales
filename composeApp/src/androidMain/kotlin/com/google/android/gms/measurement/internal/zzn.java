package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzci;

/**
 * @param zzb synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-sdk@@20.1.1 */
record zzn(AppMeasurementDynamiteService zzb, zzci zza) implements zzgt {

    public void interceptEvent(final String str, final String str2, final Bundle bundle, final long j2) {
        try {
            zza.zze(str, str2, bundle, j2);
        } catch (final RemoteException e2) {
            final zzft zzft = zzb.zza;
            if (null != zzft) {
                zzft.zzay().zzk().zzb("Event interceptor threw exception", e2);
            }
        }
    }
}
