package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfb extends zzjv {
    private zzfb() {
        super(zzfc.zza);
    }

    public int zza() {
        return ((zzfc) this.zza).zzb();
    }

    public zzfa zzb(int i2) {
        return ((zzfc) this.zza).zzd(i2);
    }

    public zzfb zzc() {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        ((zzfc) this.zza).zzk = zzjz.zzby();
        return this;
    }

    public zzfb zzd(int i2, zzez zzez) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzfc.zzm((zzfc) this.zza, i2, (zzfa) zzez.zzay());
        return this;
    }

    public List zze() {
        return Collections.unmodifiableList(((zzfc) this.zza).zzi());
    }

    zzfb(zzey zzey) {
        super(zzfc.zza);
    }
}
