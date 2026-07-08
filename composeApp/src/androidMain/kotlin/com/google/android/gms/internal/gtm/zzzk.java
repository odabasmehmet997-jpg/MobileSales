package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzzk implements zzacj {
    static final zzacj zza = new zzzk();

    private zzzk() {
    }

    public boolean zza(final int i2) {
        zzzl zzzl;
        final zzzl zzzl2 = com.google.android.gms.internal.gtm.zzzl.EDITION_UNKNOWN;
        if (0 == i2) {
            zzzl = com.google.android.gms.internal.gtm.zzzl.EDITION_UNKNOWN;
        } else if (1 == i2) {
            zzzl = com.google.android.gms.internal.gtm.zzzl.EDITION_1_TEST_ONLY;
        } else if (2 == i2) {
            zzzl = com.google.android.gms.internal.gtm.zzzl.EDITION_2_TEST_ONLY;
        } else if (900 == i2) {
            zzzl = com.google.android.gms.internal.gtm.zzzl.EDITION_LEGACY;
        } else if (Integer.MAX_VALUE != i2) {
            switch (i2) {
                case 998:
                    zzzl = com.google.android.gms.internal.gtm.zzzl.EDITION_PROTO2;
                    break;
                case 999:
                    zzzl = com.google.android.gms.internal.gtm.zzzl.EDITION_PROTO3;
                    break;
                case 1000:
                    zzzl = com.google.android.gms.internal.gtm.zzzl.EDITION_2023;
                    break;
                case 1001:
                    zzzl = com.google.android.gms.internal.gtm.zzzl.EDITION_2024;
                    break;
                default:
                    switch (i2) {
                        case 99997:
                            zzzl = com.google.android.gms.internal.gtm.zzzl.EDITION_99997_TEST_ONLY;
                            break;
                        case 99998:
                            zzzl = com.google.android.gms.internal.gtm.zzzl.EDITION_99998_TEST_ONLY;
                            break;
                        case 99999:
                            zzzl = com.google.android.gms.internal.gtm.zzzl.EDITION_99999_TEST_ONLY;
                            break;
                        default:
                            zzzl = null;
                            break;
                    }
            }
        } else {
            zzzl = com.google.android.gms.internal.gtm.zzzl.EDITION_MAX;
        }
        return null != zzzl;
    }
}
