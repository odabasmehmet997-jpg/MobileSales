package org.simpleframework.xml.core;

class Template {
    protected char[] buf;
    protected String cache;
    protected int count;
    public Template() {
        this(16);
    }
    public Template(final int i2) {
        buf = new char[i2];
    }
    public void append(final char c2) {
        this.ensureCapacity(count + 1);
        final char[] cArr = buf;
        final int i2 = count;
        count = i2 + 1;
        cArr[i2] = c2;
    }
    public void append(final String str) {
        this.ensureCapacity(count + str.length());
        str.getChars(0, str.length(), buf, count);
        count += str.length();
    }
    public void append(final Template template) {
        this.append(template.buf, 0, template.count);
    }
    public void append(final char[] cArr, final int i2, final int i3) {
        this.ensureCapacity(count + i3);
        System.arraycopy(cArr, i2, buf, count, i3);
        count += i3;
    }
    public void append(final String str, final int i2, final int i3) {
        this.ensureCapacity(count + i3);
        str.getChars(i2, i3, buf, count);
        count += i3;
    }
    public void append(final Template template, final int i2, final int i3) {
        this.append(template.buf, i2, i3);
    }
    protected void ensureCapacity(final int i2) {
        final char[] cArr = buf;
        if (cArr.length < i2) {
            final char[] cArr2 = new char[Math.max(i2, cArr.length * 2)];
            System.arraycopy(buf, 0, cArr2, 0, count);
            buf = cArr2;
        }
    }
    public void clear() {
        cache = null;
        count = 0;
    }
    public int length() {
        return count;
    }
    public String toString() {
        return new String(buf, 0, count);
    }
}
