package com.proje.mobilesales.core.utils;

import android.os.Environment;
import kotlin.jvm.internal.Intrinsics;

public final class StorageHelper {
    public static final StorageHelper INSTANCE = new StorageHelper();
    private static boolean externalStorageReadable;
    private static boolean externalStorageWritable;
    private StorageHelper() {
    }
    public static boolean isExternalStorageReadable() {
        INSTANCE.checkStorage();
        return externalStorageReadable;
    }
    public static boolean isExternalStorageWritable() {
        INSTANCE.checkStorage();
        return externalStorageWritable;
    }
    private void checkStorage() {
        String externalStorageState = Environment.getExternalStorageState();
        if (Intrinsics.areEqual(externalStorageState, "mounted")) {
            externalStorageWritable = true;
            externalStorageReadable = true;
        } else if (Intrinsics.areEqual(externalStorageState, "mounted") || Intrinsics.areEqual(externalStorageState, "mounted_ro")) {
            externalStorageReadable = true;
            externalStorageWritable = false;
        } else {
            externalStorageWritable = false;
            externalStorageReadable = false;
        }
    }
}
