package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.HideFirstParty;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.common.zzd;

public class GoogleApiAvailabilityLight {
    @KeepForSdk
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final GoogleApiAvailabilityLight zza = new GoogleApiAvailabilityLight();

    @KeepForSdk
    GoogleApiAvailabilityLight() {
    }

    @ShowFirstParty
    @NonNull
    @KeepForSdk
    public static GoogleApiAvailabilityLight getInstance() {
        return GoogleApiAvailabilityLight.zza;
    }

    @KeepForSdk
    public void cancelAvailabilityErrorNotifications(@NonNull final Context context) {
        GooglePlayServicesUtilLight.cancelAvailabilityErrorNotifications(context);
    }

    @ShowFirstParty
    @KeepForSdk
    public int getApkVersion(@NonNull final Context context) {
        return GooglePlayServicesUtilLight.getApkVersion(context);
    }

    @ShowFirstParty
    @Nullable
    @KeepForSdk
    @Deprecated
    public Intent getErrorResolutionIntent(final int i2) {
        return this.getErrorResolutionIntent(null, i2, null);
    }

    @KeepForSdk
    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(@NonNull final Context context, final int i2, final int i3) {
        return this.getErrorResolutionPendingIntent(context, i2, i3, null);
    }

    @NonNull
    @KeepForSdk
    public String getErrorString(final int i2) {
        return GooglePlayServicesUtilLight.getErrorString(i2);
    }

    
    @KeepForSdk
    @HideFirstParty
    public int isGooglePlayServicesAvailable(@NonNull final Context context) {
        return this.isGooglePlayServicesAvailable(context, GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }

    @ShowFirstParty
    @KeepForSdk
    public boolean isPlayServicesPossiblyUpdating(@NonNull final Context context, final int i2) {
        return GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, i2);
    }

    @KeepForSdk
    public boolean isUninstalledAppPossiblyUpdating(@NonNull final Context context, @NonNull final String str) {
        return GooglePlayServicesUtilLight.zza(context, str);
    }

    @KeepForSdk
    public boolean isUserResolvableError(final int i2) {
        return GooglePlayServicesUtilLight.isUserRecoverableError(i2);
    }

    @KeepForSdk
    public void verifyGooglePlayServicesIsAvailable(@NonNull final Context context, final int i2) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context, i2);
    }

    @ShowFirstParty
    @KeepForSdk
    @Nullable
    public Intent getErrorResolutionIntent(@Nullable final Context context, final int i2, @Nullable final String str) {
        if (1 == i2 || 2 == i2) {
            if (null == context || !DeviceProperties.isWearableWithoutPlayStore(context)) {
                final StringBuilder sb = new StringBuilder();
                sb.append("gcore_");
                sb.append(GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
                sb.append("-");
                if (!TextUtils.isEmpty(str)) {
                    sb.append(str);
                }
                sb.append("-");
                if (null != context) {
                    sb.append(context.getPackageName());
                }
                sb.append("-");
                if (null != context) {
                    try {
                        sb.append(Wrappers.packageManager(context).getPackageInfo(context.getPackageName(), 0).versionCode);
                    } catch (final PackageManager.NameNotFoundException unused) {
                    }
                }
                final String sb2 = sb.toString();
                final Intent intent = new Intent("android.intent.action.VIEW");
                final Uri.Builder appendQueryParameter = Uri.parse("market://details").buildUpon().appendQueryParameter(Name.MARK, "com.google.android.gms");
                if (!TextUtils.isEmpty(sb2)) {
                    appendQueryParameter.appendQueryParameter("pcampaignid", sb2);
                }
                intent.setData(appendQueryParameter.build());
                intent.setPackage("com.android.vending");
                intent.addFlags(524288);
                return intent;
            }
            final Intent intent2 = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
            intent2.setPackage("com.google.android.wearable.app");
            return intent2;
        } else if (3 != i2) {
            return null;
        } else {
            final Uri fromParts = Uri.fromParts("package", "com.google.android.gms", null);
            final Intent intent3 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent3.setData(fromParts);
            return intent3;
        }
    }

    @ShowFirstParty
    @KeepForSdk
    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(@NonNull final Context context, final int i2, final int i3, @Nullable final String str) {
        final Intent errorResolutionIntent = this.getErrorResolutionIntent(context, i2, str);
        if (null == errorResolutionIntent) {
            return null;
        }
        return PendingIntent.getActivity(context, i3, errorResolutionIntent, zzd.zza | 134217728);
    }

    @KeepForSdk
    public int isGooglePlayServicesAvailable(@NonNull final Context context, final int i2) {
        final int isGooglePlayServicesAvailable = GooglePlayServicesUtilLight.isGooglePlayServicesAvailable(context, i2);
        if (GooglePlayServicesUtilLight.isPlayServicesPossiblyUpdating(context, isGooglePlayServicesAvailable)) {
            return 18;
        }
        return isGooglePlayServicesAvailable;
    }
}
