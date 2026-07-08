package com.google.zxing.datamatrix.decoder;

final class DataBlock {
    private final byte[] codewords;
    private final int numDataCodewords;

    private DataBlock(final int i2, final byte[] bArr) {
        numDataCodewords = i2;
        codewords = bArr;
    }

    static DataBlock[] getDataBlocks(final byte[] bArr, final Version version) {
        final Version.ECBlocks eCBlocks = version.getECBlocks();
        final Version.ECB[] eCBlocks2 = eCBlocks.getECBlocks();
        int i2 = 0;
        for (final Version.ECB count : eCBlocks2) {
            i2 += count.getCount();
        }
        final DataBlock[] dataBlockArr = new DataBlock[i2];
        int i3 = 0;
        for (final Version.ECB ecb : eCBlocks2) {
            int i4 = 0;
            while (i4 < ecb.getCount()) {
                final int dataCodewords = ecb.getDataCodewords();
                dataBlockArr[i3] = new DataBlock(dataCodewords, new byte[(eCBlocks.getECCodewords() + dataCodewords)]);
                i4++;
                i3++;
            }
        }
        int length = dataBlockArr[0].codewords.length - eCBlocks.getECCodewords();
        final int i5 = length - 1;
        int i6 = 0;
        for (int i7 = 0; i7 < i5; i7++) {
            int i8 = 0;
            while (i8 < i3) {
                dataBlockArr[i8].codewords[i7] = bArr[i6];
                i8++;
                i6++;
            }
        }
        final boolean z = 24 == version.getVersionNumber();
        final int i9 = z ? 8 : i3;
        int i10 = 0;
        while (i10 < i9) {
            dataBlockArr[i10].codewords[i5] = bArr[i6];
            i10++;
            i6++;
        }
        final int length2 = dataBlockArr[0].codewords.length;
        while (length < length2) {
            int i11 = 0;
            while (i11 < i3) {
                final int i12 = z ? (i11 + 8) % i3 : i11;
                dataBlockArr[i12].codewords[(!z || 7 >= i12) ? length : length - 1] = bArr[i6];
                i11++;
                i6++;
            }
            length++;
        }
        if (i6 == bArr.length) {
            return dataBlockArr;
        }
        throw new IllegalArgumentException();
    }

    
    public int getNumDataCodewords() {
        return numDataCodewords;
    }

    
    public byte[] getCodewords() {
        return codewords;
    }
}
