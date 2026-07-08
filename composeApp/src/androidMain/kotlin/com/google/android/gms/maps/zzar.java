package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.zzbs;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzar extends zzbs {
    final OnStreetViewPanoramaReadyCallback zza;

    zzar(final zzas zzas, final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        zza = onStreetViewPanoramaReadyCallback;
    }

    public void zzb(final IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) throws RemoteException {
        zza.onStreetViewPanoramaReady(new StreetViewPanorama(iStreetViewPanoramaDelegate));
    }
}
