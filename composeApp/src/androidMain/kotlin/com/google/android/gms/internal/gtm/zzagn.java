package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzagn implements zzaci {
    zzagn() {
    }

    public zzach zza(final int i2) {
        if (0 == i2) {
            return zzago.UNKNOWN;
        }
        if (1 == i2) {
            return zzago.SUCCESS;
        }
        if (2 == i2) {
            return zzago.FAILURE;
        }
        if (3 != i2) {
            return null;
        }
        return zzago.CANCEL;
    }
}
