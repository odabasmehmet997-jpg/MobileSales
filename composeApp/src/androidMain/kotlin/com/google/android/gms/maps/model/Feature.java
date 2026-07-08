package com.google.android.gms.maps.model;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzp;

public abstract class Feature {
    private final zzp zza;
    Feature(final zzp zzp) {
        zza = zzp;
    }
    @Nullable
    static Feature zza(final zzp zzp) {
        Preconditions.checkNotNull(zzp);
        try {
            final int zzd = zzp.zzd();
            if (1 == zzd) {
                return new PlaceFeature(zzp);
            }
            if (2 == zzd) {
                return new DatasetFeature(zzp);
            }
            return null;
        } catch (final RemoteException e2) {
            throw new RuntimeRemoteException(e2);
        }
    }
}
