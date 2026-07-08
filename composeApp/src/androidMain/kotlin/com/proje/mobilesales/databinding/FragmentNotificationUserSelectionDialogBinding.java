package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class FragmentNotificationUserSelectionDialogBinding implements ViewBinding {

   
    public final AppCompatImageButton btnCancel;

   
    public final AppCompatImageButton btnDone;

   
    public final AppCompatImageButton btnSelectAll;

   
    public final AppCompatImageButton btnUnSelectAll;

   
    public final AppCompatEditText edtSearch;

   
    public final AppCompatImageButton imgBtnClearSearch;

   
    public final AppCompatImageButton imgBtnSearch;

   
    public final RecyclerView list;

   
    public final LoadingItemBinding progressBar;

   
    private final ConstraintLayout rootView;

   
    public final AppCompatTextView tvSelectedCount;

   
    public final AppCompatTextView tvSelectedCountHeader;

   
    public final AppCompatTextView tvTitle;

    private FragmentNotificationUserSelectionDialogBinding(final ConstraintLayout constraintLayout, final AppCompatImageButton appCompatImageButton, final AppCompatImageButton appCompatImageButton2, final AppCompatImageButton appCompatImageButton3, final AppCompatImageButton appCompatImageButton4, final AppCompatEditText appCompatEditText, final AppCompatImageButton appCompatImageButton5, final AppCompatImageButton appCompatImageButton6, final RecyclerView recyclerView, final LoadingItemBinding loadingItemBinding, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3) {
        rootView = constraintLayout;
        btnCancel = appCompatImageButton;
        btnDone = appCompatImageButton2;
        btnSelectAll = appCompatImageButton3;
        btnUnSelectAll = appCompatImageButton4;
        edtSearch = appCompatEditText;
        imgBtnClearSearch = appCompatImageButton5;
        imgBtnSearch = appCompatImageButton6;
        list = recyclerView;
        progressBar = loadingItemBinding;
        tvSelectedCount = appCompatTextView;
        tvSelectedCountHeader = appCompatTextView2;
        tvTitle = appCompatTextView3;
    }

    
   
    public ConstraintLayout getRoot() {
        return rootView;
    }

   
    public static FragmentNotificationUserSelectionDialogBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentNotificationUserSelectionDialogBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentNotificationUserSelectionDialogBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_notification_user_selection_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentNotificationUserSelectionDialogBinding.bind(inflate);
    }

   
    public static FragmentNotificationUserSelectionDialogBinding bind(final View view) {
        int i2 = R.id.btnCancel;
        final AppCompatImageButton appCompatImageButton = ViewBindings.findChildViewById(view, R.id.btnCancel);
        if (null != appCompatImageButton) {
            i2 = R.id.btnDone;
            final AppCompatImageButton appCompatImageButton2 = ViewBindings.findChildViewById(view, R.id.btnDone);
            if (null != appCompatImageButton2) {
                i2 = R.id.btnSelectAll;
                final AppCompatImageButton appCompatImageButton3 = ViewBindings.findChildViewById(view, R.id.btnSelectAll);
                if (null != appCompatImageButton3) {
                    i2 = R.id.btnUnSelectAll;
                    final AppCompatImageButton appCompatImageButton4 = ViewBindings.findChildViewById(view, R.id.btnUnSelectAll);
                    if (null != appCompatImageButton4) {
                        i2 = R.id.edtSearch;
                        final AppCompatEditText appCompatEditText = ViewBindings.findChildViewById(view, R.id.edtSearch);
                        if (null != appCompatEditText) {
                            i2 = R.id.imgBtnClearSearch;
                            final AppCompatImageButton appCompatImageButton5 = ViewBindings.findChildViewById(view, R.id.imgBtnClearSearch);
                            if (null != appCompatImageButton5) {
                                i2 = R.id.imgBtnSearch;
                                final AppCompatImageButton appCompatImageButton6 = ViewBindings.findChildViewById(view, R.id.imgBtnSearch);
                                if (null != appCompatImageButton6) {
                                    i2 = R.id.list;
                                    final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.list);
                                    if (null != recyclerView) {
                                        i2 = R.id.progressBar;
                                        final View findChildViewById = ViewBindings.findChildViewById(view, R.id.progressBar);
                                        if (null != findChildViewById) {
                                            final LoadingItemBinding bind = LoadingItemBinding.bind(findChildViewById);
                                            i2 = R.id.tvSelectedCount;
                                            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvSelectedCount);
                                            if (null != appCompatTextView) {
                                                i2 = R.id.tvSelectedCountHeader;
                                                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvSelectedCountHeader);
                                                if (null != appCompatTextView2) {
                                                    i2 = R.id.tvTitle;
                                                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvTitle);
                                                    if (null != appCompatTextView3) {
                                                        return new FragmentNotificationUserSelectionDialogBinding((ConstraintLayout) view, appCompatImageButton, appCompatImageButton2, appCompatImageButton3, appCompatImageButton4, appCompatEditText, appCompatImageButton5, appCompatImageButton6, recyclerView, bind, appCompatTextView, appCompatTextView2, appCompatTextView3);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
