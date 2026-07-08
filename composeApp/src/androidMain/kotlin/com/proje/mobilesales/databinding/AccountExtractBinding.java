package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
 
public final class AccountExtractBinding implements ViewBinding {
    public final AppCompatCheckBox chGroup;
    public final AppCompatImageButton imgList;
    public final ActivityBaseReportWcfBinding linearDateLayout;
    public final LinearLayout linearFilterDiv;
    public final LinearLayout linearMaterial2;
    public final LinearLayout linearProgress;
    public final LinearLayout lnDebitCredit;
    public final LinearLayout lnFicheTotalBalance;
    public final ListView lvList;
    private final CoordinatorLayout rootView;
    public final AppCompatSpinner spReportCurrencyType;
    public final AppCompatTextView tvBalance;
    public final AppCompatTextView tvDescription;
    public final AppCompatTextView tvDueDate;
    public final AppCompatTextView txtFicheTotalBalance;
    public final AppCompatTextView txtFicheTotalCredit;
    public final AppCompatTextView txtFicheTotalDebit;
    private AccountExtractBinding( final CoordinatorLayout coordinatorLayout,  final AppCompatCheckBox appCompatCheckBox,  final AppCompatImageButton appCompatImageButton,  final ActivityBaseReportWcfBinding activityBaseReportWcfBinding,  final LinearLayout linearLayout,  final LinearLayout linearLayout2,  final LinearLayout linearLayout3,  final LinearLayout linearLayout4,  final LinearLayout linearLayout5,  final ListView listView,  final AppCompatSpinner appCompatSpinner,  final AppCompatTextView appCompatTextView,  final AppCompatTextView appCompatTextView2,  final AppCompatTextView appCompatTextView3,  final AppCompatTextView appCompatTextView4,  final AppCompatTextView appCompatTextView5,  final AppCompatTextView appCompatTextView6) {
        rootView = coordinatorLayout;
        chGroup = appCompatCheckBox;
        imgList = appCompatImageButton;
        linearDateLayout = activityBaseReportWcfBinding;
        linearFilterDiv = linearLayout;
        linearMaterial2 = linearLayout2;
        linearProgress = linearLayout3;
        lnDebitCredit = linearLayout4;
        lnFicheTotalBalance = linearLayout5;
        lvList = listView;
        spReportCurrencyType = appCompatSpinner;
        tvBalance = appCompatTextView;
        tvDescription = appCompatTextView2;
        tvDueDate = appCompatTextView3;
        txtFicheTotalBalance = appCompatTextView4;
        txtFicheTotalCredit = appCompatTextView5;
        txtFicheTotalDebit = appCompatTextView6;
    }
    public CoordinatorLayout getRoot() {
        return rootView;
    }
    public static AccountExtractBinding inflate( final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.account_extract, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
    public static AccountExtractBinding bind( final View view) {
        int i2 = R.id.chGroup;
        final AppCompatCheckBox appCompatCheckBox = ViewBindings.findChildViewById(view, R.id.chGroup);
        if (null != appCompatCheckBox) {
            i2 = R.id.imgList;
            final AppCompatImageButton appCompatImageButton = ViewBindings.findChildViewById(view, R.id.imgList);
            if (null != appCompatImageButton) {
                i2 = R.id.linearDateLayout;
                final View findChildViewById = ViewBindings.findChildViewById(view, R.id.linearDateLayout);
                if (null != findChildViewById) {
                    final ActivityBaseReportWcfBinding bind = ActivityBaseReportWcfBinding.bind(findChildViewById);
                    i2 = R.id.linearFilterDiv;
                    final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearFilterDiv);
                    if (null != linearLayout) {
                        i2 = R.id.linearMaterial2;
                        final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
                        if (null != linearLayout2) {
                            i2 = R.id.linearProgress;
                            final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.linearProgress);
                            if (null != linearLayout3) {
                                i2 = R.id.lnDebitCredit;
                                final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.lnDebitCredit);
                                if (null != linearLayout4) {
                                    i2 = R.id.lnFicheTotalBalance;
                                    final LinearLayout linearLayout5 = ViewBindings.findChildViewById(view, R.id.lnFicheTotalBalance);
                                    if (null != linearLayout5) {
                                        i2 = R.id.lvList;
                                        final ListView listView = ViewBindings.findChildViewById(view, R.id.lvList);
                                        if (null != listView) {
                                            i2 = R.id.spReportCurrencyType;
                                            final AppCompatSpinner appCompatSpinner = ViewBindings.findChildViewById(view, R.id.spReportCurrencyType);
                                            if (null != appCompatSpinner) {
                                                i2 = R.id.tvBalance;
                                                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvBalance);
                                                if (null != appCompatTextView) {
                                                    i2 = R.id.tvDescription;
                                                    final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvDescription);
                                                    if (null != appCompatTextView2) {
                                                        i2 = R.id.tvDueDate;
                                                        final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvDueDate);
                                                        if (null != appCompatTextView3) {
                                                            i2 = R.id.txt_ficheTotalBalance;
                                                            final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.txt_ficheTotalBalance);
                                                            if (null != appCompatTextView4) {
                                                                i2 = R.id.txt_ficheTotalCredit;
                                                                final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.txt_ficheTotalCredit);
                                                                if (null != appCompatTextView5) {
                                                                    i2 = R.id.txt_ficheTotalDebit;
                                                                    final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.txt_ficheTotalDebit);
                                                                    if (null != appCompatTextView6) {
                                                                        return new AccountExtractBinding((CoordinatorLayout) view, appCompatCheckBox, appCompatImageButton, bind, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, listView, appCompatSpinner, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6);
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
