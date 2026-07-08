package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import org.kxml2.wap.Wbxml;

public final class Version {
    private static final Version[] VERSIONS = Version.buildVersions();
    private final int dataRegionSizeColumns;
    private final int dataRegionSizeRows;
    private final ECBlocks ecBlocks;
    private final int symbolSizeColumns;
    private final int symbolSizeRows;
    private final int totalCodewords;
    private final int versionNumber;

    private Version(final int i2, final int i3, final int i4, final int i5, final int i6, final ECBlocks eCBlocks) {
        versionNumber = i2;
        symbolSizeRows = i3;
        symbolSizeColumns = i4;
        dataRegionSizeRows = i5;
        dataRegionSizeColumns = i6;
        ecBlocks = eCBlocks;
        final int eCCodewords = eCBlocks.getECCodewords();
        int i7 = 0;
        for (final ECB ecb : eCBlocks.getECBlocks()) {
            i7 += ecb.getCount() * (ecb.getDataCodewords() + eCCodewords);
        }
        totalCodewords = i7;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public int getSymbolSizeRows() {
        return symbolSizeRows;
    }

    public int getSymbolSizeColumns() {
        return symbolSizeColumns;
    }

    public int getDataRegionSizeRows() {
        return dataRegionSizeRows;
    }

    public int getDataRegionSizeColumns() {
        return dataRegionSizeColumns;
    }

    public int getTotalCodewords() {
        return totalCodewords;
    }

    
    public ECBlocks getECBlocks() {
        return ecBlocks;
    }

    public static Version getVersionForDimensions(final int i2, final int i3) throws FormatException {
        if (0 == (i2 & 1) && 0 == (i3 & 1)) {
            for (final Version version : Version.VERSIONS) {
                if (version.symbolSizeRows == i2 && version.symbolSizeColumns == i3) {
                    return version;
                }
            }
            throw FormatException.getFormatInstance();
        }
        throw FormatException.getFormatInstance();
    }

    static final class ECBlocks {
        private final ECB[] ecBlocks;
        private final int ecCodewords;

        private ECBlocks(final int i2, final ECB ecb) {
            ecCodewords = i2;
            ecBlocks = new ECB[]{ecb};
        }

        private ECBlocks(final int i2, final ECB ecb, final ECB ecb2) {
            ecCodewords = i2;
            ecBlocks = new ECB[]{ecb, ecb2};
        }

        
        public int getECCodewords() {
            return ecCodewords;
        }

        
        public ECB[] getECBlocks() {
            return ecBlocks;
        }
    }

    static final class ECB {
        private final int count;
        private final int dataCodewords;

        private ECB(final int i2, final int i3) {
            count = i2;
            dataCodewords = i3;
        }

        
        public int getCount() {
            return count;
        }

        
        public int getDataCodewords() {
            return dataCodewords;
        }
    }

    public String toString() {
        return String.valueOf(versionNumber);
    }

    private static Version[] buildVersions() {
        final Version version = r0;
        final Version version2 = new Version(1, 10, 10, 8, 8, new ECBlocks(5, new ECB(1, 3)));
        final Version version3 = r16;
        final Version version4 = new Version(2, 12, 12, 10, 10, new ECBlocks(7, new ECB(1, 5)));
        final Version version5 = r22;
        final Version version6 = new Version(3, 14, 14, 12, 12, new ECBlocks(10, new ECB(1, 8)));
        final Version version7 = r28;
        final Version version8 = new Version(4, 16, 16, 14, 14, new ECBlocks(12, new ECB(1, 12)));
        final Version version9 = r16;
        final Version version10 = new Version(5, 18, 18, 16, 16, new ECBlocks(14, new ECB(1, 18)));
        final Version version11 = r22;
        final Version version12 = new Version(6, 20, 20, 18, 18, new ECBlocks(18, new ECB(1, 22)));
        final Version version13 = r35;
        final Version version14 = new Version(7, 22, 22, 20, 20, new ECBlocks(20, new ECB(1, 30)));
        final Version version15 = r16;
        final Version version16 = new Version(8, 24, 24, 22, 22, new ECBlocks(24, new ECB(1, 36)));
        final Version version17 = r22;
        final Version version18 = new Version(9, 26, 26, 24, 24, new ECBlocks(28, new ECB(1, 44)));
        final Version version19 = r44;
        final Version version20 = new Version(10, 32, 32, 14, 14, new ECBlocks(36, new ECB(1, 62)));
        final Version version21 = r18;
        final Version version22 = new Version(11, 36, 36, 16, 16, new ECBlocks(42, new ECB(1, 86)));
        final Version version23 = r24;
        final Version version24 = new Version(12, 40, 40, 18, 18, new ECBlocks(48, new ECB(1, 114)));
        final Version version25 = r44;
        final Version version26 = new Version(13, 44, 44, 20, 20, new ECBlocks(56, new ECB(1, 144)));
        final Version version27 = r50;
        final Version version28 = new Version(14, 48, 48, 22, 22, new ECBlocks(68, new ECB(1, 174)));
        final Version version29 = r56;
        final Version version30 = new Version(15, 52, 52, 24, 24, new ECBlocks(42, new ECB(2, 102)));
        final Version version31 = r44;
        final Version version32 = new Version(16, 64, 64, 14, 14, new ECBlocks(56, new ECB(2, 140)));
        final Version version33 = r50;
        final Version version34 = new Version(17, 72, 72, 16, 16, new ECBlocks(36, new ECB(4, 92)));
        final Version version35 = r56;
        final Version version36 = new Version(18, 80, 80, 18, 18, new ECBlocks(48, new ECB(4, 114)));
        final Version version37 = r44;
        final Version version38 = new Version(19, 88, 88, 20, 20, new ECBlocks(56, new ECB(4, 144)));
        final Version version39 = r50;
        final Version version40 = new Version(20, 96, 96, 22, 22, new ECBlocks(68, new ECB(4, 174)));
        final Version version41 = r56;
        final Version version42 = new Version(21, 104, 104, 24, 24, new ECBlocks(56, new ECB(6, 136)));
        final Version version43 = r44;
        final Version version44 = new Version(22, 120, 120, 18, 18, new ECBlocks(68, new ECB(6, 175)));
        final Version version45 = r50;
        final Version version46 = new Version(23, Wbxml.LITERAL_A, Wbxml.LITERAL_A, 20, 20, new ECBlocks(62, new ECB(8, 163)));
        final Version version47 = r56;
        final Version version48 = new Version(24, 144, 144, 22, 22, new ECBlocks(62, new ECB(8, 156), new ECB(2, 155)));
        final Version version49 = r44;
        final Version version50 = new Version(25, 8, 18, 6, 16, new ECBlocks(7, new ECB(1, 5)));
        final Version version51 = r50;
        final Version version52 = new Version(26, 8, 32, 6, 14, new ECBlocks(11, new ECB(1, 10)));
        final Version version53 = r56;
        final Version version54 = new Version(27, 12, 26, 10, 24, new ECBlocks(14, new ECB(1, 16)));
        final Version version55 = r44;
        final Version version56 = new Version(28, 12, 36, 10, 16, new ECBlocks(18, new ECB(1, 22)));
        final Version version57 = r50;
        final Version version58 = new Version(29, 16, 36, 14, 16, new ECBlocks(24, new ECB(1, 32)));
        final Version version59 = r41;
        final Version version60 = new Version(30, 16, 48, 14, 22, new ECBlocks(28, new ECB(1, 49)));
        return new Version[]{version, version3, version5, version7, version9, version11, version13, version15, version17, version19, version21, version23, version25, version27, version29, version31, version33, version35, version37, version39, version41, version43, version45, version47, version49, version51, version53, version55, version57, version59};
    }
}
