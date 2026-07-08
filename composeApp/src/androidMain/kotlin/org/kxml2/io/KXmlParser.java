package org.kxml2.io;

import androidx.core.os.EnvironmentCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.proje.mobilesales.core.sql.SqlLiteVariable;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Hashtable;


public class KXmlParser implements XmlPullParser {
    private static final String ILLEGAL_TYPE = "Wrong event type";
    private static final int LEGACY = 999;
    private static final String UNEXPECTED_EOF = "Unexpected EOF";
    private static final int XML_DECL = 998;
    private final char[] srcBuf;
    private final int[] peek = new int[2];
    private int attributeCount;
    private int column;
    private boolean degenerated;
    private int depth;
    private String encoding;
    private Hashtable entityMap;
    private String error;
    private boolean isWhitespace;
    private int line;
    private Object location;
    private String name;
    private String namespace;
    private int peekCount;
    private String prefix;
    private boolean processNsp;
    private Reader reader;
    private boolean relaxed;
    private int srcCount;
    private int srcPos;
    private Boolean standalone;
    private boolean token;
    private int txtPos;
    private int type;
    private boolean unresolved;
    private String version;
    private boolean wasCR;
    private String[] elementStack = new String[16];
    private String[] nspStack = new String[8];
    private int[] nspCounts = new int[4];
    private char[] txtBuf = new char[128];
    private String[] attributes = new String[16];

    public KXmlParser() {
        final int i = 128;
        srcBuf = new char[1048576 <= Runtime.getRuntime ().freeMemory () ? 8192 : i];
    }

    public boolean isAttributeDefault(final int i) {
        return false;
    }

    private final boolean isProp(final String str, final boolean z, final String str2) {
        if (!str.startsWith ("http://xmlpull.org/v1/doc/")) {
            return false;
        }
        if (z) {
            return str.substring (42).equals (str2);
        }
        return str.substring (40).equals (str2);
    }

    private final boolean adjustNsp() throws XmlPullParserException {
        int i;
        String str = "";
        int i2 = 0;
        boolean z = false;
        while (true) {
            i = attributeCount;
            if (i2 >= (i << 2)) {
                break;
            }
            String str2 = attributes[i2 + 2];
            final int indexOf = str2.indexOf (58);
            if (-1 != indexOf) {
                final String substring = str2.substring (0, indexOf);
                str = str2.substring (indexOf + 1);
                str2 = substring;
            } else if ("xmlns".equals (str2)) {
                str = null;
            } else {
                i2 += 4;
            }
            if (!"xmlns".equals (str2)) {
                z = true;
            } else {
                final int[] iArr = nspCounts;
                final int i3 = depth;
                final int i4 = iArr[i3];
                iArr[i3] = i4 + 1;
                final int i5 = i4 << 1;
                final String[] ensureCapacity = this.ensureCapacity(nspStack, i5 + 2);
                nspStack = ensureCapacity;
                ensureCapacity[i5] = str;
                final String[] strArr = attributes;
                final int i6 = i2 + 3;
                ensureCapacity[i5 + 1] = strArr[i6];
                if (null != str && "".equals (strArr[i6])) {
                    this.error("illegal empty namespace");
                }
                final String[] strArr2 = attributes;
                final int i7 = attributeCount - 1;
                attributeCount = i7;
                System.arraycopy (strArr2, i2 + 4, strArr2, i2, (i7 << 2) - i2);
                i2 -= 4;
            }
            i2 += 4;
        }
        if (z) {
            for (int i8 = (i << 2) - 4; 0 <= i8; i8 -= 4) {
                final int i9 = i8 + 2;
                final String str3 = attributes[i9];
                final int indexOf2 = str3.indexOf (58);
                if (0 != indexOf2 || relaxed) {
                    if (-1 != indexOf2) {
                        final String substring2 = str3.substring (0, indexOf2);
                        final String substring3 = str3.substring (indexOf2 + 1);
                        final String namespace = this.getNamespace(substring2);
                        if (null != namespace || relaxed) {
                            final String[] strArr3 = attributes;
                            strArr3[i8] = namespace;
                            strArr3[i8 + 1] = substring2;
                            strArr3[i9] = substring3;
                        } else {
                            throw new RuntimeException ("Undefined Prefix: " + substring2 + " in " + this);
                        }
                    }
                } else {
                    throw new RuntimeException ("illegal attribute name: " + str3 + " at " + this);
                }
            }
        }
        final int indexOf3 = name.indexOf (58);
        if (0 == indexOf3) {
            this.error("illegal tag name: " + name);
        }
        if (-1 != indexOf3) {
            prefix = name.substring (0, indexOf3);
            name = name.substring (indexOf3 + 1);
        }
        final String namespace2 = this.getNamespace(prefix);
        namespace = namespace2;
        if (null == namespace2) {
            if (null != this.prefix) {
                this.error("undefined prefix: " + prefix);
            }
            namespace = "";
        }
        return z;
    }

