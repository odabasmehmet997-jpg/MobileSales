package com.google.android.gms.location;

import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.annotation.GuardedBy;
import androidx.core.location.LocationCompat;
import com.google.android.gms.internal.location.zzeo;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-location@@21.2.0 */
public enum zzak {
    ;
    public static final int r8clinit = 0;
    private static final DecimalFormat zzb;
    private static final DecimalFormat zzc;
    @GuardedBy("sharedStringBuilder")
    private static final StringBuilder zzd = new StringBuilder();

    static {
        final Locale locale = Locale.ROOT;
        zzb = new DecimalFormat(".000000", DecimalFormatSymbols.getInstance(locale));
        final DecimalFormat decimalFormat = new DecimalFormat(".##", DecimalFormatSymbols.getInstance(locale));
        zzc = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
    }

    public static StringBuilder zza(final Location location, final StringBuilder sb) {
        sb.ensureCapacity(100);
        String str = null;
        if (null == location) {
            sb.append((String) null);
            return sb;
        }
        sb.append("{");
        sb.append(location.getProvider());
        sb.append(", ");
        if (LocationCompat.isMock(location)) {
            sb.append("mock, ");
        }
        final DecimalFormat decimalFormat = zzak.zzb;
        sb.append(decimalFormat.format(location.getLatitude()));
        sb.append(",");
        sb.append(decimalFormat.format(location.getLongitude()));
        if (location.hasAccuracy()) {
            sb.append("±");
            sb.append(zzak.zzc.format(location.getAccuracy()));
            sb.append("m");
        }
        if (location.hasAltitude()) {
            sb.append(", alt=");
            final DecimalFormat decimalFormat2 = zzak.zzc;
            sb.append(decimalFormat2.format(location.getAltitude()));
            if (LocationCompat.hasVerticalAccuracy(location)) {
                sb.append("±");
                sb.append(decimalFormat2.format(LocationCompat.getVerticalAccuracyMeters(location)));
            }
            sb.append("m");
        }
        if (location.hasSpeed()) {
            sb.append(", spd=");
            final DecimalFormat decimalFormat3 = zzak.zzc;
            sb.append(decimalFormat3.format(location.getSpeed()));
            if (LocationCompat.hasSpeedAccuracy(location)) {
                sb.append("±");
                sb.append(decimalFormat3.format(LocationCompat.getSpeedAccuracyMetersPerSecond(location)));
            }
            sb.append("m/s");
        }
        if (location.hasBearing()) {
            sb.append(", brg=");
            final DecimalFormat decimalFormat4 = zzak.zzc;
            sb.append(decimalFormat4.format(location.getBearing()));
            if (LocationCompat.hasBearingAccuracy(location)) {
                sb.append("±");
                sb.append(decimalFormat4.format(LocationCompat.getBearingAccuracyDegrees(location)));
            }
            sb.append("°");
        }
        final Bundle extras = location.getExtras();
        final String string = null != extras ? extras.getString("floorLabel") : null;
        if (null != string) {
            sb.append(", fl=");
            sb.append(string);
        }
        final Bundle extras2 = location.getExtras();
        if (null != extras2) {
            str = extras2.getString("levelId");
        }
        if (null != str) {
            sb.append(", lv=");
            sb.append(str);
        }
        final long currentTimeMillis = System.currentTimeMillis() - SystemClock.elapsedRealtime();
        sb.append(", ert=");
        sb.append(zzeo.zza(LocationCompat.getElapsedRealtimeMillis(location) + currentTimeMillis));
        sb.append('}');
        return sb;
    }
}
