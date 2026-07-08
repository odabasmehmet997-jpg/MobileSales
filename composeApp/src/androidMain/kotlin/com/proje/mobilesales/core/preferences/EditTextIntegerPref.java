package com.proje.mobilesales.core.preferences;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Toast;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;

public class EditTextIntegerPref extends EditTextPreference {
    final int maxTransferValue;
    final int minTransferValue;
    public EditTextIntegerPref(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDialogLayoutResource(R.layout.pref_edit_text_integer);
        setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.proje.mobilesales.core.preferences.EditTextIntegerPref.1
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object obj) {
                int convertObjectToInt = StringUtils.convertObjectToInt(obj);
                EditTextIntegerPref editTextIntegerPref = EditTextIntegerPref.this;
                if (convertObjectToInt < editTextIntegerPref.minTransferValue) {
                    Toast.makeText(ContextUtils.getmContext(), String.format("%s %d %s", EditTextIntegerPref.this.getTitle(), Integer.valueOf(EditTextIntegerPref.this.minTransferValue), ContextUtils.getStringResource(R.string.exp_16_value_low)), Toast.LENGTH_LONG).show();
                    return false;
                }
                if (convertObjectToInt <= editTextIntegerPref.maxTransferValue) {
                    return true;
                }
                Toast.makeText(ContextUtils.getmContext(), String.format("%s %d %s", EditTextIntegerPref.this.getTitle(), Integer.valueOf(EditTextIntegerPref.this.maxTransferValue), ContextUtils.getStringResource(R.string.exp_15_value_high)), Toast.LENGTH_LONG).show();
                return false;
            }
        });
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.EditTextIntegerPref);
        try {
            this.minTransferValue = obtainStyledAttributes.getInt(1, 0);
            this.maxTransferValue = obtainStyledAttributes.getInt(0, 0);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }
}
