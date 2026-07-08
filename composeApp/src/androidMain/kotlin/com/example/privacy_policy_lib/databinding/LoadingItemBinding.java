package com.example.privacy_policy_lib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.privacy_policy_lib.R;
 
public final class LoadingItemBinding implements ViewBinding {
    
    public final TextView pbText;
    
    public final ProgressBar progressBar;
    
    private final LinearLayout rootView;

    private LoadingItemBinding( LinearLayout linearLayout,  TextView textView,  ProgressBar progressBar) {
        this.rootView = linearLayout;
        this.pbText = textView;
        this.progressBar = progressBar;
    }
 
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static LoadingItemBinding inflate( LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    
    public static LoadingItemBinding inflate( LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.loading_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    
    public static LoadingItemBinding bind( View view) {
        int i2 = R.id.pbText;
        TextView textView = ViewBindings.findChildViewById(view, i2);
        if (textView != null) {
            i2 = R.id.progressBar;
            ProgressBar progressBar = ViewBindings.findChildViewById(view, i2);
            if (progressBar != null) {
                return new LoadingItemBinding((LinearLayout) view, textView, progressBar);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }
}
