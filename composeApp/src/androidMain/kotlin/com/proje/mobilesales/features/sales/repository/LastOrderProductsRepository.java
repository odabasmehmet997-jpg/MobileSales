package com.proje.mobilesales.features.sales.repository;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import kotlin.jvm.internal.Intrinsics;

public final class LastOrderProductsRepository extends BaseSalesRepository {
    public void getLastOrderProductList(final int i2, final boolean z, final ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        this.getBaseErp().getLastOrderProducts(i2, z, responseListener);
    }
}
