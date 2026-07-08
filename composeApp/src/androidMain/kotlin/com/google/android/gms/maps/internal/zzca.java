package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class zzca extends zza implements IUiSettingsDelegate {
    zzca(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.maps.internal.IUiSettingsDelegate");
    }

    public void setCompassEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        final int i2 = zzc.r8clinit;
        zza.writeInt(z ? 1 : 0);
        zzc(2, zza);
    }

    public void setZoomControlsEnabled(boolean z) throws RemoteException {
        Parcel zza = zza();
        final int i2 = zzc.r8clinit;
        zza.writeInt(z ? 1 : 0);
        zzc(1, zza);
    }
}
