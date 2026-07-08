package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzag implements zzap {
    private final zzap zza;
    private final String zzb;

    public zzag() {
        throw null;
    }

    public zzag(final String str) {
        zza = zzf;
        zzb = str;
    }

    public zzag(final String str, final zzap zzap) {
        zza = zzap;
        zzb = str;
    }

    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzag zzag)) {
            return false;
        }
        return zzb.equals(zzag.zzb) && zza.equals(zzag.zza);
    }

    public int hashCode() {
        return (zzb.hashCode() * 31) + zza.hashCode();
    }

    public zzap zzb() {
        return zza;
    }

    public zzap zzbI(final String str, final zzg zzg, final List list) {
        throw new IllegalStateException("Control does not have functions");
    }

    public String zzc() {
        return zzb;
    }

    public zzap zzd() {
        return new zzag(zzb, zza.zzd());
    }

    public Boolean zzg() {
        throw new IllegalStateException("Control is not a boolean");
    }

    public Double zzh(String zzd, int i, List list) {
        throw new IllegalStateException("Control is not a double");
    }

    public String zzi() {
        throw new IllegalStateException("Control is not a String");
    }

    public Iterator zzl() {
        return null;
    }
}
