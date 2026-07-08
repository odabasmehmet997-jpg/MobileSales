package com.sun.activation.registries;

import android.os.Build;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class MimeTypeFile {
    private String fname;
    private final Hashtable type_hash = new Hashtable();
    public MimeTypeFile(final String str) throws IOException {
        fname = str;
        FileReader fileReader = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            fileReader = new FileReader(new File(fname), StandardCharsets.UTF_8);
        }
        try {
            this.parse(new BufferedReader(fileReader));
        } finally {
            try {
                fileReader.close();
            } catch (final IOException unused) {
            }
        }
    }
    public MimeTypeFile(final InputStream inputStream) throws IOException {
        this.parse(new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1)));
    }
    public MimeTypeFile() {
    }
    public MimeTypeEntry getMimeTypeEntry(final String str) {
        return (MimeTypeEntry) type_hash.get(str);
    }
    public String getMIMETypeString(final String str) {
        final MimeTypeEntry mimeTypeEntry = this.getMimeTypeEntry(str);
        if (null != mimeTypeEntry) {
            return mimeTypeEntry.getMIMEType();
        }
        return null;
    }
    public void appendToRegistry(final String str) {
        try {
            this.parse(new BufferedReader(new StringReader(str)));
        } catch (final IOException unused) {
        }
    }
    private void parse(final BufferedReader bufferedReader) throws IOException {
        String str;
        String readLine;
        loop0:
        while (true) {
            str = null;
            while (true) {
                readLine = bufferedReader.readLine();
                if (null != readLine) {
                    if (null != str) {
                        readLine = str + readLine;
                    }
                    final int length = readLine.length();
                    if (0 >= readLine.length()) {
                        break;
                    }
                    final int i2 = length - 1;
                    if ('\\' != readLine.charAt(i2)) {
                        break;
                    }
                    str = readLine.substring(0, i2);
                } else {
                    break loop0;
                }
            }
            this.parseEntry(readLine);
        }
        if (null != str) {
            this.parseEntry(str);
        }
    }
    private void parseEntry(final String str) {
        final String trim = str.trim();
        if (0 != trim.length() && '#' != trim.charAt(0)) {
            if (0 < trim.indexOf(61)) {
                final LineTokenizer lineTokenizer = new LineTokenizer(trim);
                String str2 = null;
                while (lineTokenizer.hasMoreTokens()) {
                    final String nextToken = lineTokenizer.nextToken();
                    final String nextToken2 = (!lineTokenizer.hasMoreTokens() || !"=".equals(lineTokenizer.nextToken()) || !lineTokenizer.hasMoreTokens()) ? null : lineTokenizer.nextToken();
                    if (null == nextToken2) {
                        if (LogSupport.isLoggable()) {
                            LogSupport.log("Bad .mime.types entry: " + trim);
                            return;
                        }
                        return;
                    } else if ("type".equals(nextToken)) {
                        str2 = nextToken2;
                    } else if ("exts".equals(nextToken)) {
                        final StringTokenizer stringTokenizer = new StringTokenizer(nextToken2, ",");
                        while (stringTokenizer.hasMoreTokens()) {
                            final String nextToken3 = stringTokenizer.nextToken();
                            final MimeTypeEntry mimeTypeEntry = new MimeTypeEntry(str2, nextToken3);
                            type_hash.put(nextToken3, mimeTypeEntry);
                            if (LogSupport.isLoggable()) {
                                LogSupport.log("Added: " + mimeTypeEntry);
                            }
                        }
                    }
                }
                return;
            }
            final StringTokenizer stringTokenizer2 = new StringTokenizer(trim);
            if (0 != stringTokenizer2.countTokens()) {
                final String nextToken4 = stringTokenizer2.nextToken();
                while (stringTokenizer2.hasMoreTokens()) {
                    final String nextToken5 = stringTokenizer2.nextToken();
                    final MimeTypeEntry mimeTypeEntry2 = new MimeTypeEntry(nextToken4, nextToken5);
                    type_hash.put(nextToken5, mimeTypeEntry2);
                    if (LogSupport.isLoggable()) {
                        LogSupport.log("Added: " + mimeTypeEntry2);
                    }
                }
            }
        }
    }
}
