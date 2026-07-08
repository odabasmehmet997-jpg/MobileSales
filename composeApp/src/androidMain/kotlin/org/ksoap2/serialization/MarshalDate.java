package org.ksoap2.serialization;

import org.kobjects.isodate.IsoDate;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.util.Date;

public class MarshalDate implements Marshal {
    public static Class DATE_CLASS = new Date().getClass();
    public Object readInstance(final XmlPullParser xmlPullParser, final String str, final String str2, final PropertyInfo propertyInfo) throws IOException, XmlPullParserException {
        return IsoDate.stringToDate(xmlPullParser.nextText(), 3);
    }
    public void writeInstance(final XmlSerializer xmlSerializer, final Object obj) throws IOException {
        xmlSerializer.text(IsoDate.dateToString((Date) obj, 3));
    }
    public void register(final SoapSerializationEnvelope soapSerializationEnvelope) {
        soapSerializationEnvelope.addMapping(soapSerializationEnvelope.xsd, "dateTime", MarshalDate.DATE_CLASS, this);
    }
}
