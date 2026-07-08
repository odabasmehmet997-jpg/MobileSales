package org.simpleframework.xml.stream;

import com.proje.mobilesales.core.sql.SqlLiteVariable;


class Indenter {
    private final Cache cache;
    private int count;
    private final int indent;
    private int index;

    public Indenter() {
        this(new Format());
    }

    public Indenter(final Format format) {
        this(format, 16);
    }

    private Indenter(final Format format, final int i2) {
        indent = format.indent();
        cache = new Cache(i2);
    }

    public String top() {
        return this.indent(index);
    }

    public String push() {
        final int i2 = index;
        index = i2 + 1;
        final String indent = this.indent(i2);
        final int i3 = this.indent;
        if (0 < i3) {
            count += i3;
        }
        return indent;
    }

    public String pop() {
        final int i2 = index - 1;
        index = i2;
        final String indent = this.indent(i2);
        final int i3 = this.indent;
        if (0 < i3) {
            count -= i3;
        }
        return indent;
    }

    private String indent(final int i2) {
        if (0 < this.indent) {
            String str = cache.get(i2);
            if (null == str) {
                str = this.create();
                cache.set(i2, str);
            }
            return 0 < this.cache.size() ? str : "";
        }
        return "";
    }

    private String create() {
        final int i2 = count;
        final char[] cArr = new char[i2 + 1];
        if (0 < i2) {
            cArr[0] = '\n';
            for (int i3 = 1; i3 <= count; i3++) {
                cArr[i3] = ' ';
            }
            return new String(cArr);
        }
        return SqlLiteVariable._NEW_LINE;
    }

    private static class Cache {
        private int count;
        private String[] list;

        public Cache(final int i2) {
            list = new String[i2];
        }

        public int size() {
            return count;
        }

        public void set(final int i2, final String str) {
            if (i2 >= list.length) {
                this.resize(i2 * 2);
            }
            if (i2 > count) {
                count = i2;
            }
            list[i2] = str;
        }

        public String get(final int i2) {
            final String[] strArr = list;
            if (i2 < strArr.length) {
                return strArr[i2];
            }
            return null;
        }

        private void resize(final int i2) {
            final String[] strArr = new String[i2];
            int i3 = 0;
            while (true) {
                final String[] strArr2 = list;
                if (i3 < strArr2.length) {
                    strArr[i3] = strArr2[i3];
                    i3++;
                } else {
                    list = strArr;
                    return;
                }
            }
        }
    }
}
