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
public final class ExtractCaseDetailBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final AppCompatTextView tvAmount;
    public final AppCompatTextView tvCustomer;
    public final AppCompatTextView tvDate;
    public final AppCompatTextView tvDesc;
    public final AppCompatTextView tvDoCode;
    public final AppCompatTextView tvFicheNo;
    public final AppCompatTextView tvSpecode;
    private ExtractCaseDetailBinding(final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7) {
        rootView = linearLayout;
        tvAmount = appCompatTextView;
        tvCustomer = appCompatTextView2;
        tvDate = appCompatTextView3;
        tvDesc = appCompatTextView4;
        tvDoCode = appCompatTextView5;
        tvFicheNo = appCompatTextView6;
        tvSpecode = appCompatTextView7;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ExtractCaseDetailBinding inflate(final LayoutInflater layoutInflater) {
        return ExtractCaseDetailBinding.inflate(layoutInflater, null, false);
    }
    public static ExtractCaseDetailBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.extract_case_detail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ExtractCaseDetailBinding.bind(inflate);
    }
    public static ExtractCaseDetailBinding bind(final View view) {
        int i2 = R.id.tvAmount;
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvAmount);
        if (null != appCompatTextView) {
            i2 = R.id.tvCustomer;
            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvCustomer);
            if (null != appCompatTextView2) {
                i2 = R.id.tvDate;
                final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvDate);
                if (null != appCompatTextView3) {
                    i2 = R.id.tvDesc;
                    final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvDesc);
                    if (null != appCompatTextView4) {
                        i2 = R.id.tvDoCode;
                        final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvDoCode);
                        if (null != appCompatTextView5) {
                            i2 = R.id.tvFicheNo;
                            final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvFicheNo);
                            if (null != appCompatTextView6) {
                                i2 = R.id.tvSpecode;
                                final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.tvSpecode);
                                if (null != appCompatTextView7) {
                                    return new ExtractCaseDetailBinding((LinearLayout) view, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7);
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
