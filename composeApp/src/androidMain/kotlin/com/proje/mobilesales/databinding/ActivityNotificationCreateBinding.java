package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class ActivityNotificationCreateBinding implements ViewBinding {
    public final AppCompatImageButton imgAddTo;
    public final AppCompatImageButton imgBtnSendDate;
    public final AppCompatImageButton imgBtnSendTime;
    public final LoadingItemBinding loadingBar;
    private final ConstraintLayout rootView;
    public final ScrollView scrollView;
    public final SwitchCompat swcPayAttentionToWorkTime;
    public final Toolbar toolbar;
    public final AppCompatEditText tvMessage;
    public final AppCompatTextView tvMessageHeader;
    public final AppCompatEditText tvNotificationTitle;
    public final AppCompatTextView tvPayAttentionToWorkTimeHeader;
    public final AppCompatTextView tvSendDate;
    public final AppCompatTextView tvSendDateHeader;
    public final AppCompatTextView tvSendTime;
    public final AppCompatTextView tvTitleHeader;
    public final AppCompatTextView tvTo;
    public final AppCompatTextView tvToHeader;
    private ActivityNotificationCreateBinding( ConstraintLayout constraintLayout,  AppCompatImageButton appCompatImageButton,  AppCompatImageButton appCompatImageButton2,  AppCompatImageButton appCompatImageButton3,  LoadingItemBinding loadingItemBinding,  ScrollView scrollView,  SwitchCompat switchCompat,  Toolbar toolbar,  AppCompatEditText appCompatEditText,  AppCompatTextView appCompatTextView,  AppCompatEditText appCompatEditText2,  AppCompatTextView appCompatTextView2,  AppCompatTextView appCompatTextView3,  AppCompatTextView appCompatTextView4,  AppCompatTextView appCompatTextView5,  AppCompatTextView appCompatTextView6,  AppCompatTextView appCompatTextView7,  AppCompatTextView appCompatTextView8) {
        this.rootView = constraintLayout;
        this.imgAddTo = appCompatImageButton;
        this.imgBtnSendDate = appCompatImageButton2;
        this.imgBtnSendTime = appCompatImageButton3;
        this.loadingBar = loadingItemBinding;
        this.scrollView = scrollView;
        this.swcPayAttentionToWorkTime = switchCompat;
        this.toolbar = toolbar;
        this.tvMessage = appCompatEditText;
        this.tvMessageHeader = appCompatTextView;
        this.tvNotificationTitle = appCompatEditText2;
        this.tvPayAttentionToWorkTimeHeader = appCompatTextView2;
        this.tvSendDate = appCompatTextView3;
        this.tvSendDateHeader = appCompatTextView4;
        this.tvSendTime = appCompatTextView5;
        this.tvTitleHeader = appCompatTextView6;
        this.tvTo = appCompatTextView7;
        this.tvToHeader = appCompatTextView8;
    }
    public ConstraintLayout getRoot() {
        return this.rootView;
    }
    public static ActivityNotificationCreateBinding inflate( LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }
    public static ActivityNotificationCreateBinding inflate( LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View viewInflate = layoutInflater.inflate(R.layout.activity_notification_create, viewGroup, false);
        if (z) {
            assert viewGroup != null;
            viewGroup.addView(viewInflate);
        }
        return bind(viewInflate);
    }
    public static ActivityNotificationCreateBinding bind( View view) {
        int i2 = R.id.imgAddTo;
        AppCompatImageButton appCompatImageButton = ViewBindings.findChildViewById(view, R.id.imgAddTo);
        if (appCompatImageButton != null) {
            i2 = R.id.imgBtnSendDate;
            AppCompatImageButton appCompatImageButton2 = ViewBindings.findChildViewById(view, R.id.imgBtnSendDate);
            if (appCompatImageButton2 != null) {
                i2 = R.id.imgBtnSendTime;
                AppCompatImageButton appCompatImageButton3 = ViewBindings.findChildViewById(view, R.id.imgBtnSendTime);
                if (appCompatImageButton3 != null) {
                    i2 = R.id.loadingBar;
                    View viewFindChildViewById = ViewBindings.findChildViewById(view, R.id.loadingBar);
                    if (viewFindChildViewById != null) {
                        LoadingItemBinding loadingItemBindingBind = LoadingItemBinding.bind(viewFindChildViewById);
                        i2 = R.id.scrollView;
                        ScrollView scrollView = ViewBindings.findChildViewById(view, R.id.scrollView);
                        if (scrollView != null) {
                            i2 = R.id.swcPayAttentionToWorkTime;
                            SwitchCompat switchCompat = ViewBindings.findChildViewById(view, R.id.swcPayAttentionToWorkTime);
                            if (switchCompat != null) {
                                i2 = R.id.toolbar;
                                Toolbar toolbar = ViewBindings.findChildViewById(view, R.id.toolbar);
                                if (toolbar != null) {
                                    i2 = R.id.tvMessage;
                                    AppCompatEditText appCompatEditText = ViewBindings.findChildViewById(view, R.id.tvMessage);
                                    if (appCompatEditText != null) {
                                        i2 = R.id.tvMessageHeader;
                                        AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvMessageHeader);
                                        if (appCompatTextView != null) {
                                            i2 = R.id.tvNotificationTitle;
                                            AppCompatEditText appCompatEditText2 = ViewBindings.findChildViewById(view, R.id.tvNotificationTitle);
                                            if (appCompatEditText2 != null) {
                                                i2 = R.id.tvPayAttentionToWorkTimeHeader;
                                                AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvPayAttentionToWorkTimeHeader);
                                                if (appCompatTextView2 != null) {
                                                    i2 = R.id.tvSendDate;
                                                    AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvSendDate);
                                                    if (appCompatTextView3 != null) {
                                                        i2 = R.id.tvSendDateHeader;
                                                        AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvSendDateHeader);
                                                        if (appCompatTextView4 != null) {
                                                            i2 = R.id.tvSendTime;
                                                            AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvSendTime);
                                                            if (appCompatTextView5 != null) {
                                                                i2 = R.id.tvTitleHeader;
                                                                AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvTitleHeader);
                                                                if (appCompatTextView6 != null) {
                                                                    i2 = R.id.tvTo;
                                                                    AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.tvTo);
                                                                    if (appCompatTextView7 != null) {
                                                                        i2 = R.id.tvToHeader;
                                                                        AppCompatTextView appCompatTextView8 = ViewBindings.findChildViewById(view, R.id.tvToHeader);
                                                                        if (appCompatTextView8 != null) {
                                                                            return new ActivityNotificationCreateBinding((ConstraintLayout) view, appCompatImageButton, appCompatImageButton2, appCompatImageButton3, loadingItemBindingBind, scrollView, switchCompat, toolbar, appCompatEditText, appCompatTextView, appCompatEditText2, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8);
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
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
