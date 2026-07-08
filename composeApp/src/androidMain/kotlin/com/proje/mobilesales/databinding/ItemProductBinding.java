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
import com.proje.mobilesales.features.product.view.list.ProductView;



public final class ItemProductBinding implements ViewBinding {

   
    public final ProductView productView;

   
    private final CardView rootView;

    private ItemProductBinding(final CardView cardView, final ProductView productView) {
        rootView = cardView;
        this.productView = productView;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemProductBinding inflate(final LayoutInflater layoutInflater) {
        return ItemProductBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemProductBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_product, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemProductBinding.bind(inflate);
    }

   
    public static ItemProductBinding bind(final View view) {
        final ProductView productView = ViewBindings.findChildViewById(view, R.id.product_view);
        if (null != productView) {
            return new ItemProductBinding((CardView) view, productView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.product_view));
    }
}
