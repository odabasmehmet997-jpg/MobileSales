package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;



public final class FragmentListSwipeBinding implements ViewBinding {

   
    public final EmptyListBinding empty;

   
    public final EmptySearchBinding emptySearch;

   
    public final RecyclerView rcwList;

   
    private final FrameLayout rootView;

   
    public final AppBarSwipeRefreshLayout swipeLayout;

    private FragmentListSwipeBinding(final FrameLayout frameLayout, final EmptyListBinding emptyListBinding, final EmptySearchBinding emptySearchBinding, final RecyclerView recyclerView, final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout) {
        rootView = frameLayout;
        empty = emptyListBinding;
        emptySearch = emptySearchBinding;
        rcwList = recyclerView;
        swipeLayout = appBarSwipeRefreshLayout;
    }

    
   
    public FrameLayout getRoot() {
        return rootView;
    }

   
    public static FragmentListSwipeBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentListSwipeBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentListSwipeBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_list_swipe, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentListSwipeBinding.bind(inflate);
    }

   
    public static FragmentListSwipeBinding bind(final View view) {
        int i2 = R.id.empty;
        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.empty);
        if (null != findChildViewById) {
            final EmptyListBinding bind = EmptyListBinding.bind(findChildViewById);
            i2 = R.id.emptySearch;
            final View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.emptySearch);
            if (null != findChildViewById2) {
                final EmptySearchBinding bind2 = EmptySearchBinding.bind(findChildViewById2);
                i2 = R.id.rcwList;
                final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rcwList);
                if (null != recyclerView) {
                    i2 = R.id.swipe_layout;
                    final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = ViewBindings.findChildViewById(view, R.id.swipe_layout);
                    if (null != appBarSwipeRefreshLayout) {
                        return new FragmentListSwipeBinding((FrameLayout) view, bind, bind2, recyclerView, appBarSwipeRefreshLayout);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
