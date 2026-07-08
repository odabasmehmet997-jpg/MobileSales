package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.zzbe;
import com.google.android.gms.maps.model.PointOfInterest;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzr extends zzbe {
    final GoogleMap.OnPoiClickListener zza;

    public void zzb(final PointOfInterest pointOfInterest) throws RemoteException {
        zza.onPoiClick(pointOfInterest);
    }
}
