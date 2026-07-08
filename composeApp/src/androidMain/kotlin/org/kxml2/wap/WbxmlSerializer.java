package org.kxml2.wap;

import org.xmlpull.v1.XmlSerializer;
import java.io.*;
import java.util.Hashtable;
import java.util.Vector;

public class WbxmlSerializer implements XmlSerializer {
    int depth;
    String name;
    String namespace;
    OutputStream out;
    String pending;
    Hashtable stringTable = new Hashtable ();
    ByteArrayOutputStream buf = new ByteArrayOutputStream ();
    ByteArrayOutputStream stringTableBuf = new ByteArrayOutputStream ();
    Vector attributes = new Vector ();
    Hashtable attrStartTable = new Hashtable ();
    Hashtable attrValueTable = new Hashtable ();
    Hashtable tagTable = new Hashtable ();
    private int attrPage;
    private String encoding;
    private int tagPage;
    private boolean headerSent;

    static void writeInt(OutputStream outputStream, int i) throws IOException {
        int i2;
        byte[] bArr = new byte[5];
        int i3 = 0;
        while (true) {
            i2 = i3 + 1;
            bArr[i3] = (byte) (i & 127);
            i >>= 7;
            if (0 == i) {
                break;
            }
            i3 = i2;
        }
        while (1 < i2) {
            i2--;
            outputStream.write (bArr[i2] | 128);
        }
        outputStream.write (bArr[0]);
    }

    public void comment(String str) {
    }

    public boolean getFeature(String str) {
        return false;
    }

    public String getNamespace() {
        return null;
    }

    public Object getProperty(String str) {
        return null;
    }

    public void ignorableWhitespace(String str) {
    }

    public XmlSerializer attribute(String str, String str2, String str3) {
        this.attributes.addElement (str2);
        this.attributes.addElement (str3);
        return this;
    }

    public void cdsect(String str) throws IOException {
        text (str);
    }

    public void docdecl(String str) {
        throw new RuntimeException ("Cannot write docdecl for WBXML");
    }

    public void entityRef(String str) {
        throw new RuntimeException ("EntityReference not supported for WBXML");
    }

    public int getDepth() {
        return this.depth;
    }

    public String getName() {
        return this.pending;
    }

    public String getPrefix(String str, boolean z) {
        throw new RuntimeException ("NYI");
    }

    public void endDocument() throws IOException {
        flush ();
    }

    public void flush() throws IOException {
        checkPending (false);
        if (!this.headerSent) {
            writeInt (this.out, this.stringTableBuf.size ());
            this.out.write (this.stringTableBuf.toByteArray ());
            this.headerSent = true;
        }
        this.out.write (this.buf.toByteArray ());
        this.buf.reset ();
    }

    public void checkPending(boolean z) throws IOException {
        if (null != pending) {
            int size = this.attributes.size ();
            int[] iArr = (int[]) this.tagTable.get (this.pending);
            if (null == iArr) {
                this.buf.write (0 == size ? z ? 4 : 68 : z ? Wbxml.LITERAL_A : Wbxml.LITERAL_AC);
                writeStrT (this.pending, false);
            } else {
                int i = iArr[0];
                if (i != this.tagPage) {
                    this.tagPage = i;
                    this.buf.write (0);
                    this.buf.write (this.tagPage);
                }
                this.buf.write (0 == size ? z ? iArr[1] : iArr[1] | 64 : z ? iArr[1] | 128 : iArr[1] | Wbxml.EXT_0);
            }
            for (int i2 = 0; i2 < size; i2 += 2) {
                int[] iArr2 = (int[]) this.attrStartTable.get (this.attributes.elementAt (i2));
                if (null == iArr2) {
                    this.buf.write (4);
                    writeStrT ((String) this.attributes.elementAt (i2), false);
                } else {
                    int i3 = iArr2[0];
                    if (i3 != this.attrPage) {
                        this.attrPage = i3;
                        this.buf.write (0);
                        this.buf.write (this.attrPage);
                    }
                    this.buf.write (iArr2[1]);
                }
                int i4 = i2 + 1;
                int[] iArr3 = (int[]) this.attrValueTable.get (this.attributes.elementAt (i4));
                if (null == iArr3) {
                    writeStr ((String) this.attributes.elementAt (i4));
                } else {
                    int i5 = iArr3[0];
                    if (i5 != this.attrPage) {
                        this.attrPage = i5;
                        this.buf.write (0);
                        this.buf.write (this.attrPage);
                    }
                    this.buf.write (iArr3[1]);
                }
            }
            if (0 < size) {
                this.buf.write (1);
            }
            this.pending = null;
            this.attributes.removeAllElements ();
        }
    }

    public void processingInstruction(String str) {
        throw new RuntimeException ("PI NYI");
    }

    public void setFeature(String str, boolean z) {
        throw new IllegalArgumentException ("unknown feature " + str);
    }

    public void setOutput(Writer writer) {
        throw new RuntimeException ("Wbxml requires an OutputStream!");
    }

    public void setOutput(OutputStream outputStream, String str) throws IOException {
        if (null == str) {
            str = "UTF-8";
        }
        this.encoding = str;
        this.out = outputStream;
        this.buf = new ByteArrayOutputStream ();
        this.stringTableBuf = new ByteArrayOutputStream ();
        this.headerSent = false;
    }

    public void setPrefix(String str, String str2) {
        throw new RuntimeException ("NYI");
    }

