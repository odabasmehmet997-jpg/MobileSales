package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
 
public final class UserreportsFormBinding implements ViewBinding {

    
    private final LinearLayout rootView;

    
    public final RecyclerView rwUserReportsForm;

    private UserreportsFormBinding(final LinearLayout linearLayout, final RecyclerView recyclerView) {
        rootView = linearLayout;
        rwUserReportsForm = recyclerView;
    }

    
    public LinearLayout getRoot() {
        return rootView;
    }

    
    public static UserreportsFormBinding inflate(final LayoutInflater layoutInflater) {
        return UserreportsFormBinding.inflate(layoutInflater, null, false);
    }

    
    public static UserreportsFormBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.userreports_form, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return UserreportsFormBinding.bind(inflate);
    }

    
    public static UserreportsFormBinding bind(final View view) {
        final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rwUserReportsForm);
        if (null != recyclerView) {
            return new UserreportsFormBinding((LinearLayout) view, recyclerView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.rwUserReportsForm));
    }
}
