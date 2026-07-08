package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public final class zzsq extends zzacf {
    
    public static final zzsq zza;
    private final zzacn zzd = zzai();

    static {
        final zzsq zzsq = new zzsq();
        zza = zzsq;
        zzao(zzsq.class, zzsq);
    }

    private zzsq() {
    }

    public static zzsq zzc() {
        return zzsq.zza;
    }

    
    public Object zzb(final int i2, final Object obj, final Object obj2) {
        final int i3 = i2 - 1;
        if (0 == i3) {
            return (byte) 1;
        }
        if (2 == i3) {
            return zzal(zzsq.zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzd", zzsp.class});
        } else if (3 == i3) {
            return new zzsq();
        } else {
            if (4 == i3) {
                return new zzsn(null);
            }
            if (5 == i3) {
                return zzsq.zza;
            }
            throw null;
        }
    }
}
