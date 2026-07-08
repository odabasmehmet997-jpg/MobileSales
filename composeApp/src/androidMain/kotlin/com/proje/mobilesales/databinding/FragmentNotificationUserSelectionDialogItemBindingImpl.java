package com.proje.mobilesales.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.proje.mobilesales.features.notification.model.NotificationUserSelectionModel;

import static android.service.notification.NotificationListenerService.requestRebind;
import static androidx.databinding.adapters.TextViewBindingAdapter.setText;

public class FragmentNotificationUserSelectionDialogItemBindingImpl extends FragmentNotificationUserSelectionDialogItemBinding {

    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final AppCompatTextView mboundView1;
    private NotificationUserSelectionModel mNotificationUser;
    protected boolean onFieldChange(final int i2, final Object obj, final int i3) {
        return false;
    }
    public FragmentNotificationUserSelectionDialogItemBindingImpl(@Nullable final DataBindingComponent dataBindingComponent, final View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 3, FragmentNotificationUserSelectionDialogItemBindingImpl.sIncludes, FragmentNotificationUserSelectionDialogItemBindingImpl.sViewsWithIds));
    }
    private FragmentNotificationUserSelectionDialogItemBindingImpl(final DataBindingComponent dataBindingComponent, final View view, final Object[] objArr) {
        super(dataBindingComponent, view, 0, (AppCompatImageView) objArr[2], (ConstraintLayout) objArr[0]);
        mDirtyFlags = -1L;
        imgSelected.setTag(null);
        itemLayout.setTag(null);
        final AppCompatTextView appCompatTextView = (AppCompatTextView) objArr[1];
        mboundView1 = appCompatTextView;
        appCompatTextView.setTag(null);
        setRootTag(view);
        this.invalidateAll();
    }
    private void setRootTag(View view) {
    }
    public void invalidateAll() {
        synchronized (this) {
            mDirtyFlags = 2L;
        }
        requestRebind();
    }
    public boolean hasPendingBindings() {
        synchronized (this) {
            return 0 != this.mDirtyFlags;
        }
    }
    public boolean setVariable(final int i2, @Nullable final Object obj) {
        if (3 != i2) {
            return false;
        }
        this.setNotificationUser((NotificationUserSelectionModel) obj);
        return true;
    }
    public void setNotificationUser(@Nullable final NotificationUserSelectionModel notificationUserSelectionModel) {
        this.mNotificationUser = notificationUserSelectionModel;
        synchronized (this) {
            mDirtyFlags |= 1;
        }
        super.requestRebind();
    }
    protected void executeBindings() {
        final boolean z;
        synchronized (this) {
            final long j2 = mDirtyFlags;
            mDirtyFlags = 0L;
            final NotificationUserSelectionModel notificationUserSelectionModel = this.mNotificationUser;
            final long j3 = j2 & 3;
            int i2 = 0;
            String str = null;
            if (0 != j3) {
                if (null != notificationUserSelectionModel) {
                str = notificationUserSelectionModel.getUserDescription();
                z = notificationUserSelectionModel.getSelected();
            } else {
                z = false;
            }
            if (0 != j3) {
                j2 |= z ? 8L : 4L;
            }
            if (!z) {
                i2 = 8;
            }
        }
        if (0 != (j2 & 3)) {
            imgSelected.setVisibility(View.VISIBLE);
            setText(mboundView1, str);
        }
    }
}
