package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class AdapterAverageCalcBinding implements ViewBinding {
    public final LinearLayout lnAmount;
    public final LinearLayout lnDate;
    private final CardView rootView;
    public final AppCompatTextView twAmount;
    public final AppCompatTextView twAverageDate;
    private AdapterAverageCalcBinding(final CardView cardView, final LinearLayout linearLayout, final LinearLayout linearLayout2, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2) {
        rootView = cardView;
        lnAmount = linearLayout;
        lnDate = linearLayout2;
        twAmount = appCompatTextView;
        twAverageDate = appCompatTextView2;
    }
    public CardView getRoot() {
        return rootView;
    }
    public static AdapterAverageCalcBinding inflate(final LayoutInflater layoutInflater) {
        return AdapterAverageCalcBinding.inflate(layoutInflater, null, false);
    }
    public static AdapterAverageCalcBinding inflate(final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.adapter_average_calc, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return AdapterAverageCalcBinding.bind(inflate);
    }
    public static AdapterAverageCalcBinding bind(final View view) {
        int i2 = R.id.lnAmount;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.lnAmount);
        if (null != linearLayout) {
            i2 = R.id.lnDate;
            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.lnDate);
            if (null != linearLayout2) {
                i2 = R.id.twAmount;
                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.twAmount);
                if (null != appCompatTextView) {
                    i2 = R.id.twAverageDate;
                    final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.twAverageDate);
                    if (null != appCompatTextView2) {
                        return new AdapterAverageCalcBinding((CardView) view, linearLayout, linearLayout2, appCompatTextView, appCompatTextView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
