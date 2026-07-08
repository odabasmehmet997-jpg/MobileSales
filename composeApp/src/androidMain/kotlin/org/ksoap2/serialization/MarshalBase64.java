package org.ksoap2.serialization;

import org.kobjects.base64.Base64;
import org.ksoap2.SoapEnvelope;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import java.io.IOException;

public class MarshalBase64 implements Marshal {
    public static final Class BYTE_ARRAY_CLASS = new byte[0].getClass();
    public Object readInstance(final XmlPullParser xmlPullParser, final String str, final String str2, final PropertyInfo propertyInfo) throws IOException, XmlPullParserException {
        return Base64.decode(xmlPullParser.nextText());
    }
    public void writeInstance(final XmlSerializer xmlSerializer, final Object obj) throws IOException {
        xmlSerializer.text(Base64.encode((byte[]) obj));
    }
    public void register(final SoapSerializationEnvelope soapSerializationEnvelope) {
        soapSerializationEnvelope.addMapping(soapSerializationEnvelope.xsd, "base64Binary", MarshalBase64.BYTE_ARRAY_CLASS, this);
        soapSerializationEnvelope.addMapping(SoapEnvelope.ENC, "base64", MarshalBase64.BYTE_ARRAY_CLASS, this);
    }
}
