package com.google.android.gms.cloudmessaging;

import android.os.Bundle;

final class zzo extends zzp<Void> {
    zzo(final int i2, final int i3, final Bundle bundle) {
        super(i2, 2, bundle);
    }
    public void zza(final Bundle bundle) {
        if (bundle.getBoolean("ack", false)) {
            this.zzd(null);
        } else {
            this.zzc(new zzq(4, "Invalid response to one way request", null));
        }
    }
    public boolean zzb() {
        return true;
    }
}
