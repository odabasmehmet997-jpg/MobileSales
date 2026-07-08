package com.google.android.gms.internal.gtm;

import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public enum zztd {
    ;

    public static int zza(int i2, int i3, String str) {
        String str2;
        if (0 <= i2 && i2 < i3) {
            return i2;
        }
        if (0 > i2) {
            str2 = zzte.zza("%s (%s) must not be negative", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i2));
        } else if (0 > i3) {
            throw new IllegalArgumentException("negative size: " + i3);
        } else {
            str2 = zzte.zza("%s (%s) must be less than size (%s)", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IndexOutOfBoundsException(str2);
    }

    public static int zzb(int i2, int i3, String str) {
        if (0 <= i2 && i2 <= i3) {
            return i2;
        }
        throw new IndexOutOfBoundsException(zzd(i2, i3, FirebaseAnalytics.Param.INDEX));
    }

    public static void zzc(int i2, int i3, int i4) {
        String str;
        if (0 > i2 || i3 < i2 || i3 > i4) {
            if (0 > i2 || i2 > i4) {
                str = zzd(i2, i4, "start index");
            } else if (0 > i3 || i3 > i4) {
                str = zzd(i3, i4, "end index");
            } else {
                str = zzte.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i3), Integer.valueOf(i2));
            }
            throw new IndexOutOfBoundsException(str);
        }
    }

    private static String zzd(int i2, int i3, String str) {
        if (0 > i2) {
            return zzte.zza("%s (%s) must not be negative", str, Integer.valueOf(i2));
        }
        if (0 <= i3) {
            return zzte.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IllegalArgumentException("negative size: " + i3);
    }
}
