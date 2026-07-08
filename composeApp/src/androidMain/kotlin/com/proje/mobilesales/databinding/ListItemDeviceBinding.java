package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ListItemDeviceBinding implements ViewBinding {

   
    public final Button btnPair;

   
    private final RelativeLayout rootView;

   
    public final TextView tvAddress;

   
    public final TextView tvName;

    private ListItemDeviceBinding(final RelativeLayout relativeLayout, final Button button, final TextView textView, final TextView textView2) {
        rootView = relativeLayout;
        btnPair = button;
        tvAddress = textView;
        tvName = textView2;
    }

    
   
    public RelativeLayout getRoot() {
        return rootView;
    }

   
    public static ListItemDeviceBinding inflate(final LayoutInflater layoutInflater) {
        return ListItemDeviceBinding.inflate(layoutInflater, null, false);
    }

   
    public static ListItemDeviceBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.list_item_device, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ListItemDeviceBinding.bind(inflate);
    }

   
    public static ListItemDeviceBinding bind(final View view) {
        int i2 = R.id.btn_pair;
        final Button button = ViewBindings.findChildViewById(view, R.id.btn_pair);
        if (null != button) {
            i2 = R.id.tv_address;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.tv_address);
            if (null != textView) {
                i2 = R.id.tv_name;
                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.tv_name);
                if (null != textView2) {
                    return new ListItemDeviceBinding((RelativeLayout) view, button, textView, textView2);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
