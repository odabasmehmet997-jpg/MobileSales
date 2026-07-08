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
import com.proje.mobilesales.features.product.view.list.ProductOperationView;



public final class ItemProductOperationBinding implements ViewBinding {

   
    public final ProductOperationView productOperationView;

   
    private final CardView rootView;

    private ItemProductOperationBinding(final CardView cardView, final ProductOperationView productOperationView) {
        rootView = cardView;
        this.productOperationView = productOperationView;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemProductOperationBinding inflate(final LayoutInflater layoutInflater) {
        return ItemProductOperationBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemProductOperationBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_product_operation, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemProductOperationBinding.bind(inflate);
    }

   
    public static ItemProductOperationBinding bind(final View view) {
        final ProductOperationView productOperationView = ViewBindings.findChildViewById(view, R.id.product_operation_view);
        if (null != productOperationView) {
            return new ItemProductOperationBinding((CardView) view, productOperationView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.product_operation_view));
    }
}
