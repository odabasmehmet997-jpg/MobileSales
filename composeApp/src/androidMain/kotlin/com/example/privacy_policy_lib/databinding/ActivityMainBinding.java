package com.example.privacy_policy_lib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.example.privacy_policy_lib.R;

public final class ActivityMainBinding implements ViewBinding {
    public final ConstraintLayout main;
    private final ConstraintLayout rootView;
    private ActivityMainBinding(ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2) {
        this.rootView = constraintLayout;
        this.main = constraintLayout2;
    }
    public ConstraintLayout getRoot() {
        return this.rootView;
    }
    public static ActivityMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }
    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_main, viewGroup, false);
        if (z) {
            assert viewGroup != null;
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }
    public static ActivityMainBinding bind(View view) {
        if (view != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            return new ActivityMainBinding(constraintLayout, constraintLayout);
        }
        throw new NullPointerException("rootView");
    }
}
