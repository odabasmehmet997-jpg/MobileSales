package com.proje.mobilesales.databinding;

import android.R;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import com.proje.mobilesales.R;

public class ActivityItemReportsBindingImpl  {
    private static final SparseIntArray sViewsWithIds;
    private final Object constLayout = null;
    private long mDirtyFlags;
    private View mRoot;
    protected boolean onFieldChange(int r1,  Object obj, int r3) {
        return false;
    }
    public boolean setVariable(int r1,Object obj) {
        return true;
    }
    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.appBarLayout, 1);
        sparseIntArray.put(R.id.toolbar, 2);
        sparseIntArray.put(R.id.swiperefresh, 3);
        sparseIntArray.put(R.id.itemCode, 4);
        sparseIntArray.put(R.id.list, 5);
    }
    public ActivityItemReportsBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(view, mapBindings(dataBindingComponent,
                view,
                6,
                FragmentNotifiedUserListDialogItemBindingImpl.sIncludes,
                sViewsWithIds));
    }
    private static Object[] mapBindings(DataBindingComponent dataBindingComponent, View view, int i, IncludedLayouts sIncludes, SparseIntArray sViewsWithIds) {
        return new Object[0];
    }
    private ActivityItemReportsBindingImpl(View view, Object[] objArr) {
        setVariable(0, objArr[1]);
        this.mDirtyFlags = -1L;
        constLayout.getClass();
        setRootTag(view);
        invalidateAll();
    }
    private void setRootTag(View view) {
        this.mRoot = view;
    }
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1L;
        }
        requestRebind();
    }
    private void requestRebind() {
    }
    public boolean hasPendingBindings() {
        synchronized (this) {
            try {
                return this.mDirtyFlags != 0;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
    protected void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0L;
        }
    }
    static class IncludedLayouts {
    }
}
