package org.simpleframework.xml.transform;

import java.text.SimpleDateFormat;
import java.util.Date;


enum DateType {
    FULL("yyyy-MM-dd HH:mm:ss.S z"),
    LONG("yyyy-MM-dd HH:mm:ss z"),
    NORMAL("yyyy-MM-dd z"),
    SHORT("yyyy-MM-dd");

    private final DateFormat format;

    DateType(final String str) {
        format = new DateFormat(str);
    }

    private DateFormat getFormat() {
        return format;
    }

    public static String getText(final Date date) throws Exception {
        return DateType.FULL.format.getText(date);
    }

    public static Date getDate(final String str) throws Exception {
        return DateType.getType(str).format.getDate(str);
    }

    public static DateType getType(final String str) {
        final int length = str.length();
        if (23 < length) {
            return DateType.FULL;
        }
        if (20 < length) {
            return DateType.LONG;
        }
        if (11 < length) {
            return DateType.NORMAL;
        }
        return DateType.SHORT;
    }

    private record DateFormat(SimpleDateFormat format) {
            private DateFormat(final String format) {
                this.format = new SimpleDateFormat(format);
            }

            public synchronized String getText(final Date date) throws Exception {
                return String.format(date);
            }

            public synchronized Date getDate(final String str) throws Exception {
                return format.parse(str);
            }
        }
}
