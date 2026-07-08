package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ReportDebitFollowBinding implements ViewBinding {

   
    public final LinearLayout linearMaterial2;

   
    public final LinearLayout linearProgress;

   
    public final ListView lvReportOrder;

   
    private final LinearLayout rootView;

    private ReportDebitFollowBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final ListView listView) {
        rootView = linearLayout;
        linearMaterial2 = linearLayout2;
        linearProgress = linearLayout3;
        lvReportOrder = listView;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ReportDebitFollowBinding inflate(final LayoutInflater layoutInflater) {
        return ReportDebitFollowBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportDebitFollowBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_debit_follow, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportDebitFollowBinding.bind(inflate);
    }

   
    public static ReportDebitFollowBinding bind(final View view) {
        int i2 = R.id.linearMaterial2;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearMaterial2);
        if (null != linearLayout) {
            i2 = R.id.linearProgress;
            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.linearProgress);
            if (null != linearLayout2) {
                i2 = R.id.lvReportOrder;
                final ListView listView = ViewBindings.findChildViewById(view, R.id.lvReportOrder);
                if (null != listView) {
                    return new ReportDebitFollowBinding((LinearLayout) view, linearLayout, linearLayout2, listView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
