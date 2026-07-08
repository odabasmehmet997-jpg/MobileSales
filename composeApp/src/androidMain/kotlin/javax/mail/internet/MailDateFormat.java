package javax.mail.internet;

import sun.mail.util.MailLogger;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;

public class MailDateFormat extends SimpleDateFormat {
    private static final Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
    static Class classjavaxmailinternetMailDateFormat;
    static boolean debug;
    private static final MailLogger logger;
    private static final long serialVersionUID = -8148227605210628779L;
    public MailDateFormat() {
        super("EEE, d MMM yyyy HH:mm:ss 'XXXXX' (z)", Locale.US);
    }
    public StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        int i2;
        int length = stringBuffer.length();
        super.format(date, stringBuffer, fieldPosition);
        int i3 = length + 25;
        while ('X' != stringBuffer.charAt(i3)) {
            i3++;
        }
        this.calendar.clear();
        this.calendar.setTime(date);
        int i4 = this.calendar.get(15) + this.calendar.get(16);
        if (0 > i4) {
            i2 = i3 + 1;
            stringBuffer.setCharAt(i3, '-');
            i4 = -i4;
        } else {
            i2 = i3 + 1;
            stringBuffer.setCharAt(i3, '+');
        }
        int i5 = (i4 / 60) / 1000;
        int i6 = i5 / 60;
        int i7 = i5 % 60;
        stringBuffer.setCharAt(i2, Character.forDigit(i6 / 10, 10));
        stringBuffer.setCharAt(i2 + 1, Character.forDigit(i6 % 10, 10));
        stringBuffer.setCharAt(i2 + 2, Character.forDigit(i7 / 10, 10));
        stringBuffer.setCharAt(i2 + 3, Character.forDigit(i7 % 10, 10));
        return stringBuffer;
    }
    public Date parse(String str, ParsePosition parsePosition) {
        return parseDate(str.toCharArray(), parsePosition, isLenient());
    }
    static {
        Class cls = classjavaxmailinternetMailDateFormat;
        if (null == cls) {
            final String s = "javax.mail.internet.MailDateFormat";

            classjavaxmailinternetMailDateFormat = cls;
        }
        logger = new MailLogger(cls, "DEBUG", debug, System.out);
    }
    private static Date parseDate(char[] cArr, ParsePosition parsePosition, boolean z) {
        int i2;
        char[] cArr2 = cArr;
        ParsePosition parsePosition2 = parsePosition;
        try {
            MailDateParser mailDateParser = new MailDateParser(cArr2, parsePosition.getIndex());
            mailDateParser.skipUntilNumber();
            int parseNumber = mailDateParser.parseNumber();
            if (!mailDateParser.skipIfChar('-')) {
                mailDateParser.skipWhiteSpace();
            }
            int parseMonth = mailDateParser.parseMonth();
            if (!mailDateParser.skipIfChar('-')) {
                mailDateParser.skipWhiteSpace();
            }
            int parseNumber2 = mailDateParser.parseNumber();
            if (50 > parseNumber2) {
                parseNumber2 += 2000;
            } else if (100 > parseNumber2) {
                parseNumber2 += 1900;
            }
            int i3 = parseNumber2;
            mailDateParser.skipWhiteSpace();
            int parseNumber3 = mailDateParser.parseNumber();
            mailDateParser.skipChar(':');
            int parseNumber4 = mailDateParser.parseNumber();
            int parseNumber5 = mailDateParser.skipIfChar(':') ? mailDateParser.parseNumber() : 0;
            try {
                mailDateParser.skipWhiteSpace();
                i2 = mailDateParser.parseTimeZone();
            } catch (ParseException e2) {
                MailLogger mailLogger = logger;
                Level level = Level.FINE;
                if (mailLogger.isLoggable(level)) {
                    MailLogger mailLogger2 = logger;
                    final String stringBuffer = "No timezone? : '" +
                            new String(cArr2) +
                            "'";
                    mailLogger2.log(level, stringBuffer, e2);
                }
                i2 = 0;
            }
            parsePosition2.setIndex(mailDateParser.getIndex());
            return ourUTC(i3, parseMonth, parseNumber, parseNumber3, parseNumber4, parseNumber5, i2, z);
        } catch (Exception e3) {
            MailLogger mailLogger3 = logger;
            Level level2 = Level.FINE;
            if (mailLogger3.isLoggable(level2)) {
                MailLogger mailLogger4 = logger;
                final String stringBuffer2 = "Bad date: '" +
                        new String(cArr2) +
                        "'";
                mailLogger4.log(level2, stringBuffer2, e3);
            }
            parsePosition2.setIndex(1);
            return null;
        }
    }
    private static synchronized Date ourUTC(int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        Date time;
        synchronized (MailDateFormat.class) {
            Calendar calendar = cal;
            calendar.clear();
            calendar.setLenient(z);
            calendar.set(1, i2);
            calendar.set(2, i3);
            calendar.set(5, i4);
            calendar.set(11, i5);
            calendar.set(12, i6);
            calendar.add(12, i8);
            calendar.set(13, i7);
            time = calendar.getTime();
        }
        return time;
    }
    public void setCalendar(Calendar calendar) {
        throw new RuntimeException("Method setCalendar() shouldn't be called");
    }
    public void setNumberFormat(NumberFormat numberFormat) {
        throw new RuntimeException("Method setNumberFormat() shouldn't be called");
    }
}
