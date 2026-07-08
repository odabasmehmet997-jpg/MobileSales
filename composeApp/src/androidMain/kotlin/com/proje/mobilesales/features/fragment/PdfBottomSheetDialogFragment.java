package com.proje.mobilesales.features.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.proje.mobilesales.R;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: PdfBottomSheetDialogFragment.kt */

public final class PdfBottomSheetDialogFragment extends BottomSheetDialogFragment {
    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_pdf_bottom_sheet_dialog, viewGroup, false);
    }
}
