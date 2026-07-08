package com.proje.mobilesales.features.product.view.detail;

import com.example.privacy_policy_lib.core.model.GetCurrentApprovedAgreementContentHashByTokenResponse;
import com.proje.mobilesales.features.product.model.ProductDetail;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;

public final class ProductDetailFragmentonPriceLoads1 extends Lambda implements Function2<ProductDetail.ItemPrice, ProductDetail.ItemPrice, Integer> {
    public static final ProductDetailFragmentonPriceLoads1 INSTANCE = new ProductDetailFragmentonPriceLoads1();

    ProductDetailFragmentonPriceLoads1() {
        super(2);
    }

    public Unit invoke(ProductDetail.ItemPrice itemPrice, ProductDetail.ItemPrice itemPrice2) {
        Intrinsics.checkNotNullParameter(itemPrice, "b1");
        Intrinsics.checkNotNullParameter(itemPrice2, "b2");
        return Integer.valueOf(itemPrice2.getBegDate() - itemPrice.getBegDate());
    }

    @Override
    public Integer invoke(CoroutineScope p1, Continuation<? super Result<GetCurrentApprovedAgreementContentHashByTokenResponse>> p2) {
        return 0;
    }
}
