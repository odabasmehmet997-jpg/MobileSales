package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zah extends zad {
    public final ListenerHolder.ListenerKey zab;

    public zah(final ListenerHolder.ListenerKey listenerKey, final TaskCompletionSource taskCompletionSource) {
        super(4, taskCompletionSource);
        zab = listenerKey;
    }

    public boolean zaa(final zabq zabq) {
        final zaci zaci = (zaci) zabq.zah().get(zab);
        return null != zaci && zaci.zaa().zab();
    }

    @Nullable
    public Feature[] zab(final zabq zabq) {
        final zaci zaci = (zaci) zabq.zah().get(zab);
        if (null == zaci) {
            return null;
        }
        return zaci.zaa().getRequiredFeatures();
    }

    public void zac(final zabq zabq) throws RemoteException {
        final zaci zaci = (zaci) zabq.zah().remove(zab);
        if (null != zaci) {
            zaci.zab().unregisterListener(zabq.zaf(), zaa);
            zaci.zaa().clearListener();
            return;
        }
        zaa.trySetResult(Boolean.FALSE);
    }

    public void zag(@NonNull final zaad zaad, final boolean z) {
    }
}
