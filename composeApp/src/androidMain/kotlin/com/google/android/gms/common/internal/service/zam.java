package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.tasks.TaskCompletionSource;

/**
 * @param zaa synthetic
 */ /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public record zam(TelemetryData zaa) implements RemoteCall {
    /* synthetic */

    public void accept(Object obj, Object obj2) {
        final int i2 = zao.r8clinit;
        ((zai) ((zap) obj).getService()).zae(this.zaa);
        ((TaskCompletionSource) obj2).setResult(null);
    }
}
