package com.google.android.gms.internal.ads_identifier;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-ads-identifier@@17.1.0 */
public final class zzd extends zza implements zzf {
    zzd(final IBinder iBinder) {
        super(iBinder, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
    }

    public String zzc() throws RemoteException {
        final Parcel zzb = this.zzb(1, this.zza());
        final String readString = zzb.readString();
        zzb.recycle();
        return readString;
    }

    public boolean zze(final boolean z) throws RemoteException {
        final Parcel zza = this.zza();
        zzc.zza(zza, true);
        final Parcel zzb = this.zzb(2, zza);
        final boolean zzb2 = zzc.zzb(zzb);
        zzb.recycle();
        return zzb2;
    }
}
