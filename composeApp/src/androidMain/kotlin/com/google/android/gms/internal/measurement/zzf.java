package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement@@20.1.1 */
public final class zzf {
    final zzax zza;
    final zzg zzb;
    final zzg zzc;
    final zzj zzd;

    public zzf() {
        final zzax zzax = new zzax();
        zza = zzax;
        final zzg zzg = new zzg(null, zzax);
        zzc = zzg;
        zzb = zzg.zza();
        final zzj zzj = new zzj();
        zzd = zzj;
        zzg.zzg("require", new zzw(zzj));
        zzj.zza("internal.platform", zze.zza);
        zzg.zzg("runtime.counter", new zzah(Double.valueOf(0.0d)));
    }

    public zzap zza(final zzg zzg, final zzgt... zzgtArr) {
        zzap zzap = com.google.android.gms.internal.measurement.zzap.zzf;
        for (final zzgt zza2 : zzgtArr) {
            zzap = zzi.zza(zza2);
            zzh.zzc(zzc);
            if ((zzap instanceof zzaq) || (zzap instanceof zzao)) {
                zzap = zza.zza(zzg, zzap);
            }
        }
        return zzap;
    }
}
