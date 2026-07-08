package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.view.CounterFab;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;



public final class FragmentProductListBinding implements ViewBinding {

   
    public final EmptyListBinding empty;

   
    public final EmptySearchBinding emptySearch;

   
    public final CounterFab fabCheckAllDone;

   
    public final CounterFab fabDone;

   
    public final FrameLayout frameCheckAllFab;

   
    public final FrameLayout frameFab;

   
    public final FrameLayout frameForm;

   
    public final FrameLayout frameGroup;

   
    public final AppCompatSpinner frmGroupSpinner;

   
    public final AppCompatSpinner frmSpinner;

   
    public final Guideline guideline50;

   
    public final RecyclerView rcwList;

   
    private final FrameLayout rootView;

   
    public final RecyclerView rwProductTree;

   
    public final AppBarSwipeRefreshLayout swipeLayout;

   
    public final AppCompatTextView twFabCheckAllText;

   
    public final AppCompatTextView twFabText;

    private FragmentProductListBinding(final FrameLayout frameLayout, final EmptyListBinding emptyListBinding, final EmptySearchBinding emptySearchBinding, final CounterFab counterFab, final CounterFab counterFab2, final FrameLayout frameLayout2, final FrameLayout frameLayout3, final FrameLayout frameLayout4, final FrameLayout frameLayout5, final AppCompatSpinner appCompatSpinner, final AppCompatSpinner appCompatSpinner2, final Guideline guideline, final RecyclerView recyclerView, final RecyclerView recyclerView2, final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2) {
        rootView = frameLayout;
        empty = emptyListBinding;
        emptySearch = emptySearchBinding;
        fabCheckAllDone = counterFab;
        fabDone = counterFab2;
        frameCheckAllFab = frameLayout2;
        frameFab = frameLayout3;
        frameForm = frameLayout4;
        frameGroup = frameLayout5;
        frmGroupSpinner = appCompatSpinner;
        frmSpinner = appCompatSpinner2;
        guideline50 = guideline;
        rcwList = recyclerView;
        rwProductTree = recyclerView2;
        swipeLayout = appBarSwipeRefreshLayout;
        twFabCheckAllText = appCompatTextView;
        twFabText = appCompatTextView2;
    }

    
   
    public FrameLayout getRoot() {
        return rootView;
    }

   
    public static FragmentProductListBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentProductListBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentProductListBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_product_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentProductListBinding.bind(inflate);
    }

   
    public static FragmentProductListBinding bind(final View view) {
        int i2 = R.id.empty;
        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.empty);
        if (null != findChildViewById) {
            final EmptyListBinding bind = EmptyListBinding.bind(findChildViewById);
            i2 = R.id.emptySearch;
            final View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.emptySearch);
            if (null != findChildViewById2) {
                final EmptySearchBinding bind2 = EmptySearchBinding.bind(findChildViewById2);
                i2 = R.id.fabCheckAllDone;
                final CounterFab counterFab = ViewBindings.findChildViewById(view, R.id.fabCheckAllDone);
                if (null != counterFab) {
                    i2 = R.id.fabDone;
                    final CounterFab counterFab2 = ViewBindings.findChildViewById(view, R.id.fabDone);
                    if (null != counterFab2) {
                        i2 = R.id.frameCheckAllFab;
                        final FrameLayout frameLayout = ViewBindings.findChildViewById(view, R.id.frameCheckAllFab);
                        if (null != frameLayout) {
                            i2 = R.id.frameFab;
                            final FrameLayout frameLayout2 = ViewBindings.findChildViewById(view, R.id.frameFab);
                            if (null != frameLayout2) {
                                i2 = R.id.frameForm;
                                final FrameLayout frameLayout3 = ViewBindings.findChildViewById(view, R.id.frameForm);
                                if (null != frameLayout3) {
                                    i2 = R.id.frameGroup;
                                    final FrameLayout frameLayout4 = ViewBindings.findChildViewById(view, R.id.frameGroup);
                                    if (null != frameLayout4) {
                                        i2 = R.id.frmGroupSpinner;
                                        final AppCompatSpinner appCompatSpinner = ViewBindings.findChildViewById(view, R.id.frmGroupSpinner);
                                        if (null != appCompatSpinner) {
                                            i2 = R.id.frmSpinner;
                                            final AppCompatSpinner appCompatSpinner2 = ViewBindings.findChildViewById(view, R.id.frmSpinner);
                                            if (null != appCompatSpinner2) {
                                                i2 = R.id.guideline50;
                                                final Guideline guideline = ViewBindings.findChildViewById(view, R.id.guideline50);
                                                if (null != guideline) {
                                                    i2 = R.id.rcwList;
                                                    final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rcwList);
                                                    if (null != recyclerView) {
                                                        i2 = R.id.rwProductTree;
                                                        final RecyclerView recyclerView2 = ViewBindings.findChildViewById(view, R.id.rwProductTree);
                                                        if (null != recyclerView2) {
                                                            i2 = R.id.swipe_layout;
                                                            final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout = ViewBindings.findChildViewById(view, R.id.swipe_layout);
                                                            if (null != appBarSwipeRefreshLayout) {
                                                                i2 = R.id.twFabCheckAllText;
                                                                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.twFabCheckAllText);
                                                                if (null != appCompatTextView) {
                                                                    i2 = R.id.twFabText;
                                                                    final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.twFabText);
                                                                    if (null != appCompatTextView2) {
                                                                        return new FragmentProductListBinding((FrameLayout) view, bind, bind2, counterFab, counterFab2, frameLayout, frameLayout2, frameLayout3, frameLayout4, appCompatSpinner, appCompatSpinner2, guideline, recyclerView, recyclerView2, appBarSwipeRefreshLayout, appCompatTextView, appCompatTextView2);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
