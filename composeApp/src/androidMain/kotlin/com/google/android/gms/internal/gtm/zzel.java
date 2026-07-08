package com.google.android.gms.internal.gtm;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzel extends zzar {
    zzel(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.analytics.internal.IAnalyticsService");
    }

    public void zze() throws RemoteException {
        zzl(2, zza());
    }

    public void zzf(Map map, long j2, String str, List list) throws RemoteException {
        Parcel zza = zza();
        zza.writeMap(map);
        zza.writeLong(j2);
        zza.writeString(str);
        zza.writeTypedList(list);
        zzl(1, zza);
    }
}
