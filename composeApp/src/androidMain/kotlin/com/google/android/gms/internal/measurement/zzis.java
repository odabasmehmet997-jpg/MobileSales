package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzis extends zziv {
    private final int zzc;

    zzis(byte[] bArr, int i2, int i3) {
        super(bArr);
        zziy.zzj(0, i3, bArr.length);
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
            final String sb = "Index < 0: " +
                    i2;
            throw new ArrayIndexOutOfBoundsException(sb);
        }
        final String sb2 = "Index > length: " +
                i2 +
                ", " +
                i3;
        throw new ArrayIndexOutOfBoundsException(sb2);
    }
}
