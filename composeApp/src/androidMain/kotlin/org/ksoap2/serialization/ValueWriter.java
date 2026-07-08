package org.ksoap2.serialization;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public interface ValueWriter {
    void write(XmlSerializer xmlSerializer) throws IOException;
}
