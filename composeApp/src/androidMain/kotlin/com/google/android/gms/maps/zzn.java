package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzl;
import com.google.android.gms.maps.internal.zzw;
import com.google.android.gms.maps.model.Circle;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzn extends zzw {
    final GoogleMap.OnCircleClickListener zza;

    public void zzb(final zzl zzl) {
        zza.onCircleClick(new Circle(zzl));
    }
}
