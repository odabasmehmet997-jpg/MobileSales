package org.kobjects.pim;

import org.kobjects.io.LookAheadReader;

import java.io.IOException;
import java.io.Reader;
import java.util.Vector;

public class PimParser {
    LookAheadReader reader;
    Class type;
    public PimParser(final Reader reader, final Class cls) {
        this.reader = new LookAheadReader(reader);
        type = cls;
    }
    public PimItem readItem() throws IOException {
        Object readArrayValue;
        final String readName = this.readName();
        if (null == readName) {
            return null;
        }
        if (!"begin".equals(readName)) {
            throw new RuntimeException("'begin:' expected");
        }
        try {
            final PimItem pimItem = (PimItem) type.newInstance();
            reader.read();
            if (!pimItem.getType().equals(this.readStringValue().toLowerCase())) {
                throw new RuntimeException("item types do not match!");
            }
            while (true) {
                final String readName2 = this.readName();
                if (!"end".equals(readName2)) {
                    final PimField pimField = new PimField(readName2);
                    this.readProperties(pimField);
                    if (1 == pimItem.getType(readName2)) {
                        readArrayValue = this.readArrayValue(pimItem.getArraySize(readName2));
                    } else {
                        readArrayValue = this.readStringValue();
                    }
                    pimField.setValue(readArrayValue);
                    System.out.println("value:" + readArrayValue);
                    pimItem.addField(pimField);
                } else {
                    reader.read();
                    System.out.println("end:" + this.readStringValue());
                    return pimItem;
                }
            }
        } catch (final Exception e2) {
            throw new RuntimeException(e2.toString());
        }
    }
    String readName() throws IOException {
        final String lowerCase = reader.readTo(":;").trim().toLowerCase();
        System.out.println("name:" + lowerCase);
        if (-1 == this.reader.peek(0)) {
            return null;
        }
        return lowerCase;
    }
    String[] readArrayValue(final int i2) throws IOException {
        int i3;
        final Vector vector = new Vector();
        final StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        do {
            stringBuffer.append(reader.readTo(";\n\r"));
            final int read = reader.read();
            if (10 != read) {
                if (13 != read) {
                    if (59 == read) {
                        vector.addElement(stringBuffer.toString());
                        stringBuffer.setLength(0);
                    }
                } else if (10 == this.reader.peek(0)) {
                    reader.read();
                }
            }
            if (32 != this.reader.peek(0)) {
                z = false;
            } else {
                reader.read();
            }
        } while (z);
        if (0 != stringBuffer.length()) {
            vector.addElement(stringBuffer.toString());
        }
        final String[] strArr = new String[i2];
        for (i3 = 0; i3 < Math.min(i2, vector.size()); i3++) {
            strArr[i3] = (String) vector.elementAt(i3);
        }
        return strArr;
    }
    String readStringValue() throws IOException {
        String readLine = reader.readLine();
        while (32 == this.reader.peek(0)) {
            reader.read();
            readLine = readLine + reader.readLine();
        }
        return readLine;
    }
    void readProperties(final PimField pimField) throws IOException {
        int read = reader.read();
        while (32 == read) {
            read = reader.read();
        }
        while (58 != read) {
            final String lowerCase = reader.readTo(":;=").trim().toLowerCase();
            final int read2 = reader.read();
            if (61 == read2) {
                pimField.setProperty(lowerCase, reader.readTo(":;").trim().toLowerCase());
                read = reader.read();
            } else {
                pimField.setAttribute(lowerCase, true);
                read = read2;
            }
        }
    }
}
