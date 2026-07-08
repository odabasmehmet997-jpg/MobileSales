package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;

public interface LifecycleFragment {
    void addCallback(final String str, LifecycleCallback lifecycleCallback);

    <T extends LifecycleCallback> T getCallbackOrNull(final String str, final Class<T> cls);

    Activity getLifecycleActivity();

    void startActivityForResult(final Intent intent, final int i2);
}
