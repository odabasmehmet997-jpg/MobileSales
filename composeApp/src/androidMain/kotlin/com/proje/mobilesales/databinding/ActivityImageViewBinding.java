package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;
 
public final class ActivityImageViewBinding implements ViewBinding {
    public final FrameLayout contentFrame;
    public final ImageView imgItem;
    private final FrameLayout rootView;
    public final AppBarSwipeRefreshLayout swipeLayout;
    private ActivityImageViewBinding( final FrameLayout frameLayout,  final FrameLayout frameLayout2,  final ImageView imageView,  final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout) {
        rootView = frameLayout;
        contentFrame = frameLayout2;
        imgItem = imageView;
        swipeLayout = appBarSwipeRefreshLayout;
    }
    public FrameLayout getRoot() {
        return rootView;
    }
    public static ActivityImageViewBinding inflate( final LayoutInflater layoutInflater) {
        return ActivityImageViewBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityImageViewBinding inflate( final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_image_view, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityImageViewBinding.bind(inflate);
    }
    public static ActivityImageViewBinding bind( final View view) {
        int i2 = R.id.content_frame;
        final FrameLayout frameLayout = ViewBindings.findChildViewById(view, R.id.content_frame);
        if (null != frameLayout) {
            i2 = R.id.img_item;
            final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_item);
            if (null != imageView) {
                i2 = R.id.swipe_layout;
                final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = ViewBindings.findChildViewById(view, R.id.swipe_layout);
                if (null != appBarSwipeRefreshLayout) {
                    return new ActivityImageViewBinding((FrameLayout) view, frameLayout, imageView, appBarSwipeRefreshLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
