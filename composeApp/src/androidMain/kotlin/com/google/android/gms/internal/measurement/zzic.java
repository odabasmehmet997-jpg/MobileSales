package com.google.android.gms.internal.measurement;

import java.io.Serializable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzic implements Serializable, zzib {
    final zzib zza;
    volatile transient boolean zzb;
    transient Object zzc;

    zzic(zzib zzib) {
        zzib.getClass();
        this.zza = zzib;
    }

    public String toString() {
        Object obj;
        if (this.zzb) {
            String valueOf = String.valueOf(this.zzc);
            final String sb = "<supplier that returned " +
                    valueOf +
                    ">";
            obj = sb;
        } else {
            obj = this.zza;
        }
        String valueOf2 = String.valueOf(obj);
        final String sb2 = "Suppliers.memoize(" +
                valueOf2 +
                ")";
        return sb2;
    }

    public Object zza() {
        if (!this.zzb) {
            synchronized (this) {
                try {
                    if (!this.zzb) {
                        Object zza2 = this.zza.zza();
                        this.zzc = zza2;
                        this.zzb = true;
                        return zza2;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.zzc;
    }
}