    private final String[] ensureCapacity(final String[] strArr, final int i) {
        if (strArr.length >= i) {
            return strArr;
        }
        final String[] strArr2 = new String[i + 16];
        System.arraycopy (strArr, 0, strArr2, 0, strArr.length);
        return strArr2;
    }

    private final void error(final String str) throws XmlPullParserException {
        if (!relaxed) {
            this.exception(str);
        } else if (null == this.error) {
            error = "ERR: " + str;
        }
    }

    private final void exception(String str) throws XmlPullParserException {
        if (100 <= str.length ()) {
            str = str.substring (0, 100) + SqlLiteVariable._NEW_LINE;
        }
        throw new XmlPullParserException (str, this, null);
    }

    private final void nextImpl() throws IOException, XmlPullParserException {
        throw new UnsupportedOperationException (" org.kxml2.p028io.KXmlParser.nextImpl():void");
    }

    private final int parseLegacy(boolean z) throws IOException, XmlPullParserException {
        final int i;
        final int i2;
        final String str;
        this.read();
        final int read = this.read();
        if (63 == read) {
            if ((120 == peek (0) || 88 == peek (0)) && (109 == peek (1) || 77 == peek (1))) {
                if (z) {
                    this.push(this.peek(0));
                    this.push(this.peek(1));
                }
                this.read();
                this.read();
                if ((108 == peek (0) || 76 == peek (0)) && 32 >= peek (1)) {
                    if (1 != this.line || 4 < this.column) {
                        this.error("PI must not start with xml");
                    }
                    this.parseStartTag(true);
                    int i3 = 2;
                    if (1 > this.attributeCount || !"version".equals (attributes[2])) {
                        this.error("version expected");
                    }
                    final String[] strArr = attributes;
                    version = strArr[3];
                    if (1 >= attributeCount || !"encoding".equals (strArr[6])) {
                        i3 = 1;
                    } else {
                        encoding = attributes[7];
                    }
                    if (i3 < attributeCount) {
                        final int i4 = i3 * 4;
                        if ("standalone".equals (attributes[i4 + 2])) {
                            final String str2 = attributes[i4 + 3];
                            if ("yes".equals (str2)) {
                                standalone = Boolean.TRUE;
                            } else if ("no".equals (str2)) {
                                standalone = Boolean.FALSE;
                            } else {
                                this.error("illegal standalone value: " + str2);
                            }
                            i3++;
                        }
                    }
                    if (i3 != attributeCount) {
                        this.error("illegal xmldecl");
                    }
                    isWhitespace = true;
                    txtPos = 0;
                    return 998;
                }
            }
            str = "";
            i = 8;
            i2 = 63;
        } else if (33 != read) {
            this.error("illegal: <" + read);
            return 9;
        } else if (45 == peek (0)) {
            str = "--";
            i = 9;
            i2 = 45;
        } else if (91 == peek (0)) {
            str = "[CDATA[";
            i2 = 93;
            i = 5;
            z = true;
        } else {
            str = "DOCTYPE";
            i2 = -1;
            i = 10;
        }
        for (int i5 = 0; i5 < str.length (); i5++) {
            this.read(str.charAt (i5));
        }
        if (10 == i) {
            this.parseDoctype(z);
        } else {
            int i6 = 0;
            while (true) {
                final int read2 = this.read();
                if (-1 == read2) {
                    this.error(KXmlParser.UNEXPECTED_EOF);
                    return 9;
                }
                if (z) {
                    this.push(read2);
                }
                if ((63 == i2 || read2 == i2) && this.peek(0) == i2 && 62 == peek (1)) {
                    if (45 == i2 && 45 == i6 && !relaxed) {
                        this.error("illegal comment delimiter: --->");
                    }
                    this.read();
                    this.read();
                    if (z && 63 != i2) {
                        txtPos--;
                    }
                } else {
                    i6 = read2;
                }
            }
        }
        return i;
    }

