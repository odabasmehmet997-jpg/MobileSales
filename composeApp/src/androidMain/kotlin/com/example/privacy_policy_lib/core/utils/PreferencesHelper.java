package com.example.privacy_policy_lib.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.proje.mobilesales.BuildConfig;
import kotlin.jvm.internal.Intrinsics;
 
public final class PreferencesHelper {
    public static final PreferencesHelper INSTANCE = new PreferencesHelper();
    public static final String IS_PRIVACY_POLICY_READ = "isprivacypolicyread";
    public static final String PREF_NAME = "CommonPrefs";
    public static SharedPreferences mSharedPreferences;

    private PreferencesHelper() {
    }

    public SharedPreferences getMSharedPreferences() {
        SharedPreferences sharedPreferences = mSharedPreferences;
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mSharedPreferences");
        return null;
    }

    public void setMSharedPreferences(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<set-?>");
        mSharedPreferences = sharedPreferences;
    }

    public static void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        PreferencesHelper preferencesHelper = INSTANCE;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        preferencesHelper.setMSharedPreferences(sharedPreferences);
    }

    public void markPrivacyPolicyAsRead() {
        try {
            Object BuildConfig = null;
            setPreferences(IS_PRIVACY_POLICY_READ, BuildConfig.NETSIS_DEMO_PASSWORD);
        } catch (Exception e2) {
            Log.e("ContentValues", "markPrivacyPolicyAsRead: ", e2);
        }
    }

    private void setPreferences(String str, String str2) {
        try {
            SharedPreferences mSharedPreferences2 = getMSharedPreferences();
            Intrinsics.checkNotNull(mSharedPreferences2);
            SharedPreferences.Editor edit = mSharedPreferences2.edit();
            Intrinsics.checkNotNullExpressionValue(edit, "edit(...)");
            edit.putString(str, str2);
            edit.apply();
        } catch (Exception e2) {
            Log.e("ContentValues", "setPreferences: ", e2);
        }
    }
}
