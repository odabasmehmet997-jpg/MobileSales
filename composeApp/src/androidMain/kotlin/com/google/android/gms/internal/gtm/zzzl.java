package com.google.android.gms.internal.gtm;

import androidx.constraintlayout.core.motion.utils.TypedValues;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public enum zzzl implements zzach {
    EDITION_UNKNOWN(0),
    EDITION_LEGACY(TypedValues.Custom.TYPE_INT),
    EDITION_PROTO2(998),
    EDITION_PROTO3(999),
    EDITION_2023(1000),
    EDITION_2024(1001),
    EDITION_1_TEST_ONLY(1),
    EDITION_2_TEST_ONLY(2),
    EDITION_99997_TEST_ONLY(99997),
    EDITION_99998_TEST_ONLY(99998),
    EDITION_99999_TEST_ONLY(99999),
    EDITION_MAX(Integer.MAX_VALUE);
    
    private final int zzn;

    zzzl(final int i2) {
        zzn = i2;
    }

    public final String toString() {
        return Integer.toString(zzn);
    }

    public final int zza() {
        return zzn;
    }
}
