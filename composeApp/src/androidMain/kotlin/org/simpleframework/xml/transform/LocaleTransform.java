package org.simpleframework.xml.transform;

import java.util.Locale;
import java.util.regex.Pattern;


class LocaleTransform implements Transform<Locale> {
    private final Pattern pattern = Pattern.compile("_");

    @Override // org.simpleframework.xml.transform.Transform
    public Locale read(final String str) throws Exception {
        final String[] split = pattern.split(str);
        if (1 > split.length) {
            throw new InvalidFormatException("Invalid locale %s", str);
        }
        return this.read(split);
    }

    private Locale read(final String[] strArr) throws Exception {
        final String[] strArr2 = new String[3];
        strArr2[0] = "";
        strArr2[1] = "";
        strArr2[2] = "";
        for (int i2 = 0; 3 > i2; i2++) {
            if (i2 < strArr.length) {
                strArr2[i2] = strArr[i2];
            }
        }
        return new Locale(strArr2[0], strArr2[1], strArr2[2]);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final Locale locale) {
        return locale.toString();
    }
}
