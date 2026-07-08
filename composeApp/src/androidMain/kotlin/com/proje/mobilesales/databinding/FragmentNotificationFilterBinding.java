package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class FragmentNotificationFilterBinding implements ViewBinding {

   
    public final AppCompatButton btnApply;

   
    public final AppCompatButton btnClear;

   
    public final AppCompatButton btnClose;

   
    public final AppCompatTextView endDate;

   
    public final Guideline guidelineFirst;

   
    public final Guideline guidelineLabel;

   
    public final Guideline guidelineSecond;

   
    public final AppCompatImageButton imgBtnEndDate;

   
    public final AppCompatImageButton imgBtnStartDate;

   
    private final ConstraintLayout rootView;

   
    public final AppCompatTextView showDeleted;

   
    public final AppCompatTextView startDate;

   
    public final SwitchCompat swcShowDeleted;

   
    public final AppCompatTextView txtEndDateValue;

   
    public final AppCompatTextView txtStartDateValue;

    private FragmentNotificationFilterBinding(final ConstraintLayout constraintLayout, final AppCompatButton appCompatButton, final AppCompatButton appCompatButton2, final AppCompatButton appCompatButton3, final AppCompatTextView appCompatTextView, final Guideline guideline, final Guideline guideline2, final Guideline guideline3, final AppCompatImageButton appCompatImageButton, final AppCompatImageButton appCompatImageButton2, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final SwitchCompat switchCompat, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5) {
        rootView = constraintLayout;
        btnApply = appCompatButton;
        btnClear = appCompatButton2;
        btnClose = appCompatButton3;
        endDate = appCompatTextView;
        guidelineFirst = guideline;
        guidelineLabel = guideline2;
        guidelineSecond = guideline3;
        imgBtnEndDate = appCompatImageButton;
        imgBtnStartDate = appCompatImageButton2;
        showDeleted = appCompatTextView2;
        startDate = appCompatTextView3;
        swcShowDeleted = switchCompat;
        txtEndDateValue = appCompatTextView4;
        txtStartDateValue = appCompatTextView5;
    }

    
   
    public ConstraintLayout getRoot() {
        return rootView;
    }

   
    public static FragmentNotificationFilterBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentNotificationFilterBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentNotificationFilterBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_notification_filter, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentNotificationFilterBinding.bind(inflate);
    }

   
    public static FragmentNotificationFilterBinding bind(final View view) {
        int i2 = R.id.btnApply;
        final AppCompatButton appCompatButton = ViewBindings.findChildViewById(view, R.id.btnApply);
        if (null != appCompatButton) {
            i2 = R.id.btnClear;
            final AppCompatButton appCompatButton2 = ViewBindings.findChildViewById(view, R.id.btnClear);
            if (null != appCompatButton2) {
                i2 = R.id.btnClose;
                final AppCompatButton appCompatButton3 = ViewBindings.findChildViewById(view, R.id.btnClose);
                if (null != appCompatButton3) {
                    i2 = R.id.endDate;
                    final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.endDate);
                    if (null != appCompatTextView) {
                        i2 = R.id.guidelineFirst;
                        final Guideline guideline = ViewBindings.findChildViewById(view, R.id.guidelineFirst);
                        if (null != guideline) {
                            i2 = R.id.guidelineLabel;
                            final Guideline guideline2 = ViewBindings.findChildViewById(view, R.id.guidelineLabel);
                            if (null != guideline2) {
                                i2 = R.id.guidelineSecond;
                                final Guideline guideline3 = ViewBindings.findChildViewById(view, R.id.guidelineSecond);
                                if (null != guideline3) {
                                    i2 = R.id.imgBtnEndDate;
                                    final AppCompatImageButton appCompatImageButton = ViewBindings.findChildViewById(view, R.id.imgBtnEndDate);
                                    if (null != appCompatImageButton) {
                                        i2 = R.id.imgBtnStartDate;
                                        final AppCompatImageButton appCompatImageButton2 = ViewBindings.findChildViewById(view, R.id.imgBtnStartDate);
                                        if (null != appCompatImageButton2) {
                                            i2 = R.id.showDeleted;
                                            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.showDeleted);
                                            if (null != appCompatTextView2) {
                                                i2 = R.id.startDate;
                                                final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.startDate);
                                                if (null != appCompatTextView3) {
                                                    i2 = R.id.swcShowDeleted;
                                                    final SwitchCompat switchCompat = ViewBindings.findChildViewById(view, R.id.swcShowDeleted);
                                                    if (null != switchCompat) {
                                                        i2 = R.id.txtEndDateValue;
                                                        final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.txtEndDateValue);
                                                        if (null != appCompatTextView4) {
                                                            i2 = R.id.txtStartDateValue;
                                                            final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.txtStartDateValue);
                                                            if (null != appCompatTextView5) {
                                                                return new FragmentNotificationFilterBinding((ConstraintLayout) view, appCompatButton, appCompatButton2, appCompatButton3, appCompatTextView, guideline, guideline2, guideline3, appCompatImageButton, appCompatImageButton2, appCompatTextView2, appCompatTextView3, switchCompat, appCompatTextView4, appCompatTextView5);
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
