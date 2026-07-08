package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.errorprone.annotations.concurrent.GuardedBy;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum zzah {
    ;
    private static final Object zza = new Object();
    @GuardedBy
    private static boolean zzb;
    @Nullable
    private static String zzc;
    private static int zzd;

    public static int zza(Context context) {
        zzc(context);
        return zzd;
    }

    private static void zzc(Context context) {
        synchronized (zza) {
            try {
                if (!zzb) {
                    zzb = true;
                    Bundle bundle = Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128).metaData;
                    if (null != bundle) {
                        zzc = bundle.getString("com.google.app.id");
                        zzd = bundle.getInt("com.google.android.gms.version");
                    }
                }
            } catch (PackageManager.NameNotFoundException e2) {
                Log.wtf("MetadataValueReader", "This should never happen.", e2);
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
