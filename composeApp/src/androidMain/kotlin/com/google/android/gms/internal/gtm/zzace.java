package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzace extends zzabo {
    final zzadl zza;
    final Object zzb;
    final zzadl zzc;
    final zzacd zzd;

    zzace(zzadl zzadl, Object obj, zzadl zzadl2, zzacd zzacd, Class cls) {
        if (null == zzadl) {
            throw new IllegalArgumentException("Null containingTypeDefaultInstance");
        } else if (zzaex.MESSAGE == zzacd.zzc && null == zzadl2) {
            throw new IllegalArgumentException("Null messageDefaultInstance");
        } else {
            this.zza = zzadl;
            this.zzb = obj;
            this.zzc = zzadl2;
            this.zzd = zzacd;
        }
    }

    
    public Object zza(Object obj) {
        if (zzaey.ENUM != zzd.zzc.zza()) {
            return obj;
        }
        zzacd zzacd = this.zzd;
        return zzacd.zza.zza(((Integer) obj).intValue());
    }
}
