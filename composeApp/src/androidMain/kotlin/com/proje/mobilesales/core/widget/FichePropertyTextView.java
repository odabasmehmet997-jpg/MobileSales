package com.proje.mobilesales.core.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.proje.mobilesales.R;

public class FichePropertyTextView extends RelativeLayout {
    private final LinearLayout mLnFiche;
    private final TextView mTxtTitle;
    private final TextView mTxtValue;

    public FichePropertyTextView(Context context) {
        this(context, null);
    }

    public FichePropertyTextView(Context context,  AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FichePropertyTextView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        String str;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FicheProperty);
        int resourceId = obtainStyledAttributes.getResourceId(1, 0);
        if (resourceId == 0) {
            str = "";
        } else {
            str = getContext().getString(resourceId);
        }
        View.inflate(context, R.layout.fiche_property_text_view, this);
        this.mLnFiche = findViewById(R.id.ln_fiche_property);
        this.mTxtTitle = findViewById(R.id.txt_fiche_property_title);
        this.mTxtValue = findViewById(R.id.edt_fiche_property_text);
        this.mTxtTitle.setText(str);
        obtainStyledAttributes.recycle();
    }

    public LinearLayout getLnFiche() {
        return this.mLnFiche;
    }

    public TextView getTxtValue() {
        return this.mTxtValue;
    }
}
