package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.zzay;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzh extends zzay {
    final GoogleMap.OnMyLocationButtonClickListener zza;

    zzh(final GoogleMap googleMap, final GoogleMap.OnMyLocationButtonClickListener onMyLocationButtonClickListener) {
        zza = onMyLocationButtonClickListener;
    }

    public boolean zzb() throws RemoteException {
        return zza.onMyLocationButtonClick();
    }
}
