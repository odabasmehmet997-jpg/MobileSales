package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzaq implements zzap {
    private final String zza;
    private final ArrayList zzb;

    public zzaq(final String str, final List list) {
        zza = str;
        final ArrayList arrayList = new ArrayList();
        zzb = arrayList;
        arrayList.addAll(list);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaq zzaq)) {
            return false;
        }
        final String str = zza;
        if (Objects.equals(str, zzaq.zza)) {
            return zzb.equals(zzaq.zzb);
        }
        return false;
    }

    public int hashCode() {
        final String str = zza;
        return ((null != str ? str.hashCode() : 0) * 31) + zzb.hashCode();
    }

    public String zzb() {
        return zza;
    }

    public zzap zzbI(final String str, final zzg zzg, final List list) {
        throw new IllegalStateException("Statement is not an evaluated entity");
    }

    public ArrayList zzc() {
        return zzb;
    }

    public zzap zzd() {
        return this;
    }

    public Boolean zzg() {
        throw new IllegalStateException("Statement cannot be cast as Boolean");
    }

    public Double zzh(String zzd, int i, List list) {
        throw new IllegalStateException("Statement cannot be cast as Double");
    }

    public String zzi() {
        throw new IllegalStateException("Statement cannot be cast as String");
    }

    public Iterator zzl() {
        return null;
    }
}
