package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ListCheckboxItemBinding implements ViewBinding {

   
    public final AppCompatCheckBox cbListItem;

   
    public final AppCompatEditText edtUserAmount;

   
    public final HorizontalScrollView expDateScrollView;

   
    public final LinearLayout linearCheckList;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView tvAmount;

   
    public final TextView tvCode;

   
    public final TextView tvExpDate;

   
    public final AppCompatSpinner tvUnit;

    private ListCheckboxItemBinding(final LinearLayout linearLayout, final AppCompatCheckBox appCompatCheckBox, final AppCompatEditText appCompatEditText, final HorizontalScrollView horizontalScrollView, final LinearLayout linearLayout2, final AppCompatTextView appCompatTextView, final TextView textView, final TextView textView2, final AppCompatSpinner appCompatSpinner) {
        rootView = linearLayout;
        cbListItem = appCompatCheckBox;
        edtUserAmount = appCompatEditText;
        expDateScrollView = horizontalScrollView;
        linearCheckList = linearLayout2;
        tvAmount = appCompatTextView;
        tvCode = textView;
        tvExpDate = textView2;
        tvUnit = appCompatSpinner;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ListCheckboxItemBinding inflate(final LayoutInflater layoutInflater) {
        return ListCheckboxItemBinding.inflate(layoutInflater, null, false);
    }

   
    public static ListCheckboxItemBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.list_checkbox_item, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ListCheckboxItemBinding.bind(inflate);
    }

   
    public static ListCheckboxItemBinding bind(final View view) {
        int i2 = R.id.cbListItem;
        final AppCompatCheckBox appCompatCheckBox = ViewBindings.findChildViewById(view, R.id.cbListItem);
        if (null != appCompatCheckBox) {
            i2 = R.id.edtUserAmount;
            final AppCompatEditText appCompatEditText = ViewBindings.findChildViewById(view, R.id.edtUserAmount);
            if (null != appCompatEditText) {
                i2 = R.id.expDateScrollView;
                final HorizontalScrollView horizontalScrollView = ViewBindings.findChildViewById(view, R.id.expDateScrollView);
                if (null != horizontalScrollView) {
                    final LinearLayout linearLayout = (LinearLayout) view;
                    i2 = R.id.tvAmount;
                    final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.tvAmount);
                    if (null != appCompatTextView) {
                        i2 = R.id.tvCode;
                        final TextView textView = ViewBindings.findChildViewById(view, R.id.tvCode);
                        if (null != textView) {
                            i2 = R.id.tvExpDate;
                            final TextView textView2 = ViewBindings.findChildViewById(view, R.id.tvExpDate);
                            if (null != textView2) {
                                i2 = R.id.tvUnit;
                                final AppCompatSpinner appCompatSpinner = ViewBindings.findChildViewById(view, R.id.tvUnit);
                                if (null != appCompatSpinner) {
                                    return new ListCheckboxItemBinding(linearLayout, appCompatCheckBox, appCompatEditText, horizontalScrollView, linearLayout, appCompatTextView, textView, textView2, appCompatSpinner);
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
