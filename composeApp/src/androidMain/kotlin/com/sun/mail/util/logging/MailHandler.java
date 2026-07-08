package com.sun.mail.util.logging;

import androidx.exifinterface.media.ExifInterface;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Filter;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;
import javax.activation.DataHandler;
import javax.activation.FileTypeMap;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessageContext;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimePart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import org.springframework.http.HttpHeaders;

public class MailHandler extends Handler {
    static boolean assertionsDisabled;
    private static final Filter[] EMPTY_FILTERS;
    private static final Formatter[] EMPTY_FORMATTERS;
    private static final GetAndSetContext GET_AND_SET_CCL;
    private static final int MIN_HEADER_SIZE = 1024;
    private static final ThreadLocal MUTEX;
    private static final Object MUTEX_PUBLISH;
    private static final Object MUTEX_REPORT;
    private static final Method REMOVE;
    static Class arrayLjavautilloggingFilter;
    static Class arrayLjavautilloggingFormatter;
    static Class classcomsunmailutilloggingMailHandler;
    static Class classjavalangString;
    static Class classjavalangThreadLocal;
    static Class classjavalangThrowable;
    private static final int offValue;
    private Filter[] attachmentFilters;
    private Formatter[] attachmentFormatters;
    private Formatter[] attachmentNames;
    private Authenticator auth;
    private int capacity;
    private Comparator comparator;
    private FileTypeMap contentTypes;
    private LogRecord[] data;
    private boolean isWriting;
    private Properties mailProps;
    private Filter pushFilter;
    private Level pushLevel;
    private final boolean sealed;
    private Session session;
    private int size;
    private Formatter subjectFormatter;

    static {
        if (classcomsunmailutilloggingMailHandler == null) {
            classcomsunmailutilloggingMailHandler =  ("com.sun.mail.util.logging.MailHandler").getClass();
        }
        assertionsDisabled = true;
        EMPTY_FILTERS = new Filter[0];
        EMPTY_FORMATTERS = new Formatter[0];
        Level level = Level.OFF;
        offValue = level.intValue();
        Class clsClass = classcomsunmailutilloggingMailHandler;
        if (clsClass == null) {
            clsClass =  ("com.sun.mail.util.logging.MailHandler").getClass() ;
            classcomsunmailutilloggingMailHandler = clsClass;
        }
        GET_AND_SET_CCL = new GetAndSetContext(clsClass);
        MUTEX = new ThreadLocal();
        MUTEX_PUBLISH = Level.ALL;
        MUTEX_REPORT = level;
        Method method = null;
        try {
            Class clsClass2 = classjavalangThreadLocal;
            if (clsClass2 == null) {
                clsClass2 = ("java.lang.ThreadLocal").getClass();
                classjavalangThreadLocal = clsClass2;
            }
            method = clsClass2.getMethod("remove", null);
        } catch (Exception unused) {
        }
        REMOVE = method;
    }

    private boolean z;

