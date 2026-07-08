package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.TimeUnit;

class zap(PendingResult zaa, TaskCompletionSource zab,
           PendingResultUtil.ResultConverter zac) implements PendingResult.StatusListener {
    public void onComplete(Status status) {
        if (status.isSuccess()) {
            this.zab.setResult(this.zac.convert(this.zaa.await(0, TimeUnit.MILLISECONDS)));
            return;
        }
        this.zab.setException(ApiExceptionUtil.fromStatus(status));
    }
}
