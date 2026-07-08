package com.proje.mobilesales.core.searchdialog;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public abstract class BaseSearchDialogCompat<T extends Searchable> extends AppCompatDialog implements Filterable {
    private RecyclerView.Adapter mAdapter;
    private Filter mFilter;
    protected boolean mFilterAutomatically;
    private FilterResultListener<T> mFilterResultListener;
    private ArrayList<T> mItems;
    private OnPerformFilterListener mOnPerformFilterListener;

    @LayoutRes
    protected abstract int getLayoutResId();

    @IdRes
    protected abstract int getRecyclerViewId();

    @IdRes
    protected abstract int getSearchBoxId();

    protected abstract void getView(View view);

    protected BaseSearchDialogCompat(final Context context, final ArrayList<T> arrayList, final Filter filter, final RecyclerView.Adapter adapter, final FilterResultListener filterResultListener) {
        this(context);
        mItems = arrayList;
        mFilter = filter;
        mAdapter = adapter;
        mFilterResultListener = filterResultListener;
    }

    protected BaseSearchDialogCompat(final Context context, final ArrayList<T> arrayList, final Filter filter, final RecyclerView.Adapter adapter, final FilterResultListener filterResultListener, final int i2) {
        this(context, i2);
        mItems = arrayList;
        mFilter = filter;
        mAdapter = adapter;
        mFilterResultListener = filterResultListener;
    }

    protected BaseSearchDialogCompat(final Context context) {
        super(context);
        mFilterAutomatically = true;
    }

    protected BaseSearchDialogCompat(final Context context, final int i2) {
        super(context, i2);
        mFilterAutomatically = true;
    }

    @Override // androidx.appcompat.app.AppCompatDialog, androidx.activity.ComponentDialog, android.app.Dialog
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.supportRequestWindowFeature(1);
        final View inflate = LayoutInflater.from(this.getContext()).inflate(this.getLayoutResId(), null);
        this.getView(inflate);
        final EditText editText = inflate.findViewById(this.getSearchBoxId());
        final RecyclerView recyclerView = inflate.findViewById(this.getRecyclerViewId());
        editText.addTextChangedListener(new TextWatcher() { // from class: com.proje.mobilesales.core.searchdialog.BaseSearchDialogCompat.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(final Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(final CharSequence charSequence, final int i2, final int i3, final int i4) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(final CharSequence charSequence, final int i2, final int i3, final int i4) {
                if (isFilterAutomatically()) {
                    getFilter().filter(charSequence);
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(), 1, false));
        recyclerView.setAdapter(mAdapter);
    }

    @Override // android.widget.Filterable
    public Filter getFilter() {
        if (null == this.mFilter) {
            mFilter = new SimpleSearchFilter(mItems, mFilterResultListener, false, 0.0f);
        }
        return mFilter;
    }

    public BaseSearchDialogCompat setFilter(final Filter filter) {
        mFilter = filter;
        return this;
    }

    public ArrayList<T> getItems() {
        return mItems;
    }

    public BaseSearchDialogCompat setItems(final ArrayList<T> arrayList) {
        mItems = arrayList;
        return this;
    }

    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    public BaseSearchDialogCompat setAdapter(final RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        return this;
    }

    public FilterResultListener<T> getFilterResultListener() {
        return mFilterResultListener;
    }

    public BaseSearchDialogCompat setFilterResultListener(final FilterResultListener<T> filterResultListener) {
        mFilterResultListener = filterResultListener;
        return this;
    }

    public OnPerformFilterListener getOnPerformFilterListener() {
        return mOnPerformFilterListener;
    }

    public BaseSearchDialogCompat setOnPerformFilterListener(final OnPerformFilterListener onPerformFilterListener) {
        mOnPerformFilterListener = onPerformFilterListener;
        return this;
    }

    public boolean isFilterAutomatically() {
        return mFilterAutomatically;
    }

    public BaseSearchDialogCompat setFilterAutomatically(final boolean z) {
        mFilterAutomatically = z;
        return this;
    }
}
