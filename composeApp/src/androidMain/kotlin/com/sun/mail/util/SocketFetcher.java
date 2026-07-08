package com.sun.mail.util;

import androidx.webkit.ProxyConfig;

import javax.net.SocketFactory;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.AccessController;
import java.security.GeneralSecurityException;
import java.security.PrivilegedAction;
import java.security.cert.Certificate;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocketFetcher {
    private static final String SOCKS_SUPPORT = "com.sun.mail.util.SocksSupport";
    static Class classcomsunmailutilSocketFetcher;
    static Class classjavalangString;
    static Class classjavasecuritycertX509Certificate;
    private static final MailLogger logger;
    static {
        Class cls = SocketFetcher.classcomsunmailutilSocketFetcher;
        if (null == cls) {
            cls =  ("com.sun.mail.util.SocketFetcher").getClass()   ;
            SocketFetcher.classcomsunmailutilSocketFetcher = cls;
        }
        logger = new MailLogger(cls, "socket", "DEBUG SocketFetcher", PropUtil.getBooleanSystemProperty("mail.socket.debug", false), System.out);
    }
    public static Socket getSocket(final String r23, final int r24, final Properties r25, final String r26, final boolean r27) throws IOException {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.util.SocketFetcher.getSocket(java.lang.String, int, java.util.Properties, java.lang.String, boolean):java.net.Socket");
    }
    public static Socket getSocket(final String str, final int i2, final Properties properties, final String str2) throws IOException {
        return SocketFetcher.getSocket(str, i2, properties, str2, false);
    }
    private static Socket createSocket(final InetAddress inetAddress, final int i2, final String str, final int i3, final int i4, final int i5, final Properties properties, final String str2, final SocketFactory socketFactory, final boolean z) throws IOException {
        final SSLSocketFactory sSLSocketFactory;
        final MailSSLSocketFactory mailSSLSocketFactory;
        final InetAddress inetAddress2 = inetAddress;
        final String str3 = str;
        final int i6 = i3;
        final int i7 = i4;
        final int i8 = i5;
        final Properties properties2 = properties;
        final String str4 = str2;
        String stringBuffer = str4 +
                ".socks.host";
        Class cls = null;
        String property = properties2.getProperty(stringBuffer, null);
        int i9 = 1080;
        if (null != property) {
            final int indexOf = property.indexOf(58);
            if (0 <= indexOf) {
                property = property.substring(0, indexOf);
                try {
                    i9 = Integer.parseInt(property.substring(indexOf + 1));
                } catch (final NumberFormatException unused) {
                }
            }
            String stringBuffer2 = str4 +
                    ".socks.port";
            i9 = PropUtil.getIntProperty(properties2, stringBuffer2, i9);
            if (SocketFetcher.logger.isLoggable(Level.FINER)) {
                final MailLogger mailLogger = SocketFetcher.logger;
                String stringBuffer3 = "socks host " +
                        property +
                        ", port " +
                        i9;
                mailLogger.finer(stringBuffer3);
            }
        }
        Socket createSocket = null != socketFactory ? socketFactory.createSocket() : null;
        if (null == createSocket) {
            if (null != property) {
                try {
                    final ClassLoader contextClassLoader = SocketFetcher.getContextClassLoader();
                    if (null != contextClassLoader) {
                        try {
                            cls = Class.forName(SocketFetcher.SOCKS_SUPPORT, false, contextClassLoader);
                        } catch (final Exception unused2) {
                        }
                    }
                    if (null == cls) {
                        cls = SocksSupport.class;
                    }
                    Class cls2 = SocketFetcher.classjavalangString;
                    if (null == cls2) {
                        cls2 =  ("java.lang.String").getClass();
                        SocketFetcher.classjavalangString = cls2;
                    }
                    createSocket = (Socket) cls.getMethod("getSocket", new Class[]{cls2, Integer.TYPE}).invoke(new Object(), new Object[]{property, Integer.valueOf(i9)});
                } catch (final Exception e2) {
                    SocketFetcher.logger.log(Level.FINER, "failed to load ProxySupport class", e2);
                }
            }
            if (null == createSocket) {
                createSocket = new Socket();
            }
        }
        if (0 <= i8) {
            createSocket.setSoTimeout(i8);
        }
        if (null != inetAddress2) {
            createSocket.bind(new InetSocketAddress(inetAddress, i2));
        }
        if (0 <= i7) {
            createSocket.connect(new InetSocketAddress(str3, i6), i7);
        } else {
            createSocket.connect(new InetSocketAddress(str3, i6));
        }
        if (!z || (createSocket instanceof SSLSocket)) {
            sSLSocketFactory = ( SSLSocketFactory ) socketFactory;
        } else {
            String stringBuffer4 = str4 +
                    ".ssl.trust";
            final String property2 = properties2.getProperty(stringBuffer4);
            if (null != property2) {
                try {
                    final MailSSLSocketFactory mailSSLSocketFactory2 = new MailSSLSocketFactory();
                    if (property2.equals(ProxyConfig.MATCH_ALL_SCHEMES)) {
                        mailSSLSocketFactory2.setTrustAllHosts(true);
                    } else {
                        mailSSLSocketFactory2.setTrustedHosts(property2.split("\\s+"));
                    }
                    mailSSLSocketFactory = mailSSLSocketFactory2;
                } catch (final GeneralSecurityException e3) {
                    final IOException iOException = new IOException("Can't create MailSSLSocketFactory", e3);
                    throw iOException;
                }
            } else {
                mailSSLSocketFactory = ( MailSSLSocketFactory ) SSLSocketFactory.getDefault();
            }
            createSocket = mailSSLSocketFactory.createSocket(createSocket, str3, i6, true);
            sSLSocketFactory = mailSSLSocketFactory;
        }
        SocketFetcher.configureSSLSocket(createSocket, str3, properties2, str4, sSLSocketFactory);
        return createSocket;
    }
    private static SocketFactory getSocketFactory(final String r3) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.util.SocketFetcher.getSocketFactory(java.lang.String):javax.net.SocketFactory");
    }
    public static Socket startTLS(final Socket socket) throws IOException {
        return SocketFetcher.startTLS(socket, new Properties(), "socket");
    }
    public static Socket startTLS(final Socket socket, final Properties properties, final String str) throws IOException {
        return SocketFetcher.startTLS(socket, socket.getInetAddress().getHostName(), properties, str);
    }
    public static Socket startTLS(final Socket r7, final String r8, final Properties r9, final String r10) throws IOException {

        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.util.SocketFetcher.startTLS(java.net.Socket, java.lang.String, java.util.Properties, java.lang.String):java.net.Socket");
    }
    private static void configureSSLSocket(final Socket socket, final String str, final Properties properties, final String str2, final SocketFactory socketFactory) throws IOException {
        String stringBuffer = "";
        if (socket instanceof SSLSocket sSLSocket) {
            String stringBuffer2 = str2 +
                    ".ssl.protocols";
            final String property = properties.getProperty(stringBuffer2, null);
            if (null != property) {
                sSLSocket.setEnabledProtocols(SocketFetcher.stringArray(property));
            } else {
                sSLSocket.setEnabledProtocols(new String[]{"TLSv1"});
            }
            String stringBuffer3 = str2 +
                    ".ssl.ciphersuites";
            final String property2 = properties.getProperty(stringBuffer3, null);
            if (null != property2) {
                sSLSocket.setEnabledCipherSuites(SocketFetcher.stringArray(property2));
            }
            if (SocketFetcher.logger.isLoggable(Level.FINER)) {
                final MailLogger mailLogger = SocketFetcher.logger;
                String stringBuffer4 = "SSL protocols after " +
                        Arrays.asList(sSLSocket.getEnabledProtocols());
                mailLogger.finer(stringBuffer4);
                final MailLogger mailLogger2 = SocketFetcher.logger;
                String stringBuffer5 = "SSL ciphers after " +
                        Arrays.asList(sSLSocket.getEnabledCipherSuites());
                mailLogger2.finer(stringBuffer5);
            }
            sSLSocket.startHandshake();
            final StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append(str2);
            stringBuffer6.append(".ssl.checkserveridentity");
            if (PropUtil.getBooleanProperty(properties, stringBuffer6.toString(), false)) {
                SocketFetcher.checkServerIdentity(str, sSLSocket);
            }
            if ((socketFactory instanceof MailSSLSocketFactory) && !((MailSSLSocketFactory) socketFactory).isServerTrusted(str, sSLSocket)) {
                try {
                    sSLSocket.close();
                    throw new IOException(stringBuffer);
                } finally {
                    String stringBuffer7 = "Server is not trusted: " +
                            str;
                    final IOException iOException = new IOException(stringBuffer7);
                }
            }
        }
    }
    private static void checkServerIdentity(final String str, final SSLSocket sSLSocket) throws IOException {
        try {
            final Certificate[] peerCertificates = sSLSocket.getSession().getPeerCertificates();
            if (null != peerCertificates && 0 < peerCertificates.length) {
                final Certificate certificate = peerCertificates[0];
                if ((certificate instanceof X509Certificate) && SocketFetcher.matchCert(str, (X509Certificate) certificate)) {
                    return;
                }
            }
            sSLSocket.close();
            String stringBuffer = "Can't verify identity of server: " +
                    str;
            throw new IOException(stringBuffer);
        } catch (final SSLPeerUnverifiedException e2) {
            sSLSocket.close();
            String stringBuffer2 = "Can't verify identity of server: " +
                    str;
            final IOException iOException = new IOException(stringBuffer2, e2);
            throw iOException;
        }
    }
    private static boolean matchCert(final String str, final X509Certificate x509Certificate) {
        final MailLogger mailLogger = SocketFetcher.logger;
        final Level level = Level.FINER;
        if (mailLogger.isLoggable(level)) {
            final MailLogger mailLogger2 = SocketFetcher.logger;
            String stringBuffer = "matchCert server " +
                    str +
                    ", cert " +
                    x509Certificate;
            mailLogger2.finer(stringBuffer);
        }
        try {
            final Class<?> cls = Class.forName("sun.security.util.HostnameChecker");
            final Object invoke = cls.getMethod("getInstance", new Class[]{Byte.TYPE}).invoke(new Object(), Byte.valueOf((byte) 2));
            if (SocketFetcher.logger.isLoggable(level)) {
                SocketFetcher.logger.finer("using sun.security.util.HostnameChecker");
            }
            Class cls2 = SocketFetcher.classjavalangString;
            if (null == cls2) {
                cls2 = ("java.lang.String").getClass();
                SocketFetcher.classjavalangString = cls2;
            }
            Class cls3 = SocketFetcher.classjavasecuritycertX509Certificate;
            if (null == cls3) {
                cls3 = ("java.security.cert.X509Certificate").getClass();
                SocketFetcher.classjavasecuritycertX509Certificate = cls3;
            }
            try {
                cls.getMethod("match", new Class[]{cls2, cls3}).invoke(invoke, str, x509Certificate);
                return true;
            } catch (final InvocationTargetException e2) {
                SocketFetcher.logger.log(Level.FINER, "FAIL", e2);
                return false;
            }
        } catch (final Exception e3) {
            SocketFetcher.logger.log(Level.FINER, "NO sun.security.util.HostnameChecker", e3);
            try {
                final Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
                if (null != subjectAlternativeNames) {
                    boolean z = false;
                    for (final List next : subjectAlternativeNames) {
                        if (2 == ((Integer) next.get(0)).intValue()) {
                            final String str2 = (String) next.get(1);
                            if (SocketFetcher.logger.isLoggable(Level.FINER)) {
                                final MailLogger mailLogger3 = SocketFetcher.logger;
                                String stringBuffer2 = "found name: " +
                                        str2;
                                mailLogger3.finer(stringBuffer2);
                            }
                            if (SocketFetcher.matchServer(str, str2)) {
                                return true;
                            }
                            z = true;
                        }
                    }
                    if (z) {
                        return false;
                    }
                }
            } catch (final CertificateParsingException unused) {
            }
            final Matcher matcher = Pattern.compile("CN=([^,]*)").matcher(x509Certificate.getSubjectX500Principal().getName());
            return matcher.find() && SocketFetcher.matchServer(str, matcher.group(1).trim());
        }
    }
    private static boolean matchServer(final String str, final String str2) {
        final int length;
        if (SocketFetcher.logger.isLoggable(Level.FINER)) {
            final MailLogger mailLogger = SocketFetcher.logger;
            String stringBuffer = "match server " +
                    str +
                    " with " +
                    str2;
            mailLogger.finer(stringBuffer);
        }
        if (!str2.startsWith("*.")) {
            return str.equalsIgnoreCase(str2);
        }
        final String substring = str2.substring(2);
        if (0 == substring.length() || 1 > (length = str.length() - substring.length()) || '.' != str.charAt(length - 1)) {
            return false;
        }
        return str.regionMatches(true, length, substring, 0, substring.length());
    }
    private static String[] stringArray(final String str) {
        final StringTokenizer stringTokenizer = new StringTokenizer(str);
        final ArrayList arrayList = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            arrayList.add(stringTokenizer.nextToken());
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
    private static ClassLoader getContextClassLoader() {
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    return Thread.currentThread().getContextClassLoader();
                } catch (final SecurityException unused) {
                    return null;
                }
            }
        });
    }
}
