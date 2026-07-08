package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzlk implements zzlr {
    private final zzlg zza;
    private final zzmi zzb;
    private final boolean zzc;
    private final zzjm zzd;

    private zzlk(zzmi zzmi, zzjm zzjm, zzlg zzlg) {
        this.zzb = zzmi;
        this.zzc = zzjm.zzc(zzlg);
        this.zzd = zzjm;
        this.zza = zzlg;
    }

    static zzlk zzc(zzmi zzmi, zzjm zzjm, zzlg zzlg) {
        return new zzlk(zzmi, zzjm, zzlg);
    }

    public int zza(Object obj) {
        zzmi zzmi = this.zzb;
        int zzb2 = zzmi.zzb(zzmi.zzc(obj));
        if (!this.zzc) {
            return zzb2;
        }
        this.zzd.zza(obj);
        throw null;
    }

    public int zzb(Object obj) {
        int hashCode = this.zzb.zzc(obj).hashCode();
        if (!this.zzc) {
            return hashCode;
        }
        this.zzd.zza(obj);
        throw null;
    }

    public Object zze() {
        return this.zza.zzbA().zzaA();
    }

    public void zzf(Object obj) {
        this.zzb.zzg(obj);
        this.zzd.zzb(obj);
    }

    public void zzg(Object obj, Object obj2) {
        zzlt.zzF(this.zzb, obj, obj2);
        if (this.zzc) {
            zzlt.zzE(this.zzd, obj, obj2);
        }
    }

    public void zzh(Object obj, byte[] bArr, int i2, int i3, zzik zzik) throws IOException {
        zzjz zzjz = (zzjz) obj;
        if (zzjz.zzc == zzmj.zzc()) {
            zzjz.zzc = zzmj.zze();
        }
        zzjw zzjw = (zzjw) obj;
        throw null;
    }

    public boolean zzi(Object obj, Object obj2) {
        if (!this.zzb.zzc(obj).equals(this.zzb.zzc(obj2))) {
            return false;
        }
        if (!this.zzc) {
            return true;
        }
        this.zzd.zza(obj);
        this.zzd.zza(obj2);
        throw null;
    }

    public boolean zzj(Object obj) {
        this.zzd.zza(obj);
        throw null;
    }

    public void zzm(Object obj, zzjh zzjh) throws IOException {
        this.zzd.zza(obj);
        throw null;
    }
}
