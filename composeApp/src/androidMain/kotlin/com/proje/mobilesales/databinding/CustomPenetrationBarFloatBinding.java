package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class CustomPenetrationBarFloatBinding implements ViewBinding {
    public final EditText edtAmount;
    public final ImageView imgAccept;
    private final LinearLayout rootView;
    private CustomPenetrationBarFloatBinding(final LinearLayout linearLayout, final EditText editText, final ImageView imageView) {
        rootView = linearLayout;
        edtAmount = editText;
        imgAccept = imageView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static CustomPenetrationBarFloatBinding inflate(final LayoutInflater layoutInflater) {
        return CustomPenetrationBarFloatBinding.inflate(layoutInflater, null, false);
    }
    public static CustomPenetrationBarFloatBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.custom_penetration_bar_float, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return CustomPenetrationBarFloatBinding.bind(inflate);
    }
    public static CustomPenetrationBarFloatBinding bind(final View view) {
        int i2 = R.id.edt_amount;
        final EditText editText = ViewBindings.findChildViewById(view, R.id.edt_amount);
        if (null != editText) {
            i2 = R.id.img_accept;
            final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_accept);
            if (null != imageView) {
                return new CustomPenetrationBarFloatBinding((LinearLayout) view, editText, imageView);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
