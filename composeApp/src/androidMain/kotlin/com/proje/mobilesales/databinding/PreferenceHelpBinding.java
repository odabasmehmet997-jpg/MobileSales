package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.TintableTextView;



public final class PreferenceHelpBinding implements ViewBinding {

   
    private final TintableTextView rootView;

   
    public final TintableTextView title;

    private PreferenceHelpBinding(final TintableTextView tintableTextView, final TintableTextView tintableTextView2) {
        rootView = tintableTextView;
        title = tintableTextView2;
    }

    
   
    public TintableTextView getRoot() {
        return rootView;
    }

   
    public static PreferenceHelpBinding inflate(final LayoutInflater layoutInflater) {
        return PreferenceHelpBinding.inflate(layoutInflater, null, false);
    }

   
    public static PreferenceHelpBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.preference_help, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return PreferenceHelpBinding.bind(inflate);
    }

   
    public static PreferenceHelpBinding bind(final View view) {
        if (null == view) {
            throw new NullPointerException("rootView");
        }
        final TintableTextView tintableTextView = (TintableTextView) view;
        return new PreferenceHelpBinding(tintableTextView, tintableTextView);
    }
}
