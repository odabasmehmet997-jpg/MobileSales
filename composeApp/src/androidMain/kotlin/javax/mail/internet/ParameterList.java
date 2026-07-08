package javax.mail.internet;

import androidx.webkit.ProxyConfig;
import com.google.android.gms.common.data.DataBufferIterator;
import sun.mail.util.PropUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class ParameterList {
    private static final boolean applehack = PropUtil.getBooleanSystemProperty("mail.mime.applefilenames", false);
    private static final boolean decodeParameters = PropUtil.getBooleanSystemProperty("mail.mime.decodeparameters", false);
    private static final boolean decodeParametersStrict = PropUtil.getBooleanSystemProperty("mail.mime.decodeparameters.strict", false);
    private static final boolean encodeParameters = PropUtil.getBooleanSystemProperty("mail.mime.encodeparameters", false);
    private static final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final boolean parametersStrict = PropUtil.getBooleanSystemProperty("mail.mime.parameters.strict", true);
    private static final boolean windowshack = PropUtil.getBooleanSystemProperty("mail.mime.windowsfilenames", false);
    private final String lastName;
    private final Map list;
    private Set multisegmentNames;
    private Map slist;
    private static class Value {
        String charset;
        String encodedValue;
        String value;

        private Value() {
        }
    }
    private static class MultiValue extends ArrayList {
        String value;

        private MultiValue() {
        }
    }
    private class ParamEnum(Iterator it) implements Enumeration {

        private final ThreadLocal<DataBufferIterator<Object>> it = null;

        public ParamEnum(Iterator iterator) {
            this.it = it;
        }

        public boolean hasMoreElements() {
                return this.it.get().hasNext();
            }

            public Object nextElement() {
                return this.it.get().next();
            }
        }
    public ParameterList() {
        this.list = new LinkedHashMap();
        this.lastName = null;
        if (decodeParameters) {
            this.multisegmentNames = new HashSet();
            this.slist = new HashMap();
        }
    }
    public ParameterList(String r10) throws ParseException {
        /*
            r9 = this;
            r9.<init>()
            javax.mail.internet.HeaderTokenizer r0 = new javax.mail.internet.HeaderTokenizer
            java.lang.String r1 = "()<>@,;:\\\"\t []/?="
            r0.<init>(r10, r1)
        L_0x000a:
            javax.mail.internet.HeaderTokenizerToken r10 = r0.next()
            int r1 = r10.getType()
            r2 = -4
            if (r1 != r2) goto L_0x0016
            goto L_0x002c
        L_0x0016:
            char r3 = (char) r1
            java.lang.String r4 = "filename"
            java.lang.String r5 = "name"
            r6 = -1
            r7 = 59
            java.lang.String r8 = "\""
            if (r3 != r7) goto L_0x00ef
            javax.mail.internet.HeaderTokenizerToken r10 = r0.next()
            int r1 = r10.getType()
            if (r1 != r2) goto L_0x0035
        L_0x002c:
            boolean r10 = decodeParameters
            if (r10 == 0) goto L_0x0034
            r10 = 0
            r9.combineMultisegmentNames(r10)
        L_0x0034:
            return
        L_0x0035:
            int r1 = r10.getType()
            if (r1 != r6) goto L_0x00d1
            java.lang.String r10 = r10.getValue()
            java.util.Locale r1 = java.util.Locale.ENGLISH
            java.lang.String r10 = r10.toLowerCase(r1)
            javax.mail.internet.HeaderTokenizerToken r1 = r0.next()
            int r2 = r1.getType()
            char r2 = (char) r2
            r3 = 61
            if (r2 != r3) goto L_0x00b3
            boolean r1 = windowshack
            if (r1 == 0) goto L_0x0068
            boolean r1 = r10.equals(r5)
            if (r1 != 0) goto L_0x0062
            boolean r1 = r10.equals(r4)
            if (r1 == 0) goto L_0x0068
        L_0x0062:
            r1 = 1
            javax.mail.internet.HeaderTokenizerToken r1 = r0.next(r7, r1)
            goto L_0x0075
        L_0x0068:
            boolean r1 = parametersStrict
            if (r1 == 0) goto L_0x0071
            javax.mail.internet.HeaderTokenizerToken r1 = r0.next()
            goto L_0x0075
        L_0x0071:
            javax.mail.internet.HeaderTokenizerToken r1 = r0.next(r7)
        L_0x0075:
            int r2 = r1.getType()
            if (r2 == r6) goto L_0x009d
            r3 = -2
            if (r2 != r3) goto L_0x007f
            goto L_0x009d
        L_0x007f:
            javax.mail.internet.ParseException r10 = new javax.mail.internet.ParseException
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r2 = "Expected parameter value, got \""
            r0.append(r2)
            java.lang.String r1 = r1.getValue()
            r0.append(r1)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            r10.<init>(r0)
            throw r10
        L_0x009d:
            java.lang.String r1 = r1.getValue()
            r9.lastName = r10
            boolean r2 = decodeParameters
            if (r2 == 0) goto L_0x00ac
            r9.putEncodedName(r10, r1)
            goto L_0x000a
        L_0x00ac:
            java.util.Map r2 = r9.list
            r2.put(r10, r1)
            goto L_0x000a
        L_0x00b3:
            javax.mail.internet.ParseException r10 = new javax.mail.internet.ParseException
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r2 = "Expected '=', got \""
            r0.append(r2)
            java.lang.String r1 = r1.getValue()
            r0.append(r1)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            r10.<init>(r0)
            throw r10
        L_0x00d1:
            javax.mail.internet.ParseException r0 = new javax.mail.internet.ParseException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "Expected parameter name, got \""
            r1.append(r2)
            java.lang.String r10 = r10.getValue()
            r1.append(r10)
            r1.append(r8)
            java.lang.String r10 = r1.toString()
            r0.<init>(r10)
            throw r0
        L_0x00ef:
            if (r1 != r6) goto L_0x0136
            java.lang.String r1 = r9.lastName
            if (r1 == 0) goto L_0x0136
            boolean r2 = applehack
            if (r2 == 0) goto L_0x0107
            boolean r1 = r1.equals(r5)
            if (r1 != 0) goto L_0x010b
            java.lang.String r1 = r9.lastName
            boolean r1 = r1.equals(r4)
            if (r1 != 0) goto L_0x010b
        L_0x0107:
            boolean r1 = parametersStrict
            if (r1 != 0) goto L_0x0136
        L_0x010b:
            java.util.Map r1 = r9.list
            java.lang.String r2 = r9.lastName
            java.lang.Object r1 = r1.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            r2.append(r1)
            java.lang.String r1 = " "
            r2.append(r1)
            java.lang.String r10 = r10.getValue()
            r2.append(r10)
            java.lang.String r10 = r2.toString()
            java.util.Map r1 = r9.list
            java.lang.String r2 = r9.lastName
            r1.put(r2, r10)
            goto L_0x000a
        L_0x0136:
            javax.mail.internet.ParseException r0 = new javax.mail.internet.ParseException
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = "Expected ';', got \""
            r1.append(r2)
            java.lang.String r10 = r10.getValue()
            r1.append(r10)
            r1.append(r8)
            java.lang.String r10 = r1.toString()
            r0.<init>(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.ParameterList.<init>(java.lang.String):void");
    }
    private void putEncodedName(String str, String str2) throws ParseException {
        Object obj;
        int indexOf = str.indexOf(42);
        if (0 > indexOf) {
            this.list.put(str, str2);
        } else if (indexOf == str.length() - 1) {
            String substring = str.substring(0, indexOf);
            Value extractCharset = extractCharset(str2);
            try {
                extractCharset.value = decodeBytes(extractCharset.value, extractCharset.charset);
            } catch (UnsupportedEncodingException e2) {
                if (decodeParametersStrict) {
                    throw new ParseException(e2.toString());
                }
            }
            this.list.put(substring, extractCharset);
        } else {
            String substring2 = str.substring(0, indexOf);
            this.multisegmentNames.add(substring2);
            this.list.put(substring2, "");
            Object obj2 = str2;
            if (str.endsWith(ProxyConfig.MATCH_ALL_SCHEMES)) {
                if (str.endsWith("*0*")) {
                    obj = extractCharset(str2);
                } else {
                    Value value = new Value();
                    value.encodedValue = str2;
                    value.value = str2;
                    obj = value;
                }
                str = str.substring(0, str.length() - 1);
                obj2 = obj;
            }
            this.slist.put(str, obj2);
        }
    }
    private void combineMultisegmentNames(boolean r11) throws ParseException {
        /*
            r10 = this;
            java.util.Set r0 = r10.multisegmentNames     // Catch:{ all -> 0x004a }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x004a }
        L_0x0006:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x004a }
            if (r1 == 0) goto L_0x009f
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x004a }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x004a }
            javax.mail.internet.ParameterListMultiValue r2 = new javax.mail.internet.ParameterListMultiValue     // Catch:{ all -> 0x004a }
            r3 = 0
            r2.<init>()     // Catch:{ all -> 0x004a }
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x004a }
            r4.<init>()     // Catch:{ all -> 0x004a }
            r5 = 0
            r6 = r5
        L_0x001f:
            java.lang.StringBuffer r7 = new java.lang.StringBuffer     // Catch:{ all -> 0x004a }
            r7.<init>()     // Catch:{ all -> 0x004a }
            r7.append(r1)     // Catch:{ all -> 0x004a }
            java.lang.String r8 = "*"
            r7.append(r8)     // Catch:{ all -> 0x004a }
            r7.append(r6)     // Catch:{ all -> 0x004a }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x004a }
            java.util.Map r8 = r10.slist     // Catch:{ all -> 0x004a }
            java.lang.Object r8 = r8.get(r7)     // Catch:{ all -> 0x004a }
            if (r8 != 0) goto L_0x003c
            goto L_0x0054
        L_0x003c:
            r2.add(r8)     // Catch:{ all -> 0x004a }
            boolean r9 = r8 instanceof javax.mail.internet.ParameterList.Value     // Catch:{ IOException -> 0x0097 }
            if (r9 == 0) goto L_0x008e
            javax.mail.internet.ParameterListValue r8 = (javax.mail.internet.ParameterList.Value) r8     // Catch:{ IOException -> 0x0097 }
            if (r6 != 0) goto L_0x004d
            java.lang.String r3 = r8.charset     // Catch:{ IOException -> 0x0097 }
            goto L_0x0088
        L_0x004a:
            r0 = move-exception
            goto L_0x00ee
        L_0x004d:
            if (r3 != 0) goto L_0x0088
            java.util.Set r8 = r10.multisegmentNames     // Catch:{ IOException -> 0x0097 }
            r8.remove(r1)     // Catch:{ IOException -> 0x0097 }
        L_0x0054:
            if (r6 != 0) goto L_0x005c
            java.util.Map r2 = r10.list     // Catch:{ all -> 0x004a }
            r2.remove(r1)     // Catch:{ all -> 0x004a }
            goto L_0x0006
        L_0x005c:
            if (r3 == 0) goto L_0x0067
            java.lang.String r3 = r4.toString(r3)     // Catch:{ UnsupportedEncodingException -> 0x0065 }
            r2.value = r3     // Catch:{ UnsupportedEncodingException -> 0x0065 }
            goto L_0x0078
        L_0x0065:
            r3 = move-exception
            goto L_0x006e
        L_0x0067:
            java.lang.String r3 = r4.toString()     // Catch:{ UnsupportedEncodingException -> 0x0065 }
            r2.value = r3     // Catch:{ UnsupportedEncodingException -> 0x0065 }
            goto L_0x0078
        L_0x006e:
            boolean r6 = decodeParametersStrict     // Catch:{ all -> 0x004a }
            if (r6 != 0) goto L_0x007e
            java.lang.String r3 = r4.toString(r5)     // Catch:{ all -> 0x004a }
            r2.value = r3     // Catch:{ all -> 0x004a }
        L_0x0078:
            java.util.Map r3 = r10.list     // Catch:{ all -> 0x004a }
            r3.put(r1, r2)     // Catch:{ all -> 0x004a }
            goto L_0x0006
        L_0x007e:
            javax.mail.internet.ParseException r0 = new javax.mail.internet.ParseException     // Catch:{ all -> 0x004a }
            java.lang.String r1 = r3.toString()     // Catch:{ all -> 0x004a }
            r0.<init>(r1)     // Catch:{ all -> 0x004a }
            throw r0     // Catch:{ all -> 0x004a }
        L_0x0088:
            java.lang.String r8 = r8.value     // Catch:{ IOException -> 0x0097 }
            decodeBytes((java.lang.String) r8, (java.io.OutputStream) r4)     // Catch:{ IOException -> 0x0097 }
            goto L_0x0097
        L_0x008e:
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ IOException -> 0x0097 }
            byte[] r8 = com.sun.mail.util.ASCIIUtility.getBytes((java.lang.String) r8)     // Catch:{ IOException -> 0x0097 }
            r4.write(r8)     // Catch:{ IOException -> 0x0097 }
        L_0x0097:
            java.util.Map r8 = r10.slist     // Catch:{ all -> 0x004a }
            r8.remove(r7)     // Catch:{ all -> 0x004a }
            int r6 = r6 + 1
            goto L_0x001f
        L_0x009f:
            java.util.Map r11 = r10.slist
            int r11 = r11.size()
            if (r11 <= 0) goto L_0x00e3
            java.util.Map r11 = r10.slist
            java.util.Collection r11 = r11.values()
            java.util.Iterator r11 = r11.iterator()
        L_0x00b1:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L_0x00dc
            java.lang.Object r0 = r11.next()
            boolean r1 = r0 instanceof javax.mail.internet.ParameterList.Value
            if (r1 == 0) goto L_0x00b1
            javax.mail.internet.ParameterListValue r0 = (javax.mail.internet.ParameterList.Value) r0
            java.lang.String r1 = r0.value     // Catch:{ UnsupportedEncodingException -> 0x00cc }
            java.lang.String r2 = r0.charset     // Catch:{ UnsupportedEncodingException -> 0x00cc }
            java.lang.String r1 = decodeBytes((java.lang.String) r1, (java.lang.String) r2)     // Catch:{ UnsupportedEncodingException -> 0x00cc }
            r0.value = r1     // Catch:{ UnsupportedEncodingException -> 0x00cc }
            goto L_0x00b1
        L_0x00cc:
            r0 = move-exception
            boolean r1 = decodeParametersStrict
            if (r1 != 0) goto L_0x00d2
            goto L_0x00b1
        L_0x00d2:
            javax.mail.internet.ParseException r11 = new javax.mail.internet.ParseException
            java.lang.String r0 = r0.toString()
            r11.<init>(r0)
            throw r11
        L_0x00dc:
            java.util.Map r11 = r10.list
            java.util.Map r0 = r10.slist
            r11.putAll(r0)
        L_0x00e3:
            java.util.Set r11 = r10.multisegmentNames
            r11.clear()
            java.util.Map r11 = r10.slist
            r11.clear()
            return
        L_0x00ee:
            if (r11 == 0) goto L_0x013e
            java.util.Map r11 = r10.slist
            int r11 = r11.size()
            if (r11 <= 0) goto L_0x0134
            java.util.Map r11 = r10.slist
            java.util.Collection r11 = r11.values()
            java.util.Iterator r11 = r11.iterator()
        L_0x0102:
            boolean r1 = r11.hasNext()
            if (r1 == 0) goto L_0x012d
            java.lang.Object r1 = r11.next()
            boolean r2 = r1 instanceof javax.mail.internet.ParameterList.Value
            if (r2 == 0) goto L_0x0102
            javax.mail.internet.ParameterListValue r1 = (javax.mail.internet.ParameterList.Value) r1
            java.lang.String r2 = r1.value     // Catch:{ UnsupportedEncodingException -> 0x011d }
            java.lang.String r3 = r1.charset     // Catch:{ UnsupportedEncodingException -> 0x011d }
            java.lang.String r2 = decodeBytes((java.lang.String) r2, (java.lang.String) r3)     // Catch:{ UnsupportedEncodingException -> 0x011d }
            r1.value = r2     // Catch:{ UnsupportedEncodingException -> 0x011d }
            goto L_0x0102
        L_0x011d:
            r1 = move-exception
            boolean r2 = decodeParametersStrict
            if (r2 != 0) goto L_0x0123
            goto L_0x0102
        L_0x0123:
            javax.mail.internet.ParseException r11 = new javax.mail.internet.ParseException
            java.lang.String r0 = r1.toString()
            r11.<init>(r0)
            throw r11
        L_0x012d:
            java.util.Map r11 = r10.list
            java.util.Map r1 = r10.slist
            r11.putAll(r1)
        L_0x0134:
            java.util.Set r11 = r10.multisegmentNames
            r11.clear()
            java.util.Map r11 = r10.slist
            r11.clear()
        L_0x013e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.internet.ParameterList.combineMultisegmentNames(boolean):void");
    }
    public int size() {
        return this.list.size();
    }
    public String get(String str) {
        Object obj = this.list.get(str.trim().toLowerCase(Locale.ENGLISH));
        if (obj instanceof MultiValue) {
            return ((MultiValue) obj).value;
        }
        if (obj instanceof Value) {
            return ((Value) obj).value;
        }
        return (String) obj;
    }
    public void set(String str, String str2) {
        if (null != str || !"DONE".equals(str2)) {
            String lowerCase = str.trim().toLowerCase(Locale.ENGLISH);
            if (decodeParameters) {
                try {
                    putEncodedName(lowerCase, str2);
                } catch (ParseException unused) {
                    this.list.put(lowerCase, str2);
                }
            } else {
                this.list.put(lowerCase, str2);
            }
        } else if (decodeParameters && 0 < multisegmentNames.size()) {
            try {
                combineMultisegmentNames(true);
            } catch (ParseException unused2) {
            }
        }
    }
    public void set(String str, String str2, String str3) {
        if (encodeParameters) {
            Value encodeValue = encodeValue(str2, str3);
            if (null != encodeValue) {
                this.list.put(str.trim().toLowerCase(Locale.ENGLISH), encodeValue);
            } else {
                set(str, str2);
            }
        } else {
            set(str, str2);
        }
    }
    public void remove(String str) {
        this.list.remove(str.trim().toLowerCase(Locale.ENGLISH));
    }
    public Enumeration getNames() {
        return new ParamEnum(this.list.keySet().iterator());
    }
    public String toString() {
        return toString(0);
    }
    public String toString(int i2) {
        ToStringBuffer toStringBuffer = new ToStringBuffer(i2);
        for (Object str : this.list.keySet()) {
            Object obj = this.list.get(str);
            if (obj instanceof final MultiValue multiValue) {
                String stringBuffer2 = str +
                        ProxyConfig.MATCH_ALL_SCHEMES;
                for (int i3 = 0; i3 < multiValue.size(); i3++) {
                    Object obj2 = multiValue.get(i3);
                    if (obj2 instanceof Value) {
                        final String stringBuffer3 = stringBuffer2 +
                                i3 +
                                ProxyConfig.MATCH_ALL_SCHEMES;
                        toStringBuffer.addNV(stringBuffer3, ((Value) obj2).encodedValue);
                    } else {
                        final String stringBuffer4 = stringBuffer2 +
                                i3;
                        toStringBuffer.addNV(stringBuffer4, (String) obj2);
                    }
                }
            } else if (obj instanceof Value) {
                final String stringBuffer5 = str +
                        ProxyConfig.MATCH_ALL_SCHEMES;
                toStringBuffer.addNV(stringBuffer5, ((Value) obj).encodedValue);
            } else {
                toStringBuffer.addNV((String) str, (String) obj);
            }
        }
        return toStringBuffer.toString();
    }
    private static class ToStringBuffer {
        private final StringBuffer sb = new StringBuffer();
        private int used;

        public ToStringBuffer(int i2) {
            this.used = i2;
        }

        public void addNV(String str, String str2) {
            String access200 = ParameterList.quote(str2);
            this.sb.append("; ");
            this.used += 2;
            if (76 < used + str.length() + access200.length() + 1) {
                this.sb.append("\r\n\t");
                this.used = 8;
            }
            StringBuffer stringBuffer = this.sb;
            stringBuffer.append(str);
            stringBuffer.append('=');
            int length = this.used + str.length() + 1;
            this.used = length;
            if (76 < length + access200.length()) {
                String fold = MimeUtility.fold(this.used, access200);
                this.sb.append(fold);
                int lastIndexOf = fold.lastIndexOf(10);
                if (0 <= lastIndexOf) {
                    this.used += (fold.length() - lastIndexOf) - 1;
                } else {
                    this.used += fold.length();
                }
            } else {
                this.sb.append(access200);
                this.used += access200.length();
            }
        }

        public String toString() {
            return this.sb.toString();
        }
    }
    public static String quote(String str) {
        return MimeUtility.quote(str, HeaderTokenizer.MIME);
    }
    private static Value encodeValue(String str, String str2) {
        if (1 == MimeUtility.checkAscii(str)) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes(MimeUtility.javaCharset(str2));
            StringBuffer stringBuffer = new StringBuffer(bytes.length + str2.length() + 2);
            stringBuffer.append(str2);
            stringBuffer.append("''");
            for (byte b2 : bytes) {
                char c2 = (char) (b2 & 255);
                if (' ' >= c2 || 127 <= c2 || '*' == c2 || '\'' == c2 || '%' == c2 || 0 <= HeaderTokenizer.MIME.indexOf(c2)) {
                    stringBuffer.append('%');
                    char[] cArr = hex;
                    stringBuffer.append(cArr[c2 >> 4]);
                    stringBuffer.append(cArr[c2 & 15]);
                } else {
                    stringBuffer.append(c2);
                }
            }
            Value value = new Value();
            value.charset = str2;
            value.value = str;
            value.encodedValue = stringBuffer.toString();
            return value;
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }
    private static Value extractCharset(String str) throws ParseException {
        Value value = new Value();
        value.encodedValue = str;
        value.value = str;
        try {
            int indexOf = str.indexOf(39);
            if (0 < indexOf) {
                String substring = str.substring(0, indexOf);
                int i2 = indexOf + 1;
                int indexOf2 = str.indexOf(39, i2);
                if (0 <= indexOf2) {
                    str.substring(i2, indexOf2);
                    value.value = str.substring(indexOf2 + 1);
                    value.charset = substring;
                    return value;
                } else if (!decodeParametersStrict) {
                    return value;
                } else {
                    final String stringBuffer = "Missing language in encoded value: " +
                            str;
                    throw new ParseException(stringBuffer);
                }
            } else if (!decodeParametersStrict) {
                return value;
            } else {
                final String stringBuffer2 = "Missing charset in encoded value: " +
                        str;
                throw new ParseException(stringBuffer2);
            }
        } catch (NumberFormatException e2) {
            if (decodeParametersStrict) {
                throw new ParseException(e2.toString());
            }
        } catch (StringIndexOutOfBoundsException e3) {
            if (decodeParametersStrict) {
                throw new ParseException(e3.toString());
            }
        }
        return value;
    }
    private static String decodeBytes(String str, String str2) throws ParseException, UnsupportedEncodingException {
        byte[] bArr = new byte[str.length()];
        int i2 = 0;
        int i3 = 0;
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if ('%' == charAt) {
                try {
                    charAt = (char) Integer.parseInt(str.substring(i2 + 1, i2 + 3), 16);
                    i2 += 2;
                } catch (NumberFormatException e2) {
                    if (decodeParametersStrict) {
                        throw new ParseException(e2.toString());
                    }
                } catch (StringIndexOutOfBoundsException e3) {
                    if (decodeParametersStrict) {
                        throw new ParseException(e3.toString());
                    }
                }
            }
            bArr[i3] = (byte) charAt;
            i2++;
            i3++;
        }
        String javaCharset = MimeUtility.javaCharset(str2);
        if (null == javaCharset) {
            javaCharset = MimeUtility.getDefaultJavaCharset();
        }
        return new String(bArr, 0, i3, javaCharset);
    }
    private static void decodeBytes(String str, OutputStream outputStream) throws ParseException, IOException {
        int i2 = 0;
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if ('%' == charAt) {
                try {
                    charAt = (char) Integer.parseInt(str.substring(i2 + 1, i2 + 3), 16);
                    i2 += 2;
                } catch (NumberFormatException e2) {
                    if (decodeParametersStrict) {
                        throw new ParseException(e2.toString());
                    }
                } catch (StringIndexOutOfBoundsException e3) {
                    if (decodeParametersStrict) {
                        throw new ParseException(e3.toString());
                    }
                }
            }
            outputStream.write((byte) charAt);
            i2++;
        }
    }
}
