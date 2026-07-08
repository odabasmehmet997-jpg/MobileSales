package org.simpleframework.xml.transform;

import java.util.regex.Pattern;


class StringArrayTransform implements Transform<String[]> {
    private final Pattern pattern;
    private final String token;

    public StringArrayTransform() {
        this(",");
    }

    public StringArrayTransform(final String str) {
        pattern = Pattern.compile(str);
        token = str;
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String[] read(final String str) {
        return this.read(str, token);
    }

    private String[] read(final String str, final String str2) {
        final String[] split = pattern.split(str);
        for (int i2 = 0; i2 < split.length; i2++) {
            final String str3 = split[i2];
            if (null != str3) {
                split[i2] = str3.trim();
            }
        }
        return split;
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final String[] strArr) {
        return this.write(strArr, token);
    }

    private String write(final String[] strArr, final String str) {
        final StringBuilder sb = new StringBuilder();
        for (final String str2 : strArr) {
            if (null != str2) {
                if (0 < sb.length()) {
                    sb.append(str);
                    sb.append(' ');
                }
                sb.append(str2);
            }
        }
        return sb.toString();
    }
}
