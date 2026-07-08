package javax.mail.internet;

import com.fasterxml.jackson.core.JsonFactory;
import sun.mail.util.PropUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;

public enum MimeUtility {
    ;
    public static final int ALL = -1;
    static final int ALL_ASCII = 1;
    static final int MOSTLY_ASCII = 2;
    static final int MOSTLY_NONASCII = 3;
    static Class classjavaxmailinternetMimeUtility;
    private static final boolean decodeStrict = sun.mail.util.PropUtil.getBooleanSystemProperty("mail.mime.decodetext.strict", true);
    private static String defaultJavaCharset;
    private static String defaultMIMECharset;
    private static final boolean encodeEolStrict = sun.mail.util.PropUtil.getBooleanSystemProperty("mail.mime.encodeeol.strict", false);
    private static final boolean foldEncodedWords = sun.mail.util.PropUtil.getBooleanSystemProperty("mail.mime.foldencodedwords", false);
    private static final boolean foldText = sun.mail.util.PropUtil.getBooleanSystemProperty("mail.mime.foldtext", true);
    private static final boolean ignoreUnknownEncoding = PropUtil.getBooleanSystemProperty("mail.mime.ignoreunknownencoding", false);
    private static final Hashtable java2mime = new Hashtable(40);
    private static final Hashtable mime2java = new Hashtable(10);
    private static final Map nonAsciiCharsetMap = new HashMap();

    static final boolean nonascii(int i2) {
        return 127 <= i2 || !(32 <= i2 || 13 == i2 || 10 == i2 || 9 == i2);
    }