    private final void parseDoctype(final boolean z) throws IOException, XmlPullParserException {
        int i = 1;
        boolean z2 = false;
        while (true) {
            final int read = this.read();
            if (-1 != read) {
                if (39 == read) {
                    z2 = !z2;
                } else if (60 != read) {
                    if (62 == read && !z2 && 0 == i - 1) {
                        return;
                    }
                } else if (!z2) {
                    i++;
                }
                if (z) {
                    this.push(read);
                }
            } else {
                this.error(KXmlParser.UNEXPECTED_EOF);
                return;
            }
        }
    }

    private final void parseEndTag() throws IOException, XmlPullParserException {
        this.read();
        this.read();
        name = this.readName();
        this.skip();
        this.read('>');
        final int i = depth;
        final int i2 = (i - 1) << 2;
        if (0 == i) {
            this.error("element stack empty");
            type = 9;
        } else if (!relaxed) {
            final int i3 = i2 + 3;
            if (!name.equals (elementStack[i3])) {
                this.error("expected: /" + elementStack[i3] + " read: " + name);
            }
            final String[] strArr = elementStack;
            namespace = strArr[i2];
            prefix = strArr[i2 + 1];
            name = strArr[i2 + 2];
        }
    }

    private final int peekType() throws IOException {
        final int peek = this.peek(0);
        if (-1 == peek) {
            return 1;
        }
        if (38 == peek) {
            return 6;
        }
        if (60 != peek) {
            return 4;
        }
        final int peek2 = this.peek(1);
        if (33 == peek2) {
            return 999;
        }
        if (47 != peek2) {
            return 63 != peek2 ? 2 : 999;
        }
        return 3;
    }

    private final String get(final int i) {
        return new String (txtBuf, i, txtPos - i);
    }

    private final void push(final int i) {
        isWhitespace &= 32 >= i;
        final int i2 = txtPos;
        final char[] cArr = txtBuf;
        if (i2 == cArr.length) {
            final char[] cArr2 = new char[((i2 * 4) / 3) + 4];
            System.arraycopy (cArr, 0, cArr2, 0, i2);
            txtBuf = cArr2;
        }
        final char[] cArr3 = txtBuf;
        final int i3 = txtPos;
        txtPos = i3 + 1;
        cArr3[i3] = (char) i;
    }

