package com.proje.mobilesales.core.interfaces;

import com.proje.mobilesales.core.base.BaseResult;
import com.proje.mobilesales.features.model.GroupItem;

public interface IEditSendFicheListener {
    void editSelectedFiche(BaseResult baseResult, GroupItem groupItem);
}
