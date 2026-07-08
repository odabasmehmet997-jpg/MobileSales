package com.proje.mobilesales.core.view;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatEditText;

public class CustomEditText extends AppCompatEditText {
    private String stringTag;
    public CustomEditText(Context context) {
        super(context);
    }
    public CustomEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
    public CustomEditText(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
    public String getStringTag() {
        return this.stringTag;
    }
    public void setStringTag(String str) {
        this.stringTag = str;
    }
}
