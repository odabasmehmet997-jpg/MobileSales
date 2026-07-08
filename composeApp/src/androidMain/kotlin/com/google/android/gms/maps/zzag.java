package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzas;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzag extends zzas {
    final OnMapReadyCallback zza;

    zzag(final zzah zzah, final OnMapReadyCallback onMapReadyCallback) {
        zza = onMapReadyCallback;
    }

    public void zzb(final IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
        zza.onMapReady(new GoogleMap(iGoogleMapDelegate));
    }
}
