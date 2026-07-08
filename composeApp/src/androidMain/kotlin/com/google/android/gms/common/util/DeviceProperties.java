package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.apps.common.proguard.SideEffectFree;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum DeviceProperties {
    ;
    @Nullable
    private static Boolean zze;
    @Nullable
    private static Boolean zzf;
    @Nullable
    private static Boolean zzh;
    @Nullable
    private static Boolean zzj;

    @KeepForSdk
    public static boolean isAuto(@NonNull final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        if (null == zzj) {
            boolean z = PlatformVersion.isAtLeastO() && packageManager.hasSystemFeature("android.hardware.type.automotive");
            DeviceProperties.zzj = Boolean.valueOf(z);
        }
        return DeviceProperties.zzj.booleanValue();
    }

    @KeepForSdk
    public static boolean isUserBuild() {
        final int i2 = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        return "user".equals(Build.TYPE);
    }

    @SideEffectFree
    @TargetApi(20)
    @KeepForSdk
    public static boolean isWearable(@NonNull final Context context) {
        return DeviceProperties.zzd(context.getPackageManager());
    }

    @TargetApi(26)
    @KeepForSdk
    public static boolean isWearableWithoutPlayStore(@NonNull final Context context) {
        if (DeviceProperties.isWearable(context) && !PlatformVersion.isAtLeastN()) {
            return true;
        }
        if (DeviceProperties.zza(context)) {
            return !PlatformVersion.isAtLeastO() || PlatformVersion.isAtLeastR();
        }
        return false;
    }

    @TargetApi(21)
    public static boolean zza(@NonNull final Context context) {
        if (null == zzf) {
            boolean z = PlatformVersion.isAtLeastLollipop() && context.getPackageManager().hasSystemFeature("cn.google");
            DeviceProperties.zzf = Boolean.valueOf(z);
        }
        return DeviceProperties.zzf.booleanValue();
    }

    public static boolean zzb(@NonNull final Context context) {
        if (null == zzh) {
            boolean z = context.getPackageManager().hasSystemFeature("android.hardware.type.iot") || context.getPackageManager().hasSystemFeature("android.hardware.type.embedded");
            DeviceProperties.zzh = Boolean.valueOf(z);
        }
        return DeviceProperties.zzh.booleanValue();
    }

    @SideEffectFree
    @TargetApi(20)
    public static boolean zzd(@NonNull final PackageManager packageManager) {
        if (null == zze) {
            boolean z = PlatformVersion.isAtLeastKitKatWatch() && packageManager.hasSystemFeature("android.hardware.type.watch");
            DeviceProperties.zze = Boolean.valueOf(z);
        }
        return DeviceProperties.zze.booleanValue();
    }
}
