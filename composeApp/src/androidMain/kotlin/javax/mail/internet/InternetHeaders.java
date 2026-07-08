package javax.mail.internet;

import sun.mail.util.PropUtil;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import javax.mail.Header;
import javax.mail.MessagingException;
import org.springframework.http.HttpHeaders;

public class InternetHeaders {
    private static final boolean ignoreWhitespaceLines = PropUtil.getBooleanSystemProperty("mail.mime.ignorewhitespacelines", false);
    protected List headers;
    protected static final class InternetHeader extends Header {
        String line;

        public InternetHeader(String str) {
            super("", "");
            int indexOf = str.indexOf(58);
            if (0 > indexOf) {
                this.name = str.trim();
            } else {
                this.name = str.substring(0, indexOf).trim();
            }
            this.line = str;
        }

        public InternetHeader(String str, String str2) {
            super(str, "");
            if (null != str2) {
                final String stringBuffer = str +
                        ": " +
                        str2;
                this.line = stringBuffer;
                return;
            }
            this.line = null;
        }

        public String getValue() {
            char charAt;
            int indexOf = this.line.indexOf(58);
            if (0 > indexOf) {
                return this.line;
            }
            while (true) {
                indexOf++;
                if (indexOf >= this.line.length() || !(' ' == (charAt = line.charAt(indexOf)) || 9 == charAt || 13 == charAt || 10 == charAt)) {
                }
            }
        }
    }
    static class matchEnum implements Enumeration {

        /* renamed from: e  reason: collision with root package name */
        private final Iterator f45e;
        private final boolean match;
        private final String[] names;
        private InternetHeader next_header;
        private final boolean want_line;

        matchEnum(List list, String[] strArr, boolean z, boolean z2) {
            this.f45e = list.iterator();
            this.names = strArr;
            this.match = z;
            this.want_line = z2;
        }

        public boolean hasMoreElements() {
            if (null == next_header) {
                this.next_header = nextMatch();
            }
            return null != next_header;
        }

        public Object nextElement() {
            if (null == next_header) {
                this.next_header = nextMatch();
            }
            InternetHeader internetHeader = this.next_header;
            if (null != internetHeader) {
                this.next_header = null;
                if (this.want_line) {
                    return internetHeader.line;
                }
                return new Header(internetHeader.getName(), internetHeader.getValue());
            }
            throw new NoSuchElementException("No more headers");
        }

