package com.proje.mobilesales.core.preferences;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.EditTextPreference;
import com.proje.mobilesales.R;

public class EditTextPasswordPref extends EditTextPreference {
    public EditTextPasswordPref(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setDialogLayoutResource(R.layout.pref_edit_text_password);
    }
}
