package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;

public final class AdapterTransferRowBinding implements ViewBinding {
    private final CardView rootView;
    public final AppCompatTextView twMenuItem;
    private AdapterTransferRowBinding(final CardView cardView, final AppCompatTextView appCompatTextView) {
        rootView = cardView;
        twMenuItem = appCompatTextView;
    }
    public CardView getRoot() {
        return rootView;
    }
    public static AdapterTransferRowBinding inflate(final LayoutInflater layoutInflater) {
        return AdapterTransferRowBinding.inflate(layoutInflater, null, false);
    }
    public static AdapterTransferRowBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.adapter_transfer_row, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return AdapterTransferRowBinding.bind(inflate);
    }
    public static AdapterTransferRowBinding bind(final View view) {
        final AppCompatTextView appCompatTextView = ViewBindings.findChildViewById(view, R.id.twMenuItem);
        if (null != appCompatTextView) {
            return new AdapterTransferRowBinding((CardView) view, appCompatTextView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.twMenuItem));
    }
}
