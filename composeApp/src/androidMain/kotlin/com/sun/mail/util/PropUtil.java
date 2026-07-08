package com.sun.mail.util;

import javax.mail.Session;
import java.util.Properties;

public enum PropUtil {
    ;

    public static int getIntProperty(Properties properties, String str, int i2) {
        return getInt(getProp(properties, str), i2);
    }

    public static boolean getBooleanProperty(Properties properties, String str, boolean z) {
        return getBoolean(getProp(properties, str), z);
    }

    public static int getIntSessionProperty(Session session, String str, int i2) {
        return getInt(getProp(session.getProperties(), str), i2);
    }

    public static boolean getBooleanSessionProperty(Session session, String str, boolean z) {
        return getBoolean(getProp(session.getProperties(), str), z);
    }

    public static boolean getBooleanSystemProperty(String r1, boolean r2) {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.util.PropUtil.getBooleanSystemProperty(java.lang.String, boolean):boolean");
    }

    private static Object getProp(Properties properties, String str) {
        Object obj = properties.get(str);
        if (null != obj) {
            return obj;
        }
        return properties.getProperty(str);
    }

    private static int getInt(Object obj, int i2) {
        if (null == obj) {
            return i2;
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException unused) {
            }
        }
        return obj instanceof Integer ? ((Integer) obj).intValue() : i2;
    }

    private static boolean getBoolean(Object obj, boolean z) {
        if (null == obj) {
            return z;
        }
        if (!(obj instanceof String)) {
            return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : z;
        }
        if (z) {
            return !"false".equalsIgnoreCase((String) obj);
        }
        return "true".equalsIgnoreCase((String) obj);
    }
}
