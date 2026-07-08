package com.google.android.gms.common.util;

import android.os.Build;
import androidx.annotation.ChecksSdkIntAtLeast;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum PlatformVersion {
    ;

    @KeepForSdk
    @ChecksSdkIntAtLeast(api = 14)
    public static boolean isAtLeastIceCreamSandwich() {
        return true;
    }

    @KeepForSdk
    @ChecksSdkIntAtLeast(api = 16)
    public static boolean isAtLeastJellyBean() {
        return true;
    }

    @KeepForSdk
    @ChecksSdkIntAtLeast(api = 18)
    public static boolean isAtLeastJellyBeanMR2() {
        return true;
    }

    @KeepForSdk
    @ChecksSdkIntAtLeast(api = 19)
    public static boolean isAtLeastKitKat() {
        return true;
    }

    @KeepForSdk
    @ChecksSdkIntAtLeast(api = 20)
    public static boolean isAtLeastKitKatWatch() {
        return true;
    }

    @KeepForSdk
    @ChecksSdkIntAtLeast(api = 21)
    public static boolean isAtLeastLollipop() {
        return true;
    }

    @KeepForSdk
    @ChecksSdkIntAtLeast(api = 24)
    public static boolean isAtLeastN() {
        return true;
    }

    @KeepForSdk
    @ChecksSdkIntAtLeast(api = 26)
    public static boolean isAtLeastO() {
        return true;
    }

    @KeepForSdk
    @ChecksSdkIntAtLeast(api = 28)
    public static boolean isAtLeastP() {
        return 28 <= Build.VERSION.SDK_INT;
    }

    @KeepForSdk
    @ChecksSdkIntAtLeast(api = 29)
    public static boolean isAtLeastQ() {
        return 29 <= Build.VERSION.SDK_INT;
    }

    @KeepForSdk
    @ChecksSdkIntAtLeast(api = 30)
    public static boolean isAtLeastR() {
        return 30 <= Build.VERSION.SDK_INT;
    }

    @KeepForSdk
    @ChecksSdkIntAtLeast(api = 31)
    public static boolean isAtLeastS() {
        return 31 <= Build.VERSION.SDK_INT;
    }
}
