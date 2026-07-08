package com.sun.mail.util;

import javax.mail.Session;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class MailLogger {
    static Class classcomsunmailutilMailLogger;
    private final boolean debug;
    private final Logger logger;
    private final PrintStream out;
    private final String prefix;

    public MailLogger(String str, String str2, boolean z, PrintStream printStream) {
        this.logger = Logger.getLogger(str);
        this.prefix = str2;
        this.debug = z;
        this.out = null == printStream ? System.out : printStream;
    }

    public MailLogger(Class cls, String str, boolean z, PrintStream printStream) {
        this.logger = Logger.getLogger(packageOf(cls));
        this.prefix = str;
        this.debug = z;
        this.out = null == printStream ? System.out : printStream;
    }

    public MailLogger(Class cls, String str, String str2, boolean z, PrintStream printStream) {
        final String stringBuffer = packageOf(cls) +
                "." +
                str;
        this.logger = Logger.getLogger(stringBuffer);
        this.prefix = str2;
        this.debug = z;
        this.out = null == printStream ? System.out : printStream;
    }

    public MailLogger(String str, String str2, Session session) {
        this(str, str2, session.getDebug(), session.getDebugOut());
    }

    public MailLogger(Class cls, String str, Session session) {
        this(cls, str, session.getDebug(), session.getDebugOut());
    }

    public MailLogger getLogger(String str, String str2) {
        return new MailLogger(str, str2, this.debug, this.out);
    }

    public MailLogger getLogger(Class cls, String str) {
        return new MailLogger(cls, str, this.debug, this.out);
    }

    public MailLogger getSubLogger(String str, String str2) {
        final String stringBuffer = this.logger.getName() +
                "." +
                str;
        return new MailLogger(stringBuffer, str2, this.debug, this.out);
    }

    public MailLogger getSubLogger(String str, String str2, boolean z) {
        final String stringBuffer = this.logger.getName() +
                "." +
                str;
        return new MailLogger(stringBuffer, str2, z, this.out);
    }

    public void log(Level level, String str) {
        ifDebugOut(str);
        if (this.logger.isLoggable(level)) {
            String[] inferCaller = inferCaller();
            this.logger.logp(level, inferCaller[0], inferCaller[1], str);
        }
    }

    public void log(Level level, String str, Object obj) {
        if (this.debug) {
            str = MessageFormat.format(str, obj);
            debugOut(str);
        }
        String str2 = str;
        if (this.logger.isLoggable(level)) {
            String[] inferCaller = inferCaller();
            this.logger.logp(level, inferCaller[0], inferCaller[1], str2, obj);
        }
    }

    public void log(Level level, String str, Object[] objArr) {
        if (this.debug) {
            str = MessageFormat.format(str, objArr);
            debugOut(str);
        }
        String str2 = str;
        if (this.logger.isLoggable(level)) {
            String[] inferCaller = inferCaller();
            this.logger.logp(level, inferCaller[0], inferCaller[1], str2, objArr);
        }
    }

    public void log(Level level, String str, Throwable th) {
        if (this.debug) {
            if (null != th) {
                final String stringBuffer = str +
                        ", THROW: ";
                debugOut(stringBuffer);
                th.printStackTrace(this.out);
            } else {
                debugOut(str);
            }
        }
        if (this.logger.isLoggable(level)) {
            String[] inferCaller = inferCaller();
            this.logger.logp(level, inferCaller[0], inferCaller[1], str, th);
        }
    }

    public void config(String str) {
        log(Level.CONFIG, str);
    }

    public void fine(String str) {
        log(Level.FINE, str);
    }

    public void finer(String str) {
        log(Level.FINER, str);
    }

    public void finest(String str) {
        log(Level.FINEST, str);
    }

    public boolean isLoggable(Level level) {
        return this.debug || this.logger.isLoggable(level);
    }

    private void ifDebugOut(String str) {
        if (this.debug) {
            debugOut(str);
        }
    }

    private void debugOut(String str) {
        if (null != prefix) {
            PrintStream printStream = this.out;
            final String stringBuffer = this.prefix +
                    ": " +
                    str;
            printStream.println(stringBuffer);
            return;
        }
        this.out.println(str);
    }

    private String packageOf(Class cls) {
        Package packageR = cls.getPackage();
        if (null != packageR) {
            return packageR.getName();
        }
        String name = cls.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (0 < lastIndexOf) {
            return name.substring(0, lastIndexOf);
        }
        return "";
    }

    private String[] inferCaller() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        int i2 = 0;
        while (i2 < stackTrace.length && !isLoggerImplFrame(stackTrace[i2].getClassName())) {
            i2++;
        }
        while (i2 < stackTrace.length) {
            StackTraceElement stackTraceElement = stackTrace[i2];
            String className = stackTraceElement.getClassName();
            if (!isLoggerImplFrame(className)) {
                return new String[]{className, stackTraceElement.getMethodName()};
            }
            i2++;
        }
        return new String[]{null, null};
    }

    static Class class(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError().initCause(e2);
        }
    }

    private boolean isLoggerImplFrame(String str) {
        Class cls = classcomsunmailutilMailLogger;
        if (null == cls) {
            try {
                cls = class("com.sun.mail.util.MailLogger");
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            classcomsunmailutilMailLogger = cls;
        }
        return cls.getName().equals(str);
    }
}
