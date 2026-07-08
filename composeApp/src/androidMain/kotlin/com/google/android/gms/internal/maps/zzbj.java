package com.google.android.gms.internal.maps;

/* compiled from: com.google.android.gms:play-services-maps@@19.0.0 */
final class zzbj {
    private final Object zza;
    private final Object zzb;
    private final Object zzc;

    zzbj(final Object obj, final Object obj2, final Object obj3) {
        zza = obj;
        zzb = obj2;
        zzc = obj3;
    }

    
    public IllegalArgumentException zza() {
        final Object obj = zzc;
        final Object obj2 = zzb;
        final Object obj3 = zza;
        final String valueOf = String.valueOf(obj3);
        final String valueOf2 = String.valueOf(obj2);
        final String valueOf3 = String.valueOf(obj3);
        final String valueOf4 = String.valueOf(obj);
        return new IllegalArgumentException("Multiple entries with same key: " + valueOf + "=" + valueOf2 + " and " + valueOf3 + "=" + valueOf4);
    }
}
