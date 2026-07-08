package com.proje.mobilesales.core.preferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceViewHolder;
import com.proje.mobilesales.R;

public class HelpPreference extends PreferenceGroup {
    private final int mLayoutResId;
    private final String mTitle;
    public HelpPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.preferenceHelpStyle);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PreferenceHelp);
        try {
            this.mLayoutResId = obtainStyledAttributes.getResourceId(0, 0);
            this.mTitle = obtainStyledAttributes.getString(1);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }
    protected void onClick() {
        if (this.mLayoutResId == 0) {
            return;
        }
        new AlertDialog.Builder(getContext()).setTitle(this.mTitle).setView(this.mLayoutResId).setPositiveButton(R.string.str_understand, null).create().show();
    }
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        preferenceViewHolder.setDividerAllowedAbove(false);
    }
}
