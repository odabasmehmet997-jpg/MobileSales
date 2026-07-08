package com.proje.mobilesales.features.dbmodel;

import android.util.Log;
import com.proje.mobilesales.core.enums.BinaryType;
import com.proje.mobilesales.core.utils.CompressUtil;
import java.io.IOException;

public abstract class ConvertBinaries {
    public abstract void checkBinaries();

    static   class C26941 {
        static final int[] SwitchMapcomprojemobilesalescoreenumsBinaryType;

        static {
            int[] iArr = new int[BinaryType.values().length];
            SwitchMapcomprojemobilesalescoreenumsBinaryType = iArr;
            try {
                iArr[BinaryType.btIMAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsBinaryType[BinaryType.btSTRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                SwitchMapcomprojemobilesalescoreenumsBinaryType[BinaryType.btDOCUMENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    protected byte[] Convert(BinaryType binaryType, byte[] bArr) throws IOException {
        if (bArr == null) {
            return bArr;
        }
        try {
            return C26941.SwitchMapcomprojemobilesalescoreenumsBinaryType[binaryType.ordinal()] != 1 ? bArr : CompressUtil.compressBitmapToJpeg(CompressUtil.resizeBitmap(CompressUtil.deCompressBitmap(bArr), 48, 48));
        } catch (Exception e2) {
            Log.e("ConvertBinaries : ", e2.getMessage());
            return bArr;
        }
    }
}
