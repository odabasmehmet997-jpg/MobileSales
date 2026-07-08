package com.proje.mobilesales.features.customer.view.list;

import android.view.View;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.adapter.ItemViewHolder;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: CustomerViewHolder.kt */

public final class CustomerViewHolder extends ItemViewHolder {
    public final CustomerView mCustomerView;

    
    public CustomerViewHolder(final View itemView) {
        super(itemView);
        Intrinsics.checkNotNullParameter(itemView, "itemView");
        final View findViewById = itemView.findViewById(R.id.customer_view);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        mCustomerView = (CustomerView) findViewById;
    }
}
