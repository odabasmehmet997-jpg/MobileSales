package com.proje.mobilesales.core.searchdialog;

import com.google.android.material.bottomsheet.BottomSheetDialog;



public interface BottomSheetDialogSearchResultListener<T> {
    void onCancelled(BottomSheetDialog bottomSheetDialog);

    void onSelected(BottomSheetDialog bottomSheetDialog, T t, int i2);
}
