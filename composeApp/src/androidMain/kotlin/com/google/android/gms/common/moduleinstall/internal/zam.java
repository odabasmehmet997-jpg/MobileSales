package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.tasks.TaskCompletionSource;

/**
 * @param zaa synthetic
 * @param zab synthetic
 */ /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public record zam(zay zaa, ApiFeatureRequest zab) implements RemoteCall {
    public void accept(Object obj, Object obj2) {
        ((zaf) ((zaz) obj).getService()).zah(new zax(this.zaa, (TaskCompletionSource) obj2), this.zab);
    }
}
