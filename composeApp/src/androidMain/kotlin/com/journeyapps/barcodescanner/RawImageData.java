package com.journeyapps.barcodescanner;

import android.graphics.Rect;

public record RawImageData(byte[] data, int width, int height) {

    public RawImageData cropAndScale(Rect rect, int r12) {
        int r0 = rect.width() / r12;
        int r1 = rect.height() / r12;
        int r2 = rect.top;
        byte[] bArr = new byte[r0 * r1];
        if (r12 == 1) {
            int r22 = (r2 * this.width) + rect.left;
            for (int r4 = 0; r4 < r1; r4++) {
                System.arraycopy(this.data, r22, bArr, r4 * r0, r0);
                r22 += this.width;
            }
        } else {
            int r23 = (r2 * this.width) + rect.left;
            for (int r11 = 0; r11 < r1; r11++) {
                int r6 = r11 * r0;
                int r8 = r23;
                for (int r7 = 0; r7 < r0; r7++) {
                    bArr[r6] = this.data[r8];
                    r8 += r12;
                    r6++;
                }
                r23 += this.width * r12;
            }
        }
        return new RawImageData(bArr, r0, r1);
    }

    public RawImageData rotateCameraPreview(int r4) {
        if (r4 == 90) {
            return new RawImageData(rotateCW(this.data, this.width, this.height), this.height, this.width);
        }
        if (r4 != 180) {
            return r4 != 270 ? this : new RawImageData(rotateCCW(this.data, this.width, this.height), this.height, this.width);
        }
        return new RawImageData(rotate180(this.data, this.width, this.height), this.width, this.height);
    }

    public static byte[] rotateCW(byte[] bArr, int r6, int r7) {
        byte[] bArr2 = new byte[r6 * r7];
        int r2 = 0;
        for (int r1 = 0; r1 < r6; r1++) {
            for (int r3 = r7 - 1; r3 >= 0; r3--) {
                bArr2[r2] = bArr[(r3 * r6) + r1];
                r2++;
            }
        }
        return bArr2;
    }

    public static byte[] rotate180(byte[] bArr, int r4, int r5) {
        int r42 = r4 * r5;
        byte[] bArr2 = new byte[r42];
        int r0 = r42 - 1;
        for (int r1 = 0; r1 < r42; r1++) {
            bArr2[r0] = bArr[r1];
            r0--;
        }
        return bArr2;
    }

    public static byte[] rotateCCW(byte[] bArr, int r6, int r7) {
        int r0 = r6 * r7;
        byte[] bArr2 = new byte[r0];
        int r02 = r0 - 1;
        for (int r2 = 0; r2 < r6; r2++) {
            for (int r3 = r7 - 1; r3 >= 0; r3--) {
                bArr2[r02] = bArr[(r3 * r6) + r2];
                r02--;
            }
        }
        return bArr2;
    }
}
