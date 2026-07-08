package com.google.zxing.qrcode.decoder;

import com.google.zxing.qrcode.decoder.Version;


final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;
    private DataBlock(int r1, byte[] bArr) {
        this.numDataCodewords = r1;
        this.codewords = bArr;
    }
    static DataBlock[] getDataBlocks(byte[] bArr, Version version, ErrorCorrectionLevel errorCorrectionLevel) {
        if (bArr.length != version.getTotalCodewords()) {
            throw new IllegalArgumentException();
        }
        Version.ECBlocks eCBlocksForLevel = version.getECBlocksForLevel(errorCorrectionLevel);
        Version.ECB[] eCBlocks = eCBlocksForLevel.getECBlocks();
        int count = 0;
        for (Version.ECB ecb : eCBlocks) {
            count += ecb.getCount();
        }
        DataBlock[] dataBlockArr = new DataBlock[count];
        int r5 = 0;
        for (Version.ECB ecb2 : eCBlocks) {
            int r7 = 0;
            while (r7 < ecb2.getCount()) {
                int dataCodewords = ecb2.getDataCodewords();
                dataBlockArr[r5] = new DataBlock(dataCodewords, new byte[eCBlocksForLevel.getECCodewordsPerBlock() + dataCodewords]);
                r7++;
                r5++;
            }
        }
        int length = dataBlockArr[0].codewords.length;
        int r3 = count - 1;
        while (r3 >= 0 && dataBlockArr[r3].codewords.length != length) {
            r3--;
        }
        int r32 = r3 + 1;
        int eCCodewordsPerBlock = length - eCBlocksForLevel.getECCodewordsPerBlock();
        int r2 = 0;
        for (int r13 = 0; r13 < eCCodewordsPerBlock; r13++) {
            int r4 = 0;
            while (r4 < r5) {
                dataBlockArr[r4].codewords[r13] = bArr[r2];
                r4++;
                r2++;
            }
        }
        int r132 = r32;
        while (r132 < r5) {
            dataBlockArr[r132].codewords[eCCodewordsPerBlock] = bArr[r2];
            r132++;
            r2++;
        }
        int length2 = dataBlockArr[0].codewords.length;
        while (eCCodewordsPerBlock < length2) {
            int r42 = 0;
            while (r42 < r5) {
                dataBlockArr[r42].codewords[r42 < r32 ? eCCodewordsPerBlock : eCCodewordsPerBlock + 1] = bArr[r2];
                r42++;
                r2++;
            }
            eCCodewordsPerBlock++;
        }
        return dataBlockArr;
    }
    int getNumDataCodewords() {
        return this.numDataCodewords;
    }
    byte[] getCodewords() {
        return this.codewords;
    }
}
