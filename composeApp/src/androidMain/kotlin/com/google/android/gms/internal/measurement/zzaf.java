package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzaf implements zzap {
    private final boolean zza;

    public zzaf(final Boolean bool) {
        zza = null != bool && bool.booleanValue();
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof zzaf) && zza == ((zzaf) obj).zza;
    }

    public int hashCode() {
        return Boolean.valueOf(zza).hashCode();
    }

    public String toString() {
        return String.valueOf(zza);
    }

    public zzap zzbI(final String str, final zzg zzg, final List list) {
        if ("toString".equals(str)) {
            return new zzat(Boolean.toString(zza));
        }
        throw new IllegalArgumentException(String.format("%s.%s is not a function.", zza, str));
    }

    public zzap zzd() {
        return new zzaf(Boolean.valueOf(zza));
    }

    public Boolean zzg() {
        return Boolean.valueOf(zza);
    }

    public Double zzh(String zzd, int i, List list) {
        return Double.valueOf(!zza ? 0.0d : 1.0d);
    }

    public String zzi() {
        return Boolean.toString(zza);
    }

    public Iterator zzl() {
        return null;
    }
}
