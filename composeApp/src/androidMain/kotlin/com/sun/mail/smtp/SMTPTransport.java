package com.sun.mail.smtp;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import com.sun.mail.auth.Ntlm;
import org.springframework.http.HttpHeaders;
import com.sun.mail.util.PropUtil;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.ParseException;
import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.util.logging.Level;

public class SMTPTransport extends Transport {
    static final boolean assertionsDisabled = true;
    private static final byte[] CRLF = {13, 10};
    private static final String UNKNOWN = "UNKNOWN";
    private static final String[] UNKNOWN_SA = new String[0];
    static Class classcomsunmailsmtpSMTPTransport =  ("com.sun.mail.smtp.SMTPTransport").getClass();
    static Class classcomsunmailutilMailLogger;
    static Class classjavalangString;
    static Class classjavautilProperties;
    private static final char[] hexchar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String[] ignoreList = {"Bcc", HttpHeaders.CONTENT_LENGTH};
    private Address[] addresses;
    private final Map authenticators;
    private String authorizationID;
    private SMTPOutputStream dataStream;
    private final String defaultAuthenticationMechanisms;
    private int defaultPort;
    private boolean enableSASL;
    private MessagingException exception;
    private Hashtable extMap;
    private String host;
    private Address[] invalidAddr;
    private boolean isSSL;
    private int lastReturnCode;
    private String lastServerResponse;
    private com.sun.mail.util.LineInputStream lineInputStream;
    private String localHostName;
    public com.sun.mail.util.MailLogger logger;
    private MimeMessage message;
    public String name;
    public boolean noauthdebug;
    private boolean noopStrict;
    private boolean notificationDone;
    private String ntlmDomain;
    private boolean quitWait;
    private boolean reportSuccess;
    private boolean requireStartTLS;
    private SaslAuthenticator saslAuthenticator;
    private String[] saslMechanisms;
    private String saslRealm;
    private final boolean sendPartiallyFailed;
    private BufferedInputStream serverInput;
    private OutputStream serverOutput;
    private Socket serverSocket;
    private com.sun.mail.util.TraceInputStream traceInput;
    private final com.sun.mail.util.MailLogger traceLogger;
    private com.sun.mail.util.TraceOutputStream traceOutput;
    private boolean useRset;
    private boolean useStartTLS;
    private Address[] validSentAddr;
    private Address[] validUnsentAddr;

    private void sendMessageEnd() {
    }

    private void sendMessageStart(final String str) {
    }

    static {
        if (null == classcomsunmailsmtpSMTPTransport) {
        }
    }

    public SMTPTransport(final Session session, final URLName uRLName) {
        this(session, uRLName, "smtp", false);
    }

    protected SMTPTransport(final Session session, final URLName uRLName, String str, boolean z) {
        super(session, uRLName);
        name = "smtp";
        defaultPort = 25;
        isSSL = false;
        sendPartiallyFailed = false;
        authenticators = new HashMap();
        quitWait = false;
        saslRealm = SMTPTransport.UNKNOWN;
        authorizationID = SMTPTransport.UNKNOWN;
        enableSASL = false;
        saslMechanisms = SMTPTransport.UNKNOWN_SA;
        ntlmDomain = SMTPTransport.UNKNOWN;
        noopStrict = true;
        noauthdebug = true;
        final com.sun.mail.util.MailLogger mailLogger = new com.sun.mail.util.MailLogger(this.getClass(), "DEBUG SMTP", session);
        logger = mailLogger;
        traceLogger = mailLogger.getSubLogger("protocol", null);
        noauthdebug = !com.sun.mail.util.PropUtil.getBooleanSessionProperty(session, "mail.debug.auth", false);
        str = null != uRLName ? uRLName.getProtocol() : str;
        name = str;
        if (!z) {
            String stringBuffer = "mail." +
                    str +
                    ".ssl.enable";
            z = com.sun.mail.util.PropUtil.getBooleanSessionProperty(session, stringBuffer, false);
        }
        if (z) {
            defaultPort = 465;
        } else {
            defaultPort = 25;
        }
        isSSL = z;
        String stringBuffer2 = "mail." +
                str +
                ".quitwait";
        quitWait = com.sun.mail.util.PropUtil.getBooleanSessionProperty(session, stringBuffer2, true);
        String stringBuffer3 = "mail." +
                str +
                ".reportsuccess";
        reportSuccess = com.sun.mail.util.PropUtil.getBooleanSessionProperty(session, stringBuffer3, false);
        String stringBuffer4 = "mail." +
                str +
                ".starttls.enable";
        useStartTLS = com.sun.mail.util.PropUtil.getBooleanSessionProperty(session, stringBuffer4, false);
        String stringBuffer5 = "mail." +
                str +
                ".starttls.required";
        requireStartTLS = com.sun.mail.util.PropUtil.getBooleanSessionProperty(session, stringBuffer5, false);
        String stringBuffer6 = "mail." +
                str +
                ".userset";
        useRset = com.sun.mail.util.PropUtil.getBooleanSessionProperty(session, stringBuffer6, false);
        String stringBuffer7 = "mail." +
                str +
                ".noop.strict";
        noopStrict = com.sun.mail.util.PropUtil.getBooleanSessionProperty(session, stringBuffer7, true);
        String stringBuffer8 = "mail." +
                str +
                ".sasl.enable";
        final boolean booleanSessionProperty = com.sun.mail.util.PropUtil.getBooleanSessionProperty(session, stringBuffer8, false);
        enableSASL = booleanSessionProperty;
        if (booleanSessionProperty) {
            logger.config("enable SASL");
        }
        final Authenticator[] authenticatorArr = {new LoginAuthenticator(), new PlainAuthenticator(), new DigestMD5Authenticator(), new NtlmAuthenticator()};
        final StringBuffer stringBuffer9 = new StringBuffer();
        for (int i2 = 0; 4 > i2; i2++) {
            authenticators.put(authenticatorArr[i2].getMechanism(), authenticatorArr[i2]);
            stringBuffer9.append(authenticatorArr[i2].getMechanism());
            stringBuffer9.append(' ');
        }
        defaultAuthenticationMechanisms = stringBuffer9.toString();
    }

