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
import com.proje.mobilesales.features.sales.view.list.SalesView;



public final class ItemSalesBinding implements ViewBinding {

   
    private final CardView rootView;

   
    public final SalesView salesView;

    private ItemSalesBinding(final CardView cardView, final SalesView salesView) {
        rootView = cardView;
        this.salesView = salesView;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemSalesBinding inflate(final LayoutInflater layoutInflater) {
        return ItemSalesBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemSalesBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_sales, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemSalesBinding.bind(inflate);
    }

   
    public static ItemSalesBinding bind(final View view) {
        final SalesView salesView = ViewBindings.findChildViewById(view, R.id.sales_view);
        if (null != salesView) {
            return new ItemSalesBinding((CardView) view, salesView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.sales_view));
    }
}
