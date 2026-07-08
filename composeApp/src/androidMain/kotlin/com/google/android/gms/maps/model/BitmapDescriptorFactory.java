package com.google.android.gms.maps.model;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zzi;

public class BitmapDescriptorFactory {
    private static zzi zza;
    public static void zza(final zzi zzi) {
        BitmapDescriptorFactory.zza = Preconditions.checkNotNull(zzi, "delegate must not be null");
    }
}
