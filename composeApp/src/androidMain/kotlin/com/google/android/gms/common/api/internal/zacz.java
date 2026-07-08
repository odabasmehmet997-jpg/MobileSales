package com.google.android.gms.common.api.internal;

import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.base.zau;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zacz extends zau {
    final zada zaa;

    public void handleMessage(final Message message) {
        final int i2 = message.what;
        if (0 == i2) {
            final PendingResult pendingResult = (PendingResult) message.obj;
            synchronized (zaa.zae) {
                try {
                    final zada zada = Preconditions.checkNotNull(zaa.zab);
                    if (null == pendingResult) {
                        zada.zaj(new Status(13, "Transform returned null"));
                    } else if (pendingResult instanceof zacp) {
                        zada.zaj(((zacp) pendingResult).zaa());
                    } else {
                        zada.zai(pendingResult);
                    }
                } catch (final Throwable th) {
                    throw th;
                }
            }
        } else if (1 != i2) {
            Log.e("TransformedResultImpl", "TransformationResultHandler received unknown message type: " + i2);
        } else {
            final RuntimeException runtimeException = (RuntimeException) message.obj;
            Log.e("TransformedResultImpl", "Runtime exception on the transformation worker thread: " + runtimeException.getMessage());
            throw runtimeException;
        }
    }
}
