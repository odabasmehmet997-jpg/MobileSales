package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ReportDesignItemBinding implements ViewBinding {

   
    public final RelativeLayout contentFrame;

   
    private final CardView rootView;

   
    public final TextView txtReportDesign;

    private ReportDesignItemBinding(final CardView cardView, final RelativeLayout relativeLayout, final TextView textView) {
        rootView = cardView;
        contentFrame = relativeLayout;
        txtReportDesign = textView;
    }

    
   
    public CardView getRoot() {
        return rootView;
    }

   
    public static ReportDesignItemBinding inflate(final LayoutInflater layoutInflater) {
        return ReportDesignItemBinding.inflate(layoutInflater, null, false);
    }

   
    public static ReportDesignItemBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.report_design_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ReportDesignItemBinding.bind(inflate);
    }

   
    public static ReportDesignItemBinding bind(final View view) {
        int i2 = R.id.content_frame;
        final RelativeLayout relativeLayout = ViewBindings.findChildViewById(view, R.id.content_frame);
        if (null != relativeLayout) {
            i2 = R.id.txt_report_design;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.txt_report_design);
            if (null != textView) {
                return new ReportDesignItemBinding((CardView) view, relativeLayout, textView);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
