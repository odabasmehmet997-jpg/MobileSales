package com.google.android.gms.maps;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public final class CameraUpdate {
    private final IObjectWrapper zza;

    CameraUpdate(final IObjectWrapper iObjectWrapper) {
        zza = Preconditions.checkNotNull(iObjectWrapper);
    }

    @NonNull
    public IObjectWrapper zza() {
        return zza;
    }
}