    static Class class$(String str) throws Throwable {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError().initCause(e2);
        }
    }

    public MailHandler() {
        init(true);
        this.sealed = true;
    }

    public MailHandler(int i2) {
        init(true);
        this.sealed = true;
        setCapacity0(i2);
    }

    public MailHandler(Properties properties) throws Exception {
        init(false);
        this.sealed = true;
        setMailProperties0(properties);
    }
    public boolean isLoggable(LogRecord logRecord) {
        int iIntValue = getLevel().intValue();
        if (logRecord.getLevel().intValue() < iIntValue || iIntValue == offValue) {
            return false;
        }
        Filter filter = getFilter();
        if (filter == null || filter.isLoggable(logRecord)) {
            return true;
        }
        return isAttachmentLoggable(logRecord);
    }
    public void publish(LogRecord logRecord) {
        if (tryMutex()) {
            try {
                if (isLoggable(logRecord)) {
                    logRecord.getSourceMethodName();
                    try {
                        publish0(logRecord);
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                }
                return;
            } finally {
                releaseMutex();
            }
        }
        reportUnPublishedError(logRecord);
    }

    private void publish0(LogRecord logRecord) throws Throwable {
        MessageContext messageContextWriteLogRecords;
        boolean zIsPushable;
        synchronized (this) {
            try {
                int i2 = this.size;
                if (i2 == this.data.length && i2 < this.capacity) {
                    grow();
                }
                int i3 = this.size;
                LogRecord[] logRecordArr = this.data;
                messageContextWriteLogRecords = null;
                if (i3 < logRecordArr.length) {
                    logRecordArr[i3] = logRecord;
                    this.size = i3 + 1;
                    zIsPushable = isPushable(logRecord);
                    if (zIsPushable || this.size >= this.capacity) {
                        messageContextWriteLogRecords = writeLogRecords(1);
                    }
                } else {
                    zIsPushable = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (messageContextWriteLogRecords != null) {
            send(messageContextWriteLogRecords, zIsPushable, 1);
        }
    }

    private void reportUnPublishedError(LogRecord logRecord) {
        String string;
        Object obj = MUTEX_PUBLISH;
        ThreadLocal threadLocal = MUTEX;
        if (obj.equals(threadLocal.get())) {
            threadLocal.set(MUTEX_REPORT);
            if (logRecord != null) {
                try {
                    SimpleFormatter simpleFormatter = new SimpleFormatter();
                    String stringBuffer = "Log record " +
                            logRecord.getSequenceNumber() +
                            " was not published. " +
                            head(simpleFormatter) +
                            format(simpleFormatter, logRecord) +
                            tail(simpleFormatter, "");
                    string = stringBuffer;
                } catch (Throwable th) {
                    MUTEX.set(MUTEX_PUBLISH);
                    throw th;
                }
            } else {
                string = null;
            }
            String stringBuffer2 = "Recursive publish detected by thread " +
                    Thread.currentThread();
            reportError(string, new IllegalStateException(stringBuffer2), 1);
            threadLocal.set(obj);
        }
    }

    private boolean tryMutex() {
        ThreadLocal threadLocal = MUTEX;
        if (threadLocal.get() != null) {
            return false;
        }
        threadLocal.set(MUTEX_PUBLISH);
        return true;
    }

    private void releaseMutex() {
        Method method = REMOVE;
        if (method != null) {
            try {
                method.invoke(MUTEX, null);
                return;
            } catch (RuntimeException unused) {
                MUTEX.set(null);
                return;
            } catch (Exception unused2) {
                MUTEX.set(null);
                return;
            }
        }
        MUTEX.set(null);
    }

    public void push() {
        push(true, 2);
    }

    public void flush() {
        push(false, 2);
    }

    public void close() {
        MessageContext messageContextWriteLogRecords;
        Object andSetContextClassLoader = getAndSetContextClassLoader();
        try {
            synchronized (this) {
                try {
                    super.setLevel(Level.OFF);
                    try {
                        messageContextWriteLogRecords = writeLogRecords(3);
                        int i2 = this.capacity;
                        if (i2 > 0) {
                            this.capacity = -i2;
                        }
                        if (this.size == 0 && this.data.length != 1) {
                            this.data = new LogRecord[1];
                        }
                    } catch (Throwable th) {
                        if (this.capacity > 0) {
                            this.capacity = -this.capacity;
                        }
                        if (this.size == 0 && this.data.length != 1) {
                            this.data = new LogRecord[1];
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            if (messageContextWriteLogRecords != null) {
                try {
                    send(messageContextWriteLogRecords, false, 3);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        } finally {
            setContextClassLoader(andSetContextClassLoader);
        }
    }

    public synchronized void setLevel(Level level) {
        try {
            if (this.capacity > 0) {
                super.setLevel(level);
            } else {
                if (level == null) {
                    throw new NullPointerException();
                }
                checkAccess();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized Level getPushLevel() {
        return this.pushLevel;
    }

    public final synchronized void setPushLevel(Level level) {
        checkAccess();
        if (level == null) {
            throw new NullPointerException();
        }
        if (this.isWriting) {
            throw new IllegalStateException();
        }
        this.pushLevel = level;
    }

    public final synchronized Filter getPushFilter() {
        return this.pushFilter;
    }

    public final synchronized void setPushFilter(Filter filter) {
        checkAccess();
        if (this.isWriting) {
            throw new IllegalStateException();
        }
        this.pushFilter = filter;
    }

    public final synchronized Comparator getComparator() {
        return this.comparator;
    }

    public final synchronized void setComparator(Comparator comparator) {
        checkAccess();
        if (this.isWriting) {
            throw new IllegalStateException();
        }
        this.comparator = comparator;
    }

    public final synchronized int getCapacity() {
        int i2;
        try {
            if (!assertionsDisabled && ((i2 = this.capacity) == Integer.MIN_VALUE || i2 == 0)) {
                throw new AssertionError(this.capacity);
            }
        } catch (Throwable th) {
            throw th;
        }
        return Math.abs(this.capacity);
    }

    public final synchronized Authenticator getAuthenticator() {
        checkAccess();
        return this.auth;
    }

    public final void setAuthenticator(Authenticator authenticator) throws Exception {
        setAuthenticator0(authenticator);
    }

    public final void setAuthenticator(char[] cArr) throws Exception {
        if (cArr == null) {
            setAuthenticator0(null);
        } else {
            setAuthenticator0(new DefaultAuthenticator(new String(cArr)));
        }
    }

    private void setAuthenticator0(Authenticator authenticator) throws Exception {
        Session sessionFixUpSession;
        checkAccess();
        synchronized (this) {
            if (this.isWriting) {
                throw new IllegalStateException();
            }
            this.auth = authenticator;
            sessionFixUpSession = fixUpSession();
        }
        verifySettings(sessionFixUpSession);
    }

    public final void setMailProperties(Properties properties) throws Exception {
        setMailProperties0(properties);
    }

    private void setMailProperties0(Properties properties) throws Exception {
        Session sessionFixUpSession;
        checkAccess();
        Properties properties2 = (Properties) properties.clone();
        synchronized (this) {
            if (this.isWriting) {
                throw new IllegalStateException();
            }
            this.mailProps = properties2;
            sessionFixUpSession = fixUpSession();
        }
        verifySettings(sessionFixUpSession);
    }

    public final Properties getMailProperties() {
        Properties properties;
        checkAccess();
        synchronized (this) {
            properties = this.mailProps;
        }
        return (Properties) properties.clone();
    }

    public final Filter[] getAttachmentFilters() {
        return readOnlyAttachmentFilters().clone();
    }

    public final void setAttachmentFilters(Filter[] filterArr) throws Throwable {
        checkAccess();
        int length = filterArr.length;
        Class clsClass= arrayLjavautilloggingFilter;
        if (clsClass == null) {
            clsClass = ("[Ljava.util.logging.Filter;").getClass();
            arrayLjavautilloggingFilter = clsClass;
        }
        Filter[] filterArr2 = (Filter[]) copyOf(filterArr, length, clsClass);
        synchronized (this) {
            try {
                Formatter[] formatterArr = this.attachmentFormatters;
                if (formatterArr.length != filterArr2.length) {
                    throw attachmentMismatch(formatterArr.length, filterArr2.length);
                }
                if (this.isWriting) {
                    throw new IllegalStateException();
                }
                this.attachmentFilters = filterArr2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final Formatter[] getAttachmentFormatters() {
        Formatter[] formatterArr;
        synchronized (this) {
            formatterArr = this.attachmentFormatters;
        }
        return formatterArr.clone();
    }

    public final void setAttachmentFormatters(Formatter[] formatterArr) throws Throwable {
        Formatter[] formatterArrEmptyFormatterArray;
        checkAccess();
        if (formatterArr.length == 0) {
            formatterArrEmptyFormatterArray = emptyFormatterArray();
        } else {
            int length = formatterArr.length;
            Class clsClass = arrayLjavautilloggingFormatter;
            if (clsClass == null) {
                clsClass =  ("[Ljava.util.logging.Formatter;").getClass();
                arrayLjavautilloggingFormatter = clsClass;
            }
            formatterArrEmptyFormatterArray = (Formatter[]) copyOf(formatterArr, length, clsClass);
            for (int i2 = 0; i2 < formatterArrEmptyFormatterArray.length; i2++) {
                if (formatterArrEmptyFormatterArray[i2] == null) {
                    throw new NullPointerException(atIndexMsg(i2));
                }
            }
        }
        synchronized (this) {
            try {
                if (this.isWriting) {
                    throw new IllegalStateException();
                }
                this.attachmentFormatters = formatterArrEmptyFormatterArray;
                fixUpAttachmentFilters();
                fixUpAttachmentNames();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final Formatter[] getAttachmentNames() {
        Formatter[] formatterArr;
        synchronized (this) {
            formatterArr = this.attachmentNames;
        }
        return formatterArr.clone();
    }

    public final void setAttachmentNames(String[] strArr) {
        Formatter[] formatterArrEmptyFormatterArray;
        checkAccess();
        if (strArr.length == 0) {
            formatterArrEmptyFormatterArray = emptyFormatterArray();
        } else {
            formatterArrEmptyFormatterArray = new Formatter[strArr.length];
        }
        for (int i2 = 0; i2 < strArr.length; i2++) {
            String str = strArr[i2];
            if (str != null) {
                if (str.length() > 0) {
                    formatterArrEmptyFormatterArray[i2] = new TailNameFormatter(str);
                } else {
                    throw new IllegalArgumentException(atIndexMsg(i2));
                }
            } else {
                throw new NullPointerException(atIndexMsg(i2));
            }
        }
        synchronized (this) {
            try {
                Formatter[] formatterArr = this.attachmentFormatters;
                if (formatterArr.length != strArr.length) {
                    throw attachmentMismatch(formatterArr.length, strArr.length);
                }
                if (this.isWriting) {
                    throw new IllegalStateException();
                }
                this.attachmentNames = formatterArrEmptyFormatterArray;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void setAttachmentNames(Formatter[] formatterArr) throws Throwable {
        checkAccess();
        int length = formatterArr.length;
        Class clsClass = arrayLjavautilloggingFormatter;
        if (clsClass == null) {
            clsClass = ("[Ljava.util.logging.Formatter;").getClass();
            arrayLjavautilloggingFormatter = clsClass;
        }
        Formatter[] formatterArr2 = (Formatter[]) copyOf(formatterArr, length, clsClass);
        for (int i2 = 0; i2 < formatterArr2.length; i2++) {
            if (formatterArr2[i2] == null) {
                throw new NullPointerException(atIndexMsg(i2));
            }
        }
        synchronized (this) {
            try {
                Formatter[] formatterArr3 = this.attachmentFormatters;
                if (formatterArr3.length != formatterArr2.length) {
                    throw attachmentMismatch(formatterArr3.length, formatterArr2.length);
                }
                if (this.isWriting) {
                    throw new IllegalStateException();
                }
                this.attachmentNames = formatterArr2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final synchronized Formatter getSubject() {
        return this.subjectFormatter;
    }

    public final void setSubject(String str) {
        if (str != null) {
            setSubject(new TailNameFormatter(str));
        } else {
            checkAccess();
            throw null;
        }
    }

    public final void setSubject(Formatter formatter) {
        checkAccess();
        formatter.getClass();
        synchronized (this) {
            try {
                if (this.isWriting) {
                    throw new IllegalStateException();
                }
                this.subjectFormatter = formatter;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    protected void reportError(String str, Exception exc, int i2) {
        if (str != null) {
            String stringBuffer = Level.SEVERE.getName() +
                    ": " +
                    str;
            super.reportError(stringBuffer, exc, i2);
            return;
        }
        super.reportError(null, exc, i2);
    }

    final void checkAccess() {
        if (this.sealed) {
            LogManagerProperties.getLogManager().checkAccess();
        }
    }

    final String contentTypeOf(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        if (str.length() > 25) {
            str = str.substring(0, 25);
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes(getEncodingName()));
            if (!assertionsDisabled && !byteArrayInputStream.markSupported()) {
                throw new AssertionError(byteArrayInputStream.getClass().getName());
            }
            return URLConnection.guessContentTypeFromStream(byteArrayInputStream);
        } catch (IOException e2) {
            reportError(e2.getMessage(), e2, 5);
            return null;
        }
    }

    final boolean isMissingContent(Message message, Throwable th) {
        Throwable cause = th.getCause();
        while (true) {
            Throwable th2 = th;
            th = cause;
            if (th != null) {
                cause = th.getCause();
            } else {
                try {
                    message.writeTo(new ByteArrayOutputStream(1024));
                    return false;
                } catch (RuntimeException e2) {
                    throw e2;
                } catch (Exception e3) {
                    String message2 = e3.getMessage();
                    if (isEmpty(message2) || e3.getClass() != th2.getClass()) {
                        return false;
                    }
                    return message2.equals(th2.getMessage());
                }
            }
        }
    }

    private void reportError(Message message, Exception exc, int i2) {
        try {
            super.reportError(toRawString(message), exc, i2);
        } catch (IOException e2) {
            reportError(toMsgString(e2), exc, i2);
        } catch (MessagingException e3) {
            reportError(toMsgString(e3), exc, i2);
        }
    }

    private String getContentType(String str) {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        String contentType = this.contentTypes.getContentType(str);
        if ("application/octet-stream".equalsIgnoreCase(contentType)) {
            return null;
        }
        return contentType;
    }

    private String getEncodingName() {
        String encoding = getEncoding();
        return encoding == null ? MimeUtility.getDefaultJavaCharset() : encoding;
    }

    private void setContent(MimeBodyPart mimeBodyPart, CharSequence charSequence, String str) throws MessagingException {
        String encodingName = getEncodingName();
        if (str != null && !"text/plain".equalsIgnoreCase(str)) {
            try {
                mimeBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(charSequence.toString(), contentWithEncoding(str, encodingName))));
                return;
            } catch (IOException e2) {
                reportError(e2.getMessage(), e2, 5);
                mimeBodyPart.setText(charSequence.toString(), encodingName);
                return;
            }
        }
        mimeBodyPart.setText(charSequence.toString(), MimeUtility.mimeCharset(encodingName));
    }

    private String contentWithEncoding(String str, String str2) {
        if (!assertionsDisabled && str2 == null) {
            throw new AssertionError();
        }
        try {
            ContentType contentType = new ContentType(str);
            contentType.setParameter("charset", MimeUtility.mimeCharset(str2));
            String string = contentType.toString();
            return !isEmpty(string) ? string : str;
        } catch (MessagingException e2) {
            this.reportError(str, e2, 5);
            return str;
        }
    }

    private synchronized void setCapacity0(int i2) {
        try {
            if (i2 <= 0) {
                throw new IllegalArgumentException("Capacity must be greater than zero.");
            }
            if (this.isWriting) {
                throw new IllegalStateException();
            }
            if (this.capacity < 0) {
                this.capacity = -i2;
            } else {
                this.capacity = i2;
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private synchronized Filter[] readOnlyAttachmentFilters() {
        return this.attachmentFilters;
    }

    private static Formatter[] emptyFormatterArray() {
        return EMPTY_FORMATTERS;
    }

    private static Filter[] emptyFilterArray() {
        return EMPTY_FILTERS;
    }

    private boolean fixUpAttachmentNames() {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        int length = this.attachmentFormatters.length;
        Filter[] filterArr = this.attachmentFilters;
        int length2 = filterArr.length;
        boolean z2 = false;
        if (length2 != length) {
            this.attachmentFilters = (Filter[]) copyOf(filterArr, length);
            if (length2 != 0) {
                z2 = true;
            }
        }
        if (length == 0) {
            Filter[] filterArrEmptyFilterArray = emptyFilterArray();
            this.attachmentFilters = filterArrEmptyFilterArray;
            if (!z && filterArrEmptyFilterArray.length != 0) {
                throw new AssertionError();
            }
        }
        return z2;
    }
    private boolean fixUpAttachmentFilters() {
        boolean z = assertionsDisabled;
        if (!z && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        int length = this.attachmentFormatters.length;
        Filter[] filterArr = this.attachmentFilters;
        int length2 = filterArr.length;
        boolean z2 = false;
        if (length2 != length) {
            this.attachmentFilters = (Filter[]) copyOf(filterArr, length);
            if (length2 != 0) {
                z2 = true;
            }
        }
        if (length == 0) {
            Filter[] filterArrEmptyFilterArray = emptyFilterArray();
            this.attachmentFilters = filterArrEmptyFilterArray;
            if (!z && filterArrEmptyFilterArray.length != 0) {
                throw new AssertionError();
            }
        }
        return z2;
    }
    private static Object[] copyOf(Object[] objArr, int i2) {
        Object[] objArr2 = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i2);
        System.arraycopy(objArr, 0, objArr2, 0, Math.min(objArr.length, i2));
        return objArr2;
    }

    private static Object[] copyOf(Object[] objArr, int i2, Class cls) {
        if (cls == objArr.getClass()) {
            return objArr.clone();
        }
        Object[] objArr2 = (Object[]) Array.newInstance(cls.getComponentType(), i2);
        System.arraycopy(objArr, 0, objArr2, 0, Math.min(i2, objArr.length));
        return objArr2;
    }

    private void reset() {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        int i2 = this.size;
        LogRecord[] logRecordArr = this.data;
        if (i2 < logRecordArr.length) {
            Arrays.fill(logRecordArr, 0, i2, null);
        } else {
            Arrays.fill(logRecordArr, null);
        }
        this.size = 0;
    }

    private void grow() {
        boolean z = assertionsDisabled;
        if (!z && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        LogRecord[] logRecordArr = this.data;
        int length = logRecordArr.length;
        int i2 = (length >> 1) + length + 1;
        int i3 = this.capacity;
        if (i2 > i3 || i2 < length) {
            i2 = i3;
        }
        if (!z && length == i3) {
            throw new AssertionError(length);
        }
        this.data = (LogRecord[]) copyOf(logRecordArr, i2);
    }

    private synchronized void init(boolean z) {
        LogManager logManager = LogManagerProperties.getLogManager();
        String name = getClass().getName();
        this.mailProps = new Properties();
        this.contentTypes = FileTypeMap.getDefaultFileTypeMap();
        initErrorManager(logManager, name);
        initLevel(logManager, name);
        initFilter(logManager, name);
        initCapacity(logManager, name);
        initAuthenticator(logManager, name);
        initEncoding(logManager, name);
        initFormatter(logManager, name);
        initComparator(logManager, name);
        initPushLevel(logManager, name);
        initPushFilter(logManager, name);
        initSubject(logManager, name);
        initAttachmentFormaters(logManager, name);
        initAttachmentFilters(logManager, name);
        initAttachmentNames(logManager, name);
        if (z && logManager.getProperty(name.concat(".verify")) != null) {
            try {
                verifySettings(initSession());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    private static boolean hasValue(String str) {
        return !isEmpty(str) && !"null".equalsIgnoreCase(str);
    }

    private void initAttachmentFilters(LogManager logManager, String str) {
        boolean z = assertionsDisabled;
        if (!z && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        if (!z && this.attachmentFormatters == null) {
            throw new AssertionError();
        }
        String property = logManager.getProperty(str.concat(".attachment.filters"));
        if (property != null && property.length() > 0) {
            String[] strArrSplit = property.split(",");
            int length = strArrSplit.length;
            Filter[] filterArr = new Filter[length];
            for (int i2 = 0; i2 < length; i2++) {
                String strTrim = strArrSplit[i2].trim();
                strArrSplit[i2] = strTrim;
                if (!"null".equalsIgnoreCase(strTrim)) {
                    try {
                        filterArr[i2] = LogManagerProperties.newFilter(strArrSplit[i2]);
                    } catch (SecurityException e2) {
                        throw e2;
                    } catch (Exception e3) {
                        reportError(e3.getMessage(), e3, 4);
                    }
                }
            }
            this.attachmentFilters = filterArr;
            if (fixUpAttachmentFilters()) {
                reportError("Attachment filters.", attachmentMismatch("Length mismatch."), 4);
                return;
            }
            return;
        }
        this.attachmentFilters = emptyFilterArray();
        fixUpAttachmentFilters();
    }

    private void initAttachmentFormaters(LogManager logManager, String str) {
        Formatter[] formatterArrEmptyFormatterArray;
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        String property = logManager.getProperty(str.concat(".attachment.formatters"));
        if (property != null && property.length() > 0) {
            String[] strArrSplit = property.split(",");
            if (strArrSplit.length == 0) {
                formatterArrEmptyFormatterArray = emptyFormatterArray();
            } else {
                formatterArrEmptyFormatterArray = new Formatter[strArrSplit.length];
            }
            for (int i2 = 0; i2 < formatterArrEmptyFormatterArray.length; i2++) {
                String strTrim = strArrSplit[i2].trim();
                strArrSplit[i2] = strTrim;
                if (!"null".equalsIgnoreCase(strTrim)) {
                    try {
                        Formatter formatterNewFormatter = LogManagerProperties.newFormatter(strArrSplit[i2]);
                        formatterArrEmptyFormatterArray[i2] = formatterNewFormatter;
                        if (formatterNewFormatter instanceof TailNameFormatter) {
                            formatterArrEmptyFormatterArray[i2] = new SimpleFormatter();
                            reportError("Attachment formatter.", new ClassNotFoundException(formatterArrEmptyFormatterArray[i2].toString()), 4);
                        }
                    } catch (SecurityException e2) {
                        throw e2;
                    } catch (Exception e3) {
                        formatterArrEmptyFormatterArray[i2] = new SimpleFormatter();
                        reportError(e3.getMessage(), e3, 4);
                    }
                } else {
                    formatterArrEmptyFormatterArray[i2] = new SimpleFormatter();
                    reportError("Attachment formatter.", new NullPointerException(atIndexMsg(i2)), 4);
                }
            }
            this.attachmentFormatters = formatterArrEmptyFormatterArray;
            return;
        }
        this.attachmentFormatters = emptyFormatterArray();
    }

    private void initAttachmentNames(LogManager logManager, String str) {
        boolean z = assertionsDisabled;
        if (!z && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        if (!z && this.attachmentFormatters == null) {
            throw new AssertionError();
        }
        String property = logManager.getProperty(str.concat(".attachment.names"));
        if (property != null && property.length() > 0) {
            String[] strArrSplit = property.split(",");
            int length = strArrSplit.length;
            Formatter[] formatterArr = new Formatter[length];
            for (int i2 = 0; i2 < length; i2++) {
                String strTrim = strArrSplit[i2].trim();
                strArrSplit[i2] = strTrim;
                if (!"null".equalsIgnoreCase(strTrim)) {
                    try {
                        try {
                            formatterArr[i2] = LogManagerProperties.newFormatter(strArrSplit[i2]);
                        } catch (ClassCastException unused) {
                            formatterArr[i2] = new TailNameFormatter(strArrSplit[i2]);
                        } catch (ClassNotFoundException unused2) {
                            formatterArr[i2] = new TailNameFormatter(strArrSplit[i2]);
                        }
                    } catch (SecurityException e2) {
                        throw e2;
                    } catch (Exception e3) {
                        reportError(e3.getMessage(), e3, 4);
                    }
                } else {
                    reportError("Attachment names.", new NullPointerException(atIndexMsg(i2)), 4);
                }
            }
            this.attachmentNames = formatterArr;
            if (fixUpAttachmentNames()) {
                reportError("Attachment names.", attachmentMismatch("Length mismatch."), 4);
                return;
            }
            return;
        }
        this.attachmentNames = emptyFormatterArray();
        fixUpAttachmentNames();
    }

    private void initAuthenticator(LogManager logManager, String str) {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        String property = logManager.getProperty(str.concat(".authenticator"));
        if (hasValue(property)) {
            try {
                this.auth = LogManagerProperties.newAuthenticator(property);
            } catch (ClassCastException unused) {
                this.auth = new DefaultAuthenticator(property);
            } catch (ClassNotFoundException unused2) {
                this.auth = new DefaultAuthenticator(property);
            } catch (SecurityException e2) {
                throw e2;
            } catch (Exception e3) {
                reportError(e3.getMessage(), e3, 4);
            }
        }
    }

    private void initLevel(LogManager logManager, String str) {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        try {
            String property = logManager.getProperty(str.concat(".level"));
            if (property != null) {
                super.setLevel(Level.parse(property));
            } else {
                super.setLevel(Level.WARNING);
            }
        } catch (SecurityException e2) {
            throw e2;
        } catch (RuntimeException e3) {
            reportError(e3.getMessage(), e3, 4);
            try {
                super.setLevel(Level.WARNING);
            } catch (RuntimeException e4) {
                reportError(e4.getMessage(), e4, 4);
            }
        }
    }

    private void initFilter(LogManager logManager, String str) {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        try {
            String property = logManager.getProperty(str.concat(".filter"));
            if (hasValue(property)) {
                super.setFilter(LogManagerProperties.newFilter(property));
            }
        } catch (SecurityException e2) {
            throw e2;
        } catch (Exception e3) {
            reportError(e3.getMessage(), e3, 4);
        }
    }

    private void initCapacity(LogManager logManager, String str) {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        try {
            String property = logManager.getProperty(str.concat(".capacity"));
            if (property != null) {
                setCapacity0(Integer.parseInt(property));
            } else {
                setCapacity0(1000);
            }
        } catch (RuntimeException e2) {
            reportError(e2.getMessage(), e2, 4);
        }
        if (this.capacity <= 0) {
            this.capacity = 1000;
        }
        this.data = new LogRecord[1];
    }

    private void initEncoding(LogManager logManager, String str) {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        try {
            super.setEncoding(logManager.getProperty(str.concat(".encoding")));
        } catch (UnsupportedEncodingException e2) {
            reportError(e2.getMessage(), e2, 4);
        } catch (SecurityException e3) {
            throw e3;
        } catch (RuntimeException e4) {
            reportError(e4.getMessage(), e4, 4);
        }
    }

    private void initErrorManager(LogManager logManager, String str) {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        String property = logManager.getProperty(str.concat(".errorManager"));
        if (property != null) {
            try {
                super.setErrorManager(LogManagerProperties.newErrorManager(property));
            } catch (SecurityException e2) {
                throw e2;
            } catch (Exception e3) {
                reportError(e3.getMessage(), e3, 4);
            }
        }
    }

    private void initFormatter(LogManager logManager, String str) {
        boolean z = assertionsDisabled;
        if (!z && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        String property = logManager.getProperty(str.concat(".formatter"));
        if (hasValue(property)) {
            try {
                Formatter formatterNewFormatter = LogManagerProperties.newFormatter(property);
                if (!z && formatterNewFormatter == null) {
                    throw new AssertionError();
                }
                if (!(formatterNewFormatter instanceof TailNameFormatter)) {
                    super.setFormatter(formatterNewFormatter);
                    return;
                } else {
                    super.setFormatter(new SimpleFormatter());
                    return;
                }
            } catch (SecurityException e2) {
                throw e2;
            } catch (Exception e3) {
                reportError(e3.getMessage(), e3, 4);
                try {
                    super.setFormatter(new SimpleFormatter());
                    return;
                } catch (RuntimeException e4) {
                    reportError(e4.getMessage(), e4, 4);
                    return;
                }
            }
        }
        super.setFormatter(new SimpleFormatter());
    }

    private void initComparator(LogManager logManager, String str) {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        String property = logManager.getProperty(str.concat(".comparator"));
        if (hasValue(property)) {
            try {
                this.comparator = LogManagerProperties.newComparator(property);
            } catch (SecurityException e2) {
                throw e2;
            } catch (Exception e3) {
                reportError(e3.getMessage(), e3, 4);
            }
        }
    }

    private void initPushLevel(LogManager logManager, String str) {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        try {
            String property = logManager.getProperty(str.concat(".pushLevel"));
            if (property != null) {
                this.pushLevel = Level.parse(property);
            }
        } catch (RuntimeException e2) {
            reportError(e2.getMessage(), e2, 4);
        }
        if (this.pushLevel == null) {
            this.pushLevel = Level.OFF;
        }
    }

    private void initPushFilter(LogManager logManager, String str) {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        String property = logManager.getProperty(str.concat(".pushFilter"));
        if (hasValue(property)) {
            try {
                this.pushFilter = LogManagerProperties.newFilter(property);
            } catch (SecurityException e2) {
                throw e2;
            } catch (Exception e3) {
                reportError(e3.getMessage(), e3, 4);
            }
        }
    }

    private void initSubject(LogManager logManager, String str) {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        String property = logManager.getProperty(str.concat(".subject"));
        if (hasValue(property)) {
            try {
                this.subjectFormatter = LogManagerProperties.newFormatter(property);
            } catch (ClassCastException unused) {
                this.subjectFormatter = new TailNameFormatter(property);
            } catch (ClassNotFoundException unused2) {
                this.subjectFormatter = new TailNameFormatter(property);
            } catch (SecurityException e2) {
                throw e2;
            } catch (Exception e3) {
                this.subjectFormatter = new TailNameFormatter(property);
                reportError(e3.getMessage(), e3, 4);
            }
        }
        if (this.subjectFormatter == null) {
            this.subjectFormatter = new TailNameFormatter("");
        }
    }

    private boolean isAttachmentLoggable(LogRecord logRecord) {
        for (Filter filter : readOnlyAttachmentFilters()) {
            if (filter == null || filter.isLoggable(logRecord)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPushable(LogRecord logRecord) {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        int iIntValue = getPushLevel().intValue();
        if (iIntValue == offValue || logRecord.getLevel().intValue() < iIntValue) {
            return false;
        }
        Filter pushFilter = getPushFilter();
        return pushFilter == null || pushFilter.isLoggable(logRecord);
    }

    private void push(boolean z, int i2) {
        if (tryMutex()) {
            try {
                MessageContext messageContextWriteLogRecords = writeLogRecords(i2);
                if (messageContextWriteLogRecords != null) {
                    try {
                        send(messageContextWriteLogRecords, z, i2);
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                }
                return;
            } finally {
                releaseMutex();
            }
        }
        reportUnPublishedError(null);
    }

    private void send(MessageContext messageContext, boolean z, int i2) throws Throwable {
        Message message = messageContext.getMessage();
        try {
            envelopeFor(messageContext, z);
            Transport.send(message);
        } catch (Exception e2) {
            reportError(message, e2, i2);
        }
    }

    private void sort() {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        Comparator comparator = this.comparator;
        if (comparator != null) {
            try {
                int i2 = this.size;
                if (i2 != 1) {
                    Arrays.sort(this.data, 0, i2, comparator);
                } else {
                    LogRecord logRecord = this.data[0];
                    comparator.compare(logRecord, logRecord);
                }
            } catch (RuntimeException e2) {
                reportError(e2.getMessage(), e2, 5);
            }
        }
    }

    private synchronized javax.mail.MessageContext writeLogRecords(int r19) {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.util.logging.MailHandler.writeLogRecords(int):javax.mail.MessageContext");
    }

    private void verifySettings(Session session) throws Exception {
        if (session != null) {
            Object objPut = session.getProperties().put("verify", "");
            if (!(objPut instanceof String)) {
                if (objPut != null) {
                    verifySettings0(session, objPut.getClass().toString());
                }
            } else {
                String str = (String) objPut;
                if (hasValue(str)) {
                    verifySettings0(session, str);
                }
            }
        }
    }

    private void verifySettings0(javax.mail.Session r12, java.lang.String r13) throws java.lang.Exception {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.util.logging.MailHandler.verifySettings0(javax.mail.Session, java.lang.String):void");
    }

    private static void verifyAddresses(Address[] addressArr) throws AddressException {
        if (addressArr != null) {
            for (Address address : addressArr) {
                if (address instanceof InternetAddress) {
                    ((InternetAddress) address).validate();
                }
            }
        }
    }

    private void reportUnexpectedSend(MimeMessage mimeMessage, String str, Exception exc) throws Throwable {
        Exception messagingException = new MessagingException("An empty message was sent.", exc);
        fixUpContent(mimeMessage, str, messagingException);
        reportError(mimeMessage, messagingException, 4);
    }

    private void fixUpContent(MimeMessage mimeMessage, String str, Throwable th) throws Throwable {
        MimeBodyPart mimeBodyPartCreateBodyPart;
        String strDescriptionFrom;
        String classId;
        Class<?> clsClass$;
        try {
            synchronized (this) {
                mimeBodyPartCreateBodyPart = createBodyPart();
                strDescriptionFrom = descriptionFrom(this.comparator, this.pushLevel, this.pushFilter);
                classId = getClassId(this.subjectFormatter);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Formatted using ");
            if (th == null) {
                clsClass$ = classjavalangThrowable;
                if (clsClass$ == null) {
                    clsClass$ =  ("java.lang.Throwable").getClass();
                    classjavalangThrowable = clsClass$;
                }
            } else {
                clsClass$ = th.getClass();
            }
            stringBuffer.append(clsClass$.getName());
            stringBuffer.append(", filtered with ");
            stringBuffer.append(str);
            stringBuffer.append(", and named by ");
            stringBuffer.append(classId);
            stringBuffer.append('.');
            mimeBodyPartCreateBodyPart.setDescription(stringBuffer.toString());
            setContent(mimeBodyPartCreateBodyPart, toMsgString(th), "text/plain");
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(mimeBodyPartCreateBodyPart);
            mimeMessage.setContent(mimeMultipart);
            mimeMessage.setDescription(strDescriptionFrom);
            setAcceptLang(mimeMessage);
            mimeMessage.saveChanges();
        } catch (RuntimeException e2) {
            reportError("Unable to create body.", e2, 4);
        } catch (MessagingException e3) {
            reportError("Unable to create body.", e3, 4);
        }
    }

    private Session fixUpSession() {
        boolean z = assertionsDisabled;
        if (!z && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        if (this.mailProps.getProperty("verify") != null) {
            Session sessionInitSession = initSession();
            if (z || sessionInitSession == this.session) {
                return sessionInitSession;
            }
            throw new AssertionError();
        }
        this.session = null;
        return null;
    }

    private Session initSession() {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        Session session = Session.getInstance(new LogManagerProperties(this.mailProps, getClass().getName()), this.auth);
        this.session = session;
        return session;
    }

    private void envelopeFor(MessageContext messageContext, boolean z) throws Throwable {
        Message message = messageContext.getMessage();
        Properties properties = messageContext.getSession().getProperties();
        setAcceptLang(message);
        setFrom(message, properties);
        setRecipient(message, properties, "mail.to", Message.RecipientType.TO);
        setRecipient(message, properties, "mail.cc", Message.RecipientType.CC);
        setRecipient(message, properties, "mail.bcc", Message.RecipientType.BCC);
        setReplyTo(message, properties);
        setSender(message, properties);
        setMailer(message);
        setAutoSubmitted(message);
        if (z) {
            setPriority(message);
        }
        try {
            message.setSentDate(new Date());
        } catch (MessagingException e2) {
            reportError(e2.getMessage(), e2, 5);
        }
    }

    private MimeBodyPart createBodyPart() throws MessagingException {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setDisposition(Part.INLINE);
        mimeBodyPart.setDescription(descriptionFrom(getFormatter(), getFilter(), this.subjectFormatter));
        setAcceptLang(mimeBodyPart);
        return mimeBodyPart;
    }

    private MimeBodyPart createBodyPart(int i2) throws MessagingException {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setDisposition(Part.ATTACHMENT);
        mimeBodyPart.setDescription(descriptionFrom(this.attachmentFormatters[i2], this.attachmentFilters[i2], this.attachmentNames[i2]));
        setAcceptLang(mimeBodyPart);
        return mimeBodyPart;
    }

    private String descriptionFrom(Comparator comparator, Level level, Filter filter) {
        String stringBuffer = "Sorted using " +
                (comparator == null ? "no comparator" : comparator.getClass().getName()) +
                ", pushed when " +
                level.getName() +
                ", and " +
                (filter == null ? "no push filter" : filter.getClass().getName()) +
                '.';
        return stringBuffer;
    }

    private String descriptionFrom(Formatter formatter, Filter filter, Formatter formatter2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Formatted using ");
        try {
            stringBuffer.append(getClassId(formatter));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        stringBuffer.append(", filtered with ");
        stringBuffer.append(filter == null ? "no filter" : filter.getClass().getName());
        stringBuffer.append(", and named by ");
        try {
            stringBuffer.append(getClassId(formatter2));
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        stringBuffer.append('.');
        return stringBuffer.toString();
    }

    private String getClassId(Formatter formatter) throws Throwable {
        if (formatter instanceof TailNameFormatter) {
            Class clsClass = classjavalangString;
            if (clsClass == null) {
                clsClass = ("java.lang.String").getClass();
                classjavalangString = clsClass;
            }
            return clsClass.getName();
        }
        return formatter.getClass().getName();
    }

    private String toString(Formatter formatter) {
        String string = formatter.toString();
        try {
            return !isEmpty(string) ? string : getClassId(formatter);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private void appendFileName(Part part, String str) {
        if (str != null) {
            if (str.length() > 0) {
                appendFileName0(part, str);
                return;
            }
            return;
        }
        reportNullError(5);
    }

    private void appendFileName0(Part part, String str) {
        try {
            String fileName = part.getFileName();
            if (fileName != null) {
                str = fileName.concat(str);
            }
            part.setFileName(str);
        } catch (MessagingException e2) {
            reportError(e2.getMessage(), e2, 5);
        }
    }

    private void appendSubject(Message message, String str) {
        if (str != null) {
            if (str.length() > 0) {
                appendSubject0(message, str);
                return;
            }
            return;
        }
        reportNullError(5);
    }

    private void appendSubject0(Message message, String str) {
        try {
            String encodingName = getEncodingName();
            String subject = message.getSubject();
            if (!assertionsDisabled && !(message instanceof MimeMessage)) {
                throw new AssertionError();
            }
            MimeMessage mimeMessage = (MimeMessage) message;
            if (subject != null) {
                str = subject.concat(str);
            }
            mimeMessage.setSubject(str, MimeUtility.mimeCharset(encodingName));
        } catch (MessagingException e2) {
            reportError(e2.getMessage(), e2, 5);
        }
    }

    private Locale localeFor(LogRecord logRecord) {
        ResourceBundle resourceBundle = logRecord.getResourceBundle();
        if (resourceBundle == null) {
            return null;
        }
        Locale locale = resourceBundle.getLocale();
        return (locale == null || isEmpty(locale.getLanguage())) ? Locale.getDefault() : locale;
    }

    private void appendContentLang(MimePart mimePart, Locale locale) {
        int length;
        String strConcat;
        try {
            String languageTag = LogManagerProperties.toLanguageTag(locale);
            if (languageTag.length() != 0) {
                String header = mimePart.getHeader(HttpHeaders.CONTENT_LANGUAGE, null);
                if (isEmpty(header)) {
                    mimePart.setHeader(HttpHeaders.CONTENT_LANGUAGE, languageTag);
                    return;
                }
                if (header.equalsIgnoreCase(languageTag)) {
                    return;
                }
                String strConcat2 = ",".concat(languageTag);
                int iIndexOf = 0;
                do {
                    iIndexOf = header.indexOf(strConcat2, iIndexOf);
                    if (iIndexOf <= -1 || (iIndexOf = iIndexOf + strConcat2.length()) == header.length()) {
                        break;
                    }
                } while (header.charAt(iIndexOf) != ',');
                if (iIndexOf < 0) {
                    int iLastIndexOf = header.lastIndexOf("\r\n\t");
                    if (iLastIndexOf < 0) {
                        length = header.length() + 20;
                    } else {
                        length = (header.length() - iLastIndexOf) + 8;
                    }
                    if (length + strConcat2.length() > 76) {
                        strConcat = header.concat("\r\n\t".concat(strConcat2));
                    } else {
                        strConcat = header.concat(strConcat2);
                    }
                    mimePart.setHeader(HttpHeaders.CONTENT_LANGUAGE, strConcat);
                }
            }
        } catch (MessagingException e2) {
            reportError(e2.getMessage(), e2, 5);
        }
    }

    private void setAcceptLang(Part part) {
        try {
            String languageTag = LogManagerProperties.toLanguageTag(Locale.getDefault());
            if (languageTag.length() != 0) {
                part.setHeader(HttpHeaders.ACCEPT_LANGUAGE, languageTag);
            }
        } catch (MessagingException e2) {
            reportError(e2.getMessage(), e2, 5);
        }
    }

    private void reportFilterError(LogRecord logRecord) {
        if (!assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        String string = "Log record " +
                logRecord.getSequenceNumber() +
                " was filtered from all message parts.  " +
                head(simpleFormatter) +
                format(simpleFormatter, logRecord) +
                tail(simpleFormatter, "");
        String stringBuffer2 = getFilter() +
                ", " +
                Arrays.asList(readOnlyAttachmentFilters());
        reportError(string, new IllegalArgumentException(stringBuffer2), 5);
    }

    private void reportNullError(int i2) {
        reportError("null", new NullPointerException(), i2);
    }

    private String head(Formatter formatter) {
        try {
            return formatter.getHead(this);
        } catch (RuntimeException e2) {
            this.reportError(e2.getMessage(), e2, 5);
            return "";
        }
    }

    private String format(Formatter formatter, LogRecord logRecord) {
        try {
            return formatter.format(logRecord);
        } catch (RuntimeException e2) {
            this.reportError(e2.getMessage(), e2, 5);
            return "";
        }
    }

    private String tail(Formatter formatter, String str) {
        try {
            return formatter.getTail(this);
        } catch (RuntimeException e2) {
            this.reportError(e2.getMessage(), e2, 5);
            return str;
        }
    }

    private void setMailer(Message message) throws Throwable {
        String strReplaceAll;
        String strFold;
        try {
            Class<?> clsClass = classcomsunmailutilloggingMailHandler;
            if (clsClass == null) {
                clsClass = ("com.sun.mail.util.logging.MailHandler").getClass();
                classcomsunmailutilloggingMailHandler = clsClass;
            }
            Class<?> cls = getClass();
            if (cls == clsClass) {
                strFold = clsClass.getName();
            } else {
                try {
                    strReplaceAll = MimeUtility.encodeText(cls.getName());
                } catch (UnsupportedEncodingException e2) {
                    reportError(e2.getMessage(), e2, 5);
                    strReplaceAll = cls.getName().replaceAll("[^\\x00-\\x7F]", "\u001a");
                }
                String stringBuffer = clsClass.getName() +
                        " using the " +
                        strReplaceAll +
                        " extension.";
                strFold = MimeUtility.fold(10, stringBuffer);
            }
            message.setHeader("X-Mailer", strFold);
        } catch (MessagingException e3) {
            reportError(e3.getMessage(), e3, 5);
        }
    }

    private void setPriority(Message message) {
        try {
            message.setHeader("Importance", "High");
            message.setHeader("Priority", "urgent");
            message.setHeader("X-Priority", ExifInterface.GPS_MEASUREMENT_2D);
        } catch (MessagingException e2) {
            reportError(e2.getMessage(), e2, 5);
        }
    }

    private void setIncompleteCopy(Message message) {
        try {
            message.setHeader("Incomplete-Copy", "");
        } catch (MessagingException e2) {
            reportError(e2.getMessage(), e2, 5);
        }
    }

    private void setAutoSubmitted(Message message) {
        try {
            message.setHeader("auto-submitted", "auto-generated");
        } catch (MessagingException e2) {
            reportError(e2.getMessage(), e2, 5);
        }
    }

    private void setFrom(Message message, Properties properties) {
        String property = properties.getProperty("mail.from");
        if (property != null && property.length() > 0) {
            try {
                InternetAddress[] internetAddressArr = InternetAddress.parse(property, false);
                if (internetAddressArr == null || internetAddressArr.length == 0) {
                    setDefaultFrom(message);
                } else if (internetAddressArr.length == 1) {
                    message.setFrom(internetAddressArr[0]);
                } else {
                    message.addFrom(internetAddressArr);
                }
                return;
            } catch (MessagingException e2) {
                reportError(e2.getMessage(), e2, 5);
                setDefaultFrom(message);
                return;
            }
        }
        setDefaultFrom(message);
    }

    private void setDefaultFrom(Message message) {
        try {
            message.setFrom();
        } catch (MessagingException e2) {
            reportError(e2.getMessage(), e2, 5);
        }
    }

    private void setReplyTo(Message message, Properties properties) {
        String property = properties.getProperty("mail.reply.to");
        if (property == null || property.length() <= 0) {
            return;
        }
        try {
            InternetAddress[] internetAddressArr = InternetAddress.parse(property, false);
            if (internetAddressArr == null || internetAddressArr.length <= 0) {
                return;
            }
            message.setReplyTo(internetAddressArr);
        } catch (MessagingException e2) {
            reportError(e2.getMessage(), e2, 5);
        }
    }

    private void setSender(Message message, Properties properties) {
        if (!assertionsDisabled && !(message instanceof MimeMessage)) {
            throw new AssertionError(message);
        }
        String property = properties.getProperty("mail.sender");
        if (property == null || property.length() <= 0) {
            return;
        }
        try {
            InternetAddress[] internetAddressArr = InternetAddress.parse(property, false);
            if (internetAddressArr == null || internetAddressArr.length <= 0) {
                return;
            }
            ((MimeMessage) message).setSender(internetAddressArr[0]);
            if (internetAddressArr.length > 1) {
                reportError("Ignoring other senders.", tooManyAddresses(internetAddressArr, 1), 5);
            }
        } catch (MessagingException e2) {
            reportError(e2.getMessage(), e2, 5);
        }
    }

    private static AddressException tooManyAddresses(Address[] addressArr, int i2) {
        return new AddressException(Arrays.asList(addressArr).subList(i2, addressArr.length).toString());
    }

    private void setRecipient(Message message, Properties properties, String str, Message.RecipientType recipientType) {
        String property = properties.getProperty(str);
        if (property == null || property.length() <= 0) {
            return;
        }
        try {
            InternetAddress[] internetAddressArr = InternetAddress.parse(property, false);
            if (internetAddressArr == null || internetAddressArr.length <= 0) {
                return;
            }
            message.setRecipients(recipientType, internetAddressArr);
        } catch (MessagingException e2) {
            reportError(e2.getMessage(), e2, 5);
        }
    }

    private String toRawString(Message message) throws MessagingException, IOException {
        if (message == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(message.getSize() + 1024, 1024));
        message.writeTo(byteArrayOutputStream);
        return byteArrayOutputStream.toString("US-ASCII");
    }

    private String toMsgString(Throwable th) {
        if (th == null) {
            return "null";
        }
        String encodingName = getEncodingName();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream, encodingName));
            printWriter.println(th.getMessage());
            th.printStackTrace(printWriter);
            printWriter.flush();
            printWriter.close();
            return byteArrayOutputStream.toString(encodingName);
        } catch (IOException e2) {
            String stringBuffer = String.valueOf(th) +
                    ' ' +
                    e2;
            return stringBuffer;
        }
    }

    private Object getAndSetContextClassLoader() {
        try {
            return AccessController.doPrivileged(GET_AND_SET_CCL);
        } catch (SecurityException unused) {
            return GET_AND_SET_CCL;
        }
    }

    private void setContextClassLoader(Object obj) {
        if (obj == null || (obj instanceof ClassLoader)) {
            AccessController.doPrivileged(new GetAndSetContext(obj));
        }
    }

    private static RuntimeException attachmentMismatch(String str) {
        return new IndexOutOfBoundsException(str);
    }

    private static RuntimeException attachmentMismatch(int i2, int i3) {
        String stringBuffer = "Attachments mismatched, expected " +
                i2 +
                " but given " +
                i3 +
                '.';
        return attachmentMismatch(stringBuffer);
    }

    private static MessagingException attach(MessagingException messagingException, Exception exc) {
        if (exc != null && !messagingException.setNextException(exc) && (exc instanceof MessagingException)) {
            MessagingException messagingException2 = (MessagingException) exc;
            if (messagingException2.setNextException(messagingException)) {
                return messagingException2;
            }
        }
        return messagingException;
    }

    private static String atIndexMsg(int i2) {
        String stringBuffer = "At index: " +
                i2 +
                '.';
        return stringBuffer;
    }

    private static final class DefaultAuthenticator extends Authenticator {
        static final boolean assertionsDisabled;
        private final String pass;

        static {
            if (MailHandler.classcomsunmailutilloggingMailHandler == null) {
                MailHandler.classcomsunmailutilloggingMailHandler =  ("com.sun.mail.util.logging.MailHandler").getClass();
            }
            assertionsDisabled = true;
        }

        DefaultAuthenticator(String str) {
            if (!assertionsDisabled && str == null) {
                throw new AssertionError();
            }
            try {
                this.pass = str;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        public   PasswordAuthentication getPasswordAuthentication() {
            try {
                return new PasswordAuthentication(getDefaultUserName(), this.pass);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static final class GetAndSetContext implements PrivilegedAction {
        private final Object source;

        GetAndSetContext(Object obj) {
            this.source = obj;
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            ClassLoader classLoader;
            Thread threadCurrentThread = Thread.currentThread();
            ClassLoader contextClassLoader = threadCurrentThread.getContextClassLoader();
            Object obj = this.source;
            if (obj == null) {
                classLoader = null;
            } else if (obj instanceof ClassLoader) {
                classLoader = (ClassLoader) obj;
            } else if (obj instanceof Class) {
                classLoader = ((Class) obj).getClassLoader();
            } else {
                classLoader = obj.getClass().getClassLoader();
            }
            if (contextClassLoader == classLoader) {
                return this;
            }
            threadCurrentThread.setContextClassLoader(classLoader);
            return contextClassLoader;
        }
    }

    private static final class TailNameFormatter extends Formatter {
        static final boolean assertionsDisabled;
        private final String name;

        static {
            if (MailHandler.classcomsunmailutilloggingMailHandler == null) {
                MailHandler.classcomsunmailutilloggingMailHandler = ("com.sun.mail.util.logging.MailHandler").getClass();
            }
            assertionsDisabled = true;
        }

        TailNameFormatter(String str) {
            try {
                if (!assertionsDisabled && str == null) {
                    throw new AssertionError();
                }
                this.name = str;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        } 
        public String format(LogRecord logRecord) {
            try {
                return "";
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        } 
        public String getTail(Handler handler) {
            try {
                return this.name;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public boolean equals(Object obj) {
            try {
                if (obj instanceof TailNameFormatter) {
                    return this.name.equals(((TailNameFormatter) obj).name);
                }
                return false;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public int hashCode() {
            try {
                return TailNameFormatter.class.hashCode() + this.name.hashCode();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }

        public String toString() {
            try {
                return this.name;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
 
}
