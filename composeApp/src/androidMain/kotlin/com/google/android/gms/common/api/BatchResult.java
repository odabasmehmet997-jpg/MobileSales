package com.google.android.gms.common.api;

import androidx.annotation.NonNull;

public final class BatchResult implements Result {
    private final Status zaa;
    private final PendingResult[] zab;

    BatchResult(final Status status, final PendingResult[] pendingResultArr) {
        zaa = status;
        zab = pendingResultArr;
    }

    @NonNull
    public Status getStatus() {
        return zaa;
    }
}
