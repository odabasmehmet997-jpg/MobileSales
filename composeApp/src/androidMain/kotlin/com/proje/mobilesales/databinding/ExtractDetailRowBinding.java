package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ExtractDetailRowBinding implements ViewBinding {
    public final LinearLayout linearMaterial2;
    private final LinearLayout rootView;
    public final AppCompatTextView tvField1;
    public final AppCompatTextView tvField2;
    public final AppCompatTextView tvField3;
    public final AppCompatTextView tvField4;
    private ExtractDetailRowBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4) {
        rootView = linearLayout;
        linearMaterial2 = linearLayout2;
        tvField1 = appCompatTextView;
        tvField2 = appCompatTextView2;
        tvField3 = appCompatTextView3;
        tvField4 = appCompatTextView4;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ExtractDetailRowBinding inflate(final LayoutInflater layoutInflater) {
        return ExtractDetailRowBinding.inflate(layoutInflater, null, false);
    }
    public static ExtractDetailRowBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.extract_detail_row, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ExtractDetailRowBinding.bind(inflate);
    }
    public static ExtractDetailRowBinding bind(final View view) {
        int i2 = R.id.linearMaterial2;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
        if (null != linearLayout) {
            i2 = R.id.tvField1;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvField1);
            if (null != appCompatTextView) {
                i2 = R.id.tvField2;
                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvField2);
                if (null != appCompatTextView2) {
                    i2 = R.id.tvField3;
                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvField3);
                    if (null != appCompatTextView3) {
                        i2 = R.id.tvField4;
                        final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvField4);
                        if (null != appCompatTextView4) {
                            return new ExtractDetailRowBinding((LinearLayout) view, linearLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
