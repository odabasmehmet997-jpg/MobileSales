package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;

/**
 * @param zza synthetic
 */ /* compiled from: com.google.android.gms:play-services-measurement-impl@@20.1.1 */
record zzhm(zzhy zza) implements zzkx {

    public void zza(String str, String str2, Bundle bundle) {
        if (!TextUtils.isEmpty(str)) {
            this.zza.zzF("auto", "_err", bundle, str);
        } else {
            this.zza.zzD("auto", "_err", bundle);
        }
    }
}
