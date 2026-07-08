package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class SalesVariantsBinding implements ViewBinding {

   
    public final AppCompatEditText edVariant;

   
    public final AppCompatImageButton imgBtnClearSearch;

   
    public final AppCompatImageButton imgBtnSearch;

   
    public final LinearLayout linearProgress;

   
    public final ListView lvVariants;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView tvCheckedCount;

   
    public final AppCompatTextView tvSerialNo;

   
    public final AppCompatTextView tvUserAmount;

    private SalesVariantsBinding(final LinearLayout linearLayout, final AppCompatEditText appCompatEditText, final AppCompatImageButton appCompatImageButton, final AppCompatImageButton appCompatImageButton2, final LinearLayout linearLayout2, final ListView listView, final AppCompatTextView appCompatTextView, final AppCompatTextView appCompatTextView2, final AppCompatTextView appCompatTextView3) {
        rootView = linearLayout;
        edVariant = appCompatEditText;
        imgBtnClearSearch = appCompatImageButton;
        imgBtnSearch = appCompatImageButton2;
        linearProgress = linearLayout2;
        lvVariants = listView;
        tvCheckedCount = appCompatTextView;
        tvSerialNo = appCompatTextView2;
        tvUserAmount = appCompatTextView3;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static SalesVariantsBinding inflate(final LayoutInflater layoutInflater) {
        return SalesVariantsBinding.inflate(layoutInflater, null, false);
    }

   
    public static SalesVariantsBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.sales_variants, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return SalesVariantsBinding.bind(inflate);
    }

   
    public static SalesVariantsBinding bind(final View view) {
        int i2 = R.id.edVariant;
        final AppCompatEditText appCompatEditText = ViewBindings.findChildViewById(view, R.id.edVariant);
        if (null != appCompatEditText) {
            i2 = R.id.imgBtnClearSearch;
            final AppCompatImageButton appCompatImageButton = ViewBindings.findChildViewById(view, R.id.imgBtnClearSearch);
            if (null != appCompatImageButton) {
                i2 = R.id.imgBtnSearch;
                final AppCompatImageButton appCompatImageButton2 = ViewBindings.findChildViewById(view, R.id.imgBtnSearch);
                if (null != appCompatImageButton2) {
                    i2 = R.id.linearProgress;
                    final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.linearProgress);
                    if (null != linearLayout) {
                        i2 = R.id.lvVariants;
                        final ListView listView = ViewBindings.findChildViewById(view, R.id.lvVariants);
                        if (null != listView) {
                            i2 = R.id.tvCheckedCount;
                            final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvCheckedCount);
                            if (null != appCompatTextView) {
                                i2 = R.id.tvSerialNo;
                                final AppCompatTextView appCompatTextView2 = ViewBindings.findChildViewById(view, R.id.tvSerialNo);
                                if (null != appCompatTextView2) {
                                    i2 = R.id.tvUserAmount;
                                    final AppCompatTextView appCompatTextView3 = ViewBindings.findChildViewById(view, R.id.tvUserAmount);
                                    if (null != appCompatTextView3) {
                                        return new SalesVariantsBinding((LinearLayout) view, appCompatEditText, appCompatImageButton, appCompatImageButton2, linearLayout, listView, appCompatTextView, appCompatTextView2, appCompatTextView3);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
