package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zaf extends zad {
    public final zaci zab;

    public zaf(final zaci zaci, final TaskCompletionSource taskCompletionSource) {
        super(3, taskCompletionSource);
        zab = zaci;
    }

    public boolean zaa(final zabq zabq) {
        return zab.zaa().zab();
    }

    @Nullable
    public Feature[] zab(final zabq zabq) {
        return zab.zaa().getRequiredFeatures();
    }

    public void zac(final zabq zabq) throws RemoteException {
        zab.zaa().registerListener(zabq.zaf(), zaa);
        final ListenerHolder.ListenerKey listenerKey = zab.zaa().getListenerKey();
        if (null != listenerKey) {
            zabq.zah().put(listenerKey, zab);
        }
    }

    public void zag(@NonNull final zaad zaad, final boolean z) {
    }
}
