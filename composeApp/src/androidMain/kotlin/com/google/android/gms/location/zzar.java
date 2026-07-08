package com.google.android.gms.location;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public enum zzar {
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
        Preconditions.checkArgument(z, "throttle behavior %d must be a ThrottleBehavior.THROTTLE_* constant", Integer.valueOf(i2));
        return i2;
    }

    public static String zzb(final int i2) {
        if (0 == i2) {
            return "THROTTLE_BACKGROUND";
        }
        if (1 == i2) {
            return "THROTTLE_ALWAYS";
        }
        if (2 == i2) {
            return "THROTTLE_NEVER";
        }
        throw new IllegalArgumentException();
    }
}
