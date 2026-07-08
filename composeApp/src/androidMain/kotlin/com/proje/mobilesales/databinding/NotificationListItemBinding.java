package com.proje.mobilesales.databinding;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.notification.model.NotificationModel;
 
public abstract class NotificationListItemBinding extends ViewDataBinding {
    public final CardView cardView;
    public final AppCompatImageButton imgBtnDelete;
    public final AppCompatImageButton imgBtnInfo;
    public final AppCompatImageView imgViewStatus;
    protected NotificationModel mNotification;
    public final AppCompatTextView tvMessage;
    public final AppCompatTextView tvSendDate;
    public final AppCompatTextView tvSenderName;
    public final AppCompatTextView tvTitle;
    public abstract void setNotification(NotificationModel notificationModel);
    protected NotificationListItemBinding(final Object obj, final View view, final int i2, final CardView cardView, final AppCompatImageButton appCompatImageButton, AppCompatImageButton imgBtnDelete, final AppCompatImageButton appCompatImageButton2, final AppCompatImageView appCompatImageView, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4) {
        super(obj, view, i2);
        this.cardView = cardView;
        this.imgBtnDelete = imgBtnDelete;
        this.imgBtnInfo = appCompatImageButton2;
        this.imgViewStatus = appCompatImageView;
        this.tvMessage = appCompatTextView;
        this.tvSendDate = appCompatTextView2;
        this.tvSenderName = appCompatTextView3;
        this.tvTitle = appCompatTextView4;
    }
    public NotificationModel getNotification() {
        return mNotification;
    }
    public static NotificationListItemBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }
    @SuppressLint("RestrictedApi")
    public static NotificationListItemBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z, final Object obj) {
        return (NotificationListItemBinding)  ViewDataBinding.inflateInternal(layoutInflater, R.layout.notification_list_item, viewGroup, z, obj);
    }
    @SuppressLint("RestrictedApi")
    public static NotificationListItemBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (NotificationListItemBinding)  ViewDataBinding.inflateInternal(layoutInflater, R.layout.notification_list_item, null, false, obj);
    }
    public static NotificationListItemBinding inflate(final LayoutInflater layoutInflater) {
        return NotificationListItemBinding.inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }
    public static NotificationListItemBinding bind(final View view) {
        return  bind(view, DataBindingUtil.getDefaultComponent());
    }
    public static NotificationListItemBinding bind(final View view, final Object obj) {
        return bind(obj, view, R.layout.notification_list_item);
    }
}
