package com.proje.mobilesales.databinding;

import android.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class PreferenceSeekbarBinding implements ViewBinding {

   
    private final LinearLayout rootView;

   
    public final SeekBar seekBar;

   
    public final TextView summary;

   
    public final TextView title;

   
    public final TextView txtSeekBarProgress;

    private PreferenceSeekbarBinding(final LinearLayout linearLayout, final SeekBar seekBar, final TextView textView, final TextView textView2, final TextView textView3) {
        rootView = linearLayout;
        this.seekBar = seekBar;
        summary = textView;
        title = textView2;
        txtSeekBarProgress = textView3;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static PreferenceSeekbarBinding inflate(final LayoutInflater layoutInflater) {
        return PreferenceSeekbarBinding.inflate(layoutInflater, null, false);
    }

   
    public static PreferenceSeekbarBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.preference_seekbar, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return PreferenceSeekbarBinding.bind(inflate);
    }

   
    public static PreferenceSeekbarBinding bind(final View view) {
        int i2 = R.id.seekBar;
        final SeekBar seekBar = ViewBindings.findChildViewById(view, R.id.seekBar);
        if (null != seekBar) {
            i2 = R.id.summary;
            final TextView textView = ViewBindings.findChildViewById(view, R.id.summary);
            if (null != textView) {
                i2 = R.id.title;
                final TextView textView2 = ViewBindings.findChildViewById(view, R.id.title);
                if (null != textView2) {
                    i2 = R.id.txtSeekBarProgress;
                    final TextView textView3 = ViewBindings.findChildViewById(view, R.id.txtSeekBarProgress);
                    if (null != textView3) {
                        return new PreferenceSeekbarBinding((LinearLayout) view, seekBar, textView, textView2, textView3);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
