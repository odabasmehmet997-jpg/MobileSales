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



public final class FragmentListNonSwipeBinding implements ViewBinding {

   
    public final EmptyListBinding empty;

   
    public final EmptySearchBinding emptySearch;

   
    public final RecyclerView rcwList;

   
    private final FrameLayout rootView;

    private FragmentListNonSwipeBinding(final FrameLayout frameLayout, final EmptyListBinding emptyListBinding, final EmptySearchBinding emptySearchBinding, final RecyclerView recyclerView) {
        rootView = frameLayout;
        empty = emptyListBinding;
        emptySearch = emptySearchBinding;
        rcwList = recyclerView;
    }

    
   
    public FrameLayout getRoot() {
        return rootView;
    }

   
    public static FragmentListNonSwipeBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentListNonSwipeBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentListNonSwipeBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_list_non_swipe, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentListNonSwipeBinding.bind(inflate);
    }

   
    public static FragmentListNonSwipeBinding bind(final View view) {
        int i2 = R.id.empty;
        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.empty);
        if (null != findChildViewById) {
            final EmptyListBinding bind = EmptyListBinding.bind(findChildViewById);
            final View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.emptySearch);
            if (null != findChildViewById2) {
                final EmptySearchBinding bind2 = EmptySearchBinding.bind(findChildViewById2);
                final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rcwList);
                if (null != recyclerView) {
                    return new FragmentListNonSwipeBinding((FrameLayout) view, bind, bind2, recyclerView);
                }
                i2 = R.id.rcwList;
            } else {
                i2 = R.id.emptySearch;
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
