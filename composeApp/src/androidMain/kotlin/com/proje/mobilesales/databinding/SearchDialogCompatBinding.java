package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class SearchDialogCompatBinding implements ViewBinding {

    /* renamed from: no */
   
    public final AppCompatButton f1222no;

   
    public final ProgressBar progress;

   
    private final LinearLayout rootView;

   
    public final RecyclerView rvItems;

   
    public final AppCompatEditText txtSearch;

   
    public final AppCompatTextView txtTitle;

   
    public final AppCompatButton yes;

    private SearchDialogCompatBinding(final LinearLayout linearLayout, final AppCompatButton appCompatButton, final ProgressBar progressBar, final RecyclerView recyclerView, final AppCompatEditText appCompatEditText, final AppCompatTextView appCompatTextView, final AppCompatButton appCompatButton2) {
        rootView = linearLayout;
        f1222no = appCompatButton;
        progress = progressBar;
        rvItems = recyclerView;
        txtSearch = appCompatEditText;
        txtTitle = appCompatTextView;
        yes = appCompatButton2;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static SearchDialogCompatBinding inflate(final LayoutInflater layoutInflater) {
        return SearchDialogCompatBinding.inflate(layoutInflater, null, false);
    }

   
    public static SearchDialogCompatBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.search_dialog_compat, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return SearchDialogCompatBinding.bind(inflate);
    }

   
    public static SearchDialogCompatBinding bind(final View view) {
        int i2 = R.id.f1138no;
        final AppCompatButton appCompatButton = ViewBindings.findChildViewById(view, R.id.f1138no);
        if (null != appCompatButton) {
            i2 = R.id.progress;
            final ProgressBar progressBar = ViewBindings.findChildViewById(view, R.id.progress);
            if (null != progressBar) {
                i2 = R.id.rv_items;
                final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rv_items);
                if (null != recyclerView) {
                    i2 = R.id.txt_search;
                    final AppCompatEditText appCompatEditText = ViewBindings.findChildViewById(view, R.id.txt_search);
                    if (null != appCompatEditText) {
                        i2 = R.id.txt_title;
                        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.txt_title);
                        if (null != appCompatTextView) {
                            i2 = R.id.yes;
                            final AppCompatButton appCompatButton2 = ViewBindings.findChildViewById(view, R.id.yes);
                            if (null != appCompatButton2) {
                                return new SearchDialogCompatBinding((LinearLayout) view, appCompatButton, progressBar, recyclerView, appCompatEditText, appCompatTextView, appCompatButton2);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
