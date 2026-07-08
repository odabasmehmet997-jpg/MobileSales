package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JavaType;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public enum BeanUtil {
    ;

    public static Object getDefaultValue(final JavaType javaType) {
        final Class<?> rawClass = javaType.getRawClass();
        final Class<?> clsPrimitiveType = ClassUtil.primitiveType(rawClass);
        if (null != clsPrimitiveType) {
            return ClassUtil.defaultValue(clsPrimitiveType);
        }
        if (javaType.isContainerType() || javaType.isReferenceType()) {
            return JsonInclude.Include.NON_EMPTY;
        }
        if (String.class == rawClass) {
            return "";
        }
        if (javaType.isTypeOrSubTypeOf(Date.class)) {
            return new Date(0L);
        }
        if (!javaType.isTypeOrSubTypeOf(Calendar.class)) {
            return null;
        }
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(0L);
        return gregorianCalendar;
    }

    public static String checkUnsupportedType(final JavaType javaType) {
        final String str;
        final String str2;
        final Class<?> rawClass = javaType.getRawClass();
        if (BeanUtil.isJava8TimeClass(rawClass)) {
            str = "Java 8 date/time";
            str2 = "com.fasterxml.jackson.datatype:jackson-datatype-jsr310";
        } else {
            if (!BeanUtil.isJodaTimeClass(rawClass)) {
                return null;
            }
            str = "Joda date/time";
            str2 = "com.fasterxml.jackson.datatype:jackson-datatype-joda";
        }
        return String.format("%s type %s not supported by default: add Module \"%s\" to enable handling", str, ClassUtil.getTypeDescription(javaType), str2);
    }

    public static boolean isJava8TimeClass(final Class<?> cls) {
        return cls.getName().startsWith("java.time.");
    }

    public static boolean isJodaTimeClass(final Class<?> cls) {
        return cls.getName().startsWith("org.joda.time.");
    }
}
