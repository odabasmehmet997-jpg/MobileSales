package com.google.android.gms.internal.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zao extends ContextCompat {
    @ResultIgnorabilityUnspecified
    @Deprecated
    @Nullable
    public static Intent zaa(final Context context, @Nullable final BroadcastReceiver broadcastReceiver, final IntentFilter intentFilter) {
        if (!zan.zaa()) {
            return context.registerReceiver(broadcastReceiver, intentFilter);
        }
        return context.registerReceiver(broadcastReceiver, intentFilter, !zan.zaa() ? 0 : 2);
    }
}
