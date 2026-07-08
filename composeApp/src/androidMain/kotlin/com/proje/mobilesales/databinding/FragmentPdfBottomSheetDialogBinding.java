package com.proje.mobilesales.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.proje.mobilesales.R;



public final class FragmentPdfBottomSheetDialogBinding implements ViewBinding {

   
    public final LinearLayout lnClearPdfPath;

   
    public final LinearLayout lnDownloadPdf;

   
    public final LinearLayout lnSharePdf;

   
    public final LinearLayout lnShowPdf;

   
    private final LinearLayout rootView;

    private FragmentPdfBottomSheetDialogBinding(final LinearLayout linearLayout, final LinearLayout linearLayout2, final LinearLayout linearLayout3, final LinearLayout linearLayout4, final LinearLayout linearLayout5) {
        rootView = linearLayout;
        lnClearPdfPath = linearLayout2;
        lnDownloadPdf = linearLayout3;
        lnSharePdf = linearLayout4;
        lnShowPdf = linearLayout5;
    }

    
   
    public LinearLayout getRoot() {
        return rootView;
    }

   
    public static FragmentPdfBottomSheetDialogBinding inflate(final LayoutInflater layoutInflater) {
        return FragmentPdfBottomSheetDialogBinding.inflate(layoutInflater, null, false);
    }

   
    public static FragmentPdfBottomSheetDialogBinding inflate(final LayoutInflater layoutInflater, @Nullable final ViewGroup viewGroup, final boolean z) {
        final View inflate = layoutInflater.inflate(R.layout.fragment_pdf_bottom_sheet_dialog, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return FragmentPdfBottomSheetDialogBinding.bind(inflate);
    }

   
    public static FragmentPdfBottomSheetDialogBinding bind(final View view) {
        int i2 = R.id.ln_clear_pdf_path;
        final LinearLayout linearLayout = ViewBindings.findChildViewById(view, R.id.ln_clear_pdf_path);
        if (null != linearLayout) {
            i2 = R.id.ln_download_pdf;
            final LinearLayout linearLayout2 = ViewBindings.findChildViewById(view, R.id.ln_download_pdf);
            if (null != linearLayout2) {
                i2 = R.id.ln_share_pdf;
                final LinearLayout linearLayout3 = ViewBindings.findChildViewById(view, R.id.ln_share_pdf);
                if (null != linearLayout3) {
                    i2 = R.id.ln_show_pdf;
                    final LinearLayout linearLayout4 = ViewBindings.findChildViewById(view, R.id.ln_show_pdf);
                    if (null != linearLayout4) {
                        return new FragmentPdfBottomSheetDialogBinding((LinearLayout) view, linearLayout, linearLayout2, linearLayout3, linearLayout4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: " + view.getResources().getResourceName(i2));
    }
}
