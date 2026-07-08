package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public enum zzan {
    ;

    public static int zza(int i2) {
        boolean z = true;
        if (!(100 == i2 || 102 == i2 || 104 == i2)) {
            if (105 == i2) {
                i2 = 105;
            } else {
                z = false;
            }
        }
        Preconditions.checkArgument(z, "priority %d must be a Priority.PRIORITY_* constant", Integer.valueOf(i2));
        return i2;
    }

    public static String zzb(final int i2) {
        if (100 == i2) {
            return "HIGH_ACCURACY";
        }
        if (102 == i2) {
            return "BALANCED_POWER_ACCURACY";
        }
        if (104 == i2) {
            return "LOW_POWER";
        }
        if (105 == i2) {
            return "PASSIVE";
        }
        throw new IllegalArgumentException();
    }
}
