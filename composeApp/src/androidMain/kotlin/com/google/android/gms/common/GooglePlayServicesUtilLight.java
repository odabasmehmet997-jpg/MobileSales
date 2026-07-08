package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.errorprone.annotations.InlineMe;
import java.util.concurrent.atomic.AtomicBoolean;

public class GooglePlayServicesUtilLight {
    @KeepForSdk
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 12451000;
    @KeepForSdk
    @Deprecated
    static final AtomicBoolean sCanceledAvailabilityNotification = new AtomicBoolean();
    @VisibleForTesting
    static boolean zza;
    private static boolean zzb;
    private static final AtomicBoolean zzc = new AtomicBoolean();

    @KeepForSdk
    GooglePlayServicesUtilLight() {
    }

    @KeepForSdk
    @Deprecated
    public static void cancelAvailabilityErrorNotifications(@NonNull final Context context) {
        if (!GooglePlayServicesUtilLight.sCanceledAvailabilityNotification.getAndSet(true)) {
            try {
                final NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                if (null != notificationManager) {
                    notificationManager.cancel(10436);
                }
            } catch (final SecurityException e2) {
                Log.d("GooglePlayServicesUtil", "Suppressing Security Exception %s in cancelAvailabilityErrorNotifications.", e2);
            }
        }
    }

    @KeepForSdk
    @Deprecated
    public static void ensurePlayServicesAvailable(@NonNull final Context context, final int i2) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        final int isGooglePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, i2);
        if (0 != isGooglePlayServicesAvailable) {
            final Intent errorResolutionIntent = GoogleApiAvailabilityLight.getInstance().getErrorResolutionIntent(context, isGooglePlayServicesAvailable, "e");
            Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + isGooglePlayServicesAvailable);
            if (null == errorResolutionIntent) {
                throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
            }
            throw new GooglePlayServicesRepairableException(isGooglePlayServicesAvailable, "Google Play Services not available", errorResolutionIntent);
        }
    }

    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    public static int getApkVersion(@NonNull final Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (final PackageManager.NameNotFoundException unused) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }

    @NonNull
    @KeepForSdk
    @Deprecated
    public static String getErrorString(final int i2) {
        return ConnectionResult.zza(i2);
    }

    @KeepForSdk
    @Nullable
    public static Context getRemoteContext(@NonNull final Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (final PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @KeepForSdk
    @Nullable
    public static Resources getRemoteResource(@NonNull final Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (final PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    @ShowFirstParty
    @KeepForSdk
    public static boolean honorsDebugCertificates(@NonNull final Context context) {
        if (!GooglePlayServicesUtilLight.zza) {
            try {
                final PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo("com.google.android.gms", 64);
                GoogleSignatureVerifier.getInstance(context);
                GooglePlayServicesUtilLight.zzb = null != packageInfo && !GoogleSignatureVerifier.zzb(packageInfo, false) && GoogleSignatureVerifier.zzb(packageInfo, true);
                GooglePlayServicesUtilLight.zza = true;
            } catch (final PackageManager.NameNotFoundException e2) {
                Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", e2);
                GooglePlayServicesUtilLight.zza = true;
            } catch (final Throwable th) {
                GooglePlayServicesUtilLight.zza = true;
                throw th;
            }
        }
        return GooglePlayServicesUtilLight.zzb || !DeviceProperties.isUserBuild();
    }

    @ShowFirstParty
    @KeepForSdk
    @Deprecated
    public static boolean isPlayServicesPossiblyUpdating(@NonNull final Context context, final int i2) {
        if (18 == i2) {
            return true;
        }
        if (1 == i2) {
            return GooglePlayServicesUtilLight.zza(context, "com.google.android.gms");
        }
        return false;
    }

    @TargetApi(18)
    @KeepForSdk
    public static boolean isRestrictedUserProfile(@NonNull final Context context) {
        if (!PlatformVersion.isAtLeastJellyBeanMR2()) {
            return false;
        }
        final Object systemService = context.getSystemService("user");
        Preconditions.checkNotNull(systemService);
        final Bundle applicationRestrictions = ((UserManager) systemService).getApplicationRestrictions(context.getPackageName());
        return null != applicationRestrictions && "true".equals(applicationRestrictions.getString("restricted_profile"));
    }

    @KeepForSdk
    @Deprecated
    public static boolean isUserRecoverableError(final int i2) {
        return 1 == i2 || 2 == i2 || 3 == i2 || 9 == i2;
    }

    @InlineMe
    @TargetApi(19)
    @KeepForSdk
    @Deprecated
    public static boolean uidHasPackageName(@NonNull final Context context, final int i2, @NonNull final String str) {
        return UidVerifier.uidHasPackageName(context, i2, str);
    }

    @TargetApi(21)
    static boolean zza(final Context context, final String str) {
        final boolean equals = "com.google.android.gms".equals(str);
        if (PlatformVersion.isAtLeastLollipop()) {
            try {
                for (final PackageInstaller.SessionInfo appPackageName : context.getPackageManager().getPackageInstaller().getAllSessions()) {
                    if (str.equals(appPackageName.getAppPackageName())) {
                        return true;
                    }
                }
            } catch (final Exception unused) {
                return false;
            }
        }
        try {
            final ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            if (equals) {
                return applicationInfo.enabled;
            }
            return applicationInfo.enabled && !GooglePlayServicesUtilLight.isRestrictedUserProfile(context);
        } catch (final PackageManager.NameNotFoundException unused2) {
        }
        return equals;
    }

    public static int isGooglePlayServicesAvailable(@androidx.annotation.NonNull final android.content.Context r10, final int r11) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(android.content.Context, int):int");
    }
}
