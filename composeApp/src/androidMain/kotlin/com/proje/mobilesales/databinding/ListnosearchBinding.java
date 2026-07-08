package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ListnosearchBinding implements ViewBinding {

   
    public final AppCompatTextView NAME;

   
    public final ListView lvListListnosearch;

   
    private final LinearLayout rootView;

   
    public final LinearLayout widget42;

    private ListnosearchBinding(final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final ListView listView, final LinearLayout linearLayout2) {
        rootView = linearLayout;
        NAME = appCompatTextView;
        lvListListnosearch = listView;
        widget42 = linearLayout2;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ListnosearchBinding inflate(final LayoutInflater layoutInflater) {
        return ListnosearchBinding.inflate(layoutInflater, null, false);
    }

   
    public static ListnosearchBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.listnosearch, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ListnosearchBinding.bind(inflate);
    }

   
    public static ListnosearchBinding bind(final View view) {
        int i2 = R.id.NAME;
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.NAME);
        if (null != appCompatTextView) {
            i2 = R.id.lvList_listnosearch;
            final ListView listView = ViewBindings.findChildViewById(view, R.id.lvList_listnosearch);
            if (null != listView) {
                final LinearLayout linearLayout = (LinearLayout) view;
                return new ListnosearchBinding(linearLayout, appCompatTextView, listView, linearLayout);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
