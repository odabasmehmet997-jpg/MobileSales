package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class RowcsdeailBinding implements ViewBinding {

   
    public final TextView CDUEDATE;

   
    public final TextView CSPECODE;

   
    public final TextView CTOTAL;

   
    public final TextView LOGICALREF;

   
    private final RelativeLayout rootView;

    private RowcsdeailBinding(final RelativeLayout relativeLayout, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4) {
        rootView = relativeLayout;
        CDUEDATE = textView;
        CSPECODE = textView2;
        CTOTAL = textView3;
        LOGICALREF = textView4;
    }
    public RelativeLayout getRoot() {
        return rootView;
    }

   
    public static RowcsdeailBinding inflate(final LayoutInflater layoutInflater) {
        return RowcsdeailBinding.inflate(layoutInflater, null, false);
    }

   
    public static RowcsdeailBinding inflate(final LayoutInflater layoutInflater,   final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.rowcsdeail, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return RowcsdeailBinding.bind(inflate);
    }

   
    public static RowcsdeailBinding bind(final View view) {
        int i2 = R.id.CDUEDATE;
        final TextView textView = ViewBindings.findChildViewById(view, R.id.CDUEDATE);
        if (null != textView) {
            i2 = R.id.CSPECODE;
            final TextView textView2 = ViewBindings.findChildViewById(view, R.id.CSPECODE);
            if (null != textView2) {
                i2 = R.id.CTOTAL;
                final TextView textView3 = ViewBindings.findChildViewById(view, R.id.CTOTAL);
                if (null != textView3) {
                    i2 = R.id.LOGICALREF;
                    final TextView textView4 = ViewBindings.findChildViewById(view, R.id.LOGICALREF);
                    if (null != textView4) {
                        return new RowcsdeailBinding((RelativeLayout) view, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
