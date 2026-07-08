package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.WorkSource;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.Method;

@KeepForSdk
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum WorkSourceUtil {
    ;
    private static final int zza = Process.myUid();
    private static final Method zzb;
    private static final Method zzc;
    private static final Method zzd;
    private static final Method zze;
    private static final Method zzf;
    private static final Method zzg;
    private static final Method zzh;
    private static final Method zzi;
    @GuardedBy("WorkSourceUtil.class")
    private static Boolean zzj;

    static {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.WorkSourceUtil.<clinit>():void");
    }

    public static void add(@NonNull final WorkSource workSource, final int i2, @NonNull String str) {
        final Method method = WorkSourceUtil.zzc;
        if (null != method) {
            if (null == str) {
                str = "";
            }
            try {
                method.invoke(workSource, Integer.valueOf(i2), str);
            } catch (final Exception e2) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
            }
        } else {
            final Method method2 = WorkSourceUtil.zzb;
            if (null != method2) {
                try {
                    method2.invoke(workSource, Integer.valueOf(i2));
                } catch (final Exception e3) {
                    Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e3);
                }
            }
        }
    }

    @NonNull
    @KeepForSdk
    public static WorkSource fromPackage(@NonNull final Context context, @NonNull final String str) {
        if (!(null == context || null == context.getPackageManager() || null == str)) {
            try {
                final ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(str, 0);
                if (null == applicationInfo) {
                    Log.e("WorkSourceUtil", "Could not get applicationInfo from package: " + str);
                    return null;
                }
                final int i2 = applicationInfo.uid;
                final WorkSource workSource = new WorkSource();
                WorkSourceUtil.add(workSource, i2, str);
                return workSource;
            } catch (final PackageManager.NameNotFoundException unused) {
                Log.e("WorkSourceUtil", "Could not find package: " + str);
            }
        }
        return null;
    }

    @KeepForSdk
    public static synchronized boolean hasWorkSourcePermission(@NonNull final Context context) {
        synchronized (WorkSourceUtil.class) {
            final Boolean bool = WorkSourceUtil.zzj;
            if (null != bool) {
                final boolean booleanValue = bool.booleanValue();
                return booleanValue;
            }
            boolean z = false;
            if (null == context) {
                return false;
            }
            if (0 == ContextCompat.checkSelfPermission(context, "android.permission.UPDATE_DEVICE_STATS")) {
                z = true;
            }
            WorkSourceUtil.zzj = Boolean.valueOf(z);
            return z;
        }
    }

    @KeepForSdk
    public static boolean isEmpty(@NonNull final WorkSource workSource) {
        final Method method = WorkSourceUtil.zzi;
        if (null != method) {
            try {
                final Object invoke = method.invoke(workSource, (Object[]) null);
                Preconditions.checkNotNull(invoke);
                return ((Boolean) invoke).booleanValue();
            } catch (final Exception e2) {
                Log.e("WorkSourceUtil", "Unable to check WorkSource emptiness", e2);
            }
        }
        return 0 == size(workSource);
    }

    @KeepForSdk
    public static int size(@NonNull final WorkSource workSource) {
        final Method method = WorkSourceUtil.zzd;
        if (null == method) {
            return 0;
        }
        try {
            final Object invoke = method.invoke(workSource, (Object[]) null);
            Preconditions.checkNotNull(invoke);
            return ((Integer) invoke).intValue();
        } catch (final Exception e2) {
            Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", e2);
            return 0;
        }
    }
}
