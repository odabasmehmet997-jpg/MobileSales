package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzah;
import com.google.android.gms.maps.internal.zzag;
import com.google.android.gms.maps.model.Marker;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzd extends zzag {
    final GoogleMap.OnInfoWindowLongClickListener zza;

    public void zzb(final zzah zzah) {
        zza.onInfoWindowLongClick(new Marker(zzah));
    }
}
