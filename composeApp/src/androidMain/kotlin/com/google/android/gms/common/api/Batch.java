package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import com.google.android.gms.common.api.internal.BasePendingResult;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public final class Batch extends BasePendingResult<BatchResult> {
    
    public int zae;
    
    public boolean zaf;
    
    public boolean zag;
    
    public final PendingResult[] zah;
    
    public final Object zai;

    /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
    public static final class Builder {
    }

    public void cancel() {
        super.cancel();
        int i2 = 0;
        while (true) {
            final PendingResult[] pendingResultArr = zah;
            if (i2 < pendingResultArr.length) {
                pendingResultArr[i2].cancel();
                i2++;
            } else {
                return;
            }
        }
    }

    @NonNull
    public BatchResult createFailedResult(@NonNull final Status status) {
        return new BatchResult(status, zah);
    }
}
