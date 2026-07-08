package com.proje.mobilesales.databinding;

import android.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ActivityPreferenceAllBinding implements ViewBinding {
    public final LinearLayout contentFrame;
    public final ListView list;
    private final LinearLayout rootView;
    private ActivityPreferenceAllBinding( final LinearLayout linearLayout,  final LinearLayout linearLayout2,  final ListView listView) {
        rootView = linearLayout;
        contentFrame = linearLayout2;
        list = listView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityPreferenceAllBinding inflate( final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_preference_all, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityPreferenceAllBinding.bind(inflate);
    }
    public static ActivityPreferenceAllBinding bind( final View view) {
        final LinearLayout linearLayout = (LinearLayout) view;
        final ListView listView = ViewBindings.findChildViewById(view, R.id.list);
        if (null != listView) {
            return new ActivityPreferenceAllBinding(linearLayout, linearLayout, listView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.list));
    }
}
