package com.proje.mobilesales.features.sales.view.list;

import android.view.View;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.adapter.ItemViewHolder;
import kotlin.jvm.internal.Intrinsics;

public final class SalesViewHolder extends ItemViewHolder {
    public final SalesView mSalesView;

    public SalesViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkNotNullParameter(itemView, "itemView");
        View findViewById = itemView.findViewById(R.id.sales_view);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.mSalesView = (SalesView) findViewById;
    }
}
