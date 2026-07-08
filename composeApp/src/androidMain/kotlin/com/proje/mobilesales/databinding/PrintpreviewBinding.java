package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class PrintpreviewBinding implements ViewBinding {

   
    public final ScrollView ScrollView01;

   
    private final LinearLayout rootView;

   
    public final AppCompatSeekBar seekChangeTextsize;

   
    public final AppCompatTextView tvPreview;

   
    public final LinearLayout widget42;

    private PrintpreviewBinding(final LinearLayout linearLayout, final ScrollView scrollView, final AppCompatSeekBar appCompatSeekBar, final AppCompatTextView appCompatTextView, final LinearLayout linearLayout2) {
        rootView = linearLayout;
        ScrollView01 = scrollView;
        seekChangeTextsize = appCompatSeekBar;
        tvPreview = appCompatTextView;
        widget42 = linearLayout2;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static PrintpreviewBinding inflate(final LayoutInflater layoutInflater) {
        return PrintpreviewBinding.inflate(layoutInflater, null, false);
    }

   
    public static PrintpreviewBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.printpreview, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return PrintpreviewBinding.bind(inflate);
    }

   
    public static PrintpreviewBinding bind(final View view) {
        int i2 = R.id.ScrollView01;
        final ScrollView scrollView = ViewBindings.findChildViewById(view, R.id.ScrollView01);
        if (null != scrollView) {
            i2 = R.id.seek_change_textsize;
            final AppCompatSeekBar appCompatSeekBar = ViewBindings.findChildViewById(view, R.id.seek_change_textsize);
            if (null != appCompatSeekBar) {
                i2 = R.id.tvPreview;
                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvPreview);
                if (null != appCompatTextView) {
                    final LinearLayout linearLayout = (LinearLayout) view;
                    return new PrintpreviewBinding(linearLayout, scrollView, appCompatSeekBar, appCompatTextView, linearLayout);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
