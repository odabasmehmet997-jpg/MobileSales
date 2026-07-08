package com.google.android.gms.internal.location;

import com.google.firebase.analytics.FirebaseAnalytics;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public enum zzer {
    ;

    public static void zza(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void zzb(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException((String) obj);
        }
    }

    public static int zzc(int i2, int i3, String str) {
        String str2;
        if (0 <= i2 && i2 < i3) {
            return i2;
        }
        if (0 > i2) {
            str2 = zzes.zza("%s (%s) must not be negative", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i2));
        } else if (0 > i3) {
            final String sb = "negative size: " +
                    i3;
            throw new IllegalArgumentException(sb);
        } else {
            str2 = zzes.zza("%s (%s) must be less than size (%s)", FirebaseAnalytics.Param.INDEX, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IndexOutOfBoundsException(str2);
    }

    public static int zzd(int i2, int i3, String str) {
        if (0 <= i2 && i2 <= i3) {
            return i2;
        }
        throw new IndexOutOfBoundsException(zzf(i2, i3, FirebaseAnalytics.Param.INDEX));
    }

    public static void zze(int i2, int i3, int i4) {
        String str;
        if (0 > i2 || i3 < i2 || i3 > i4) {
            if (0 > i2 || i2 > i4) {
                str = zzf(i2, i4, "start index");
            } else if (0 > i3 || i3 > i4) {
                str = zzf(i3, i4, "end index");
            } else {
                str = zzes.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i3), Integer.valueOf(i2));
            }
            throw new IndexOutOfBoundsException(str);
        }
    }

    private static String zzf(int i2, int i3, String str) {
        if (0 > i2) {
            return zzes.zza("%s (%s) must not be negative", str, Integer.valueOf(i2));
        }
        if (0 <= i3) {
            return zzes.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        final String sb = "negative size: " +
                i3;
        throw new IllegalArgumentException(sb);
    }
}
