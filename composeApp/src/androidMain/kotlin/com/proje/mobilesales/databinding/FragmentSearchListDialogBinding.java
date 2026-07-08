package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class FragmentSearchListDialogBinding implements ViewBinding {

   
    public final ImageView clearButton;

   
    public final ImageView closeButton;

   
    public final AppCompatEditText edtSearch;

   
    public final RecyclerView list;

   
    private final LinearLayout rootView;

   
    public final AppCompatTextView txtDialogTitle;

    private FragmentSearchListDialogBinding(final LinearLayout linearLayout, final ImageView imageView, final ImageView imageView2, final AppCompatEditText appCompatEditText, final RecyclerView recyclerView, final AppCompatTextView appCompatTextView) {
        rootView = linearLayout;
        clearButton = imageView;
        closeButton = imageView2;
        edtSearch = appCompatEditText;
        list = recyclerView;
        txtDialogTitle = appCompatTextView;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static FragmentSearchListDialogBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentSearchListDialogBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentSearchListDialogBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_search_list_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentSearchListDialogBinding.bind(inflate);
    }

   
    public static FragmentSearchListDialogBinding bind(final View view) {
        int i2 = R.id.clearButton;
        final ImageView imageView = ViewBindings.findChildViewById(view, R.id.clearButton);
        if (null != imageView) {
            i2 = R.id.closeButton;
            final ImageView imageView2 = ViewBindings.findChildViewById(view, R.id.closeButton);
            if (null != imageView2) {
                i2 = R.id.edtSearch;
                final AppCompatEditText appCompatEditText = ViewBindings.findChildViewById(view, R.id.edtSearch);
                if (null != appCompatEditText) {
                    i2 = R.id.list;
                    final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.list);
                    if (null != recyclerView) {
                        i2 = R.id.txtDialogTitle;
                        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.txtDialogTitle);
                        if (null != appCompatTextView) {
                            return new FragmentSearchListDialogBinding((LinearLayout) view, imageView, imageView2, appCompatEditText, recyclerView, appCompatTextView);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
