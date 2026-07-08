package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzks extends zzku {
    private zzks() {
        super(null);
    }

    zzks(final zzkr zzkr) {
        super(null);
    }

    
    public void zza(final Object obj, final long j2) {
        ((zzkg) zzms.zzf(obj, j2)).zzb();
    }

    
    public void zzb(final Object obj, final Object obj2, final long j2) {
        zzkg zzkg = (zzkg) zzms.zzf(obj, j2);
        zzkg zzkg2 = (zzkg) zzms.zzf(obj2, j2);
        final int size = zzkg.size();
        final int size2 = zzkg2.size();
        if (0 < size && 0 < size2) {
            if (!zzkg.zzc()) {
                zzkg = zzkg.zzd(size2 + size);
            }
            zzkg.addAll(zzkg2);
        }
        if (0 < size) {
            zzkg2 = zzkg;
        }
        zzms.zzs(obj, j2, zzkg2);
    }
}
