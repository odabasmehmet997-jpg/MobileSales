package com.google.android.gms.tagmanager;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;

@VisibleForTesting
/* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
public interface ContainerHolder extends Result, Releasable {

    /* compiled from: com.google.android.gms:play-services-tagmanager-v4-impl@@18.1.1 */
    interface ContainerAvailableListener {
        void onContainerAvailable(@NonNull ContainerHolder containerHolder, @NonNull String str);
    }
}
