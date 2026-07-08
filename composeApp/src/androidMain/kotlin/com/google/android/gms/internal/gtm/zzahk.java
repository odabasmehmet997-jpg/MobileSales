package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzahk implements zzaci {
    zzahk() {
    }

    public zzach zza(final int i2) {
        if (0 == i2) {
            return zzahl.TOMBSTONE_UNSPECIFIED;
        }
        if (1 == i2) {
            return zzahl.TOMBSTONE_RETAIN;
        }
        if (2 != i2) {
            return null;
        }
        return zzahl.TOMBSTONE_DROP;
    }
}
