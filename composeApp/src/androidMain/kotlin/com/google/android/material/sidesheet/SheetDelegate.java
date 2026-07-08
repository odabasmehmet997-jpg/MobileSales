package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/*  INFO: loaded from: classes2.dex */
abstract class SheetDelegate {
    abstract int calculateInnerMargin(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams);

    abstract float calculateSlideOffset(int i2);

    abstract int getCoplanarSiblingAdjacentMargin(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams);

    abstract int getExpandedOffset();

    abstract int getHiddenOffset();

    abstract int getMaxViewPositionHorizontal();

    abstract int getMinViewPositionHorizontal();

    abstract <V extends View> int getOuterEdge(@NonNull V v);

    abstract int getParentInnerEdge(@NonNull CoordinatorLayout coordinatorLayout);

    abstract int getSheetEdge();

    abstract boolean isExpandingOutwards(float f2);

    abstract boolean isReleasedCloseToInnerEdge(@NonNull View view);

    abstract boolean isSwipeSignificant(float f2, float f3);

    abstract boolean shouldHide(@NonNull View view, float f2);

    abstract void updateCoplanarSiblingAdjacentMargin(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams, int i2);

    abstract void updateCoplanarSiblingLayoutParams(@NonNull ViewGroup.MarginLayoutParams marginLayoutParams, int i2, int i3);

    SheetDelegate() {
    }
}
