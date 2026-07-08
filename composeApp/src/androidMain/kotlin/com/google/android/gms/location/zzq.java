package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public enum zzq {
    ;

    public static int zza(int i2) {
        boolean z = true;
        if (!(0 == i2 || 1 == i2)) {
            if (2 == i2) {
                i2 = 2;
            } else {
                z = false;
            }
        }
        Preconditions.checkArgument(z, "granularity %d must be a Granularity.GRANULARITY_* constant", Integer.valueOf(i2));
        return i2;
    }

    public static String zzb(final int i2) {
        if (0 == i2) {
            return "GRANULARITY_PERMISSION_LEVEL";
        }
        if (1 == i2) {
            return "GRANULARITY_COARSE";
        }
        if (2 == i2) {
            return "GRANULARITY_FINE";
        }
        throw new IllegalArgumentException();
    }
}
