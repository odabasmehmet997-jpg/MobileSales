package com.proje.mobilesales.core.interfaces;

import androidx.appcompat.view.ActionMode;

public interface ActionModeDelegate {
    boolean isInActionMode();

    boolean startActionMode(ActionMode.Callback callback);

    void stopActionMode();
}
