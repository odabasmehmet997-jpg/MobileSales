package org.simpleframework.xml.transform;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


class PackageMatcher implements Matcher {
    @Override // org.simpleframework.xml.transform.Matcher
    public Transform match(final Class cls) throws Exception {
        final String name = cls.getName();
        if (name.startsWith("java.lang")) {
            return this.matchLanguage(cls);
        }
        if (name.startsWith("java.util")) {
            return this.matchUtility(cls);
        }
        if (name.startsWith("java.net")) {
            return this.matchURL(cls);
        }
        if (name.startsWith("java.io")) {
            return this.matchFile(cls);
        }
        if (name.startsWith("java.sql")) {
            return this.matchSQL(cls);
        }
        if (name.startsWith("java.math")) {
            return this.matchMath(cls);
        }
        return this.matchEnum(cls);
    }

    private Transform matchEnum(final Class cls) {
        final Class superclass = cls.getSuperclass();
        if (null == superclass) {
            return null;
        }
        if (superclass.isEnum()) {
            return new EnumTransform(cls);
        }
        if (cls.isEnum()) {
            return new EnumTransform(cls);
        }
        return null;
    }

    private Transform matchLanguage(final Class cls) throws Exception {
        if (Boolean.class == cls) {
            return new BooleanTransform();
        }
        if (Integer.class == cls) {
            return new IntegerTransform();
        }
        if (Long.class == cls) {
            return new LongTransform();
        }
        if (Double.class == cls) {
            return new DoubleTransform();
        }
        if (Float.class == cls) {
            return new FloatTransform();
        }
        if (Short.class == cls) {
            return new ShortTransform();
        }
        if (Byte.class == cls) {
            return new ByteTransform();
        }
        if (Character.class == cls) {
            return new CharacterTransform();
        }
        if (String.class == cls) {
            return new StringTransform();
        }
        if (Class.class == cls) {
            return new ClassTransform();
        }
        return null;
    }

    private Transform matchMath(final Class cls) throws Exception {
        if (BigDecimal.class == cls) {
            return new BigDecimalTransform();
        }
        if (BigInteger.class == cls) {
            return new BigIntegerTransform();
        }
        return null;
    }

    private Transform matchUtility(final Class cls) throws Exception {
        if (Date.class == cls) {
            return new DateTransform(Date.class);
        }
        if (Locale.class == cls) {
            return new LocaleTransform();
        }
        if (Currency.class == cls) {
            return new CurrencyTransform();
        }
        if (GregorianCalendar.class == cls) {
            return new GregorianCalendarTransform();
        }
        if (TimeZone.class == cls) {
            return new TimeZoneTransform();
        }
        if (AtomicInteger.class == cls) {
            return new AtomicIntegerTransform();
        }
        if (AtomicLong.class == cls) {
            return new AtomicLongTransform();
        }
        return null;
    }

    private Transform matchSQL(final Class cls) throws Exception {
        if (Time.class == cls) {
            return new DateTransform(Time.class);
        }
        if (java.sql.Date.class == cls) {
            return new DateTransform(java.sql.Date.class);
        }
        if (Timestamp.class == cls) {
            return new DateTransform(Timestamp.class);
        }
        return null;
    }

    private Transform matchFile(final Class cls) throws Exception {
        if (File.class == cls) {
            return new FileTransform();
        }
        return null;
    }

    private Transform matchURL(final Class cls) throws Exception {
        if (URL.class == cls) {
            return new URLTransform();
        }
        return null;
    }
}
