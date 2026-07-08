package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
final class zzid implements zzib {
    volatile zzib zza;
    volatile boolean zzb;
    Object zzc;

    zzid(zzib zzib) {
        zzib.getClass();
        this.zza = zzib;
    }

    public String toString() {
        Object obj = this.zza;
        if (null == obj) {
            String valueOf = String.valueOf(this.zzc);
            final String sb = "<supplier that returned " +
                    valueOf +
                    ">";
            obj = sb;
        }
        String obj2 = obj.toString();
        final String sb2 = "Suppliers.memoize(" +
                obj2 +
                ")";
        return sb2;
    }

    public Object zza() {
        if (!this.zzb) {
            synchronized (this) {
                try {
                    if (!this.zzb) {
                        zzib zzib = this.zza;
                        zzib.getClass();
                        Object zza2 = zzib.zza();
                        this.zzc = zza2;
                        this.zzb = true;
                        this.zza = null;
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
