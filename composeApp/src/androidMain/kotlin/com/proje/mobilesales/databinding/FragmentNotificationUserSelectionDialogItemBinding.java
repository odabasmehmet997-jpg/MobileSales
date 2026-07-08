package com.proje.mobilesales.databinding;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.notification.model.NotificationUserSelectionModel;

public abstract class FragmentNotificationUserSelectionDialogItemBinding extends ViewDataBinding {
    public final AppCompatImageView imgSelected;
    public final ConstraintLayout itemLayout;
    protected NotificationUserSelectionModel mNotificationUser;
    public abstract void setNotificationUser(@Nullable NotificationUserSelectionModel notificationUserSelectionModel);
    protected FragmentNotificationUserSelectionDialogItemBinding(final Object obj, final View view, final int i2, final AppCompatImageView appCompatImageView, final ConstraintLayout constraintLayout) {
        super(obj, view, i2);
        imgSelected = appCompatImageView;
        itemLayout = constraintLayout;
    }
    public NotificationUserSelectionModel getNotificationUser() {
        return mNotificationUser;
    }
    public static FragmentNotificationUserSelectionDialogItemBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        return FragmentNotificationUserSelectionDialogItemBinding.inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }
    @SuppressLint("RestrictedApi")
    public static FragmentNotificationUserSelectionDialogItemBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z, @Nullable final Object obj) {
        return (FragmentNotificationUserSelectionDialogItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.fragment_notification_user_selection_dialog_item, viewGroup, z, obj);
    }
    public static FragmentNotificationUserSelectionDialogItemBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentNotificationUserSelectionDialogItemBinding.inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }
    @SuppressLint("RestrictedApi")
    public static FragmentNotificationUserSelectionDialogItemBinding inflate(final LayoutInflater layoutInflater, @Nullable final Object obj) {
        return (FragmentNotificationUserSelectionDialogItemBinding) inflateInternal(layoutInflater, R.layout.fragment_notification_user_selection_dialog_item, null, false, obj);
    }
    public static FragmentNotificationUserSelectionDialogItemBinding bind(final View view) {
        return FragmentNotificationUserSelectionDialogItemBinding.bind(view, DataBindingUtil.getDefaultComponent());
    }
    public static FragmentNotificationUserSelectionDialogItemBinding bind(final View view, @Nullable final Object obj) {
        return bind(obj, view, R.layout.fragment_notification_user_selection_dialog_item);
    }
}
