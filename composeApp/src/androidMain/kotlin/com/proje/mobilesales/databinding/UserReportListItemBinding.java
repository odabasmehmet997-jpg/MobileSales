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



public final class UserReportListItemBinding implements ViewBinding {

   
    public final TextView expandedListItem;

   
    private final LinearLayout rootView;

    private UserReportListItemBinding(final LinearLayout linearLayout, final TextView textView) {
        rootView = linearLayout;
        expandedListItem = textView;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static UserReportListItemBinding inflate(final LayoutInflater layoutInflater) {
        return UserReportListItemBinding.inflate(layoutInflater, null, false);
    }

   
    public static UserReportListItemBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.user_report_list_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return UserReportListItemBinding.bind(inflate);
    }

   
    public static UserReportListItemBinding bind(final View view) {
        final TextView textView = ViewBindings.findChildViewById(view, R.id.expandedListItem);
        if (null != textView) {
            return new UserReportListItemBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.expandedListItem));
    }
}
