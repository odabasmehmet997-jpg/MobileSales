package com.google.android.gms.maps.model;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public record BitmapDescriptor(IObjectWrapper zza) {
    public BitmapDescriptor(@NonNull final IObjectWrapper zza) {
        this.zza = Preconditions.checkNotNull(zza);
    }

    @Override
    @NonNull
    public IObjectWrapper zza() {
        return zza;
    }
}