    public void setProperty(String str, Object obj) {
        throw new IllegalArgumentException ("unknown property " + str);
    }

    public void startDocument(String str, Boolean bool) throws IOException {
        this.out.write (3);
        this.out.write (1);
        if (null != str) {
            this.encoding = str;
        }
        if ("UTF-8".equalsIgnoreCase (encoding)) {
            this.out.write (106);
        } else if ("ISO-8859-1".equalsIgnoreCase (encoding)) {
            this.out.write (4);
        } else {
            throw new UnsupportedEncodingException (str);
        }
    }

    public XmlSerializer startTag(String str, String str2) throws IOException {
        if (null == str || "".equals (str)) {
            checkPending (false);
            this.pending = str2;
            this.depth++;
            return this;
        }
        throw new RuntimeException ("NSP NYI");
    }

    public XmlSerializer text(char[] cArr, int i, int i2) throws IOException {
        checkPending (false);
        writeStr (new String (cArr, i, i2));
        return this;
    }

    public XmlSerializer text(String str) throws IOException {
        checkPending (false);
        writeStr (str);
        return this;
    }

    private void writeStr(String str) throws IOException {
        int length = str.length ();
        if (this.headerSent) {
            writeStrI (this.buf, str);
            return;
        }
        int i = 0;
        int i2 = 0;
        while (i < length) {
            while (i < length && 'A' > str.charAt (i)) {
                i++;
            }
            int i3 = i;
            while (i3 < length && 'A' <= str.charAt (i3)) {
                i3++;
            }
            if (10 < i3 - i) {
                if (i > i2 && ' ' == str.charAt (i - 1) && null == stringTable.get (str.substring (i, i3))) {
                    this.buf.write (Wbxml.STR_T);
                    writeStrT (str.substring (i2, i3), false);
                } else {
                    if (i > i2 && ' ' == str.charAt (i - 1)) {
                        i--;
                    }
                    if (i > i2) {
                        this.buf.write (Wbxml.STR_T);
                        writeStrT (str.substring (i2, i), false);
                    }
                    this.buf.write (Wbxml.STR_T);
                    writeStrT (str.substring (i, i3), true);
                }
                i2 = i3;
            }
            i = i3;
        }
        if (i2 < length) {
            this.buf.write (Wbxml.STR_T);
            writeStrT (str.substring (i2, length), false);
        }
    }

    public XmlSerializer endTag(String str, String str2) throws IOException {
        if (null != pending) {
            checkPending (true);
        } else {
            this.buf.write (1);
        }
        this.depth--;
        return this;
    }

    public void writeWapExtension(int i, Object obj) throws IOException {
        checkPending (false);
        this.buf.write (i);
        switch (i) {
            case 64:
            case 65:
            case 66:
                writeStrI (this.buf, (String) obj);
                return;
            default:
                switch (i) {
                    case 128:
                    case Wbxml.EXT_T_1:
                    case Wbxml.EXT_T_2:
                        writeStrT ((String) obj, false);
                        return;
                    default:
                        switch (i) {
                            case Wbxml.EXT_0:
                            case Wbxml.EXT_1:
                            case Wbxml.EXT_2:
                                return;
                            case Wbxml.OPAQUE:
                                byte[] bArr = (byte[]) obj;
                                writeInt (this.buf, bArr.length);
                                this.buf.write (bArr);
                                return;
                            default:
                                throw new IllegalArgumentException ();
                        }
                }
        }
    }

    void writeStrI(OutputStream outputStream, String str) throws IOException {
        outputStream.write (str.getBytes (this.encoding));
        outputStream.write (0);
    }

    private final void writeStrT(String str, boolean z) throws IOException {
        Integer num = (Integer) this.stringTable.get (str);
        writeInt (this.buf, null == num ? addToStringTable (str, z) : num.intValue ());
    }

    public int addToStringTable(String str, boolean z) throws IOException {
        int i;
        if (!this.headerSent) {
            int size = this.stringTableBuf.size ();
            if ('0' > str.charAt (0) || !z) {
                i = size;
            } else {
                str = ' ' + str;
                i = size + 1;
            }
            this.stringTable.put (str, Integer.valueOf (size));
            if (' ' == str.charAt (0)) {
                this.stringTable.put (str.substring (1), Integer.valueOf (size + 1));
            }
            int lastIndexOf = str.lastIndexOf (32);
            if (1 < lastIndexOf) {
                int i2 = size + lastIndexOf;
                this.stringTable.put (str.substring (lastIndexOf), Integer.valueOf (i2));
                this.stringTable.put (str.substring (lastIndexOf + 1), Integer.valueOf (i2 + 1));
            }
            writeStrI (this.stringTableBuf, str);
            this.stringTableBuf.flush ();
            return i;
        }
        throw new IOException ("stringtable sent");
    }

    public void setTagTable(int i, String [] strArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            String str = strArr[i2];
            if (null != str) {
                this.tagTable.put (str, new int[]{i, i2 + 5});
            }
        }
    }

    public void setAttrStartTable(int i, String [] strArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            String str = strArr[i2];
            if (null != str) {
                this.attrStartTable.put (str, new int[]{i, i2 + 5});
            }
        }
    }

    public void setAttrValueTable(int i, String [] strArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            String str = strArr[i2];
            if (null != str) {
                this.attrValueTable.put (str, new int[]{i, i2 + 133});
            }
        }
    }
}
