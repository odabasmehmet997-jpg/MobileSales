package com.google.android.gms.common.api.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zaad {
    
    public final Map zaa = Collections.synchronizedMap(new WeakHashMap());
    
    public final Map zab = Collections.synchronizedMap(new WeakHashMap());

    private void zah(final boolean z, final Status status) {
        final HashMap hashMap;
        final HashMap hashMap2;
        synchronized (zaa) {
            hashMap = new HashMap(zaa);
        }
        synchronized (zab) {
            hashMap2 = new HashMap(zab);
        }
        for (final Map.Entry entry : hashMap.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((BasePendingResult) entry.getKey()).forceFailureUnlessReady(status);
            }
        }
        for (final Map.Entry entry2 : hashMap2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((TaskCompletionSource) entry2.getKey()).trySetException(new ApiException(status));
            }
        }
    }

    
    public void zac(final BasePendingResult basePendingResult, final boolean z) {
        zaa.put(basePendingResult, Boolean.valueOf(z));
        basePendingResult.addStatusListener(new zaab(this, basePendingResult));
    }

    
    public void zad(final TaskCompletionSource taskCompletionSource, final boolean z) {
        zab.put(taskCompletionSource, Boolean.valueOf(z));
        taskCompletionSource.getTask().addOnCompleteListener(new zaac(this, taskCompletionSource));
    }

    
    public void zae(final int i2, @Nullable final String str) {
        final StringBuilder sb = new StringBuilder("The connection to Google Play services was lost");
        if (1 == i2) {
            sb.append(" due to service disconnection.");
        } else if (3 == i2) {
            sb.append(" due to dead object exception.");
        }
        if (null != str) {
            sb.append(" Last reason for disconnect: ");
            sb.append(str);
        }
        this.zah(true, new Status(20, sb.toString()));
    }

    public void zaf() {
        this.zah(false, GoogleApiManager.zaa);
    }

    
    public boolean zag() {
        return !zaa.isEmpty() || !zab.isEmpty();
    }
}
