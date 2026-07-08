package com.proje.mobilesales.databinding;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;

public final class FragmentItemReportsBinding extends ViewDataBinding {

   
    public final ConstraintLayout constLayout;

   
    public final TextView itemCode;

   
    public final ListView list;

   
    public final AppBarSwipeRefreshLayout swiperefresh;

    private FragmentItemReportsBinding(final Object obj, final View view, final int i2, final ConstraintLayout constraintLayout, final TextView textView, final ListView listView, final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout) {
        super(obj, view, i2);
        constLayout = constraintLayout;
        itemCode = textView;
        list = listView;
        swiperefresh = appBarSwipeRefreshLayout;
    }

   
    public static FragmentItemReportsBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        return FragmentItemReportsBinding.inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @SuppressLint("RestrictedApi")
   
    @Deprecated
    public static FragmentItemReportsBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z, @Nullable final Object obj) {
        return (FragmentItemReportsBinding) inflateInternal(layoutInflater, R.layout.fragment_item_reports, viewGroup, z, obj);
    }

   
    public static FragmentItemReportsBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentItemReportsBinding.inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @SuppressLint("RestrictedApi")
   
    @Deprecated
    public static FragmentItemReportsBinding inflate(final LayoutInflater layoutInflater, @Nullable final Object obj) {
        return (FragmentItemReportsBinding) inflateInternal(layoutInflater, R.layout.fragment_item_reports, null, false, obj);
    }

    public static FragmentItemReportsBinding bind(final View view) {
        return FragmentItemReportsBinding.bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentItemReportsBinding bind(final View view, @Nullable final Object obj) {
        return bind(obj, view, R.layout.fragment_item_reports);
    }

    @Override
    protected boolean onFieldChange(final int i, final Object o, final int i1) {
        return false;
    }

    @Override
    public boolean setVariable(final int i, @Nullable final Object o) {
        return false;
    }

    @Override
    protected void executeBindings() {

    }

    @Override
    public void invalidateAll() {

    }

    @Override
    public boolean hasPendingBindings() {
        return false;
    }
}
