package org.ksoap2.serialization;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import java.io.IOException;
import java.math.BigDecimal;

public class MarshalFloat implements Marshal {
    public Object readInstance(final XmlPullParser xmlPullParser, final String str, final String str2, final PropertyInfo propertyInfo) throws IOException, XmlPullParserException {
        final String nextText = xmlPullParser.nextText();
        if ("float".equals(str2)) {
            return new Float(nextText);
        }
        if ("double".equals(str2)) {
            return new Double(nextText);
        }
        if ("decimal".equals(str2)) {
            return new BigDecimal(nextText);
        }
        throw new RuntimeException("float, double, or decimal expected");
    }
    public void writeInstance(final XmlSerializer xmlSerializer, final Object obj) throws IOException {
        xmlSerializer.text(obj.toString());
    }
    public void register(final SoapSerializationEnvelope soapSerializationEnvelope) {
        soapSerializationEnvelope.addMapping(soapSerializationEnvelope.xsd, "float", Float.class, this);
        soapSerializationEnvelope.addMapping(soapSerializationEnvelope.xsd, "double", Double.class, this);
        soapSerializationEnvelope.addMapping(soapSerializationEnvelope.xsd, "decimal", BigDecimal.class, this);
    }
}
