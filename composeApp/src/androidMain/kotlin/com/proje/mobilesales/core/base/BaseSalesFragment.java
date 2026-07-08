package com.proje.mobilesales.core.base;

import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.features.sales.model.Sales;
import com.proje.mobilesales.features.sales.model.SalesFicheUserRights;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheHeaderFields;
import com.proje.mobilesales.features.sales.view.newadd.SalesActivityNew;
import kotlin.jvm.internal.Intrinsics;

public abstract class BaseSalesFragment extends BaseFicheFragment {
    public final SalesActivityNew getSalesActivity() {
        return (SalesActivityNew) getActivity();
    }
    protected final Sales getmSales() {
        if (getSalesActivity() == null) {
            return null;
        }
        SalesActivityNew salesActivity = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity);
        return salesActivity.getmSales();
    }
    protected final SalesFicheHeaderFields getSalesFicheHeaderFields() {
        if (getSalesActivity() == null) {
            return null;
        }
        SalesActivityNew salesActivity = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity);
        return salesActivity.getmSalesFicheHeaderFields();
    }
    protected final SalesFicheUserRights getSalesFicheUserRights() {
        if (getSalesActivity() == null) {
            return null;
        }
        SalesActivityNew salesActivity = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity);
        return salesActivity.getSalesFicheUserRights();
    }
    protected final SalesType getSalesType() {
        if (getSalesActivity() == null) {
            return null;
        }
        SalesActivityNew salesActivity = getSalesActivity();
        Intrinsics.checkNotNull(salesActivity);
        return salesActivity.getmCustomerOperation().getSalesType();
    }
}
