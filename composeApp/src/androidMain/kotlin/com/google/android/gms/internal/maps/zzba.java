package com.google.android.gms.internal.maps;

import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
public enum zzba {
    ;

    public static int zza(final int i2, final int i3, final String str) {
        final String str2;
        if (0 <= i2 && i2 < i3) {
            return i2;
        }
        if (0 > i2) {
            str2 = zzbb.zza("%s (%s) must not be negative", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i2));
        } else if (0 > i3) {
            throw new IllegalArgumentException("negative size: " + i3);
        } else {
            str2 = zzbb.zza("%s (%s) must be less than size (%s)", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IndexOutOfBoundsException(str2);
    }

    public static int zzb(final int i2, final int i3, final String str) {
        if (0 <= i2 && i2 <= i3) {
            return i2;
        }
        throw new IndexOutOfBoundsException(zzba.zzd(i2, i3, FirebaseAnalytics.Param.INDEX));
    }

    public static void zzc(final int i2, final int i3, final int i4) {
        final String str;
        if (0 > i2 || i3 < i2 || i3 > i4) {
            if (0 > i2 || i2 > i4) {
                str = zzba.zzd(i2, i4, "start index");
            } else if (0 > i3 || i3 > i4) {
                str = zzba.zzd(i3, i4, "end index");
            } else {
                str = zzbb.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i3), Integer.valueOf(i2));
            }
            throw new IndexOutOfBoundsException(str);
        }
    }

    private static String zzd(final int i2, final int i3, final String str) {
        if (0 > i2) {
            return zzbb.zza("%s (%s) must not be negative", str, Integer.valueOf(i2));
        }
        if (0 <= i3) {
            return zzbb.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IllegalArgumentException("negative size: " + i3);
    }
}
