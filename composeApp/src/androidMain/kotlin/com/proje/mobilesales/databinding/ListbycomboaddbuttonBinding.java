package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class ListbycomboaddbuttonBinding implements ViewBinding {

   
    public final AppCompatTextView NAME;

   
    public final AppCompatButton btnAdd;

   
    public final RecyclerView lvListBycombo;

   
    public final LinearLayout lyHeader;

   
    public final LinearLayout noEntry;

   
    private final LinearLayout rootView;

   
    public final AppCompatSpinner spnFicheType;

   
    public final LinearLayout widget42;

    private ListbycomboaddbuttonBinding(final LinearLayout linearLayout, final AppCompatTextView appCompatTextView, final AppCompatButton appCompatButton, final RecyclerView recyclerView, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final AppCompatSpinner appCompatSpinner, final LinearLayout linearLayout4) {
        rootView = linearLayout;
        NAME = appCompatTextView;
        btnAdd = appCompatButton;
        lvListBycombo = recyclerView;
        lyHeader = linearLayout2;
        noEntry = linearLayout3;
        spnFicheType = appCompatSpinner;
        widget42 = linearLayout4;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static ListbycomboaddbuttonBinding inflate(final LayoutInflater layoutInflater) {
        return ListbycomboaddbuttonBinding.inflate(layoutInflater, null, false);
    }

   
    public static ListbycomboaddbuttonBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.listbycomboaddbutton, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ListbycomboaddbuttonBinding.bind(inflate);
    }

   
    public static ListbycomboaddbuttonBinding bind(final View view) {
        int i2 = R.id.NAME;
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.NAME);
        if (null != appCompatTextView) {
            i2 = R.id.btnAdd;
            final AppCompatButton appCompatButton = ViewBindings.findChildViewById(view, R.id.btnAdd);
            if (null != appCompatButton) {
                i2 = R.id.lvList_bycombo;
                final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.lvList_bycombo);
                if (null != recyclerView) {
                    i2 = R.id.lyHeader;
                    final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.lyHeader);
                    if (null != linearLayout) {
                        i2 = R.id.noEntry;
                        final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.noEntry);
                        if (null != linearLayout2) {
                            i2 = R.id.spnFicheType;
                            final AppCompatSpinner appCompatSpinner = ViewBindings.findChildViewById(view, R.id.spnFicheType);
                            if (null != appCompatSpinner) {
                                final LinearLayout linearLayout3 = (LinearLayout) view;
                                return new ListbycomboaddbuttonBinding(linearLayout3, appCompatTextView, appCompatButton, recyclerView, linearLayout, linearLayout2, appCompatSpinner, linearLayout3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
