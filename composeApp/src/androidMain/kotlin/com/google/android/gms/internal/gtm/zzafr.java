package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzafr implements zzaci {
    zzafr() {
    }

    public zzach zza(final int i2) {
        switch (i2) {
            case 0:
                return zzafs.DF_NONE;
            case 1:
                return zzafs.DF_HTTPHEADER;
            case 2:
                return zzafs.DF_COOKIE;
            case 3:
                return zzafs.DF_URL;
            case 4:
                return zzafs.DF_CGI_ARGS;
            case 5:
                return zzafs.DF_HOST_ORDER;
            case 6:
                return zzafs.DF_BYTE_SWAPPED;
            case 7:
                return zzafs.DF_LOGGING_ELEMENT_TYPE_ID;
            default:
                return null;
        }
    }
}
