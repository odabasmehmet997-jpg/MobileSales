package com.proje.mobilesales.core.masterpass;

import cardtek.masterpass.data.MasterPassCard;
import java.io.Serializable;

public class MasterPassCardItem extends MasterPassCard implements Serializable {
    private boolean mSelected;

    public boolean isSelected() {
        return this.mSelected;
    }

    public void setSelected(boolean z) {
        this.mSelected = z;
    }
}
