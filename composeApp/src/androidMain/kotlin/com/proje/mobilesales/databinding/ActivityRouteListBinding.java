package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;
 
public final class ActivityRouteListBinding implements ViewBinding {
    public final AppCompatImageButton imgList;
    public final LinearLayout linearDate1;
    public final LinearLayout linearFilterDiv;
    public final RecyclerView rcwList;
    private final LinearLayout rootView;
    public final AppBarSwipeRefreshLayout swipeLayout;
    public final AppCompatTextView tvDate1;
    private ActivityRouteListBinding( final LinearLayout linearLayout,  final AppCompatImageButton appCompatImageButton,  final LinearLayout linearLayout2,  final LinearLayout linearLayout3,  final RecyclerView recyclerView,  final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout,  final AppCompatTextView appCompatTextView) {
        rootView = linearLayout;
        imgList = appCompatImageButton;
        linearDate1 = linearLayout2;
        linearFilterDiv = linearLayout3;
        rcwList = recyclerView;
        swipeLayout = appBarSwipeRefreshLayout;
        tvDate1 = appCompatTextView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityRouteListBinding inflate( final LayoutInflater layoutInflater) {
        return ActivityRouteListBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityRouteListBinding inflate( final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_route_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityRouteListBinding.bind(inflate);
    }
    public static ActivityRouteListBinding bind( final View view) {
        int i2 = R.id.imgList;
        final AppCompatImageButton appCompatImageButton = ViewBindings.findChildViewById(view, R.id.imgList);
        if (null != appCompatImageButton) {
            i2 = R.id.linearDate1;
            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearDate1);
            if (null != linearLayout) {
                i2 = R.id.linearFilterDiv;
                final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.linearFilterDiv);
                if (null != linearLayout2) {
                    i2 = R.id.rcwList;
                    final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rcwList);
                    if (null != recyclerView) {
                        i2 = R.id.swipe_layout;
                        final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = ViewBindings.findChildViewById(view, R.id.swipe_layout);
                        if (null != appBarSwipeRefreshLayout) {
                            i2 = R.id.tvDate1;
                            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvDate1);
                            if (null != appCompatTextView) {
                                return new ActivityRouteListBinding((LinearLayout) view, appCompatImageButton, linearLayout, linearLayout2, recyclerView, appBarSwipeRefreshLayout, appCompatTextView);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
