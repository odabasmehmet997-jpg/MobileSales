package com.google.android.gms.location;

import com.google.android.gms.common.api.CommonStatusCodes;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public final class GeofenceStatusCodes extends CommonStatusCodes {
    private GeofenceStatusCodes() {
    }

    public static int zza(final int i2) {
        if (0 == i2) {
            return 0;
        }
        if (1000 > i2 || 1006 <= i2) {
            return 13;
        }
        return i2;
    }
}
