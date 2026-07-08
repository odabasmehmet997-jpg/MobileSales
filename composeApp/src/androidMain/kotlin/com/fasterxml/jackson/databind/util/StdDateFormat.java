package com.fasterxml.jackson.databind.util;

import androidx.core.app.NotificationManagerCompat;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.io.NumberInput;

import java.text.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 
public class StdDateFormat extends DateFormat {
    protected static final String[] ALL_FORMATS;
    protected static final Calendar CALENDAR;
    protected static final DateFormat DATE_FORMAT_RFC1123;
    public static final String DATE_FORMAT_STR_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
    protected static final String DATE_FORMAT_STR_PLAIN = "yyyy-MM-dd";
    protected static final String DATE_FORMAT_STR_RFC1123 = "EEE, dd MMM yyyy HH:mm:ss zzz";
    protected static final Locale DEFAULT_LOCALE;
    protected static final TimeZone DEFAULT_TIMEZONE;
    protected static final Pattern PATTERN_ISO8601;
    public static final StdDateFormat instance;
    private transient Calendar _calendar;
    private transient DateFormat _formatRFC1123;
    protected Boolean _lenient;
    protected final Locale _locale;
    protected transient TimeZone _timezone;
    private final boolean _tzSerializedWithColon;
    protected static final String PATTERN_PLAIN_STR = "\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d";
    protected static final Pattern PATTERN_PLAIN = Pattern.compile(StdDateFormat.PATTERN_PLAIN_STR);
    public boolean equals(final Object obj) {
        return obj == this;
    }
    static {
        try {
            PATTERN_ISO8601 = Pattern.compile("\\d\\d\\d\\d[-]\\d\\d[-]\\d\\d[T]\\d\\d[:]\\d\\d(?:[:]\\d\\d)?(\\.\\d+)?(Z|[+-]\\d\\d(?:[:]?\\d\\d)?)?");
            ALL_FORMATS = new String[]{StdDateFormat.DATE_FORMAT_STR_ISO8601, "yyyy-MM-dd'T'HH:mm:ss.SSS", StdDateFormat.DATE_FORMAT_STR_RFC1123, StdDateFormat.DATE_FORMAT_STR_PLAIN};
            final TimeZone timeZone = TimeZone.getTimeZone("UTC");
            DEFAULT_TIMEZONE = timeZone;
            final Locale locale = Locale.US;
            DEFAULT_LOCALE = locale;
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(StdDateFormat.DATE_FORMAT_STR_RFC1123, locale);
            DATE_FORMAT_RFC1123 = simpleDateFormat;
            simpleDateFormat.setTimeZone(timeZone);
            instance = new StdDateFormat();
            CALENDAR = new GregorianCalendar(timeZone, locale);
        } catch (final Throwable th) {
            throw new RuntimeException(th);
        }
    }
    public StdDateFormat() {
        _tzSerializedWithColon = true;
        _locale = StdDateFormat.DEFAULT_LOCALE;
    }
    public StdDateFormat(final TimeZone timeZone, final Locale locale) {
        _tzSerializedWithColon = true;
        _timezone = timeZone;
        _locale = locale;
    }
    protected StdDateFormat(final TimeZone timeZone, final Locale locale, final Boolean bool) {
        this(timeZone, locale, bool, false);
    }
    protected StdDateFormat(final TimeZone timeZone, final Locale locale, final Boolean bool, final boolean z) {
        _timezone = timeZone;
        _locale = locale;
        _lenient = bool;
        _tzSerializedWithColon = z;
    }
    public static TimeZone getDefaultTimeZone() {
        return StdDateFormat.DEFAULT_TIMEZONE;
    }
    public StdDateFormat withTimeZone(TimeZone timeZone) {
        if (null == timeZone) {
            timeZone = StdDateFormat.DEFAULT_TIMEZONE;
        }
        final TimeZone timeZone2 = _timezone;
        return (timeZone == timeZone2 || timeZone.equals(timeZone2)) ? this : new StdDateFormat(timeZone, _locale, _lenient, _tzSerializedWithColon);
    }
    public StdDateFormat withLocale(final Locale locale) {
        return locale.equals(_locale) ? this : new StdDateFormat(_timezone, locale, _lenient, _tzSerializedWithColon);
    }
    public StdDateFormat withLenient(final Boolean bool) {
        return StdDateFormat._equals(bool, _lenient) ? this : new StdDateFormat(_timezone, _locale, bool, _tzSerializedWithColon);
    }
    public StdDateFormat withColonInTimeZone(final boolean z) {
        return _tzSerializedWithColon == z ? this : new StdDateFormat(_timezone, _locale, _lenient, z);
    }
    public StdDateFormat clone() {
        return new StdDateFormat(_timezone, _locale, _lenient, _tzSerializedWithColon);
    }
    public static DateFormat getISO8601Format(final TimeZone timeZone, final Locale locale) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(StdDateFormat.DATE_FORMAT_STR_ISO8601, locale);
        simpleDateFormat.setTimeZone(StdDateFormat.DEFAULT_TIMEZONE);
        return simpleDateFormat;
    }
    public static DateFormat getRFC1123Format(final TimeZone timeZone, final Locale locale) {
        return StdDateFormat._cloneFormat(StdDateFormat.DATE_FORMAT_RFC1123, StdDateFormat.DATE_FORMAT_STR_RFC1123, timeZone, locale, null);
    }
    public TimeZone getTimeZone() {
        return _timezone;
    }
    public void setTimeZone(final TimeZone timeZone) {
        if (timeZone.equals(_timezone)) {
            return;
        }
        this._clearFormats();
        _timezone = timeZone;
    }
    public void setLenient(final boolean z) {
        final Boolean boolValueOf = Boolean.valueOf(z);
        if (StdDateFormat._equals(boolValueOf, _lenient)) {
            return;
        }
        _lenient = boolValueOf;
        this._clearFormats();
    }
    public boolean isLenient() {
        final Boolean bool = _lenient;
        return null == bool || bool.booleanValue();
    }
    public boolean isColonIncludedInTimeZone() {
        return _tzSerializedWithColon;
    }
    public Date parse(final String str) throws ParseException {
        final String strTrim = str.trim();
        final ParsePosition parsePosition = new ParsePosition(0);
        final Date date_parseDate = this._parseDate(strTrim, parsePosition);
        if (null != date_parseDate) {
            return date_parseDate;
        }
        final StringBuilder sb = new StringBuilder();
        for (final String str2 : StdDateFormat.ALL_FORMATS) {
            if (0 < sb.length()) {
                sb.append("\", \"");
            } else {
                sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
            }
            sb.append(str2);
        }
        sb.append(JsonFactory.DEFAULT_QUOTE_CHAR);
        throw new ParseException(String.format("Cannot parse date \"%s\": not compatible with any of standard forms (%s)", strTrim, sb), parsePosition.getErrorIndex());
    }
    public Date parse(final String str, final ParsePosition parsePosition) {
        try {
            return this._parseDate(str, parsePosition);
        } catch (final ParseException unused) {
            return null;
        }
    }
    protected Date _parseDate(final String str, final ParsePosition parsePosition) throws ParseException {
        if (this.looksLikeISO8601(str)) {
            return this.parseAsISO8601(str, parsePosition);
        }
        int length = str.length();
        while (true) {
            length--;
            if (0 > length) {
                break;
            }
            final char cCharAt = str.charAt(length);
            if ('0' > cCharAt || '9' < cCharAt) {
                if (0 < length || '-' != cCharAt) {
                    break;
                }
            }
        }
        if (0 > length && ('-' == str.charAt(0) || NumberInput.inLongRange(str, false))) {
            return this._parseDateFromLong(str, parsePosition);
        }
        return this.parseAsRFC1123(str, parsePosition);
    }
    public StringBuffer format(final Date date, final StringBuffer stringBuffer, final FieldPosition fieldPosition) {
        TimeZone timeZone = _timezone;
        if (null == timeZone) {
            timeZone = StdDateFormat.DEFAULT_TIMEZONE;
        }
        this._format(timeZone, _locale, date, stringBuffer);
        return stringBuffer;
    }
    protected void _format(final TimeZone timeZone, final Locale locale, final Date date, final StringBuffer stringBuffer) {
        final Calendar calendar_getCalendar = this._getCalendar(timeZone);
        calendar_getCalendar.setTime(date);
        final int i2 = calendar_getCalendar.get(1);
        if (0 == calendar_getCalendar.get(0)) {
            this._formatBCEYear(stringBuffer, i2);
        } else {
            if (9999 < i2) {
                stringBuffer.append('+');
            }
            StdDateFormat.pad4(stringBuffer, i2);
        }
        stringBuffer.append('-');
        StdDateFormat.pad2(stringBuffer, calendar_getCalendar.get(2) + 1);
        stringBuffer.append('-');
        StdDateFormat.pad2(stringBuffer, calendar_getCalendar.get(5));
        stringBuffer.append('T');
        StdDateFormat.pad2(stringBuffer, calendar_getCalendar.get(11));
        stringBuffer.append(':');
        StdDateFormat.pad2(stringBuffer, calendar_getCalendar.get(12));
        stringBuffer.append(':');
        StdDateFormat.pad2(stringBuffer, calendar_getCalendar.get(13));
        stringBuffer.append('.');
        StdDateFormat.pad3(stringBuffer, calendar_getCalendar.get(14));
        final int offset = timeZone.getOffset(calendar_getCalendar.getTimeInMillis());
        if (0 != offset) {
            final int i3 = offset / 60000;
            final int iAbs = Math.abs(i3 / 60);
            final int iAbs2 = Math.abs(i3 % 60);
            stringBuffer.append(0 > offset ? '-' : '+');
            StdDateFormat.pad2(stringBuffer, iAbs);
            if (_tzSerializedWithColon) {
                stringBuffer.append(':');
            }
            StdDateFormat.pad2(stringBuffer, iAbs2);
            return;
        }
        if (_tzSerializedWithColon) {
            stringBuffer.append("+00:00");
        } else {
            stringBuffer.append("+0000");
        }
    }
    protected void _formatBCEYear(final StringBuffer stringBuffer, final int i2) {
        if (1 == i2) {
            stringBuffer.append("+0000");
        } else {
            stringBuffer.append('-');
            StdDateFormat.pad4(stringBuffer, i2 - 1);
        }
    }
    private static void pad2(final StringBuffer stringBuffer, int i2) {
        final int i3 = i2 / 10;
        if (0 == i3) {
            stringBuffer.append('0');
        } else {
            stringBuffer.append((char) (i3 + 48));
            i2 -= i3 * 10;
        }
        stringBuffer.append((char) (i2 + 48));
    }
    private static void pad3(final StringBuffer stringBuffer, int i2) {
        final int i3 = i2 / 100;
        if (0 == i3) {
            stringBuffer.append('0');
        } else {
            stringBuffer.append((char) (i3 + 48));
            i2 -= i3 * 100;
        }
        StdDateFormat.pad2(stringBuffer, i2);
    }
    private static void pad4(final StringBuffer stringBuffer, int i2) {
        final int i3 = i2 / 100;
        if (0 == i3) {
            stringBuffer.append('0');
            stringBuffer.append('0');
        } else {
            if (99 < i3) {
                stringBuffer.append(i3);
            } else {
                StdDateFormat.pad2(stringBuffer, i3);
            }
            i2 -= i3 * 100;
        }
        StdDateFormat.pad2(stringBuffer, i2);
    }
    public String toString() {
        return String.format("DateFormat %s: (timezone: %s, locale: %s, lenient: %s)", this.getClass().getName(), _timezone, _locale, _lenient);
    }
    public String toPattern() {
        String sb = "[one of: '" +
                StdDateFormat.DATE_FORMAT_STR_ISO8601 +
                "', '" +
                StdDateFormat.DATE_FORMAT_STR_RFC1123 +
                "' (" +
                (Boolean.FALSE.equals(_lenient) ? "strict" : "lenient") +
                ")]";
        return sb;
    }
    public int hashCode() {
        return System.identityHashCode(this);
    }
    protected boolean looksLikeISO8601(final String str) {
        return 7 <= str.length() && Character.isDigit(str.charAt(0)) && Character.isDigit(str.charAt(3)) && '-' == str.charAt(4) && Character.isDigit(str.charAt(5));
    }
    private Date _parseDateFromLong(final String str, final ParsePosition parsePosition) throws ParseException {
        try {
            return new Date(NumberInput.parseLong(str));
        } catch (final NumberFormatException unused) {
            throw new ParseException(String.format("Timestamp value %s out of 64-bit value range", str), parsePosition.getErrorIndex());
        }
    }
    protected Date parseAsISO8601(final String str, final ParsePosition parsePosition) throws ParseException {
        try {
            return this._parseAsISO8601(str, parsePosition);
        } catch (final IllegalArgumentException e2) {
            throw new ParseException(String.format("Cannot parse date \"%s\", problem: %s", str, e2.getMessage()), parsePosition.getErrorIndex());
        }
    }
    protected Date _parseAsISO8601(final String str, final ParsePosition parsePosition) throws ParseException, IllegalArgumentException {
        final String str2;
        final int length = str.length();
        TimeZone timeZone = StdDateFormat.DEFAULT_TIMEZONE;
        if (null != this._timezone && 'Z' != str.charAt(length - 1)) {
            timeZone = _timezone;
        }
        final Calendar calendar_getCalendar = this._getCalendar(timeZone);
        calendar_getCalendar.clear();
        int iCharAt = 0;
        if (10 >= length) {
            if (StdDateFormat.PATTERN_PLAIN.matcher(str).matches()) {
                calendar_getCalendar.set(StdDateFormat._parse4D(str, 0), StdDateFormat._parse2D(str, 5) - 1, StdDateFormat._parse2D(str, 8), 0, 0, 0);
                calendar_getCalendar.set(14, 0);
                return calendar_getCalendar.getTime();
            }
            str2 = StdDateFormat.DATE_FORMAT_STR_PLAIN;
        } else {
            final Matcher matcher = StdDateFormat.PATTERN_ISO8601.matcher(str);
            if (matcher.matches()) {
                final int iStart = matcher.start(2);
                final int iEnd = matcher.end(2);
                final int i2 = iEnd - iStart;
                if (1 < i2) {
                    int i_parse2D = StdDateFormat._parse2D(str, iStart + 1) * 3600;
                    if (5 <= i2) {
                        i_parse2D += StdDateFormat._parse2D(str, iEnd - 2) * 60;
                    }
                    calendar_getCalendar.set(15, '-' == str.charAt(iStart) ? i_parse2D * NotificationManagerCompat.IMPORTANCE_UNSPECIFIED : i_parse2D * 1000);
                    calendar_getCalendar.set(16, 0);
                }
                calendar_getCalendar.set(StdDateFormat._parse4D(str, 0), StdDateFormat._parse2D(str, 5) - 1, StdDateFormat._parse2D(str, 8), StdDateFormat._parse2D(str, 11), StdDateFormat._parse2D(str, 14), (16 >= length || ':' != str.charAt(16)) ? 0 : StdDateFormat._parse2D(str, 17));
                final int iStart2 = matcher.start(1);
                final int i3 = iStart2 + 1;
                final int iEnd2 = matcher.end(1);
                if (i3 >= iEnd2) {
                    calendar_getCalendar.set(14, 0);
                } else {
                    final int i4 = iEnd2 - i3;
                    if (0 != i4) {
                        if (1 != i4) {
                            if (2 != i4) {
                                if (3 != i4 && 9 < i4) {
                                    throw new ParseException(String.format("Cannot parse date \"%s\": invalid fractional seconds '%s'; can use at most 9 digits", str, matcher.group(1).substring(1)), i3);
                                }
                                iCharAt = str.charAt(iStart2 + 3) - '0';
                            }
                            iCharAt += (str.charAt(iStart2 + 2) - '0') * 10;
                        }
                        iCharAt += (str.charAt(i3) - '0') * 100;
                    }
                    calendar_getCalendar.set(14, iCharAt);
                }
                return calendar_getCalendar.getTime();
            }
            str2 = StdDateFormat.DATE_FORMAT_STR_ISO8601;
        }
        throw new ParseException(String.format("Cannot parse date \"%s\": while it seems to fit format '%s', parsing fails (leniency? %s)", str, str2, _lenient), 0);
    }
    private static int _parse4D(final String str, final int i2) {
        return ((str.charAt(i2) - '0') * 1000) + ((str.charAt(i2 + 1) - '0') * 100) + ((str.charAt(i2 + 2) - '0') * 10) + (str.charAt(i2 + 3) - '0');
    }
    private static int _parse2D(final String str, final int i2) {
        return ((str.charAt(i2) - '0') * 10) + (str.charAt(i2 + 1) - '0');
    }
    protected Date parseAsRFC1123(final String str, final ParsePosition parsePosition) {
        if (null == this._formatRFC1123) {
            _formatRFC1123 = StdDateFormat._cloneFormat(StdDateFormat.DATE_FORMAT_RFC1123, StdDateFormat.DATE_FORMAT_STR_RFC1123, _timezone, _locale, _lenient);
        }
        return _formatRFC1123.parse(str, parsePosition);
    }
    private static final DateFormat _cloneFormat(final DateFormat dateFormat, final String str, TimeZone timeZone, final Locale locale, final Boolean bool) {
        final DateFormat simpleDateFormat;
        if (!locale.equals(StdDateFormat.DEFAULT_LOCALE)) {
            simpleDateFormat = new SimpleDateFormat(str, locale);
            if (null == timeZone) {
                timeZone = StdDateFormat.DEFAULT_TIMEZONE;
            }
            simpleDateFormat.setTimeZone(timeZone);
        } else {
            simpleDateFormat = (DateFormat) dateFormat.clone();
            if (null != timeZone) {
                simpleDateFormat.setTimeZone(timeZone);
            }
        }
        if (null != bool) {
            simpleDateFormat.setLenient(bool.booleanValue());
        }
        return simpleDateFormat;
    }
    protected void _clearFormats() {
        _formatRFC1123 = null;
    }
    protected Calendar _getCalendar(final TimeZone timeZone) {
        Calendar calendar = _calendar;
        if (null == calendar) {
            calendar = (Calendar) StdDateFormat.CALENDAR.clone();
            _calendar = calendar;
        }
        if (!calendar.getTimeZone().equals(timeZone)) {
            calendar.setTimeZone(timeZone);
        }
        calendar.setLenient(this.isLenient());
        return calendar;
    }
    protected static <T> boolean _equals(final T t, final T t2) {
        return Objects.equals(t, t2);
    }
}
