package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

final class zaan extends zabg {
    final BaseGmsClient.ConnectionProgressReportCallbacks zaa;

    zaan(final zaao zaao, final zabf zabf, final BaseGmsClient.ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        super(zabf);
        zaa = connectionProgressReportCallbacks;
    }
    public void zaa() {
        zaa.onReportServiceBinding(new ConnectionResult(16, null));
    }
}
