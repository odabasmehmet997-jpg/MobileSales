package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@20.1.1 */
final class zzmv extends zzmu {
    zzmv() {
    }

    
    public int zza(final int i2, final byte[] bArr, int i3, final int i4) {
        while (r10 < i4 && 0 <= bArr[r10]) {
            i3 = r10 + 1;
        }
        if (r10 >= i4) {
            return 0;
        }
        while (r10 < i4) {
            final int i5 = r10 + 1;
            final byte b2 = bArr[r10];
            if (0 > b2) {
                if (-32 > b2) {
                    if (i5 >= i4) {
                        return b2;
                    }
                    if (-62 <= b2) {
                        r10 += 2;
                        if (-65 < bArr[i5]) {
                        }
                    }
                } else if (-16 > b2) {
                    if (i5 >= i4 - 1) {
                        return zzmx.zza(bArr, i5, i4);
                    }
                    final int i6 = r10 + 2;
                    final byte b3 = bArr[i5];
                    if (-65 >= b3 && ((-32 != b2 || -96 <= b3) && (-19 != b2 || -96 > b3))) {
                        r10 += 3;
                        if (-65 < bArr[i6]) {
                        }
                    }
                } else if (i5 >= i4 - 2) {
                    return zzmx.zza(bArr, i5, i4);
                } else {
                    final int i7 = r10 + 2;
                    final byte b4 = bArr[i5];
                    if (-65 >= b4 && 0 == (((b2 << 28) + (b4 + 112)) >> 30)) {
                        final int i8 = r10 + 3;
                        if (-65 >= bArr[i7]) {
                            r10 += 4;
                            if (-65 < bArr[i8]) {
                            }
                        }
                    }
                }
                return -1;
            }
            r10 = i5;
        }
        return 0;
    }
}
