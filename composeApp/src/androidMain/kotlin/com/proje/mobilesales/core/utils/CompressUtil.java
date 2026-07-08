package com.proje.mobilesales.core.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.kobjects.base64.Base64;

public class CompressUtil {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static final String TAG = "Base64Compress";
    public static String base64Encode(String str) {
        try {
            return Base64.encode(new String(str.getBytes(StandardCharsets.UTF_8)).getBytes());
        } catch (Exception e3) {
            Log.e(TAG, "base64Encode: ", e3);
            return "";
        }
    }
    public static String base64Decode(String str) {
        String str2 = new String(Base64.decode(str));
        return new String(str2.getBytes(StandardCharsets.UTF_8));
    }
    public static byte[] base64DecodeArray(String str) {
        try {
            return Base64.decode(str);
        } catch (Exception e2) {
            Log.e(TAG, "base64DecodeArray: ", e2);
            return null;
        }
    }
    public static String compress(String str) {
        return Base64.encode(compressS(str));
    }
    public static byte[] compressS(String str) {
        byte[] bArr = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(str.length());
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes(StandardCharsets.UTF_8));
            gZIPOutputStream.close();
            bArr = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return bArr;
        } catch (IOException e2) {
            Log.d(TAG, "compressS: " + e2.getMessage());
            return bArr;
        }
    }
    public static String decompress(Object obj) {
        return decompressS(Base64.decode(obj.toString()));
    }
    public static String decompressS(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length == 0) {
            return sb.toString();
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream, 32);
            InputStreamReader inputStreamReader = new InputStreamReader(gZIPInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append(System.getProperty("line.separator"));
            }
            gZIPInputStream.close();
            byteArrayInputStream.close();
            inputStreamReader.close();
            bufferedReader.close();
        } catch (IOException e2) {
            Log.e(TAG, "decompressS: ", e2);
            return e2.getMessage();
        } catch (Exception e3) {
            Log.e(TAG, "decompressS: ", e3);
        } catch (OutOfMemoryError e4) {
            sb = new StringBuilder();
            Log.e(TAG, "decompressS: ", e4);
        }
        return sb.toString();
    }
    public static Bitmap resizeImageForImageView(Bitmap bitmap, int i2) {
        int i3;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (height < i2 && width < i2) {
            return bitmap;
        }
        if (height > width) {
            int i4 = i2 * (width / height);
            i3 = i2;
            i2 = i4;
        } else if (width > height) {
            i3 = i2 * (height / width);
        } else {
            if (height != width) {
                i2 = -1;
            }
            i3 = i2;
        }
        return Bitmap.createScaledBitmap(bitmap, i2, i3, false);
    }
    public static byte[] compressBitmapToJpeg(Bitmap bitmap) throws IOException {
        return compressBitmapToJpeg(bitmap, 0);
    }
    public static byte[] compressBitmapToJpeg(Bitmap bitmap, int i2) throws IOException {
        if (bitmap == null) {
            return null;
        }
        if (i2 != 0) {
            bitmap = resizeImageForImageView(bitmap, i2);
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return byteArray;
    }
    public static byte[] compressBitmapToPng(Bitmap bitmap) throws IOException {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return byteArray;
    }
    public static Bitmap deCompressBitmap(byte[] bArr) throws IOException {
        if (bArr == null) {
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        Bitmap decodeStream = BitmapFactory.decodeStream(byteArrayInputStream);
        byteArrayInputStream.close();
        return decodeStream;
    }
    public static String base64CompressImage(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        try {
            return android.util.Base64.encodeToString(bArr, android.util.Base64.DEFAULT);
        } catch (Exception e2) {
            Log.e(TAG, "base64CompressImage: ", e2);
            return "";
        }
    }
    public static Bitmap resizeBitmap(Bitmap bitmap, int i2, int i3) {
        return Bitmap.createScaledBitmap(bitmap, i2, i3, true);
    }
    public static String bytesToHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b2 = bArr[i2];
            int i3 = i2 * 2;
            char[] cArr2 = HEX_ARRAY;
            cArr[i3] = cArr2[(b2 & 255) >>> 4];
            cArr[i3 + 1] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }
}
