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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.proje.mobilesales.R;

public final class ActivityPrintPreferencesBinding implements ViewBinding {
    public final FloatingActionButton btnPrint;
    public final LinearLayout lnDefaultPrinter;
    public final LinearLayout lnReportDesign;
    public final View printerDivider;
    private final LinearLayout rootView;
    public final AppCompatTextView txtChooseDesign;
    public final AppCompatTextView txtChoosePrinter;
    private ActivityPrintPreferencesBinding(final LinearLayout linearLayout, final FloatingActionButton floatingActionButton, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final View view, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2) {
        rootView = linearLayout;
        btnPrint = floatingActionButton;
        lnDefaultPrinter = linearLayout2;
        lnReportDesign = linearLayout3;
        printerDivider = view;
        txtChooseDesign = appCompatTextView;
        txtChoosePrinter = appCompatTextView2;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ActivityPrintPreferencesBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityPrintPreferencesBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityPrintPreferencesBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_print_preferences, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityPrintPreferencesBinding.bind(inflate);
    }
    public static ActivityPrintPreferencesBinding bind(final View view) {
        int i2 = R.id.btnPrint;
        final FloatingActionButton floatingActionButton = ViewBindings.findChildViewById(view, R.id.btnPrint);
        if (null != floatingActionButton) {
            i2 = R.id.ln_defaultPrinter;
            final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_defaultPrinter);
            if (null != linearLayout) {
                i2 = R.id.ln_reportDesign;
                final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_reportDesign);
                if (null != linearLayout2) {
                    i2 = R.id.printerDivider;
                    final View findChildViewById = ViewBindings.findChildViewById(view, R.id.printerDivider);
                    if (null != findChildViewById) {
                        i2 = R.id.txt_chooseDesign;
                        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.txt_chooseDesign);
                        if (null != appCompatTextView) {
                            i2 = R.id.txt_choosePrinter;
                            final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.txt_choosePrinter);
                            if (null != appCompatTextView2) {
                                return new ActivityPrintPreferencesBinding((LinearLayout) view, floatingActionButton, linearLayout, linearLayout2, findChildViewById, appCompatTextView, appCompatTextView2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
