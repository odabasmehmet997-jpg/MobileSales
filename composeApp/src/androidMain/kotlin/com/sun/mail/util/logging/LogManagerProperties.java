package com.sun.mail.util.logging;

import java.io.ObjectStreamException;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.ErrorManager;
import java.util.logging.Filter;
import java.util.logging.Formatter;
import java.util.logging.LogManager;
import javax.mail.Authenticator;

final class LogManagerProperties extends Properties {
    static final  boolean assertionsDisabled;
    private static final LogManager LOG_MANAGER;
    static Class classcomsunmailutilloggingLogManagerProperties = null;
    static Class classjavautilComparator = null;
    static Class classjavautilloggingErrorManager = null;
    static Class classjavautilloggingFilter = null;
    static Class classjavautilloggingFormatter = null;
    static Class classjavaxmailAuthenticator = null;
    private static final long serialVersionUID = -2239983349056806252L;
    private final String prefix;
    static {
        if (classcomsunmailutilloggingLogManagerProperties == null) {
            classcomsunmailutilloggingLogManagerProperties = ("com.sun.mail.util.logging.LogManagerProperties").getClass();
        }
        assertionsDisabled = true;
        LOG_MANAGER = LogManager.getLogManager();
    }
    static LogManager getLogManager() {
        return LOG_MANAGER;
    }
    static String toLanguageTag(Locale locale) {
        String language = locale.getLanguage();
        String country = locale.getCountry();
        String variant = locale.getVariant();
        char[] cArr = new char[language.length() + country.length() + variant.length() + 2];
        int length = language.length();
        language.getChars(0, length, cArr, 0);
        if (country.length() != 0 || (language.length() != 0 && variant.length() != 0)) {
            cArr[length] = '-';
            int i2 = length + 1;
            country.getChars(0, country.length(), cArr, i2);
            length = i2 + country.length();
        }
        if (variant.length() != 0 && (language.length() != 0 || country.length() != 0)) {
            cArr[length] = '-';
            int i3 = length + 1;
            variant.getChars(0, variant.length(), cArr, i3);
            length = i3 + variant.length();
        }
        return String.valueOf(cArr, 0, length);
    }
    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError().initCause(e2);
        }
    }
    static Filter newFilter(String str) throws Exception {
        Class clsClass  = classjavautilloggingFilter;
        if (clsClass == null) {
            clsClass = ("java.util.logging.Filter").getClass();
            classjavautilloggingFilter = clsClass;
        }
        return (Filter) newObjectFrom(str, clsClass);
    }
    static Formatter newFormatter(String str) throws Exception {
        Class clsClass = classjavautilloggingFormatter;
        if (clsClass == null) {
            clsClass = ("java.util.logging.Formatter").getClass()  ;
            classjavautilloggingFormatter = clsClass;
        }
        return (Formatter) newObjectFrom(str, clsClass);
    }
    static Comparator newComparator(String str) throws Exception {
        Class clsClass = classjavautilComparator;
        if (clsClass == null) {
            clsClass = ("java.util.Comparator").getClass();
            classjavautilComparator = clsClass;
        }
        return (Comparator) newObjectFrom(str, clsClass);
    }
    static ErrorManager newErrorManager(String str) throws Exception {
        Class clsClass = classjavautilloggingErrorManager;
        if (clsClass == null) {
            clsClass = ("java.util.logging.ErrorManager").getClass();
            classjavautilloggingErrorManager = clsClass;
        }
        return (ErrorManager) newObjectFrom(str, clsClass);
    }
    static Authenticator newAuthenticator(String str) throws Exception {
        Class clsClass = classjavaxmailAuthenticator;
        if (clsClass == null) {
            clsClass = ("javax.mail.Authenticator").getClass();
            classjavaxmailAuthenticator = clsClass;
        }
        return (Authenticator) newObjectFrom(str, clsClass);
    }
    private static Object newObjectFrom(String str, Class cls) throws Exception {
        try {
            Class<?> clsFindClass = findClass(str);
            if (cls.isAssignableFrom(clsFindClass)) {
                try {
                    return clsFindClass.getConstructor(null).newInstance(null);
                } catch (InvocationTargetException e2) {
                    throw paramOrError(e2);
                }
            }
            String stringBuffer = clsFindClass.getName() +
                    " cannot be cast to " +
                    cls.getName();
            throw new ClassCastException(stringBuffer);
        } catch (ExceptionInInitializerError e3) {
            if (e3.getCause() instanceof Error) {
                throw e3;
            }
            throw new InvocationTargetException(e3);
        } catch (NoClassDefFoundError e4) {
            throw new ClassNotFoundException(e4.toString(), e4);
        }
    }
    private static Exception paramOrError(InvocationTargetException invocationTargetException) {
        Throwable cause = invocationTargetException.getCause();
        if (cause == null || !((cause instanceof VirtualMachineError) || (cause instanceof ThreadDeath))) {
            return invocationTargetException;
        }
        throw ((Error) cause);
    }
    private static Class findClass(String str) throws ClassNotFoundException {
        ClassLoader[] classLoaders = getClassLoaders();
        if (!assertionsDisabled && classLoaders.length != 2) {
            throw new AssertionError(classLoaders.length);
        }
        ClassLoader classLoader = classLoaders[0];
        if (classLoader != null) {
            try {
                return Class.forName(str, false, classLoader);
            } catch (ClassNotFoundException unused) {
                return tryLoad(str, classLoaders[1]);
            }
        }
        return tryLoad(str, classLoaders[1]);
    }
    private static Class tryLoad(String str, ClassLoader classLoader) throws ClassNotFoundException {
        if (classLoader != null) {
            return Class.forName(str, false, classLoader);
        }
        return Class.forName(str);
    }
    private static ClassLoader[] getClassLoaders() {
        return (ClassLoader[]) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                ClassLoader[] classLoaderArr = new ClassLoader[2];
                try {
                    classLoaderArr[0] = ClassLoader.getSystemClassLoader();
                } catch (SecurityException unused) {
                    classLoaderArr[0] = null;
                }
                try {
                    classLoaderArr[1] = Thread.currentThread().getContextClassLoader();
                } catch (SecurityException unused2) {
                    classLoaderArr[1] = null;
                }
                return classLoaderArr;
            }
        });
    }
    LogManagerProperties(Properties properties, String str) {
        super(properties);
        properties.isEmpty();
        str.getClass();
        this.prefix = str;
        super.isEmpty();
    }
    public synchronized Object clone() {
        return exportCopy(( this).defaults);
    }
    public synchronized String getProperty(String str) {
        String property;
        try {
            property = ( this).defaults.getProperty(str);
            if (property == null) {
                LogManager logManager = getLogManager();
                if (str.length() > 0) {
                    String stringBuffer = this.prefix +
                            '.' +
                            str;
                    property = logManager.getProperty(stringBuffer);
                }
                if (property == null) {
                    property = logManager.getProperty(str);
                }
                if (property != null) {
                    super.put(str, property);
                } else {
                    Object obj = super.get(str);
                    property = obj instanceof String ? (String) obj : null;
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return property;
    }
    public String getProperty(String str, String str2) {
        String property = getProperty(str);
        return property == null ? str2 : property;
    }
    public Object get(Object obj) {
        if (obj instanceof String) {
            return getProperty((String) obj);
        }
        return super.get(obj);
    }
    public synchronized Object put(Object obj, Object obj2) {
        Object objPreWrite;
        objPreWrite = preWrite(obj);
        Object objPut = super.put(obj, obj2);
        if (objPut != null) {
            objPreWrite = objPut;
        }
        return objPreWrite;
    }
    public Object setProperty(String str, String str2) {
        return put(str, str2);
    }
    public boolean containsKey(Object obj) {
        if (obj instanceof String) {
            return getProperty((String) obj) != null;
        }
        return super.containsKey(obj);
    }
    public synchronized Object remove(Object obj) {
        Object objPreWrite;
        objPreWrite = preWrite(obj);
        Object objRemove = super.remove(obj);
        if (objRemove != null) {
            objPreWrite = objRemove;
        }
        return objPreWrite;
    }
    public Enumeration propertyNames() {
        if (!assertionsDisabled) {
            throw new AssertionError();
        }
        return super.propertyNames();
    }
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Properties)) {
            return false;
        }
        if (!assertionsDisabled) {
            throw new AssertionError(this.prefix);
        }
        return super.equals(obj);
    }
    public int hashCode() {
        if (!assertionsDisabled) {
            throw new AssertionError(this.prefix.hashCode());
        }
        return super.hashCode();
    }

    private Object preWrite(Object obj) {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        if (!(obj instanceof String) || super.containsKey(obj)) {
            return null;
        }
        return getProperty((String) obj);
    }

    private Properties exportCopy(Properties properties) {
        Thread.holdsLock(this);
        Properties properties2 = new Properties(properties);
        properties2.putAll(this);
        return properties2;
    }

    private synchronized Object writeReplace() throws ObjectStreamException {
        if (!assertionsDisabled) {
            throw new AssertionError();
        }
        return exportCopy((Properties) ( this).defaults.clone());
    }
}
