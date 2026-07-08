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



public final class ReportCollectionsListColumnBinding implements ViewBinding {

   
    public final LinearLayout linearMaterial2;

   
    private final ScrollView rootView;

   
    public final AppCompatTextView tvAmount;

   
    public final AppCompatTextView tvBalance;

   
    public final AppCompatTextView tvDueDate;

   
    public final AppCompatTextView tvFicheNo;

   
    public final AppCompatTextView tvFicheType;

   
    public final AppCompatTextView tvProcessDate;

   
    public final AppCompatTextView tvRecordNo;

   
    public final AppCompatTextView tvViewDue;

    private ReportCollectionsListColumnBinding(final ScrollView scrollView, final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3, final AppCompatTextView appCompatTextView4, final AppCompatTextView appCompatTextView5, final AppCompatTextView appCompatTextView6, final AppCompatTextView appCompatTextView7, final AppCompatTextView appCompatTextView8) {
        rootView = scrollView;
        linearMaterial2 = linearLayout;
        tvAmount = appCompatTextView;
        tvBalance = appCompatTextView2;
        tvDueDate = appCompatTextView3;
        tvFicheNo = appCompatTextView4;
        tvFicheType = appCompatTextView5;
        tvProcessDate = appCompatTextView6;
        tvRecordNo = appCompatTextView7;
        tvViewDue = appCompatTextView8;
    }

    
   
    public ScrollView getRoot() {
        return rootView;
    }

   
    public static ReportCollectionsListColumnBinding inflate(final LayoutInflater layoutInflater) {
        return ReportCollectionsListColumnBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportCollectionsListColumnBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_collections_list_column, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportCollectionsListColumnBinding.bind(inflate);
    }

   
    public static ReportCollectionsListColumnBinding bind(final View view) {
        int i2 = R.id.linearMaterial2;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
        if (null != linearLayout) {
            i2 = R.id.tvAmount;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvAmount);
            if (null != appCompatTextView) {
                i2 = R.id.tvBalance;
                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvBalance);
                if (null != appCompatTextView2) {
                    i2 = R.id.tvDueDate;
                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvDueDate);
                    if (null != appCompatTextView3) {
                        i2 = R.id.tvFicheNo;
                        final AppCompatTextView appCompatTextView4 = ViewBindings.findChildViewById(view, R.id.tvFicheNo);
                        if (null != appCompatTextView4) {
                            i2 = R.id.tvFicheType;
                            final AppCompatTextView appCompatTextView5 = ViewBindings.findChildViewById(view, R.id.tvFicheType);
                            if (null != appCompatTextView5) {
                                i2 = R.id.tvProcessDate;
                                final AppCompatTextView appCompatTextView6 = ViewBindings.findChildViewById(view, R.id.tvProcessDate);
                                if (null != appCompatTextView6) {
                                    i2 = R.id.tvRecordNo;
                                    final AppCompatTextView appCompatTextView7 = ViewBindings.findChildViewById(view, R.id.tvRecordNo);
                                    if (null != appCompatTextView7) {
                                        i2 = R.id.tvViewDue;
                                        final AppCompatTextView appCompatTextView8 = ViewBindings.findChildViewById(view, R.id.tvViewDue);
                                        if (null != appCompatTextView8) {
                                            return new ReportCollectionsListColumnBinding((ScrollView) view, linearLayout, appCompatTextView, appCompatTextView2, appCompatTextView3, appCompatTextView4, appCompatTextView5, appCompatTextView6, appCompatTextView7, appCompatTextView8);
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