    private final void parseStartTag(final boolean z) throws IOException, XmlPullParserException {
        if (!z) {
            this.read();
        }
        name = this.readName();
        attributeCount = 0;
        while (true) {
            this.skip();
            final int peek = this.peek(0);
            if (!z) {
                if (47 != peek) {
                    if (62 == peek && !z) {
                        this.read();
                        break;
                    }
                } else {
                    degenerated = true;
                    this.read();
                    this.skip();
                    this.read('>');
                    break;
                }
            } else if (63 == peek) {
                this.read();
                this.read('>');
                return;
            }
            if (-1 == peek) {
                this.error(KXmlParser.UNEXPECTED_EOF);
                return;
            }
            final String readName = this.readName();
            if (0 == readName.length ()) {
                this.error("attr name expected");
                break;
            }
            final int i = attributeCount;
            attributeCount = i + 1;
            final int i2 = i << 2;
            final String[] ensureCapacity = this.ensureCapacity(attributes, i2 + 4);
            attributes = ensureCapacity;
            ensureCapacity[i2] = "";
            final int i3 = i2 + 2;
            ensureCapacity[i2 + 1] = null;
            final int i4 = i2 + 3;
            ensureCapacity[i3] = readName;
            this.skip();
            if (61 != peek (0)) {
                if (!relaxed) {
                    this.error("Attr.value missing f. " + readName);
                }
                attributes[i4] = readName;
            } else {
                this.read('=');
                this.skip();
                int peek2 = this.peek(0);
                if (39 == peek2 || 34 == peek2) {
                    this.read();
                } else {
                    if (!relaxed) {
                        this.error("attr value delimiter missing!");
                    }
                    peek2 = 32;
                }
                final int i5 = txtPos;
                this.pushText(peek2, true);
                attributes[i4] = this.get(i5);
                txtPos = i5;
                if (32 != peek2) {
                    this.read();
                }
            }
        }
        final int i6 = depth;
        depth = i6 + 1;
        final int i7 = i6 << 2;
        final String[] ensureCapacity2 = this.ensureCapacity(elementStack, i7 + 4);
        elementStack = ensureCapacity2;
        ensureCapacity2[i7 + 3] = name;
        final int i8 = depth;
        final int[] iArr = nspCounts;
        if (i8 >= iArr.length) {
            final int[] iArr2 = new int[i8 + 4];
            System.arraycopy (iArr, 0, iArr2, 0, iArr.length);
            nspCounts = iArr2;
        }
        final int[] iArr3 = nspCounts;
        final int i9 = depth;
        iArr3[i9] = iArr3[i9 - 1];
        if (processNsp) {
            this.adjustNsp();
        } else {
            namespace = "";
        }
        final String[] strArr = elementStack;
        strArr[i7] = namespace;
        strArr[i7 + 1] = prefix;
        strArr[i7 + 2] = name;
    }

    private final void pushEntity() throws IOException, XmlPullParserException {
        this.push(this.read());
        final int i = txtPos;
        while (true) {
            final int peek = this.peek(0);
            boolean z = true;
            if (59 == peek) {
                this.read();
                final String str = this.get(i);
                txtPos = i - 1;
                if (token && 6 == this.type) {
                    name = str;
                }
                if ('#' == str.charAt (0)) {
                    this.push('x' == str.charAt (1) ? Integer.parseInt (str.substring (2), 16) : Integer.parseInt (str.substring (1)));
                    return;
                }
                final String str2 = (String) entityMap.get (str);
                if (null != str2) {
                    z = false;
                }
                unresolved = z;
                if (!z) {
                    for (int i2 = 0; i2 < str2.length (); i2++) {
                        this.push(str2.charAt (i2));
                    }
                    return;
                } else if (!token) {
                    this.error("unresolved: &" + str + ";");
                    return;
                } else {
                    return;
                }
            } else if (128 <= peek || ((48 <= peek && 57 >= peek) || ((97 <= peek && 122 >= peek) || ((65 <= peek && 90 >= peek) || 95 == peek || 45 == peek || 35 == peek)))) {
                this.push(this.read());
            } else {
                if (!relaxed) {
                    this.error("unterminated entity ref");
                }
                System.out.println ("broken entitiy: " + this.get(i - 1));
                return;
            }
        }
    }

    private final void pushText(final int i, final boolean z) throws IOException, XmlPullParserException {
        int peek = this.peek(0);
        int i2 = 0;
        while (-1 != peek && peek != i) {
            if (32 != i || (32 < peek && 62 != peek)) {
                if (38 == peek) {
                    if (z) {
                        this.pushEntity();
                    } else {
                        return;
                    }
                } else if (10 == peek && 2 == this.type) {
                    this.read();
                    this.push(32);
                } else {
                    this.push(this.read());
                }
                if (62 == peek && 2 <= i2 && 93 != i) {
                    this.error("Illegal: ]]>");
                }
                i2 = 93 == peek ? i2 + 1 : 0;
                peek = this.peek(0);
            } else {
                return;
            }
        }
    }

    private final void read(final char c) throws IOException, XmlPullParserException {
        final int read = this.read();
        if (read != c) {
            this.error("expected: '" + c + "' actual: '" + ((char) read) + "'");
        }
    }

