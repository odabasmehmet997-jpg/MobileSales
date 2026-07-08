package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;
 
public final class ActivityImageDialogBinding implements ViewBinding {
    public final AppCompatImageButton cancelImage;
    public final AppCompatImageButton deleteImage;
    public final ImageView imgPhoto;
    private final ConstraintLayout rootView;
    public final AppCompatImageButton saveImage;
    public final AppCompatImageButton updateImage;
    private ActivityImageDialogBinding( final ConstraintLayout constraintLayout,  final AppCompatImageButton appCompatImageButton,  final AppCompatImageButton appCompatImageButton2,  final ImageView imageView,  final AppCompatImageButton appCompatImageButton3,  final AppCompatImageButton appCompatImageButton4) {
        rootView = constraintLayout;
        cancelImage = appCompatImageButton;
        deleteImage = appCompatImageButton2;
        imgPhoto = imageView;
        saveImage = appCompatImageButton3;
        updateImage = appCompatImageButton4;
    }
    public ConstraintLayout getRoot() {
        return rootView;
    }
    public static ActivityImageDialogBinding inflate( final LayoutInflater layoutInflater) {
        return ActivityImageDialogBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityImageDialogBinding inflate( final LayoutInflater layoutInflater,  final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_image_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityImageDialogBinding.bind(inflate);
    }
    public static ActivityImageDialogBinding bind( final View view) {
        int i2 = R.id.cancelImage;
        final AppCompatImageButton appCompatImageButton = ViewBindings.findChildViewById(view, R.id.cancelImage);
        if (null != appCompatImageButton) {
            i2 = R.id.deleteImage;
            final AppCompatImageButton appCompatImageButton2 = ViewBindings.findChildViewById(view, R.id.deleteImage);
            if (null != appCompatImageButton2) {
                i2 = R.id.img_photo;
                final ImageView imageView = ViewBindings.findChildViewById(view, R.id.img_photo);
                if (null != imageView) {
                    i2 = R.id.saveImage;
                    final AppCompatImageButton appCompatImageButton3 = ViewBindings.findChildViewById(view, R.id.saveImage);
                    if (null != appCompatImageButton3) {
                        i2 = R.id.updateImage;
                        final AppCompatImageButton appCompatImageButton4 = ViewBindings.findChildViewById(view, R.id.updateImage);
                        if (null != appCompatImageButton4) {
                            return new ActivityImageDialogBinding((ConstraintLayout) view, appCompatImageButton, appCompatImageButton2, imageView, appCompatImageButton3, appCompatImageButton4);
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
