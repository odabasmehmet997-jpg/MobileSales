package org.kobjects.rss;

import org.kobjects.xml.XmlReader;

import java.io.IOException;
import java.io.Reader;

public class RssReader {
    public static final int AUTHOR = 4;
    public static final int DATE = 3;
    public static final int DESCRIPTION = 2;
    public static final int LINK = 1;
    public static final int TITLE = 0;
    XmlReader f2063xr;
    public RssReader(final Reader reader) throws IOException {
        f2063xr = new XmlReader(reader);
    }
    void readText(final StringBuffer stringBuffer) throws IOException {
        while (3 != this.f2063xr.next()) {
            final int type = f2063xr.getType();
            if (2 == type) {
                this.readText(stringBuffer);
            } else if (4 == type) {
                stringBuffer.append(f2063xr.getText());
            }
        }
    }
    public String[] next() throws IOException {
        final String[] strArr = new String[5];
        while (1 != this.f2063xr.next()) {
            if (2 == this.f2063xr.getType()) {
                final String lowerCase = f2063xr.getName().toLowerCase();
                if ("item".equals(lowerCase) || lowerCase.endsWith(":item")) {
                    while (3 != this.f2063xr.next()) {
                        if (2 == this.f2063xr.getType()) {
                            String lowerCase2 = f2063xr.getName().toLowerCase();
                            final int indexOf = lowerCase2.indexOf(':');
                            if (-1 != indexOf) {
                                lowerCase2 = lowerCase2.substring(indexOf + 1);
                            }
                            final StringBuffer stringBuffer = new StringBuffer();
                            this.readText(stringBuffer);
                            final String stringBuffer2 = stringBuffer.toString();
                            if ("title".equals(lowerCase2)) {
                                strArr[0] = stringBuffer2;
                            } else if ("link".equals(lowerCase2)) {
                                strArr[1] = stringBuffer2;
                            } else if ("description".equals(lowerCase2)) {
                                strArr[2] = stringBuffer2;
                            } else if ("date".equals(lowerCase2)) {
                                strArr[3] = stringBuffer2;
                            } else if ("author".equals(lowerCase2)) {
                                strArr[4] = stringBuffer2;
                            }
                        }
                    }
                    return strArr;
                }
            }
        }
        return null;
    }
}
