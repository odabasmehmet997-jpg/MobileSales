package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public enum zzacr {
    VOID(Void.class, Void.class, null),
    INT(r0, Integer.class, 0),
    LONG(Long.TYPE, Long.class, 0L),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(0.0d)),
    BOOLEAN(Boolean.TYPE, Boolean.class, Boolean.FALSE),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzyx.class, zzyx.class, zzyx.zzb),
    ENUM(r0, Integer.class, (Class) null),
    MESSAGE(Object.class, Object.class, null);
    
    private final Class zzl;

    zzacr(final Class cls, final Class cls2, final Object obj) {
        zzl = cls2;
    }

    public final Class zza() {
        return zzl;
    }
}
