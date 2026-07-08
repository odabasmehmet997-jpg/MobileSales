package org.kobjects.xml;

import java.io.IOException;
import java.io.Reader;
import java.util.Hashtable;

public class XmlReader {
    static final int CDSECT = 5;
    public static final int END_DOCUMENT = 1;
    public static final int END_TAG = 3;
    static final int ENTITY_REF = 6;
    private static final int LEGACY = 999;
    public static final int START_DOCUMENT = 0;
    public static final int START_TAG = 2;
    public static final int TEXT = 4;
    private static final String UNEXPECTED_EOF = "Unexpected EOF";
    private final String[] TYPES;
    private int attributeCount;
    private String[] attributes;
    private int column;
    private boolean degenerated;
    private int depth;
    private String[] elementStack = new String[4];
    private final Hashtable entityMap;
    private boolean eof;
    public boolean isWhitespace;
    private int line;
    private String name;
    private int peek0;
    private int peek1;
    private final Reader reader;
    public boolean relaxed;
    private final char[] srcBuf;
    private int srcCount;
    private int srcPos;
    private String text;
    private char[] txtBuf;
    private int txtPos;
    private int type;
    public boolean z;
    private int read() throws IOException {
        final int i2 = peek0;
        final int i3 = peek1;
        peek0 = i3;
        if (-1 == i3) {
            eof = true;
            return i2;
        }
        if (10 == i2 || 13 == i2) {
            line++;
            column = 0;
            if (13 == i2 && 10 == i3) {
                peek0 = 0;
            }
        }
        column++;
        if (srcPos >= srcCount) {
            final Reader reader = this.reader;
            final char[] cArr = srcBuf;
            final int read = reader.read(cArr, 0, cArr.length);
            srcCount = read;
            if (0 >= read) {
                peek1 = -1;
                return i2;
            }
            srcPos = 0;
        }
        final char[] cArr2 = srcBuf;
        final int i4 = srcPos;
        srcPos = i4 + 1;
        peek1 = cArr2[i4];
        return i2;
    }
    private void exception(final String str) throws IOException {
        throw new IOException(str + " pos: " + this.getPositionDescription());
    }
    private void push(final int i2) {
        if (0 == i2) {
            return;
        }
        final int i3 = txtPos;
        final char[] cArr = txtBuf;
        if (i3 == cArr.length) {
            final char[] cArr2 = new char[((i3 * 4) / 3) + 4];
            System.arraycopy(cArr, 0, cArr2, 0, i3);
            txtBuf = cArr2;
        }
        final char[] cArr3 = txtBuf;
        final int i4 = txtPos;
        txtPos = i4 + 1;
        cArr3[i4] = (char) i2;
    }
    private void read(final char c2) throws IOException {
        if (this.read() != c2) {
            if (relaxed) {
                if (' ' >= c2) {
                    this.skip();
                    this.read();
                    return;
                }
                return;
            }
            this.exception("expected: '" + c2 + "'");
        }
    }
    private void skip() throws IOException {
        while (!eof && 32 >= this.peek0) {
            this.read();
        }
    }
    private String pop(final int i2) {
        final String str = new String(txtBuf, i2, txtPos - i2);
        txtPos = i2;
        return str;
    }
    private String readName() throws IOException {
        final int i2 = txtPos;
        final int i3 = peek0;
        if ((97 > i3 || 122 < i3) && ((65 > i3 || 90 < i3) && 95 != i3 && 58 != i3 && !relaxed)) {
            this.exception("name expected");
        }
        while (true) {
            this.push(this.read());
            final int i4 = peek0;
            if (97 > i4 || 122 < i4) {
                if (65 > i4 || 90 < i4) {
                    if (48 > i4 || 57 < i4) {
                        if (95 != i4 && 45 != i4 && 58 != i4 && 46 != i4) {
                            return this.pop(i2);
                        }
                    }
                }
            }
        }
    }
    private void parseLegacy(final boolean z) throws IOException {
        final String str;
        int i2;
        this.read();
        final int read = this.read();
        if (63 == read) {
            str = "";
            i2 = 63;
        } else if (33 == read) {
            i2 = 45;
            if (45 == this.peek0) {
                str = "--";
            } else {
                str = "DOCTYPE";
                i2 = -1;
            }
        } else {
            if (91 != read) {
                this.exception("cantreachme: " + read);
            }
            str = "CDATA[";
            i2 = 93;
        }
        for (int i3 = 0; i3 < str.length(); i3++) {
            this.read(str.charAt(i3));
        }
        if (-1 == i2) {
            this.parseDoctype();
            return;
        }
        while (true) {
            if (eof) {
                this.exception(XmlReader.UNEXPECTED_EOF);
            }
            final int read2 = this.read();
            if (z) {
                this.push(read2);
            }
            if (63 == i2 || read2 == i2) {
                if (peek0 == i2 && 62 == this.peek1) {
                    break;
                }
            }
        }
        this.read();
        this.read();
        if (!z || 63 == i2) {
            return;
        }
        this.pop(txtPos - 1);
    }
    private void parseDoctype() throws IOException {
        int i2 = 1;
        while (true) {
            final int read = this.read();
            if (-1 == read) {
                this.exception(XmlReader.UNEXPECTED_EOF);
            } else if (60 != read) {
                if (62 == read && 0 == i2 - 1) {
                    return;
                }
            }
            i2++;
        }
    }
    private void parseEndTag() throws IOException {
        this.read();
        this.read();
        name = this.readName();
        if (0 == this.depth && !relaxed) {
            this.exception("element stack empty");
        }
        if (name.equals(elementStack[depth - 1])) {
            depth--;
        } else if (!relaxed) {
            this.exception("expected: " + elementStack[depth]);
        }
        this.skip();
        this.read('>');
    }
    private int peekType() {
        final int i2 = peek0;
        if (-1 == i2) {
            return 1;
        }
        if (38 == i2) {
            return 6;
        }
        if (60 != i2) {
            return 4;
        }
        final int i3 = peek1;
        if (33 == i3) {
            return 999;
        }
        if (47 == i3) {
            return 3;
        }
        if (63 != i3) {
            return 91 != i3 ? 2 : 5;
        }
        return 999;
    }
    private static String[] ensureCapacity(final String[] strArr, final int i2) {
        if (strArr.length >= i2) {
            return strArr;
        }
        final String[] strArr2 = new String[i2 + 16];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        return strArr2;
    }
    private void parseStartTag() throws IOException {
        this.read();
        name = this.readName();
        final String[] ensureCapacity = XmlReader.ensureCapacity(elementStack, depth + 1);
        elementStack = ensureCapacity;
        final int i2 = depth;
        depth = i2 + 1;
        ensureCapacity[i2] = name;
        while (true) {
            this.skip();
            final int i3 = peek0;
            if (47 == i3) {
                degenerated = true;
                this.read();
                this.skip();
                this.read('>');
                return;
            }
            if (62 == i3) {
                this.read();
                return;
            }
            if (-1 == i3) {
                this.exception(XmlReader.UNEXPECTED_EOF);
            }
            final String readName = this.readName();
            if (0 == readName.length()) {
                this.exception("attr name expected");
            }
            this.skip();
            this.read('=');
            this.skip();
            int read = this.read();
            if (39 != read && 34 != read) {
                if (!relaxed) {
                    this.exception("<" + name + ">: invalid delimiter: " + ((char) read));
                }
                read = 32;
            }
            final int i4 = attributeCount;
            attributeCount = i4 + 1;
            final int i5 = i4 << 1;
            final String[] ensureCapacity2 = XmlReader.ensureCapacity(attributes, i5 + 4);
            attributes = ensureCapacity2;
            ensureCapacity2[i5] = readName;
            final int i6 = txtPos;
            this.pushText(read);
            attributes[i5 + 1] = this.pop(i6);
            if (32 != read) {
                this.read();
            }
        }
    }
    public final boolean pushEntity() throws IOException {
        this.read();
        final int i2 = txtPos;
        while (!eof && 59 != this.peek0) {
            this.push(this.read());
        }
        final String pop = this.pop(i2);
        this.read();
        boolean z = true;
        if (0 < pop.length() && '#' == pop.charAt(0)) {
            final int parseInt = 'x' == pop.charAt(1) ? Integer.parseInt(pop.substring(2), 16) : Integer.parseInt(pop.substring(1));
            this.push(parseInt);
            return 32 >= parseInt;
        }
        String str = (String) entityMap.get(pop);
        if (null == str) {
            str = "&" + pop + ";";
        }
        for (int i3 = 0; i3 < str.length(); i3++) {
            final char charAt = str.charAt(i3);
            if (' ' < charAt) {
                z = false;
            }
            this.push(charAt);
        }
        return z;
    }
    private boolean pushText(final int i2) throws IOException {
        int i3 = peek0;
        boolean z = true;
        while (!eof && i3 != i2 && (32 != i2 || (32 < i3 && 62 != i3))) {
            if (38 == i3) {
                if (!this.pushEntity()) {
                    z = false;
                }
            } else {
                if (32 < i3) {
                    z = false;
                }
                this.push(this.read());
            }
            i3 = peek0;
        }
        return z;
    }
    public XmlReader(final Reader reader) throws IOException {
        srcBuf = new char[1048576 <= Runtime.getRuntime().freeMemory() ? 8192 : 128];
        txtBuf = new char[128];
        attributes = new String[16];
        TYPES = new String[]{"Start Document", "End Document", "Start Tag", "End Tag", "Text"};
        this.reader = reader;
        peek0 = reader.read();
        peek1 = reader.read();
        eof = -1 == this.peek0;
        final Hashtable hashtable = new Hashtable();
        entityMap = hashtable;
        hashtable.put("amp", "&");
        entityMap.put("apos", "'");
        entityMap.put("gt", ">");
        entityMap.put("lt", "<");
        entityMap.put("quot", "\"");
        line = 1;
        column = 1;
    }
    public void defineCharacterEntity(final String str, final String str2) {
        entityMap.put(str, str2);
    }
    public int getDepth() {
        return depth;
    }
    public String getPositionDescription() {
        final int i2 = type;
        final String[] strArr = TYPES;
        final StringBuffer stringBuffer = new StringBuffer(i2 < strArr.length ? strArr[i2] : "Other");
        stringBuffer.append(" @" + line + ":" + column + ": ");
        final int i3 = type;
        if (2 == i3 || 3 == i3) {
            stringBuffer.append('<');
            if (3 == this.type) {
                stringBuffer.append('/');
            }
            stringBuffer.append(name);
            stringBuffer.append('>');
        } else if (isWhitespace) {
            stringBuffer.append("[whitespace]");
        } else {
            stringBuffer.append(this.getText());
        }
        return stringBuffer.toString();
    }
    public int getLineNumber() {
        return line;
    }
    public int getColumnNumber() {
        return column;
    }
    public boolean isWhitespace() {
        return isWhitespace;
    }
    public String getText() {
        if (null == this.text) {
            text = this.pop(0);
        }
        return text;
    }
    public String getName() {
        return name;
    }
    public boolean isEmptyElementTag() {
        return degenerated;
    }
    public int getAttributeCount() {
        return attributeCount;
    }
    public String getAttributeName(final int i2) {
        if (i2 >= attributeCount) {
            throw new IndexOutOfBoundsException();
        }
        return attributes[i2 << 1];
    }
    public String getAttributeValue(final int i2) {
        if (i2 >= attributeCount) {
            throw new IndexOutOfBoundsException();
        }
        return attributes[(i2 << 1) + 1];
    }
    public String getAttributeValue(final String str) {
        for (int i2 = (attributeCount << 1) - 2; 0 <= i2; i2 -= 2) {
            if (attributes[i2].equals(str)) {
                return attributes[i2 + 1];
            }
        }
        return null;
    }
    public int getType() {
        return this.type;
    }
    public int next(){
        if (this.degenerated) {
            this.type = 3;
            this.degenerated = false;
            this.depth--;
            return 3;
        }
        this.txtPos = 0;
        this.isWhitespace = true;
        while (true) {
            this.attributeCount = 0;
            this.name = null;
            this.text = null;
            int iPeekType = peekType();
            this.type = iPeekType;
            switch (iPeekType) {
                case 1:
                    break;
                case 2:
                    try {
                        parseStartTag();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    try {
                        parseEndTag();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    try {
                        this.isWhitespace &= pushText(60);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 5:
                    try {
                        parseLegacy(true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    this.isWhitespace = false;
                    this.type = 4;
                    break;
                case 6:
                    try {
                        this.isWhitespace &= pushEntity();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    this.type = 4;
                    break;
                default:
                    try {
                        parseLegacy(false);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
            }
            int i2 = this.type;
        }
        z = isWhitespace;
        int i3 = this.type;
        this.isWhitespace = z & (i3 == 4);
        return i3;
    }
    public void require(final int i2, final String str) throws IOException {
        if (4 == this.type && 4 != i2 && this.isWhitespace) {
            this.next();
        }
        if (i2 == this.type && (null == str || str.equals(this.name))) {
            return;
        }
        try {
            this.exception("expected: " + TYPES[i2] + "/" + str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String readText() throws IOException {
        if (4 != this.type) {
            return "";
        }
        final String text = this.getText();
        this.next();
        return text;
    }
}
