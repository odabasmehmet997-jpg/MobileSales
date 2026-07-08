package com.proje.mobilesales.databinding;

import android.R;
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



public final class PreferenceCategoryHintBinding implements ViewBinding {

   
    private final LinearLayout rootView;

   
    public final TextView summary;

   
    public final TextView title;

    private PreferenceCategoryHintBinding(final LinearLayout linearLayout, final TextView textView, final TextView textView2) {
        rootView = linearLayout;
        summary = textView;
        title = textView2;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static PreferenceCategoryHintBinding inflate(final LayoutInflater layoutInflater) {
        return PreferenceCategoryHintBinding.inflate(layoutInflater, null, false);
    }

   
    public static PreferenceCategoryHintBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.preference_category_hint, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return PreferenceCategoryHintBinding.bind(inflate);
    }

   
    public static PreferenceCategoryHintBinding bind(final View view) {
        int i2 = R.id.summary;
        final TextView textView = ViewBindings.findChildViewById(view, R.id.summary);
        if (null != textView) {
            i2 = R.id.title;
            final TextView textView2 = ViewBindings.findChildViewById(view, R.id.title);
            if (null != textView2) {
                return new PreferenceCategoryHintBinding((LinearLayout) view, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
