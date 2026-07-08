package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class RowMenuListviewBinding implements ViewBinding {

   
    private final LinearLayoutCompat rootView;

   
    public final AppCompatTextView tvRowMenuListview;

    private RowMenuListviewBinding(final LinearLayoutCompat linearLayoutCompat, final AppCompatTextView appCompatTextView) {
        rootView = linearLayoutCompat;
        tvRowMenuListview = appCompatTextView;
    }

    
   
    public LinearLayoutCompat getRoot() {
        return rootView;
    }

   
    public static RowMenuListviewBinding inflate(final LayoutInflater layoutInflater) {
        return RowMenuListviewBinding.inflate(layoutInflater, null, false);
    }

   
    public static RowMenuListviewBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.row_menu_listview, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return RowMenuListviewBinding.bind(inflate);
    }

   
    public static RowMenuListviewBinding bind(final View view) {
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tv_row_menu_listview);
        if (null != appCompatTextView) {
            return new RowMenuListviewBinding((LinearLayoutCompat) view, appCompatTextView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.tv_row_menu_listview));
    }
}
