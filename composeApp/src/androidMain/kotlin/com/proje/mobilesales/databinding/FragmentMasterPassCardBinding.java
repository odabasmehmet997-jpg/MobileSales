package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class FragmentMasterPassCardBinding implements ViewBinding {

   
    public final LinearLayout cardContainer;

   
    public final AppCompatTextView cardName;

   
    public final AppCompatTextView cardNumber;

   
    public final AppCompatCheckedTextView chkSelected;

   
    private final LinearLayout rootView;

    private FragmentMasterPassCardBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatCheckedTextView appCompatCheckedTextView) {
        rootView = linearLayout;
        cardContainer = linearLayout2;
        cardName = appCompatTextView;
        cardNumber = appCompatTextView2;
        chkSelected = appCompatCheckedTextView;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static FragmentMasterPassCardBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentMasterPassCardBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentMasterPassCardBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_master_pass_card, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentMasterPassCardBinding.bind(inflate);
    }

   
    public static FragmentMasterPassCardBinding bind(final View view) {
        int i2 = R.id.card_container;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.card_container);
        if (null != linearLayout) {
            i2 = R.id.card_name;
            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.card_name);
            if (null != appCompatTextView) {
                i2 = R.id.card_number;
                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.card_number);
                if (null != appCompatTextView2) {
                    i2 = R.id.chkSelected;
                    final AppCompatCheckedTextView appCompatCheckedTextView = ViewBindings.findChildViewById(view, R.id.chkSelected);
                    if (null != appCompatCheckedTextView) {
                        return new FragmentMasterPassCardBinding((LinearLayout) view, linearLayout, appCompatTextView, appCompatTextView2, appCompatCheckedTextView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
