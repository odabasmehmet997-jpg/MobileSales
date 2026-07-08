package org.kobjects.pim;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

public class PimWriter {
    Writer writer;
    public PimWriter(final Writer writer) {
        this.writer = writer;
    }
    public void writeEntry(final PimItem pimItem) throws IOException {
        writer.write("begin:");
        writer.write(pimItem.getType());
        writer.write("\r\n");
        final Enumeration fieldNames = pimItem.fieldNames();
        while (fieldNames.hasMoreElements()) {
            final String str = (String) fieldNames.nextElement();
            for (int i2 = 0; i2 < pimItem.getFieldCount(str); i2++) {
                final PimField field = pimItem.getField(str, i2);
                writer.write(str);
                writer.write(58);
                writer.write(field.getValue().toString());
                writer.write("\r\n");
            }
        }
        writer.write("end:");
        writer.write(pimItem.getType());
        writer.write("\r\n\r\n");
    }
}
