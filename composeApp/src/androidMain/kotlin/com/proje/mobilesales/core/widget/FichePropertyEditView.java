package com.proje.mobilesales.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.proje.mobilesales.R;

public class FichePropertyEditView extends RelativeLayout {
    private final EditText mEdtValue;
    private final LinearLayout mLnFiche;
    private final TextView mTxtTitle;
    public FichePropertyEditView(Context context) {
        this(context, null);
    }
    public FichePropertyEditView(Context context,  AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
    public FichePropertyEditView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        String str;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FicheProperty);
        int resourceId = obtainStyledAttributes.getResourceId(1, 0);
        if (resourceId == 0) {
            str = "";
        } else {
            str = getContext().getString(resourceId);
        }
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        View.inflate(context, R.layout.fiche_property_edit, this);
        this.mLnFiche = findViewById(R.id.ln_fiche_property);
        this.mTxtTitle = findViewById(R.id.txt_fiche_property_title);
        EditText editText = findViewById(R.id.edt_fiche_property_edit);
        this.mEdtValue = editText;
        editText.setSingleLine(z);
        if (z) {
            this.mEdtValue.setImeOptions(5);
        }
        this.mTxtTitle.setText(str);
        obtainStyledAttributes.recycle();
    }
    public LinearLayout getLnFiche() {
        return this.mLnFiche;
    }
    public EditText getEdtValue() {
        return this.mEdtValue;
    }
}
