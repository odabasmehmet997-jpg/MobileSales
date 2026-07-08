package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class UserReportGroupHeaderBinding implements ViewBinding {

   
    public final TextView listTitle;

   
    private final LinearLayout rootView;

    private UserReportGroupHeaderBinding(final LinearLayout linearLayout, final TextView textView) {
        rootView = linearLayout;
        listTitle = textView;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static UserReportGroupHeaderBinding inflate(final LayoutInflater layoutInflater) {
        return UserReportGroupHeaderBinding.inflate(layoutInflater, null, false);
    }

   
    public static UserReportGroupHeaderBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.user_report_group_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return UserReportGroupHeaderBinding.bind(inflate);
    }

   
    public static UserReportGroupHeaderBinding bind(final View view) {
        final TextView textView = ViewBindings.findChildViewById(view, R.id.listTitle);
        if (null != textView) {
            return new UserReportGroupHeaderBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.listTitle));
    }
}
