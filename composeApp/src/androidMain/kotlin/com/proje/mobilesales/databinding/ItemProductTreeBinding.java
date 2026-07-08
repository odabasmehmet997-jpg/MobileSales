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
import com.proje.mobilesales.core.widget.TintableTextView;



public final class ItemProductTreeBinding implements ViewBinding {

   
    private final CardView rootView;

   
    public final TintableTextView txtTreeItemDesc;

    private ItemProductTreeBinding(final CardView cardView, final TintableTextView tintableTextView) {
        rootView = cardView;
        txtTreeItemDesc = tintableTextView;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemProductTreeBinding inflate(final LayoutInflater layoutInflater) {
        return ItemProductTreeBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemProductTreeBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_product_tree, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemProductTreeBinding.bind(inflate);
    }

   
    public static ItemProductTreeBinding bind(final View view) {
        final TintableTextView tintableTextView = ViewBindings.findChildViewById(view, R.id.txt_treeItemDesc);
        if (null != tintableTextView) {
            return new ItemProductTreeBinding((CardView) view, tintableTextView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.txt_treeItemDesc));
    }
}
