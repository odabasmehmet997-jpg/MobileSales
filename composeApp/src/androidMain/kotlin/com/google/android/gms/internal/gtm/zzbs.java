package com.google.android.gms.internal.gtm;

import com.google.android.gms.common.GoogleApiAvailabilityLight;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public enum zzbs {
    ;
    public static final String zza;
    public static final String zzb;

    static {
        final String replaceAll = String.valueOf(GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE / 1000).replaceAll("(\\d+)(\\d)(\\d\\d)", "1.2.3");
        zza = replaceAll;
        zzb = "ma" + replaceAll;
    }
}
