package com.google.android.gms.internal.measurement;

import androidx.exifinterface.media.ExifInterface;
import com.fasterxml.jackson.databind.node.DecimalNodeExternalSyntheticBackportWithForwarding0;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzah implements zzap {
    private final Double zza;

    public zzah(final Double d2) {
        if (null == d2) {
            zza = Double.valueOf(Double.NaN);
        } else {
            zza = d2;
        }
    }

    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzah)) {
            return false;
        }
        return zza.equals(((zzah) obj).zza);
    }

    public int hashCode() {
        return zza.hashCode();
    }

    public String toString() {
        return this.zzi();
    }

    public zzap zzbI(final String str, final zzg zzg, final List list) {
        if ("toString".equals(str)) {
            return new zzat(this.zzi());
        }
        throw new IllegalArgumentException(String.format("%s.%s is not a function.", this.zzi(), str));
    }

    public zzap zzd() {
        return new zzah(zza);
    }

    public Boolean zzg() {
        boolean z = !Double.isNaN(zza.doubleValue()) && 0.0d != this.zza.doubleValue();
        return Boolean.valueOf(z);
    }

    public Double zzh(String zzd, int i, List list) {
        return zza;
    }

    public String zzi() {
        final int scale;
        if (Double.isNaN(zza.doubleValue())) {
            return "NaN";
        }
        if (Double.isInfinite(zza.doubleValue())) {
            return 0.0d < this.zza.doubleValue() ? "Infinity" : "-Infinity";
        }
        final BigDecimal m = DecimalNodeExternalSyntheticBackportWithForwarding0.m(BigDecimal.valueOf(zza.doubleValue()));
        final DecimalFormat decimalFormat = new DecimalFormat("0E0");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        if (0 < m.scale()) {
            scale = m.precision();
        } else {
            scale = m.scale();
        }
        decimalFormat.setMinimumFractionDigits(scale - 1);
        final String format = decimalFormat.format(m);
        final int indexOf = format.indexOf(ExifInterface.LONGITUDE_EAST);
        if (0 >= indexOf) {
            return format;
        }
        final int parseInt = Integer.parseInt(format.substring(indexOf + 1));
        if ((0 <= parseInt || -7 >= parseInt) && (0 > parseInt || 21 <= parseInt)) {
            return format.replace("E-", "e-").replace(ExifInterface.LONGITUDE_EAST, "e+");
        }
        return m.toPlainString();
    }

    public Iterator zzl() {
        return null;
    }
}
