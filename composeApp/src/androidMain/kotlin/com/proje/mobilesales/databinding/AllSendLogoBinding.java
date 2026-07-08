package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.view.AnimatedExpandableListView;

public final class AllSendLogoBinding implements ViewBinding {
    public final AnimatedExpandableListView listView;
    private final LinearLayout rootView;
    private AllSendLogoBinding(final LinearLayout linearLayout, final AnimatedExpandableListView animatedExpandableListView) {
        rootView = linearLayout;
        listView = animatedExpandableListView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static AllSendLogoBinding inflate(final LayoutInflater layoutInflater) {
        return AllSendLogoBinding.inflate(layoutInflater, null, false);
    }
    public static AllSendLogoBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.all_send_logo, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return AllSendLogoBinding.bind(inflate);
    }
    public static AllSendLogoBinding bind(final View view) {
        final AnimatedExpandableListView animatedExpandableListView = ViewBindings.findChildViewById(view, R.id.listView);
        if (null != animatedExpandableListView) {
            return new AllSendLogoBinding((LinearLayout) view, animatedExpandableListView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.listView));
    }
}
