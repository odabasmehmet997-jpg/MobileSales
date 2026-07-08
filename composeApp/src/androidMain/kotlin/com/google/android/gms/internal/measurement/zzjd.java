package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzjd extends zzjg {
    private final byte[] zzb;
    private final int zzc;
    private int zzd;

    zzjd(byte[] bArr, int i2, int i3) {
        super(null);
        if (null != bArr) {
            int length = bArr.length;
            if (0 <= ((length - i3) | i3)) {
                this.zzb = bArr;
                this.zzd = 0;
                this.zzc = i3;
                return;
            }
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(length), 0, Integer.valueOf(i3)));
        }
        throw new NullPointerException("buffer");
    }

    public int zza() {
        return this.zzc - this.zzd;
    }

    public void zzd(int i2, boolean z) throws IOException {
        zzq(i2 << 3);
        zzb(z ? (byte) 1 : 0);
    }

    public void zze(int i2, zziy zziy) throws IOException {
        zzq((i2 << 3) | 2);
        zzq(zziy.zzd());
        zziy.zzh(this);
    }

    public void zzf(int i2, int i3) throws IOException {
        zzq((i2 << 3) | 5);
        zzg(i3);
    }

    public void zzg(int i2) throws IOException {
        try {
            byte[] bArr = this.zzb;
            int i3 = this.zzd;
            int i4 = i3 + 1;
            this.zzd = i4;
            bArr[i3] = (byte) (i2 & 255);
            int i5 = i3 + 2;
            this.zzd = i5;
            bArr[i4] = (byte) ((i2 >> 8) & 255);
            int i6 = i3 + 3;
            this.zzd = i6;
            bArr[i5] = (byte) ((i2 >> 16) & 255);
            this.zzd = i3 + 4;
            bArr[i6] = (byte) ((i2 >> 24) & 255);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzje(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
        }
    }

    public void zzh(int i2, long j2) throws IOException {
        zzq((i2 << 3) | 1);
        zzi(j2);
    }

    public void zzi(long j2) throws IOException {
        try {
            byte[] bArr = this.zzb;
            int i2 = this.zzd;
            int i3 = i2 + 1;
            this.zzd = i3;
            bArr[i2] = (byte) (((int) j2) & 255);
            int i4 = i2 + 2;
            this.zzd = i4;
            bArr[i3] = (byte) (((int) (j2 >> 8)) & 255);
            int i5 = i2 + 3;
            this.zzd = i5;
            bArr[i4] = (byte) (((int) (j2 >> 16)) & 255);
            int i6 = i2 + 4;
            this.zzd = i6;
            bArr[i5] = (byte) (((int) (j2 >> 24)) & 255);
            int i7 = i2 + 5;
            this.zzd = i7;
            bArr[i6] = (byte) (((int) (j2 >> 32)) & 255);
            int i8 = i2 + 6;
            this.zzd = i8;
            bArr[i7] = (byte) (((int) (j2 >> 40)) & 255);
            int i9 = i2 + 7;
            this.zzd = i9;
            bArr[i8] = (byte) (((int) (j2 >> 48)) & 255);
            this.zzd = i2 + 8;
            bArr[i9] = (byte) (((int) (j2 >> 56)) & 255);
        } catch (IndexOutOfBoundsException e2) {
            throw new zzje(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
        }
    }

    public void zzj(int i2, int i3) throws IOException {
        zzq(i2 << 3);
        zzk(i3);
    }

    public void zzk(int i2) throws IOException {
        if (0 <= i2) {
            zzq(i2);
        } else {
            zzs(i2);
        }
    }

    public void zzl(byte[] bArr, int i2, int i3) throws IOException {
        zzc(bArr, 0, i3);
    }

    public void zzm(int i2, String str) throws IOException {
        zzq((i2 << 3) | 2);
        zzn(str);
    }

    public void zzn(String str) throws IOException {
        int i2 = this.zzd;
        try {
            int zzA = zzjg.zzA(str.length() * 3);
            int zzA2 = zzjg.zzA(str.length());
            if (zzA2 == zzA) {
                int i3 = i2 + zzA2;
                this.zzd = i3;
                int zzb2 = zzmx.zzb(str, this.zzb, i3, this.zzc - i3);
                this.zzd = i2;
                zzq((zzb2 - i2) - zzA2);
                this.zzd = zzb2;
                return;
            }
            zzq(zzmx.zzc(str));
            byte[] bArr = this.zzb;
            int i4 = this.zzd;
            this.zzd = zzmx.zzb(str, bArr, i4, this.zzc - i4);
        } catch (zzmw e2) {
            this.zzd = i2;
            zzE(str, e2);
        } catch (IndexOutOfBoundsException e3) {
            throw new zzje(e3);
        }
    }

    public void zzo(int i2, int i3) throws IOException {
        zzq((i2 << 3) | i3);
    }

    public void zzp(int i2, int i3) throws IOException {
        zzq(i2 << 3);
        zzq(i3);
    }

    public void zzq(int i2) throws IOException {
        if (zzjg.zzc) {
            final int i3 = zzij.r8clinit;
        }
        while (0 != (i2 & -128)) {
            byte[] bArr = this.zzb;
            int i4 = this.zzd;
            this.zzd = i4 + 1;
            bArr[i4] = (byte) ((i2 & 127) | 128);
            i2 >>>= 7;
        }
        try {
            byte[] bArr2 = this.zzb;
            int i5 = this.zzd;
            this.zzd = i5 + 1;
            bArr2[i5] = (byte) i2;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzje(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
        }
    }

    public void zzr(int i2, long j2) throws IOException {
        zzq(i2 << 3);
        zzs(j2);
    }

    public void zzb(byte b2) throws IOException {
        try {
            byte[] bArr = this.zzb;
            int i2 = this.zzd;
            this.zzd = i2 + 1;
            bArr[i2] = b2;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzje(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
        }
    }

    public void zzc(byte[] bArr, int i2, int i3) throws IOException {
        try {
            System.arraycopy(bArr, 0, this.zzb, this.zzd, i3);
            this.zzd += i3;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzje(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), Integer.valueOf(i3)), e2);
        }
    }

    public void zzs(long j2) throws IOException {
        if (!zzjg.zzc || 10 > zzc - zzd) {
            while (0 != (j2 & -128)) {
                byte[] bArr = this.zzb;
                int i2 = this.zzd;
                this.zzd = i2 + 1;
                bArr[i2] = (byte) ((((int) j2) & 127) | 128);
                j2 >>>= 7;
            }
            try {
                byte[] bArr2 = this.zzb;
                int i3 = this.zzd;
                this.zzd = i3 + 1;
                bArr2[i3] = (byte) ((int) j2);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzje(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.zzd), Integer.valueOf(this.zzc), 1), e2);
            }
        } else {
            while (0 != (j2 & -128)) {
                byte[] bArr3 = this.zzb;
                int i4 = this.zzd;
                this.zzd = i4 + 1;
                zzms.zzn(bArr3, i4, (byte) ((((int) j2) & 127) | 128));
                j2 >>>= 7;
            }
            byte[] bArr4 = this.zzb;
            int i5 = this.zzd;
            this.zzd = i5 + 1;
            zzms.zzn(bArr4, i5, (byte) ((int) j2));
        }
    }
}
