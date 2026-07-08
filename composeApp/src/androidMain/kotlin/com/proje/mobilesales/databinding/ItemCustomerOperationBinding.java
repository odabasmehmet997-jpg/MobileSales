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



public final class ItemCustomerOperationBinding implements ViewBinding {

   
    private final CardView rootView;

   
    public final TintableTextView txtCustomerOperation;

    private ItemCustomerOperationBinding(final CardView cardView, final TintableTextView tintableTextView) {
        rootView = cardView;
        txtCustomerOperation = tintableTextView;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemCustomerOperationBinding inflate(final LayoutInflater layoutInflater) {
        return ItemCustomerOperationBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemCustomerOperationBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_customer_operation, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemCustomerOperationBinding.bind(inflate);
    }

   
    public static ItemCustomerOperationBinding bind(final View view) {
        final TintableTextView tintableTextView = ViewBindings.findChildViewById(view, R.id.txt_customer_operation);
        if (null != tintableTextView) {
            return new ItemCustomerOperationBinding((CardView) view, tintableTextView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.txt_customer_operation));
    }
}