        private InternetHeader nextMatch() {
            while (this.f45e.hasNext()) {
                InternetHeader internetHeader = (InternetHeader) this.f45e.next();
                if (null != internetHeader.line) {
                    if (null != names) {
                        int i2 = 0;
                        while (true) {
                            String[] strArr = this.names;
                            if (i2 < strArr.length) {
                                if (!strArr[i2].equalsIgnoreCase(internetHeader.getName())) {
                                    i2++;
                                } else if (this.match) {
                                    return internetHeader;
                                }
                            } else if (!this.match) {
                                return internetHeader;
                            }
                        }
                    } else if (this.match) {
                        return null;
                    } else {
                        return internetHeader;
                    }
                }
            }
            return null;
        }
    }
    public InternetHeaders() {
        ArrayList arrayList = new ArrayList(40);
        this.headers = arrayList;
        arrayList.add(new InternetHeader("Return-Path", null));
        this.headers.add(new InternetHeader("Received", null));
        this.headers.add(new InternetHeader("Resent-Date", null));
        this.headers.add(new InternetHeader("Resent-From", null));
        this.headers.add(new InternetHeader("Resent-Sender", null));
        this.headers.add(new InternetHeader("Resent-To", null));
        this.headers.add(new InternetHeader("Resent-Cc", null));
        this.headers.add(new InternetHeader("Resent-Bcc", null));
        this.headers.add(new InternetHeader("Resent-Message-Id", null));
        this.headers.add(new InternetHeader(HttpHeaders.DATE, null));
        this.headers.add(new InternetHeader(HttpHeaders.FROM, null));
        this.headers.add(new InternetHeader("Sender", null));
        this.headers.add(new InternetHeader("Reply-To", null));
        this.headers.add(new InternetHeader("To", null));
        this.headers.add(new InternetHeader("Cc", null));
        this.headers.add(new InternetHeader("Bcc", null));
        this.headers.add(new InternetHeader("Message-Id", null));
        this.headers.add(new InternetHeader("In-Reply-To", null));
        this.headers.add(new InternetHeader("References", null));
        this.headers.add(new InternetHeader("Subject", null));
        this.headers.add(new InternetHeader("Comments", null));
        this.headers.add(new InternetHeader("Keywords", null));
        this.headers.add(new InternetHeader("Errors-To", null));
        this.headers.add(new InternetHeader("MIME-Version", null));
        this.headers.add(new InternetHeader(HttpHeaders.CONTENT_TYPE, null));
        this.headers.add(new InternetHeader("Content-Transfer-Encoding", null));
        this.headers.add(new InternetHeader("Content-MD5", null));
        this.headers.add(new InternetHeader(":", null));
        this.headers.add(new InternetHeader(HttpHeaders.CONTENT_LENGTH, null));
        this.headers.add(new InternetHeader("Status", null));
    }
    public InternetHeaders(InputStream inputStream) throws MessagingException {
        this.headers = new ArrayList(40);
        load(inputStream);
    }
    public void load(InputStream r6) throws MessagingException {
        /*
            r5 = this;
            com.sun.mail.util.LineInputStream r0 = new com.sun.mail.util.LineInputStream
            r0.<init>(r6)
            java.lang.StringBuffer r6 = new java.lang.StringBuffer
            r6.<init>()
            r1 = 0
            r2 = r1
        L_0x000c:
            java.lang.String r3 = r0.readLine()     // Catch:{ IOException -> 0x0023 }
            if (r3 == 0) goto L_0x0034
            java.lang.String r4 = " "
            boolean r4 = r3.startsWith(r4)     // Catch:{ IOException -> 0x0023 }
            if (r4 != 0) goto L_0x0025
            java.lang.String r4 = "\t"
            boolean r4 = r3.startsWith(r4)     // Catch:{ IOException -> 0x0023 }
            if (r4 == 0) goto L_0x0034
            goto L_0x0025
        L_0x0023:
            r6 = move-exception
            goto L_0x0055
        L_0x0025:
            if (r2 == 0) goto L_0x002b
            r6.append(r2)     // Catch:{ IOException -> 0x0023 }
            r2 = r1
        L_0x002b:
            java.lang.String r4 = "\r\n"
            r6.append(r4)     // Catch:{ IOException -> 0x0023 }
            r6.append(r3)     // Catch:{ IOException -> 0x0023 }
            goto L_0x004c
        L_0x0034:
            if (r2 == 0) goto L_0x003a
            r5.addHeaderLine(r2)     // Catch:{ IOException -> 0x0023 }
            goto L_0x004b
        L_0x003a:
            int r2 = r6.length()     // Catch:{ IOException -> 0x0023 }
            if (r2 <= 0) goto L_0x004b
            java.lang.String r2 = r6.toString()     // Catch:{ IOException -> 0x0023 }
            r5.addHeaderLine(r2)     // Catch:{ IOException -> 0x0023 }
            r2 = 0
            r6.setLength(r2)     // Catch:{ IOException -> 0x0023 }
        L_0x004b:
            r2 = r3
        L_0x004c:
            if (r3 == 0) goto L_0x0054
            boolean r3 = isEmpty(r3)     // Catch:{ IOException -> 0x0023 }
            if (r3 == 0) goto L_0x000c
        L_0x0054:
            return
        L_0x0055:
            javax.mail.MessagingException r0 = new javax.mail.MessagingException
            java.lang.String r1 = "Error in input stream"
            r0.<init>(r1, r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.InternetHeaders.load(java.io.InputStream):void");
    }
    private static final boolean isEmpty(String str) {
        return 0 == str.length() || (ignoreWhitespaceLines && 0 == str.trim().length());
    }
    public String[] getHeader(String str) {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (Object internetHeader : this.headers) {
            if (str.equalsIgnoreCase(internetHeader.getName()) && (null != internetHeader.line())) {
                arrayList.add(internetHeader.getClass());
            }
        }
        if (0 == arrayList.size()) {
            return null;
        }
        return arrayList.toArray(new String[arrayList.size()]);
    }
    public String getHeader(String str, String str2) {
        String[] header = getHeader(str);
        if (null == header) {
            return null;
        }
        if (1 == header.length || null == str2) {
            return header[0];
        }
        StringBuffer stringBuffer = new StringBuffer(header[0]);
        for (int i2 = 1; i2 < header.length; i2++) {
            stringBuffer.append(str2);
            stringBuffer.append(header[i2]);
        }
        return stringBuffer.toString();
    }
    public void setHeader(String str, String str2) {
        int indexOf;
        int i2 = 0;
        boolean z = false;
        while (i2 < this.headers.size()) {
            InternetHeader internetHeader = (InternetHeader) this.headers.get(i2);
            if (str.equalsIgnoreCase(internetHeader.getName())) {
                if (!z) {
                    String str3 = internetHeader.line;
                    if (null == str3 || 0 > (indexOf = str3.indexOf(58))) {
                        final String stringBuffer = str +
                                ": " +
                                str2;
                        internetHeader.line = stringBuffer;
                    } else {
                        final String stringBuffer2 = internetHeader.line.substring(0, indexOf + 1) +
                                " " +
                                str2;
                        internetHeader.line = stringBuffer2;
                    }
                    z = true;
                } else {
                    this.headers.remove(i2);
                    i2--;
                }
            }
            i2++;
        }
        if (!z) {
            addHeader(str, str2);
        }
    }
    public void addHeader(String str, String str2) {
        int size = this.headers.size();
        boolean z = "Received".equalsIgnoreCase(str) || "Return-Path".equalsIgnoreCase(str);
        if (z) {
            size = 0;
        }
        for (int size2 = this.headers.size() - 1; 0 <= size2; size2--) {
            InternetHeader internetHeader = (InternetHeader) this.headers.get(size2);
            if (str.equalsIgnoreCase(internetHeader.getName())) {
                if (z) {
                    size = size2;
                } else {
                    this.headers.add(size2 + 1, new InternetHeader(str, str2));
                    return;
                }
            }
            if (!z && ":".equals(internetHeader.getName())) {
                size = size2;
            }
        }
        this.headers.add(size, new InternetHeader(str, str2));
    }
    public void removeHeader(String str) {
        for (int i2 = 0; i2 < this.headers.size(); i2++) {
            InternetHeader internetHeader = (InternetHeader) this.headers.get(i2);
            if (str.equalsIgnoreCase(internetHeader.getName())) {
                internetHeader.line = null;
            }
        }
    }
    public Enumeration getAllHeaders() {
        return new matchEnum(this.headers, null, false, false);
    }
    public Enumeration getMatchingHeaders(String[] strArr) {
        return new matchEnum(this.headers, strArr, true, false);
    }
    public Enumeration getNonMatchingHeaders(String[] strArr) {
        return new matchEnum(this.headers, strArr, false, false);
    }
    public void addHeaderLine(String str) {
        try {
            char charAt = str.charAt(0);
            if (' ' != charAt) {
                if (9 != charAt) {
                    this.headers.add(new InternetHeader(str));
                    return;
                }
            }
            List list = this.headers;
            InternetHeader internetHeader = (InternetHeader) list.get(list.size() - 1);
            final String stringBuffer = internetHeader.line +
                    "\r\n" +
                    str;
            internetHeader.line = stringBuffer;
        } catch (StringIndexOutOfBoundsException | NoSuchElementException unused) {
        }
    }
    public Enumeration getAllHeaderLines() {
        return getNonMatchingHeaderLines(null);
    }
    public Enumeration getMatchingHeaderLines(String[] strArr) {
        return new matchEnum(this.headers, strArr, true, true);
    }
    public Enumeration getNonMatchingHeaderLines(String[] strArr) {
        return new matchEnum(this.headers, strArr, false, true);
    }
}
