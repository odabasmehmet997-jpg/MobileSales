package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzau implements zzap {
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        return obj instanceof zzau;
    }

    public zzap zzbI(final String str, final zzg zzg, final List list) {
        throw new IllegalStateException(String.format("Undefined has no function %s", str));
    }

    public zzap zzd() {
        return zzf;
    }

    public Boolean zzg() {
        return Boolean.FALSE;
    }

    public Double zzh(String zzd, int i, List list) {
        return Double.valueOf(Double.NaN);
    }

    public String zzi() {
        return "undefined";
    }

    public Iterator zzl() {
        return null;
    }
}
