package com.proje.mobilesales.core.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import androidx.documentfile.provider.DocumentFile;

import java.io.*;

import static com.proje.mobilesales.core.utils.AppUtils.getImageFileName;

public class BitmapUtils {
    private static final int OUTPUT_QUALITY_RATIO = 75;
    private static final String TAG = "com.proje.mobilesales.core.utils.BitmapUtils";
    public static Uri printToImage(Bitmap bitmap) {
        Uri uri = null;
        try {
            SharedPreferencesHelper sharedPreferencesHelper = new SharedPreferencesHelper();
            String imageFileName = getImageFileName();
            Uri printToFilePath = sharedPreferencesHelper.getPrintToFilePath();
            if (printToFilePath != null) {
                DocumentFile fromTreeUri = DocumentFile.fromTreeUri(ContextUtils.getmContext(), printToFilePath);
                if (fromTreeUri.isDirectory()) {
                    DocumentFile createFile = fromTreeUri.createFile("image/png", imageFileName);
                    OutputStream openOutputStream = ContextUtils.getmContext().getContentResolver().openOutputStream(createFile.getUri());
                    bitmap.compress(Bitmap.CompressFormat.PNG, 75, openOutputStream);
                    openOutputStream.close();
                    uri = createFile.getUri();
                }
            } else {
                sharedPreferencesHelper.removePrintToFilePath();
            }
        } catch (FileNotFoundException e2) {
            Log.e("SaveLog", "printToImage", e2);
        } catch (IOException e3) {
            Log.e("SaveLog", "printToImage", e3);
        } catch (Exception e4) {
            Log.e("SaveLog", "printToImage", e4);
        }
        return uri;
    }
    public static String saveImage(String str, Bitmap bitmap) {
        File file = new File(str);
        if (!file.exists()) {
            file.mkdir();
            Log.i(TAG, "Pdf Directory created");
        }
        File file2 = new File(file.getPath() + "/" + getImageFileName());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.PNG, 75, fileOutputStream);
            fileOutputStream.close();
        } catch (IOException e2) {
            Log.e(TAG, "saveImage: ", e2);
        }
        return file2.getPath();
    }
    public static Bitmap getBitmapFromView(View view) {
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        view.getBackground();
        canvas.drawColor(-1);
        view.draw(canvas);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
    }
    public static Bitmap scaleDown(Bitmap bitmap, int i2, int i3) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (i2 > width) {
            i2 = width;
        }
        if (i3 > height) {
            i3 = height;
        }
        String str = TAG;
        Log.d(str, "Width and height are " + width + "--" + height);
        if (width > height) {
            i3 = height / (width / i2);
        } else if (height > width) {
            i2 = width / (height / i3);
        }
        Log.d(str, "after scaling Width and height are " + i2 + "--" + i3);
        return Bitmap.createScaledBitmap(bitmap, i2, i3, true);
    }
    public static String convertBitmapToJpgBase64(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (Exception e2) {
            Log.e("BitmapUtils", "convertBitmapToBase64", e2);
            return null;
        }
    }
}
