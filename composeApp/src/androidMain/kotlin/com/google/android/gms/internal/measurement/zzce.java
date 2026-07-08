package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public abstract class zzce extends zzbn implements zzcf {
    protected zzce() {
        super("com.google.android.gms.measurement.api.internal.IBundleReceiver");
    }

    
    public final boolean zza(final int i2, final Parcel parcel, final Parcel parcel2, final int i3) throws RemoteException {
        if (1 != i2) {
            return false;
        }
        this.zzd((Bundle) zzbo.zza(parcel, Bundle.CREATOR));
        parcel2.writeNoException();
        return true;
    }
}
