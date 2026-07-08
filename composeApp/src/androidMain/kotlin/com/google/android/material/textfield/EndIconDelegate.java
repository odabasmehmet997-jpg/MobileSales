package com.google.android.material.textfield;

import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.internal.CheckableImageButton;

/*  INFO: loaded from: classes2.dex */
abstract class EndIconDelegate {
    final Context context;
    final CheckableImageButton endIconView;
    final EndCompoundLayout endLayout;
    final TextInputLayout textInputLayout;

    void afterEditTextChanged(Editable editable) {
    }

    void beforeEditTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
    }

    @StringRes
    int getIconContentDescriptionResId() {
        return 0;
    }

    @DrawableRes
    int getIconDrawableResId() {
        return 0;
    }

    View.OnFocusChangeListener getOnEditTextFocusChangeListener() {
        return null;
    }

    View.OnClickListener getOnIconClickListener() {
        return null;
    }

    View.OnFocusChangeListener getOnIconViewFocusChangeListener() {
        return null;
    }

    AccessibilityManagerCompat.TouchExplorationStateChangeListener getTouchExplorationStateChangeListener() {
        return null;
    }

    boolean isBoxBackgroundModeSupported(int i2) {
        return true;
    }

    boolean isIconActivable() {
        return false;
    }

    boolean isIconActivated() {
        return false;
    }

    boolean isIconCheckable() {
        return false;
    }

    boolean isIconChecked() {
        return false;
    }

    void onEditTextAttached(@Nullable EditText editText) {
    }

    void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    void onPopulateAccessibilityEvent(View view, @NonNull AccessibilityEvent accessibilityEvent) {
    }

    void onSuffixVisibilityChanged(boolean z) {
    }

    void setUp() {
    }

    boolean shouldTintIconOnError() {
        return false;
    }

    void tearDown() {
    }

    EndIconDelegate(@NonNull EndCompoundLayout endCompoundLayout) {
        this.textInputLayout = endCompoundLayout.textInputLayout;
        this.endLayout = endCompoundLayout;
        this.context = endCompoundLayout.getContext();
        this.endIconView = endCompoundLayout.getEndIconView();
    }

    final void refreshIconState() {
        this.endLayout.refreshIconState(false);
    }
}
