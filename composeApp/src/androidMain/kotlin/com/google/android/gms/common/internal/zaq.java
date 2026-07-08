package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.common.api.Result;

/**
 * @param zaa synthetic
 */ /* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
record zaq(Response zaa) implements PendingResultUtil.ResultConverter {
    public Object convert(Result result) {
        this.zaa.setResult(result);
        return this.zaa;
    }
}
