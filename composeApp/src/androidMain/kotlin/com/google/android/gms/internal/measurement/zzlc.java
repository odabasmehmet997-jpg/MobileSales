package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
enum zzlc {
    ;
    private static final zzlb zza;
    private static final zzlb zzb = new zzlb();

    static {
        zzlb zzlb = null;
        try {
            zzlb = (zzlb) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (final Exception unused) {
        }
        zza = zzlb;
    }

    static zzlb zza() {
        return zzlc.zza;
    }

    static zzlb zzb() {
        return zzlc.zzb;
    }
}
