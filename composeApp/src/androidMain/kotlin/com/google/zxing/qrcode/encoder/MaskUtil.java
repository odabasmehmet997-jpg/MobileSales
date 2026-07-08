package com.google.zxing.qrcode.encoder;

final class MaskUtil {
    private MaskUtil() {
    }
    static int applyMaskPenaltyRule1(ByteMatrix byteMatrix) {
        return applyMaskPenaltyRule1Internal(byteMatrix, true) + applyMaskPenaltyRule1Internal(byteMatrix, false);
    }
    static int applyMaskPenaltyRule2(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int r4 = 0;
        for (int r3 = 0; r3 < height - 1; r3++) {
            int r5 = 0;
            while (r5 < width - 1) {
                byte[] bArr = array[r3];
                byte b2 = bArr[r5];
                int r8 = r5 + 1;
                if (b2 == bArr[r8]) {
                    byte[] bArr2 = array[r3 + 1];
                    if (b2 == bArr2[r5] && b2 == bArr2[r8]) {
                        r4++;
                    }
                }
                r5 = r8;
            }
        }
        return r4 * 3;
    }
    static int applyMaskPenaltyRule3(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int r4 = 0;
        for (int r3 = 0; r3 < height; r3++) {
            for (int r5 = 0; r5 < width; r5++) {
                byte[] bArr = array[r3];
                int r7 = r5 + 6;
                if (r7 < width && bArr[r5] == 1 && bArr[r5 + 1] == 0 && bArr[r5 + 2] == 1 && bArr[r5 + 3] == 1 && bArr[r5 + 4] == 1 && bArr[r5 + 5] == 0 && bArr[r7] == 1 && (isWhiteHorizontal(bArr, r5 - 4, r5) || isWhiteHorizontal(bArr, r5 + 7, r5 + 11))) {
                    r4++;
                }
                int r6 = r3 + 6;
                if (r6 < height && array[r3][r5] == 1 && array[r3 + 1][r5] == 0 && array[r3 + 2][r5] == 1 && array[r3 + 3][r5] == 1 && array[r3 + 4][r5] == 1 && array[r3 + 5][r5] == 0 && array[r6][r5] == 1 && (isWhiteVertical(array, r5, r3 - 4, r3) || isWhiteVertical(array, r5, r3 + 7, r3 + 11))) {
                    r4++;
                }
            }
        }
        return r4 * 40;
    }
    private static boolean isWhiteHorizontal(byte[] bArr, int r4, int r5) {
        int r52 = Math.min(r5, bArr.length);
        for (int r42 = Math.max(r4, 0); r42 < r52; r42++) {
            if (bArr[r42] == 1) {
                return false;
            }
        }
        return true;
    }
    private static boolean isWhiteVertical(byte[][] bArr, int r4, int r5, int r6) {
        int r62 = Math.min(r6, bArr.length);
        for (int r52 = Math.max(r5, 0); r52 < r62; r52++) {
            if (bArr[r52][r4] == 1) {
                return false;
            }
        }
        return true;
    }
    static int applyMaskPenaltyRule4(ByteMatrix byteMatrix) {
        byte[][] array = byteMatrix.getArray();
        int width = byteMatrix.getWidth();
        int height = byteMatrix.getHeight();
        int r5 = 0;
        for (int r4 = 0; r4 < height; r4++) {
            byte[] bArr = array[r4];
            for (int r8 = 0; r8 < width; r8++) {
                if (bArr[r8] == 1) {
                    r5++;
                }
            }
        }
        int height2 = byteMatrix.getHeight() * byteMatrix.getWidth();
        return ((Math.abs((r5 << 1) - height2) * 10) / height2) * 10;
    }
    static boolean getDataMaskBit(int r1, int r2, int r3) {
        int r12;
        int r13;
        switch (r1) {
            case 0:
                r3 += r2;
                r12 = r3 & 1;
                return r12 != 0;
            case 1:
                r12 = r3 & 1;
                if (r12 != 0) {
                }
                break;
            case 2:
                r12 = r2 % 3;
                if (r12 != 0) {
                }
                break;
            case 3:
                r12 = (r3 + r2) % 3;
                if (r12 != 0) {
                }
                break;
            case 4:
                r3 /= 2;
                r2 /= 3;
                r3 += r2;
                r12 = r3 & 1;
                if (r12 != 0) {
                }
                break;
            case 5:
                int r32 = r3 * r2;
                r12 = (r32 & 1) + (r32 % 3);
                if (r12 != 0) {
                }
                break;
            case 6:
                int r33 = r3 * r2;
                r13 = (r33 & 1) + (r33 % 3);
                r12 = r13 & 1;
                if (r12 != 0) {
                }
                break;
            case 7:
                r13 = ((r3 * r2) % 3) + ((r3 + r2) & 1);
                r12 = r13 & 1;
                if (r12 != 0) {
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid mask pattern: " + r1);
        }
        return false;
    }
    private static int applyMaskPenaltyRule1Internal(ByteMatrix byteMatrix, boolean z) {
        int height = z ? byteMatrix.getHeight() : byteMatrix.getWidth();
        int width = z ? byteMatrix.getWidth() : byteMatrix.getHeight();
        byte[][] array = byteMatrix.getArray();
        int r4 = 0;
        for (int r3 = 0; r3 < height; r3++) {
            byte b2 = -1;
            int r7 = 0;
            for (int r6 = 0; r6 < width; r6++) {
                byte b3 = z ? array[r3][r6] : array[r6][r3];
                if (b3 == b2) {
                    r7++;
                } else {
                    if (r7 >= 5) {
                        r4 += r7 - 2;
                    }
                    r7 = 1;
                    b2 = b3;
                }
            }
            if (r7 >= 5) {
                r4 += r7 - 2;
            }
        }
        return r4;
    }
}
