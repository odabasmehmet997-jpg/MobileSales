package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.proje.mobilesales.R;



public final class LayoutProductSpinnerItemBinding implements ViewBinding {

   
    private final TextView rootView;

   
    public final TextView text1;

    private LayoutProductSpinnerItemBinding(final TextView textView, final TextView textView2) {
        rootView = textView;
        text1 = textView2;
    }

    
   
    public TextView getRoot() {
        return rootView;
    }

   
    public static LayoutProductSpinnerItemBinding inflate(final LayoutInflater layoutInflater) {
        return LayoutProductSpinnerItemBinding.inflate(layoutInflater, null, false);
    }

   
    public static LayoutProductSpinnerItemBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.layout_product_spinner_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return LayoutProductSpinnerItemBinding.bind(inflate);
    }

   
    public static LayoutProductSpinnerItemBinding bind(final View view) {
        if (null == view) {
            throw new NullPointerException("rootView");
        }
        final TextView textView = (TextView) view;
        return new LayoutProductSpinnerItemBinding(textView, textView);
    }
}
