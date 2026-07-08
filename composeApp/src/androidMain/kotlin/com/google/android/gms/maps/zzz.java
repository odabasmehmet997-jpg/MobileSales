package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzam;
import com.google.android.gms.maps.model.LatLng;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzz extends zzam {
    final GoogleMap.OnMapClickListener zza;

    zzz(final GoogleMap googleMap, final GoogleMap.OnMapClickListener onMapClickListener) {
        zza = onMapClickListener;
    }

    public void zzb(final LatLng latLng) {
        zza.onMapClick(latLng);
    }
}
