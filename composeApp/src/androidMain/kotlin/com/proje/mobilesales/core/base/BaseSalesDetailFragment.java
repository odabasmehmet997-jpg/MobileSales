package com.proje.mobilesales.core.base;

import androidx.fragment.app.FragmentActivity;
import com.proje.mobilesales.core.enums.FicheMode;
import com.proje.mobilesales.features.sales.model.SalesDetail;
import com.proje.mobilesales.features.sales.model.SalesFicheUserRights;
import com.proje.mobilesales.features.sales.model.fiche.SalesFicheDetailFields;
import com.proje.mobilesales.features.sales.view.detail.SalesDetailActivity;
import kotlin.jvm.internal.Intrinsics;

public abstract class BaseSalesDetailFragment extends BaseFicheFragment {
    protected final SalesDetailActivity getSalesDetailActivity() {
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.view.detail.SalesDetailActivity");
        return (SalesDetailActivity) activity;
    }
    public final SalesDetail getSalesDetail() {
        return getSalesDetailActivity().getMSalesDetail();
    }
    public final FicheMode getmFicheMode() {
        return getSalesDetailActivity().getMFicheMode();
    }
    protected final int getSalesDetailPosition() {
        return getSalesDetailActivity().getMSalesDetailPosition();
    }
    protected final SalesFicheDetailFields getSalesFicheDetailFields() {
        return getSalesDetailActivity().getMSalesFicheDetailFields();
    }
    protected final SalesFicheUserRights getSalesFicheUserRights() {
        return getSalesDetailActivity().getSalesFicheUserRights();
    }
    protected final boolean getProductSizeHighOne() {
        return getSalesDetailActivity().getMSalesDetailListSize();
    }
    protected final int getWareHouseNr() {
        return getSalesDetailActivity().getMWareHouseNr();
    }
}
