package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
public enum zzaex {
    DOUBLE(zzaey.DOUBLE, 1),
    FLOAT(zzaey.FLOAT, 5),
    INT64(r12, 0),
    UINT64(r12, 0),
    INT32(r9, 0),
    FIXED64(r12, 1),
    FIXED32(r9, 5),
    BOOL(zzaey.BOOLEAN, 0),
    STRING(zzaey.STRING, 2),
    GROUP(r13, 3),
    MESSAGE(r13, 2),
    BYTES(zzaey.BYTE_STRING, 2),
    UINT32(r15, 0),
    ENUM(zzaey.ENUM, 0),
    SFIXED32(r15, 5),
    SFIXED64(r1, 1),
    SINT32(r3, 0),
    SINT64(r1, 0);
    
    private final zzaey zzt;

    zzaex(final zzaey zzaey, final int i2) {
        zzt = zzaey;
    }

    public final zzaey zza() {
        return zzt;
    }
}
