package com.fasterxml.jackson.databind.util;

import java.text.*;
import java.util.Date;
import java.util.GregorianCalendar;

public class ISO8601DateFormat extends DateFormat {
    private static final long serialVersionUID = 1;

    public Object clone() {
        return this;
    }

    public ISO8601DateFormat() {
        this.numberFormat = new DecimalFormat();
        this.calendar = new GregorianCalendar();
    }

    public StringBuffer format(final Date date, final StringBuffer stringBuffer, final FieldPosition fieldPosition) {
        stringBuffer.append(ISO8601Utils.format(date));
        return stringBuffer;
    }

    public Date parse(final String str, final ParsePosition parsePosition) {
        try {
            return ISO8601Utils.parse(str, parsePosition);
        } catch (final ParseException unused) {
            return null;
        }
    }

    public Date parse(final String str) throws ParseException {
        return ISO8601Utils.parse(str, new ParsePosition(0));
    }
}
