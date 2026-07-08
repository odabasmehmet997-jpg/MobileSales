package com.google.android.gms.maps.model;

import com.google.android.gms.internal.maps.zzai;
import com.google.android.gms.internal.maps.zzm;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zze extends zzai {
    final FeatureLayer.OnFeatureClickListener zza;

    public void zzb(final zzm zzm) {
        zza.onFeatureClick(new FeatureClickEvent(zzm));
    }
}
