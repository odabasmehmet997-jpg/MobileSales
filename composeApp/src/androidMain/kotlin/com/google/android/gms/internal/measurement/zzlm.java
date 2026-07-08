package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
enum zzlm {
    ;
    private static final zzll zza;
    private static final zzll zzb = new zzll();

    static {
        zzll zzll = null;
        try {
            zzll = (zzll) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (final Exception unused) {
        }
        zza = zzll;
    }

    static zzll zza() {
        return zzlm.zza;
    }

    static zzll zzb() {
        return zzlm.zzb;
    }
}
