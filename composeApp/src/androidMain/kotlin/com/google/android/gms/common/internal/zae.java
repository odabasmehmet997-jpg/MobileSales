package com.google.android.gms.common.internal;

import android.content.Intent;
import androidx.fragment.app.Fragment;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zae extends zag {
    final Intent zaa;
    final Fragment zab;
    final int zac;

    public void zaa() {
        Intent intent = this.zaa;
        if (null != intent) {
            this.zab.startActivityForResult(intent, this.zac);
        }
    }
}
