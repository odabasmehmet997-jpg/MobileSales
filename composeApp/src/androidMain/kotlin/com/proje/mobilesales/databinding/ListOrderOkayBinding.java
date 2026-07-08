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



public final class ListOrderOkayBinding implements ViewBinding {

   
    public final ListView lvListOrderOkay;

   
    private final LinearLayout rootView;

   
    public final LinearLayout widget42;

    private ListOrderOkayBinding(final LinearLayout linearLayout, final ListView listView, final LinearLayout linearLayout2) {
        rootView = linearLayout;
        lvListOrderOkay = listView;
        widget42 = linearLayout2;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ListOrderOkayBinding inflate(final LayoutInflater layoutInflater) {
        return ListOrderOkayBinding.inflate(layoutInflater, null, false);
    }

   
    public static ListOrderOkayBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.list_order_okay, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ListOrderOkayBinding.bind(inflate);
    }

   
    public static ListOrderOkayBinding bind(final View view) {
        final ListView listView = ViewBindings.findChildViewById(view, R.id.lvListOrderOkay);
        if (null != listView) {
            final LinearLayout linearLayout = (LinearLayout) view;
            return new ListOrderOkayBinding(linearLayout, listView, linearLayout);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.lvListOrderOkay));
    }
}
