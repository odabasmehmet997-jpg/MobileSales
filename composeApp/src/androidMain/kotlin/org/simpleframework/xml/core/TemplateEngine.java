package org.simpleframework.xml.core;

import org.simpleframework.xml.filter.Filter;

class TemplateEngine {
    private final Filter filter;
    private int off;
    private final Template source = new Template();
    private final Template name = new Template();
    private final Template text = new Template();
    public TemplateEngine(final Filter filter) {
        this.filter = filter;
    }
    public String process(final String str) {
        if (0 > str.indexOf(36)) {
            return str;
        }
        try {
            source.append(str);
            this.parse();
            return text.toString();
        } finally {
            this.clear();
        }
    }
    private void parse() {
        while (true) {
            final int i2 = off;
            final Template template = source;
            final int i3 = template.count;
            if (i2 >= i3) {
                return;
            }
            final char[] cArr = template.buf;
            final int i4 = i2 + 1;
            off = i4;
            final char c2 = cArr[i2];
            if (c2==' ')
                if (i4 < i3) {
                    off = i2 + 2;
                    if ('{' == cArr[i4]) {
                        this.name();
                    } else {
                        off = i2 + 1;
                    }
                }
            text.append(c2);
        }
    }
    private void name() {
        while (true) {
            final int i2 = off;
            final Template template = source;
            if (i2 >= template.count) {
                break;
            }
            final char[] cArr = template.buf;
            off = i2 + 1;
            final char c2 = cArr[i2];
            if ('}' == c2) {
                this.replace();
                break;
            }
            name.append(c2);
        }
        if (0 < this.name.length()) {
            text.append("{");
            text.append(name);
        }
    }
    private void replace() {
        if (0 < this.name.length()) {
            this.replace(name);
        }
        name.clear();
    }
    private void replace(final Template template) {
        this.replace(template.toString());
    }
    private void replace(final String str) {
        final String replace = filter.replace(str);
        if (null == replace) {
            text.append("{");
            text.append(str);
            text.append("}");
            return;
        }
        text.append(replace);
    }
    public void clear() {
        name.clear();
        text.clear();
        source.clear();
        off = 0;
    }
}
