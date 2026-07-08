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

public final class ReportCollectionListRowBinding implements ViewBinding {
    public final LinearLayout linearMaterial2;
    private final LinearLayout rootView;
    public final AppCompatTextView txtViewAmount;
    public final AppCompatTextView txtViewBalance;
    public final AppCompatTextView txtViewDue;
    public final AppCompatTextView txtViewDueDate;
    public final AppCompatTextView txtViewFicheNo;
    public final AppCompatTextView txtViewFicheType;
    public final AppCompatTextView txtViewPrecessDate;
    public final AppCompatTextView txtViewRecordNo;
    private ReportCollectionListRowBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7, final AppCompatTextView appCompatTextView8) {
        rootView = linearLayout;
        linearMaterial2 = linearLayout2;
        txtViewAmount = appCompatTextView;
        txtViewBalance = appCompatTextView2;
        txtViewDue = appCompatTextView3;
        txtViewDueDate = appCompatTextView4;
        txtViewFicheNo = appCompatTextView5;
        txtViewFicheType = appCompatTextView6;
        txtViewPrecessDate = appCompatTextView7;
        txtViewRecordNo = appCompatTextView8;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static ReportCollectionListRowBinding inflate(final LayoutInflater layoutInflater) {
        return ReportCollectionListRowBinding.inflate(layoutInflater, null, false);
    }
    public static ReportCollectionListRowBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_collection_list_row, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportCollectionListRowBinding.bind(inflate);
    }
    public static ReportCollectionListRowBinding bind(final View view) {
        int i2 = R.id.linearMaterial2;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
        if (null != linearLayout) {
            i2 = R.id.txtViewAmount;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.txtViewAmount);
            if (null != appCompatTextView) {
                i2 = R.id.txtViewBalance;
                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.txtViewBalance);
                if (null != appCompatTextView2) {
                    i2 = R.id.txtViewDue;
                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.txtViewDue);
                    if (null != appCompatTextView3) {
                        i2 = R.id.txtViewDueDate;
                        final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.txtViewDueDate);
                        if (null != appCompatTextView4) {
                            i2 = R.id.txtViewFicheNo;
                            final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.txtViewFicheNo);
                            if (null != appCompatTextView5) {
                                i2 = R.id.txtViewFicheType;
                                final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.txtViewFicheType);
                                if (null != appCompatTextView6) {
                                    i2 = R.id.txtViewPrecessDate;
                                    final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.txtViewPrecessDate);
                                    if (null != appCompatTextView7) {
                                        i2 = R.id.txtViewRecordNo;
                                        final AppCompatTextView appCompatTextView8 = ViewBindings.findChildViewById(view, R.id.txtViewRecordNo);
                                        if (null != appCompatTextView8) {
                                            return new ReportCollectionListRowBinding((LinearLayout) view, linearLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8);
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
