package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.customer.view.list.CustomerView;

public final class ItemCustomerBinding implements ViewBinding {
    public final CustomerView customerView;
    public final LinearLayout lnCustomerRcwMenu;
    private final CardView rootView;

    private ItemCustomerBinding(final CardView cardView, final CustomerView customerView, final LinearLayout linearLayout) {
        rootView = cardView;
        this.customerView = customerView;
        lnCustomerRcwMenu = linearLayout;
    }

    public CardView getRoot() {
        return rootView;
    }

    
    public static ItemCustomerBinding inflate(final LayoutInflater layoutInflater) {
        return ItemCustomerBinding.inflate(layoutInflater, null, false);
    }

    
    public static ItemCustomerBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_customer, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemCustomerBinding.bind(inflate);
    }

    
    public static ItemCustomerBinding bind(final View view) {
        int i2 = R.id.customer_view;
        final CustomerView customerView = ViewBindings.findChildViewById(view, R.id.customer_view);
        if (null != customerView) {
            i2 = R.id.ln_customerRcwMenu;
            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_customerRcwMenu);
            if (null != linearLayout) {
                return new ItemCustomerBinding((CardView) view, customerView, linearLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
