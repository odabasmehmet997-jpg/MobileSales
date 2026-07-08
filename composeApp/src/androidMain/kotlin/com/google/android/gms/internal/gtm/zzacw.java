package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public class zzacw {
    protected volatile zzadl zza;
    private volatile zzyx zzb;

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzacw zzacw)) {
            return false;
        }
        final zzadl zzadl = zza;
        final zzadl zzadl2 = zzacw.zza;
        if (null == zzadl && null == zzadl2) {
            return this.zzb().equals(zzacw.zzb());
        }
        if (null != zzadl && null != zzadl2) {
            return zzadl.equals(zzadl2);
        }
        if (null != zzadl) {
            zzacw.zzd(zzadl.zzay());
            return zzadl.equals(zzacw.zza);
        }
        this.zzd(zzadl2.zzay());
        return zza.equals(zzadl2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (null != this.zzb) {
            return ((zzyv) zzb).zza.length;
        }
        if (null != this.zza) {
            return zza.zzY();
        }
        return 0;
    }

    public final zzyx zzb() {
        if (null != this.zzb) {
            return zzb;
        }
        synchronized (this) {
            try {
                if (null != this.zzb) {
                    final zzyx zzyx = zzb;
                    return zzyx;
                }
                if (null == this.zza) {
                    zzb = zzyx.zzb;
                } else {
                    zzb = zza.zzR();
                }
                final zzyx zzyx2 = zzb;
                return zzyx2;
            } catch (final Throwable th) {
                throw th;
            }
        }
    }

    public final zzadl zzc(final zzadl zzadl) {
        final zzadl zzadl2 = zza;
        zzb = null;
        zza = zzadl;
        return zzadl2;
    }

    public final void zzd(final com.google.android.gms.internal.gtm.zzadl r2) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.gtm.zzacw.zzd(com.google.android.gms.internal.gtm.zzadl):void");
    }
}
