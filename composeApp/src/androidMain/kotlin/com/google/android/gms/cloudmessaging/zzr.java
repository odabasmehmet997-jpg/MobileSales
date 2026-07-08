package com.google.android.gms.cloudmessaging;

import android.os.Bundle;
import com.google.firebase.messaging.Constants;

final class zzr extends zzp<Bundle> {
    zzr(final int i2, final int i3, final Bundle bundle) {
        super(i2, 1, bundle);
    }
    public void zza(final Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(Constants.ScionAnalytics.MessageType.DATA_MESSAGE);
        if (null == bundle2) {
            bundle2 = Bundle.EMPTY;
        }
        this.zzd(bundle2);
    }
    public boolean zzb() {
        return false;
    }
}
