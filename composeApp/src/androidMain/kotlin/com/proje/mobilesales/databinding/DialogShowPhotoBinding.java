package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
public final class DialogShowPhotoBinding implements ViewBinding {
    public final ImageView imgPhoto;
    private final LinearLayout rootView;
    private DialogShowPhotoBinding(final LinearLayout linearLayout, final ImageView imageView) {
        rootView = linearLayout;
        imgPhoto = imageView;
    }
    public LinearLayout getRoot() {
        return rootView;
    }
    public static DialogShowPhotoBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.dialog_show_photo, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return DialogShowPhotoBinding.bind(inflate);
    }
    public static DialogShowPhotoBinding bind(final View view) {
        final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_photo);
        if (imageView != null) {
            return new DialogShowPhotoBinding((LinearLayout) view, imageView);
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(R.id.img_photo));
    }
}
