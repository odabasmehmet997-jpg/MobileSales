package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.viewbinding.ViewBinding;
import com.proje.mobilesales.R;



public final class PreferenceSpinnerBinding implements ViewBinding {

   
    private final AppCompatSpinner rootView;

   
    public final AppCompatSpinner spinner;

    private PreferenceSpinnerBinding(final AppCompatSpinner appCompatSpinner, final AppCompatSpinner appCompatSpinner2) {
        rootView = appCompatSpinner;
        spinner = appCompatSpinner2;
    }

    
   
    public AppCompatSpinner getRoot() {
        return rootView;
    }

   
    public static PreferenceSpinnerBinding inflate(final LayoutInflater layoutInflater) {
        return PreferenceSpinnerBinding.inflate(layoutInflater, null, false);
    }

   
    public static PreferenceSpinnerBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.preference_spinner, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return PreferenceSpinnerBinding.bind(inflate);
    }

   
    public static PreferenceSpinnerBinding bind(final View view) {
        if (null == view) {
            throw new NullPointerException("rootView");
        }
        final AppCompatSpinner appCompatSpinner = (AppCompatSpinner) view;
        return new PreferenceSpinnerBinding(appCompatSpinner, appCompatSpinner);
    }
}
