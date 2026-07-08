package com.proje.mobilesales.features.settings.preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;
import com.proje.mobilesales.R;
import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.enums.ErpType;
import kotlin.jvm.internal.Intrinsics;

import static com.proje.mobilesales.core.utils.AppUtils.matterCompare;
import static com.proje.mobilesales.core.utils.AppUtils.matterParseNumber;

public final class MatterPreference extends EditTextPreference {
    private final String comparePref;
    private boolean first;
    private int maxLength;
 
    @SuppressLint("ResourceType")
    public MatterPreference(Context context, final AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        final TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MatterPref);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        try {
            comparePref = obtainStyledAttributes.getString(0);
            first = obtainStyledAttributes.getBoolean(1, false);
            final BaseErp baseErp = ErpCreator.getInstance().getmBaseErp();
            maxLength = null != baseErp ? baseErp.getErpType() == ErpType.TIGER ? 16 : 15 : 0;
            obtainStyledAttributes.recycle();
            this.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                public void onBindEditText(final EditText editText) {
                    _init_lambda0(MatterPreference.this, editText);
                }
            });
            this.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                public boolean onPreferenceChange(final Preference preference, final Object obj) {
                    final boolean _init_lambda1;
                    _init_lambda1 = _init_lambda1(MatterPreference.this, context, preference, obj);
                    return _init_lambda1;
                }
            });
        } catch (final Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }
    public boolean getFirst() {
        return first;
    }
    public void setFirst(final boolean z) {
        first = z;
    }
    public int getMaxLength() {
        return maxLength;
    }
    public void setMaxLength(final int i2) {
        maxLength = i2;
    }
    public static void _init_lambda0(final MatterPreference this0, final EditText editText) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(editText, "editText");
        if (0 == this0.maxLength) {
            return;
        }
        try {
            if (!TextUtils.isEmpty(editText.getText()) && editText.getText().length() > this0.maxLength) {
                final String substring = editText.getText().toString().substring(0, this0.maxLength);
                Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                editText.setText(substring);
            }
        } catch (final Exception e2) {
            Log.e("MatterPreference", "on bind", e2);
        }
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this0.maxLength)});
    }
    public static boolean _init_lambda1(final MatterPreference this0, final Context context, final Preference preference, final Object o) {
        Intrinsics.checkNotNullParameter(this0, "this0");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(preference, "preference");
        Intrinsics.checkNotNullParameter(o, "o");
        final String obj = o.toString();
        final PreferenceManager preferenceManager = preference.getPreferenceManager();
        final String str = this0.comparePref;
        Intrinsics.checkNotNull(str);
        final MatterPreference matterPreference = preferenceManager.findPreference(str);
        Intrinsics.checkNotNull(matterPreference);
        final String text = matterPreference.getText();
        if (TextUtils.isEmpty(obj) && this0.first) {
            final PreferenceManager preferenceManager2 = preference.getPreferenceManager();
            final String str2 = this0.comparePref;
            Intrinsics.checkNotNull(str2);
            final MatterPreference matterPreference2 = preferenceManager2.findPreference(str2);
            Intrinsics.checkNotNull(matterPreference2);
            matterPreference2.setText("");
            return true;
        }
        if (TextUtils.isEmpty(obj) && !this0.first) {
            return true;
        }
        if (TextUtils.isEmpty(text) && this0.first) {
            return true;
        }
        if (TextUtils.isEmpty(text) && !this0.first) {
            Toast.makeText(context, context.getString(R.string.str_matter_first_value_enter), Toast.LENGTH_LONG).show();
            return false;
        }
        if (matterCompare(this0.first, matterParseNumber(obj), matterParseNumber(text))) {
            return true;
        }
        Toast.makeText(context, context.getString(R.string.str_matter_first_value_less), Toast.LENGTH_LONG).show();
        return false;
    }
}
