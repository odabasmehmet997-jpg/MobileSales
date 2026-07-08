package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzam;
import com.google.android.gms.maps.internal.zzbg;
import com.google.android.gms.maps.model.Polygon;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzo extends zzbg {
    final GoogleMap.OnPolygonClickListener zza;

    public void zzb(final zzam zzam) {
        zza.onPolygonClick(new Polygon(zzam));
    }
}
