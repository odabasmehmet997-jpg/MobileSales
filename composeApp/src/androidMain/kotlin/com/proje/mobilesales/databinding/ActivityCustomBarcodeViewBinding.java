package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.widget.TintableTextView;
public final class ActivityCustomBarcodeViewBinding implements ViewBinding {
    public final AppCompatImageView barcodePreview;
    public final View centerHorizont;
    public final AppCompatImageView imgClose;
    public final AppCompatImageView imgComplete;
    public final AppCompatImageView imgPause;
    public final AppCompatImageView imgSwitchFlashlight;
    public final LinearLayout lnBarcodeTextContainer;
    public final ListView lstBarcode;
    private final RelativeLayout rootView;
    public final TintableTextView tintableTextView;
    public final DecoratedBarcodeView zxingBarcodeScanner;
    private ActivityCustomBarcodeViewBinding(final RelativeLayout relativeLayout, final AppCompatImageView appCompatImageView, final View view, final AppCompatImageView appCompatImageView2, final AppCompatImageView appCompatImageView3, final AppCompatImageView appCompatImageView4, final AppCompatImageView appCompatImageView5, final LinearLayout linearLayout, final ListView listView, final TintableTextView tintableTextView, final DecoratedBarcodeView decoratedBarcodeView) {
        rootView = relativeLayout;
        barcodePreview = appCompatImageView;
        centerHorizont = view;
        imgClose = appCompatImageView2;
        imgComplete = appCompatImageView3;
        imgPause = appCompatImageView4;
        imgSwitchFlashlight = appCompatImageView5;
        lnBarcodeTextContainer = linearLayout;
        lstBarcode = listView;
        this.tintableTextView = tintableTextView;
        zxingBarcodeScanner = decoratedBarcodeView;
    }
    public RelativeLayout getRoot() {
        return rootView;
    }
    public static ActivityCustomBarcodeViewBinding inflate(final LayoutInflater layoutInflater) {
        return ActivityCustomBarcodeViewBinding.inflate(layoutInflater, null, false);
    }
    public static ActivityCustomBarcodeViewBinding inflate(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.activity_custom_barcode_view, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return ActivityCustomBarcodeViewBinding.bind(inflate);
    }
    public static ActivityCustomBarcodeViewBinding bind(final View view) {
        int i2 = R.id.barcodePreview;
        final AppCompatImageView appCompatImageView = ViewBindings.findChildViewById(view, R.id.barcodePreview);
        if (null != appCompatImageView) {
            i2 = R.id.centerHorizont;
            final View findChildViewById = ViewBindings.findChildViewById(view, R.id.centerHorizont);
            if (null != findChildViewById) {
                i2 = R.id.img_close;
                final AppCompatImageView appCompatImageView2 = ViewBindings.findChildViewById(view, R.id.img_close);
                if (null != appCompatImageView2) {
                    i2 = R.id.img_complete;
                    final AppCompatImageView appCompatImageView3 = ViewBindings.findChildViewById(view, R.id.img_complete);
                    if (null != appCompatImageView3) {
                        i2 = R.id.img_pause;
                        final AppCompatImageView appCompatImageView4 = ViewBindings.findChildViewById(view, R.id.img_pause);
                        if (null != appCompatImageView4) {
                            i2 = R.id.img_switch_flashlight;
                            final AppCompatImageView appCompatImageView5 = ViewBindings.findChildViewById(view, R.id.img_switch_flashlight);
                            if (null != appCompatImageView5) {
                                i2 = R.id.ln_barcodeTextContainer;
                                final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_barcodeTextContainer);
                                if (null != linearLayout) {
                                    i2 = R.id.lst_barcode;
                                    final ListView listView = ViewBindings.findChildViewById(view, R.id.lst_barcode);
                                    if (null != listView) {
                                        i2 = R.id.tintableTextView;
                                        final TintableTextView tintableTextView = ViewBindings.findChildViewById(view, R.id.tintableTextView);
                                        if (null != tintableTextView) {
                                            i2 = R.id.zxing_barcode_scanner;
                                            final DecoratedBarcodeView decoratedBarcodeView = ViewBindings.findChildViewById(view, R.id.zxing_barcode_scanner);
                                            if (null != decoratedBarcodeView) {
                                                return new ActivityCustomBarcodeViewBinding((RelativeLayout) view, appCompatImageView, findChildViewById, appCompatImageView2, appCompatImageView3, appCompatImageView4, appCompatImageView5, linearLayout, listView, tintableTextView, decoratedBarcodeView);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
