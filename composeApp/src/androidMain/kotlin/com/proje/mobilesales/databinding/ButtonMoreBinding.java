package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.IconButton;

public final class ButtonMoreBinding implements ViewBinding {
    private final IconButton rootView;
    private ButtonMoreBinding(final IconButton iconButton) {
        rootView = iconButton;
    }
    public IconButton getRoot() {
        return rootView;
    }
    public static ButtonMoreBinding inflate(final LayoutInflater layoutInflater) {
        return ButtonMoreBinding.inflate(layoutInflater, null, false);
    }
    public static ButtonMoreBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.button_more, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ButtonMoreBinding.bind(inflate);
    }
    public static ButtonMoreBinding bind(final View view) {
        if (null == view) {
            throw new NullPointerException("rootView");
        }
        return new ButtonMoreBinding((IconButton) view);
    }
}
