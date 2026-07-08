package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class TestBinding implements ViewBinding {
    public final ExpandableListView expandableListView1;
    private final LinearLayout rootView;
    private TestBinding(final LinearLayout linearLayout, final ExpandableListView expandableListView) {
        rootView = linearLayout;
        expandableListView1 = expandableListView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static TestBinding inflate(final LayoutInflater layoutInflater) {
        return TestBinding.inflate(layoutInflater, null, false);
    }
    public static TestBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.test, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return TestBinding.bind(inflate);
    }
    public static TestBinding bind(final View view) {
        final ExpandableListView expandableListView = ViewBindings.findChildViewById(view, R.id.expandableListView1);
        if (null != expandableListView) {
            return new TestBinding((LinearLayout) view, expandableListView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.expandableListView1));
    }
}
