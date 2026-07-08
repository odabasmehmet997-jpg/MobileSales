package com.proje.mobilesales.features.product.repository;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import kotlin.jvm.internal.Intrinsics;

public abstract class ProductSerialRepository extends BaseProductRepository implements IProductSerialRepository {
    public void getProductSerialInfo(String str, ResponseListener<?> responseListener) {
        Intrinsics.checkNotNullParameter(str, "itemCode");
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        getBaseErp().getProductSerialInfo(str, responseListener);
    }
}
