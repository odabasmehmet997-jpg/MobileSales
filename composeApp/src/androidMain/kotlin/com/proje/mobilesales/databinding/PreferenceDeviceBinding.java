package com.proje.mobilesales.databinding;

import android.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class PreferenceDeviceBinding implements ViewBinding {

   
    public final ImageView imgClear;

   
    private final LinearLayout rootView;

   
    public final TextView summary;

   
    public final TextView title;

   
    public final TextView txtDeviceAddress;

   
    public final TextView txtDeviceName;

    private PreferenceDeviceBinding(final LinearLayout linearLayout, final ImageView imageView, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4) {
        rootView = linearLayout;
        imgClear = imageView;
        summary = textView;
        title = textView2;
        txtDeviceAddress = textView3;
        txtDeviceName = textView4;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static PreferenceDeviceBinding inflate(final LayoutInflater layoutInflater) {
        return PreferenceDeviceBinding.inflate(layoutInflater, null, false);
    }

   
    public static PreferenceDeviceBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.preference_device, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return PreferenceDeviceBinding.bind(inflate);
    }

   
    public static PreferenceDeviceBinding bind(final View view) {
        int i2 = R.id.imgClear;
        final ImageView imageView = ViewBindings.findChildViewById(view, R.id.imgClear);
        if (null != imageView) {
            i2 = R.id.summary;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.summary);
            if (null != textView) {
                i2 = R.id.title;
                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.title);
                if (null != textView2) {
                    i2 = R.id.txtDeviceAddress;
                    final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txtDeviceAddress);
                    if (null != textView3) {
                        i2 = R.id.txtDeviceName;
                        final TextView textView4 = ViewBindings.findChildViewById(view, R.id.txtDeviceName);
                        if (null != textView4) {
                            return new PreferenceDeviceBinding((LinearLayout) view, imageView, textView, textView2, textView3, textView4);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
