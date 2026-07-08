package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.os.ConfigurationCompat;
import com.google.android.gms.base.R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.Locale;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zac {
    @GuardedBy
    private static final SimpleArrayMap zaa = new SimpleArrayMap();
    @Nullable
    @GuardedBy
    private static Locale zab;

    public static String zaa(Context context) {
        String packageName = context.getPackageName();
        try {
            return Wrappers.packageManager(context).getApplicationLabel(packageName).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            String str = context.getApplicationInfo().name;
            return TextUtils.isEmpty(str) ? packageName : str;
        }
    }

    @NonNull
    public static String zab(Context context, int i2) {
        Resources resources = context.getResources();
        if (1 == i2) {
            return resources.getString(R.string.common_google_play_services_install_button);
        }
        if (2 == i2) {
            return resources.getString(R.string.common_google_play_services_update_button);
        }
        if (3 != i2) {
            return resources.getString(17039370);
        }
        return resources.getString(R.string.common_google_play_services_enable_button);
    }

    @NonNull
    public static String zac(Context context, int i2) {
        Resources resources = context.getResources();
        String zaa2 = zaa(context);
        if (1 == i2) {
            return resources.getString(R.string.common_google_play_services_install_text, zaa2);
        }
        if (2 != i2) {
            if (3 == i2) {
                return resources.getString(R.string.common_google_play_services_enable_text, zaa2);
            }
            if (5 == i2) {
                return zag(context, "common_google_play_services_invalid_account_text", zaa2);
            }
            if (7 == i2) {
                return zag(context, "common_google_play_services_network_error_text", zaa2);
            }
            if (9 == i2) {
                return resources.getString(R.string.common_google_play_services_unsupported_text, zaa2);
            }
            if (20 == i2) {
                return zag(context, "common_google_play_services_restricted_profile_text", zaa2);
            }
            switch (i2) {
                case 16:
                    return zag(context, "common_google_play_services_api_unavailable_text", zaa2);
                case 17:
                    return zag(context, "common_google_play_services_sign_in_failed_text", zaa2);
                case 18:
                    return resources.getString(R.string.common_google_play_services_updating_text, zaa2);
                default:
                    return resources.getString(com.google.android.gms.common.R.string.common_google_play_services_unknown_issue, zaa2);
            }
        } else if (DeviceProperties.isWearableWithoutPlayStore(context)) {
            return resources.getString(R.string.common_google_play_services_wear_update_text);
        } else {
            return resources.getString(R.string.common_google_play_services_update_text, zaa2);
        }
    }

    @NonNull
    public static String zae(Context context, int i2) {
        String str;
        if (6 == i2) {
            str = zah(context, "common_google_play_services_resolution_required_title");
        } else {
            str = zaf(context, i2);
        }
        return null == str ? context.getResources().getString(R.string.common_google_play_services_notification_ticker) : str;
    }

    @Nullable
    public static String zaf(Context context, int i2) {
        Resources resources = context.getResources();
        switch (i2) {
            case 1:
                return resources.getString(R.string.common_google_play_services_install_title);
            case 2:
                return resources.getString(R.string.common_google_play_services_update_title);
            case 3:
                return resources.getString(R.string.common_google_play_services_enable_title);
            case 4:
            case 6:
            case 18:
                return null;
            case 5:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return zah(context, "common_google_play_services_invalid_account_title");
            case 7:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return zah(context, "common_google_play_services_network_error_title");
            case 8:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case 9:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            case 10:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case 11:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case 16:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case 17:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return zah(context, "common_google_play_services_sign_in_failed_title");
            case 20:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return zah(context, "common_google_play_services_restricted_profile_title");
            default:
                Log.e("GoogleApiAvailability", "Unexpected error code " + i2);
                return null;
        }
    }

    private static String zag(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String zah = zah(context, str);
        if (null == zah) {
            zah = resources.getString(com.google.android.gms.common.R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, zah, str2);
    }

    @Nullable
    private static String zah(Context context, String str) {
        SimpleArrayMap simpleArrayMap = zaa;
        synchronized (simpleArrayMap) {
            try {
                Locale locale = ConfigurationCompat.getLocales(context.getResources().getConfiguration()).get(0);
                if (!locale.equals(zab)) {
                    simpleArrayMap.clear();
                    zab = locale;
                }
                String str2 = (String) simpleArrayMap.get(str);
                if (null != str2) {
                    return str2;
                }
                Resources remoteResource = GooglePlayServicesUtil.getRemoteResource(context);
                if (null == remoteResource) {
                    return null;
                }
                int identifier = remoteResource.getIdentifier(str, TypedValues.Custom.S_STRING, "com.google.android.gms");
                if (0 == identifier) {
                    Log.w("GoogleApiAvailability", "Missing resource: " + str);
                    return null;
                }
                String string = remoteResource.getString(identifier);
                if (TextUtils.isEmpty(string)) {
                    Log.w("GoogleApiAvailability", "Got empty resource: " + str);
                    return null;
                }
                simpleArrayMap.put(str, string);
                return string;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @NonNull
    public static String zad(Context context, int i2) {
        if (6 == i2 || 19 == i2) {
            return zag(context, "common_google_play_services_resolution_required_text", zaa(context));
        }
        return zac(context, i2);
    }
}
