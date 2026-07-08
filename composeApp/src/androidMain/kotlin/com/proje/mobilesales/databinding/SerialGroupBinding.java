package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class SerialGroupBinding implements ViewBinding {

   
    public final ImageButton btnSearch;

   
    public final EditText edtSearch;

   
    private final LinearLayout rootView;

   
    public final RecyclerView rwSeriGrup;

    private SerialGroupBinding(final LinearLayout linearLayout, final ImageButton imageButton, final EditText editText, final RecyclerView recyclerView) {
        rootView = linearLayout;
        btnSearch = imageButton;
        edtSearch = editText;
        rwSeriGrup = recyclerView;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static SerialGroupBinding inflate(final LayoutInflater layoutInflater) {
        return SerialGroupBinding.inflate(layoutInflater, null, false);
    }

   
    public static SerialGroupBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.serial_group, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return SerialGroupBinding.bind(inflate);
    }

   
    public static SerialGroupBinding bind(final View view) {
        int i2 = R.id.btn_search;
        final ImageButton imageButton = ViewBindings.findChildViewById(view, R.id.btn_search);
        if (null != imageButton) {
            i2 = R.id.edt_search;
            final EditText editText = ViewBindings.findChildViewById(view, R.id.edt_search);
            if (null != editText) {
                i2 = R.id.rwSeriGrup;
                final RecyclerView recyclerView = ViewBindings.findChildViewById(view, R.id.rwSeriGrup);
                if (null != recyclerView) {
                    return new SerialGroupBinding((LinearLayout) view, imageButton, editText, recyclerView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
