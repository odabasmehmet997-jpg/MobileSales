package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.maps.internal.zzaw;
import com.google.android.gms.maps.model.Marker;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzb extends zzaw {
    final GoogleMap.OnMarkerDragListener zza;

    zzb(final GoogleMap googleMap, final GoogleMap.OnMarkerDragListener onMarkerDragListener) {
        zza = onMarkerDragListener;
    }

    public void zzb(final zzah zzah) {
        zza.onMarkerDrag(new Marker(zzah));
    }

    public void zzc(final zzah zzah) {
        zza.onMarkerDragEnd(new Marker(zzah));
    }

    public void zzd(final zzah zzah) {
        zza.onMarkerDragStart(new Marker(zzah));
    }
}
