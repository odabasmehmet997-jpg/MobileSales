package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzacd implements zzabu {
    final zzaci zza;
    final int zzb;
    final zzaex zzc;
    final boolean zzd;

    zzacd(zzaci zzaci, int i2, zzaex zzaex, boolean z, boolean z2) {
        this.zza = zzaci;
        this.zzb = i2;
        this.zzc = zzaex;
        this.zzd = z;
    }

    public int compareTo(Object obj) {
        return this.zzb - ((zzacd) obj).zzb;
    }

    public int zza() {
        return this.zzb;
    }

    public zzadk zzb(zzadk zzadk, zzadl zzadl) {
        zzaca zzaca = (zzaca) zzadk;
        zzaca.zzA((zzacf) zzadl);
        return zzaca;
    }

    public zzadq zzc(zzadq zzadq, zzadq zzadq2) {
        throw new UnsupportedOperationException();
    }

    public zzaex zzd() {
        return this.zzc;
    }

    public zzaey zze() {
        return this.zzc.zza();
    }

    public boolean zzf() {
        return false;
    }

    public boolean zzg() {
        return this.zzd;
    }
}