    public synchronized String getLocalHost() {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.smtp.SMTPTransport.getLocalHost():java.lang.String");
    }

    public synchronized void setLocalHost(final String str) {
        localHostName = str;
    }

    public synchronized void connect(final Socket socket) throws MessagingException {
        serverSocket = socket;
        connect();
    }

    public synchronized String getAuthorizationId() {
        try {
            if (SMTPTransport.UNKNOWN == this.authorizationID) {
                final Session session = this.session;
                String stringBuffer = "mail." +
                        name +
                        ".sasl.authorizationid";
                authorizationID = session.getProperty(stringBuffer);
            }
        } catch (final Throwable th) {
            while (true) {
                throw th;
            }
        }
        return authorizationID;
    }

    public synchronized void setAuthorizationID(final String str) {
        authorizationID = str;
    }

    public synchronized boolean getSASLEnabled() {
        return enableSASL;
    }

    public synchronized void setSASLEnabled(final boolean z) {
        enableSASL = z;
    }

    public synchronized String getSASLRealm() {
        try {
            if (SMTPTransport.UNKNOWN == this.saslRealm) {
                final Session session = this.session;
                String stringBuffer = "mail." +
                        name +
                        ".sasl.realm";
                final String property = session.getProperty(stringBuffer);
                saslRealm = property;
                if (null == property) {
                    final Session session2 = this.session;
                    String stringBuffer2 = "mail." +
                            name +
                            ".saslrealm";
                    saslRealm = session2.getProperty(stringBuffer2);
                }
            }
        } catch (final Throwable th) {
            while (true) {
                throw th;
            }
        }
        return saslRealm;
    }

    public synchronized void setSASLRealm(final String str) {
        saslRealm = str;
    }

    public synchronized String[] getSASLMechanisms() {
        try {
            if (saslMechanisms == SMTPTransport.UNKNOWN_SA) {
                final ArrayList arrayList = new ArrayList(5);
                final Session session = this.session;
                String stringBuffer = "mail." +
                        name +
                        ".sasl.mechanisms";
                final String property = session.getProperty(stringBuffer);
                if (null != property && 0 < property.length()) {
                    if (logger.isLoggable(Level.FINE)) {
                        final com.sun.mail.util.MailLogger mailLogger = logger;
                        String stringBuffer2 = "SASL mechanisms allowed: " +
                                property;
                        mailLogger.fine(stringBuffer2);
                    }
                    final StringTokenizer stringTokenizer = new StringTokenizer(property, " ,");
                    while (stringTokenizer.hasMoreTokens()) {
                        final String nextToken = stringTokenizer.nextToken();
                        if (0 < nextToken.length()) {
                            arrayList.add(nextToken);
                        }
                    }
                }
                final String[] strArr = new String[arrayList.size()];
                saslMechanisms = strArr;
                arrayList.toArray(strArr);
            }
            final String[] strArr2 = saslMechanisms;
            if (null == strArr2) {
                return null;
            }
            return strArr2.clone();
        } finally {
            while (true) {
            }
        }
    }

