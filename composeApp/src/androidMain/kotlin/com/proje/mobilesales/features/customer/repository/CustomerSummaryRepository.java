package com.proje.mobilesales.features.customer.repository;

import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.features.customer.model.CustomerBalance;
import com.proje.mobilesales.features.model.MonthlySales;
import com.proje.mobilesales.features.model.TopProduct;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerSummaryRepository extends BaseCustomerRepository {
    public void getCustomerSummaryTotalBalance(int i2, ResponseListener<List<CustomerBalance>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        Intrinsics.checkNotNull(baseErp, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseErp<com.proje.mobilesales.features.customer.model.CustomerBalance>");
        baseErp.getCustomerSummaryTotalBalance(i2, responseListener);
    }

    public void getCustomerSummaryTopTenProduct(int i2, ResponseListener<List<TopProduct>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        Intrinsics.checkNotNull(baseErp, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseErp<com.proje.mobilesales.features.model.TopProduct>");
        baseErp.getCustomerSummaryTopTenProduct(i2, responseListener);
    }

    public void getCustomerSummarySales(int i2, boolean z, ResponseListener<List<MonthlySales>> responseListener) {
        Intrinsics.checkNotNullParameter(responseListener, "responseListener");
        BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
        Intrinsics.checkNotNull(baseErp, "null cannot be cast to non-null type com.proje.mobilesales.core.base.BaseErp<com.proje.mobilesales.features.model.MonthlySales>");
        baseErp.getCustomerSummarySales(i2, z, responseListener);
    }
}
