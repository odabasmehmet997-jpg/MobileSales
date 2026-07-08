package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzadd implements zzady {
    private static final zzadj zza = new zzadb();
    private final zzadj zzb;

    public zzadd() {
        zzabz zza2 = zzabz.zza();
        final int i2 = zzadt.r8clinit;
        zzadc zzadc = new zzadc(zza2, zza);
        byte[] bArr = zzaco.zzb;
        this.zzb = zzadc;
    }

    public zzadx zza(Class cls) {
        final int i2 = zzadz.r8clinit;
        if (!zzacf.class.isAssignableFrom(cls)) {
            final int i3 = zzadt.r8clinit;
        }
        zzadi zzb2 = this.zzb.zzb(cls);
        if (!zzb2.zzb()) {
            final int i4 = zzadt.r8clinit;
            return zzado.zzm(cls, zzb2, zzads.zza(), zzacz.zza(), zzadz.zzm(), 1 != zzb2.zzc() + -1 ? zzabt.zza() : null, zzadh.zza());
        }
        final int i5 = zzadt.r8clinit;
        return zzadp.zzc(zzadz.zzm(), zzabt.zza(), zzb2.zza());
    }
}
