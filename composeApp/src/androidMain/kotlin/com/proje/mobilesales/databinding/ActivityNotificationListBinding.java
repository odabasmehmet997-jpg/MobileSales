package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ActivityNotificationListBinding implements ViewBinding {
    public final AppCompatEditText edtSearch;
    public final AppCompatImageButton imgBtnClearSearch;
    public final AppCompatImageButton imgBtnSearch;
    public final ToolbarBinding include;
    public final RecyclerView list;
    public final LoadingItemBinding loadingBar;
    public final ConstraintLayout mainArea;
    public final EmptySearchBinding noResults;
    public final SwipeRefreshLayout refreshLayout;
    private final ConstraintLayout rootView;
    private ActivityNotificationListBinding(final ConstraintLayout constraintLayout, final AppCompatEditText appCompatEditText, final AppCompatImageButton appCompatImageButton, final AppCompatImageButton appCompatImageButton2, final ToolbarBinding toolbarBinding, final RecyclerView recyclerView, final LoadingItemBinding loadingItemBinding, final ConstraintLayout constraintLayout2, final EmptySearchBinding emptySearchBinding, final SwipeRefreshLayout swipeRefreshLayout) {
        rootView = constraintLayout;
        edtSearch = appCompatEditText;
        imgBtnClearSearch = appCompatImageButton;
        imgBtnSearch = appCompatImageButton2;
        include = toolbarBinding;
        list = recyclerView;
        loadingBar = loadingItemBinding;
        mainArea = constraintLayout2;
        noResults = emptySearchBinding;
        refreshLayout = swipeRefreshLayout;
    }
    public ConstraintLayout getRoot() {
        return rootView;
    }
    public static ActivityNotificationListBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityNotificationListBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityNotificationListBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_notification_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityNotificationListBinding.bind(inflate);
    }
    public static ActivityNotificationListBinding bind(final View view) {
        int i2 = R.id.edtSearch;
        final AppCompatEditText appCompatEditText = ViewBindings.findChildViewById(view, R.id.edtSearch);
        if (null != appCompatEditText) {
            i2 = R.id.imgBtnClearSearch;
            final AppCompatImageButton appCompatImageButton = ViewBindings.findChildViewById(view, R.id.imgBtnClearSearch);
            if (null != appCompatImageButton) {
                i2 = R.id.imgBtnSearch;
                final AppCompatImageButton appCompatImageButton2 = ViewBindings.findChildViewById(view, R.id.imgBtnSearch);
                if (null != appCompatImageButton2) {
                    i2 = R.id.include;
                    final View findChildViewById = ViewBindings.findChildViewById(view, R.id.include);
                    if (null != findChildViewById) {
                        final ToolbarBinding bind = ToolbarBinding.bind(findChildViewById);
                        i2 = R.id.list;
                        final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.list);
                        if (null != recyclerView) {
                            i2 = R.id.loadingBar;
                            final View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.loadingBar);
                            if (null != findChildViewById2) {
                                final LoadingItemBinding bind2 = LoadingItemBinding.bind(findChildViewById2);
                                i2 = R.id.main_area;
                                final ConstraintLayout constraintLayout = ViewBindings.findChildViewById(view, R.id.main_area);
                                if (null != constraintLayout) {
                                    i2 = R.id.noResults;
                                    final View findChildViewById3 = ViewBindings.findChildViewById(view, R.id.noResults);
                                    if (null != findChildViewById3) {
                                        final EmptySearchBinding bind3 = EmptySearchBinding.bind(findChildViewById3);
                                        i2 = R.id.refresh_layout;
                                        final SwipeRefreshLayout swipeRefreshLayout = ViewBindings.findChildViewById(view, R.id.refresh_layout);
                                        if (null != swipeRefreshLayout) {
                                            return new ActivityNotificationListBinding((ConstraintLayout) view, appCompatEditText, appCompatImageButton, appCompatImageButton2, bind, recyclerView, bind2, constraintLayout, bind3, swipeRefreshLayout);
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
