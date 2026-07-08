package com.proje.mobilesales.core.searchdialog;

import android.widget.Filter;
import java.util.ArrayList;



public abstract class BaseFilter<T> extends Filter {
    private FilterResultListener mFilterResultListener;
    private ArrayList<T> mItems;
    private OnPerformFilterListener mOnPerformFilterListener;

    public OnPerformFilterListener getOnPerformFilterListener() {
        return mOnPerformFilterListener;
    }

    public BaseFilter setOnPerformFilterListener(final OnPerformFilterListener onPerformFilterListener) {
        mOnPerformFilterListener = onPerformFilterListener;
        return this;
    }

    public void doAfterFiltering() {
        final OnPerformFilterListener onPerformFilterListener = mOnPerformFilterListener;
        if (null != onPerformFilterListener) {
            onPerformFilterListener.doAfterFiltering();
        }
    }

    public void doBeforeFiltering() {
        final OnPerformFilterListener onPerformFilterListener = mOnPerformFilterListener;
        if (null != onPerformFilterListener) {
            onPerformFilterListener.doBeforeFiltering();
        }
    }

    public ArrayList<T> getItems() {
        return mItems;
    }

    public BaseFilter setItems(final ArrayList<T> arrayList) {
        mItems = arrayList;
        return this;
    }

    public FilterResultListener getFilterResultListener() {
        return mFilterResultListener;
    }

    public BaseFilter setFilterResultListener(final FilterResultListener filterResultListener) {
        mFilterResultListener = filterResultListener;
        return this;
    }
}
