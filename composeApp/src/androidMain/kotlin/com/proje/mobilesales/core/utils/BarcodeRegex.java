package com.proje.mobilesales.core.utils;

import android.text.TextUtils;
import android.util.Log;
import com.proje.mobilesales.core.MobileSales;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BarcodeRegex {
    final Pattern mPattern;
    public BarcodeRegex(String str) {
        Log.d(MobileSales.TAG, "BarcodeRegex: " + str);
        this.mPattern = Pattern.compile(str);
    }
    public ArrayList<String> findBarcodeRegex(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        Matcher matcher = this.mPattern.matcher(str);
        for (int i2 = 0; matcher.find() && i2 <= 2; i2++) {
            Log.d(MobileSales.TAG, "Full match: " + matcher.group(0));
            int i3 = 1;
            while (true) {
                if (i3 > matcher.groupCount()) {
                    break;
                }
                if (!TextUtils.isEmpty(matcher.group(i3))) {
                    Log.d(MobileSales.TAG, "findRegex: Group " + i3 + ": " + matcher.group(i3));
                    arrayList.add(matcher.group(i3));
                    break;
                }
                i3++;
            }
        }
        return arrayList;
    }
}
