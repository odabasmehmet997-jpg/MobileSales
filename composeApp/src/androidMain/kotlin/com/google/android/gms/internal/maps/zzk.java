package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public abstract class zzk extends zzb implements zzl {
    public static zzl zzb(final IBinder iBinder) {
        if (null == iBinder) {
            return null;
        }
        final IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.model.internal.ICircleDelegate");
        return queryLocalInterface instanceof zzl ? (zzl) queryLocalInterface : new zzj(iBinder);
    }
}
