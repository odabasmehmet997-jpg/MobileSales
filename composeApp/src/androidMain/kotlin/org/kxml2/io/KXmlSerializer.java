package org.kxml2.io;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
public class KXmlSerializer implements XmlSerializer {
    private int auto;
    private int depth;
    private String encoding;
    private boolean pending;
    private boolean unicode;
    private Writer writer;
    private String [] elementStack = new String[12];
    private int [] nspCounts = new int[4];
    private String [] nspStack = new String[8];
    private boolean [] indent = new boolean[4];
    private final void check(final boolean z) throws IOException {
        if (pending) {
            final int i = depth;
            final int i2 = i + 1;
            depth = i2;
            pending = false;
            final boolean[] zArr = indent;
            if (zArr.length <= i2) {
                final boolean[] zArr2 = new boolean[i + 5];
                System.arraycopy (zArr, 0, zArr2, 0, i2);
                indent = zArr2;
            }
            final boolean[] zArr3 = indent;
            final int i3 = depth;
            zArr3[i3] = zArr3[i3 - 1];
            int i4 = nspCounts[i3 - 1];
            while (true) {
                final int[] iArr = nspCounts;
                final int i5 = depth;
                if (i4 < iArr[i5]) {
                    writer.write (32);
                    writer.write ("xmlns");
                    final int i6 = i4 * 2;
                    if (!"".equals (nspStack[i6])) {
                        writer.write (58);
                        writer.write (nspStack[i6]);
                    } else if ("".equals (this.getNamespace()) && !"".equals (nspStack[i6 + 1])) {
                        throw new IllegalStateException ("Cannot set default namespace for elements in no namespace");
                    }
                    writer.write ("=\"");
                    this.writeEscaped(nspStack[i6 + 1], 34);
                    writer.write (34);
                    i4++;
                } else {
                    if (iArr.length <= i5 + 1) {
                        final int[] iArr2 = new int[i5 + 8];
                        System.arraycopy (iArr, 0, iArr2, 0, i5 + 1);
                        nspCounts = iArr2;
                    }
                    final int[] iArr3 = nspCounts;
                    final int i7 = depth;
                    iArr3[i7 + 1] = iArr3[i7];
                    writer.write (z ? " />" : ">");
                    return;
                }
            }
        }
    }
    private final void writeEscaped(final String r6, final int r7) throws IOException {
        throw new UnsupportedOperationException (" org.kxml2.p028io.KXmlSerializer.writeEscaped(java.lang.String, int):void");
    }
    public void docdecl(final String str) throws IOException {
        writer.write ("<!DOCTYPE");
        writer.write (str);
        writer.write (">");
    }
    public void endDocument() throws IOException {
        while (true) {
            final int i = depth;
            if (0 < i) {
                final String[] strArr = elementStack;
                this.endTag(strArr[(i * 3) - 3], strArr[(i * 3) - 1]);
            } else {
                this.flush();
                return;
            }
        }
    }
    public void entityRef(final String str) throws IOException {
        this.check(false);
        writer.write (38);
        writer.write (str);
        writer.write (59);
    }
    public boolean getFeature(final String str) {
        if ("http://xmlpull.org/v1/doc/features.html#indent-output".equals (str)) {
            return indent[depth];
        }
        return false;
    }
    public String getPrefix(final String str, final boolean z) {
        try {
            return this.getPrefix(str, false, z);
        } catch (final IOException e) {
            throw new RuntimeException (e.toString ());
        }
    }
    private final String getPrefix(final String str, final boolean z, final boolean z2) throws IOException {
        int i = nspCounts[depth + 1] * 2;
        while (true) {
            i -= 2;
            String str2 = null;
            String str3 = "";
            if (0 <= i) {
                if (nspStack[i + 1].equals (str) && (z || !nspStack[i].equals (str3))) {
                    final String str4 = nspStack[i];
                    int i2 = i + 2;
                    while (true) {
                        if (i2 >= nspCounts[depth + 1] * 2) {
                            str2 = str4;
                            break;
                        } else if (nspStack[i2].equals (str4)) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (null != str2) {
                        return str2;
                    }
                }
            } else if (!z2) {
                return null;
            } else {
                final boolean z3;
                if (str3.equals (str)) {
                    z3 = pending;
                    pending = false;
                    this.setPrefix(str3, str);
                    pending = z3;
                    return str3;
                }
                do {
                    final StringBuilder sb = new StringBuilder ();
                    sb.append ("n");
                    final int i3 = auto;
                    auto = i3 + 1;
                    sb.append (i3);
                    final String sb2 = sb.toString ();
                    int i4 = (nspCounts[depth + 1] * 2) - 2;
                    while (true) {
                        if (0 > i4) {
                            str3 = sb2;
                            continue;
                        } else if (sb2.equals (nspStack[i4])) {
                            str3 = null;
                            continue;
                        } else {
                            i4 -= 2;
                        }
                    }
                } while (null == str3);
            }
        }
    }
    public Object getProperty(final String str) {
        throw new RuntimeException ("Unsupported property");
    }
    public void ignorableWhitespace(final String str) throws IOException {
        this.text(str);
    }
    public void setFeature(final String str, final boolean z) {
        if ("http://xmlpull.org/v1/doc/features.html#indent-output".equals (str)) {
            indent[depth] = z;
            return;
        }
        throw new RuntimeException ("Unsupported Feature");
    }
    public void setProperty(final String str, final Object obj) {
        throw new RuntimeException ("Unsupported Property:" + obj);
    }
    public void setPrefix(String str, String str2) throws IOException {
        this.check(false);
        if (null == str) {
            str = "";
        }
        if (null == str2) {
            str2 = "";
        }
        if (!str.equals (this.getPrefix(str2, true, false))) {
            final int[] iArr = nspCounts;
            final int i = depth + 1;
            final int i2 = iArr[i];
            iArr[i] = i2 + 1;
            final int i3 = i2 << 1;
            final String[] strArr = nspStack;
            final int i4 = i3 + 1;
            if (strArr.length < i4) {
                final String[] strArr2 = new String[strArr.length + 16];
                System.arraycopy (strArr, 0, strArr2, 0, i3);
                nspStack = strArr2;
            }
            final String[] strArr3 = nspStack;
            strArr3[i3] = str;
            strArr3[i4] = str2;
        }
    }
    public void setOutput(final Writer writer) {
        this.writer = writer;
        final int[] iArr = nspCounts;
        iArr[0] = 2;
        iArr[1] = 2;
        final String[] strArr = nspStack;
        strArr[0] = "";
        strArr[1] = "";
        strArr[2] = "xml";
        strArr[3] = "http://www.w3.org/XML/1998/namespace";
        pending = false;
        auto = 0;
        depth = 0;
        unicode = false;
    }
    public void setOutput(final OutputStream outputStream, final String str) throws IOException {
        if (null != outputStream) {
            this.setOutput(null == str ? new OutputStreamWriter (outputStream, StandardCharsets.UTF_8) : new OutputStreamWriter (outputStream, str));
            encoding = str;
            if (null != str && str.toLowerCase ().startsWith ("utf")) {
                unicode = true;
                return;
            }
            return;
        }
        throw new IllegalArgumentException ();
    }
    public void startDocument(final String str, final Boolean bool) throws IOException {
        writer.write ("<?xml version='1.0' ");
        if (null != str) {
            encoding = str;
            if (str.toLowerCase ().startsWith ("utf")) {
                unicode = true;
            }
        }
        if (null != this.encoding) {
            writer.write ("encoding='");
            writer.write (encoding);
            writer.write ("' ");
        }
        if (null != bool) {
            writer.write ("standalone='");
            writer.write (bool.booleanValue () ? "yes" : "no");
            writer.write ("' ");
        }
        writer.write ("?>");
    }
    public XmlSerializer startTag(final String str, final String str2) throws IOException {
        this.check(false);
        if (indent[depth]) {
            writer.write ("\r\n");
            for (int i = 0; i < depth; i++) {
                writer.write ("  ");
            }
        }
        final int i2 = depth * 3;
        final String[] strArr = elementStack;
        if (strArr.length < i2 + 3) {
            final String[] strArr2 = new String[strArr.length + 12];
            System.arraycopy (strArr, 0, strArr2, 0, i2);
            elementStack = strArr2;
        }
        final String prefix = null == str ? "" : this.getPrefix(str, true, true);
        if ("".equals (str)) {
            for (int i3 = nspCounts[depth]; i3 < nspCounts[depth + 1]; i3++) {
                final int i4 = i3 * 2;
                if ("".equals (nspStack[i4]) && !"".equals (nspStack[i4 + 1])) {
                    throw new IllegalStateException ("Cannot set default namespace for elements in no namespace");
                }
            }
        }
        final String[] strArr3 = elementStack;
        strArr3[i2] = str;
        strArr3[i2 + 1] = prefix;
        strArr3[i2 + 2] = str2;
        writer.write (60);
        if (!"".equals (prefix)) {
            writer.write (prefix);
            writer.write (58);
        }
        writer.write (str2);
        pending = true;
        return this;
    }
    public XmlSerializer attribute(String str, final String str2, final String str3) throws IOException {
        final String str4;
        if (pending) {
            if (null == str) {
                str = "";
            }
            if ("".equals (str)) {
                str4 = "";
            } else {
                str4 = this.getPrefix(str, false, true);
            }
            writer.write (32);
            if (!"".equals (str4)) {
                writer.write (str4);
                writer.write (58);
            }
            writer.write (str2);
            writer.write (61);
            int i = 34;
            if (-1 != str3.indexOf (34)) {
                i = 39;
            }
            writer.write (i);
            this.writeEscaped(str3, i);
            writer.write (i);
            return this;
        }
        throw new IllegalStateException ("illegal position for attribute");
    }
    public void flush() throws IOException {
        this.check(false);
        writer.flush ();
    }
    public XmlSerializer endTag(final String str, final String str2) throws IOException {
        if (!pending) {
            depth--;
        }
        if ((null != str || null == this.elementStack[this.depth * 3]) && ((null == str || str.equals (elementStack[depth * 3])) && elementStack[(depth * 3) + 2].equals (str2))) {
            if (pending) {
                this.check(true);
                depth--;
            } else {
                if (indent[depth + 1]) {
                    writer.write ("\r\n");
                    for (int i = 0; i < depth; i++) {
                        writer.write ("  ");
                    }
                }
                writer.write ("</");
                final String str3 = elementStack[(depth * 3) + 1];
                if (!"".equals (str3)) {
                    writer.write (str3);
                    writer.write (58);
                }
                writer.write (str2);
                writer.write (62);
            }
            final int[] iArr = nspCounts;
            final int i2 = depth;
            iArr[i2 + 1] = iArr[i2];
            return this;
        }
        throw new IllegalArgumentException ("</{" + str + "}" + str2 + "> does not match start");
    }
    public String getNamespace() {
        if (0 == getDepth ()) {
            return null;
        }
        return elementStack[(this.getDepth() * 3) - 3];
    }
    public String getName() {
        if (0 == getDepth ()) {
            return null;
        }
        return elementStack[(this.getDepth() * 3) - 1];
    }
    public int getDepth() {
        return pending ? depth + 1 : depth;
    }
    public XmlSerializer text(final String str) throws IOException {
        this.check(false);
        indent[depth] = false;
        this.writeEscaped(str, -1);
        return this;
    }
    public XmlSerializer text(final char[] cArr, final int i, final int i2) throws IOException {
        this.text(new String (cArr, i, i2));
        return this;
    }
    public void cdsect(final String str) throws IOException {
        this.check(false);
        writer.write ("<![CDATA[");
        writer.write (str);
        writer.write ("]]>");
    }
    public void comment(final String str) throws IOException {
        this.check(false);
        writer.write ("<!--");
        writer.write (str);
        writer.write ("-->");
    }
    public void processingInstruction(final String str) throws IOException {
        this.check(false);
        writer.write ("<?");
        writer.write (str);
        writer.write ("?>");
    }
}
