package com.google.android.gms.maps.model;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.maps.zzaq;
import com.google.android.gms.internal.maps.zzp;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzd extends zzaq {
    final FeatureLayer.StyleFactory zza;

    @Nullable
    public FeatureStyle zzb(final zzp zzp) {
        final Feature zza2 = Feature.zza(zzp);
        if (null == zza2) {
            return null;
        }
        return zza.getStyle(zza2);
    }
}