    private final int read() throws IOException {
        final int i;
        if (0 == this.peekCount) {
            i = this.peek(0);
        } else {
            final int[] iArr = peek;
            final int i2 = iArr[0];
            iArr[0] = iArr[1];
            i = i2;
        }
        peekCount--;
        column++;
        if (10 == i) {
            line++;
            column = 1;
        }
        return i;
    }

    private final int peek(final int i) throws IOException {
        char c;
        char c2;
        while (i >= peekCount) {
            final char[] cArr = srcBuf;
            if (1 >= cArr.length) {
                c = (char) reader.read ();
            } else {
                final int i2 = srcPos;
                if (i2 < srcCount) {
                    srcPos = i2 + 1;
                    c = cArr[i2];
                } else {
                    final int read = reader.read (cArr, 0, cArr.length);
                    srcCount = read;
                    if (0 >= read) {
                        c2 = 65535;
                    } else {
                        c2 = srcBuf[0];
                    }
                    srcPos = 1;
                    c = c2;
                }
            }
            if ('\r' == c) {
                wasCR = true;
                final int[] iArr = peek;
                final int i3 = peekCount;
                peekCount = i3 + 1;
                iArr[i3] = 10;
            } else {
                if ('\n' != c) {
                    final int[] iArr2 = peek;
                    final int i4 = peekCount;
                    peekCount = i4 + 1;
                    iArr2[i4] = 1 == c ? 1 : 0;
                } else if (!wasCR) {
                    final int[] iArr3 = peek;
                    final int i5 = peekCount;
                    peekCount = i5 + 1;
                    iArr3[i5] = 10;
                }
                wasCR = false;
            }
        }
        return peek[i];
    }

    private final String readName() throws IOException, XmlPullParserException {
        final int i = txtPos;
        final int peek = this.peek(0);
        if ((97 > peek || 122 < peek) && ((65 > peek || 90 < peek) && 95 != peek && 58 != peek && 192 > peek && !relaxed)) {
            this.error("name expected");
        }
        while (true) {
            this.push(this.read());
            final int peek2 = this.peek(0);
            if (97 > peek2 || 122 < peek2) {
                if (65 > peek2 || 90 < peek2) {
                    if (48 > peek2 || 57 < peek2) {
                        if (!(95 == peek2 || 45 == peek2 || 58 == peek2 || 46 == peek2 || 183 <= peek2)) {
                            final String str = this.get(i);
                            txtPos = i;
                            return str;
                        }
                    }
                }
            }
        }
    }

    private final void skip() throws IOException {
        while (true) {
            final int peek = this.peek(0);
            if (32 >= peek && -1 != peek) {
                this.read();
            } else {
                return;
            }
        }
    }
    public void setInput(final Reader reader) throws XmlPullParserException {
        this.reader = reader;
        line = 1;
        column = 0;
        type = 0;
        name = null;
        namespace = null;
        degenerated = false;
        attributeCount = -1;
        encoding = null;
        version = null;
        standalone = null;
        if (null != reader) {
            srcPos = 0;
            srcCount = 0;
            peekCount = 0;
            depth = 0;
            final Hashtable hashtable = new Hashtable ();
            entityMap = hashtable;
            hashtable.put ("amp", "&");
            entityMap.put ("apos", "'");
            entityMap.put ("gt", ">");
            entityMap.put ("lt", "<");
            entityMap.put ("quot", "\"");
        }
    }
    public void setInput(final InputStream r14, final String r15) throws XmlPullParserException {
        /*
        // Method dump skipped, instructions count: 318
        */
        throw new UnsupportedOperationException (" org.kxml2.p028io.KXmlParser.setInput(java.io.InputStream, java.lang.String):void");
    }
    public boolean getFeature(final String str) {
        if (FEATURE_PROCESS_NAMESPACES.equals (str)) {
            return processNsp;
        }
        if (this.isProp(str, false, "relaxed")) {
            return relaxed;
        }
        return false;
    }

    public String getInputEncoding() {
        return encoding;
    }

    public void defineEntityReplacementText(final String str, final String str2) throws XmlPullParserException {
        final Hashtable hashtable = entityMap;
        if (null != hashtable) {
            hashtable.put (str, str2);
            return;
        }
        throw new RuntimeException ("entity replacement text must be defined after setInput!");
    }

