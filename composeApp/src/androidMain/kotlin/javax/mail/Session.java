package javax.mail;

import androidx.core.app.NotificationCompat;
import sun.mail.util.LineInputStream;
import sun.mail.util.MailLogger;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;

public final class Session {
    static Class classjavaxmailSession;
    static Class classjavaxmailURLName;
    private static Session defaultSession;

    public final Properties addressMap = new Properties();
    private final Hashtable authTable = new Hashtable();
    private final Authenticator authenticator;
    private boolean debug;
    private MailLogger logger;
    private PrintStream out;
    private final Properties props;
    private final Vector providers = new Vector();
    private final Hashtable providersByClassName = new Hashtable();
    private final Hashtable providersByProtocol = new Hashtable();

    private Session(Properties properties, Authenticator authenticator2) {
        Class cls;
        this.props = properties;
        this.authenticator = authenticator2;
        if (Boolean.valueOf(properties.getProperty("mail.debug")).booleanValue()) {
            this.debug = true;
        }
        initLogger();
        this.logger.log(Level.CONFIG, "JavaMail version {0}", Version.version);
        if (null != authenticator2) {
            cls = authenticator2.getClass();
        } else {
            cls = Session.class;
        }
        loadProviders(cls);
        loadAddressMap(cls);
    }

    private void initLogger() {
        this.logger = new MailLogger(Session.class, "DEBUG", this.debug, getDebugOut());
    }

    public static Session getInstance(Properties properties, Authenticator authenticator2) {
        return new Session(properties, authenticator2);
    }

    public static Session getInstance(Properties properties) {
        return new Session(properties, null);
    }

    public static synchronized Session getDefaultInstance(Properties properties, Authenticator authenticator2) {
        Session session;
        synchronized (Session.class) {
            try {
                Session session2 = defaultSession;
                if (null == session2) {
                    defaultSession = new Session(properties, authenticator2);
                } else {
                    Authenticator authenticator3 = session2.authenticator;
                    if (authenticator3 != authenticator2) {
                        if (null == authenticator3 || null == authenticator2 || authenticator3.getClass().getClassLoader() != authenticator2.getClass().getClassLoader()) {
                            throw new SecurityException("Access to default session denied");
                        }
                    }
                }
                session = defaultSession;
            } catch (Throwable th) {
                throw th;
            }
        }
        return session;
    }

    public static Session getDefaultInstance(Properties properties) {
        return getDefaultInstance(properties, null);
    }

    public synchronized void setDebug(boolean z) {
        this.debug = z;
        initLogger();
        this.logger.log(Level.CONFIG, "setDebug: JavaMail version {0}", Version.version);
    }

    public synchronized boolean getDebug() {
        return this.debug;
    }

    public synchronized void setDebugOut(PrintStream printStream) {
        this.out = printStream;
        initLogger();
    }

    public synchronized PrintStream getDebugOut() {
        PrintStream printStream = this.out;
        if (null != printStream) {
            return printStream;
        }
        return System.out;
    }

    public synchronized Provider[] getProviders() {
        Provider[] providerArr;
        providerArr = new Provider[this.providers.size()];
        this.providers.copyInto(providerArr);
        return providerArr;
    }

