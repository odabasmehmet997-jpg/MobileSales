package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.proje.mobilesales.R;

public final class ActivityTransferGetOptionsBinding implements ViewBinding {
    public final LinearLayout contentFrame;
    private final LinearLayout rootView;
    private ActivityTransferGetOptionsBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2) {
        rootView = linearLayout;
        contentFrame = linearLayout2;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityTransferGetOptionsBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityTransferGetOptionsBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityTransferGetOptionsBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_transfer_get_options, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityTransferGetOptionsBinding.bind(inflate);
    }
    public static ActivityTransferGetOptionsBinding bind(final View view) {
        if (null == view) {
            throw new NullPointerException("rootView");
        }
        final LinearLayout linearLayout = (LinearLayout) view;
        return new ActivityTransferGetOptionsBinding(linearLayout, linearLayout);
    }
}
