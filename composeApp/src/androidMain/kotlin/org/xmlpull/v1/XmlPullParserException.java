package org.xmlpull.v1;

import java.io.PrintStream;

public class XmlPullParserException extends Exception {
    protected int column;
    protected Throwable detail;
    protected int row;
    public XmlPullParserException(final String str) {
        super(str);
        row = -1;
        column = -1;
    }
    public XmlPullParserException(final String str, final XmlPullParser xmlPullParser, final Throwable th) {

        final String str2;
        final String str3;
        final StringBuilder sb = new StringBuilder();
        String str4 = "";
        if (null == str) {
            str2 = "";
        } else {
            str2 = str + " ";
        }
        sb.append(str2);
        if (null == xmlPullParser) {
            str3 = "";
        } else {
            str3 = "(position:" + xmlPullParser.getPositionDescription() + ") ";
        }
        sb.append(str3);
        if (null != th) {
            str4 = "caused by: " + th;
        }
        sb.append(str4);
        row = -1;
        column = -1;
        if (null != xmlPullParser) {
            row = xmlPullParser.getLineNumber();
            column = xmlPullParser.getColumnNumber();
        }
        detail = th;
    }
    public Throwable getDetail() {
        return detail;
    }
    public int getLineNumber() {
        return row;
    }
    public int getColumnNumber() {
        return column;
    }
    public void printStackTrace() {
        if (null == this.detail) {
            super.printStackTrace();
            return;
        }
        final PrintStream printStream = System.err;
        synchronized (printStream) {
            printStream.println(getMessage() + "; nested exception is:");
            detail.printStackTrace();
        }
    }
}