    public Object getProperty(final String str) {
        if (this.isProp(str, true, "xmldecl-version")) {
            return version;
        }
        if (this.isProp(str, true, "xmldecl-standalone")) {
            return standalone;
        }
        if (!this.isProp(str, true, FirebaseAnalytics.Param.LOCATION)) {
            return null;
        }
        final Object obj = location;
        return null != obj ? obj : reader.toString ();
    }

    public int getNamespaceCount(final int i) {
        if (i <= depth) {
            return nspCounts[i];
        }
        throw new IndexOutOfBoundsException ();
    }

    public String getNamespacePrefix(final int i) {
        return nspStack[i << 1];
    }

    public String getNamespaceUri(final int i) {
        return nspStack[(i << 1) + 1];
    }

    public String getNamespace(final String str) {
        if ("xml".equals (str)) {
            return "http://www.w3.org/XML/1998/namespace";
        }
        if ("xmlns".equals (str)) {
            return "http://www.w3.org/2000/xmlns/";
        }
        for (int namespaceCount = (this.getNamespaceCount(depth) << 1) - 2; 0 <= namespaceCount; namespaceCount -= 2) {
            if (null == str) {
                final String[] strArr = nspStack;
                if (null == strArr[namespaceCount]) {
                    return strArr[namespaceCount + 1];
                }
            } else if (str.equals (nspStack[namespaceCount])) {
                return nspStack[namespaceCount + 1];
            }
        }
        return null;
    }

    public int getDepth() {
        return depth;
    }

    public String getPositionDescription() {
        final int i = type;
        final String[] strArr = TYPES;
        final StringBuffer stringBuffer = new StringBuffer (i < strArr.length ? strArr[i] : EnvironmentCompat.MEDIA_UNKNOWN);
        stringBuffer.append (' ');
        final int i2 = type;
        if (2 == i2 || 3 == i2) {
            if (degenerated) {
                stringBuffer.append ("(empty) ");
            }
            stringBuffer.append ('<');
            if (3 == this.type) {
                stringBuffer.append ('/');
            }
            if (null != this.prefix) {
                stringBuffer.append ("{" + namespace + "}" + prefix + ":");
            }
            stringBuffer.append (name);
            final int i3 = attributeCount << 2;
            for (int i4 = 0; i4 < i3; i4 += 4) {
                stringBuffer.append (' ');
                final int i5 = i4 + 1;
                if (null != this.attributes[i5]) {
                    stringBuffer.append ("{" + attributes[i4] + "}" + attributes[i5] + ":");
                }
                stringBuffer.append (attributes[i4 + 2] + "='" + attributes[i4 + 3] + "'");
            }
            stringBuffer.append ('>');
        } else if (7 != i2) {
            if (4 != i2) {
                stringBuffer.append (this.getText());
            } else if (isWhitespace) {
                stringBuffer.append ("(whitespace)");
            } else {
                String text = this.getText();
                if (16 < text.length ()) {
                    text = text.substring (0, 16) + "...";
                }
                stringBuffer.append (text);
            }
        }
        stringBuffer.append ("@" + line + ":" + column);
        if (null != this.location) {
            stringBuffer.append (" in ");
            stringBuffer.append (location);
        } else if (null != this.reader) {
            stringBuffer.append (" in ");
            stringBuffer.append (reader);
        }
        return stringBuffer.toString ();
    }
    public int getLineNumber() {
        return line;
    }
    public int getColumnNumber() {
        return column;
    }
    public boolean isWhitespace() throws XmlPullParserException {
        final int i = type;
        if (!(4 == i || 7 == i || 5 == i)) {
            this.exception(KXmlParser.ILLEGAL_TYPE);
        }
        return isWhitespace;
    }

    public String getText() {
        final int i = type;
        if (4 > i || (6 == i && unresolved)) {
            return null;
        }
        return this.get(0);
    }

