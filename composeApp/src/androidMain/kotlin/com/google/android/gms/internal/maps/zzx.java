package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public abstract class zzx extends zzb implements zzy {
    public static zzy zzb(final IBinder iBinder) {
        if (null == iBinder) {
            return null;
        }
        final IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.IIndoorBuildingDelegate");
        return queryLocalInterface instanceof zzy ? (zzy) queryLocalInterface : new zzw(iBinder);
    }
}
