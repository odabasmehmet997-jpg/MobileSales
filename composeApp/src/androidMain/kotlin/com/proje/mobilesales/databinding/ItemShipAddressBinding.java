package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.TintableTextView;



public final class ItemShipAddressBinding implements ViewBinding {

   
    public final RelativeLayout contentFrame;

   
    public final ImageButton imgCustomerShipAddress;

   
    private final CardView rootView;

   
    public final TintableTextView txtCustomerShipAddress;

    private ItemShipAddressBinding(final CardView cardView, final RelativeLayout relativeLayout, final ImageButton imageButton, final TintableTextView tintableTextView) {
        rootView = cardView;
        contentFrame = relativeLayout;
        imgCustomerShipAddress = imageButton;
        txtCustomerShipAddress = tintableTextView;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ItemShipAddressBinding inflate(final LayoutInflater layoutInflater) {
        return ItemShipAddressBinding.inflate(layoutInflater, null, false);
    }

   
    public static ItemShipAddressBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.item_ship_address, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ItemShipAddressBinding.bind(inflate);
    }

   
    public static ItemShipAddressBinding bind(final View view) {
        int i2 = R.id.content_frame;
        final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.content_frame);
        if (null != relativeLayout) {
            i2 = R.id.img_customer_ship_address;
            final ImageButton imageButton = ViewBindings.findChildViewById(view, R.id.img_customer_ship_address);
            if (null != imageButton) {
                i2 = R.id.txt_customer_ship_address;
                final TintableTextView tintableTextView = ViewBindings.findChildViewById(view, R.id.txt_customer_ship_address);
                if (null != tintableTextView) {
                    return new ItemShipAddressBinding((CardView) view, relativeLayout, imageButton, tintableTextView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
