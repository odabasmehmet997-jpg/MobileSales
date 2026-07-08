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



public final class ListItemScheduleHeaderBinding implements ViewBinding {

   
    private final LinearLayout rootView;

   
    public final TextView titleTextView;

    private ListItemScheduleHeaderBinding(final LinearLayout linearLayout, final TextView textView) {
        rootView = linearLayout;
        titleTextView = textView;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ListItemScheduleHeaderBinding inflate(final LayoutInflater layoutInflater) {
        return ListItemScheduleHeaderBinding.inflate(layoutInflater, null, false);
    }

   
    public static ListItemScheduleHeaderBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.list_item_schedule_header, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ListItemScheduleHeaderBinding.bind(inflate);
    }

   
    public static ListItemScheduleHeaderBinding bind(final View view) {
        final TextView textView = ViewBindings.findChildViewById(view, R.id.titleTextView);
        if (null != textView) {
            return new ListItemScheduleHeaderBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.titleTextView));
    }
}
