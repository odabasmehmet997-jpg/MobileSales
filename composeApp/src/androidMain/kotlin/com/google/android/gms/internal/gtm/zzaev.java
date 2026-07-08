package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzaev extends IllegalArgumentException {
    zzaev(final int i2, final int i3) {
        super("Unpaired surrogate at index " + i2 + " of " + i3);
    }
}
