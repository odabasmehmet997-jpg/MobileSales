package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.maps.internal.zzau;
import com.google.android.gms.maps.model.Marker;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zza extends zzau {
    final GoogleMap.OnMarkerClickListener zza;

    public boolean zzb(final zzah zzah) {
        return zza.onMarkerClick(new Marker(zzah));
    }
}
