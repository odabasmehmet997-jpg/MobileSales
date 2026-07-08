package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
enum zzjo {
    ;
    private static final zzjm zza = new zzjn();
    private static final zzjm zzb;

    static {
        zzjm zzjm = null;
        try {
            zzjm = (zzjm) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (final Exception unused) {
        }
        zzb = zzjm;
    }

    static zzjm zza() {
        final zzjm zzjm = zzjo.zzb;
        if (null != zzjm) {
            return zzjm;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzjm zzb() {
        return zzjo.zza;
    }
}
