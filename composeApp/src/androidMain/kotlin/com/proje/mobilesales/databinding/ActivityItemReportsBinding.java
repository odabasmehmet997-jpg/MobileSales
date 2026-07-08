package com.proje.mobilesales.databinding;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.appbar.AppBarLayout;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;

public abstract class ActivityItemReportsBinding extends ViewDataBinding {
    public final AppBarLayout appBarLayout;
    public final ConstraintLayout constLayout;
    public final TextView itemCode;
    public final ListView list;
    public final AppBarSwipeRefreshLayout swiperefresh;
    public final Toolbar toolbar;
    protected ActivityItemReportsBinding(final Object obj, final View view, final int i2, final AppBarLayout appBarLayout, final ConstraintLayout constraintLayout, final TextView textView, final ListView listView, final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout, final Toolbar toolbar) {
        super(obj, view, i2);
        this.appBarLayout = appBarLayout;
        constLayout = constraintLayout;
        itemCode = textView;
        list = listView;
        swiperefresh = appBarSwipeRefreshLayout;
        this.toolbar = toolbar;
    }
    public static ActivityItemReportsBinding inflate( final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        return ActivityItemReportsBinding.inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }
    @SuppressLint("RestrictedApi")
    public static ActivityItemReportsBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z, final Object obj) {
        return (ActivityItemReportsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.activity_item_reports, viewGroup, z, obj);
    }
    public static ActivityItemReportsBinding inflate( final LayoutInflater layoutInflater) {
        return ActivityItemReportsBinding.inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }
    @SuppressLint("RestrictedApi")
    public static ActivityItemReportsBinding inflate(final LayoutInflater layoutInflater, final Object obj) {
        return (ActivityItemReportsBinding) inflateInternal(layoutInflater, R.layout.activity_item_reports, null, false, obj);
    }
    public static ActivityItemReportsBinding bind( final View view) {
        return ActivityItemReportsBinding.bind(view, DataBindingUtil.getDefaultComponent());
    }
    public static ActivityItemReportsBinding bind( final View view, final Object obj) {
        return bind(obj, view, R.layout.activity_item_reports);
    }
}
