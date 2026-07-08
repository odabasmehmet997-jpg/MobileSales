package com.proje.mobilesales.core.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Toast;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.utils.ContextUtils;
import com.proje.mobilesales.core.utils.StringUtils;

public class EditTextIntPasswordPref extends EditTextPreference {
    final int maxTransferValue;
    final int minTransferValue;
    @SuppressLint("ResourceType")
    public EditTextIntPasswordPref(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDialogLayoutResource(R.layout.pref_edit_text_integer_password);
        setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object obj) {
                int convertObjectToInt = StringUtils.convertObjectToInt(obj);
                EditTextIntPasswordPref editTextIntPasswordPref = EditTextIntPasswordPref.this;
                if (convertObjectToInt < editTextIntPasswordPref.minTransferValue) {
                    Toast.makeText(ContextUtils.getmContext(), String.format("%s %d %s", EditTextIntPasswordPref.this.getTitle(), Integer.valueOf(EditTextIntPasswordPref.this.minTransferValue), ContextUtils.getStringResource(R.string.exp_16_value_low)), Toast.LENGTH_LONG).show();
                    return false;
                }
                if (convertObjectToInt <= editTextIntPasswordPref.maxTransferValue) {
                    return true;
                }
                Toast.makeText(ContextUtils.getmContext(), String.format("%s %d %s", EditTextIntPasswordPref.this.getTitle(), Integer.valueOf(EditTextIntPasswordPref.this.maxTransferValue), ContextUtils.getStringResource(R.string.exp_15_value_high)), Toast.LENGTH_LONG).show();
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
