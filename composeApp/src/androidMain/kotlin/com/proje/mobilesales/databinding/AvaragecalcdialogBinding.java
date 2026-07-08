package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class AvaragecalcdialogBinding implements ViewBinding {
    public final RelativeLayout layoutRoot;
    private final RelativeLayout rootView;
    public final AppCompatTextView tvDay;
    public final AppCompatTextView tvDayLabel;
    public final AppCompatTextView tvHistory;
    public final AppCompatTextView tvHistoryLabel;
    public final AppCompatTextView tvTotal;
    public final AppCompatTextView tvTotalLabel;
    private AvaragecalcdialogBinding(final RelativeLayout relativeLayout, final RelativeLayout relativeLayout2, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6) {
        rootView = relativeLayout;
        layoutRoot = relativeLayout2;
        tvDay = appCompatTextView;
        tvDayLabel = appCompatTextView2;
        tvHistory = appCompatTextView3;
        tvHistoryLabel = appCompatTextView4;
        tvTotal = appCompatTextView5;
        tvTotalLabel = appCompatTextView6;
    }
    public RelativeLayout getRoot() {
        return rootView;
    }
    public static AvaragecalcdialogBinding inflate(final LayoutInflater layoutInflater) {
        return AvaragecalcdialogBinding.inflate(layoutInflater, null, false);
    }
    public static AvaragecalcdialogBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.avaragecalcdialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return AvaragecalcdialogBinding.bind(inflate);
    }
    public static AvaragecalcdialogBinding bind(final View view) {
        final RelativeLayout relativeLayout = (RelativeLayout) view;
        int i2 = R.id.tvDay;
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvDay);
        if (null != appCompatTextView) {
            i2 = R.id.tvDayLabel;
            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvDayLabel);
            if (null != appCompatTextView2) {
                i2 = R.id.tvHistory;
                final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvHistory);
                if (null != appCompatTextView3) {
                    i2 = R.id.tvHistoryLabel;
                    final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvHistoryLabel);
                    if (null != appCompatTextView4) {
                        i2 = R.id.tvTotal;
                        final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvTotal);
                        if (null != appCompatTextView5) {
                            i2 = R.id.tvTotalLabel;
                            final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvTotalLabel);
                            if (null != appCompatTextView6) {
                                return new AvaragecalcdialogBinding(relativeLayout, relativeLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
