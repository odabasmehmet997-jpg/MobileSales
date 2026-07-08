package com.proje.mobilesales.core.preferences;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.ListPreference;
import android.util.AttributeSet;
import com.proje.mobilesales.core.sql.SqlLiteVariable;

public class LogoCheckPreference extends ListPreference {
    private static final String SEPARATOR = ",";
    private boolean[] mClickedDialogEntryIndices;
    public LogoCheckPreference(Context context) {
        super(context);
    }
    public LogoCheckPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
    public void setEntries(CharSequence[] charSequenceArr) {
        super.setEntries(charSequenceArr);
        this.mClickedDialogEntryIndices = new boolean[charSequenceArr.length];
    }
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        CharSequence[] entries = getEntries();
        CharSequence[] entryValues = getEntryValues();
        if (entries == null || entryValues == null || entries.length != entryValues.length) {
            throw new IllegalStateException("ListPreference requires an entries array and an entryValues array which are both the same length");
        }
        restoreCheckedEntries();
        builder.setMultiChoiceItems(entries, this.mClickedDialogEntryIndices, new DialogInterface.OnMultiChoiceClickListener() { // from class: com.proje.mobilesales.core.preferences.LogoCheckPreference.1
            @Override // android.content.DialogInterface.OnMultiChoiceClickListener
            public void onClick(DialogInterface dialogInterface, int i2, boolean z) {
                LogoCheckPreference.this.mClickedDialogEntryIndices[i2] = z;
            }
        });
    }
    public static String[] parseStoredValue(CharSequence charSequence) {
        if (charSequence == null || "".equals(charSequence)) {
            return null;
        }
        return ((String) charSequence).split(SEPARATOR);
    }
    private void restoreCheckedEntries() {
        CharSequence[] entryValues = getEntryValues();
        String[] parseStoredValue = parseStoredValue(getValue());
        if (parseStoredValue != null) {
            for (String str : parseStoredValue) {
                String trim = str.trim();
                int i2 = 0;
                while (i2 < entryValues.length) {
                    if (entryValues[i2].equals(trim)) {
                        this.mClickedDialogEntryIndices[i2] = true;
                        break;
                    }
                    i2++;
                }
            }
        }
    }
    protected void onDialogClosed(boolean z) {
        CharSequence[] entryValues = getEntryValues();
        CharSequence[] entries = getEntries();
        if (!z || entryValues == null) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        for (int i2 = 0; i2 < entryValues.length; i2++) {
            if (this.mClickedDialogEntryIndices[i2]) {
                stringBuffer.append(entryValues[i2]);
                stringBuffer.append(SEPARATOR);
                stringBuffer2.append(entries[i2]);
                stringBuffer2.append(SqlLiteVariable._NEW_LINE);
            }
        }
        if (callChangeListener(stringBuffer)) {
            String stringBuffer3 = stringBuffer.toString();
            if (stringBuffer3.length() > 0) {
                stringBuffer3 = stringBuffer3.substring(0, stringBuffer3.length() - 1);
            }
            setValue(stringBuffer3);
            setSummary(stringBuffer2);
        }
    }
}
