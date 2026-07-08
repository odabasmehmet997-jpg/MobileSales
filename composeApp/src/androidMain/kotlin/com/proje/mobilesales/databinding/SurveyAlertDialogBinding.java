package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class SurveyAlertDialogBinding implements ViewBinding {

   
    public final CheckBox checkBox;

   
    private final LinearLayout rootView;

    private SurveyAlertDialogBinding(final LinearLayout linearLayout, final CheckBox checkBox) {
        rootView = linearLayout;
        this.checkBox = checkBox;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static SurveyAlertDialogBinding inflate(final LayoutInflater layoutInflater) {
        return SurveyAlertDialogBinding.inflate(layoutInflater, null, false);
    }

   
    public static SurveyAlertDialogBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.survey_alert_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return SurveyAlertDialogBinding.bind(inflate);
    }

   
    public static SurveyAlertDialogBinding bind(final View view) {
        final CheckBox checkBox = ViewBindings.findChildViewById(view, R.id.checkBox);
        if (null != checkBox) {
            return new SurveyAlertDialogBinding((LinearLayout) view, checkBox);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.checkBox));
    }
}
