package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public class zzui {
    private final String zza;
    private final Class zzb;
    private final boolean zzc;

    protected zzui(String str, Class cls, boolean z) {
        this(str, cls, z, true);
    }

    public static zzui zza(String str, Class cls) {
        return new zzui(str, cls, false, false);
    }

    public final String toString() {
        Class cls = this.zzb;
        String name = getClass().getName();
        String name2 = cls.getName();
        return name + "/" + this.zza + "[" + name2 + "]";
    }

    public final boolean zzb() {
        return this.zzc;
    }

    private zzui(String str, Class cls, boolean z, boolean z2) {
        zzwe.zzb(str);
        this.zza = str;
        this.zzb = cls;
        this.zzc = z;
        System.identityHashCode(this);
        for (int i2 = 0; 5 > i2; i2++) {
        }
    }
}
