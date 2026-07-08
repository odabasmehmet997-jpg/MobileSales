package com.google.android.gms.internal.location;

import androidx.annotation.GuardedBy;
import androidx.core.location.LocationRequestCompat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public enum zzeo {
    ;
    private static final SimpleDateFormat zza;
    private static final SimpleDateFormat zzb;
    @GuardedBy("sharedStringBuilder")
    private static final StringBuilder zzc = new StringBuilder(33);

    static {
        Locale locale = Locale.ROOT;
        zza = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", locale);
        zzb = new SimpleDateFormat("MM-dd HH:mm:ss", locale);
    }

    public static String zza(long j2) {
        if (0 <= j2) {
            return zza.format(new Date(j2));
        }
        return Long.toString(j2);
    }

    public static String zzb(long j2) {
        String sb;
        StringBuilder sb2 = zzc;
        synchronized (sb2) {
            sb2.setLength(0);
            zzc(j2, sb2);
            sb = sb2.toString();
        }
        return sb;
    }

    public static StringBuilder zzc(long j2, StringBuilder sb) {
        int i2 = (0 < j2 ? 1 : (0 == j2 ? 0 : -1));
        if (0 == i2) {
            sb.append("0s");
            return sb;
        }
        sb.ensureCapacity(sb.length() + 27);
        boolean z = false;
        if (0 > i2) {
            sb.append("-");
            if (Long.MIN_VALUE != j2) {
                j2 = -j2;
            } else {
                j2 = LocationRequestCompat.PASSIVE_INTERVAL;
                z = true;
            }
        }
        if (86400000 <= j2) {
            sb.append(j2 / 86400000);
            sb.append("d");
            j2 %= 86400000;
        }
        if (z) {
            j2 = 25975808;
        }
        if (3600000 <= j2) {
            sb.append(j2 / 3600000);
            sb.append("h");
            j2 %= 3600000;
        }
        if (60000 <= j2) {
            sb.append(j2 / 60000);
            sb.append("m");
            j2 %= 60000;
        }
        if (1000 <= j2) {
            sb.append(j2 / 1000);
            sb.append("s");
            j2 %= 1000;
        }
        if (0 < j2) {
            sb.append(j2);
            sb.append("ms");
        }
        return sb;
    }
}
