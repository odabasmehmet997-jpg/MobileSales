package org.simpleframework.xml.stream;


abstract class Splitter {
    protected StringBuilder builder = new StringBuilder();
    protected int count;
    protected int off;
    protected char[] text;

    protected abstract void commit(char[] cArr, int i2, int i3);

    protected abstract void parse(char[] cArr, int i2, int i3);

    protected Splitter(final String str) {
        final char[] charArray = str.toCharArray();
        text = charArray;
        count = charArray.length;
    }

    public String process() {
        while (off < count) {
            while (true) {
                final int i2 = off;
                if (i2 >= count || !this.isSpecial(text[i2])) {
                    break;
                }
                off++;
            }
            if (!this.acronym()) {
                this.token();
                this.number();
            }
        }
        return builder.toString();
    }

    private void token() {
        int i2 = off;
        while (i2 < count) {
            final char c2 = text[i2];
            if (!this.isLetter(c2) || (i2 > off && this.isUpper(c2))) {
                break;
            } else {
                i2++;
            }
        }
        final int i3 = off;
        if (i2 > i3) {
            this.parse(text, i3, i2 - i3);
            final char[] cArr = text;
            final int i4 = off;
            this.commit(cArr, i4, i2 - i4);
        }
        off = i2;
    }

    private boolean acronym() {
        int i2 = off;
        int i3 = 0;
        while (i2 < count && this.isUpper(text[i2])) {
            i3++;
            i2++;
        }
        if (1 < i3) {
            if (i2 < count && this.isUpper(text[i2 - 1])) {
                i2--;
            }
            final char[] cArr = text;
            final int i4 = off;
            this.commit(cArr, i4, i2 - i4);
            off = i2;
        }
        return 1 < i3;
    }

    private boolean number() {
        int i2 = off;
        int i3 = 0;
        while (i2 < count && this.isDigit(text[i2])) {
            i3++;
            i2++;
        }
        if (0 < i3) {
            final char[] cArr = text;
            final int i4 = off;
            this.commit(cArr, i4, i2 - i4);
        }
        off = i2;
        return 0 < i3;
    }

    private boolean isLetter(final char c2) {
        return Character.isLetter(c2);
    }

    private boolean isSpecial(final char c2) {
        return !Character.isLetterOrDigit(c2);
    }

    private boolean isDigit(final char c2) {
        return Character.isDigit(c2);
    }

    private boolean isUpper(final char c2) {
        return Character.isUpperCase(c2);
    }

    protected char toUpper(final char c2) {
        return Character.toUpperCase(c2);
    }

    protected char toLower(final char c2) {
        return Character.toLowerCase(c2);
    }
}
