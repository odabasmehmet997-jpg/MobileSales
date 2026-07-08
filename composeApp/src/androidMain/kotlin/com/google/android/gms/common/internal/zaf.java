package com.google.android.gms.common.internal;

import android.content.Intent;
import com.google.android.gms.common.api.internal.LifecycleFragment;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zaf extends zag {
    final Intent zaa;
    final LifecycleFragment zab;

    zaf(Intent intent, LifecycleFragment lifecycleFragment, int i2) {
        this.zaa = intent;
        this.zab = lifecycleFragment;
    }

    public void zaa() {
        Intent intent = this.zaa;
        if (null != intent) {
            this.zab.startActivityForResult(intent, 2);
        }
    }
}
