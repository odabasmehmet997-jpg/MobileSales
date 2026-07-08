package com.sun.mail.util;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

public class MailSSLSocketFactory extends SSLSocketFactory {
    private SSLSocketFactory adapteeFactory;
    private KeyManager[] keyManagers;
    private SecureRandom secureRandom;
    private final SSLContext sslcontext;
    private boolean trustAllHosts;
    private TrustManager[] trustManagers;
    private String[] trustedHosts;

    public MailSSLSocketFactory() throws GeneralSecurityException {
        this("TLS");
    }

    public MailSSLSocketFactory(String str) throws GeneralSecurityException {
        this.trustedHosts = null;
        this.adapteeFactory = null;
        this.trustAllHosts = false;
        this.sslcontext = SSLContext.getInstance(str);
        this.keyManagers = null;
        this.trustManagers = new TrustManager[]{new MailTrustManager()};
        this.secureRandom = null;
        newAdapteeFactory();
    }

    private synchronized void newAdapteeFactory() throws KeyManagementException {
        this.sslcontext.init(this.keyManagers, this.trustManagers, this.secureRandom);
        this.adapteeFactory = this.sslcontext.getSocketFactory();
    }

    public synchronized KeyManager[] getKeyManagers() {
        return this.keyManagers.clone();
    }

    public synchronized void setKeyManagers(KeyManager[] keyManagerArr) throws GeneralSecurityException {
        this.keyManagers = keyManagerArr.clone();
        newAdapteeFactory();
    }

    public synchronized SecureRandom getSecureRandom() {
        return this.secureRandom;
    }

    public synchronized void setSecureRandom(SecureRandom secureRandom2) throws GeneralSecurityException {
        this.secureRandom = secureRandom2;
        newAdapteeFactory();
    }

    public synchronized TrustManager[] getTrustManagers() {
        return this.trustManagers;
    }

    public synchronized void setTrustManagers(TrustManager[] trustManagerArr) throws GeneralSecurityException {
        this.trustManagers = trustManagerArr;
        newAdapteeFactory();
    }

    public synchronized boolean isTrustAllHosts() {
        return this.trustAllHosts;
    }

    public synchronized void setTrustAllHosts(boolean z) {
        this.trustAllHosts = z;
    }

    public synchronized String[] getTrustedHosts() {
        return this.trustedHosts.clone();
    }

    public synchronized void setTrustedHosts(String[] strArr) {
        this.trustedHosts = strArr.clone();
    }

    public synchronized boolean isServerTrusted(String str, SSLSocket sSLSocket) {
        if (this.trustAllHosts) {
            return true;
        }
        String[] strArr = this.trustedHosts;
        if (null == strArr) {
            return true;
        }
        return Arrays.asList(strArr).contains(str);
    }

    public synchronized Socket createSocket(Socket socket, String str, int i2, boolean z) throws IOException {
        return this.adapteeFactory.createSocket(socket, str, i2, z);
    }

    public synchronized String[] getDefaultCipherSuites() {
        return this.adapteeFactory.getDefaultCipherSuites();
    }

    public synchronized String[] getSupportedCipherSuites() {
        return this.adapteeFactory.getSupportedCipherSuites();
    }

    public synchronized Socket createSocket() throws IOException {
        return this.adapteeFactory.createSocket();
    }

    public synchronized Socket createSocket(InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3) throws IOException {
        return this.adapteeFactory.createSocket(inetAddress, i2, inetAddress2, i3);
    }

    public synchronized Socket createSocket(InetAddress inetAddress, int i2) throws IOException {
        return this.adapteeFactory.createSocket(inetAddress, i2);
    }

    public synchronized Socket createSocket(String str, int i2, InetAddress inetAddress, int i3) throws IOException {
        return this.adapteeFactory.createSocket(str, i2, inetAddress, i3);
    }

    public synchronized Socket createSocket(String str, int i2) throws IOException {
        return this.adapteeFactory.createSocket(str, i2);
    }

    private class MailTrustManager implements X509TrustManager {
        private X509TrustManager adapteeTrustManager;

        private MailTrustManager() throws GeneralSecurityException {
            this.adapteeTrustManager = null;
            TrustManagerFactory instance = TrustManagerFactory.getInstance("X509");
            instance.init((KeyStore) null);
            this.adapteeTrustManager = (X509TrustManager) instance.getTrustManagers()[0];
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            if (!MailSSLSocketFactory.this.isTrustAllHosts() && null == getTrustedHosts()) {
                this.adapteeTrustManager.checkClientTrusted(x509CertificateArr, str);
            }
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            if (!MailSSLSocketFactory.this.isTrustAllHosts() && null == getTrustedHosts()) {
                this.adapteeTrustManager.checkServerTrusted(x509CertificateArr, str);
            }
        }

        public X509Certificate[] getAcceptedIssuers() {
            return this.adapteeTrustManager.getAcceptedIssuers();
        }
    }
}
