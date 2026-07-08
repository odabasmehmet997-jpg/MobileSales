package com.proje.mobilesales.databinding;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.notification.model.NotifiedUserModel;
public abstract class FragmentNotifiedUserListDialogItemBinding extends ViewDataBinding {
    public final Guideline guideline50;
    protected NotifiedUserModel mNotifiedUser;
    public final TextView tvStatus;
    public final TextView tvStatusDate;
    public abstract void setNotifiedUser(NotifiedUserModel notifiedUserModel);
    protected FragmentNotifiedUserListDialogItemBinding(final Object obj, final View view, final int i2, final Guideline guideline, final TextView textView, final TextView textView2) {
        super(obj, view, i2);
        guideline50 = guideline;
        tvStatus = textView;
        tvStatusDate = textView2;
    }
    public NotifiedUserModel getNotifiedUser() {
        return mNotifiedUser;
    }
    public static FragmentNotifiedUserListDialogItemBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        return FragmentNotifiedUserListDialogItemBinding.inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }
    @SuppressLint("RestrictedApi")
    public static FragmentNotifiedUserListDialogItemBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z, final Object obj) {
        return (FragmentNotifiedUserListDialogItemBinding) inflateInternal(layoutInflater, R.layout.fragment_notified_user_list_dialog_item, viewGroup, z, obj);
    }
    public static FragmentNotifiedUserListDialogItemBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentNotifiedUserListDialogItemBinding.inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }
    @SuppressLint("RestrictedApi")
    public static FragmentNotifiedUserListDialogItemBinding inflate(final LayoutInflater layoutInflater, final Object obj) {
        return (FragmentNotifiedUserListDialogItemBinding) inflateInternal(layoutInflater, R.layout.fragment_notified_user_list_dialog_item, null, false, obj);
    }
    public static FragmentNotifiedUserListDialogItemBinding bind(final View view) {
        return FragmentNotifiedUserListDialogItemBinding.bind(view, DataBindingUtil.getDefaultComponent());
    }
    public static FragmentNotifiedUserListDialogItemBinding bind(final View view, final Object obj) {
        return bind(obj, view, R.layout.fragment_notified_user_list_dialog_item);
    }
}
