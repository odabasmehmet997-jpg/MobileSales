package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public class zzkm {
    private static final zzjl zzb = zzjl.zza();
    protected volatile zzlg zza;
    private volatile zziy zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof final zzkm zzkm)) {
            return false;
        }
        zzlg zzlg = this.zza;
        zzlg zzlg2 = zzkm.zza;
        if (null == zzlg && null == zzlg2) {
            return zzb().equals(zzkm.zzb());
        }
        if (null != zzlg && null != zzlg2) {
            return zzlg.equals(zzlg2);
        }
        if (null != zzlg) {
            zzkm.zzc(zzlg.zzbJ());
            return zzlg.equals(zzkm.zza);
        }
        zzc(zzlg2.zzbJ());
        return this.zza.equals(zzlg2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (null != zzc) {
            return ((zziv) this.zzc).zza.length;
        }
        if (null != zza) {
            return this.zza.zzbr();
        }
        return 0;
    }

    public final zziy zzb() {
        if (null != zzc) {
            return this.zzc;
        }
        synchronized (this) {
            try {
                if (null != zzc) {
                    zziy zziy = this.zzc;
                    return zziy;
                }
                if (null == zza) {
                    this.zzc = zziy.zzb;
                } else {
                    this.zzc = this.zza.zzbn();
                }
                zziy zziy2 = this.zzc;
                return zziy2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }


    /*  WARNING: Can't wrap try/catch for region: R(4:7|8|10|11) */
    /*  WARNING: Missing exception handler attribute for start block: B:10:0x0013 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzc(com.google.android.gms.internal.measurement.zzlg r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.measurement.zzlg r0 = r1.zza
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            monitor-enter(r1)
            com.google.android.gms.internal.measurement.zzlg r0 = r1.zza     // Catch:{ all -> 0x0011 }
            if (r0 != 0) goto L_0x001b
            r1.zza = r2     // Catch:{ zzkj -> 0x0013 }
            com.google.android.gms.internal.measurement.zziy r0 = com.google.android.gms.internal.measurement.zziy.zzb     // Catch:{ zzkj -> 0x0013 }
            r1.zzc = r0     // Catch:{ zzkj -> 0x0013 }
            goto L_0x0019
        L_0x0011:
            r2 = move-exception
            goto L_0x001d
        L_0x0013:
            r1.zza = r2     // Catch:{ all -> 0x0011 }
            com.google.android.gms.internal.measurement.zziy r2 = com.google.android.gms.internal.measurement.zziy.zzb     // Catch:{ all -> 0x0011 }
            r1.zzc = r2     // Catch:{ all -> 0x0011 }
        L_0x0019:
            monitor-exit(r1)     // Catch:{ all -> 0x0011 }
            return
        L_0x001b:
            monitor-exit(r1)     // Catch:{ all -> 0x0011 }
            return
        L_0x001d:
            monitor-exit(r1)     // Catch:{ all -> 0x0011 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzkm.zzc(com.google.android.gms.internal.measurement.zzlg):void");
    }
}
