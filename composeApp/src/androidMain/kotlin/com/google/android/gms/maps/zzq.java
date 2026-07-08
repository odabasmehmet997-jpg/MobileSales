package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.maps.internal.zzbv;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzq extends zzbv {
    final GoogleMap.SnapshotReadyCallback zza;

    public void zzb(final Bitmap bitmap) throws RemoteException {
        zza.onSnapshotReady(bitmap);
    }

    public void zzc(final IObjectWrapper iObjectWrapper) throws RemoteException {
        zza.onSnapshotReady(ObjectWrapper.unwrap(iObjectWrapper));
    }
}
