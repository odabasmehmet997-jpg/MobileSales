package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzaeo extends zzaem {
    zzaeo() {
    }

    
    public Object zza(final Object obj) {
        final zzacf zzacf = (zzacf) obj;
        final zzaen zzaen = zzacf.zzc;
        if (zzaen != com.google.android.gms.internal.gtm.zzaen.zzc()) {
            return zzaen;
        }
        final zzaen zzf = com.google.android.gms.internal.gtm.zzaen.zzf();
        zzacf.zzc = zzf;
        return zzf;
    }

    
    public Object zzb() {
        return zzaen.zzf();
    }

    
    public Object zzc(final Object obj) {
        final zzaen zzaen = (zzaen) obj;
        zzaen.zzh();
        return zzaen;
    }

    
    public void zzd(final Object obj, final int i2, final int i3) {
        ((zzaen) obj).zzj((i2 << 3) | 5, Integer.valueOf(i3));
    }

    
    public void zze(final Object obj, final int i2, final long j2) {
        ((zzaen) obj).zzj((i2 << 3) | 1, Long.valueOf(j2));
    }

    
    public void zzf(final Object obj, final int i2, final Object obj2) {
        ((zzaen) obj).zzj((i2 << 3) | 3, obj2);
    }

    
    public void zzg(final Object obj, final int i2, final zzyx zzyx) {
        ((zzaen) obj).zzj((i2 << 3) | 2, zzyx);
    }

    
    public void zzh(final Object obj, final int i2, final long j2) {
        ((zzaen) obj).zzj(i2 << 3, Long.valueOf(j2));
    }

    
    public void zzi(final Object obj) {
        ((zzacf) obj).zzc.zzh();
    }

    
    public void zzj(final Object obj, final Object obj2) {
        ((zzacf) obj).zzc = (zzaen) obj2;
    }
}
