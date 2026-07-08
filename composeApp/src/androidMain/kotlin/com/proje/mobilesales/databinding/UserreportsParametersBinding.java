package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
 
public final class UserreportsParametersBinding implements ViewBinding {

    
    private final LinearLayout rootView;

    
    public final Button runQueryButton;

    
    public final RecyclerView rwParametersListView;

    private UserreportsParametersBinding(final LinearLayout linearLayout, final Button button, final RecyclerView recyclerView) {
        rootView = linearLayout;
        runQueryButton = button;
        rwParametersListView = recyclerView;
    }

    
    
    public LinearLayout getRoot() {
        return rootView;
    }

    
    public static UserreportsParametersBinding inflate(final LayoutInflater layoutInflater) {
        return UserreportsParametersBinding.inflate(layoutInflater, null, false);
    }

    
    public static UserreportsParametersBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.userreports_parameters, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return UserreportsParametersBinding.bind(inflate);
    }

    
    public static UserreportsParametersBinding bind(final View view) {
        int i2 = R.id.run_query_button;
        final Button button = ViewBindings.findChildViewById(view, R.id.run_query_button);
        if (null != button) {
            i2 = R.id.rwParametersListView;
            final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rwParametersListView);
            if (null != recyclerView) {
                return new UserreportsParametersBinding((LinearLayout) view, button, recyclerView);
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
