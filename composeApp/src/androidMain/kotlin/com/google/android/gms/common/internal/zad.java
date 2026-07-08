package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zad extends zag {
    final Intent zaa;
    final Activity zab;
    final int zac;

    zad(Intent intent, Activity activity, int i2) {
        this.zaa = intent;
        this.zab = activity;
        this.zac = i2;
    }

    public void zaa() {
        Intent intent = this.zaa;
        if (null != intent) {
            this.zab.startActivityForResult(intent, this.zac);
        }
    }
}
