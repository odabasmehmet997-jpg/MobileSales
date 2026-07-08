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



public final class ListItemMarketMessageBinding implements ViewBinding {

   
    private final LinearLayout rootView;

   
    public final TextView titleTextView;

    private ListItemMarketMessageBinding(final LinearLayout linearLayout, final TextView textView) {
        rootView = linearLayout;
        titleTextView = textView;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ListItemMarketMessageBinding inflate(final LayoutInflater layoutInflater) {
        return ListItemMarketMessageBinding.inflate(layoutInflater, null, false);
    }

   
    public static ListItemMarketMessageBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.list_item_market_message, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ListItemMarketMessageBinding.bind(inflate);
    }

   
    public static ListItemMarketMessageBinding bind(final View view) {
        final TextView textView = ViewBindings.findChildViewById(view, R.id.titleTextView);
        if (null != textView) {
            return new ListItemMarketMessageBinding((LinearLayout) view, textView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.titleTextView));
    }
}
