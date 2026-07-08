package com.proje.mobilesales.databinding;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView; 
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.notification.model.NotificationDetailModel;

public abstract class ActivityNotificationDetailBinding extends ViewDataBinding {
    public final Guideline guideline40;
    public final LoadingItemBinding loadingBar;
    protected NotificationDetailModel mNotificationDetail;
    public final RecyclerView notifiedUserList;
    public final ScrollView scrollView;
    public final Toolbar toolbar;
    public final AppCompatTextView tvMessage;
    public final TextView tvSendDate;
    public final AppCompatTextView tvSendDateLabel;
    public final AppCompatTextView tvSender;
    public final AppCompatTextView tvStatus;
    public final AppCompatTextView tvStatusLabel;
    public final AppCompatTextView tvTitle;
    public final AppCompatTextView tvWorkingHours;
    public final AppCompatTextView tvWorkingHoursLabel;
    public abstract void setNotificationDetail( NotificationDetailModel notificationDetailModel);
    protected ActivityNotificationDetailBinding(final Object obj, final View view, final int i2, final Guideline guideline, final LoadingItemBinding loadingItemBinding, final RecyclerView recyclerView, final ScrollView scrollView, final Toolbar toolbar, final AppCompatTextView appCompatTextView, final TextView textView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7, final AppCompatTextView appCompatTextView8) {
        super(obj, view, i2);
        guideline40 = guideline;
        loadingBar = loadingItemBinding;
        notifiedUserList = recyclerView;
        this.scrollView = scrollView;
        this.toolbar = toolbar;
        tvMessage = appCompatTextView;
        tvSendDate = textView;
        tvSendDateLabel = appCompatTextView2;
        tvSender = appCompatTextView3;
        tvStatus = appCompatTextView4;
        tvStatusLabel = appCompatTextView5;
        tvTitle = appCompatTextView6;
        tvWorkingHours = appCompatTextView7;
        tvWorkingHoursLabel = appCompatTextView8;
    }
    public NotificationDetailModel getNotificationDetail() {
        return mNotificationDetail;
    }
    public static ActivityNotificationDetailBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        return ActivityNotificationDetailBinding.inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }
    @SuppressLint("RestrictedApi")
    public static ActivityNotificationDetailBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z, final Object obj) {
        return (ActivityNotificationDetailBinding) inflateInternal(layoutInflater, R.layout.activity_notification_detail, viewGroup, z, obj);
    }
    public static ActivityNotificationDetailBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityNotificationDetailBinding.inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }
    @SuppressLint("RestrictedApi")
    public static ActivityNotificationDetailBinding inflate(final LayoutInflater layoutInflater, final Object obj) {
        return (ActivityNotificationDetailBinding) inflateInternal(layoutInflater, R.layout.activity_notification_detail, null, false, obj);
    }
    public static ActivityNotificationDetailBinding bind(final View view) {
        return ActivityNotificationDetailBinding.bind(view, DataBindingUtil.getDefaultComponent());
    }
    public static ActivityNotificationDetailBinding bind(final View view, final Object obj) {
        return  bind(view, R.layout.activity_notification_detail);
    }
}
