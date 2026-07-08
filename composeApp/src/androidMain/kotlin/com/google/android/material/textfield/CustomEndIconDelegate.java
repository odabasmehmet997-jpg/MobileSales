package com.google.android.material.textfield;

import androidx.annotation.NonNull;

class CustomEndIconDelegate extends EndIconDelegate {
    CustomEndIconDelegate(  EndCompoundLayout endCompoundLayout) {
        super(endCompoundLayout);
    }

    void setUp() {
        this.endLayout.setEndIconOnLongClickListener(null);
    }
}
