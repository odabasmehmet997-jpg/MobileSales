package org.apache.harmony.awt.datatransfer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.SystemFlavorMap;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;


public class TextFlavor {
    public static final Class[] unicodeTextClasses = {String.class, Reader.class, CharBuffer.class, char[].class};
    public static final Class[] charsetTextClasses = {InputStream.class, ByteBuffer.class, byte[].class};

    public static void addUnicodeClasses(SystemFlavorMap systemFlavorMap, String str, String str2) {
        int i2 = 0;
        while (true) {
            Class[] clsArr = unicodeTextClasses;
            if (i2 < clsArr.length) {
                String str3 = "text/" + str2;
                DataFlavor dataFlavor = new DataFlavor(str3 + (";class=\"" + clsArr[i2].getName() + "\""), str3);
                systemFlavorMap.addFlavorForUnencodedNative(str, dataFlavor);
                systemFlavorMap.addUnencodedNativeForFlavor(dataFlavor, str);
                i2++;
            } else {
                return;
            }
        }
    }

    public static void addCharsetClasses(SystemFlavorMap systemFlavorMap, String str, String str2, String str3) {
        int i2 = 0;
        while (true) {
            Class[] clsArr = charsetTextClasses;
            if (i2 < clsArr.length) {
                String str4 = "text/" + str2;
                DataFlavor dataFlavor = new DataFlavor(str4 + (";class=\"" + clsArr[i2].getName() + "\";charset=\"" + str3 + "\""), str4);
                systemFlavorMap.addFlavorForUnencodedNative(str, dataFlavor);
                systemFlavorMap.addUnencodedNativeForFlavor(dataFlavor, str);
                i2++;
            } else {
                return;
            }
        }
    }
}
