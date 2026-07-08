package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.zzao;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzj extends zzao {
    final GoogleMap.OnMapLoadedCallback zza;

    public void zzb() throws RemoteException {
        zza.onMapLoaded();
    }
}
