package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zak implements Handler.Callback {
    @VisibleForTesting
    final ArrayList zaa;
    private final zaj zab;
    private final ArrayList zac;
    private final ArrayList zad;
    private volatile boolean zae;
    private final AtomicInteger zaf;
    private boolean zag;
    private final Handler zah;
    private final Object zai;

    public boolean handleMessage(Message message) {
        int i2 = message.what;
        if (1 == i2) {
            GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) message.obj;
            synchronized (this.zai) {
                try {
                    if (this.zae && this.zab.isConnected() && this.zac.contains(connectionCallbacks)) {
                        connectionCallbacks.onConnected(null);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return true;
        }
        Log.wtf("GmsClientEvents", "Don't know how to handle message: " + i2, new Exception());
        return false;
    }

    public void zaa() {
        this.zae = false;
        this.zaf.incrementAndGet();
    }

    public void zab() {
        this.zae = true;
    }


    public void zac(com.google.android.gms.common.ConnectionResult r6) {

        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.zak.zac(com.google.android.gms.common.ConnectionResult):void");
    }

    public void zad(@Nullable Bundle bundle) {
        Preconditions.checkHandlerThread(this.zah, "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.zai) {
            Preconditions.checkState(!zag);
            zah.removeMessages(1);
            zag = true;
            Preconditions.checkState(zaa.isEmpty());
            final ArrayList arrayList = new ArrayList(zac);
            final int i2 = zaf.get();
            final Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                final GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                if (!zae || !zab.isConnected()) {
                    break;
                } else if (zaf.get() != i2) {
                    break;
                } else if (!zaa.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            zaa.clear();
            zag = false;
        }
    }

    @VisibleForTesting
    public void zae(int i2) {
        Preconditions.checkHandlerThread(this.zah, "onUnintentionalDisconnection must only be called on the Handler thread");
        this.zah.removeMessages(1);
        synchronized (this.zai) {
            zag = true;
            final ArrayList arrayList = new ArrayList(zac);
            final int i3 = zaf.get();
            final Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                final GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                if (!zae) {
                    break;
                } else if (zaf.get() != i3) {
                    break;
                } else if (zac.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i2);
                }
            }
            zaa.clear();
            zag = false;
        }
    }

    public void zai(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Preconditions.checkNotNull(onConnectionFailedListener);
        synchronized (this.zai) {
            try {
                if (!this.zad.remove(onConnectionFailedListener)) {
                    String valueOf = String.valueOf(onConnectionFailedListener);
                    Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + valueOf + " not found");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
