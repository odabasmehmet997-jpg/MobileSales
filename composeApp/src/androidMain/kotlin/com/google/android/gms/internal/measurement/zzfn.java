package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzfn extends zzjv {
    private zzfn() {
        super(zzfo.zza);
    }

    public int zza() {
        return ((zzfo) this.zza).zzb();
    }

    public long zzb() {
        return ((zzfo) this.zza).zzc();
    }

    public long zzc() {
        return ((zzfo) this.zza).zzd();
    }

    public zzfn zzd(Iterable iterable) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzfo.zzm((zzfo) this.zza, iterable);
        return this;
    }

    public zzfn zze(zzfr zzfr) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzfo.zzk((zzfo) this.zza, (zzfs) zzfr.zzay());
        return this;
    }

    public zzfn zzf(zzfs zzfs) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzfo.zzk((zzfo) this.zza, zzfs);
        return this;
    }

    public zzfn zzg() {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        ((zzfo) this.zza).zzf = zzjz.zzby();
        return this;
    }

    public zzfn zzh(int i2) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzfo.zzo((zzfo) this.zza, i2);
        return this;
    }

    public zzfn zzi(String str) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzfo.zzp((zzfo) this.zza, str);
        return this;
    }

    public zzfn zzj(int i2, zzfr zzfr) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzfo.zzj((zzfo) this.zza, i2, (zzfs) zzfr.zzay());
        return this;
    }

    public zzfn zzk(int i2, zzfs zzfs) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzfo.zzj((zzfo) this.zza, i2, zzfs);
        return this;
    }

    public zzfn zzl(long j2) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzfo.zzr((zzfo) this.zza, j2);
        return this;
    }

    public zzfn zzm(long j2) {
        if (this.zzb) {
            zzaC();
            this.zzb = false;
        }
        zzfo.zzq((zzfo) this.zza, j2);
        return this;
    }

    public zzfs zzn(int i2) {
        return ((zzfo) this.zza).zzg(i2);
    }

    public String zzo() {
        return ((zzfo) this.zza).zzh();
    }

    public List zzp() {
        return Collections.unmodifiableList(((zzfo) this.zza).zzi());
    }

    public boolean zzq() {
        return ((zzfo) this.zza).zzu();
    }

    zzfn(zzff zzff) {
        super(zzfo.zza);
    }
}
