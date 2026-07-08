package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.proje.mobilesales.R;

public final class HelpPplzFontBinding implements ViewBinding {

    private final ScrollView rootView;

    private HelpPplzFontBinding(final ScrollView scrollView) {
        rootView = scrollView;
    }
    public ScrollView getRoot() {
        return rootView;
    }
    public static HelpPplzFontBinding inflate(final LayoutInflater layoutInflater) {
        return HelpPplzFontBinding.inflate(layoutInflater, null, false);
    }
    public static HelpPplzFontBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.help_pplz_font, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return HelpPplzFontBinding.bind(inflate);
    }
    public static HelpPplzFontBinding bind(final View view) {
        if (null == view) {
            throw new NullPointerException("rootView");
        }
        return new HelpPplzFontBinding((ScrollView) view);
    }
}
