package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.proje.mobilesales.R;
 
public final class ActivityAboutBinding implements ViewBinding {
    public final LinearLayout contentFrame;
    private final LinearLayout rootView;
    private ActivityAboutBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2) {
        rootView = linearLayout;
        contentFrame = linearLayout2;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityAboutBinding inflate(final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_about, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
    public static ActivityAboutBinding bind(final View view) {
        if (null == view) {
            throw new NullPointerException("rootView");
        }
        final LinearLayout linearLayout = (LinearLayout) view;
        return new ActivityAboutBinding(linearLayout, linearLayout);
    }
}
