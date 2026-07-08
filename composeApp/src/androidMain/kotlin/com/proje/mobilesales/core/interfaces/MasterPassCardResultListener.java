package com.proje.mobilesales.core.interfaces;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.proje.mobilesales.core.masterpass.MasterPassCardItem;

public interface MasterPassCardResultListener {
    void onCancelled(BottomSheetDialog bottomSheetDialog);

    void onSelected(BottomSheetDialog bottomSheetDialog, MasterPassCardItem masterPassCardItem, int i2);
}
