package com.proje.mobilesales.features.settings.expr;

import android.util.Log;
import com.proje.mobilesales.core.MobileSales;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.Intrinsics;
 
public final class MatterRegex {
    private final Pattern mPattern;

    public MatterRegex(final String regex) {
        Intrinsics.checkNotNullParameter(regex, "regex");
        Log.d(MobileSales.TAG, "MatterRegex: " + regex);
        final Pattern compile = Pattern.compile(regex);
        Intrinsics.checkNotNullExpressionValue(compile, "compile(...)");
        mPattern = compile;
    }
    public ArrayList<String> getMatterFind(final String str) {
        int i2 = 0;
        final ArrayList<String> arrayList = new ArrayList<>();
        final Matcher matcher = mPattern.matcher(str);
        while (matcher.find()) {
            Log.d(MobileSales.TAG, "Full match: " + matcher.group(0));
            final int groupCount = matcher.groupCount();
            if (1 <= groupCount) {
                while (true) {
                    Log.d(MobileSales.TAG, "findRegex: Group " + i2 + ": " + matcher.group(i2));
                    arrayList.add(matcher.group(i2));
                    i2 = i2 != groupCount ? i2 + 1 : 1;
                }
            }
        }
        return arrayList;
    }
}
