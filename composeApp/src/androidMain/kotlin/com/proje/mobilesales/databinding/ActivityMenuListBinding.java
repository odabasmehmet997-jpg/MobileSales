package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ActivityMenuListBinding implements ViewBinding {
    public final RecyclerView rcwMenuList;
    private final CoordinatorLayout rootView;
    private ActivityMenuListBinding(final CoordinatorLayout coordinatorLayout, final RecyclerView recyclerView) {
        rootView = coordinatorLayout;
        rcwMenuList = recyclerView;
    }
    public CoordinatorLayout getRoot() {
        return rootView;
    }
    public static ActivityMenuListBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityMenuListBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityMenuListBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_menu_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityMenuListBinding.bind(inflate);
    }
    public static ActivityMenuListBinding bind(final View view) {
        final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rcwMenuList);
        if (recyclerView == null) {
            throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.rcwMenuList));
        }
        return new ActivityMenuListBinding((CoordinatorLayout) view, recyclerView);
    }
}
