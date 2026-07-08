package org.kxml2.wap;

import androidx.core.os.EnvironmentCompat;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class WbxmlParser implements XmlPullParser {
    static final String HEX_DIGITS = "0123456789abcdef";
    private static final String ILLEGAL_TYPE = "Wrong event type";
    private static final String UNEXPECTED_EOF = "Unexpected EOF";
    public static final int WAP_EXTENSION = 64;
    private String[] attrStartTable;
    private String[] attrValueTable;
    private int attributeCount;
    private boolean degenerated;
    private int depth;
    private String encoding;
    private InputStream in;
    private boolean isWhitespace;
    private String name;
    private String namespace;
    private String prefix;
    private boolean processNsp;
    private int publicIdentifierId;
    private byte[] stringTable;
    private String[] tagTable;
    private String text;
    private int type;
    private int version;
    private int wapCode;
    private Object wapExtensionData;
    private final int TAG_TABLE = 0;
    private final int ATTR_START_TABLE = 1;
    private final int ATTR_VALUE_TABLE = 2;
    private Hashtable cacheStringTable = null;
    private String[] elementStack = new String[16];
    private String[] nspStack = new String[8];
    private int[] nspCounts = new int[4];
    private final String[] attributes = new String[16];
    private int nextId = -2;
    private final Vector tables = new Vector();
    public void defineEntityReplacementText(String str, String str2) throws XmlPullParserException {
    }
    public int getColumnNumber() {
        return -1;
    }
    public int getLineNumber() {
        return -1;
    }
    public Object getProperty(String str) {
        return null;
    }
    public boolean isAttributeDefault(int i2) {
        return false;
    }
    public boolean getFeature(String str) {
        if (XmlPullParser.FEATURE_PROCESS_NAMESPACES.equals(str)) {
            return this.processNsp;
        }
        return false;
    }
    public String getInputEncoding() {
        return this.encoding;
    }
    public int getNamespaceCount(int i2) {
        if (i2 > this.depth) {
            throw new IndexOutOfBoundsException();
        }
        return this.nspCounts[i2];
    }
    public String getNamespacePrefix(int i2) {
        return this.nspStack[i2 << 1];
    }
    public String getNamespaceUri(int i2) {
        return this.nspStack[(i2 << 1) + 1];
    }
    public String getNamespace(String str) {
        if ("xml".equals(str)) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        if ("xmlns".equals(str)) {
            return "http://www.w3.org/2000/xmlns/";
        }
        for (int namespaceCount = (getNamespaceCount(this.depth) << 1) - 2; namespaceCount >= 0; namespaceCount -= 2) {
            if (str == null) {
                String[] strArr = this.nspStack;
                if (strArr[namespaceCount] == null) {
                    return strArr[namespaceCount + 1];
                }
            } else if (str.equals(this.nspStack[namespaceCount])) {
                return this.nspStack[namespaceCount + 1];
            }
        }
        return null;
    }
    public int getDepth() {
        return this.depth;
    }
    public String getPositionDescription() {
        int i2 = this.type;
        String[] strArr = XmlPullParser.TYPES;
        StringBuffer stringBuffer = new StringBuffer(i2 < strArr.length ? strArr[i2] : EnvironmentCompat.MEDIA_UNKNOWN);
        stringBuffer.append(' ');
        int i3 = this.type;
        if (i3 == 2 || i3 == 3) {
            if (this.degenerated) {
                stringBuffer.append("(empty) ");
            }
            stringBuffer.append('<');
            if (this.type == 3) {
                stringBuffer.append('/');
            }
            if (this.prefix != null) {
                stringBuffer.append("{" + this.namespace + "}" + this.prefix + ":");
            }
            stringBuffer.append(this.name);
            int i4 = this.attributeCount << 2;
            for (int i5 = 0; i5 < i4; i5 += 4) {
                stringBuffer.append(' ');
                int i6 = i5 + 1;
                if (this.attributes[i6] != null) {
                    stringBuffer.append("{" + this.attributes[i5] + "}" + this.attributes[i6] + ":");
                }
                stringBuffer.append(this.attributes[i5 + 2] + "='" + this.attributes[i5 + 3] + "'");
            }
            stringBuffer.append('>');
        } else if (i3 != 7) {
            if (i3 != 4) {
                stringBuffer.append(getText());
            } else if (this.isWhitespace) {
                stringBuffer.append("(whitespace)");
            } else {
                String text = getText();
                if (text.length() > 16) {
                    text = text.substring(0, 16) + "...";
                }
                stringBuffer.append(text);
            }
        }
        return stringBuffer.toString();
    }
    public boolean isWhitespace() throws XmlPullParserException {
        int i2 = this.type;
        if (i2 != 4 && i2 != 7 && i2 != 5) {
            exception(ILLEGAL_TYPE);
        }
        return this.isWhitespace;
    }
    public String getText() {
        return this.text;
    }
    public char[] getTextCharacters(int[] iArr) {
        if (this.type >= 4) {
            iArr[0] = 0;
            iArr[1] = this.text.length();
            char[] cArr = new char[this.text.length()];
            String str = this.text;
            str.getChars(0, str.length(), cArr, 0);
            return cArr;
        }
        iArr[0] = -1;
        iArr[1] = -1;
        return null;
    }
    public String getNamespace() {
        return this.namespace;
    }
    public String getName() {
        return this.name;
    }
    public String getPrefix() {
        return this.prefix;
    }
    public boolean isEmptyElementTag() throws XmlPullParserException {
        if (this.type != 2) {
            exception(ILLEGAL_TYPE);
        }
        return this.degenerated;
    }
    public int getAttributeCount() {
        return this.attributeCount;
    }
    public String getAttributeType(int i2) {
        return "CDATA";
    }
    public String getAttributeNamespace(int i2) {
        if (i2 >= this.attributeCount) {
            throw new IndexOutOfBoundsException();
        }
        return this.attributes[i2 << 2];
    }
    public String getAttributeName(int i2) {
        if (i2 >= this.attributeCount) {
            throw new IndexOutOfBoundsException();
        }
        return this.attributes[(i2 << 2) + 2];
    }
    public String getAttributePrefix(int i2) {
        if (i2 >= this.attributeCount) {
            throw new IndexOutOfBoundsException();
        }
        return this.attributes[(i2 << 2) + 1];
    }
    public String getAttributeValue(int i2) {
        if (i2 >= this.attributeCount) {
            throw new IndexOutOfBoundsException();
        }
        return this.attributes[(i2 << 2) + 3];
    }
    public String getAttributeValue(String str, String str2) {
        for (int i2 = (this.attributeCount << 2) - 4; i2 >= 0; i2 -= 4) {
            if (this.attributes[i2 + 2].equals(str2) && (str == null || this.attributes[i2].equals(str))) {
                return this.attributes[i2 + 3];
            }
        }
        return null;
    }
    public int getEventType() throws XmlPullParserException {
        return this.type;
    }
    public int next() throws XmlPullParserException, IOException {
        this.isWhitespace = true;
        int i2 = 9999;
        while (true) {
            String str = this.text;
            nextImpl();
            int i3 = this.type;
            if (i3 < i2) {
                i2 = i3;
            }
            if (i2 <= 5) {
                if (i2 >= 4) {
                    if (str != null) {
                        if (this.text != null) {
                            str = str + this.text;
                        }
                        this.text = str;
                    }
                    int iPeekId = peekId();
                    if (iPeekId != 2 && iPeekId != 3 && iPeekId != 4 && iPeekId != 68 && iPeekId != 196 && iPeekId != 131 && iPeekId != 132) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        this.type = i2;
        if (i2 > 4) {
            this.type = 4;
        }
        return this.type;
    }
    public int nextToken() throws XmlPullParserException, IOException {
        this.isWhitespace = true;
        nextImpl();
        return this.type;
    }
    public int nextTag() throws XmlPullParserException, IOException {
        next();
        if (this.type == 4 && this.isWhitespace) {
            next();
        }
        int i2 = this.type;
        if (i2 != 3 && i2 != 2) {
            exception("unexpected type");
        }
        return this.type;
    }
    public String nextText() throws XmlPullParserException, IOException {
        String text;
        if (this.type != 2) {
            exception("precondition: START_TAG");
        }
        next();
        if (this.type == 4) {
            text = getText();
            next();
        } else {
            text = "";
        }
        if (this.type != 3) {
            exception("END_TAG expected");
        }
        return text;
    }
    public void require(int i2, String str, String str2) throws XmlPullParserException, IOException {
        String str3;
        if (i2 == this.type && ((str == null || str.equals(getNamespace())) && (str2 == null || str2.equals(getName())))) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("expected: ");
        if (i2 == 64) {
            str3 = "WAP Ext.";
        } else {
            str3 = XmlPullParser.TYPES[i2] + " {" + str + "}" + str2;
        }
        sb.append(str3);
        exception(sb.toString());
    }
    public void setInput(Reader reader) throws XmlPullParserException {
        exception("InputStream required");
    }
    public void setInput(InputStream inputStream, String str) throws XmlPullParserException {
        this.in = inputStream;
        try {
            this.version = readByte();
            int i2 = readInt();
            this.publicIdentifierId = i2;
            if (i2 == 0) {
                readInt();
            }
            int i3 = readInt();
            if (str != null) {
                this.encoding = str;
            } else if (i3 == 4) {
                this.encoding = "ISO-8859-1";
            } else if (i3 == 106) {
                this.encoding = "UTF-8";
            } else {
                throw new UnsupportedEncodingException("" + i3);
            }
            int i4 = readInt();
            this.stringTable = new byte[i4];
            int i5 = 0;
            while (i5 < i4) {
                int i6 = inputStream.read(this.stringTable, i5, i4 - i5);
                if (i6 <= 0) {
                    break;
                } else {
                    i5 += i6;
                }
            }
            selectPage(0, true);
            selectPage(0, false);
        } catch (IOException unused) {
            exception("Illegal input format");
        }
    }
    public void setFeature(String str, boolean z) throws XmlPullParserException {
        if (XmlPullParser.FEATURE_PROCESS_NAMESPACES.equals(str)) {
            this.processNsp = z;
            return;
        }
        exception("unsupported feature: " + str);
    }
    public void setProperty(String str, Object obj) throws XmlPullParserException {
        throw new XmlPullParserException("unsupported property: " + str);
    }
    private boolean adjustNsp() throws XmlPullParserException {
        int i2;
        String strSubstring = "";
        int i3 = 0;
        boolean z = false;
        while (true) {
            i2 = this.attributeCount;
            if (i3 >= (i2 << 2)) {
                break;
            }
            String str = this.attributes[i3 + 2];
            int iIndexOf = str.indexOf(58);
            if (iIndexOf != -1) {
                String strSubstring2 = str.substring(0, iIndexOf);
                strSubstring = str.substring(iIndexOf + 1);
                str = strSubstring2;
            } else if (str.equals("xmlns")) {
                strSubstring = null;
            } else {
                i3 += 4;
            }
            if (str.equals("xmlns")) {
                int[] iArr = this.nspCounts;
                int i4 = this.depth;
                int i5 = iArr[i4];
                iArr[i4] = i5 + 1;
                int i6 = i5 << 1;
                String[] strArrEnsureCapacity = ensureCapacity(this.nspStack, i6 + 2);
                this.nspStack = strArrEnsureCapacity;
                strArrEnsureCapacity[i6] = strSubstring;
                String[] strArr = this.attributes;
                int i7 = i3 + 3;
                strArrEnsureCapacity[i6 + 1] = strArr[i7];
                if (strSubstring != null && strArr[i7].equals("")) {
                    exception("illegal empty namespace");
                }
                String[] strArr2 = this.attributes;
                int i8 = this.attributeCount - 1;
                this.attributeCount = i8;
                System.arraycopy(strArr2, i3 + 4, strArr2, i3, (i8 << 2) - i3);
                i3 -= 4;
            } else {
                z = true;
            }
            i3 += 4;
        }
        if (z) {
            for (int i9 = (i2 << 2) - 4; i9 >= 0; i9 -= 4) {
                int i10 = i9 + 2;
                String str2 = this.attributes[i10];
                int iIndexOf2 = str2.indexOf(58);
                if (iIndexOf2 == 0) {
                    throw new RuntimeException("illegal attribute name: " + str2 + " at " + this);
                }
                if (iIndexOf2 != -1) {
                    String strSubstring3 = str2.substring(0, iIndexOf2);
                    String strSubstring4 = str2.substring(iIndexOf2 + 1);
                    String namespace = getNamespace(strSubstring3);
                    if (namespace == null) {
                        throw new RuntimeException("Undefined Prefix: " + strSubstring3 + " in " + this);
                    }
                    String[] strArr3 = this.attributes;
                    strArr3[i9] = namespace;
                    strArr3[i9 + 1] = strSubstring3;
                    strArr3[i10] = strSubstring4;
                    for (int i11 = (this.attributeCount << 2) - 4; i11 > i9; i11 -= 4) {
                        if (strSubstring4.equals(this.attributes[i11 + 2]) && namespace.equals(this.attributes[i11])) {
                            exception("Duplicate Attribute: {" + namespace + "}" + strSubstring4);
                        }
                    }
                }
            }
        }
        int iIndexOf3 = this.name.indexOf(58);
        if (iIndexOf3 == 0) {
            exception("illegal tag name: " + this.name);
        } else if (iIndexOf3 != -1) {
            this.prefix = this.name.substring(0, iIndexOf3);
            this.name = this.name.substring(iIndexOf3 + 1);
        }
        String namespace2 = getNamespace(this.prefix);
        this.namespace = namespace2;
        if (namespace2 == null) {
            if (this.prefix != null) {
                exception("undefined prefix: " + this.prefix);
            }
            this.namespace = "";
        }
        return z;
    }
    private void setTable(int i2, int i3, String[] strArr) {
        if (this.stringTable != null) {
            throw new RuntimeException("setXxxTable must be called before setInput!");
        }
        while (true) {
            int i4 = i2 * 3;
            if (this.tables.size() < i4 + 3) {
                this.tables.addElement(null);
            } else {
                this.tables.setElementAt(strArr, i4 + i3);
                return;
            }
        }
    }
    private void exception(String str) throws XmlPullParserException {
        throw new XmlPullParserException(str, this, null);
    }
    private void selectPage(int i2, boolean z) throws XmlPullParserException {
        if (this.tables.size() == 0 && i2 == 0) {
            return;
        }
        int i3 = i2 * 3;
        if (i3 > this.tables.size()) {
            exception("Code Page " + i2 + " undefined!");
        }
        if (z) {
            this.tagTable = (String[]) this.tables.elementAt(i3 + this.TAG_TABLE);
        } else {
            this.attrStartTable = (String[]) this.tables.elementAt(this.ATTR_START_TABLE + i3);
            this.attrValueTable = (String[]) this.tables.elementAt(i3 + this.ATTR_VALUE_TABLE);
        }
    }
    private void nextImpl() throws XmlPullParserException, IOException {
        if (this.type == 3) {
            this.depth--;
        }
        if (this.degenerated) {
            this.type = 3;
            this.degenerated = false;
            return;
        }
        this.text = null;
        this.prefix = null;
        this.name = null;
        int iPeekId = peekId();
        while (iPeekId == 0) {
            this.nextId = -2;
            selectPage(readByte(), true);
            iPeekId = peekId();
        }
        this.nextId = -2;
        if (iPeekId == -1) {
            this.type = 1;
            return;
        }
        if (iPeekId == 1) {
            int i2 = (this.depth - 1) << 2;
            this.type = 3;
            String[] strArr = this.elementStack;
            this.namespace = strArr[i2];
            this.prefix = strArr[i2 + 1];
            this.name = strArr[i2 + 2];
            return;
        }
        if (iPeekId == 2) {
            this.type = 6;
            char c2 = (char) readInt();
            this.text = "" + c2;
            this.name = "#" + ((int) c2);
            return;
        }
        if (iPeekId == 3) {
            this.type = 4;
            this.text = readStrI();
            return;
        }
        switch (iPeekId) {
            case 64:
            case 65:
            case 66:
                break;
            case 67:
                throw new RuntimeException("PI curr. not supp.");
            default:
                switch (iPeekId) {
                    case 128:
                    case Wbxml.EXT_T_1 /* 129 */:
                    case Wbxml.EXT_T_2 /* 130 */:
                        break;
                    case Wbxml.STR_T /* 131 */:
                        this.type = 4;
                        this.text = readStrT();
                        return;
                    default:
                        switch (iPeekId) {
                            case Wbxml.EXT_0 /* 192 */:
                            case Wbxml.EXT_1 /* 193 */:
                            case Wbxml.EXT_2 /* 194 */:
                            case Wbxml.OPAQUE /* 195 */:
                                break;
                            default:
                                parseElement(iPeekId);
                                return;
                        }
                        break;
                }
                break;
        }
        this.type = 64;
        this.wapCode = iPeekId;
        this.wapExtensionData = parseWapExtension(iPeekId);
    }
    public Object parseWapExtension(int i2) throws XmlPullParserException, IOException {
        switch (i2) {
            case 64:
            case 65:
            case 66:
                return readStrI();
            default:
                switch (i2) {
                    case 128:
                    case Wbxml.EXT_T_1 /* 129 */:
                    case Wbxml.EXT_T_2 /* 130 */:
                        return Integer.valueOf(readInt());
                    default:
                        byte[] bArr = null;
                        switch (i2) {
                            case Wbxml.OPAQUE /* 195 */:
                                int i3 = readInt();
                                bArr = new byte[i3];
                                int i4 = i3;
                                while (i4 > 0) {
                                    i4 -= this.in.read(bArr, i3 - i4, i4);
                                }
                            case Wbxml.EXT_0 /* 192 */:
                            case Wbxml.EXT_1 /* 193 */:
                            case Wbxml.EXT_2 /* 194 */:
                                return bArr;
                            default:
                                exception("illegal id: " + i2);
                                return null;
                        }
                }
        }
    }
    public void readAttr() throws XmlPullParserException, IOException {
        StringBuffer stringBuffer;
        int i2 = readByte();
        int i3 = 0;
        while (i2 != 1) {
            while (i2 == 0) {
                selectPage(readByte(), false);
                i2 = readByte();
            }
            String strResolveId = resolveId(this.attrStartTable, i2);
            int iIndexOf = strResolveId.indexOf(61);
            if (iIndexOf == -1) {
                stringBuffer = new StringBuffer();
            } else {
                StringBuffer stringBuffer2 = new StringBuffer(strResolveId.substring(iIndexOf + 1));
                strResolveId = strResolveId.substring(0, iIndexOf);
                stringBuffer = stringBuffer2;
            }
            int i4 = readByte();
            while (true) {
                if (i4 > 128 || i4 == 0 || i4 == 2 || i4 == 3 || i4 == 131 || ((i4 >= 64 && i4 <= 66) || (i4 >= 128 && i4 <= 130))) {
                    if (i4 == 0) {
                        selectPage(readByte(), false);
                    } else if (i4 == 2) {
                        stringBuffer.append((char) readInt());
                    } else if (i4 == 3) {
                        stringBuffer.append(readStrI());
                    } else {
                        switch (i4) {
                            default:
                                switch (i4) {
                                    case 128:
                                    case Wbxml.EXT_T_1 /* 129 */:
                                    case Wbxml.EXT_T_2 /* 130 */:
                                        break;
                                    case Wbxml.STR_T /* 131 */:
                                        stringBuffer.append(readStrT());
                                        break;
                                    default:
                                        switch (i4) {
                                            case Wbxml.EXT_0 /* 192 */:
                                            case Wbxml.EXT_1 /* 193 */:
                                            case Wbxml.EXT_2 /* 194 */:
                                            case Wbxml.OPAQUE /* 195 */:
                                                break;
                                            default:
                                                stringBuffer.append(resolveId(this.attrValueTable, i4));
                                                break;
                                        }
                                        break;
                                }
                            case 64:
                            case 65:
                            case 66:
                                stringBuffer.append(resolveWapExtension(i4, parseWapExtension(i4)));
                                break;
                        }
                    }
                    i4 = readByte();
                }
            }

        }
    }
    private int peekId() throws IOException {
        if (this.nextId == -2) {
            this.nextId = this.in.read();
        }
        return this.nextId;
    }
    protected String resolveWapExtension(int i2, Object obj) {
        if (obj instanceof byte[]) {
            StringBuffer stringBuffer = new StringBuffer();
            byte[] bArr = (byte[]) obj;
            for (int i3 = 0; i3 < bArr.length; i3++) {
                stringBuffer.append(HEX_DIGITS.charAt((bArr[i3] >> 4) & 15));
                stringBuffer.append(HEX_DIGITS.charAt(bArr[i3] & 15));
            }
            return stringBuffer.toString();
        }
        return "$(" + obj + ")";
    }
    String resolveId(String[] strArr, int i2) throws IOException {
        String str;
        int i3 = i2 & 127;
        int i4 = i3 - 5;
        if (i4 == -1) {
            this.wapCode = -1;
            return readStrT();
        }
        if (i4 < 0 || strArr == null || i4 >= strArr.length || (str = strArr[i4]) == null) {
            throw new IOException("id " + i2 + " undef.");
        }
        this.wapCode = i3;
        return str;
    }
    void parseElement(int i2) throws XmlPullParserException, IOException {
        this.type = 2;
        this.name = resolveId(this.tagTable, i2 & 63);
        this.attributeCount = 0;
        if ((i2 & 128) != 0) {
            readAttr();
        }
        this.degenerated = (i2 & 64) == 0;
        int i3 = this.depth;
        this.depth = i3 + 1;
        int i4 = i3 << 2;
        String[] strArrEnsureCapacity = ensureCapacity(this.elementStack, i4 + 4);
        this.elementStack = strArrEnsureCapacity;
        strArrEnsureCapacity[i4 + 3] = this.name;
        int i5 = this.depth;
        int[] iArr = this.nspCounts;
        if (i5 >= iArr.length) {
            int[] iArr2 = new int[i5 + 4];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.nspCounts = iArr2;
        }
        int[] iArr3 = this.nspCounts;
        int i6 = this.depth;
        iArr3[i6] = iArr3[i6 - 1];
        for (int i7 = this.attributeCount - 1; i7 > 0; i7--) {
            for (int i8 = 0; i8 < i7; i8++) {
                if (getAttributeName(i7).equals(getAttributeName(i8))) {
                    exception("Duplicate Attribute: " + getAttributeName(i7));
                }
            }
        }
        if (this.processNsp) {
            adjustNsp();
        } else {
            this.namespace = "";
        }
        String[] strArr = this.elementStack;
        strArr[i4] = this.namespace;
        strArr[i4 + 1] = this.prefix;
        strArr[i4 + 2] = this.name;
    }
    private String[] ensureCapacity(String[] strArr, int i2) {
        if (strArr.length >= i2) {
            return strArr;
        }
        String[] strArr2 = new String[i2 + 16];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        return strArr2;
    }
    int readByte() throws IOException {
        int i2 = this.in.read();
        if (i2 != -1) {
            return i2;
        }
        throw new IOException(UNEXPECTED_EOF);
    }
    int readInt() throws IOException {
        int i2;
        int i3 = 0;
        do {
            i2 = readByte();
            i3 = (i3 << 7) | (i2 & 127);
        } while ((i2 & 128) != 0);
        return i3;
    }
    String readStrI() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean z = true;
        while (true) {
            int i2 = this.in.read();
            if (i2 == 0) {
                this.isWhitespace = z;
                String str = byteArrayOutputStream.toString(this.encoding);
                byteArrayOutputStream.close();
                return str;
            }
            if (i2 == -1) {
                throw new IOException(UNEXPECTED_EOF);
            }
            if (i2 > 32) {
                z = false;
            }
            byteArrayOutputStream.write(i2);
        }
    }
    String readStrT() throws IOException {
        byte[] bArr;
        int i2 = readInt();
        if (this.cacheStringTable == null) {
            this.cacheStringTable = new Hashtable();
        }
        String str = (String) this.cacheStringTable.get(Integer.valueOf(i2));
        if (str != null) {
            return str;
        }
        int i3 = i2;
        while (true) {
            bArr = this.stringTable;
            if (i3 >= bArr.length || bArr[i3] == 0) {
                break;
            }
            i3++;
        }
        String str2 = new String(bArr, i2, i3 - i2, this.encoding);
        this.cacheStringTable.put(Integer.valueOf(i2), str2);
        return str2;
    }
    public void setTagTable(int i2, String[] strArr) {
        setTable(i2, this.TAG_TABLE, strArr);
    }
    public void setAttrStartTable(int i2, String[] strArr) {
        setTable(i2, this.ATTR_START_TABLE, strArr);
    }
    public void setAttrValueTable(int i2, String[] strArr) {
        setTable(i2, this.ATTR_VALUE_TABLE, strArr);
    }
    public int getWapCode() {
        return this.wapCode;
    }
    public Object getWapExtensionData() {
        return this.wapExtensionData;
    }
}