    public static String getEncoding(DataSource dataSource) {
        String str = "base64";
        InputStream inputStream = null;
        try {
            ContentType contentType = new ContentType(dataSource.getContentType());
            InputStream inputStream2 = dataSource.getInputStream();
            boolean match = contentType.match("text/*");
            int checkAscii = checkAscii(inputStream2, -1, !match);
            if (1 == checkAscii) {
                str = "7bit";
            } else if (2 == checkAscii) {
                if (!match || !nonAsciiCharset(contentType)) {
                    str = "quoted-printable";
                }
            }
            if (null != inputStream2) {
                try {
                    inputStream2.close();
                } catch (IOException unused) {
                }
            }
            return str;
        } catch (Exception unused2) {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException unused3) {
                }
            }
            return str;
        } catch (Throwable th) {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
    }

    private static boolean nonAsciiCharset(ContentType contentType) {
        Boolean bool;
        Boolean bool2;
        String parameter = contentType.getParameter("charset");
        boolean z = false;
        if (null == parameter) {
            return false;
        }
        String lowerCase = parameter.toLowerCase(Locale.ENGLISH);
        Map map = nonAsciiCharsetMap;
        synchronized (map) {
            bool = (Boolean) map.get(lowerCase);
        }
        if (null == bool) {
            try {
                byte[] bytes = "\r\n".getBytes(lowerCase);
                if (!(2 == bytes.length && 13 == bytes[0] && 10 == bytes[1])) {
                    z = true;
                }
                bool2 = Boolean.valueOf(z);
            } catch (UnsupportedEncodingException unused) {
                bool2 = Boolean.FALSE;
            } catch (RuntimeException unused2) {
                bool2 = Boolean.TRUE;
            }
            bool = bool2;
            Map map2 = nonAsciiCharsetMap;
            synchronized (map2) {
                map2.put(lowerCase, bool);
            }
        }
        return bool.booleanValue();
    }

    public static String getEncoding(DataHandler dataHandler) {
        if (null != dataHandler.getName()) {
            return getEncoding(dataHandler.getDataSource());
        }
        try {
            if (new ContentType(dataHandler.getContentType()).match("text/*")) {
                AsciiOutputStream asciiOutputStream = new AsciiOutputStream(false, false);
                try {
                    dataHandler.writeTo(asciiOutputStream);
                } catch (IOException unused) {
                }
                int ascii = asciiOutputStream.getAscii();
                if (1 != ascii) {
                    if (2 != ascii) {
                        return "base64";
                    }
                    return "quoted-printable";
                }
            } else {
                AsciiOutputStream asciiOutputStream2 = new AsciiOutputStream(true, encodeEolStrict);
                try {
                    dataHandler.writeTo(asciiOutputStream2);
                } catch (IOException unused2) {
                }
                if (1 != asciiOutputStream2.getAscii()) {
                    return "base64";
                }
            }
            return "7bit";
        } catch (Exception unused3) {
            return "base64";
        }
    }

    public static InputStream decode(InputStream inputStream, String str) throws MessagingException {
        if ("base64".equalsIgnoreCase(str)) {
            return new sun.mail.util.BASE64DecoderStream(inputStream);
        }
        if ("quoted-printable".equalsIgnoreCase(str)) {
            return new sun.mail.util.QPDecoderStream(inputStream);
        }
        if ("uuencode".equalsIgnoreCase(str) || "x-uuencode".equalsIgnoreCase(str) || "x-uue".equalsIgnoreCase(str)) {
            return new sun.mail.util.UUDecoderStream(inputStream);
        }
        if ("binary".equalsIgnoreCase(str) || "7bit".equalsIgnoreCase(str) || "8bit".equalsIgnoreCase(str) || ignoreUnknownEncoding) {
            return inputStream;
        }
        final String stringBuffer = "Unknown encoding: " +
                str;
        throw new MessagingException(stringBuffer);
    }

    public static OutputStream encode(OutputStream outputStream, String str) throws MessagingException {
        if (null == str) {
            return outputStream;
        }
        if ("base64".equalsIgnoreCase(str)) {
            return new sun.mail.util.BASE64EncoderStream(outputStream);
        }
        if ("quoted-printable".equalsIgnoreCase(str)) {
            return new sun.mail.util.QPEncoderStream(outputStream);
        }
        if ("uuencode".equalsIgnoreCase(str) || "x-uuencode".equalsIgnoreCase(str) || "x-uue".equalsIgnoreCase(str)) {
            return new sun.mail.util.UUEncoderStream(outputStream);
        }
        if ("binary".equalsIgnoreCase(str) || "7bit".equalsIgnoreCase(str) || "8bit".equalsIgnoreCase(str)) {
            return outputStream;
        }
        final String stringBuffer = "Unknown encoding: " +
                str;
        throw new MessagingException(stringBuffer);
    }

    public static OutputStream encode(OutputStream outputStream, String str, String str2) throws MessagingException {
        if (null == str) {
            return outputStream;
        }
        if ("base64".equalsIgnoreCase(str)) {
            return new sun.mail.util.BASE64EncoderStream(outputStream);
        }
        if ("quoted-printable".equalsIgnoreCase(str)) {
            return new sun.mail.util.QPEncoderStream(outputStream);
        }
        if ("uuencode".equalsIgnoreCase(str) || "x-uuencode".equalsIgnoreCase(str) || "x-uue".equalsIgnoreCase(str)) {
            return new sun.mail.util.UUEncoderStream(outputStream, str2);
        }
        if ("binary".equalsIgnoreCase(str) || "7bit".equalsIgnoreCase(str) || "8bit".equalsIgnoreCase(str)) {
            return outputStream;
        }
        final String stringBuffer = "Unknown encoding: " +
                str;
        throw new MessagingException(stringBuffer);
    }

    public static String encodeText(String str) throws UnsupportedEncodingException {
        return encodeText(str, null, null);
    }

    public static String encodeText(String str, String str2, String str3) throws UnsupportedEncodingException {
        return encodeWord(str, str2, str3, false);
    }

    public static String decodeText(String str) throws UnsupportedEncodingException {
        String decodeInnerWords = "";
        if (-1 == str.indexOf("=?")) {
            return str;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, " \t\n\r", true);
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        boolean z = false;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            char charAt = nextToken.charAt(0);
            if (' ' == charAt || 9 == charAt || 13 == charAt || 10 == charAt) {
                stringBuffer2.append(charAt);
            } else {
                try {
                    decodeInnerWords = decodeWord(nextToken);
                    if (!z && 0 < stringBuffer2.length()) {
                        stringBuffer.append(stringBuffer2);
                    }
                    z = true;
                } catch (ParseException unused) {
                    if (!decodeStrict) {
                        decodeInnerWords = decodeInnerWords(nextToken);
                        if (decodeInnerWords != nextToken) {
                            if ((!z || !nextToken.startsWith("=?")) && 0 < stringBuffer2.length()) {
                                stringBuffer.append(stringBuffer2);
                            }
                            z = nextToken.endsWith("?=");
                        } else if (0 < stringBuffer2.length()) {
                            stringBuffer.append(stringBuffer2);
                        }
                    } else if (0 < stringBuffer2.length()) {
                        stringBuffer.append(stringBuffer2);
                    }
                    z = false;
                }
                nextToken = decodeInnerWords;
                stringBuffer.append(nextToken);
                stringBuffer2.setLength(0);
            }
        }
        stringBuffer.append(stringBuffer2);
        return stringBuffer.toString();
    }

    public static String encodeWord(String str) throws UnsupportedEncodingException {
        return encodeWord(str, null, null);
    }

    public static String encodeWord(String str, String str2, String str3) throws UnsupportedEncodingException {
        return encodeWord(str, str2, str3, true);
    }

    private static String encodeWord(String str, String str2, String str3, boolean z) throws UnsupportedEncodingException {
        String str4;
        int checkAscii = checkAscii(str);
        boolean z2 = true;
        if (1 == checkAscii) {
            return str;
        }
        if (null == str2) {
            str4 = getDefaultJavaCharset();
            str2 = getDefaultMIMECharset();
        } else {
            str4 = javaCharset(str2);
        }
        if (null == str3) {
            str3 = 3 != checkAscii ? "Q" : "B";
        }
        if (!"B".equalsIgnoreCase(str3)) {
            if ("Q".equalsIgnoreCase(str3)) {
                z2 = false;
            } else {
                final String stringBuffer = "Unknown transfer encoding: " +
                        str3;
                throw new UnsupportedEncodingException(stringBuffer);
            }
        }
        boolean z3 = z2;
        StringBuffer stringBuffer2 = new StringBuffer();
        int length = 68 - str2.length();
        final String stringBuffer3 = "=?" +
                str2 +
                "?" +
                str3 +
                "?";
        doEncode(str, z3, str4, length, stringBuffer3, true, z, stringBuffer2);
        return stringBuffer2.toString();
    }

    private static void doEncode(String str, boolean z, String str2, int i2, String str3, boolean z2, boolean z3, StringBuffer stringBuffer) throws UnsupportedEncodingException {
        int i3;
        OutputStream outputStream;
        int length;
        String str4 = str;
        boolean z4 = z3;
        StringBuffer stringBuffer2 = stringBuffer;
        byte[] bytes = str.getBytes(str2);
        if (z) {
            i3 = sun.mail.util.BEncoderStream.encodedLength(bytes);
        } else {
            i3 = sun.mail.util.QEncoderStream.encodedLength(bytes, z4);
        }
        if (i3 <= i2 || 1 >= (length = str.length())) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (z) {
                outputStream = new sun.mail.util.BEncoderStream(byteArrayOutputStream);
            } else {
                outputStream = new sun.mail.util.QEncoderStream(byteArrayOutputStream, z4);
            }
            try {
                outputStream.write(bytes);
                outputStream.close();
            } catch (IOException unused) {
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (!z2) {
                if (foldEncodedWords) {
                    stringBuffer2.append("\r\n ");
                } else {
                    stringBuffer2.append(" ");
                }
            }
            stringBuffer2.append(str3);
            for (byte b2 : byteArray) {
                stringBuffer2.append((char) b2);
            }
            stringBuffer2.append("?=");
            return;
        }
        int i4 = length / 2;
        doEncode(str.substring(0, i4), z, str2, i2, str3, z2, z3, stringBuffer);
        doEncode(str.substring(i4, length), z, str2, i2, str3, false, z3, stringBuffer);
    }

    public static String decodeWord(String str) throws ParseException, UnsupportedEncodingException {
        InputStream inputStream;
        if (str.startsWith("=?")) {
            int indexOf = str.indexOf(63, 2);
            if (-1 != indexOf) {
                String substring = str.substring(2, indexOf);
                int indexOf2 = substring.indexOf(42);
                if (0 <= indexOf2) {
                    substring = substring.substring(0, indexOf2);
                }
                String javaCharset = javaCharset(substring);
                int i2 = indexOf + 1;
                int indexOf3 = str.indexOf(63, i2);
                if (-1 != indexOf3) {
                    String substring2 = str.substring(i2, indexOf3);
                    int i3 = indexOf3 + 1;
                    int indexOf4 = str.indexOf("?=", i3);
                    if (-1 != indexOf4) {
                        String substring3 = str.substring(i3, indexOf4);
                        try {
                            String str2 = "";
                            if (0 < substring3.length()) {
                                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(sun.mail.util.ASCIIUtility.getBytes(substring3));
                                if ("B".equalsIgnoreCase(substring2)) {
                                    inputStream = new sun.mail.util.BASE64DecoderStream(byteArrayInputStream);
                                } else if ("Q".equalsIgnoreCase(substring2)) {
                                    inputStream = new sun.mail.util.QDecoderStream(byteArrayInputStream);
                                } else {
                                    final String stringBuffer = "unknown encoding: " +
                                            substring2;
                                    throw new UnsupportedEncodingException(stringBuffer);
                                }
                                int available = byteArrayInputStream.available();
                                byte[] bArr = new byte[available];
                                int read = inputStream.read(bArr, 0, available);
                                if (0 < read) {
                                    str2 = new String(bArr, 0, read, javaCharset);
                                }
                            }
                            int i4 = indexOf4 + 2;
                            if (i4 >= str.length()) {
                                return str2;
                            }
                            String substring4 = str.substring(i4);
                            if (!decodeStrict) {
                                substring4 = decodeInnerWords(substring4);
                            }
                            final String stringBuffer2 = str2 +
                                    substring4;
                            return stringBuffer2;
                        } catch (UnsupportedEncodingException e2) {
                            throw e2;
                        } catch (IOException e3) {
                            throw new ParseException(e3.toString());
                        } catch (IllegalArgumentException unused) {
                            throw new UnsupportedEncodingException(javaCharset);
                        }
                    } else {
                        final String stringBuffer3 = "encoded word does not end with \"?=\": " +
                                str;
                        throw new ParseException(stringBuffer3);
                    }
                } else {
                    final String stringBuffer4 = "encoded word does not include encoding: " +
                            str;
                    throw new ParseException(stringBuffer4);
                }
            } else {
                final String stringBuffer5 = "encoded word does not include charset: " +
                        str;
                throw new ParseException(stringBuffer5);
            }
        } else {
            final String stringBuffer6 = "encoded word does not start with \"=?\": " +
                    str;
            throw new ParseException(stringBuffer6);
        }
    }

    private static String decodeInnerWords(String str) throws UnsupportedEncodingException {
        int indexOf;
        int indexOf2;
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = 0;
        while (true) {
            int indexOf3 = str.indexOf("=?", i2);
            if (0 > indexOf3) {
                break;
            }
            stringBuffer.append(str, i2, indexOf3);
            int indexOf4 = str.indexOf(63, indexOf3 + 2);
            if (0 > indexOf4 || 0 > (indexOf = str.indexOf(63, indexOf4 + 1)) || 0 > (indexOf2 = str.indexOf("?=", indexOf + 1))) {
                break;
            }
            i2 = indexOf2 + 2;
            String substring = str.substring(indexOf3, i2);
            try {
                substring = decodeWord(substring);
            } catch (ParseException unused) {
            }
            stringBuffer.append(substring);
        }
        if (0 == i2) {
            return str;
        }
        if (i2 < str.length()) {
            stringBuffer.append(str.substring(i2));
        }
        return stringBuffer.toString();
    }

    public static String quote(String str, String str2) {
        int length = str.length();
        if (0 == length) {
            return "\"\"";
        }
        char c2 = 0;
        int i2 = 0;
        boolean z = false;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if ('\"' == charAt || '\\' == charAt || 13 == charAt || 10 == charAt) {
                StringBuffer stringBuffer = new StringBuffer(length + 3);
                stringBuffer.append(JsonFactory.DEFAULT_QUOTE_CHAR);
                stringBuffer.append(str, 0, i2);
                while (i2 < length) {
                    char charAt2 = str.charAt(i2);
                    if (('\"' == charAt2 || '\\' == charAt2 || 13 == charAt2 || 10 == charAt2) && !(10 == charAt2 && 13 == c2)) {
                        stringBuffer.append('\\');
                    }
                    stringBuffer.append(charAt2);
                    i2++;
                    c2 = charAt2;
                }
                stringBuffer.append(JsonFactory.DEFAULT_QUOTE_CHAR);
                return stringBuffer.toString();
            }
            if (' ' > charAt || 127 <= charAt || 0 <= str2.indexOf(charAt)) {
                z = true;
            }
            i2++;
        }
        if (!z) {
            return str;
        }
        final String stringBuffer2 = JsonFactory.DEFAULT_QUOTE_CHAR +
                str +
                JsonFactory.DEFAULT_QUOTE_CHAR;
        return stringBuffer2;
    }

    public static String fold(int i2, String str) {
        if (!foldText) {
            return str;
        }
        int length = str.length() - 1;
        char r4;
        while (0 <= length && (' ' == (r4 = str.charAt(length)) || 9 == r4 || 13 == r4 || 10 == r4)) {
            length--;
        }
        if (length != str.length() - 1) {
            str = str.substring(0, length + 1);
        }
        if (76 >= str.length() + i2) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 4);
        char c2 = 0;
        while (true) {
            if (76 >= str.length() + i2) {
                break;
            }
            int i3 = 0;
            int i4 = -1;
            while (i3 < str.length() && (-1 == i4 || 76 >= i2 + i3)) {
                char charAt = str.charAt(i3);
                if (!((' ' != charAt && 9 != charAt) || ' ' == c2 || 9 == c2)) {
                    i4 = i3;
                }
                i3++;
                c2 = charAt;
            }
            if (-1 == i4) {
                stringBuffer.append(str);
                str = "";
                break;
            }
            stringBuffer.append(str, 0, i4);
            stringBuffer.append("\r\n");
            c2 = str.charAt(i4);
            stringBuffer.append(c2);
            str = str.substring(i4 + 1);
            i2 = 1;
        }
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public static String unfold(String r8) {
        /*
            boolean r0 = foldText
            if (r0 != 0) goto L_0x0005
            return r8
        L_0x0005:
            r0 = 0
        L_0x0006:
            java.lang.String r1 = "\r\n"
            int r1 = indexOfAny(r8, r1)
            if (r1 < 0) goto L_0x00a4
            int r2 = r8.length()
            int r3 = r1 + 1
            if (r3 >= r2) goto L_0x0028
            char r4 = r8.charAt(r1)
            r5 = 13
            if (r4 != r5) goto L_0x0028
            char r4 = r8.charAt(r3)
            r5 = 10
            if (r4 != r5) goto L_0x0028
            int r3 = r1 + 2
        L_0x0028:
            r4 = 0
            if (r1 == 0) goto L_0x0054
            int r5 = r1 + -1
            char r6 = r8.charAt(r5)
            r7 = 92
            if (r6 == r7) goto L_0x0036
            goto L_0x0054
        L_0x0036:
            if (r0 != 0) goto L_0x0041
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            int r2 = r8.length()
            r0.<init>(r2)
        L_0x0041:
            java.lang.String r2 = r8.substring(r4, r5)
            r0.append(r2)
            java.lang.String r1 = r8.substring(r1, r3)
            r0.append(r1)
            java.lang.String r8 = r8.substring(r3)
            goto L_0x0006
        L_0x0054:
            if (r3 >= r2) goto L_0x008c
            char r5 = r8.charAt(r3)
            r6 = 9
            r7 = 32
            if (r5 == r7) goto L_0x0062
            if (r5 != r6) goto L_0x008c
        L_0x0062:
            int r3 = r3 + 1
            if (r3 >= r2) goto L_0x006f
            char r5 = r8.charAt(r3)
            if (r5 == r7) goto L_0x0062
            if (r5 != r6) goto L_0x006f
            goto L_0x0062
        L_0x006f:
            if (r0 != 0) goto L_0x007a
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            int r2 = r8.length()
            r0.<init>(r2)
        L_0x007a:
            if (r1 == 0) goto L_0x0086
            java.lang.String r1 = r8.substring(r4, r1)
            r0.append(r1)
            r0.append(r7)
        L_0x0086:
            java.lang.String r8 = r8.substring(r3)
            goto L_0x0006
        L_0x008c:
            if (r0 != 0) goto L_0x0097
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            int r1 = r8.length()
            r0.<init>(r1)
        L_0x0097:
            java.lang.String r1 = r8.substring(r4, r3)
            r0.append(r1)
            java.lang.String r8 = r8.substring(r3)
            goto L_0x0006
        L_0x00a4:
            if (r0 == 0) goto L_0x00ad
            r0.append(r8)
            java.lang.String r8 = r0.toString()
        L_0x00ad:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeUtility.unfold(java.lang.String):java.lang.String");
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

    public static String javaCharset(String r2) {
        /*
            java.util.Hashtable r0 = mime2java
            if (r0 == 0) goto L_0x0017
            if (r2 != 0) goto L_0x0007
            goto L_0x0017
        L_0x0007:
            java.util.Locale r1 = java.util.Locale.ENGLISH
            java.lang.String r1 = r2.toLowerCase(r1)
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x0016
            goto L_0x0017
        L_0x0016:
            r2 = r0
        L_0x0017:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeUtility.javaCharset(java.lang.String):java.lang.String");
    }

    public static String mimeCharset(String r2) {
        /*
            java.util.Hashtable r0 = java2mime
            if (r0 == 0) goto L_0x0017
            if (r2 != 0) goto L_0x0007
            goto L_0x0017
        L_0x0007:
            java.util.Locale r1 = java.util.Locale.ENGLISH
            java.lang.String r1 = r2.toLowerCase(r1)
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            if (r0 != 0) goto L_0x0016
            goto L_0x0017
        L_0x0016:
            r2 = r0
        L_0x0017:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeUtility.mimeCharset(java.lang.String):java.lang.String");
    }

    public static String getDefaultJavaCharset() {
        String str;
        if (null == MimeUtility.defaultJavaCharset) {
            try {
                str = System.getProperty("mail.mime.charset");
            } catch (SecurityException unused) {
                str = null;
            }
            if (null == str || 0 >= str.length()) {
                try {
                    defaultJavaCharset = System.getProperty("file.encoding", "8859_1");
                } catch (SecurityException unused2) {
                    String encoding = new InputStreamReader(new InputStream() {
                        public int read() {
                            return 0;
                        }
                    }, StandardCharsets.UTF_8).getEncoding();
                    defaultJavaCharset = encoding;
                    if (null == encoding) {
                        defaultJavaCharset = "8859_1";
                    }
                }
            } else {
                String javaCharset = javaCharset(str);
                defaultJavaCharset = javaCharset;
                return javaCharset;
            }
        }
        return defaultJavaCharset;
    }

    static String getDefaultMIMECharset() {
        if (null == MimeUtility.defaultMIMECharset) {
            try {
                defaultMIMECharset = System.getProperty("mail.mime.charset");
            } catch (SecurityException unused) {
            }
        }
        if (null == MimeUtility.defaultMIMECharset) {
            defaultMIMECharset = mimeCharset(getDefaultJavaCharset());
        }
        return defaultMIMECharset;
    }

    private static void loadMappings(sun.mail.util.LineInputStream r3, Hashtable r4) {
        /*
        L_0x0000:
            java.lang.String r0 = r3.readLine()     // Catch:{ IOException -> 0x0042 }
            if (r0 != 0) goto L_0x0007
            goto L_0x0042
        L_0x0007:
            java.lang.String r1 = "--"
            boolean r2 = r0.startsWith(r1)
            if (r2 == 0) goto L_0x0016
            boolean r1 = r0.endsWith(r1)
            if (r1 == 0) goto L_0x0016
            goto L_0x0042
        L_0x0016:
            java.lang.String r1 = r0.trim()
            int r1 = r1.length()
            if (r1 == 0) goto L_0x0000
            java.lang.String r1 = "#"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L_0x0029
            goto L_0x0000
        L_0x0029:
            java.util.StringTokenizer r1 = new java.util.StringTokenizer
            java.lang.String r2 = " \t"
            r1.<init>(r0, r2)
            java.lang.String r0 = r1.nextToken()     // Catch:{ NoSuchElementException -> 0x0000 }
            java.lang.String r1 = r1.nextToken()     // Catch:{ NoSuchElementException -> 0x0000 }
            java.util.Locale r2 = java.util.Locale.ENGLISH     // Catch:{ NoSuchElementException -> 0x0000 }
            java.lang.String r0 = r0.toLowerCase(r2)     // Catch:{ NoSuchElementException -> 0x0000 }
            r4.put(r0, r1)     // Catch:{ NoSuchElementException -> 0x0000 }
            goto L_0x0000
        L_0x0042:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeUtility.loadMappings(com.sun.mail.util.LineInputStream, java.util.Hashtable):void");
    }

    static int checkAscii(String str) {
        int length = str.length();
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            if (nonascii(str.charAt(i4))) {
                i2++;
            } else {
                i3++;
            }
        }
        if (0 == i2) {
            return 1;
        }
        return i3 > i2 ? 2 : 3;
    }

    static int checkAscii(byte[] bArr) {
        int i2 = 0;
        int i3 = 0;
        for (byte b2 : bArr) {
            if (nonascii(b2 & 255)) {
                i2++;
            } else {
                i3++;
            }
        }
        if (0 == i2) {
            return 1;
        }
        return i3 > i2 ? 2 : 3;
    }

    static int checkAscii(InputStream r16, int r17, boolean r18) {
        /*
            r0 = r17
            boolean r1 = encodeEolStrict
            r3 = 0
            if (r1 == 0) goto L_0x000b
            if (r18 == 0) goto L_0x000b
            r1 = 1
            goto L_0x000c
        L_0x000b:
            r1 = r3
        L_0x000c:
            r4 = -1
            r5 = 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L_0x0020
            if (r0 != r4) goto L_0x0014
            goto L_0x0018
        L_0x0014:
            int r5 = java.lang.Math.min(r0, r5)
        L_0x0018:
            byte[] r6 = new byte[r5]
        L_0x001a:
            r7 = r3
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            goto L_0x0022
        L_0x0020:
            r6 = 0
            goto L_0x001a
        L_0x0022:
            if (r0 == 0) goto L_0x006c
            r13 = r16
            int r14 = r13.read(r6, r3, r5)     // Catch:{ IOException -> 0x006c }
            if (r14 != r4) goto L_0x002d
            goto L_0x006c
        L_0x002d:
            r15 = r3
        L_0x002e:
            if (r15 >= r14) goto L_0x0065
            byte r2 = r6[r15]     // Catch:{ IOException -> 0x006c }
            r2 = r2 & 255(0xff, float:3.57E-43)
            r4 = 10
            r12 = 13
            if (r1 == 0) goto L_0x0043
            if (r3 != r12) goto L_0x003e
            if (r2 != r4) goto L_0x0042
        L_0x003e:
            if (r3 == r12) goto L_0x0043
            if (r2 != r4) goto L_0x0043
        L_0x0042:
            r9 = 1
        L_0x0043:
            if (r2 == r12) goto L_0x0050
            if (r2 != r4) goto L_0x0048
            goto L_0x0050
        L_0x0048:
            int r11 = r11 + 1
            r3 = 998(0x3e6, float:1.398E-42)
            if (r11 <= r3) goto L_0x0051
            r10 = 1
            goto L_0x0051
        L_0x0050:
            r11 = 0
        L_0x0051:
            boolean r3 = nonascii(r2)     // Catch:{ IOException -> 0x006c }
            if (r3 == 0) goto L_0x005e
            if (r18 == 0) goto L_0x005b
            r3 = 3
            return r3
        L_0x005b:
            int r8 = r8 + 1
            goto L_0x0060
        L_0x005e:
            int r7 = r7 + 1
        L_0x0060:
            int r15 = r15 + 1
            r3 = r2
            r4 = -1
            goto L_0x002e
        L_0x0065:
            r2 = r4
            if (r0 == r2) goto L_0x0069
            int r0 = r0 - r14
        L_0x0069:
            r4 = r2
            r3 = 0
            goto L_0x0022
        L_0x006c:
            if (r0 != 0) goto L_0x0072
            if (r18 == 0) goto L_0x0072
            r0 = 3
            return r0
        L_0x0072:
            r0 = 3
            r1 = 2
            if (r8 != 0) goto L_0x007e
            if (r9 == 0) goto L_0x0079
            return r0
        L_0x0079:
            if (r10 == 0) goto L_0x007c
            return r1
        L_0x007c:
            r0 = 1
            return r0
        L_0x007e:
            if (r7 <= r8) goto L_0x0081
            return r1
        L_0x0081:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.MimeUtility.checkAscii(java.io.InputStream, int, boolean):int");
    }
}
