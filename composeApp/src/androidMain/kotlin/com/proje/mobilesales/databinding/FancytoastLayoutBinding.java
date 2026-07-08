package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class FancytoastLayoutBinding implements ViewBinding {
    private final RelativeLayout rootView;
    public final ImageView toastIcon;
    public final TextView toastText;
    public final LinearLayout toastType;
    private FancytoastLayoutBinding(final RelativeLayout relativeLayout, final ImageView imageView, final TextView textView, final LinearLayout linearLayout) {
        rootView = relativeLayout;
        toastIcon = imageView;
        toastText = textView;
        toastType = linearLayout;
    }
    public RelativeLayout getRoot() {
        return rootView;
    }
    public static FancytoastLayoutBinding inflate(final LayoutInflater layoutInflater) {
        return FancytoastLayoutBinding.inflate(layoutInflater, null, false);
    }
    public static FancytoastLayoutBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fancytoast_layout, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FancytoastLayoutBinding.bind(inflate);
    }
    public static FancytoastLayoutBinding bind(final View view) {
        int i2 = R.id.toast_icon;
        final ImageView imageView = ViewBindings.findChildViewById(view, R.id.toast_icon);
        if (null != imageView) {
            i2 = R.id.toast_text;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.toast_text);
            if (null != textView) {
                i2 = R.id.toast_type;
                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.toast_type);
                if (null != linearLayout) {
                    return new FancytoastLayoutBinding((RelativeLayout) view, imageView, textView, linearLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
