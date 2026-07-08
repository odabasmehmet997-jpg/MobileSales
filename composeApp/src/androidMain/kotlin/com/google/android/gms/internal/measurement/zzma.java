package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzma implements Iterator {
    final zzme zza;
    private int zzb = -1;
    private boolean zzc;
    private Iterator zzd;

    zzma(zzme zzme, zzlz zzlz) {
        this.zza = zzme;
    }

    private Iterator zza() {
        if (null == zzd) {
            this.zzd = this.zza.zzc.entrySet().iterator();
        }
        return this.zzd;
    }

    public boolean hasNext() {
        if (this.zzb + 1 < this.zza.zzb.size()) {
            return true;
        }
        if (this.zza.zzc.isEmpty()) {
            return false;
        }
        return zza().hasNext();
    }

    public Object next() {
        this.zzc = true;
        int i2 = this.zzb + 1;
        this.zzb = i2;
        if (i2 < this.zza.zzb.size()) {
            return this.zza.zzb.get(this.zzb);
        }
        return zza().next();
    }

    public void remove() {
        if (this.zzc) {
            this.zzc = false;
            this.zza.zzn();
            if (this.zzb < this.zza.zzb.size()) {
                zzme zzme = this.zza;
                int i2 = this.zzb;
                this.zzb = i2 - 1;
                Object unused = zzme.zzl(i2);
                return;
            }
            zza().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
