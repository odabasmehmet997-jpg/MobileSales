package org.simpleframework.xml.stream;

import java.io.IOException;
import java.io.Writer;


class OutputBuffer {
    private final StringBuilder text = new StringBuilder();

    public void append(final char c2) {
        text.append(c2);
    }

    public void append(final String str) {
        text.append(str);
    }

    public void append(final char[] cArr) {
        text.append(cArr, 0, cArr.length);
    }

    public void append(final char[] cArr, final int i2, final int i3) {
        text.append(cArr, i2, i3);
    }

    public void append(final String str, final int i2, final int i3) {
        text.append(str, i2, i3);
    }

    public void write(final Writer writer) throws IOException {
        writer.append((CharSequence) text);
    }

    public void clear() {
        text.setLength(0);
    }
}
