package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
final class zas {
    final BasePendingResult zaa;

    zas(final BasePendingResult basePendingResult, final zar zar) {
        zaa = basePendingResult;
    }

    
    protected void finalize() throws Throwable {
        BasePendingResult.zal(zaa.zaj);
        super.finalize();
    }
}
