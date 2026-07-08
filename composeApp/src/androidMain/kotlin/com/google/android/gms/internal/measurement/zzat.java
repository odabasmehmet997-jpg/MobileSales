package com.google.android.gms.internal.measurement;

import com.fasterxml.jackson.core.JsonFactory;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzat implements Iterable, zzap {
    
    public final String zza;

    public zzat(final String str) {
        if (null != str) {
            zza = str;
            return;
        }
        throw new IllegalArgumentException("StringValue cannot be null.");
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzat)) {
            return false;
        }
        return zza.equals(((zzat) obj).zza);
    }

    public int hashCode() {
        return zza.hashCode();
    }

    public Iterator iterator() {
        return new zzas(this);
    }

    public String toString() {
        final String str = zza;
        String sb = JsonFactory.DEFAULT_QUOTE_CHAR +
                str +
                JsonFactory.DEFAULT_QUOTE_CHAR;
        return sb;
    }

    public com.google.android.gms.internal.measurement.zzap zzbI(final java.lang.String r22, final com.google.android.gms.internal.measurement.zzg r23, final java.util.List r24) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzat.zzbI(java.lang.String, com.google.android.gms.internal.measurement.zzg, java.util.List):com.google.android.gms.internal.measurement.zzap");
    }

    public zzap zzd() {
        return new zzat(zza);
    }

    public Boolean zzg() {
        return Boolean.valueOf(!zza.isEmpty());
    }

    public Double zzh(String zzd, int i, List list) {
        if (zza.isEmpty()) {
            return Double.valueOf(0.0d);
        }
        try {
            return Double.valueOf(zza);
        } catch (final NumberFormatException unused) {
            return Double.valueOf(Double.NaN);
        }
    }

    public String zzi() {
        return zza;
    }

    public Iterator zzl() {
        return new zzar(this);
    }
}
