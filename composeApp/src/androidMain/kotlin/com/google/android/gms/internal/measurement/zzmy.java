package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
public enum zzmy {
    DOUBLE(zzmz.DOUBLE, 1),
    FLOAT(zzmz.FLOAT, 5),
    INT64(r12, 0),
    UINT64(r12, 0),
    INT32(r9, 0),
    FIXED64(r12, 1),
    FIXED32(r9, 5),
    BOOL(zzmz.BOOLEAN, 0),
    STRING(zzmz.STRING, 2),
    GROUP(r13, 3),
    MESSAGE(r13, 2),
    BYTES(zzmz.BYTE_STRING, 2),
    UINT32(r15, 0),
    ENUM(zzmz.ENUM, 0),
    SFIXED32(r15, 5),
    SFIXED64(r1, 1),
    SINT32(r3, 0),
    SINT64(r1, 0);
    
    private final zzmz zzt;

    zzmy(zzmz zzmz, int i2) {
        this.zzt = zzmz;
    }

    public final zzmz zza() {
        return this.zzt;
    }
}
