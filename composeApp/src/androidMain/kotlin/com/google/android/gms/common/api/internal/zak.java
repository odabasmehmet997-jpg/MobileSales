package com.google.android.gms.common.api.internal;

import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class zak extends zap {
    private final SparseArray zad;

    @Nullable
    private zaj zai(final int i2) {
        if (zad.size() <= i2) {
            return null;
        }
        final SparseArray sparseArray = zad;
        return (zaj) sparseArray.get(sparseArray.keyAt(i2));
    }

    public void dump(final String str, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] strArr) {
        for (int i2 = 0; i2 < zad.size(); i2++) {
            final zaj zai = this.zai(i2);
            if (null != zai) {
                printWriter.append(str).append("GoogleApiClient #").print(zai.zaa());
                printWriter.println(":");
                zai.zab().dump(str + "  ", fileDescriptor, printWriter, strArr);
            }
        }
    }

    public void onStart() {
        super.onStart();
        final SparseArray sparseArray = zad;
        final boolean z = zaa;
        final String valueOf = String.valueOf(sparseArray);
        Log.d("AutoManageHelper", "onStart " + z + " " + valueOf);
        if (null == this.zab.get()) {
            for (int i2 = 0; i2 < zad.size(); i2++) {
                final zaj zai = this.zai(i2);
                if (null != zai) {
                    zai.zab().connect();
                }
            }
        }
    }

    public void onStop() {
        super.onStop();
        for (int i2 = 0; i2 < zad.size(); i2++) {
            final zaj zai = this.zai(i2);
            if (null != zai) {
                zai.zab().disconnect();
            }
        }
    }

    
    public void zab(final ConnectionResult connectionResult, final int i2) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (0 > i2) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        final zaj zaj = (zaj) zad.get(i2);
        if (null != zaj) {
            this.zae(i2);
            final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zaj.zac();
            if (null != onConnectionFailedListener) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    
    public void zac() {
        for (int i2 = 0; i2 < zad.size(); i2++) {
            final zaj zai = this.zai(i2);
            if (null != zai) {
                zai.zab().connect();
            }
        }
    }

    public void zae(final int i2) {
        final zaj zaj = (zaj) zad.get(i2);
        zad.remove(i2);
        if (null != zaj) {
            zaj.zab().unregisterConnectionFailedListener(zaj);
            zaj.zab().disconnect();
        }
    }
}
