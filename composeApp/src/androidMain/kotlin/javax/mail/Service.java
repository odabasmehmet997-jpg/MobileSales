package javax.mail;

import java.util.Vector;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.MailEvent;

public abstract class Service {
    private boolean connected;
    private final Vector connectionListeners = new Vector();
    protected boolean debug;
    private EventQueue q;
    private final Object qLock = new Object();
    
    public Session session;
    protected URLName url;
    public boolean protocolConnect(String str, int i2, String str2, String str3) throws MessagingException {
        return false;
    }
    protected Service(Session session2, URLName uRLName) {
        this.session = session2;
        this.url = uRLName;
        this.debug = session2.getDebug();
    }
    public void connect() throws MessagingException {
        connect((String) null, (String) null, (String) null);
    }
    public void connect(String str, String str2, String str3) throws MessagingException {
        connect(str, -1, str2, str3);
    }
    public void connect(String str, String str2) throws MessagingException {
        connect((String) null, str, str2);
    }
    public synchronized void connect(String r18, int r19, String r20, String r21) throws MessagingException {
        /*
            r17 = this;
            r1 = r17
            r0 = r20
            monitor-enter(r17)
            boolean r2 = r17.isConnected()     // Catch:{ all -> 0x001c }
            if (r2 != 0) goto L_0x0194
            javax.mail.URLName r2 = r1.url     // Catch:{ all -> 0x001c }
            if (r2 == 0) goto L_0x0061
            java.lang.String r2 = r2.getProtocol()     // Catch:{ all -> 0x001c }
            if (r18 != 0) goto L_0x001f
            javax.mail.URLName r4 = r1.url     // Catch:{ all -> 0x001c }
            java.lang.String r4 = r4.getHost()     // Catch:{ all -> 0x001c }
            goto L_0x0021
        L_0x001c:
            r0 = move-exception
            goto L_0x019c
        L_0x001f:
            r4 = r18
        L_0x0021:
            r5 = -1
            r6 = r19
            if (r6 != r5) goto L_0x002d
            javax.mail.URLName r5 = r1.url     // Catch:{ all -> 0x001c }
            int r5 = r5.getPort()     // Catch:{ all -> 0x001c }
            goto L_0x002e
        L_0x002d:
            r5 = r6
        L_0x002e:
            if (r0 != 0) goto L_0x0042
            javax.mail.URLName r0 = r1.url     // Catch:{ all -> 0x001c }
            java.lang.String r0 = r0.getUsername()     // Catch:{ all -> 0x001c }
            if (r21 != 0) goto L_0x003f
            javax.mail.URLName r6 = r1.url     // Catch:{ all -> 0x001c }
            java.lang.String r6 = r6.getPassword()     // Catch:{ all -> 0x001c }
            goto L_0x0056
        L_0x003f:
            r6 = r21
            goto L_0x0056
        L_0x0042:
            if (r21 != 0) goto L_0x003f
            javax.mail.URLName r6 = r1.url     // Catch:{ all -> 0x001c }
            java.lang.String r6 = r6.getUsername()     // Catch:{ all -> 0x001c }
            boolean r6 = r0.equals(r6)     // Catch:{ all -> 0x001c }
            if (r6 == 0) goto L_0x003f
            javax.mail.URLName r6 = r1.url     // Catch:{ all -> 0x001c }
            java.lang.String r6 = r6.getPassword()     // Catch:{ all -> 0x001c }
        L_0x0056:
            javax.mail.URLName r7 = r1.url     // Catch:{ all -> 0x001c }
            java.lang.String r7 = r7.getFile()     // Catch:{ all -> 0x001c }
            r13 = r2
            r2 = r5
            r12 = r6
            r14 = r7
            goto L_0x006a
        L_0x0061:
            r6 = r19
            r4 = r18
            r12 = r21
            r2 = r6
            r13 = 0
            r14 = 0
        L_0x006a:
            if (r13 == 0) goto L_0x00a8
            if (r4 != 0) goto L_0x008a
            javax.mail.Session r4 = r1.session     // Catch:{ all -> 0x001c }
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch:{ all -> 0x001c }
            r5.<init>()     // Catch:{ all -> 0x001c }
            java.lang.String r6 = "mail."
            r5.append(r6)     // Catch:{ all -> 0x001c }
            r5.append(r13)     // Catch:{ all -> 0x001c }
            java.lang.String r6 = ".host"
            r5.append(r6)     // Catch:{ all -> 0x001c }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x001c }
            java.lang.String r4 = r4.getProperty(r5)     // Catch:{ all -> 0x001c }
        L_0x008a:
            if (r0 != 0) goto L_0x00a8
            javax.mail.Session r0 = r1.session     // Catch:{ all -> 0x001c }
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch:{ all -> 0x001c }
            r5.<init>()     // Catch:{ all -> 0x001c }
            java.lang.String r6 = "mail."
            r5.append(r6)     // Catch:{ all -> 0x001c }
            r5.append(r13)     // Catch:{ all -> 0x001c }
            java.lang.String r6 = ".user"
            r5.append(r6)     // Catch:{ all -> 0x001c }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x001c }
            java.lang.String r0 = r0.getProperty(r5)     // Catch:{ all -> 0x001c }
        L_0x00a8:
            if (r4 != 0) goto L_0x00b2
            javax.mail.Session r4 = r1.session     // Catch:{ all -> 0x001c }
            java.lang.String r5 = "mail.host"
            java.lang.String r4 = r4.getProperty(r5)     // Catch:{ all -> 0x001c }
        L_0x00b2:
            if (r0 != 0) goto L_0x00bc
            javax.mail.Session r0 = r1.session     // Catch:{ all -> 0x001c }
            java.lang.String r5 = "mail.user"
            java.lang.String r0 = r0.getProperty(r5)     // Catch:{ all -> 0x001c }
        L_0x00bc:
            r5 = r0
            if (r5 != 0) goto L_0x00c5
            java.lang.String r0 = "user.name"
            java.lang.String r5 = java.lang.System.getProperty(r0)     // Catch:{ SecurityException -> 0x00c7 }
        L_0x00c5:
            r0 = r5
            goto L_0x00d6
        L_0x00c7:
            r0 = move-exception
            boolean r6 = r1.debug     // Catch:{ all -> 0x001c }
            if (r6 == 0) goto L_0x00c5
            javax.mail.Session r6 = r1.session     // Catch:{ all -> 0x001c }
            java.io.PrintStream r6 = r6.getDebugOut()     // Catch:{ all -> 0x001c }
            r0.printStackTrace(r6)     // Catch:{ all -> 0x001c }
            goto L_0x00c5
        L_0x00d6:
            r11 = 1
            if (r12 != 0) goto L_0x011e
            javax.mail.URLName r5 = r1.url     // Catch:{ all -> 0x001c }
            if (r5 == 0) goto L_0x011e
            javax.mail.URLName r10 = new javax.mail.URLName     // Catch:{ all -> 0x001c }
            r16 = 0
            r5 = r10
            r6 = r13
            r7 = r4
            r8 = r2
            r9 = r14
            r3 = r10
            r10 = r0
            r15 = r11
            r11 = r16
            r5.<init>(r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x001c }
            r1.setURLName(r3)     // Catch:{ all -> 0x001c }
            javax.mail.Session r3 = r1.session     // Catch:{ all -> 0x001c }
            javax.mail.URLName r5 = r17.getURLName()     // Catch:{ all -> 0x001c }
            javax.mail.PasswordAuthentication r3 = r3.getPasswordAuthentication(r5)     // Catch:{ all -> 0x001c }
            if (r3 == 0) goto L_0x011a
            if (r0 != 0) goto L_0x010b
            java.lang.String r0 = r3.getUserName()     // Catch:{ all -> 0x001c }
            java.lang.String r12 = r3.getPassword()     // Catch:{ all -> 0x001c }
        L_0x0107:
            r3 = r0
            r11 = r12
            r12 = 0
            goto L_0x0120
        L_0x010b:
            java.lang.String r5 = r3.getUserName()     // Catch:{ all -> 0x001c }
            boolean r5 = r0.equals(r5)     // Catch:{ all -> 0x001c }
            if (r5 == 0) goto L_0x0107
            java.lang.String r12 = r3.getPassword()     // Catch:{ all -> 0x001c }
            goto L_0x0107
        L_0x011a:
            r3 = r0
            r11 = r12
            r12 = r15
            goto L_0x0120
        L_0x011e:
            r15 = r11
            goto L_0x0107
        L_0x0120:
            boolean r0 = r1.protocolConnect(r4, r2, r3, r11)     // Catch:{ AuthenticationFailedException -> 0x0127 }
            r16 = 0
            goto L_0x012b
        L_0x0127:
            r0 = move-exception
            r16 = r0
            r0 = 0
        L_0x012b:
            if (r0 != 0) goto L_0x014c
            java.net.InetAddress r5 = java.net.InetAddress.getByName(r4)     // Catch:{ UnknownHostException -> 0x0133 }
            r6 = r5
            goto L_0x0134
        L_0x0133:
            r6 = 0
        L_0x0134:
            javax.mail.Session r5 = r1.session     // Catch:{ all -> 0x001c }
            r9 = 0
            r7 = r2
            r8 = r13
            r10 = r3
            javax.mail.PasswordAuthentication r5 = r5.requestPasswordAuthentication(r6, r7, r8, r9, r10)     // Catch:{ all -> 0x001c }
            if (r5 == 0) goto L_0x014c
            java.lang.String r3 = r5.getUserName()     // Catch:{ all -> 0x001c }
            java.lang.String r11 = r5.getPassword()     // Catch:{ all -> 0x001c }
            boolean r0 = r1.protocolConnect(r4, r2, r3, r11)     // Catch:{ all -> 0x001c }
        L_0x014c:
            if (r0 != 0) goto L_0x016d
            if (r16 != 0) goto L_0x016c
            if (r3 == 0) goto L_0x0164
            if (r11 != 0) goto L_0x015c
            javax.mail.AuthenticationFailedException r0 = new javax.mail.AuthenticationFailedException     // Catch:{ all -> 0x001c }
            java.lang.String r2 = "failed to connect, no password specified?"
            r0.<init>(r2)     // Catch:{ all -> 0x001c }
            throw r0     // Catch:{ all -> 0x001c }
        L_0x015c:
            javax.mail.AuthenticationFailedException r0 = new javax.mail.AuthenticationFailedException     // Catch:{ all -> 0x001c }
            java.lang.String r2 = "failed to connect"
            r0.<init>(r2)     // Catch:{ all -> 0x001c }
            throw r0     // Catch:{ all -> 0x001c }
        L_0x0164:
            javax.mail.AuthenticationFailedException r0 = new javax.mail.AuthenticationFailedException     // Catch:{ all -> 0x001c }
            java.lang.String r2 = "failed to connect, no user name specified?"
            r0.<init>(r2)     // Catch:{ all -> 0x001c }
            throw r0     // Catch:{ all -> 0x001c }
        L_0x016c:
            throw r16     // Catch:{ all -> 0x001c }
        L_0x016d:
            javax.mail.URLName r0 = new javax.mail.URLName     // Catch:{ all -> 0x001c }
            r5 = r0
            r6 = r13
            r7 = r4
            r8 = r2
            r9 = r14
            r10 = r3
            r2 = r11
            r5.<init>(r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x001c }
            r1.setURLName(r0)     // Catch:{ all -> 0x001c }
            if (r12 == 0) goto L_0x018c
            javax.mail.Session r0 = r1.session     // Catch:{ all -> 0x001c }
            javax.mail.URLName r4 = r17.getURLName()     // Catch:{ all -> 0x001c }
            javax.mail.PasswordAuthentication r5 = new javax.mail.PasswordAuthentication     // Catch:{ all -> 0x001c }
            r5.<init>(r3, r2)     // Catch:{ all -> 0x001c }
            r0.setPasswordAuthentication(r4, r5)     // Catch:{ all -> 0x001c }
        L_0x018c:
            r1.setConnected(r15)     // Catch:{ all -> 0x001c }
            r1.notifyConnectionListeners(r15)     // Catch:{ all -> 0x001c }
            monitor-exit(r17)
            return
        L_0x0194:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x001c }
            java.lang.String r2 = "already connected"
            r0.<init>(r2)     // Catch:{ all -> 0x001c }
            throw r0     // Catch:{ all -> 0x001c }
        L_0x019c:
            monitor-exit(r17)     // Catch:{ all -> 0x001c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.Service.connect(java.lang.String, int, java.lang.String, java.lang.String):void");
    }
    public synchronized boolean isConnected() {
        return this.connected;
    }
    public synchronized void setConnected(boolean z) {
        this.connected = z;
    }
    public synchronized void close() throws MessagingException {
        connected = false;
        notifyConnectionListeners(3);
    }
    public synchronized URLName getURLName() {
        try {
            URLName uRLName = this.url;
            if (null != uRLName) {
                if (null == uRLName.getPassword()) {
                    if (null != url.getFile()) {
                    }
                }
                return new URLName(this.url.getProtocol(), this.url.getHost(), this.url.getPort(), null, this.url.getUsername(), null);
            }
            return this.url;
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
    public synchronized void setURLName(URLName uRLName) {
        this.url = uRLName;
    }
    public void addConnectionListener(ConnectionListener connectionListener) {
        this.connectionListeners.addElement(connectionListener);
    }
    public void removeConnectionListener(ConnectionListener connectionListener) {
        this.connectionListeners.removeElement(connectionListener);
    }
    public void notifyConnectionListeners(int i2) {
        if (0 < connectionListeners.size()) {
            queueEvent(new ConnectionEvent(this, i2), this.connectionListeners);
        }
        if (3 == i2) {
            terminateQueue();
        }
    }
    public String toString() {
        URLName uRLName = getURLName();
        if (null != uRLName) {
            return uRLName.toString();
        }
        return super.toString();
    }
    public void queueEvent(MailEvent mailEvent, Vector vector) {
        synchronized (this.qLock) {
            try {
                if (null == q) {
                    this.q = new EventQueue();
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.q.enqueue(mailEvent, (Vector) vector.clone());
    }
    static class TerminatorEvent extends MailEvent {
        private static final long serialVersionUID = 5542172141759168416L;

        TerminatorEvent() {
            super(new Object());
        }

        public void dispatch(Object obj) {
            Thread.currentThread().interrupt();
        }
    }
    private void terminateQueue() {
        synchronized (this.qLock) {
            try {
                if (null != q) {
                    Vector vector = new Vector();
                    vector.setSize(1);
                    this.q.enqueue(new TerminatorEvent(), vector);
                    this.q = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
    protected void finalize() throws Throwable {
        super.finalize();
        terminateQueue();
    }
}
