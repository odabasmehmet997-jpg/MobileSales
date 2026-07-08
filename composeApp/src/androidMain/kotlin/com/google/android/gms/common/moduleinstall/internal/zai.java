package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.moduleinstall.InstallStatusListener;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @param zaa synthetic
 * @param zab synthetic
 * @param zac synthetic
 * @param zad synthetic
 * @param zae synthetic
 */ /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public record zai(zay zaa, AtomicReference zab, InstallStatusListener zac, ApiFeatureRequest zad,
                                  zaab zae) implements RemoteCall {
    public void accept(Object obj, Object obj2) {
        ((zaf) ((zaz) obj).getService()).zag(new zau(this.zaa, this.zab, (TaskCompletionSource) obj2, this.zac), this.zad, this.zae);
    }
}
