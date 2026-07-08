package com.proje.mobilesales.databinding;

import android.annotation.SuppressLint;
import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.notification.model.NotificationModel;

import static androidx.databinding.adapters.TextViewBindingAdapter.setText;


public class NotificationListItemBindingImpl extends NotificationListItemBinding {

    private static final ViewDataBinding.IncludedLayouts sIncludes;

    static {
        sIncludes = null;
    }

    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private NotificationModel mNotification;
    private AppCompatImageButton imgBtnDelete;
    private AppCompatImageButton imgBtnInfo;
    protected boolean onFieldChange(int fieldId, Object object, int subId) {
        return false;
    }
    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.imgBtnInfo, 5);
        sparseIntArray.put(R.id.imgBtnDelete, 6);
        sparseIntArray.put(R.id.imgViewStatus, 7);
    }
    public NotificationListItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }
    private static Object[] mapBindings(DataBindingComponent dataBindingComponent, View view, int i, SparseIntArray sViewsWithIds) {
        return mapBindings(dataBindingComponent, view, i, null, sViewsWithIds);
    }
    private static Object[] mapBindings(DataBindingComponent dataBindingComponent, View view, int i, ViewDataBinding.IncludedLayouts sIncludes, SparseIntArray sViewsWithIds) {
        return new Object[0];
    }
    private NotificationListItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        setVariable(dataBindingComponent, view, 0, (CardView) objArr[0], (AppCompatImageButton) objArr[6], (AppCompatImageButton) objArr[5], (AppCompatImageView) objArr[7], (AppCompatTextView) objArr[3], (AppCompatTextView) objArr[4], (AppCompatTextView) objArr[1], (AppCompatTextView) objArr[2]);
        this.mDirtyFlags = -1L;
        this.cardView.setTag(null);
        this.tvMessage.setTag(null);
        this.tvSendDate.setTag(null);
        this.tvSenderName.setTag(null);
        this.tvTitle.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
    private void setRootTag(View view) {

    }
    private void setVariable(DataBindingComponent dataBindingComponent, View view, int i, CardView cardView, AppCompatImageButton appCompatImageButton, AppCompatImageButton appCompatImageButton1, AppCompatImageView appCompatImageView, AppCompatTextView appCompatTextView, AppCompatTextView appCompatTextView1, AppCompatTextView appCompatTextView2, AppCompatTextView appCompatTextView3) {
    }
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2L;
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
    public boolean setVariable(int r2, Object obj) {
        if (1 != r2) {
            return false;
        }
        setNotification((NotificationModel) obj);
        return true;
    }
    public void setNotification(NotificationModel notificationModel) {
        this.mNotification = notificationModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(1);
        super.toString();
    }
    private void notifyPropertyChanged(int i) {
        if (i == 1) {
            invalidateAll();
        }
    }
    protected void executeBindings() {
        long j2;
        String message;
        String senderName;
        String title;
        CharSequence relativeTime;
        synchronized (this) {
            j2 = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        NotificationModel notificationModel = this.mNotification;
        long j3 = j2 & 3;
        if (j3 == 0 || notificationModel == null) {
            message = null;
            senderName = null;
            title = null;
            relativeTime = null;
        } else {
            message = notificationModel.getMessage();
            senderName = notificationModel.getSenderName();
            title = notificationModel.getTitle();
            relativeTime = notificationModel.getRelativeTime();
        }
        if (j3 != 0) {
            setText(this.tvMessage, message);
            setText(this.tvSendDate, relativeTime);
            setText(this.tvSenderName, senderName);
            setText(this.tvTitle, title);
        }
    }
}
