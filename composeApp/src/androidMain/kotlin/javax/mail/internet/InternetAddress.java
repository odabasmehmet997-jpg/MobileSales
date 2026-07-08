package javax.mail.internet;

import com.fasterxml.jackson.core.JsonFactory;
import sun.mail.util.PropUtil;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import javax.mail.Address;
import javax.mail.Session;

public class InternetAddress extends Address implements Cloneable {
    private static final boolean ignoreBogusGroupName = PropUtil.getBooleanSystemProperty("mail.mime.address.ignorebogusgroupname", true);
    private static final String rfc822phrase = HeaderTokenizer.RFC822.replace(' ', (char) 0).replace((char) 9, (char) 0);
    private static final long serialVersionUID = -7507595530758302903L;
    private static final String specialsNoDot = "()<>,;:\\\"[]@";
    private static final String specialsNoDotNoAt = "()<>,;:\\\"[]";
    protected String address;
    protected String encodedPersonal;
    protected String personal;
    public InternetAddress() {
    }
    public InternetAddress(String str) throws AddressException {
        InternetAddress[] parse = parse(str, true);
        if (1 == parse.length) {
            InternetAddress internetAddress = parse[0];
            this.address = internetAddress.address;
            this.personal = internetAddress.personal;
            this.encodedPersonal = internetAddress.encodedPersonal;
            return;
        }
        throw new AddressException("Illegal address", str);
    }
    public InternetAddress(String str, boolean z) throws AddressException {
        this(str);
        if (!z) {
            return;
        }
        if (isGroup()) {
            getGroup(true);
        } else {
            checkAddress(this.address, true, true);
        }
    }
    public InternetAddress(String str, String str2) throws UnsupportedEncodingException {
        this(str, str2, (String) null);
    }
    public InternetAddress(String str, String str2, String str3) throws UnsupportedEncodingException {
        this.address = str;
        setPersonal(str2, str3);
    }
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }
    public String getType() {
        return "rfc822";
    }
    public void setAddress(String str) {
        this.address = str;
    }
    public void setPersonal(String str, String str2) throws UnsupportedEncodingException {
        this.personal = str;
        if (null != str) {
            this.encodedPersonal = MimeUtility.encodeWord(str, str2, null);
        } else {
            this.encodedPersonal = null;
        }
    }
    public void setPersonal(String str) throws UnsupportedEncodingException {
        this.personal = str;
        if (null != str) {
            this.encodedPersonal = MimeUtility.encodeWord(str);
        } else {
            this.encodedPersonal = null;
        }
    }
    public String getAddress() {
        return this.address;
    }
    public String getPersonal() {
        String str = this.personal;
        if (null != str) {
            return str;
        }
        String str2 = this.encodedPersonal;
        if (null == str2) {
            return null;
        }
        try {
            String decodeText = MimeUtility.decodeText(str2);
            this.personal = decodeText;
            return decodeText;
        } catch (Exception unused) {
            return this.encodedPersonal;
        }
    }
    public String toString() {
        String str;
        if (null == encodedPersonal && null != (str = personal)) {
            try {
                this.encodedPersonal = MimeUtility.encodeWord(str);
            } catch (UnsupportedEncodingException unused) {
            }
        }
        if (null != encodedPersonal) {
            final String stringBuffer = quotePhrase(this.encodedPersonal) +
                    " <" +
                    this.address +
                    ">";
            return stringBuffer;
        } else if (isGroup() || isSimple()) {
            return this.address;
        } else {
            final String stringBuffer2 = "<" +
                    this.address +
                    ">";
            return stringBuffer2;
        }
    }
    public String toUnicodeString() {
        String personal2 = getPersonal();
        if (null != personal2) {
            final String stringBuffer = quotePhrase(personal2) +
                    " <" +
                    this.address +
                    ">";
            return stringBuffer;
        } else if (isGroup() || isSimple()) {
            return this.address;
        } else {
            final String stringBuffer2 = "<" +
                    this.address +
                    ">";
            return stringBuffer2;
        }
    }
    private static String quotePhrase(String str) {
        int length = str.length();
        boolean z = false;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if ('\"' == charAt || '\\' == charAt) {
                StringBuffer stringBuffer = new StringBuffer(length + 3);
                stringBuffer.append(JsonFactory.DEFAULT_QUOTE_CHAR);
                for (int i3 = 0; i3 < length; i3++) {
                    char charAt2 = str.charAt(i3);
                    if ('\"' == charAt2 || '\\' == charAt2) {
                        stringBuffer.append('\\');
                    }
                    stringBuffer.append(charAt2);
                }
                stringBuffer.append(JsonFactory.DEFAULT_QUOTE_CHAR);
                return stringBuffer.toString();
            }
            if ((' ' > charAt && 13 != charAt && 10 != charAt && 9 != charAt) || 127 <= charAt || 0 <= InternetAddress.rfc822phrase.indexOf(charAt)) {
                z = true;
            }
        }
        if (!z) {
            return str;
        }
        final String stringBuffer2 = JsonFactory.DEFAULT_QUOTE_CHAR +
                str +
                JsonFactory.DEFAULT_QUOTE_CHAR;
        return stringBuffer2;
    }
    private static String unquote(String str) {
        if (!str.startsWith("\"") || !str.endsWith("\"")) {
            return str;
        }
        String substring = str.substring(1, str.length() - 1);
        if (0 > substring.indexOf(92)) {
            return substring;
        }
        StringBuffer stringBuffer = new StringBuffer(substring.length());
        int i2 = 0;
        while (i2 < substring.length()) {
            char charAt = substring.charAt(i2);
            if ('\\' == charAt && i2 < substring.length() - 1) {
                i2++;
                charAt = substring.charAt(i2);
            }
            stringBuffer.append(charAt);
            i2++;
        }
        return stringBuffer.toString();
    }
    public boolean equals(Object obj) {
        if (!(obj instanceof InternetAddress)) {
            return false;
        }
        String address2 = ((InternetAddress) obj).address;
        String str = this.address;
        if (address2 == str) {
            return true;
        }
        return null != str && str.equalsIgnoreCase(address2);
    }
    public int hashCode() {
        String str = this.address;
        if (null == str) {
            return 0;
        }
        return str.toLowerCase(Locale.ENGLISH).hashCode();
    }
    public static String toString(Address[] addressArr) {
        return toString(addressArr, 0);
    }
    public static String toString(Address[] addressArr, int i2) {
        if (null == addressArr || 0 == addressArr.length) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = 0; i3 < addressArr.length; i3++) {
            if (0 != i3) {
                stringBuffer.append(", ");
                i2 += 2;
            }
            String address2 = addressArr[i3].toString();
            if (76 < InternetAddress.lengthOfFirstSegment(address2) + i2) {
                stringBuffer.append("\r\n\t");
                i2 = 8;
            }
            stringBuffer.append(address2);
            i2 = lengthOfLastSegment(address2, i2);
        }
        return stringBuffer.toString();
    }
    private static int lengthOfFirstSegment(String str) {
        int indexOf = str.indexOf("\r\n");
        if (-1 != indexOf) {
            return indexOf;
        }
        return str.length();
    }
    private static int lengthOfLastSegment(String str, int i2) {
        int lastIndexOf = str.lastIndexOf("\r\n");
        if (-1 != lastIndexOf) {
            return (str.length() - lastIndexOf) - 2;
        }
        return str.length() + i2;
    }
    public static InternetAddress getLocalAddress(Session session) {
        try {
            return _getLocalAddress(session);
        } catch (SecurityException | UnknownHostException | AddressException unused) {
            return null;
        }
    }
    static InternetAddress _getLocalAddress(Session session) throws SecurityException, AddressException, UnknownHostException {
        String str;
        String str2;
        String str3;
        String str4;
        if (null == session) {
            str = System.getProperty("user.name");
            str3 = getLocalHostName();
            str2 = null;
        } else {
            str2 = session.getProperty("mail.from");
            if (null == str2) {
                String property = session.getProperty("mail.user");
                if (null == property || 0 == property.length()) {
                    property = session.getProperty("user.name");
                }
                if (null == property || 0 == property.length()) {
                    str4 = System.getProperty("user.name");
                } else {
                    str4 = property;
                }
                String property2 = session.getProperty("mail.host");
                if (null == property2 || 0 == property2.length()) {
                    property2 = getLocalHostName();
                }
                String str5 = str4;
                str3 = property2;
                str = str5;
            } else {
                str = null;
                str3 = null;
            }
        }
        if (!(null != str2 || null == str || 0 == str.length() || null == str3 || 0 == str3.length())) {
            final String stringBuffer = MimeUtility.quote(str.trim(), "()<>,;:\\\"[]@\t ") +
                    "@" +
                    str3;
            str2 = stringBuffer;
        }
        if (null == str2) {
            return null;
        }
        return new InternetAddress(str2);
    }
    private static String getLocalHostName() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        if (null == localHost) {
            return null;
        }
        String hostName = localHost.getHostName();
        if (null == hostName || 0 >= hostName.length() || !isInetAddressLiteral(hostName)) {
            return hostName;
        }
        final String stringBuffer = '[' +
                hostName +
                ']';
        return stringBuffer;
    }
    private static boolean isInetAddressLiteral(String str) {
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (('0' > charAt || '9' < charAt) && '.' != charAt) {
                if (('a' <= charAt && 'z' >= charAt) || ('A' <= charAt && 'Z' >= charAt)) {
                    z = true;
                } else if (':' != charAt) {
                    return false;
                } else {
                    z2 = true;
                }
            }
        }
        return !z || z2;
    }
    public static InternetAddress[] parse(String str) throws AddressException {
        return parse(str, true);
    }
    public static InternetAddress[] parse(String str, boolean z) throws AddressException {
        return parse(str, z, false);
    }
    public static InternetAddress[] parseHeader(String str, boolean z) throws AddressException {
        return parse(str, z, true);
    }
    private static InternetAddress[] parse(String r19, boolean r20, boolean r21) throws AddressException {
        /*
            r0 = r19
            int r1 = r19.length()
            if (r21 == 0) goto L_0x000c
            if (r20 != 0) goto L_0x000c
            r4 = 1
            goto L_0x000d
        L_0x000c:
            r4 = 0
        L_0x000d:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r7 = 0
            r8 = -1
            r9 = -1
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = -1
            r14 = -1
        L_0x001a:
            r16 = 0
            if (r7 >= r1) goto L_0x0334
            char r15 = r0.charAt(r7)
            r2 = 9
            if (r15 == r2) goto L_0x00fc
            r2 = 10
            if (r15 == r2) goto L_0x00fc
            r2 = 13
            if (r15 == r2) goto L_0x00fc
            r2 = 32
            if (r15 == r2) goto L_0x00fc
            java.lang.String r2 = "Missing '\"'"
            r3 = 34
            if (r15 == r3) goto L_0x0306
            r6 = 44
            if (r15 == r6) goto L_0x0158
            r6 = 62
            if (r15 == r6) goto L_0x0263
            r6 = 91
            if (r15 == r6) goto L_0x023d
            r6 = 41
            r3 = 40
            if (r15 == r3) goto L_0x01f8
            if (r15 == r6) goto L_0x01e9
            switch(r15) {
                case 58: goto L_0x0163;
                case 59: goto L_0x0119;
                case 60: goto L_0x0055;
                default: goto L_0x004f;
            }
        L_0x004f:
            r3 = -1
            if (r8 != r3) goto L_0x00fc
            r2 = r3
            goto L_0x0268
        L_0x0055:
            r3 = -1
            if (r11 == 0) goto L_0x009a
            if (r4 == 0) goto L_0x0092
            if (r8 != r3) goto L_0x0063
            r8 = r3
        L_0x005d:
            r9 = r8
            r2 = 1
        L_0x005f:
            r11 = 0
            r12 = 0
            goto L_0x0331
        L_0x0063:
            if (r10 != 0) goto L_0x009a
            if (r9 != r3) goto L_0x0068
            r9 = r7
        L_0x0068:
            java.lang.String r3 = r0.substring(r8, r9)
            java.lang.String r3 = r3.trim()
            javax.mail.internet.InternetAddress r6 = new javax.mail.internet.InternetAddress
            r6.<init>()
            r6.setAddress(r3)
            if (r13 < 0) goto L_0x0088
            java.lang.String r3 = r0.substring(r13, r14)
            java.lang.String r3 = r3.trim()
            java.lang.String r3 = unquote(r3)
            r6.encodedPersonal = r3
        L_0x0088:
            r5.add(r6)
            r8 = -1
            r9 = -1
            r11 = 0
            r12 = 0
            r13 = -1
            r14 = -1
            goto L_0x009b
        L_0x0092:
            javax.mail.internet.AddressException r1 = new javax.mail.internet.AddressException
            java.lang.String r2 = "Extra route-addr"
            r1.<init>(r2, r0, r7)
            throw r1
        L_0x009a:
            r12 = 1
        L_0x009b:
            int r3 = r7 + 1
            r15 = r3
            r6 = 0
        L_0x009f:
            r16 = r3
            if (r15 >= r1) goto L_0x00c7
            char r3 = r0.charAt(r15)
            r17 = r9
            r9 = 34
            if (r3 == r9) goto L_0x00bd
            r9 = 62
            if (r3 == r9) goto L_0x00ba
            r9 = 92
            if (r3 == r9) goto L_0x00b6
            goto L_0x00bc
        L_0x00b6:
            int r15 = r15 + 1
        L_0x00b8:
            r3 = 1
            goto L_0x00c1
        L_0x00ba:
            if (r6 == 0) goto L_0x00c9
        L_0x00bc:
            goto L_0x00b8
        L_0x00bd:
            r3 = r6 ^ 1
            r6 = r3
            goto L_0x00b8
        L_0x00c1:
            int r15 = r15 + r3
            r3 = r16
            r9 = r17
            goto L_0x009f
        L_0x00c7:
            r17 = r9
        L_0x00c9:
            if (r6 == 0) goto L_0x00ef
            if (r4 == 0) goto L_0x00e9
            r2 = r16
        L_0x00cf:
            if (r2 >= r1) goto L_0x00e7
            char r3 = r0.charAt(r2)
            r6 = 92
            if (r3 != r6) goto L_0x00df
            int r2 = r2 + 1
            r3 = 1
            r6 = 62
            goto L_0x00e5
        L_0x00df:
            r6 = 62
            if (r3 != r6) goto L_0x00e4
            goto L_0x00e7
        L_0x00e4:
            r3 = 1
        L_0x00e5:
            int r2 = r2 + r3
            goto L_0x00cf
        L_0x00e7:
            r9 = r2
            goto L_0x00f0
        L_0x00e9:
            javax.mail.internet.AddressException r1 = new javax.mail.internet.AddressException
            r1.<init>(r2, r0, r15)
            throw r1
        L_0x00ef:
            r9 = r15
        L_0x00f0:
            if (r9 < r1) goto L_0x0107
            if (r4 == 0) goto L_0x00ff
            r2 = -1
            if (r8 != r2) goto L_0x00f8
            r8 = r7
        L_0x00f8:
            r7 = r16
            r9 = r17
        L_0x00fc:
            r2 = 1
            goto L_0x0331
        L_0x00ff:
            javax.mail.internet.AddressException r1 = new javax.mail.internet.AddressException
            java.lang.String r2 = "Missing '>'"
            r1.<init>(r2, r0, r9)
            throw r1
        L_0x0107:
            if (r10 != 0) goto L_0x0112
            if (r8 < 0) goto L_0x010c
            goto L_0x010d
        L_0x010c:
            r7 = r14
        L_0x010d:
            r14 = r7
            r13 = r8
            r7 = r16
            goto L_0x0113
        L_0x0112:
            r7 = r8
        L_0x0113:
            r8 = r7
            r7 = r9
            r2 = 1
            r11 = 1
            goto L_0x0331
        L_0x0119:
            r2 = -1
            if (r8 != r2) goto L_0x0121
            r2 = 1
            r8 = -1
            r9 = -1
            goto L_0x005f
        L_0x0121:
            if (r10 == 0) goto L_0x0156
            if (r21 == 0) goto L_0x0137
            if (r20 != 0) goto L_0x0137
            int r2 = r7 + 1
            if (r2 >= r1) goto L_0x0137
            char r2 = r0.charAt(r2)
            r3 = 64
            if (r2 != r3) goto L_0x0137
            r2 = 1
            r10 = 0
            goto L_0x0331
        L_0x0137:
            javax.mail.internet.InternetAddress r2 = new javax.mail.internet.InternetAddress
            r2.<init>()
            int r3 = r7 + 1
            java.lang.String r3 = r0.substring(r8, r3)
            java.lang.String r3 = r3.trim()
            r2.setAddress(r3)
            r5.add(r2)
            r2 = 1
            r8 = -1
            r9 = -1
            r10 = 0
        L_0x0150:
            r11 = 0
            r12 = 0
            r13 = -1
            r14 = -1
            goto L_0x0331
        L_0x0156:
            if (r4 == 0) goto L_0x015b
        L_0x0158:
            r2 = -1
            goto L_0x0273
        L_0x015b:
            javax.mail.internet.AddressException r1 = new javax.mail.internet.AddressException
            java.lang.String r2 = "Illegal semicolon, not in group"
            r1.<init>(r2, r0, r7)
            throw r1
        L_0x0163:
            if (r10 == 0) goto L_0x0167
            if (r4 == 0) goto L_0x0169
        L_0x0167:
            r2 = -1
            goto L_0x0171
        L_0x0169:
            javax.mail.internet.AddressException r1 = new javax.mail.internet.AddressException
            java.lang.String r2 = "Nested group"
            r1.<init>(r2, r0, r7)
            throw r1
        L_0x0171:
            if (r8 != r2) goto L_0x0174
            r8 = r7
        L_0x0174:
            if (r21 == 0) goto L_0x01e6
            if (r20 != 0) goto L_0x01e6
            int r2 = r7 + 1
            if (r2 >= r1) goto L_0x01aa
            char r2 = r0.charAt(r2)
            java.lang.String r3 = ")>[]:@\\,."
            int r6 = r3.indexOf(r2)
            if (r6 < 0) goto L_0x01aa
            r6 = 64
            if (r2 == r6) goto L_0x018d
            goto L_0x01a6
        L_0x018d:
            int r6 = r7 + 2
        L_0x018f:
            r12 = 59
            if (r6 >= r1) goto L_0x01a4
            char r2 = r0.charAt(r6)
            if (r2 != r12) goto L_0x019a
            goto L_0x01a4
        L_0x019a:
            int r15 = r3.indexOf(r2)
            if (r15 < 0) goto L_0x01a1
            goto L_0x01a4
        L_0x01a1:
            int r6 = r6 + 1
            goto L_0x018f
        L_0x01a4:
            if (r2 != r12) goto L_0x01aa
        L_0x01a6:
            r2 = 1
        L_0x01a7:
            r12 = 1
            goto L_0x0331
        L_0x01aa:
            java.lang.String r2 = r0.substring(r8, r7)
            boolean r3 = ignoreBogusGroupName
            if (r3 == 0) goto L_0x01e4
            java.lang.String r3 = "mailto"
            boolean r3 = r2.equalsIgnoreCase(r3)
            if (r3 != 0) goto L_0x01e2
            java.lang.String r3 = "From"
            boolean r3 = r2.equalsIgnoreCase(r3)
            if (r3 != 0) goto L_0x01e2
            java.lang.String r3 = "To"
            boolean r3 = r2.equalsIgnoreCase(r3)
            if (r3 != 0) goto L_0x01e2
            java.lang.String r3 = "Cc"
            boolean r3 = r2.equalsIgnoreCase(r3)
            if (r3 != 0) goto L_0x01e2
            java.lang.String r3 = "Subject"
            boolean r3 = r2.equalsIgnoreCase(r3)
            if (r3 != 0) goto L_0x01e2
            java.lang.String r3 = "Re"
            boolean r2 = r2.equalsIgnoreCase(r3)
            if (r2 == 0) goto L_0x01e4
        L_0x01e2:
            r8 = -1
            goto L_0x01a6
        L_0x01e4:
            r10 = 1
            goto L_0x01a6
        L_0x01e6:
            r2 = 1
            r10 = 1
            goto L_0x01a7
        L_0x01e9:
            if (r4 == 0) goto L_0x01f0
            r2 = -1
            if (r8 != r2) goto L_0x00fc
            goto L_0x0268
        L_0x01f0:
            javax.mail.internet.AddressException r1 = new javax.mail.internet.AddressException
            java.lang.String r2 = "Missing '('"
            r1.<init>(r2, r0, r7)
            throw r1
        L_0x01f8:
            r2 = -1
            if (r8 < 0) goto L_0x01fe
            if (r9 != r2) goto L_0x01fe
            r9 = r7
        L_0x01fe:
            int r7 = r7 + 1
            r12 = r7
            r2 = 1
        L_0x0202:
            if (r12 >= r1) goto L_0x0221
            if (r2 <= 0) goto L_0x0221
            char r15 = r0.charAt(r12)
            if (r15 == r3) goto L_0x021a
            if (r15 == r6) goto L_0x0217
            r3 = 92
            if (r15 == r3) goto L_0x0214
        L_0x0212:
            r3 = 1
            goto L_0x021d
        L_0x0214:
            int r12 = r12 + 1
            goto L_0x0212
        L_0x0217:
            int r2 = r2 + -1
            goto L_0x0212
        L_0x021a:
            int r2 = r2 + 1
            goto L_0x0212
        L_0x021d:
            int r12 = r12 + r3
            r3 = 40
            goto L_0x0202
        L_0x0221:
            if (r2 <= 0) goto L_0x022e
            if (r4 == 0) goto L_0x0226
            goto L_0x01a6
        L_0x0226:
            javax.mail.internet.AddressException r1 = new javax.mail.internet.AddressException
            java.lang.String r2 = "Missing ')'"
            r1.<init>(r2, r0, r12)
            throw r1
        L_0x022e:
            int r2 = r12 + -1
            r3 = -1
            if (r13 != r3) goto L_0x0234
            r13 = r7
        L_0x0234:
            if (r14 != r3) goto L_0x023a
            r7 = r2
            r14 = r7
            goto L_0x01a6
        L_0x023a:
            r7 = r2
            goto L_0x01a6
        L_0x023d:
            int r7 = r7 + 1
            r2 = r7
        L_0x0240:
            if (r2 >= r1) goto L_0x0255
            char r3 = r0.charAt(r2)
            r6 = 92
            if (r3 == r6) goto L_0x0250
            r6 = 93
            if (r3 == r6) goto L_0x0255
        L_0x024e:
            r3 = 1
            goto L_0x0253
        L_0x0250:
            int r2 = r2 + 1
            goto L_0x024e
        L_0x0253:
            int r2 = r2 + r3
            goto L_0x0240
        L_0x0255:
            if (r2 < r1) goto L_0x023a
            if (r4 == 0) goto L_0x025b
            goto L_0x01a6
        L_0x025b:
            javax.mail.internet.AddressException r1 = new javax.mail.internet.AddressException
            java.lang.String r3 = "Missing ']'"
            r1.<init>(r3, r0, r2)
            throw r1
        L_0x0263:
            if (r4 == 0) goto L_0x026b
            r2 = -1
            if (r8 != r2) goto L_0x00fc
        L_0x0268:
            r8 = r7
            goto L_0x00fc
        L_0x026b:
            javax.mail.internet.AddressException r1 = new javax.mail.internet.AddressException
            java.lang.String r2 = "Missing '<'"
            r1.<init>(r2, r0, r7)
            throw r1
        L_0x0273:
            if (r8 != r2) goto L_0x0278
            r8 = r2
            goto L_0x005d
        L_0x0278:
            if (r10 == 0) goto L_0x027e
            r2 = 1
            r11 = 0
            goto L_0x0331
        L_0x027e:
            if (r9 != r2) goto L_0x0281
            r9 = r7
        L_0x0281:
            java.lang.String r2 = r0.substring(r8, r9)
            java.lang.String r2 = r2.trim()
            if (r12 == 0) goto L_0x02a3
            if (r13 < 0) goto L_0x02a3
            java.lang.String r3 = r0.substring(r13, r14)
            java.lang.String r3 = r3.trim()
            java.lang.String r3 = unquote(r3)
            java.lang.String r6 = r3.trim()
            int r6 = r6.length()
            if (r6 != 0) goto L_0x02a5
        L_0x02a3:
            r3 = r16
        L_0x02a5:
            if (r21 == 0) goto L_0x02c6
            if (r20 != 0) goto L_0x02c6
            if (r3 == 0) goto L_0x02c6
            r6 = 64
            int r8 = r3.indexOf(r6)
            if (r8 < 0) goto L_0x02c6
            int r6 = r2.indexOf(r6)
            if (r6 >= 0) goto L_0x02c6
            r6 = 33
            int r6 = r2.indexOf(r6)
            if (r6 >= 0) goto L_0x02c6
            r18 = r3
            r3 = r2
            r2 = r18
        L_0x02c6:
            if (r12 != 0) goto L_0x02cc
            if (r20 != 0) goto L_0x02cc
            if (r21 == 0) goto L_0x02ce
        L_0x02cc:
            r6 = 0
            goto L_0x02ed
        L_0x02ce:
            java.util.StringTokenizer r3 = new java.util.StringTokenizer
            r3.<init>(r2)
        L_0x02d3:
            boolean r2 = r3.hasMoreTokens()
            if (r2 == 0) goto L_0x0301
            java.lang.String r2 = r3.nextToken()
            r6 = 0
            checkAddress(r2, r6, r6)
            javax.mail.internet.InternetAddress r8 = new javax.mail.internet.InternetAddress
            r8.<init>()
            r8.setAddress(r2)
            r5.add(r8)
            goto L_0x02d3
        L_0x02ed:
            if (r4 != 0) goto L_0x02f2
            checkAddress(r2, r11, r6)
        L_0x02f2:
            javax.mail.internet.InternetAddress r6 = new javax.mail.internet.InternetAddress
            r6.<init>()
            r6.setAddress(r2)
            if (r3 == 0) goto L_0x02fe
            r6.encodedPersonal = r3
        L_0x02fe:
            r5.add(r6)
        L_0x0301:
            r2 = 1
            r8 = -1
            r9 = -1
            goto L_0x0150
        L_0x0306:
            r3 = -1
            if (r8 != r3) goto L_0x030a
            r8 = r7
        L_0x030a:
            int r7 = r7 + 1
            r3 = r7
        L_0x030d:
            if (r3 >= r1) goto L_0x0322
            char r6 = r0.charAt(r3)
            r12 = 34
            if (r6 == r12) goto L_0x0322
            r15 = 92
            if (r6 == r15) goto L_0x031d
        L_0x031b:
            r6 = 1
            goto L_0x0320
        L_0x031d:
            int r3 = r3 + 1
            goto L_0x031b
        L_0x0320:
            int r3 = r3 + r6
            goto L_0x030d
        L_0x0322:
            if (r3 < r1) goto L_0x032e
            if (r4 == 0) goto L_0x0328
            goto L_0x01a6
        L_0x0328:
            javax.mail.internet.AddressException r1 = new javax.mail.internet.AddressException
            r1.<init>(r2, r0, r3)
            throw r1
        L_0x032e:
            r7 = r3
            goto L_0x01a6
        L_0x0331:
            int r7 = r7 + r2
            goto L_0x001a
        L_0x0334:
            if (r8 < 0) goto L_0x03bb
            r2 = -1
            if (r9 != r2) goto L_0x033a
            goto L_0x033b
        L_0x033a:
            r1 = r9
        L_0x033b:
            java.lang.String r1 = r0.substring(r8, r1)
            java.lang.String r1 = r1.trim()
            if (r12 == 0) goto L_0x035d
            if (r13 < 0) goto L_0x035d
            java.lang.String r0 = r0.substring(r13, r14)
            java.lang.String r0 = r0.trim()
            java.lang.String r0 = unquote(r0)
            java.lang.String r2 = r0.trim()
            int r2 = r2.length()
            if (r2 != 0) goto L_0x035f
        L_0x035d:
            r0 = r16
        L_0x035f:
            if (r21 == 0) goto L_0x0380
            if (r20 != 0) goto L_0x0380
            if (r0 == 0) goto L_0x0380
            r2 = 64
            int r3 = r0.indexOf(r2)
            if (r3 < 0) goto L_0x0380
            int r2 = r1.indexOf(r2)
            if (r2 >= 0) goto L_0x0380
            r2 = 33
            int r2 = r1.indexOf(r2)
            if (r2 >= 0) goto L_0x0380
            r18 = r1
            r1 = r0
            r0 = r18
        L_0x0380:
            if (r12 != 0) goto L_0x0386
            if (r20 != 0) goto L_0x0386
            if (r21 == 0) goto L_0x0388
        L_0x0386:
            r2 = 0
            goto L_0x03a7
        L_0x0388:
            java.util.StringTokenizer r0 = new java.util.StringTokenizer
            r0.<init>(r1)
        L_0x038d:
            boolean r1 = r0.hasMoreTokens()
            if (r1 == 0) goto L_0x03bb
            java.lang.String r1 = r0.nextToken()
            r2 = 0
            checkAddress(r1, r2, r2)
            javax.mail.internet.InternetAddress r3 = new javax.mail.internet.InternetAddress
            r3.<init>()
            r3.setAddress(r1)
            r5.add(r3)
            goto L_0x038d
        L_0x03a7:
            if (r4 != 0) goto L_0x03ac
            checkAddress(r1, r11, r2)
        L_0x03ac:
            javax.mail.internet.InternetAddress r2 = new javax.mail.internet.InternetAddress
            r2.<init>()
            r2.setAddress(r1)
            if (r0 == 0) goto L_0x03b8
            r2.encodedPersonal = r0
        L_0x03b8:
            r5.add(r2)
        L_0x03bb:
            int r0 = r5.size()
            javax.mail.internet.InternetAddress[] r0 = new javax.mail.internet.InternetAddress[r0]
            r5.toArray(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.InternetAddress.parse(java.lang.String, boolean, boolean):javax.mail.internet.InternetAddress[]");
    }
    public void validate() throws AddressException {
        if (isGroup()) {
            getGroup(true);
        } else {
            checkAddress(address, true, true);
        }
    }
    private static void checkAddress(String address, boolean allowLocal, boolean allowWhitespace) throws AddressException {

        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.InternetAddress.checkAddress(java.lang.String, boolean, boolean):void");
    }
    private boolean isSimple() {
        String str = this.address;
        return null == str || 0 > InternetAddress.indexOfAny(str, InternetAddress.specialsNoDotNoAt);
    }
    public boolean isGroup() {
        String str = this.address;
        return null != str && str.endsWith(";") && 0 < address.indexOf(58);
    }
    public InternetAddress[] getGroup(boolean z) throws AddressException {
        int indexOf;
        String address2 = address;
        if (address2.endsWith(";") && 0 <= (indexOf = address2.indexOf(58))) {
            return parseHeader(address2.substring(indexOf + 1, address2.length() - 1), z);
        }
        return null;
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
