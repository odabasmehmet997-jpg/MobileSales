package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.sales.view.detail.SalesDetailLineView;



public final class ItemSalesLineBinding implements ViewBinding {

   
    private final CardView rootView;

   
    public final SalesDetailLineView salesLineView;

    private ItemSalesLineBinding(final CardView cardView, final SalesDetailLineView salesDetailLineView) {
        rootView = cardView;
        salesLineView = salesDetailLineView;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemSalesLineBinding inflate(final LayoutInflater layoutInflater) {
        return ItemSalesLineBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemSalesLineBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_sales_line, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemSalesLineBinding.bind(inflate);
    }

   
    public static ItemSalesLineBinding bind(final View view) {
        final SalesDetailLineView salesDetailLineView = ViewBindings.findChildViewById(view, R.id.sales_line_view);
        if (null != salesDetailLineView) {
            return new ItemSalesLineBinding((CardView) view, salesDetailLineView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.sales_line_view));
    }
}
