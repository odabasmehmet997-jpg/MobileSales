package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ReportDebitFollowColumnBinding implements ViewBinding {

   
    public final LinearLayout linearMaterial2;

   
    private final ScrollView rootView;

   
    public final AppCompatTextView tvCredit;

   
    public final AppCompatTextView tvCurrency;

   
    public final AppCompatTextView tvDate;

   
    public final AppCompatTextView tvDebit;

   
    public final AppCompatTextView tvFicheNo;

   
    public final AppCompatTextView tvProcess;

   
    public final AppCompatTextView tvProcessDate;

   
    public final AppCompatTextView tvRemaining;

    private ReportDebitFollowColumnBinding(final ScrollView scrollView, final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7, final AppCompatTextView appCompatTextView8) {
        rootView = scrollView;
        linearMaterial2 = linearLayout;
        tvCredit = appCompatTextView;
        tvCurrency = appCompatTextView2;
        tvDate = appCompatTextView3;
        tvDebit = appCompatTextView4;
        tvFicheNo = appCompatTextView5;
        tvProcess = appCompatTextView6;
        tvProcessDate = appCompatTextView7;
        tvRemaining = appCompatTextView8;
    }

    
   
    public ScrollView getRoot() {
        return rootView;
    }

   
    public static ReportDebitFollowColumnBinding inflate(final LayoutInflater layoutInflater) {
        return ReportDebitFollowColumnBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportDebitFollowColumnBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_debit_follow_column, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportDebitFollowColumnBinding.bind(inflate);
    }

   
    public static ReportDebitFollowColumnBinding bind(final View view) {
        int i2 = R.id.linearMaterial2;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
        if (null != linearLayout) {
            i2 = R.id.tvCredit;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvCredit);
            if (null != appCompatTextView) {
                i2 = R.id.tvCurrency;
                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvCurrency);
                if (null != appCompatTextView2) {
                    i2 = R.id.tvDate;
                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvDate);
                    if (null != appCompatTextView3) {
                        i2 = R.id.tvDebit;
                        final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvDebit);
                        if (null != appCompatTextView4) {
                            i2 = R.id.tvFicheNo;
                            final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvFicheNo);
                            if (null != appCompatTextView5) {
                                i2 = R.id.tvProcess;
                                final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvProcess);
                                if (null != appCompatTextView6) {
                                    i2 = R.id.tvProcessDate;
                                    final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.tvProcessDate);
                                    if (null != appCompatTextView7) {
                                        i2 = R.id.tvRemaining;
                                        final AppCompatTextView appCompatTextView8 = ViewBindings.findChildViewById(view, R.id.tvRemaining);
                                        if (null != appCompatTextView8) {
                                            return new ReportDebitFollowColumnBinding((ScrollView) view, linearLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8);
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
