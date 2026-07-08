package com.proje.mobilesales.core.interfaces;

import com.proje.mobilesales.features.sales.model.SalesDetail;
public interface ConvertSalesDetail {
    void convertSalesDetail(SalesDetail salesDetail, int i2);

    SalesDetail convertSalesFicheDetailToSalesDetail();
}
