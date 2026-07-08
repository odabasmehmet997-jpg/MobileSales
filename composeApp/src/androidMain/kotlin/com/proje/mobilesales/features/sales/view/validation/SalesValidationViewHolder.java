package com.proje.mobilesales.features.sales.view.validation;

import android.view.View;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.adapter.ItemViewHolder;
import kotlin.jvm.internal.Intrinsics;

public final class SalesValidationViewHolder extends ItemViewHolder {
    private final SalesValidationView mSalesValidationView;

    public SalesValidationViewHolder(View itemView) {
        super(itemView);
        Intrinsics.checkNotNullParameter(itemView, "itemView");
        View findViewById = itemView.findViewById(R.id.sales_validation_view);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type com.proje.mobilesales.features.sales.view.validation.SalesValidationView");
        this.mSalesValidationView = (SalesValidationView) findViewById;
    }
    public SalesValidationView getMSalesValidationView() {
        return this.mSalesValidationView;
    }
}
