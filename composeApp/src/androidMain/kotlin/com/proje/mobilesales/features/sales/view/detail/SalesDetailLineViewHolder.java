package com.proje.mobilesales.features.sales.view.detail;

import android.view.View;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.adapter.ItemViewHolder;
import kotlin.jvm.internal.Intrinsics;

public final class SalesDetailLineViewHolder extends ItemViewHolder {
    public SalesDetailLineView mSalesDetailLineView;
    public SalesDetailLineViewHolder(final View itemView) {
        super(itemView);
        Intrinsics.checkNotNullParameter(itemView, "itemView");
        View findViewById = itemView.findViewById(R.id.sales_line_view);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.view.detail.SalesDetailLineView");
        this.mSalesDetailLineView = (SalesDetailLineView) findViewById;
    }
}