    /*  WARNING: Code restructure failed: missing block: B:24:0x008f, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized Provider getProvider(String r5) throws NoSuchProviderException {
        /*
            r4 = this;
            monitor-enter(r4)
            if (r5 == 0) goto L_0x00a7
            int r0 = r5.length()     // Catch:{ all -> 0x0050 }
            if (r0 <= 0) goto L_0x00a7
            java.util.Properties r0 = r4.props     // Catch:{ all -> 0x0050 }
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ all -> 0x0050 }
            r1.<init>()     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = "mail."
            r1.append(r2)     // Catch:{ all -> 0x0050 }
            r1.append(r5)     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = ".class"
            r1.append(r2)     // Catch:{ all -> 0x0050 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0050 }
            java.lang.String r0 = r0.getProperty(r1)     // Catch:{ all -> 0x0050 }
            if (r0 == 0) goto L_0x005b
            com.sun.mail.util.MailLogger r1 = r4.logger     // Catch:{ all -> 0x0050 }
            java.util.logging.Level r2 = java.util.logging.Level.FINE     // Catch:{ all -> 0x0050 }
            boolean r1 = r1.isLoggable(r2)     // Catch:{ all -> 0x0050 }
            if (r1 == 0) goto L_0x0052
            com.sun.mail.util.MailLogger r1 = r4.logger     // Catch:{ all -> 0x0050 }
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ all -> 0x0050 }
            r2.<init>()     // Catch:{ all -> 0x0050 }
            java.lang.String r3 = "mail."
            r2.append(r3)     // Catch:{ all -> 0x0050 }
            r2.append(r5)     // Catch:{ all -> 0x0050 }
            java.lang.String r3 = ".class property exists and points to "
            r2.append(r3)     // Catch:{ all -> 0x0050 }
            r2.append(r0)     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0050 }
            r1.fine(r2)     // Catch:{ all -> 0x0050 }
            goto L_0x0052
        L_0x0050:
            r5 = move-exception
            goto L_0x00af
        L_0x0052:
            java.util.Hashtable r1 = r4.providersByClassName     // Catch:{ all -> 0x0050 }
            java.lang.Object r0 = r1.get(r0)     // Catch:{ all -> 0x0050 }
            javax.mail.Provider r0 = (javax.mail.Provider) r0     // Catch:{ all -> 0x0050 }
            goto L_0x005c
        L_0x005b:
            r0 = 0
        L_0x005c:
            if (r0 == 0) goto L_0x0060
            monitor-exit(r4)
            return r0
        L_0x0060:
            java.util.Hashtable r0 = r4.providersByProtocol     // Catch:{ all -> 0x0050 }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x0050 }
            javax.mail.Provider r0 = (javax.mail.Provider) r0     // Catch:{ all -> 0x0050 }
            if (r0 == 0) goto L_0x0090
            com.sun.mail.util.MailLogger r5 = r4.logger     // Catch:{ all -> 0x0050 }
            java.util.logging.Level r1 = java.util.logging.Level.FINE     // Catch:{ all -> 0x0050 }
            boolean r5 = r5.isLoggable(r1)     // Catch:{ all -> 0x0050 }
            if (r5 == 0) goto L_0x008e
            com.sun.mail.util.MailLogger r5 = r4.logger     // Catch:{ all -> 0x0050 }
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ all -> 0x0050 }
            r1.<init>()     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = "getProvider() returning "
            r1.append(r2)     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = r0.toString()     // Catch:{ all -> 0x0050 }
            r1.append(r2)     // Catch:{ all -> 0x0050 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0050 }
            r5.fine(r1)     // Catch:{ all -> 0x0050 }
        L_0x008e:
            monitor-exit(r4)
            return r0
        L_0x0090:
            javax.mail.NoSuchProviderException r0 = new javax.mail.NoSuchProviderException     // Catch:{ all -> 0x0050 }
            java.lang.StringBuffer r1 = new java.lang.StringBuffer     // Catch:{ all -> 0x0050 }
            r1.<init>()     // Catch:{ all -> 0x0050 }
            java.lang.String r2 = "No provider for "
            r1.append(r2)     // Catch:{ all -> 0x0050 }
            r1.append(r5)     // Catch:{ all -> 0x0050 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0050 }
            r0.<init>(r5)     // Catch:{ all -> 0x0050 }
            throw r0     // Catch:{ all -> 0x0050 }
        L_0x00a7:
            javax.mail.NoSuchProviderException r5 = new javax.mail.NoSuchProviderException     // Catch:{ all -> 0x0050 }
            java.lang.String r0 = "Invalid protocol: null"
            r5.<init>(r0)     // Catch:{ all -> 0x0050 }
            throw r5     // Catch:{ all -> 0x0050 }
        L_0x00af:
            monitor-exit(r4)     // Catch:{ all -> 0x0050 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.Session.getProvider(java.lang.String):javax.mail.Provider");
    }

    public synchronized void setProvider(Provider provider) throws NoSuchProviderException {
        if (null != provider) {
            this.providersByProtocol.put(provider.protocol(), provider);
            Properties properties = this.props;
            final String stringBuffer = "mail." +
                    provider.protocol() +
                    ".class";
            properties.setProperty(stringBuffer, provider.className());
        } else {
            throw new NoSuchProviderException("Can't set null provider");
        }
    }

    public Store getStore() throws NoSuchProviderException {
        return getStore(getProperty("mail.store.protocol"));
    }

    public Store getStore(String str) throws NoSuchProviderException {
        return getStore(new URLName(str, null, -1, null, null, null));
    }

    public Store getStore(URLName uRLName) throws NoSuchProviderException {
        return getStore(getProvider(uRLName.getProtocol()), uRLName);
    }

    public Store getStore(Provider provider) throws NoSuchProviderException {
        return getStore(provider, null);
    }

    private Store getStore(Provider provider, URLName uRLName) throws NoSuchProviderException {
        if (null == provider || provider.type() != Provider.Type.STORE) {
            throw new NoSuchProviderException("invalid provider");
        }
        try {
            return (Store) getService(provider, uRLName);
        } catch (ClassCastException unused) {
            throw new NoSuchProviderException("incorrect class");
        }
    }

    public Folder getFolder(URLName uRLName) throws MessagingException {
        Store store = getStore(uRLName);
        store.connect();
        return store.getFolder(uRLName);
    }

    public Transport getTransport() throws NoSuchProviderException {
        return getTransport(getProperty("mail.transport.protocol"));
    }

    public Transport getTransport(String str) throws NoSuchProviderException {
        return getTransport(new URLName(str, null, -1, null, null, null));
    }

    public Transport getTransport(URLName uRLName) throws NoSuchProviderException {
        return getTransport(getProvider(uRLName.getProtocol()), uRLName);
    }

    public Transport getTransport(Provider provider) throws NoSuchProviderException {
        return getTransport(provider, null);
    }

    public Transport getTransport(Address address) throws NoSuchProviderException {
        final String stringBuffer = "mail.transport.protocol." +
                address.getType();
        String property = getProperty(stringBuffer);
        if (null != property) {
            return getTransport(property);
        }
        String str = this.addressMap.getProperty(address.getType());
        if (null != str) {
            return getTransport(str);
        }
        final String stringBuffer2 = "No provider for Address type: " +
                address.getType();
        throw new NoSuchProviderException(stringBuffer2);
    }

    private Transport getTransport(Provider provider, URLName uRLName) throws NoSuchProviderException {
        if (null == provider || provider.type() != Provider.Type.TRANSPORT) {
            throw new NoSuchProviderException("invalid provider");
        }
        try {
            return (Transport) getService(provider, uRLName);
        } catch (ClassCastException unused) {
            throw new NoSuchProviderException("incorrect class");
        }
    }

    /*  WARNING: Can't wrap try/catch for region: R(2:19|20) */
    /*  WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r2 = java.lang.Class.forName(r9.getClassName());
     */
    /*  WARNING: Code restructure failed: missing block: B:34:0x0089, code lost:
        r10 = move-exception;
     */
    /*  WARNING: Code restructure failed: missing block: B:35:0x008a, code lost:
        r8.logger.log(java.util.logging.Level.FINE, "Exception loading provider", (java.lang.Throwable) r10);
     */
    /*  WARNING: Code restructure failed: missing block: B:36:0x009a, code lost:
        throw new javax.mail.NoSuchProviderException(r9.getProtocol());
     */
    /*  WARNING: Failed to process nested try/catch */
    /*  WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
    /*  WARNING: Removed duplicated region for block: B:17:0x003b A[SYNTHETIC, Splitter:B:17:0x003b] */
    /*  WARNING: Removed duplicated region for block: B:24:0x0050 A[Catch:{ Exception -> 0x0059 }] */
    /*  WARNING: Removed duplicated region for block: B:29:0x005f A[Catch:{ Exception -> 0x0059 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private Object getService(Provider r9, URLName r10) throws NoSuchProviderException {
        /*
            r8 = this;
            java.lang.String r0 = "Exception loading provider"
            if (r9 == 0) goto L_0x009b
            if (r10 != 0) goto L_0x0015
            javax.mail.URLName r10 = new javax.mail.URLName
            java.lang.String r2 = r9.getProtocol()
            r6 = 0
            r7 = 0
            r3 = 0
            r4 = -1
            r5 = 0
            r1 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7)
        L_0x0015:
            javax.mail.Authenticator r1 = r8.authenticator
            if (r1 == 0) goto L_0x0022
            java.lang.Class r1 = r1.getClass()
            java.lang.ClassLoader r1 = r1.getClassLoader()
            goto L_0x0028
        L_0x0022:
            java.lang.Class<javax.mail.Session> r1 = javax.mail.Session.class
            java.lang.ClassLoader r1 = r1.getClassLoader()
        L_0x0028:
            java.lang.ClassLoader r2 = getContextClassLoader()     // Catch:{ Exception -> 0x0044 }
            r3 = 0
            if (r2 == 0) goto L_0x0038
            java.lang.String r4 = r9.getClassName()     // Catch:{ ClassNotFoundException -> 0x0038 }
            java.lang.Class r2 = java.lang.Class.forName(r4, r3, r2)     // Catch:{ ClassNotFoundException -> 0x0038 }
            goto L_0x0039
        L_0x0038:
            r2 = 0
        L_0x0039:
            if (r2 != 0) goto L_0x004c
            java.lang.String r2 = r9.getClassName()     // Catch:{ Exception -> 0x0044 }
            java.lang.Class r2 = java.lang.Class.forName(r2, r3, r1)     // Catch:{ Exception -> 0x0044 }
            goto L_0x004c
        L_0x0044:
            java.lang.String r1 = r9.getClassName()     // Catch:{ Exception -> 0x0089 }
            java.lang.Class r2 = java.lang.Class.forName(r1)     // Catch:{ Exception -> 0x0089 }
        L_0x004c:
            java.lang.Class r1 = classjavaxmailSession     // Catch:{ Exception -> 0x0059 }
            if (r1 != 0) goto L_0x005b
            java.lang.String r1 = "javax.mail.Session"
            java.lang.Class r1 = class(r1)     // Catch:{ Exception -> 0x0059 }
            classjavaxmailSession = r1     // Catch:{ Exception -> 0x0059 }
            goto L_0x005b
        L_0x0059:
            r10 = move-exception
            goto L_0x0078
        L_0x005b:
            java.lang.Class r3 = classjavaxmailURLName     // Catch:{ Exception -> 0x0059 }
            if (r3 != 0) goto L_0x0067
            java.lang.String r3 = "javax.mail.URLName"
            java.lang.Class r3 = class(r3)     // Catch:{ Exception -> 0x0059 }
            classjavaxmailURLName = r3     // Catch:{ Exception -> 0x0059 }
        L_0x0067:
            java.lang.Class[] r1 = new java.lang.Class[]{r1, r3}     // Catch:{ Exception -> 0x0059 }
            java.lang.reflect.Constructor r1 = r2.getConstructor(r1)     // Catch:{ Exception -> 0x0059 }
            java.lang.Object[] r10 = new java.lang.Object[]{r8, r10}     // Catch:{ Exception -> 0x0059 }
            java.lang.Object r9 = r1.newInstance(r10)     // Catch:{ Exception -> 0x0059 }
            return r9
        L_0x0078:
            com.sun.mail.util.MailLogger r1 = r8.logger
            java.util.logging.Level r2 = java.util.logging.Level.FINE
            r1.log((java.util.logging.Level) r2, (java.lang.String) r0, (java.lang.Throwable) r10)
            javax.mail.NoSuchProviderException r10 = new javax.mail.NoSuchProviderException
            java.lang.String r9 = r9.getProtocol()
            r10.<init>(r9)
            throw r10
        L_0x0089:
            r10 = move-exception
            com.sun.mail.util.MailLogger r1 = r8.logger
            java.util.logging.Level r2 = java.util.logging.Level.FINE
            r1.log((java.util.logging.Level) r2, (java.lang.String) r0, (java.lang.Throwable) r10)
            javax.mail.NoSuchProviderException r10 = new javax.mail.NoSuchProviderException
            java.lang.String r9 = r9.getProtocol()
            r10.<init>(r9)
            throw r10
        L_0x009b:
            javax.mail.NoSuchProviderException r9 = new javax.mail.NoSuchProviderException
            java.lang.String r10 = "null"
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.Session.getService(javax.mail.Provider, javax.mail.URLName):java.lang.Object");
    }

    static Class class(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e2) {
            throw new NoClassDefFoundError().initCause(e2);
        }
    }

    public void setPasswordAuthentication(URLName uRLName, PasswordAuthentication passwordAuthentication) {
        if (null == passwordAuthentication) {
            this.authTable.remove(uRLName);
        } else {
            this.authTable.put(uRLName, passwordAuthentication);
        }
    }

    public PasswordAuthentication getPasswordAuthentication(URLName uRLName) {
        return (PasswordAuthentication) this.authTable.get(uRLName);
    }

    public PasswordAuthentication requestPasswordAuthentication(InetAddress inetAddress, int i2, String str, String str2, String str3) {
        Authenticator authenticator2 = this.authenticator;
        if (null != authenticator2) {
            return authenticator2.requestPasswordAuthentication(inetAddress, i2, str, str2, str3);
        }
        return null;
    }

    public Properties getProperties() {
        return this.props;
    }

    public String getProperty(String str) {
        return this.props.getProperty(str);
    }

    private void loadProviders(Class cls) {
        AnonymousClass1 r0 = new StreamLoader() {
            public void load(InputStream inputStream) throws IOException {
                Session.this.loadProvidersFromStream(inputStream);
            }
        };
        try {
            String str = File.separator;
            final String stringBuffer = System.getProperty("java.home") +
                    str +
                    "lib" +
                    str +
                    "javamail.providers";
            loadFile(stringBuffer, r0);
        } catch (SecurityException e2) {
            this.logger.log(Level.CONFIG, "can't get java.home", e2);
        }
        loadAllResources("META-INF/javamail.providers", cls, r0);
        loadResource("/META-INF/javamail.default.providers", cls, r0);
        if (0 == providers.size()) {
            this.logger.config("failed to load any providers, using defaults");
            Provider.Type type = Provider.Type.STORE;
            addProvider(new Provider(type, "imap", "com.sun.mail.imap.IMAPStore", "Sun Microsystems, Inc.", Version.version));
            Provider.Type type2 = type;
            addProvider(new Provider(type2, "imaps", "com.sun.mail.imap.IMAPSSLStore", "Sun Microsystems, Inc.", Version.version));
            addProvider(new Provider(type2, "pop3", "com.sun.mail.pop3.POP3Store", "Sun Microsystems, Inc.", Version.version));
            addProvider(new Provider(type2, "pop3s", "com.sun.mail.pop3.POP3SSLStore", "Sun Microsystems, Inc.", Version.version));
            Provider.Type type3 = Provider.Type.TRANSPORT;
            addProvider(new Provider(type3, "smtp", "com.sun.mail.smtp.SMTPTransport", "Sun Microsystems, Inc.", Version.version));
            addProvider(new Provider(type3, "smtps", "com.sun.mail.smtp.SMTPSSLTransport", "Sun Microsystems, Inc.", Version.version));
        }
        if (this.logger.isLoggable(Level.CONFIG)) {
            this.logger.config("Tables of loaded providers");
            MailLogger mailLogger = this.logger;
            final String stringBuffer2 = "Providers Listed By Class Name: " +
                    this.providersByClassName.toString();
            mailLogger.config(stringBuffer2);
            MailLogger mailLogger2 = this.logger;
            final String stringBuffer3 = "Providers Listed By Protocol: " +
                    this.providersByProtocol.toString();
            mailLogger2.config(stringBuffer3);
        }
    }


    public void loadProvidersFromStream(InputStream inputStream) throws IOException {
        if (null != inputStream) {
            LineInputStream lineInputStream = new LineInputStream(inputStream);
            while (true) {
                String readLine = lineInputStream.readLine();
                if (null == readLine) {
                    return;
                }
                if (!readLine.startsWith("#")) {
                    StringTokenizer stringTokenizer = new StringTokenizer(readLine, ";");
                    Provider.Type type = null;
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    String str4 = null;
                    while (stringTokenizer.hasMoreTokens()) {
                        String trim = stringTokenizer.nextToken().trim();
                        int indexOf = trim.indexOf('=');
                        if (trim.startsWith("protocol=")) {
                            str = trim.substring(indexOf + 1);
                        } else if (trim.startsWith("type=")) {
                            String substring = trim.substring(indexOf + 1);
                            if ("store".equalsIgnoreCase(substring)) {
                                type = Provider.Type.STORE;
                            } else if (substring.equalsIgnoreCase(NotificationCompat.CATEGORY_TRANSPORT)) {
                                type = Provider.Type.TRANSPORT;
                            }
                        } else if (trim.startsWith("class=")) {
                            str2 = trim.substring(indexOf + 1);
                        } else if (trim.startsWith("vendor=")) {
                            str3 = trim.substring(indexOf + 1);
                        } else if (trim.startsWith("version=")) {
                            str4 = trim.substring(indexOf + 1);
                        }
                    }
                    if (null == type || null == str || null == str2 || 0 >= str.length() || 0 >= str2.length()) {
                        this.logger.log(Level.CONFIG, "Bad provider entry: {0}", readLine);
                    } else {
                        addProvider(new Provider(type, str, str2, str3, str4));
                    }
                }
            }
        }
    }

    public synchronized void addProvider(Provider provider) {
        this.providers.addElement(provider);
        this.providersByClassName.put(provider.className(), provider);
        if (!this.providersByProtocol.containsKey(provider.protocol())) {
            this.providersByProtocol.put(provider.protocol(), provider);
        }
    }

    private void loadAddressMap(Class cls) {
        AnonymousClass2 r0 = new StreamLoader() {
            public void load(InputStream inputStream) throws IOException {
                Session.this.addressMap.load(inputStream);
            }
        };
        loadResource("/META-INF/javamail.default.address.map", cls, r0);
        loadAllResources("META-INF/javamail.address.map", cls, r0);
        try {
            String str = File.separator;
            final String stringBuffer = System.getProperty("java.home") +
                    str +
                    "lib" +
                    str +
                    "javamail.address.map";
            loadFile(stringBuffer, r0);
        } catch (SecurityException e2) {
            this.logger.log(Level.CONFIG, "can't get java.home", e2);
        }
        if (this.addressMap.isEmpty()) {
            this.logger.config("failed to load address map, using defaults");
            this.addressMap.setProperty("rfc822", "smtp");
        }
    }

    public synchronized void setProtocolForAddress(String str, String str2) {
        if (null == str2) {
            try {
                this.addressMap.remove(str);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        } else {
            this.addressMap.setProperty(str, str2);
        }
    }

    /*  WARNING: Removed duplicated region for block: B:21:0x0039 A[Catch:{ all -> 0x0029 }] */
    /*  WARNING: Removed duplicated region for block: B:28:0x005d A[Catch:{ all -> 0x0029 }] */
    /*  WARNING: Removed duplicated region for block: B:31:0x0076 A[SYNTHETIC, Splitter:B:31:0x0076] */
    /*  WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /*  WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /*  WARNING: Removed duplicated region for block: B:41:? A[RETURN, SYNTHETIC] */
    /*  WARNING: Unknown top exception splitter block from list: {B:25:0x0053=Splitter:B:25:0x0053, B:18:0x002f=Splitter:B:18:0x002f} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadFile(String r6, StreamLoader r7) {
        /*
            r5 = this;
            java.lang.String r0 = "not loading file: "
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x007a, IOException -> 0x002d, SecurityException -> 0x002b }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x007a, IOException -> 0x002d, SecurityException -> 0x002b }
            r3.<init>(r6)     // Catch:{ FileNotFoundException -> 0x007a, IOException -> 0x002d, SecurityException -> 0x002b }
            r2.<init>(r3)     // Catch:{ FileNotFoundException -> 0x007a, IOException -> 0x002d, SecurityException -> 0x002b }
            r7.load(r2)     // Catch:{ FileNotFoundException -> 0x0027, IOException -> 0x0024, SecurityException -> 0x0021, all -> 0x001e }
            com.sun.mail.util.MailLogger r7 = r5.logger     // Catch:{ FileNotFoundException -> 0x0027, IOException -> 0x0024, SecurityException -> 0x0021, all -> 0x001e }
            java.util.logging.Level r1 = java.util.logging.Level.CONFIG     // Catch:{ FileNotFoundException -> 0x0027, IOException -> 0x0024, SecurityException -> 0x0021, all -> 0x001e }
            java.lang.String r3 = "successfully loaded file: {0}"
            r7.log((java.util.logging.Level) r1, (java.lang.String) r3, (java.lang.Object) r6)     // Catch:{ FileNotFoundException -> 0x0027, IOException -> 0x0024, SecurityException -> 0x0021, all -> 0x001e }
            r2.close()     // Catch:{ IOException -> 0x007d }
            goto L_0x007d
        L_0x001e:
            r6 = move-exception
            r1 = r2
            goto L_0x0074
        L_0x0021:
            r7 = move-exception
            r1 = r2
            goto L_0x002f
        L_0x0024:
            r7 = move-exception
            r1 = r2
            goto L_0x0053
        L_0x0027:
            r1 = r2
            goto L_0x007a
        L_0x0029:
            r6 = move-exception
            goto L_0x0074
        L_0x002b:
            r7 = move-exception
            goto L_0x002f
        L_0x002d:
            r7 = move-exception
            goto L_0x0053
        L_0x002f:
            com.sun.mail.util.MailLogger r2 = r5.logger     // Catch:{ all -> 0x0029 }
            java.util.logging.Level r3 = java.util.logging.Level.CONFIG     // Catch:{ all -> 0x0029 }
            boolean r2 = r2.isLoggable(r3)     // Catch:{ all -> 0x0029 }
            if (r2 == 0) goto L_0x004d
            com.sun.mail.util.MailLogger r2 = r5.logger     // Catch:{ all -> 0x0029 }
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ all -> 0x0029 }
            r4.<init>()     // Catch:{ all -> 0x0029 }
            r4.append(r0)     // Catch:{ all -> 0x0029 }
            r4.append(r6)     // Catch:{ all -> 0x0029 }
            java.lang.String r6 = r4.toString()     // Catch:{ all -> 0x0029 }
            r2.log((java.util.logging.Level) r3, (java.lang.String) r6, (java.lang.Throwable) r7)     // Catch:{ all -> 0x0029 }
        L_0x004d:
            if (r1 == 0) goto L_0x007d
        L_0x004f:
            r1.close()     // Catch:{ IOException -> 0x007d }
            goto L_0x007d
        L_0x0053:
            com.sun.mail.util.MailLogger r2 = r5.logger     // Catch:{ all -> 0x0029 }
            java.util.logging.Level r3 = java.util.logging.Level.CONFIG     // Catch:{ all -> 0x0029 }
            boolean r2 = r2.isLoggable(r3)     // Catch:{ all -> 0x0029 }
            if (r2 == 0) goto L_0x0071
            com.sun.mail.util.MailLogger r2 = r5.logger     // Catch:{ all -> 0x0029 }
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ all -> 0x0029 }
            r4.<init>()     // Catch:{ all -> 0x0029 }
            r4.append(r0)     // Catch:{ all -> 0x0029 }
            r4.append(r6)     // Catch:{ all -> 0x0029 }
            java.lang.String r6 = r4.toString()     // Catch:{ all -> 0x0029 }
            r2.log((java.util.logging.Level) r3, (java.lang.String) r6, (java.lang.Throwable) r7)     // Catch:{ all -> 0x0029 }
        L_0x0071:
            if (r1 == 0) goto L_0x007d
            goto L_0x004f
        L_0x0074:
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch:{ IOException -> 0x0079 }
        L_0x0079:
            throw r6
        L_0x007a:
            if (r1 == 0) goto L_0x007d
            goto L_0x004f
        L_0x007d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.Session.loadFile(java.lang.String, javax.mail.StreamLoader):void");
    }

    private void loadResource(String str, Class cls, StreamLoader streamLoader) {
        InputStream inputStream = null;
        try {
            inputStream = getResourceAsStream(cls, str);
            if (null != inputStream) {
                streamLoader.load(inputStream);
                this.logger.log(Level.CONFIG, "successfully loaded resource: {0}", str);
            }
            if (null == inputStream) {
                return;
            }
        } catch (IOException e2) {
            this.logger.log(Level.CONFIG, "Exception loading resource", e2);
            if (null == inputStream) {
                return;
            }
        } catch (SecurityException e3) {
            this.logger.log(Level.CONFIG, "Exception loading resource", e3);
            if (null == inputStream) {
                return;
            }
        } catch (Throwable th) {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
            }
            throw th;
        }
        try {
            inputStream.close();
        } catch (IOException unused2) {
        }
    }

    /*  WARNING: Exception block dominator not found, dom blocks: [] */
    /*  WARNING: Missing exception handler attribute for start block: B:42:0x006f */
    /*  WARNING: Removed duplicated region for block: B:51:0x0081  */
    /*  WARNING: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadAllResources(String r10, Class r11, StreamLoader r12) {
        /*
            r9 = this;
            java.lang.String r0 = "Exception loading resource"
            r1 = 0
            java.lang.ClassLoader r2 = getContextClassLoader()     // Catch:{ Exception -> 0x000e }
            if (r2 != 0) goto L_0x0011
            java.lang.ClassLoader r2 = r11.getClassLoader()     // Catch:{ Exception -> 0x000e }
            goto L_0x0011
        L_0x000e:
            r2 = move-exception
            goto L_0x0078
        L_0x0011:
            if (r2 == 0) goto L_0x0018
            java.net.URL[] r2 = getResources(r2, r10)     // Catch:{ Exception -> 0x000e }
            goto L_0x001c
        L_0x0018:
            java.net.URL[] r2 = getSystemResources(r10)     // Catch:{ Exception -> 0x000e }
        L_0x001c:
            if (r2 == 0) goto L_0x007f
            r3 = r1
        L_0x001f:
            int r4 = r2.length     // Catch:{ Exception -> 0x0053 }
            if (r1 >= r4) goto L_0x0076
            r4 = r2[r1]     // Catch:{ Exception -> 0x0053 }
            com.sun.mail.util.MailLogger r5 = r9.logger     // Catch:{ Exception -> 0x0053 }
            java.util.logging.Level r6 = java.util.logging.Level.CONFIG     // Catch:{ Exception -> 0x0053 }
            java.lang.String r7 = "URL {0}"
            r5.log((java.util.logging.Level) r6, (java.lang.String) r7, (java.lang.Object) r4)     // Catch:{ Exception -> 0x0053 }
            r5 = 0
            java.io.InputStream r5 = openStream(r4)     // Catch:{ FileNotFoundException -> 0x0070, IOException -> 0x0044, SecurityException -> 0x0042 }
            if (r5 == 0) goto L_0x0046
            r12.load(r5)     // Catch:{ FileNotFoundException -> 0x0070, IOException -> 0x0044, SecurityException -> 0x0042 }
            r3 = 1
            com.sun.mail.util.MailLogger r7 = r9.logger     // Catch:{ FileNotFoundException -> 0x0070, IOException -> 0x0044, SecurityException -> 0x0042 }
            java.lang.String r8 = "successfully loaded resource: {0}"
            r7.log((java.util.logging.Level) r6, (java.lang.String) r8, (java.lang.Object) r4)     // Catch:{ FileNotFoundException -> 0x0070, IOException -> 0x0044, SecurityException -> 0x0042 }
            goto L_0x004d
        L_0x0040:
            r1 = move-exception
            goto L_0x006a
        L_0x0042:
            r4 = move-exception
            goto L_0x0056
        L_0x0044:
            r4 = move-exception
            goto L_0x0060
        L_0x0046:
            com.sun.mail.util.MailLogger r7 = r9.logger     // Catch:{ FileNotFoundException -> 0x0070, IOException -> 0x0044, SecurityException -> 0x0042 }
            java.lang.String r8 = "not loading resource: {0}"
            r7.log((java.util.logging.Level) r6, (java.lang.String) r8, (java.lang.Object) r4)     // Catch:{ FileNotFoundException -> 0x0070, IOException -> 0x0044, SecurityException -> 0x0042 }
        L_0x004d:
            if (r5 == 0) goto L_0x0073
        L_0x004f:
            r5.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x0073
        L_0x0053:
            r2 = move-exception
            r1 = r3
            goto L_0x0078
        L_0x0056:
            com.sun.mail.util.MailLogger r6 = r9.logger     // Catch:{ all -> 0x0040 }
            java.util.logging.Level r7 = java.util.logging.Level.CONFIG     // Catch:{ all -> 0x0040 }
            r6.log((java.util.logging.Level) r7, (java.lang.String) r0, (java.lang.Throwable) r4)     // Catch:{ all -> 0x0040 }
            if (r5 == 0) goto L_0x0073
            goto L_0x004f
        L_0x0060:
            com.sun.mail.util.MailLogger r6 = r9.logger     // Catch:{ all -> 0x0040 }
            java.util.logging.Level r7 = java.util.logging.Level.CONFIG     // Catch:{ all -> 0x0040 }
            r6.log((java.util.logging.Level) r7, (java.lang.String) r0, (java.lang.Throwable) r4)     // Catch:{ all -> 0x0040 }
            if (r5 == 0) goto L_0x0073
            goto L_0x004f
        L_0x006a:
            if (r5 == 0) goto L_0x006f
            r5.close()     // Catch:{ IOException -> 0x006f }
        L_0x006f:
            throw r1     // Catch:{ Exception -> 0x0053 }
        L_0x0070:
            if (r5 == 0) goto L_0x0073
            goto L_0x004f
        L_0x0073:
            int r1 = r1 + 1
            goto L_0x001f
        L_0x0076:
            r1 = r3
            goto L_0x007f
        L_0x0078:
            com.sun.mail.util.MailLogger r3 = r9.logger
            java.util.logging.Level r4 = java.util.logging.Level.CONFIG
            r3.log((java.util.logging.Level) r4, (java.lang.String) r0, (java.lang.Throwable) r2)
        L_0x007f:
            if (r1 != 0) goto L_0x0095
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = "/"
            r0.append(r1)
            r0.append(r10)
            java.lang.String r10 = r0.toString()
            r9.loadResource(r10, r11, r12)
        L_0x0095:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.Session.loadAllResources(java.lang.String, java.lang.Class, javax.mail.StreamLoader):void");
    }

    private static ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    return Thread.currentThread().getContextClassLoader();
                } catch (SecurityException unused) {
                    return null;
                }
            }
        });
    }

    private static InputStream getResourceAsStream(final Class cls, final String str) throws IOException {
        try {
            return (InputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws IOException {
                    return cls.getResourceAsStream(str);
                }
            });
        } catch (PrivilegedActionException e2) {
            throw ((IOException) e2.getException());
        }
    }

    private static URL[] getResources(final ClassLoader classLoader, final String str) {
        return (URL[]) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    Vector vector = new Vector();
                    Enumeration<URL> resources = classLoader.getResources(str);
                    while (null != resources && resources.hasMoreElements()) {
                        URL nextElement = resources.nextElement();
                        if (null != nextElement) {
                            vector.addElement(nextElement);
                        }
                    }
                    if (0 >= vector.size()) {
                        return null;
                    }
                    URL[] urlArr = new URL[vector.size()];
                    vector.copyInto(urlArr);
                    return urlArr;
                } catch (IOException | SecurityException unused) {
                    return null;
                }
            }
        });
    }

    private static URL[] getSystemResources(final String str) {
        return (URL[]) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    Vector vector = new Vector();
                    Enumeration<URL> systemResources = ClassLoader.getSystemResources(str);
                    while (null != systemResources && systemResources.hasMoreElements()) {
                        URL nextElement = systemResources.nextElement();
                        if (null != nextElement) {
                            vector.addElement(nextElement);
                        }
                    }
                    if (0 >= vector.size()) {
                        return null;
                    }
                    URL[] urlArr = new URL[vector.size()];
                    vector.copyInto(urlArr);
                    return urlArr;
                } catch (IOException | SecurityException unused) {
                    return null;
                }
            }
        });
    }

    private static InputStream openStream(final URL url) throws IOException {
        try {
            return (InputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws IOException {
                    return url.openStream();
                }
            });
        } catch (PrivilegedActionException e2) {
            throw ((IOException) e2.getException());
        }
    }
}
