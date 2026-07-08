package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzap;
import com.google.android.gms.maps.internal.zzbi;
import com.google.android.gms.maps.model.Polyline;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzp extends zzbi {
    final GoogleMap.OnPolylineClickListener zza;

    public void zzb(final zzap zzap) {
        zza.onPolylineClick(new Polyline(zzap));
    }
}
