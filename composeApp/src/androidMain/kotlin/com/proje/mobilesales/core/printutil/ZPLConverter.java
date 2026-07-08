package com.proje.mobilesales.core.printutil;

import android.graphics.Bitmap;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.material.card.MaterialCardViewHelper;
import com.google.android.material.internal.ViewUtils;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ZPLConverter {
    private static final Map<Integer, String> mapCode = new HashMap<>();
    private int blackLimit = 380;
    private boolean compressHex = false;
    private int total;
    private int widthBytes;
    public ZPLConverter() {
        this.total = total;
        this.widthBytes = widthBytes;
        mapCode.put(1, "G");
        mapCode.put(2, "H");
        mapCode.put(3, "I");
        mapCode.put(4, "J");
        mapCode.put(5, "K");
        mapCode.put(6, "L");
        mapCode.put(7, "M");
        mapCode.put(8, "N");
        mapCode.put(9, "O");
        mapCode.put(10, "P");
        mapCode.put(11, "Q");
        mapCode.put(12, "R");
        mapCode.put(13, ExifInterface.LATITUDE_SOUTH);
        mapCode.put(14, ExifInterface.GPS_DIRECTION_TRUE);
        mapCode.put(15, "U");
        mapCode.put(16, ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        mapCode.put(17, ExifInterface.LONGITUDE_WEST);
        mapCode.put(18, "X");
        mapCode.put(19, "Y");
        mapCode.put(20, "g");
        mapCode.put(40, "h");
        mapCode.put(60, "i");
        mapCode.put(80, "j");
        mapCode.put(100, "k");
        mapCode.put(120, "l");
        mapCode.put(140, "m");
        mapCode.put(160, "n");
        mapCode.put(180, "o");
        mapCode.put(200, "p");
        mapCode.put(220, "q");
        mapCode.put(240, "r");
        mapCode.put(260, "s");
        mapCode.put(280, "t");
        mapCode.put(Integer.valueOf(MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION), "u");
        mapCode.put(320, "v");
        mapCode.put(340, "w");
        mapCode.put(360, "x");
        mapCode.put(380, "y");
        mapCode.put(400, "z");
    }
    public String convertfromImg(Bitmap bitmap) throws IOException {
        String createBody = createBody(bitmap);
        if (this.compressHex) {
            createBody = encodeHexAscii(createBody);
        }
        return headDoc() + createBody + footDoc();
    }
    private String createBody(Bitmap bitmap) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        char[] cArr = {'0', '0', '0', '0', '0', '0', '0', '0'};
        int i2 = width / 8;
        this.widthBytes = i2;
        if (width % 8 > 0) {
            this.widthBytes = i2 + 1;
        } else {
            this.widthBytes = i2;
        }
        this.total = this.widthBytes * height;
        bitmap.getPixels(new int[width * height], 0, width, 0, 0, width, height);
        int i3 = 0;
        for (int i4 = 0; i4 < height; i4++) {
            for (int i5 = 0; i5 < width; i5++) {
                int pixel = bitmap.getPixel(i5, i4);
                cArr[i3] = (((pixel >> 16) & 255) + ((pixel >> 8) & 255)) + (pixel & 255) > this.blackLimit ? '0' : '1';
                i3++;
                if (i3 == 8 || i5 == width - 1) {
                    stringBuffer.append(fourByteBinary(new String(cArr)));
                    cArr = new char[]{'0', '0', '0', '0', '0', '0', '0', '0'};
                    i3 = 0;
                }
            }
            stringBuffer.append(SqlLiteVariable._NEW_LINE);
        }
        return stringBuffer.toString();
    }
    private String fourByteBinary(String str) {
        int parseInt = Integer.parseInt(str, 2);
        if (parseInt > 15) {
            return Integer.toString(parseInt, 16).toUpperCase();
        }
        return "0" + Integer.toString(parseInt, 16).toUpperCase();
    }
    public String encodeHexAscii(String str) {
        int i2 = this.widthBytes * 2;
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        char charAt = str.charAt(0);
        String str2 = null;
        boolean z = false;
        int i3 = 1;
        for (int i4 = 1; i4 < str.length(); i4++) {
            if (z) {
                charAt = str.charAt(i4);
                z = false;
            } else if (str.charAt(i4) == '\n') {
                if (i3 >= i2 && charAt == '0') {
                    stringBuffer2.append(",");
                } else if (i3 >= i2 && charAt == 'F') {
                    stringBuffer2.append("!");
                } else if (i3 > 20) {
                    int i5 = (i3 / 20) * 20;
                    int i6 = i3 % 20;
                    stringBuffer2.append(mapCode.get(Integer.valueOf(i5)));
                    if (i6 != 0) {
                        stringBuffer2.append(mapCode.get(Integer.valueOf(i6)) + charAt);
                    } else {
                        stringBuffer2.append(charAt);
                    }
                } else {
                    stringBuffer2.append(mapCode.get(Integer.valueOf(i3)) + charAt);
                    mapCode.get(Integer.valueOf(i3));
                }
                if (stringBuffer2.toString().equals(str2)) {
                    stringBuffer.append(":");
                } else {
                    stringBuffer.append(stringBuffer2);
                }
                str2 = stringBuffer2.toString();
                stringBuffer2.setLength(0);
                z = true;
                i3 = 1;
            } else if (charAt == str.charAt(i4)) {
                i3++;
            } else {
                if (i3 > 20) {
                    int i7 = (i3 / 20) * 20;
                    int i8 = i3 % 20;
                    stringBuffer2.append(mapCode.get(Integer.valueOf(i7)));
                    if (i8 != 0) {
                        stringBuffer2.append(mapCode.get(Integer.valueOf(i8)) + charAt);
                    } else {
                        stringBuffer2.append(charAt);
                    }
                } else {
                    stringBuffer2.append(mapCode.get(Integer.valueOf(i3)) + charAt);
                }
                charAt = str.charAt(i4);
                i3 = 1;
            }
        }
        return stringBuffer.toString();
    }
    private String headDoc() {
        return "^XA ^FO50,50^GFA," + this.total + "," + this.total + "," + this.widthBytes + ", ";
    }
    private String footDoc() {
        return "^FS^XZ";
    }
    public void setCompressHex(boolean compressHex) {
        this.compressHex = compressHex;
    }
    public void setBlacknessLimitPercentage(int blacknessLimitPercentage) {
        this.blackLimit = (blacknessLimitPercentage * ViewUtils.EDGE_TO_EDGE_FLAGS) / 100;
    }
}
