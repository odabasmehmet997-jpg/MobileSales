package com.proje.mobilesales.core.widget;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import com.proje.mobilesales.core.MobileSales;
import java.util.regex.Pattern;

public class DecimalDigitsInputFilter implements InputFilter {
    Pattern mPattern;
    public DecimalDigitsInputFilter(int i2, int i3) {
        String sb = "[0-9]{0," +
                (i2 - 1) +
                "}+((\\.[0-9]{0," +
                (i3 - 1) +
                "})?)||(\\.)?";
        this.mPattern = Pattern.compile(sb);
    }
    public DecimalDigitsInputFilter(int i2) {
        try {
            String sb = "[0-9]+((\\.[0-9]{0," +
                    (i2 - 1) +
                    "})?)||(\\.)?";
            this.mPattern = Pattern.compile(sb);
        } catch (Exception e2) {
            this.mPattern = Pattern.compile("[0-9]+((\\.[0-9]{0,4})?)||(\\.)?");
            Log.e(MobileSales.TAG, "DecimalDigitsInputFilter: ", e2);
        }
    }
    public CharSequence filter(CharSequence charSequence, int i2, int i3, Spanned spanned, int i4, int i5) {
        if (this.mPattern.matcher(spanned).matches()) {
            return null;
        }
        return "";
    }
}
