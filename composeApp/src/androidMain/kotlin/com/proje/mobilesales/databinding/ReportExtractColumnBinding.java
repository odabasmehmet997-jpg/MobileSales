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



public final class ReportExtractColumnBinding implements ViewBinding {

   
    public final LinearLayout linearMaterial2;

   
    private final ScrollView rootView;

   
    public final AppCompatTextView tvBakiye;

   
    public final AppCompatTextView tvCredit;

   
    public final AppCompatTextView tvDebit;

   
    public final AppCompatTextView tvFicheNo;

   
    public final AppCompatTextView tvFicheType;

   
    public final AppCompatTextView tvFicheTypeHeader;

   
    public final AppCompatTextView tvProcessDate;

    private ReportExtractColumnBinding(final ScrollView scrollView, final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7) {
        rootView = scrollView;
        linearMaterial2 = linearLayout;
        tvBakiye = appCompatTextView;
        tvCredit = appCompatTextView2;
        tvDebit = appCompatTextView3;
        tvFicheNo = appCompatTextView4;
        tvFicheType = appCompatTextView5;
        tvFicheTypeHeader = appCompatTextView6;
        tvProcessDate = appCompatTextView7;
    }

    
   
    public ScrollView getRoot() {
        return rootView;
    }

   
    public static ReportExtractColumnBinding inflate(final LayoutInflater layoutInflater) {
        return ReportExtractColumnBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportExtractColumnBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_extract_column, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportExtractColumnBinding.bind(inflate);
    }

   
    public static ReportExtractColumnBinding bind(final View view) {
        int i2 = R.id.linearMaterial2;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
        if (null != linearLayout) {
            i2 = R.id.tvBakiye;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvBakiye);
            if (null != appCompatTextView) {
                i2 = R.id.tvCredit;
                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvCredit);
                if (null != appCompatTextView2) {
                    i2 = R.id.tvDebit;
                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvDebit);
                    if (null != appCompatTextView3) {
                        i2 = R.id.tvFicheNo;
                        final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvFicheNo);
                        if (null != appCompatTextView4) {
                            i2 = R.id.tvFicheType;
                            final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvFicheType);
                            if (null != appCompatTextView5) {
                                i2 = R.id.tvFicheTypeHeader;
                                final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvFicheTypeHeader);
                                if (null != appCompatTextView6) {
                                    i2 = R.id.tvProcessDate;
                                    final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.tvProcessDate);
                                    if (null != appCompatTextView7) {
                                        return new ReportExtractColumnBinding((ScrollView) view, linearLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7);
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
