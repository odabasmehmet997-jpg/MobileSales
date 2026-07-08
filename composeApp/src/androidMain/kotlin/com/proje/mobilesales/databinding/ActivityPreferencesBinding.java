package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ActivityPreferencesBinding implements ViewBinding {
    public final FrameLayout contentFrame;
    private final LinearLayout rootView;
    private ActivityPreferencesBinding( final LinearLayout linearLayout,  final FrameLayout frameLayout) {
        rootView = linearLayout;
        contentFrame = frameLayout;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityPreferencesBinding inflate( final LayoutInflater layoutInflater) {
        return ActivityPreferencesBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityPreferencesBinding inflate( final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_preferences, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityPreferencesBinding.bind(inflate);
    }
    public static ActivityPreferencesBinding bind( final View view) {
        final FrameLayout frameLayout = ViewBindings.findChildViewById(view, R.id.content_frame);
        if (null != frameLayout) {
            return new ActivityPreferencesBinding((LinearLayout) view, frameLayout);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.content_frame));
    }
}
