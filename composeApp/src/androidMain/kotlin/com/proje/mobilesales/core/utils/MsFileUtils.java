package com.proje.mobilesales.core.utils;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MsFileUtils {
    public static boolean copyFile(String str, String str2, String str3) {
        File file = new File(str3);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(str + str2);
            FileOutputStream fileOutputStream = new FileOutputStream(str3 + str2);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    fileInputStream.close();
                    return true;
                }
            }
        } catch (FileNotFoundException e2) {
            Log.e("tag", "File not found: " + e2.getMessage());
            return false;
        } catch (IOException e3) {
            Log.e("tag", "I/O error: " + e3.getMessage());
            return false;
        }
    }
}
