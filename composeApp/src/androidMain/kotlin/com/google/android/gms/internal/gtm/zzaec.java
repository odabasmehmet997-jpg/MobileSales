package com.google.android.gms.internal.gtm;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzaec implements Iterator {
    final zzaef zza;
    private int zzb = -1;
    private boolean zzc;
    private Iterator zzd;

    zzaec(final zzaef zzaef, final zzaee zzaee) {
        zza = zzaef;
    }

    private Iterator zza() {
        if (null == this.zzd) {
            zzd = zza.zzc.entrySet().iterator();
        }
        return zzd;
    }

    public boolean hasNext() {
        final int i2 = zzb + 1;
        final zzaef zzaef = zza;
        if (i2 < zzaef.zzb) {
            return true;
        }
        if (!zzaef.zzc.isEmpty()) {
            return this.zza().hasNext();
        }
        return false;
    }

    public Object next() {
        zzc = true;
        final int i2 = zzb + 1;
        zzb = i2;
        final zzaef zzaef = zza;
        if (i2 < zzaef.zzb) {
            return zzaef.zza[i2];
        }
        return this.zza().next();
    }

    public void remove() {
        if (zzc) {
            zzc = false;
            zza.zzo();
            final int i2 = zzb;
            final zzaef zzaef = zza;
            if (i2 < zzaef.zzb) {
                zzb = i2 - 1;
                final Object unused = zzaef.zzm(i2);
                return;
            }
            this.zza().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
