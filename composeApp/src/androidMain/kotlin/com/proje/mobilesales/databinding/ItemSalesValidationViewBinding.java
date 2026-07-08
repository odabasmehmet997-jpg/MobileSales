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
import com.proje.mobilesales.features.sales.view.validation.SalesValidationView;



public final class ItemSalesValidationViewBinding implements ViewBinding {

   
    private final CardView rootView;

   
    public final SalesValidationView salesValidationView;

    private ItemSalesValidationViewBinding(final CardView cardView, final SalesValidationView salesValidationView) {
        rootView = cardView;
        this.salesValidationView = salesValidationView;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemSalesValidationViewBinding inflate(final LayoutInflater layoutInflater) {
        return ItemSalesValidationViewBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemSalesValidationViewBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_sales_validation_view, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemSalesValidationViewBinding.bind(inflate);
    }

   
    public static ItemSalesValidationViewBinding bind(final View view) {
        final SalesValidationView salesValidationView = ViewBindings.findChildViewById(view, R.id.sales_validation_view);
        if (null != salesValidationView) {
            return new ItemSalesValidationViewBinding((CardView) view, salesValidationView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.sales_validation_view));
    }
}
