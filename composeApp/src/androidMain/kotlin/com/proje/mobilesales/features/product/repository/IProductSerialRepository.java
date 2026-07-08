package com.proje.mobilesales.features.product.repository;

import com.proje.mobilesales.core.interfaces.ResponseListener;

public interface IProductSerialRepository extends IBaseProductRepository {
    void getProductSerialInfo(String str, ResponseListener<?> responseListener);
}
