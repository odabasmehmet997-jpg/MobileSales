package com.proje.mobilesales.features.model;

import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.features.product.model.ProductListParameter;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public record AlternativeProductListOptions(SalesType salesType, int customerRef, String paymentTradeGroup,
                                            int warehouseNumber, int paymentRef, int divisionNumber,
                                            ProductListParameter productListParameter,
                                            List<KeyValuePair> logicalRefList) {
    
    public AlternativeProductListOptions(final SalesType salesType, final int customerRef, final String paymentTradeGroup, final int warehouseNumber, final int paymentRef, final int divisionNumber, final ProductListParameter productListParameter, final List<? extends KeyValuePair> logicalRefList) {
        Intrinsics.checkNotNullParameter(salesType, "salesType");
        Intrinsics.checkNotNullParameter(paymentTradeGroup, "paymentTradeGroup");
        Intrinsics.checkNotNullParameter(productListParameter, "productListParameter");
        Intrinsics.checkNotNullParameter(logicalRefList, "logicalRefList");
        this.salesType = salesType;
        this.customerRef = customerRef;
        this.paymentTradeGroup = paymentTradeGroup;
        this.warehouseNumber = warehouseNumber;
        this.paymentRef = paymentRef;
        this.divisionNumber = divisionNumber;
        this.productListParameter = productListParameter;
        this.logicalRefList = (List<KeyValuePair>) logicalRefList;
    }
}
