package com.google.android.gms.internal.gtm;

/* compiled from: com.google.android.gms:play-services-analytics-impl@@18.2.0 */
final class zzys extends zzyv {
    private final int zzc;

    zzys(byte[] bArr, int i2, int i3) {
        super(bArr);
        zzyx.zzh(0, i3, bArr.length);
        this.zzc = i3;
    }

    
    public byte zzb(int i2) {
        return this.zza[i2];
    }

    
    public int zzc() {
        return 0;
    }

    public int zzd() {
        return this.zzc;
    }

    public byte zza(int i2) {
        int i3 = this.zzc;
        if (0 <= ((i3 - (i2 + 1)) | i2)) {
            return this.zza[i2];
        }
        if (0 > i2) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + i2);
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i2 + ", " + i3);
    }
}
