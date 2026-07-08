package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class FichelistBinding implements ViewBinding {
    public final ListView listFicheList;
    private final LinearLayout rootView;
    private FichelistBinding(final LinearLayout linearLayout, final ListView listView) {
        rootView = linearLayout;
        listFicheList = listView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static FichelistBinding inflate(final LayoutInflater layoutInflater) {
        return FichelistBinding.inflate(layoutInflater, null, false);
    }
    public static FichelistBinding inflate(final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fichelist, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FichelistBinding.bind(inflate);
    }
    public static FichelistBinding bind(final View view) {
        final ListView listView = ViewBindings.findChildViewById(view, R.id.list_fiche_list);
        if (null != listView) {
            return new FichelistBinding((LinearLayout) view, listView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.list_fiche_list));
    }
}
