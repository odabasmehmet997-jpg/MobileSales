package com.example.privacy_policy_lib.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.privacy_policy_lib.R;
 
public final class FragmentContractsBinding implements ViewBinding {
    public final AppCompatButton btnRead;
    public final ConstraintLayout container;
    public final TextView explanation;
    public final RecyclerView rcw;
    private final ConstraintLayout rootView;
    public final TextView title;
    private FragmentContractsBinding( ConstraintLayout constraintLayout,  AppCompatButton appCompatButton,  ConstraintLayout constraintLayout2,  TextView textView,  RecyclerView recyclerView,  TextView textView2) {
        this.rootView = constraintLayout;
        this.btnRead = appCompatButton;
        this.container = constraintLayout2;
        this.explanation = textView;
        this.rcw = recyclerView;
        this.title = textView2;
    }
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    
    public static FragmentContractsBinding inflate( LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    
    public static FragmentContractsBinding inflate( LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_contracts, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    
    public static FragmentContractsBinding bind( View view) {
        int i2 = R.id.btnRead;
        AppCompatButton appCompatButton = ViewBindings.findChildViewById(view, i2);
        if (appCompatButton != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) view;
            i2 = R.id.explanation;
            TextView textView = ViewBindings.findChildViewById(view, i2);
            if (textView != null) {
                i2 = R.id.rcw;
                RecyclerView recyclerView = ViewBindings.findChildViewById(view, i2);
                if (recyclerView != null) {
                    i2 = R.id.title;
                    TextView textView2 = ViewBindings.findChildViewById(view, i2);
                    if (textView2 != null) {
                        return new FragmentContractsBinding(constraintLayout, appCompatButton, constraintLayout, textView, recyclerView, textView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i2)));
    }
}
