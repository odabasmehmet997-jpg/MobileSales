package org.kobjects.mime;

import org.kobjects.base64.Base64;
import org.springframework.http.HttpHeaders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;

public class Decoder {
    String boundary;
    char[] buf;
    String characterEncoding;
    boolean consumed;
    boolean eof;
    Hashtable header;
    InputStream f2062is;
    private final String readLine() throws IOException {
        int i2 = 0;
        while (true) {
            final int read = f2062is.read();
            if (-1 == read && 0 == i2) {
                return null;
            }
            if (-1 == read || 10 == read) {
                break;
            }
            if (13 != read) {
                final char[] cArr = buf;
                if (i2 >= cArr.length) {
                    final char[] cArr2 = new char[(cArr.length * 3) / 2];
                    System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                    buf = cArr2;
                }
                buf[i2] = (char) read;
                i2++;
            }
        }
        return new String(buf, 0, i2);
    }
    public static Hashtable getHeaderElements(final String str) {
        int i2;
        final Hashtable hashtable = new Hashtable();
        final int length = str.length();
        String str2 = "";
        int i3 = 0;
        while (true) {
            if (i3 >= length || ' ' < str.charAt(i3)) {
                if (i3 >= length) {
                    break;
                }
                if ('\"' == str.charAt(i3)) {
                    final int i4 = i3 + 1;
                    final int indexOf = str.indexOf(34, i4);
                    if (-1 == indexOf) {
                        throw new RuntimeException("End quote expected in " + str);
                    }
                    hashtable.put(str2, str.substring(i4, indexOf));
                    i2 = indexOf + 2;
                    if (i2 >= length) {
                        break;
                    }
                    if (';' != str.charAt(indexOf + 1)) {
                        throw new RuntimeException("; expected in " + str);
                    }
                    i3 = str.indexOf(61, i2);
                    if (-1 != i3) {
                        break;
                    }
                    str2 = str.substring(i2, i3).toLowerCase().trim();
                } else {
                    final int indexOf2 = str.indexOf(59, i3);
                    if (-1 == indexOf2) {
                        hashtable.put(str2, str.substring(i3));
                        break;
                    }
                    hashtable.put(str2, str.substring(i3, indexOf2));
                    i2 = indexOf2 + 1;
                    i3 = str.indexOf(61, i2);
                    if (-1 != i3) {
                    }
                }
            }
            i3++;
        }
        return hashtable;
    }
    public Decoder(final InputStream inputStream, final String str) throws IOException {
        this(inputStream, str, null);
    }
    public Decoder(final InputStream inputStream, final String str, final String str2) throws IOException {
        String readLine;
        buf = new char[256];
        characterEncoding = str2;
        f2062is = inputStream;
        boundary = "--" + str;
        do {
            readLine = this.readLine();
            if (null == readLine) {
                throw new IOException("Unexpected EOF");
            }
        } while (!readLine.startsWith(boundary));
        if (readLine.endsWith("--")) {
            eof = true;
            inputStream.close();
        }
        consumed = true;
    }
    public Enumeration getHeaderNames() {
        return header.keys();
    }
    public String getHeader(final String str) {
        return (String) header.get(str.toLowerCase());
    }
    public String readContent() throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.readContent(byteArrayOutputStream);
        final String str = null == this.characterEncoding ? byteArrayOutputStream.toString() : byteArrayOutputStream.toString(characterEncoding);
        System.out.println("Field content: '" + str + "'");
        return str;
    }
    public void readContent(final OutputStream outputStream) throws IOException {
        String readLine;
        if (consumed) {
            throw new RuntimeException("Content already consumed!");
        }
        this.getHeader(HttpHeaders.CONTENT_TYPE);
        if ("base64".equals(this.getHeader("Content-Transfer-Encoding"))) {
            new ByteArrayOutputStream();
            while (true) {
                readLine = this.readLine();
                if (null == readLine) {
                    throw new IOException("Unexpected EOF");
                }
                if (readLine.startsWith(boundary)) {
                    break;
                } else {
                    Base64.decode(readLine, outputStream);
                }
            }
        } else {
            final String str = "\r\n" + boundary;
            int i2 = 0;
            while (true) {
                final int read = f2062is.read();
                if (-1 == read) {
                    throw new RuntimeException("Unexpected EOF");
                }
                final char c2 = (char) read;
                if (c2 == str.charAt(i2)) {
                    i2++;
                    if (i2 == str.length()) {
                        readLine = this.readLine();
                        break;
                    }
                } else {
                    if (0 < i2) {
                        for (int i3 = 0; i3 < i2; i3++) {
                            outputStream.write((byte) str.charAt(i3));
                        }
                        i2 = c2 == str.charAt(0) ? 1 : 0;
                    }
                    if (0 == i2) {
                        outputStream.write((byte) read);
                    }
                }
            }
        }
        if (readLine.endsWith("--")) {
            eof = true;
        }
        consumed = true;
    }
    public boolean next() throws IOException {
        if (!consumed) {
            this.readContent(null);
        }
        if (eof) {
            return false;
        }
        header = new Hashtable();
        while (true) {
            final String readLine = this.readLine();
            if (null == readLine || "".equals(readLine)) {
                break;
            }
            final int indexOf = readLine.indexOf(58);
            if (-1 == indexOf) {
                throw new IOException("colon missing in multipart header line: " + readLine);
            }
            header.put(readLine.substring(0, indexOf).trim().toLowerCase(), readLine.substring(indexOf + 1).trim());
        }
        return false;
    }
}
