package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamite.DynamiteModule;
import java.lang.reflect.Method;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public enum ProviderInstaller {
    ;
    
    public static final GoogleApiAvailabilityLight zza = GoogleApiAvailabilityLight.getInstance();
    private static final Object zzb = new Object();
    @GuardedBy("ProviderInstaller.lock")
    private static Method zzc;
    @GuardedBy("ProviderInstaller.lock")
    private static Method zzd;

    /* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public interface ProviderInstallListener {
        void onProviderInstallFailed(int i2, @Nullable Intent intent);

        void onProviderInstalled();
    }

    public static void installIfNeeded(@NonNull final Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        Context context2;
        Preconditions.checkNotNull(context, "Context must not be null");
        ProviderInstaller.zza.verifyGooglePlayServicesIsAvailable(context, 11925000);
        synchronized (ProviderInstaller.zzb) {
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                context2 = DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.providerinstaller.dynamite").getModuleContext();
            } catch (final DynamiteModule.LoadingException e2) {
                Log.w("ProviderInstaller", "Failed to load providerinstaller module: " + e2.getMessage());
                context2 = null;
            }
            if (null != context2) {
                ProviderInstaller.zzc(context2, context, "com.google.android.gms.providerinstaller.ProviderInstallerImpl");
                return;
            }
            final long elapsedRealtime2 = SystemClock.elapsedRealtime();
            final Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
            if (null != remoteContext) {
                try {
                    if (null == zzd) {
                        final Class cls = Long.TYPE;
                        ProviderInstaller.zzd = ProviderInstaller.zzb(remoteContext, "com.google.android.gms.common.security.ProviderInstallerImpl", "reportRequestStats", new Class[]{Context.class, cls, cls});
                    }
                    ProviderInstaller.zzd.invoke((Object) null, context, Long.valueOf(elapsedRealtime), Long.valueOf(elapsedRealtime2));
                } catch (final Exception e3) {
                    Log.w("ProviderInstaller", "Failed to report request stats: " + e3.getMessage());
                }
            }
            if (null != remoteContext) {
                ProviderInstaller.zzc(remoteContext, context, "com.google.android.gms.common.security.ProviderInstallerImpl");
            } else {
                Log.e("ProviderInstaller", "Failed to get remote context");
                throw new GooglePlayServicesNotAvailableException(8);
            }
        }
    }

    private static Method zzb(final Context context, final String str, final String str2, final Class[] clsArr) throws ClassNotFoundException, NoSuchMethodException {
        return context.getClassLoader().loadClass(str).getMethod(str2, clsArr);
    }

    @GuardedBy("ProviderInstaller.lock")
    private static void zzc(final Context context, final Context context2, final String str) throws GooglePlayServicesNotAvailableException {
        try {
            if (null == zzc) {
                ProviderInstaller.zzc = ProviderInstaller.zzb(context, str, "insertProvider", new Class[]{Context.class});
            }
            ProviderInstaller.zzc.invoke((Object) null, context);
        } catch (final Exception e2) {
            final Throwable cause = e2.getCause();
            if (Log.isLoggable("ProviderInstaller", 6)) {
                Log.e("ProviderInstaller", "Failed to install provider: " + (null == cause ? e2.getMessage() : cause.getMessage()));
            }
            throw new GooglePlayServicesNotAvailableException(8);
        }
    }
}