    public synchronized void setSASLMechanisms(String[] strArr) {
        if (null != strArr) {
            try {
                strArr = strArr.clone();
            } catch (final Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        saslMechanisms = strArr;
    }

    public synchronized String getNTLMDomain() {
        try {
            if (SMTPTransport.UNKNOWN == this.ntlmDomain) {
                final Session session = this.session;
                String stringBuffer = "mail." +
                        name +
                        ".auth.ntlm.domain";
                ntlmDomain = session.getProperty(stringBuffer);
            }
        } catch (final Throwable th) {
            while (true) {
                throw th;
            }
        }
        return ntlmDomain;
    }

    public synchronized void setNTLMDomain(final String str) {
        ntlmDomain = str;
    }

    public synchronized boolean getReportSuccess() {
        return reportSuccess;
    }

    public synchronized void setReportSuccess(final boolean z) {
        reportSuccess = z;
    }

    public synchronized boolean getStartTLS() {
        return useStartTLS;
    }

    public synchronized void setStartTLS(final boolean z) {
        useStartTLS = z;
    }

    public synchronized boolean getRequireStartTLS() {
        return requireStartTLS;
    }

    public synchronized void setRequireStartTLS(final boolean z) {
        requireStartTLS = z;
    }

    public boolean isSSL() {
        return serverSocket instanceof SSLSocket;
    }

    public synchronized boolean getUseRset() {
        return useRset;
    }

    public synchronized void setUseRset(final boolean z) {
        useRset = z;
    }

    public synchronized boolean getNoopStrict() {
        return noopStrict;
    }

    public synchronized void setNoopStrict(final boolean z) {
        noopStrict = z;
    }

    public synchronized String getLastServerResponse() {
        return lastServerResponse;
    }

    public synchronized int getLastReturnCode() {
        return lastReturnCode;
    }

    public synchronized boolean protocolConnect(final String r8, final int r9, final String r10, final String r11) throws MessagingException {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.smtp.SMTPTransport.protocolConnect(java.lang.String, int, java.lang.String, java.lang.String):boolean");
    }

    private boolean authenticate(final String str, final String str2) throws MessagingException {
        final Session session = this.session;
        String stringBuffer = "mail." +
                name +
                ".auth.mechanisms";
        String property = session.getProperty(stringBuffer);
        if (null == property) {
            property = defaultAuthenticationMechanisms;
        }
        String authorizationId = this.getAuthorizationId();
        if (null == authorizationId) {
            authorizationId = str;
        }
        if (enableSASL) {
            logger.fine("Authenticate with SASL");
            if (this.sasllogin(this.getSASLMechanisms(), this.getSASLRealm(), authorizationId, str, str2)) {
                return true;
            }
            logger.fine("SASL authentication failed");
        }
        if (logger.isLoggable(Level.FINE)) {
            final com.sun.mail.util.MailLogger mailLogger = logger;
            String stringBuffer2 = "Attempt to authenticate using mechanisms: " +
                    property;
            mailLogger.fine(stringBuffer2);
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(property);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final Locale locale = Locale.ENGLISH;
            final String stringBuffer4 = "mail." +
                    name +
                    ".auth." +
                    nextToken.toLowerCase(locale) +
                    ".disable";
            if (!com.sun.mail.util.PropUtil.getBooleanSessionProperty(this.session, stringBuffer4, false)) {
                final String upperCase = nextToken.toUpperCase(locale);
                if (!this.supportsAuthentication(upperCase)) {
                    logger.log(Level.FINE, "mechanism {0} not supported by server", upperCase);
                } else {
                    final Authenticator authenticator = (Authenticator) authenticators.get(upperCase);
                    if (null != authenticator) {
                        return authenticator.authenticate(host, authorizationId, str, str2);
                    }
                    logger.log(Level.FINE, "no authenticator for mechanism {0}", upperCase);
                }
            } else if (logger.isLoggable(Level.FINE)) {
                final com.sun.mail.util.MailLogger mailLogger2 = logger;
                String stringBuffer5 = "mechanism " +
                        nextToken +
                        " disabled by property: " +
                        stringBuffer4;
                mailLogger2.fine(stringBuffer5);
            }
        }
        throw new AuthenticationFailedException("No authentication mechanisms supported by both server and client");
    }

    private abstract class Authenticator {
        private final String mech;
        protected int resp;


        public abstract void doAuth(String str, String str2, String str3, String str4) throws MessagingException, IOException;


        public String getInitialResponse(final String str, final String str2, final String str3, final String str4) throws MessagingException, IOException {
            return null;
        }

        Authenticator(final String str) {
            mech = str.toUpperCase(Locale.ENGLISH);
        }


        public String getMechanism() {
            return mech;
        }


        public boolean authenticate(final String str, final String str2, final String str3, final String str4) throws MessagingException {
            String str5 = "failed";
            try {
                final String initialResponse = this.getInitialResponse(str, str2, str3, str4);
                if (noauthdebug && isTracing()) {
                    final com.sun.mail.util.MailLogger access200 = logger;
                    String stringBuffer = "AUTH " +
                            mech +
                            " command trace suppressed";
                    access200.fine(stringBuffer);
                    suspendTracing();
                }
                if (null != initialResponse) {
                    final SMTPTransport sMTPTransport = SMTPTransport.this;
                    String stringBuffer2 = "AUTH " +
                            mech +
                            " " +
                            (0 == initialResponse.length() ? "=" : initialResponse);
                    resp = sMTPTransport.simpleCommand(stringBuffer2);
                } else {
                    final SMTPTransport sMTPTransport2 = SMTPTransport.this;
                    String stringBuffer3 = "AUTH " +
                            mech;
                    resp = sMTPTransport2.simpleCommand(stringBuffer3);
                }
                if (530 == this.resp) {
                    startTLS();
                    if (null != initialResponse) {
                        final SMTPTransport sMTPTransport3 = SMTPTransport.this;
                        String stringBuffer4 = "AUTH " +
                                mech +
                                " " +
                                initialResponse;
                        resp = sMTPTransport3.simpleCommand(stringBuffer4);
                    } else {
                        final SMTPTransport sMTPTransport4 = SMTPTransport.this;
                        String stringBuffer5 = "AUTH " +
                                mech;
                        resp = sMTPTransport4.simpleCommand(stringBuffer5);
                    }
                }
                if (334 == this.resp) {
                    this.doAuth(str, str2, str3, str4);
                }
                if (noauthdebug && isTracing()) {
                    final com.sun.mail.util.MailLogger access2002 = logger;
                    final StringBuffer stringBuffer6 = new StringBuffer();
                    stringBuffer6.append("AUTH ");
                    stringBuffer6.append(mech);
                    stringBuffer6.append(" ");
                    if (235 == this.resp) {
                        str5 = "succeeded";
                    }
                    stringBuffer6.append(str5);
                    access2002.fine(stringBuffer6.toString());
                }
                resumeTracing();
                if (235 == this.resp) {
                    return true;
                }
                closeConnection();
                throw new AuthenticationFailedException(getLastServerResponse());
            } catch (final IOException e2) {
                final com.sun.mail.util.MailLogger access2003 = logger;
                final Level level = Level.FINE;
                String stringBuffer7 = "AUTH " +
                        mech +
                        " failed";
                access2003.log(level, stringBuffer7, e2);
                if (noauthdebug && isTracing()) {
                    final com.sun.mail.util.MailLogger access2004 = logger;
                    final StringBuffer stringBuffer8 = new StringBuffer();
                    stringBuffer8.append("AUTH ");
                    stringBuffer8.append(mech);
                    stringBuffer8.append(" ");
                    if (235 == this.resp) {
                        str5 = "succeeded";
                    }
                    stringBuffer8.append(str5);
                    access2004.fine(stringBuffer8.toString());
                }
                resumeTracing();
                if (235 == this.resp) {
                    return true;
                }
                closeConnection();
                throw new AuthenticationFailedException(getLastServerResponse());
            } catch (final Throwable th) {
                if (noauthdebug && isTracing()) {
                    final com.sun.mail.util.MailLogger access2005 = logger;
                    final StringBuffer stringBuffer9 = new StringBuffer();
                    stringBuffer9.append("AUTH ");
                    stringBuffer9.append(mech);
                    stringBuffer9.append(" ");
                    if (235 == this.resp) {
                        str5 = "succeeded";
                    }
                    stringBuffer9.append(str5);
                    access2005.fine(stringBuffer9.toString());
                }
                resumeTracing();
                if (235 != this.resp) {
                    closeConnection();
                    throw new AuthenticationFailedException(getLastServerResponse());
                }
                throw th;
            }
        }
    }

    private class LoginAuthenticator extends Authenticator {
        LoginAuthenticator() {
            super("LOGIN");
        }
        public void doAuth(final String str, final String str2, final String str3, final String str4) throws MessagingException, IOException {
            final int simpleCommand = simpleCommand(com.sun.mail.util.BASE64EncoderStream.encode(com.sun.mail.util.ASCIIUtility.getBytes(str3)));
            resp = simpleCommand;
            if (334 == simpleCommand) {
                resp = simpleCommand(com.sun.mail.util.BASE64EncoderStream.encode(com.sun.mail.util.ASCIIUtility.getBytes(str4)));
            }
        }
    }

    private class PlainAuthenticator extends Authenticator {
        PlainAuthenticator() {
            super("PLAIN");
        }


        public String getInitialResponse(final String str, final String str2, final String str3, final String str4) throws MessagingException, IOException {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final com.sun.mail.util.BASE64EncoderStream bASE64EncoderStream = new com.sun.mail.util.BASE64EncoderStream(byteArrayOutputStream, Integer.MAX_VALUE);
            if (null != str2) {
                bASE64EncoderStream.write(com.sun.mail.util.ASCIIUtility.getBytes(str2));
            }
            bASE64EncoderStream.write(0);
            bASE64EncoderStream.write(com.sun.mail.util.ASCIIUtility.getBytes(str3));
            bASE64EncoderStream.write(0);
            bASE64EncoderStream.write(com.sun.mail.util.ASCIIUtility.getBytes(str4));
            bASE64EncoderStream.flush();
            return com.sun.mail.util.ASCIIUtility.toString(byteArrayOutputStream.toByteArray());
        }


        public void doAuth(final String str, final String str2, final String str3, final String str4) throws MessagingException, IOException {
            throw new AuthenticationFailedException("PLAIN asked for more");
        }
    }

    private class DigestMD5Authenticator extends Authenticator {
        private DigestMD5 md5support;

        DigestMD5Authenticator() {
            super("DIGEST-MD5");
        }

        private synchronized DigestMD5 getMD5() {
            try {
                if (null == this.md5support) {
                    md5support = new DigestMD5(logger);
                }
            } catch (final Throwable th) {
                while (true) {
                    throw th;
                }
            }
            return md5support;
        }


        public void doAuth(final String str, final String str2, final String str3, final String str4) throws MessagingException, IOException {
            final DigestMD5 md5 = this.getMD5();
            if (null == md5) {
                resp = -1;
                return;
            }
            final int simpleCommand = simpleCommand(md5.authClient(str, str3, str4, getSASLRealm(), getLastServerResponse()));
            resp = simpleCommand;
            if (334 != simpleCommand) {
                return;
            }
            if (!md5.authServer(getLastServerResponse())) {
                resp = -1;
            } else {
                resp = simpleCommand(new byte[0]);
            }
        }
    }

    private class NtlmAuthenticator extends Authenticator {
        private int flags;
        private Ntlm ntlm;

        NtlmAuthenticator() {
            super("NTLM");
        }


        public String getInitialResponse(final String str, final String str2, final String str3, final String str4) throws MessagingException, IOException {
            ntlm = new Ntlm(getNTLMDomain(), getLocalHost(), str3, str4, logger);
            final Properties properties = session.getProperties();
            String stringBuffer = "mail." +
                    name +
                    ".auth.ntlm.flags";
            final int intProperty = com.sun.mail.util.PropUtil.getIntProperty(properties, stringBuffer, 0);
            flags = intProperty;
            return ntlm.generateType1Msg(intProperty);
        }


        public void doAuth(final String str, final String str2, final String str3, final String str4) throws MessagingException, IOException {
            resp = simpleCommand(ntlm.generateType3Msg(getLastServerResponse().substring(4).trim()));
        }
    }

    public boolean sasllogin(final String[] strArr, final String str, final String str2, final String str3, final String str4) throws MessagingException {
        final ArrayList arrayList;
        final String str5;
        if (null == this.saslAuthenticator) {
            final Class<SMTPSaslAuthenticator> cls = SMTPSaslAuthenticator.class;
            try {
                Class cls2 = SMTPTransport.classcomsunmailsmtpSMTPTransport;
                if (null == cls2) {
                    cls2 =  ("com.sun.mail.smtp.SMTPTransport").getClass();
                    SMTPTransport.classcomsunmailsmtpSMTPTransport = cls2;
                }
                Class cls3 = SMTPTransport.classjavalangString;
                if (null == cls3) {
                    cls3 =  ("java.lang.String").getClass();
                    SMTPTransport.classjavalangString = cls3;
                }
                Class cls4 = SMTPTransport.classjavautilProperties;
                if (null == cls4) {
                    cls4 =  ("java.util.Properties").getClass();
                    SMTPTransport.classjavautilProperties = cls4;
                }
                Class cls5 = SMTPTransport.classcomsunmailutilMailLogger;
                if (null == cls5) {
                    cls5 = ("com.sun.mail.util.MailLogger").getClass();
                    SMTPTransport.classcomsunmailutilMailLogger = cls5;
                }
                Class cls6 = SMTPTransport.classjavalangString;
                if (null == cls6) {
                    cls6 =  ("java.lang.String").getClass();
                    SMTPTransport.classjavalangString = cls6;
                }
                saslAuthenticator = cls.getConstructor(new Class[]{cls2, cls3, cls4, cls5, cls6}).newInstance(this, name, session.getProperties(), logger, host);
            } catch (final Exception e2) {
                logger.log(Level.FINE, "Can't load SASL authenticator", e2);
                return false;
            }
        }
        if (null == strArr || 0 >= strArr.length) {
            arrayList = new ArrayList();
            final Hashtable hashtable = extMap;
            if (!(null == hashtable || null == (str5 = (String) hashtable.get("AUTH")))) {
                final StringTokenizer stringTokenizer = new StringTokenizer(str5);
                while (stringTokenizer.hasMoreTokens()) {
                    arrayList.add(stringTokenizer.nextToken());
                }
            }
        } else {
            arrayList = new ArrayList(strArr.length);
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (this.supportsAuthentication(strArr[i2])) {
                    arrayList.add(strArr[i2]);
                }
            }
        }
        final String[] strArr2 = (String[]) arrayList.toArray(new String[arrayList.size()]);
        try {
            if (noauthdebug && this.isTracing()) {
                logger.fine("SASL AUTH command trace suppressed");
                this.suspendTracing();
            }
            final boolean authenticate = saslAuthenticator.authenticate(strArr2, str, str2, str3, str4);
            this.resumeTracing();
            return authenticate;
        } catch (final Throwable th) {
            this.resumeTracing();
            throw th;
        }
    }

    public synchronized void sendMessage(final Message r19, final Address[] r20) throws MessagingException {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.smtp.SMTPTransport.sendMessage(javax.mail.Message, javax.mail.Address[]):void");
    }

    private void addressesFailed() {
        final Address[] addressArr = validSentAddr;
        if (null != addressArr) {
            final Address[] addressArr2 = validUnsentAddr;
            if (null != addressArr2) {
                final Address[] addressArr3 = new Address[(addressArr.length + addressArr2.length)];
                System.arraycopy(addressArr, 0, addressArr3, 0, addressArr.length);
                final Address[] addressArr4 = validUnsentAddr;
                System.arraycopy(addressArr4, 0, addressArr3, validSentAddr.length, addressArr4.length);
                validSentAddr = null;
                validUnsentAddr = addressArr3;
                return;
            }
            validUnsentAddr = addressArr;
            validSentAddr = null;
        }
    }

    public synchronized void close() throws MessagingException {
        final int readServerResponse;
        if (super.isConnected()) {
            try {
                if (null != this.serverSocket) {
                    this.sendCommand("QUIT");
                    if (quitWait && 221 != (readServerResponse = readServerResponse()) && -1 != readServerResponse && logger.isLoggable(Level.FINE)) {
                        final com.sun.mail.util.MailLogger mailLogger = logger;
                        String stringBuffer = "QUIT failed with " +
                                readServerResponse;
                        mailLogger.fine(stringBuffer);
                    }
                }
            } finally {
                this.closeConnection();
            }
        }
    }


    public void closeConnection() throws MessagingException {
        try {
            final Socket socket = serverSocket;
            if (null != socket) {
                socket.close();
            }
            serverSocket = null;
            serverOutput = null;
            serverInput = null;
            lineInputStream = null;
            if (super.isConnected()) {
                super.close();
            }
        } catch (final IOException e2) {
            throw new MessagingException("Server Close Failed", e2);
        } catch (final Throwable th) {
            serverSocket = null;
            serverOutput = null;
            serverInput = null;
            lineInputStream = null;
            if (super.isConnected()) {
                super.close();
            }
            throw th;
        }
    }

    public synchronized boolean isConnected() {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.smtp.SMTPTransport.isConnected():boolean");
    }
    public void notifyTransportListeners(final int i2, final Address[] addressArr, final Address[] addressArr2, final Address[] addressArr3, final Message message2) {
        if (!notificationDone) {
            super.notifyTransportListeners(i2, addressArr, addressArr2, addressArr3, message2);
            notificationDone = true;
        }
    }

    private void expandGroups() {
        Vector vector = null;
        int i2 = 0;
        while (true) {
            final Address[] addressArr = addresses;
            if (i2 >= addressArr.length) {
                break;
            }
            final InternetAddress internetAddress = (InternetAddress) addressArr[i2];
            if (internetAddress.isGroup()) {
                if (null == vector) {
                    vector = new Vector();
                    for (int i3 = 0; i3 < i2; i3++) {
                        vector.addElement(addresses[i3]);
                    }
                }
                try {
                    final InternetAddress[] group = internetAddress.getGroup(true);
                    if (null != group) {
                        for (final InternetAddress addElement : group) {
                            vector.addElement(addElement);
                        }
                    } else {
                        vector.addElement(internetAddress);
                    }
                } catch (final ParseException unused) {
                    vector.addElement(internetAddress);
                }
            } else if (null != vector) {
                vector.addElement(internetAddress);
            }
            i2++;
        }
        if (null != vector) {
            final InternetAddress[] internetAddressArr = new InternetAddress[vector.size()];
            vector.copyInto(internetAddressArr);
            addresses = internetAddressArr;
        }
    }

    private boolean convertTo8Bit(final javax.mail.internet.MimePart r6) {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.smtp.SMTPTransport.convertTo8Bit(javax.mail.internet.MimePart):boolean");
    }

    private boolean is8Bit(final InputStream inputStream) {
        boolean z = false;
        int i2 = 0;
        while (true) {
            try {
                final int read = inputStream.read();
                if (0 <= read) {
                    final int i3 = read & 255;
                    if (13 == i3 || 10 == i3) {
                        i2 = 0;
                    } else if (0 == i3 || 998 < (i2 = i2 + 1)) {
                        return false;
                    }
                    if (127 < i3) {
                        z = true;
                    }
                } else {
                    if (z) {
                        logger.fine("found an 8bit part");
                    }
                    return z;
                }
            } catch (final IOException unused) {
                return false;
            }
        }
    }


    protected void finalize() throws Throwable {
        super.finalize();
        try {
            this.closeConnection();
        } catch (final MessagingException unused) {
        }
    }


    public void helo(final String str) throws MessagingException {
        if (null != str) {
            String stringBuffer = "HELO " +
                    str;
            this.issueCommand(stringBuffer, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            return;
        }
        this.issueCommand("HELO", ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
    }


    public boolean ehlo(final String str) throws MessagingException {
        final String str2;
        if (null != str) {
            String stringBuffer = "EHLO " +
                    str;
            str2 = stringBuffer;
        } else {
            str2 = "EHLO";
        }
        this.sendCommand(str2);
        final int readServerResponse = this.readServerResponse();
        if (250 == readServerResponse) {
            final BufferedReader bufferedReader = new BufferedReader(new StringReader(lastServerResponse));
            extMap = new Hashtable();
            boolean z = true;
            while (true) {
                try {
                    final String readLine = bufferedReader.readLine();
                    if (null == readLine) {
                        break;
                    } else if (z) {
                        z = false;
                    } else if (5 <= readLine.length()) {
                        String substring = readLine.substring(4);
                        final int indexOf = substring.indexOf(32);
                        String str3 = "";
                        if (0 < indexOf) {
                            str3 = substring.substring(indexOf + 1);
                            substring = substring.substring(0, indexOf);
                        }
                        if (logger.isLoggable(Level.FINE)) {
                            final com.sun.mail.util.MailLogger mailLogger = logger;
                            String stringBuffer2 = "Found extension \"" +
                                    substring +
                                    "\", arg \"" +
                                    str3 +
                                    "\"";
                            mailLogger.fine(stringBuffer2);
                        }
                        extMap.put(substring.toUpperCase(Locale.ENGLISH), str3);
                    }
                } catch (final IOException unused) {
                }
            }
        }
        return 250 == readServerResponse;
    }


    public void mailFrom() throws MessagingException {
        final Address address;
        final Address[] from;
        final MimeMessage mimeMessage = message;
        String str = null;
        String envelopeFrom = mimeMessage instanceof SMTPMessage ? ((SMTPMessage) mimeMessage).getEnvelopeFrom() : null;
        if (null == envelopeFrom || 0 >= envelopeFrom.length()) {
            final Session session = this.session;
            String stringBuffer = "mail." +
                    name +
                    ".from";
            envelopeFrom = session.getProperty(stringBuffer);
        }
        if (null == envelopeFrom || 0 >= envelopeFrom.length()) {
            final MimeMessage mimeMessage2 = message;
            if (null == mimeMessage2 || null == (from = mimeMessage2.getFrom()) || 0 >= from.length) {
                address = InternetAddress.getLocalAddress(session);
            } else {
                address = from[0];
            }
            if (null != address) {
                envelopeFrom = ((InternetAddress) address).getAddress();
            } else {
                throw new MessagingException("can't determine local email address");
            }
        }
        String stringBuffer3 = "MAIL FROM:" +
                this.normalizeAddress(envelopeFrom);
        if (this.supportsExtension("DSN")) {
            final MimeMessage mimeMessage3 = message;
            String dSNRet = mimeMessage3 instanceof SMTPMessage ? ((SMTPMessage) mimeMessage3).getDSNRet() : null;
            if (null == dSNRet) {
                final Session session2 = session;
                String stringBuffer4 = "mail." +
                        name +
                        ".dsn.ret";
                dSNRet = session2.getProperty(stringBuffer4);
            }
            if (null != dSNRet) {
                String stringBuffer5 = stringBuffer3 +
                        " RET=" +
                        dSNRet;
                stringBuffer3 = stringBuffer5;
            }
        }
        if (this.supportsExtension("AUTH")) {
            final MimeMessage mimeMessage4 = message;
            String submitter = mimeMessage4 instanceof SMTPMessage ? ((SMTPMessage) mimeMessage4).getSubmitter() : null;
            if (null == submitter) {
                final Session session3 = session;
                String stringBuffer6 = "mail." +
                        name +
                        ".submitter";
                submitter = session3.getProperty(stringBuffer6);
            }
            if (null != submitter) {
                try {
                    final String xtext = SMTPTransport.xtext(submitter);
                    String stringBuffer7 = stringBuffer3 +
                            " AUTH=" +
                            xtext;
                    stringBuffer3 = stringBuffer7;
                } catch (final IllegalArgumentException e2) {
                    final com.sun.mail.util.MailLogger mailLogger = logger;
                    final Level level = Level.FINE;
                    if (mailLogger.isLoggable(level)) {
                        final com.sun.mail.util.MailLogger mailLogger2 = logger;
                        String stringBuffer8 = "ignoring invalid submitter: " +
                                submitter;
                        mailLogger2.log(level, stringBuffer8, e2);
                    }
                }
            }
        }
        final MimeMessage mimeMessage5 = message;
        if (mimeMessage5 instanceof SMTPMessage) {
            str = ((SMTPMessage) mimeMessage5).getMailExtension();
        }
        if (null == str) {
            final Session session4 = session;
            String stringBuffer9 = "mail." +
                    name +
                    ".mailextension";
            str = session4.getProperty(stringBuffer9);
        }
        if (null != str && 0 < str.length()) {
            String stringBuffer10 = stringBuffer3 +
                    " " +
                    str;
            stringBuffer3 = stringBuffer10;
        }
        try {
            this.issueSendCommand(stringBuffer3, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
        } catch (final SMTPSendFailedException e3) {
            final int returnCode = e3.getReturnCode();
            if (501 == returnCode || 503 == returnCode || 553 == returnCode || 550 == returnCode || 551 == returnCode) {
                try {
                    e3.setNextException(new SMTPSenderFailedException(new InternetAddress(envelopeFrom), stringBuffer3, returnCode, e3.getMessage()));
                } catch (final AddressException unused) {
                }
            }
            throw e3;
        }
    }

    public void rcptTo() throws MessagingException {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.smtp.SMTPTransport.rcptTo():void");
    }

    public SMTPOutputStream data() throws MessagingException {
        if (SMTPTransport.assertionsDisabled || Thread.holdsLock(this)) {
            this.issueSendCommand("DATA", 354);
            final SMTPOutputStream sMTPOutputStream = new SMTPOutputStream(serverOutput);
            dataStream = sMTPOutputStream;
            return sMTPOutputStream;
        }
        throw new AssertionError();
    }


    public void finishData() throws IOException, MessagingException {
        if (SMTPTransport.assertionsDisabled || Thread.holdsLock(this)) {
            dataStream.ensureAtBOL();
            this.issueSendCommand(".", ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            return;
        }
        throw new AssertionError();
    }


    public void startTLS() throws MessagingException {
        this.issueCommand("STARTTLS", 220);
        try {
            final Socket socket = serverSocket;
            final String str = host;
            final Properties properties = session.getProperties();
            String stringBuffer = "mail." +
                    name;
            serverSocket = com.sun.mail.util.SocketFetcher.startTLS(socket, str, properties, stringBuffer);
            this.initStreams();
        } catch (final IOException e2) {
            this.closeConnection();
            throw new MessagingException("Could not convert socket to TLS", e2);
        }
    }

    private void openServer(final String str, int i2) throws MessagingException {
        final com.sun.mail.util.MailLogger mailLogger = logger;
        final Level level = Level.FINE;
        if (mailLogger.isLoggable(level)) {
            final com.sun.mail.util.MailLogger mailLogger2 = logger;
            String stringBuffer = "trying to connect to host \"" +
                    str +
                    "\", port " +
                    i2 +
                    ", isSSL " +
                    isSSL;
            mailLogger2.fine(stringBuffer);
        }
        try {
            final Properties properties = session.getProperties();
            String stringBuffer2 = "mail." +
                    name;
            final Socket socket = com.sun.mail.util.SocketFetcher.getSocket(str, i2, properties, stringBuffer2, isSSL);
            serverSocket = socket;
            i2 = socket.getPort();
            host = str;
            this.initStreams();
            final int readServerResponse = this.readServerResponse();
            if (220 != readServerResponse) {
                serverSocket.close();
                serverSocket = null;
                serverOutput = null;
                serverInput = null;
                lineInputStream = null;
                if (logger.isLoggable(level)) {
                    final com.sun.mail.util.MailLogger mailLogger3 = logger;
                    String stringBuffer3 = "could not connect to host \"" +
                            str +
                            "\", port: " +
                            i2 +
                            ", response: " +
                            readServerResponse +
                            SqlLiteVariable._NEW_LINE;
                    mailLogger3.fine(stringBuffer3);
                }
                String stringBuffer4 = "Could not connect to SMTP host: " +
                        str +
                        ", port: " +
                        i2 +
                        ", response: " +
                        readServerResponse;
                throw new MessagingException(stringBuffer4);
            } else if (logger.isLoggable(level)) {
                final com.sun.mail.util.MailLogger mailLogger4 = logger;
                String stringBuffer5 = "connected to host \"" +
                        str +
                        "\", port: " +
                        i2 +
                        SqlLiteVariable._NEW_LINE;
                mailLogger4.fine(stringBuffer5);
            }
        } catch (final UnknownHostException e2) {
            String stringBuffer6 = "Unknown SMTP host: " +
                    str;
            throw new MessagingException(stringBuffer6, e2);
        } catch (final IOException e3) {
            String stringBuffer7 = "Could not connect to SMTP host: " +
                    str +
                    ", port: " +
                    i2;
            throw new MessagingException(stringBuffer7, e3);
        }
    }

    private void openServer() throws MessagingException {
        host = SMTPTransport.UNKNOWN;
        int i2 = -1;
        try {
            i2 = serverSocket.getPort();
            host = serverSocket.getInetAddress().getHostName();
            final com.sun.mail.util.MailLogger mailLogger = logger;
            final Level level = Level.FINE;
            if (mailLogger.isLoggable(level)) {
                final com.sun.mail.util.MailLogger mailLogger2 = logger;
                String stringBuffer = "starting protocol to host \"" +
                        host +
                        "\", port " +
                        i2;
                mailLogger2.fine(stringBuffer);
            }
            this.initStreams();
            final int readServerResponse = this.readServerResponse();
            if (220 != readServerResponse) {
                serverSocket.close();
                serverSocket = null;
                serverOutput = null;
                serverInput = null;
                lineInputStream = null;
                if (logger.isLoggable(level)) {
                    final com.sun.mail.util.MailLogger mailLogger3 = logger;
                    String stringBuffer2 = "got bad greeting from host \"" +
                            host +
                            "\", port: " +
                            i2 +
                            ", response: " +
                            readServerResponse +
                            SqlLiteVariable._NEW_LINE;
                    mailLogger3.fine(stringBuffer2);
                }
                String stringBuffer3 = "Got bad greeting from SMTP host: " +
                        host +
                        ", port: " +
                        i2 +
                        ", response: " +
                        readServerResponse;
                throw new MessagingException(stringBuffer3);
            } else if (logger.isLoggable(level)) {
                final com.sun.mail.util.MailLogger mailLogger4 = logger;
                String stringBuffer4 = "protocol started to host \"" +
                        host +
                        "\", port: " +
                        i2 +
                        SqlLiteVariable._NEW_LINE;
                mailLogger4.fine(stringBuffer4);
            }
        } catch (final IOException e2) {
            String stringBuffer5 = "Could not start protocol to SMTP host: " +
                    host +
                    ", port: " +
                    i2;
            throw new MessagingException(stringBuffer5, e2);
        }
    }

    private void initStreams() throws IOException {
        final boolean booleanSessionProperty = PropUtil.getBooleanSessionProperty(session, "mail.debug.quote", false);
        final com.sun.mail.util.TraceInputStream traceInputStream = new com.sun.mail.util.TraceInputStream(serverSocket.getInputStream(), traceLogger);
        traceInput = traceInputStream;
        traceInputStream.setQuote(booleanSessionProperty);
        final com.sun.mail.util.TraceOutputStream traceOutputStream = new com.sun.mail.util.TraceOutputStream(serverSocket.getOutputStream(), traceLogger);
        traceOutput = traceOutputStream;
        traceOutputStream.setQuote(booleanSessionProperty);
        serverOutput = new BufferedOutputStream(traceOutput);
        serverInput = new BufferedInputStream(traceInput);
        lineInputStream = new com.sun.mail.util.LineInputStream(serverInput);
    }

    public boolean isTracing() {
        return traceLogger.isLoggable(Level.FINEST);
    }


    public void suspendTracing() {
        if (traceLogger.isLoggable(Level.FINEST)) {
            traceInput.setTrace(false);
            traceOutput.setTrace(false);
        }
    }


    public void resumeTracing() {
        if (traceLogger.isLoggable(Level.FINEST)) {
            traceInput.setTrace(true);
            traceOutput.setTrace(true);
        }
    }

    public synchronized void issueCommand(final String str, final int i2) throws MessagingException {
        this.sendCommand(str);
        final int readServerResponse = this.readServerResponse();
        if (-1 != i2) {
            if (readServerResponse != i2) {
                throw new MessagingException(lastServerResponse);
            }
        }
    }

    private void issueSendCommand(final String str, final int i2) throws MessagingException {
        this.sendCommand(str);
        final int readServerResponse = this.readServerResponse();
        if (readServerResponse != i2) {
            final Address[] addressArr = validSentAddr;
            final int length = null == addressArr ? 0 : addressArr.length;
            final Address[] addressArr2 = validUnsentAddr;
            final int length2 = null == addressArr2 ? 0 : addressArr2.length;
            final Address[] addressArr3 = new Address[(length + length2)];
            if (0 < length) {
                System.arraycopy(addressArr, 0, addressArr3, 0, length);
            }
            if (0 < length2) {
                System.arraycopy(validUnsentAddr, 0, addressArr3, length, length2);
            }
            validSentAddr = null;
            validUnsentAddr = addressArr3;
            if (logger.isLoggable(Level.FINE)) {
                final com.sun.mail.util.MailLogger mailLogger = logger;
                String stringBuffer = "got response code " +
                        readServerResponse +
                        ", with response: " +
                        lastServerResponse;
                mailLogger.fine(stringBuffer);
            }
            final String str2 = lastServerResponse;
            final int i3 = lastReturnCode;
            if (null != this.serverSocket) {
                this.issueCommand("RSET", -1);
            }
            lastServerResponse = str2;
            lastReturnCode = i3;
            throw new SMTPSendFailedException(str, readServerResponse, lastServerResponse, exception, validSentAddr, validUnsentAddr, invalidAddr);
        }
    }

    public synchronized int simpleCommand(final String str) throws MessagingException {
        this.sendCommand(str);
        return this.readServerResponse();
    }


    public int simpleCommand(final byte[] bArr) throws MessagingException {
        if (SMTPTransport.assertionsDisabled || Thread.holdsLock(this)) {
            this.sendCommand(bArr);
            return this.readServerResponse();
        }
        throw new AssertionError();
    }


    public void sendCommand(final String str) throws MessagingException {
        this.sendCommand(com.sun.mail.util.ASCIIUtility.getBytes(str));
    }

    private void sendCommand(final byte[] bArr) throws MessagingException {
        if (SMTPTransport.assertionsDisabled || Thread.holdsLock(this)) {
            try {
                serverOutput.write(bArr);
                serverOutput.write(SMTPTransport.CRLF);
                serverOutput.flush();
            } catch (final IOException e2) {
                throw new MessagingException("Can't send command to SMTP host", e2);
            }
        } else {
            throw new AssertionError();
        }
    }

    public int readServerResponse() throws MessagingException {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.smtp.SMTPTransport.readServerResponse():int");
    }

    public void checkConnected() {
        if (!super.isConnected()) {
            throw new IllegalStateException("Not connected");
        }
    }

    private boolean isNotLastLine(final String str) {
        return null != str && 4 <= str.length() && '-' == str.charAt(3);
    }

    private String normalizeAddress(final String str) {
        if (str.startsWith("<") || str.endsWith(">")) {
            return str;
        }
        String stringBuffer = "<" +
                str +
                ">";
        return stringBuffer;
    }

    public boolean supportsExtension(final String str) {
        final Hashtable hashtable = extMap;
        return null != hashtable && null != hashtable.get(str.toUpperCase(Locale.ENGLISH));
    }

    public String getExtensionParameter(final String str) {
        final Hashtable hashtable = extMap;
        if (null == hashtable) {
            return null;
        }
        return (String) hashtable.get(str.toUpperCase(Locale.ENGLISH));
    }

    
    public boolean supportsAuthentication(final String str) {
        final String str2;
        if (SMTPTransport.assertionsDisabled || Thread.holdsLock(this)) {
            final Hashtable hashtable = extMap;
            if (null == hashtable || null == (str2 = (String) hashtable.get("AUTH"))) {
                return false;
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(str2);
            while (stringTokenizer.hasMoreTokens()) {
                if (stringTokenizer.nextToken().equalsIgnoreCase(str)) {
                    return true;
                }
            }
            if (!"LOGIN".equalsIgnoreCase(str) || !this.supportsExtension("AUTH=LOGIN")) {
                return false;
            }
            logger.fine("use AUTH=LOGIN hack");
            return true;
        }
        throw new AssertionError();
    }

    protected static String xtext(final String str) {
        StringBuffer stringBuffer = null;
        int i2 = 0;
        while (i2 < str.length()) {
            final char charAt = str.charAt(i2);
            if (128 > charAt) {
                if ('!' > charAt || '~' < charAt || '+' == charAt || '=' == charAt) {
                    if (null == stringBuffer) {
                        stringBuffer = new StringBuffer(str.length() + 4);
                        stringBuffer.append(str, 0, i2);
                    }
                    stringBuffer.append('+');
                    stringBuffer.append(SMTPTransport.hexchar[(charAt & 240) >> 4]);
                    stringBuffer.append(SMTPTransport.hexchar[charAt & 15]);
                } else if (null != stringBuffer) {
                    stringBuffer.append(charAt);
                }
                i2++;
            } else {
                String stringBuffer2 = "Non-ASCII character in SMTP submitter: " +
                        str;
                throw new IllegalArgumentException(stringBuffer2);
            }
        }
        return null != stringBuffer ? stringBuffer.toString() : str;
    }
}
