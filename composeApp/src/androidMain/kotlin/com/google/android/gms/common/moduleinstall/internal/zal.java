package com.google.android.gms.common.moduleinstall.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

public class zal(zay zaa, ApiFeatureRequest zab) implements RemoteCall {
    public void accept(Object obj, Object obj2) {
        try {
            ((zaf) ((zaz) obj).getService()).zae(new zar(this.zaa, (TaskCompletionSource) obj2), this.zab);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
