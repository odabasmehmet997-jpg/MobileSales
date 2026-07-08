package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.proje.mobilesales.R;
public final class EmptyListBinding implements ViewBinding {
    public final LinearLayout empty;
    private final LinearLayout rootView;
    private EmptyListBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2) {
        rootView = linearLayout;
        empty = linearLayout2;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static EmptyListBinding inflate(final LayoutInflater layoutInflater) {
        return EmptyListBinding.inflate(layoutInflater, null, false);
    }
    public static EmptyListBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.empty_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return EmptyListBinding.bind(inflate);
    }
    public static EmptyListBinding bind(final View view) {
        if (null == view) {
            throw new NullPointerException("rootView");
        }
        final LinearLayout linearLayout = (LinearLayout) view;
        return new EmptyListBinding(linearLayout, linearLayout);
    }
}
