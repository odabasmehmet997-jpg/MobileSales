package com.proje.mobilesales.core.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceDataStore;
import androidx.preference.PreferenceManager;
import kotlin.jvm.internal.Intrinsics;

public final class SecureAwareDataStore extends PreferenceDataStore {
    private final Context context;
    public SecureAwareDataStore(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }
    private SharedPreferences getSecurePrefs() {
        SharedPreferences securePreferences = Preferences.getSecurePreferences(this.context);
        Intrinsics.checkNotNullExpressionValue(securePreferences, "getSecurePreferences(...)");
        return securePreferences;
    }
    private SharedPreferences getDefaultPrefs() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(...)");
        return defaultSharedPreferences;
    }
    private SharedPreferences prefsFor(String str) {
        return Preferences.SENSITIVE_KEYS.contains(str) ? getSecurePrefs() : getDefaultPrefs();
    }
    public void putString(String key, String str) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences.Editor editorEdit = prefsFor(key).edit();
        editorEdit.putString(key, str);
        editorEdit.apply();
    } 
    public String getString(String key, String str) {
        Intrinsics.checkNotNullParameter(key, "key");
        return prefsFor(key).getString(key, str);
    }
    public void putBoolean(String key, boolean z) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences.Editor editorEdit = prefsFor(key).edit();
        editorEdit.putBoolean(key, z);
        editorEdit.apply();
    }
    public boolean getBoolean(String key, boolean z) {
        Intrinsics.checkNotNullParameter(key, "key");
        return prefsFor(key).getBoolean(key, z);
    }
    public void putInt(String key, int i2) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences.Editor editorEdit = prefsFor(key).edit();
        editorEdit.putInt(key, i2);
        editorEdit.apply();
    }
    public int getInt(String key, int i2) {
        Intrinsics.checkNotNullParameter(key, "key");
        return prefsFor(key).getInt(key, i2);
    }
    public void putLong(String key, long j2) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences.Editor editorEdit = prefsFor(key).edit();
        editorEdit.putLong(key, j2);
        editorEdit.apply();
    }
    public long getLong(String key, long j2) {
        Intrinsics.checkNotNullParameter(key, "key");
        return prefsFor(key).getLong(key, j2);
    }
    public void putFloat(String key, float f2) {
        Intrinsics.checkNotNullParameter(key, "key");
        SharedPreferences.Editor editorEdit = prefsFor(key).edit();
        editorEdit.putFloat(key, f2);
        editorEdit.apply();
    }
    public float getFloat(String key, float f2) {
        Intrinsics.checkNotNullParameter(key, "key");
        return prefsFor(key).getFloat(key, f2);
    }
}
