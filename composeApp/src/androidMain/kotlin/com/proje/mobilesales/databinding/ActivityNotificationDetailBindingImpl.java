package com.proje.mobilesales.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.notification.model.NotificationDetailModel;
import com.proje.mobilesales.features.notification.model.NotificationModel;

import static androidx.databinding.adapters.TextViewBindingAdapter.setText;

public class ActivityNotificationDetailBindingImpl extends ActivityNotificationDetailBinding {

    protected static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    protected boolean onFieldChange(int r1, Object obj, int r3) {
        return false;
    }
    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.loadingBar, 8);
        sparseIntArray.put(R.id.toolbar, 9);
        sparseIntArray.put(R.id.scrollView, 10);
        sparseIntArray.put(R.id.guideline40, 11);
        sparseIntArray.put(R.id.tvSendDateLabel, 12);
        sparseIntArray.put(R.id.tvStatusLabel, 13);
        sparseIntArray.put(R.id.tvStatus, 14);
    }
    public ActivityNotificationDetailBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent,
                view,
                ViewDataBinding.mapBindings(dataBindingComponent, view, 15, sIncludes, sViewsWithIds));
    }
    private ActivityNotificationDetailBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        Guideline guideline = (Guideline) objArr[11];
        Object obj = objArr[8];
        setVariable(dataBindingComponent, view, 0, guideline, obj != null ? LoadingItemBinding.bind((View) obj) : null, (RecyclerView) objArr[7], (ScrollView) objArr[10], (Toolbar) objArr[9], (AppCompatTextView) objArr[2], (TextView) objArr[4], (AppCompatTextView) objArr[12], (AppCompatTextView) objArr[3], (AppCompatTextView) objArr[14], (AppCompatTextView) objArr[13], (AppCompatTextView) objArr[1], (AppCompatTextView) objArr[6], (AppCompatTextView) objArr[5]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.notifiedUserList.setTag(null);
        this.tvMessage.setTag(null);
        this.tvSendDate.setTag(null);
        this.tvSender.setTag(null);
        this.tvTitle.setTag(null);
        this.tvWorkingHours.setTag(null);
        this.tvWorkingHoursLabel.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
    private void setRootTag(View view) {
    }
    private void setVariable(DataBindingComponent dataBindingComponent, View view, int i, Guideline guideline, LoadingItemBinding loadingItemBinding, RecyclerView recyclerView, ScrollView scrollView, Toolbar toolbar, AppCompatTextView appCompatTextView, TextView textView, AppCompatTextView appCompatTextView1, AppCompatTextView appCompatTextView2, AppCompatTextView appCompatTextView3, AppCompatTextView appCompatTextView4, AppCompatTextView appCompatTextView5, AppCompatTextView appCompatTextView6, AppCompatTextView appCompatTextView7) {
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
    public void setNotificationDetail( NotificationDetailModel notificationDetailModel) {
        this.mNotificationDetail = notificationDetailModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }
    protected void executeBindings() {
        long j2;
        CharSequence charSequence;
        String title;
        String senderName;
        boolean zIsNotifiedUsersEmpty;
        NotificationModel notificationModel;
        String message;
        synchronized (this) {
            j2 = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        NotificationDetailModel notificationDetailModel = this.mNotificationDetail;
        long j3 = j2 & 3;
        CharSequence relativeTime = null;
        if (j3 != 0) {
            if (notificationDetailModel != null) {
                notificationModel = notificationDetailModel.getNotificationModel();
                zIsNotifiedUsersEmpty = notificationDetailModel.isNotifiedUsersEmpty();
            } else {
                zIsNotifiedUsersEmpty = false;
                notificationModel = null;
            }
            if (j3 != 0) {
                j2 |= zIsNotifiedUsersEmpty ? 8L : 4L;
            }
            if (notificationModel != null) {
                relativeTime = notificationModel.getRelativeTime();
                title = notificationModel.getTitle();
                senderName = notificationModel.getSenderName();
                message = notificationModel.getMessage();
            } else {
                title = null;
                message = null;
                senderName = null;
            }
            ı = zIsNotifiedUsersEmpty ? 8 : 0;
            charSequence = relativeTime;
            relativeTime = message;
        } else {
            charSequence = null;
            title = null;
            senderName = null;
        }
        if ((j2 & 3) != 0) {
            this.notifiedUserList.setVisibility(ı);
            setText(this.tvMessage, relativeTime);
            setText(this.tvSendDate, charSequence);
            setText(this.tvSender, senderName);
            setText(this.tvTitle, title);
            this.tvWorkingHours.setVisibility(ı);
            this.tvWorkingHoursLabel.setVisibility(ı);
        }
    }
}