    public char[] getTextCharacters(final int[] iArr) {
        final int i = type;
        if (4 > i) {
            iArr[0] = -1;
            iArr[1] = -1;
            return null;
        } else if (6 == i) {
            iArr[0] = 0;
            iArr[1] = name.length ();
            return name.toCharArray ();
        } else {
            iArr[0] = 0;
            iArr[1] = txtPos;
            return txtBuf;
        }
    }

    public String getNamespace() {
        return namespace;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isEmptyElementTag() throws XmlPullParserException {
        if (2 != this.type) {
            this.exception(KXmlParser.ILLEGAL_TYPE);
        }
        return degenerated;
    }

    public int getAttributeCount() {
        return attributeCount;
    }

    public String getAttributeType(final int i) {
        return "CDATA";
    }

    public String getAttributeNamespace(final int i) {
        if (i < attributeCount) {
            return attributes[i << 2];
        }
        throw new IndexOutOfBoundsException ();
    }

    public String getAttributeName(final int i) {
        if (i < attributeCount) {
            return attributes[(i << 2) + 2];
        }
        throw new IndexOutOfBoundsException ();
    }

    public String getAttributePrefix(final int i) {
        if (i < attributeCount) {
            return attributes[(i << 2) + 1];
        }
        throw new IndexOutOfBoundsException ();
    }

    public String getAttributeValue(final int i) {
        if (i < attributeCount) {
            return attributes[(i << 2) + 3];
        }
        throw new IndexOutOfBoundsException ();
    }

    public String getAttributeValue(final String str, final String str2) {
        for (int i = (attributeCount << 2) - 4; 0 <= i; i -= 4) {
            if (attributes[i + 2].equals (str2) && (null == str || attributes[i].equals (str))) {
                return attributes[i + 3];
            }
        }
        return null;
    }

    public int getEventType() throws XmlPullParserException {
        return type;
    }

    public int next() throws XmlPullParserException, IOException {
        txtPos = 0;
        isWhitespace = true;
        token = false;
        int i = 9999;
        while (true) {
            this.nextImpl();
            final int i2 = type;
            if (i2 < i) {
                i = i2;
            }
            if (6 < i || (4 <= i && 4 <= peekType ())) {
            }
        }
    }

    public int nextToken() throws XmlPullParserException, IOException {
        isWhitespace = true;
        txtPos = 0;
        token = true;
        this.nextImpl();
        return type;
    }

    public int nextTag() throws XmlPullParserException, IOException {
        this.next();
        if (4 == this.type && isWhitespace) {
            this.next();
        }
        final int i = type;
        if (!(3 == i || 2 == i)) {
            this.exception("unexpected type");
        }
        return type;
    }

    public void require(final int i, final String str, final String str2) throws XmlPullParserException, IOException {
        if (i != type || ((null != str && !str.equals (this.namespace)) || (null != str2 && !str2.equals (this.name)))) {
            this.exception("expected: " + TYPES[i] + " {" + str + "}" + str2);
        }
    }

    public String nextText() throws XmlPullParserException, IOException {
        final String str;
        if (2 != this.type) {
            this.exception("precondition: START_TAG");
        }
        this.next();
        if (4 == this.type) {
            str = this.getText();
            this.next();
        } else {
            str = "";
        }
        if (3 != this.type) {
            this.exception("END_TAG expected");
        }
        return str;
    }

    public void setFeature(final String str, final boolean z) throws XmlPullParserException {
        if (FEATURE_PROCESS_NAMESPACES.equals (str)) {
            processNsp = z;
        } else if (this.isProp(str, false, "relaxed")) {
            relaxed = z;
        } else {
            this.exception("unsupported feature: " + str);
        }
    }

    public void setProperty(final String str, final Object obj) throws XmlPullParserException {
        if (this.isProp(str, true, FirebaseAnalytics.Param.LOCATION)) {
            location = obj;
            return;
        }
        throw new XmlPullParserException ("unsupported property: " + str);
    }

    public void skipSubTree() throws XmlPullParserException, IOException {
        this.require(2, null, null);
        int i = 1;
        while (0 < i) {
            final int next = this.next();
            if (3 == next) {
                i--;
            } else if (2 == next) {
                i++;
            }
        }
    }
}
