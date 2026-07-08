package com.proje.mobilesales.features.product.view.list;

import android.view.View;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.adapter.ItemViewHolder;
import kotlin.jvm.internal.Intrinsics;

public final class ProductOperationViewHolder extends ItemViewHolder {
    public final ProductOperationView mProductOperationView;
    public ProductOperationViewHolder(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "itemView");
        View findViewById = view.findViewById(R.id.product_operation_view);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.mProductOperationView = (ProductOperationView) findViewById;
    }
}
