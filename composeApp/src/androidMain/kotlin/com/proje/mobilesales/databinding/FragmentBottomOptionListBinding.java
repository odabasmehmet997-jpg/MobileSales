package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class FragmentBottomOptionListBinding implements ViewBinding {
    public final LinearLayout lnButtonList;
    private final LinearLayout rootView;
    public final LinearLayout testid;
    public final AppCompatTextView txtDialogDescription;
    public final AppCompatTextView txtDialogTitle;
    private FragmentBottomOptionListBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2) {
        rootView = linearLayout;
        lnButtonList = linearLayout2;
        testid = linearLayout3;
        txtDialogDescription = appCompatTextView;
        txtDialogTitle = appCompatTextView2;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static FragmentBottomOptionListBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentBottomOptionListBinding.inflate(layoutInflater, null, false);
    }
    public static FragmentBottomOptionListBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_bottom_option_list, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentBottomOptionListBinding.bind(inflate);
    }
    public static FragmentBottomOptionListBinding bind(final View view) {
        int i2 = R.id.lnButtonList;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.lnButtonList);
        if (null != linearLayout) {
            i2 = R.id.testid;
            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.testid);
            if (null != linearLayout2) {
                i2 = R.id.txtDialogDescription;
                final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.txtDialogDescription);
                if (null != appCompatTextView) {
                    i2 = R.id.txtDialogTitle;
                    final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.txtDialogTitle);
                    if (null != appCompatTextView2) {
                        return new FragmentBottomOptionListBinding((LinearLayout) view, linearLayout, linearLayout2, appCompatTextView, appCompatTextView2);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
