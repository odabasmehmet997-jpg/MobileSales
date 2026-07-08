package javax.mail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;
import java.util.Locale;

public class URLName {
    static final int caseDiff = 32;
    private static boolean doEncode = true;
    static BitSet dontNeedEncoding = new BitSet(256);
    private String file;
    protected String fullURL;
    private int hashCode;
    private String host;
    private InetAddress hostAddress;
    private final boolean hostAddressKnown;
    private String password;
    private int port;
    private String protocol;
    private String ref;
    private String username;
    static {
        try {
            doEncode = !Boolean.getBoolean("mail.URLName.dontencode");
        } catch (Exception unused) {
        }
        for (int i2 = 97; 122 >= i2; i2++) {
            dontNeedEncoding.set(i2);
        }
        for (int i3 = 65; 90 >= i3; i3++) {
            dontNeedEncoding.set(i3);
        }
        for (int i4 = 48; 57 >= i4; i4++) {
            dontNeedEncoding.set(i4);
        }
        dontNeedEncoding.set(32);
        dontNeedEncoding.set(45);
        dontNeedEncoding.set(95);
        dontNeedEncoding.set(46);
        dontNeedEncoding.set(42);
    }
    public URLName(String str, String str2, int i2, String str3, String str4, String str5) {
        int indexOf;
        this.hostAddressKnown = false;
        this.hashCode = 0;
        this.protocol = str;
        this.host = str2;
        this.port = i2;
        if (null == str3 || -1 == (indexOf = str3.indexOf(35))) {
            this.file = str3;
            this.ref = null;
        } else {
            this.file = str3.substring(0, indexOf);
            this.ref = str3.substring(indexOf + 1);
        }
        this.username = doEncode ? encode(str4) : str4;
        this.password = doEncode ? encode(str5) : str5;
    }
    public URLName(URL url) {
        this(url.toString());
    }
    public URLName(String str) {
        this.hostAddressKnown = false;
        this.port = -1;
        this.hashCode = 0;
        parseString(str);
    }
    public String toString() {
        if (null == fullURL) {
            StringBuffer stringBuffer = new StringBuffer();
            String str = this.protocol;
            if (null != str) {
                stringBuffer.append(str);
                stringBuffer.append(":");
            }
            if (!(null == username && null == host)) {
                stringBuffer.append("//");
                String str2 = this.username;
                if (null != str2) {
                    stringBuffer.append(str2);
                    if (null != password) {
                        stringBuffer.append(":");
                        stringBuffer.append(this.password);
                    }
                    stringBuffer.append("@");
                }
                String str3 = this.host;
                if (null != str3) {
                    stringBuffer.append(str3);
                }
                if (-1 != port) {
                    stringBuffer.append(":");
                    stringBuffer.append(this.port);
                }
                if (null != file) {
                    stringBuffer.append("/");
                }
            }
            String str4 = this.file;
            if (null != str4) {
                stringBuffer.append(str4);
            }
            if (null != ref) {
                stringBuffer.append("#");
                stringBuffer.append(this.ref);
            }
            this.fullURL = stringBuffer.toString();
        }
        return this.fullURL;
    }
    public void parseString(String str) {
        int indexOf;
        String str2;
        int i2;
        this.password = null;
        this.username = null;
        this.host = null;
        this.ref = null;
        this.file = null;
        this.protocol = null;
        this.port = -1;
        int length = str.length();
        int indexOf2 = str.indexOf(58);
        if (-1 != indexOf2) {
            this.protocol = str.substring(0, indexOf2);
        }
        int i3 = indexOf2 + 1;
        if (str.regionMatches(i3, "//", 0, 2)) {
            int i4 = indexOf2 + 3;
            int indexOf3 = str.indexOf(47, i4);
            if (-1 != indexOf3) {
                str2 = str.substring(i4, indexOf3);
                int i5 = indexOf3 + 1;
                if (i5 < length) {
                    this.file = str.substring(i5);
                } else {
                    this.file = "";
                }
            } else {
                str2 = str.substring(i4);
            }
            int indexOf4 = str2.indexOf(64);
            if (-1 != indexOf4) {
                String substring = str2.substring(0, indexOf4);
                str2 = str2.substring(indexOf4 + 1);
                int indexOf5 = substring.indexOf(58);
                if (-1 != indexOf5) {
                    this.username = substring.substring(0, indexOf5);
                    this.password = substring.substring(indexOf5 + 1);
                } else {
                    this.username = substring;
                }
            }
            if (0 >= str2.length() || '[' != str2.charAt(0)) {
                i2 = str2.indexOf(58);
            } else {
                i2 = str2.indexOf(58, str2.indexOf(93));
            }
            if (-1 != i2) {
                String substring2 = str2.substring(i2 + 1);
                if (0 < substring2.length()) {
                    try {
                        this.port = Integer.parseInt(substring2);
                    } catch (NumberFormatException unused) {
                        this.port = -1;
                    }
                }
                this.host = str2.substring(0, i2);
            } else {
                this.host = str2;
            }
        } else if (i3 < length) {
            this.file = str.substring(i3);
        }
        String str3 = this.file;
        if (null != str3 && -1 != (indexOf = str3.indexOf(35))) {
            this.ref = this.file.substring(indexOf + 1);
            this.file = this.file.substring(0, indexOf);
        }
    }
    public int getPort() {
        return this.port;
    }
    public String getProtocol() {
        return this.protocol;
    }
    public String getFile() {
        return this.file;
    }
    public String getRef() {
        return this.ref;
    }
    public String getHost() {
        return this.host;
    }
    public String getUsername() {
        return doEncode ? decode(this.username) : this.username;
    }
    public String getPassword() {
        return doEncode ? decode(this.password) : this.password;
    }
    public URL getURL() throws MalformedURLException {
        return new URL(protocol, host, port, file);
    }
    public boolean equals(Object r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof javax.mail.URLName
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            javax.mail.URLName r5 = (javax.mail.URLName) r5
            java.lang.String r0 = r5.protocol
            if (r0 == 0) goto L_0x0068
            java.lang.String r2 = r4.protocol
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0015
            goto L_0x0068
        L_0x0015:
            java.net.InetAddress r0 = r4.getHostAddress()
            java.net.InetAddress r2 = r5.getHostAddress()
            if (r0 == 0) goto L_0x0028
            if (r2 == 0) goto L_0x0028
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x003c
            return r1
        L_0x0028:
            java.lang.String r0 = r4.host
            if (r0 == 0) goto L_0x0037
            java.lang.String r2 = r5.host
            if (r2 == 0) goto L_0x0037
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 != 0) goto L_0x003c
            return r1
        L_0x0037:
            java.lang.String r2 = r5.host
            if (r0 == r2) goto L_0x003c
            return r1
        L_0x003c:
            java.lang.String r0 = r4.username
            java.lang.String r2 = r5.username
            if (r0 == r2) goto L_0x004b
            if (r0 == 0) goto L_0x004a
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x004b
        L_0x004a:
            return r1
        L_0x004b:
            java.lang.String r0 = r4.file
            java.lang.String r2 = ""
            if (r0 != 0) goto L_0x0052
            r0 = r2
        L_0x0052:
            java.lang.String r3 = r5.file
            if (r3 != 0) goto L_0x0057
            goto L_0x0058
        L_0x0057:
            r2 = r3
        L_0x0058:
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x005f
            return r1
        L_0x005f:
            int r0 = r4.port
            int r5 = r5.port
            if (r0 == r5) goto L_0x0066
            return r1
        L_0x0066:
            r5 = 1
            return r5
        L_0x0068:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.URLName.equals(java.lang.Object):boolean");
    }
    public int hashCode() {
        int i2 = this.hashCode;
        if (0 != i2) {
            return i2;
        }
        String str = this.protocol;
        if (null != str) {
            this.hashCode = i2 + str.hashCode();
        }
        InetAddress hostAddress2 = getHostAddress();
        if (null != hostAddress2) {
            this.hashCode += hostAddress2.hashCode();
        } else {
            String str2 = this.host;
            if (null != str2) {
                this.hashCode += str2.toLowerCase(Locale.ENGLISH).hashCode();
            }
        }
        String str3 = this.username;
        if (null != str3) {
            this.hashCode += str3.hashCode();
        }
        String str4 = this.file;
        if (null != str4) {
            this.hashCode += str4.hashCode();
        }
        int i3 = this.hashCode + this.port;
        this.hashCode = i3;
        return i3;
    }
    private synchronized InetAddress getHostAddress() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.hostAddressKnown     // Catch:{ all -> 0x0009 }
            if (r0 == 0) goto L_0x000b
            java.net.InetAddress r0 = r2.hostAddress     // Catch:{ all -> 0x0009 }
            monitor-exit(r2)
            return r0
        L_0x0009:
            r0 = move-exception
            goto L_0x0022
        L_0x000b:
            java.lang.String r0 = r2.host     // Catch:{ all -> 0x0009 }
            r1 = 0
            if (r0 != 0) goto L_0x0012
            monitor-exit(r2)
            return r1
        L_0x0012:
            java.net.InetAddress r0 = java.net.InetAddress.getByName(r0)     // Catch:{ UnknownHostException -> 0x0019 }
            r2.hostAddress = r0     // Catch:{ UnknownHostException -> 0x0019 }
            goto L_0x001b
        L_0x0019:
            r2.hostAddress = r1     // Catch:{ all -> 0x0009 }
        L_0x001b:
            r0 = 1
            r2.hostAddressKnown = r0     // Catch:{ all -> 0x0009 }
            java.net.InetAddress r0 = r2.hostAddress     // Catch:{ all -> 0x0009 }
            monitor-exit(r2)
            return r0
        L_0x0022:
            monitor-exit(r2)     // Catch:{ all -> 0x0009 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.URLName.getHostAddress():java.net.InetAddress");
    }
    static String encode(String str) {
        if (null == str) {
            return null;
        }
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (' ' == charAt || !dontNeedEncoding.get(charAt)) {
                return _encode(str);
            }
        }
        return str;
    }
    private static String _encode(String str) {
        StringBuffer stringBuffer = new StringBuffer(str.length());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(10);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8);
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (dontNeedEncoding.get(charAt)) {
                if (' ' == charAt) {
                    charAt = '+';
                }
                stringBuffer.append(charAt);
            } else {
                try {
                    outputStreamWriter.write(charAt);
                    outputStreamWriter.flush();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    for (int i3 = 0; i3 < byteArray.length; i3++) {
                        stringBuffer.append('%');
                        char forDigit = Character.forDigit((byteArray[i3] >> 4) & 15, 16);
                        if (Character.isLetter(forDigit)) {
                            forDigit = (char) (forDigit - ' ');
                        }
                        stringBuffer.append(forDigit);
                        char forDigit2 = Character.forDigit(byteArray[i3] & 15, 16);
                        if (Character.isLetter(forDigit2)) {
                            forDigit2 = (char) (forDigit2 - ' ');
                        }
                        stringBuffer.append(forDigit2);
                    }
                    byteArrayOutputStream.reset();
                } catch (IOException unused) {
                    byteArrayOutputStream.reset();
                }
            }
        }
        return stringBuffer.toString();
    }
    static String decode(String str) {
        if (null == str) {
            return null;
        }
        if (-1 == URLName.indexOfAny(str, "+%")) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = 0;
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if ('%' == charAt) {
                int i3 = i2 + 3;
                try {
                    stringBuffer.append((char) Integer.parseInt(str.substring(i2 + 1, i3), 16));
                    i2 += 2;
                } catch (NumberFormatException unused) {
                    final String stringBuffer2 = "Illegal URL encoded value: " +
                            str.substring(i2, i3);
                    throw new IllegalArgumentException(stringBuffer2);
                }
            } else if ('+' != charAt) {
                stringBuffer.append(charAt);
            } else {
                stringBuffer.append(' ');
            }
            i2++;
        }
        String stringBuffer3 = stringBuffer.toString();
        return new String(stringBuffer3.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
    private static int indexOfAny(String str, String str2) {
        return indexOfAny(str, str2, 0);
    }
    private static int indexOfAny(String str, String str2, int i2) {
        try {
            int length = str.length();
            while (i2 < length) {
                if (0 <= str2.indexOf(str.charAt(i2))) {
                    return i2;
                }
                i2++;
            }
        } catch (StringIndexOutOfBoundsException unused) {
        }
        return -1;
    }
}
