package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ReportDebitFollowRowBinding implements ViewBinding {

   
    public final LinearLayout linearMaterial2;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView txtCurrency;

   
    public final AppCompatTextView txtViewCredit;

   
    public final AppCompatTextView txtViewDate;

   
    public final AppCompatTextView txtViewDebit;

   
    public final AppCompatTextView txtViewExplanation;

   
    public final AppCompatTextView txtViewFischeNumber;

   
    public final AppCompatTextView txtViewProcessDate;

   
    public final AppCompatTextView txtViewRemaining;

    private ReportDebitFollowRowBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7, final AppCompatTextView appCompatTextView8) {
        rootView = linearLayout;
        linearMaterial2 = linearLayout2;
        txtCurrency = appCompatTextView;
        txtViewCredit = appCompatTextView2;
        txtViewDate = appCompatTextView3;
        txtViewDebit = appCompatTextView4;
        txtViewExplanation = appCompatTextView5;
        txtViewFischeNumber = appCompatTextView6;
        txtViewProcessDate = appCompatTextView7;
        txtViewRemaining = appCompatTextView8;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ReportDebitFollowRowBinding inflate(final LayoutInflater layoutInflater) {
        return ReportDebitFollowRowBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportDebitFollowRowBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_debit_follow_row, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportDebitFollowRowBinding.bind(inflate);
    }

   
    public static ReportDebitFollowRowBinding bind(final View view) {
        int i2 = R.id.linearMaterial2;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
        if (null != linearLayout) {
            i2 = R.id.txtCurrency;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.txtCurrency);
            if (null != appCompatTextView) {
                i2 = R.id.txtViewCredit;
                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.txtViewCredit);
                if (null != appCompatTextView2) {
                    i2 = R.id.txtViewDate;
                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.txtViewDate);
                    if (null != appCompatTextView3) {
                        i2 = R.id.txtViewDebit;
                        final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.txtViewDebit);
                        if (null != appCompatTextView4) {
                            i2 = R.id.txtViewExplanation;
                            final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.txtViewExplanation);
                            if (null != appCompatTextView5) {
                                i2 = R.id.txtViewFischeNumber;
                                final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.txtViewFischeNumber);
                                if (null != appCompatTextView6) {
                                    i2 = R.id.txtViewProcessDate;
                                    final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.txtViewProcessDate);
                                    if (null != appCompatTextView7) {
                                        i2 = R.id.txtViewRemaining;
                                        final AppCompatTextView appCompatTextView8 = ViewBindings.findChildViewById(view, R.id.txtViewRemaining);
                                        if (null != appCompatTextView8) {
                                            return new ReportDebitFollowRowBinding((LinearLayout) view, linearLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8);
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
