package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import org.json.JSONException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Storage {
    private static final Lock zaa = new ReentrantLock();
    private static Storage zab;
    private final Lock zac = new ReentrantLock();
    private final SharedPreferences zad;
    Storage(final Context context) {
        zad = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }
    public static Storage getInstance( final Context context) {
        Preconditions.checkNotNull(context);
        final Lock lock = Storage.zaa;
        lock.lock();
        try {
            if (null == zab) {
                Storage.zab = new Storage(context.getApplicationContext());
            }
            final Storage storage = Storage.zab;
            lock.unlock();
            return storage;
        } catch (final Throwable th) {
            Storage.zaa.unlock();
            throw th;
        }
    }
    private static   String zae(final String str, final String str2) {
        return str + ":" + str2;
    }
    public GoogleSignInAccount getSavedDefaultGoogleSignInAccount() {
        final String zaa2;
        final String zaa3 = this.zaa("defaultGoogleSignInAccount");
        if (TextUtils.isEmpty(zaa3) || null == (zaa2 = zaa(zae("googleSignInAccount", zaa3)))) {
            return null;
        }
        try {
            return GoogleSignInAccount.zab(zaa2);
        } catch (final JSONException unused) {
            return null;
        }
    }
    public final String zaa( final String str) {
        zac.lock();
        try {
            return zad.getString(str, null);
        } finally {
            zac.unlock();
        }
    }
    public final void zab( final String str) {
        zac.lock();
        try {
            zad.edit().remove(str).apply();
        } finally {
            zac.unlock();
        }
    }
    public final void zac() {
        final String zaa2 = this.zaa("defaultGoogleSignInAccount");
        this.zab("defaultGoogleSignInAccount");
        if (!TextUtils.isEmpty(zaa2)) {
            this.zab(Storage.zae("googleSignInAccount", zaa2));
            this.zab(Storage.zae("googleSignInOptions", zaa2));
        }
    }
}
