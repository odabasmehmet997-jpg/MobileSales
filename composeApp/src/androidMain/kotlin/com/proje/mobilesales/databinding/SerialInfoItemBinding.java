package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class SerialInfoItemBinding implements ViewBinding {

   
    private final LinearLayout rootView;

   
    public final TextView txtProductStock;

   
    public final TextView txtSerialNo1;

   
    public final TextView txtSerialNo2;

    private SerialInfoItemBinding(final LinearLayout linearLayout, final TextView textView, final TextView textView2, final TextView textView3) {
        rootView = linearLayout;
        txtProductStock = textView;
        txtSerialNo1 = textView2;
        txtSerialNo2 = textView3;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static SerialInfoItemBinding inflate(final LayoutInflater layoutInflater) {
        return SerialInfoItemBinding.inflate(layoutInflater, null, false);
    }

   
    public static SerialInfoItemBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.serial_info_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return SerialInfoItemBinding.bind(inflate);
    }

   
    public static SerialInfoItemBinding bind(final View view) {
        int i2 = R.id.txt_product_stock;
        final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_product_stock);
        if (null != textView) {
            i2 = R.id.txt_serial_no_1;
            final TextView textView2 = ViewBindings.findChildViewById(view, R.id.txt_serial_no_1);
            if (null != textView2) {
                i2 = R.id.txt_serial_no_2;
                final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txt_serial_no_2);
                if (null != textView3) {
                    return new SerialInfoItemBinding((LinearLayout) view, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
