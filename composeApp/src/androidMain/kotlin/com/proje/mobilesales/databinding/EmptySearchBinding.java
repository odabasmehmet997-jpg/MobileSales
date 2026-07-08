package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.proje.mobilesales.R;
public final class EmptySearchBinding implements ViewBinding {
    public final RelativeLayout emptySearch;
    private final RelativeLayout rootView;
    private EmptySearchBinding(final RelativeLayout relativeLayout, final RelativeLayout relativeLayout2) {
        rootView = relativeLayout;
        emptySearch = relativeLayout2;
    }
    public RelativeLayout getRoot() {
        return rootView;
    }
    public static EmptySearchBinding inflate(final LayoutInflater layoutInflater) {
        return EmptySearchBinding.inflate(layoutInflater, null, false);
    }
    public static EmptySearchBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.empty_search, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return EmptySearchBinding.bind(inflate);
    }
    public static EmptySearchBinding bind(final View view) {
        if (null == view) {
            throw new NullPointerException("rootView");
        }
        final RelativeLayout relativeLayout = (RelativeLayout) view;
        return new EmptySearchBinding(relativeLayout, relativeLayout);
    }
}
