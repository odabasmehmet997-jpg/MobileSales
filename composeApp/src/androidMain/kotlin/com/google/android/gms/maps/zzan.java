package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.zzbs;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzan extends zzbs {
    final OnStreetViewPanoramaReadyCallback zza;

    zzan(final zzao zzao, final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        zza = onStreetViewPanoramaReadyCallback;
    }

    public void zzb(final IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) throws RemoteException {
        zza.onStreetViewPanoramaReady(new StreetViewPanorama(iStreetViewPanoramaDelegate));
    }
}
