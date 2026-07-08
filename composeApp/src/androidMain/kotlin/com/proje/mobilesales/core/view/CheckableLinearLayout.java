package com.proje.mobilesales.core.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

public class CheckableLinearLayout extends LinearLayout implements Checkable {
    private CheckedTextView _checkbox;
    public CheckableLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt instanceof CheckedTextView) {
                this._checkbox = (CheckedTextView) childAt;
            }
        }
    }
    public boolean isChecked() {
        CheckedTextView checkedTextView = this._checkbox;
        if (checkedTextView != null) {
            return checkedTextView.isChecked();
        }
        return false;
    }
    public void setChecked(boolean z) {
        CheckedTextView checkedTextView = this._checkbox;
        if (checkedTextView != null) {
            checkedTextView.setChecked(z);
        }
    }
    public void toggle() {
        CheckedTextView checkedTextView = this._checkbox;
        if (checkedTextView != null) {
            checkedTextView.toggle();
        }
    }
}
