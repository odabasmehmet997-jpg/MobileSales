package com.proje.mobilesales.databinding;

import android.R;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.AppBarSwipeRefreshLayout;


public final class FragmentItemReportsBindingImpl extends FragmentItemReportsBinding {

    private static final ViewDataBinding.IncludedLayouts sIncludes = null;

    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    private boolean onFieldChange(final int i2, final Object obj, final int i3) {
        return false;
    }

    public boolean setVariable(final int i2, @Nullable final Object obj) {
        return true;
    }

    static {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.swiperefresh, 1);
        sparseIntArray.put(R.id.itemCode, 2);
        sparseIntArray.put(R.id.list, 3);
    }

    public FragmentItemReportsBindingImpl(@Nullable final DataBindingComponent dataBindingComponent, final View view) {
        this(dataBindingComponent, view, FragmentItemReportsBindingImpl.mapBindings(dataBindingComponent, view, 4, FragmentItemReportsBindingImpl.sIncludes, FragmentItemReportsBindingImpl.sViewsWithIds));
    }

    private static Object[] mapBindings(final DataBindingComponent dataBindingComponent, final View view, final int i, final ViewDataBinding.IncludedLayouts sIncludes, final SparseIntArray sViewsWithIds) {
        return new Object[0];
    }

    private FragmentItemReportsBindingImpl(final DataBindingComponent dataBindingComponent, final View view, final Object[] objArr) {
        this.setVariable(dataBindingComponent, view, 0, (ConstraintLayout) objArr[0], (TextView) objArr[2], (ListView) objArr[3], (AppBarSwipeRefreshLayout) objArr[1]);
        mDirtyFlags = -1L;
        constLayout.setTag(null);
        this.setRootTag(view);
        this.invalidateAll();
    }

    private void setVariable(final DataBindingComponent dataBindingComponent, final View view, final int i, final ConstraintLayout constraintLayout, final TextView textView, final ListView listView, final AppBarSwipeRefreshLayout appBarSwipeRefreshLayout) {

    }

    private void setRootTag(final View view) {
    }

    public void invalidateAll() {
        synchronized (this) {
            mDirtyFlags = 1L;
        }
        this.requestRebind();
    }

    private void requestRebind() {
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            try {
                return 0 != this.mDirtyFlags;
            } catch (final Throwable th) {
                throw th;
            }
        }
    }

    private void executeBindings() {
        synchronized (this) {
            mDirtyFlags = 0L;
        }
    }
}
