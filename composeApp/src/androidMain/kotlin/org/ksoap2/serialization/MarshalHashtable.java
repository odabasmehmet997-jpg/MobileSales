package org.ksoap2.serialization;

import com.proje.mobilesales.core.preferences.EditTextDialog;
import okhttp3.HttpUrl;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;

public class MarshalHashtable implements Marshal {
    public static final Class HASHTABLE_CLASS = new Hashtable().getClass();
    public static final String NAME = "Map";
    public static final String NAMESPACE = "http://xml.apache.org/xml-soap";
    SoapSerializationEnvelope envelope;
    public Object readInstance(final XmlPullParser xmlPullParser, final String str, final String str2, final PropertyInfo propertyInfo) throws IOException, XmlPullParserException {
        final Hashtable hashtable = new Hashtable();
        final String name = xmlPullParser.getName();
        while (3 != xmlPullParser.nextTag()) {
            final ItemSoapObject itemSoapObject = new ItemSoapObject(hashtable);
            xmlPullParser.require(2, null, "item");
            xmlPullParser.nextTag();
            final SoapSerializationEnvelope soapSerializationEnvelope = envelope;
            final PropertyInfo propertyInfo2 = PropertyInfo.OBJECT_TYPE;
            final Object read = soapSerializationEnvelope.read(xmlPullParser, itemSoapObject, 0, null, null, propertyInfo2);
            xmlPullParser.nextTag();
            if (null != read) {
                itemSoapObject.setProperty(0, read);
            }
            final Object read2 = envelope.read(xmlPullParser, itemSoapObject, 1, null, null, propertyInfo2);
            xmlPullParser.nextTag();
            if (null != read2) {
                itemSoapObject.setProperty(1, read2);
            }
            xmlPullParser.require(3, null, "item");
        }
        xmlPullParser.require(3, null, name);
        return hashtable;
    }
    public void writeInstance(final XmlSerializer xmlSerializer, final Object obj) throws IOException {
        final Hashtable hashtable = (Hashtable) obj;
        final SoapObject soapObject = new SoapObject(null, null);
        soapObject.addProperty("key", null);
        soapObject.addProperty(EditTextDialog.EDIT_VALUE_KEY, null);
        Iterator iterator = hashtable.keySet().iterator();
        while (iterator.hasNext()) {
            xmlSerializer.startTag(HttpUrl.FRAGMENT_ENCODE_SET, "item");
            final Object nextElement = iterator.next();
            soapObject.setProperty(0, nextElement);
            soapObject.setProperty(1, hashtable.get(nextElement));
            envelope.writeObjectBodyWithAttributes(xmlSerializer, soapObject);
            xmlSerializer.endTag(HttpUrl.FRAGMENT_ENCODE_SET, "item");
        }
    }
    class ItemSoapObject extends SoapObject {
        Hashtable f897h;
        int resolvedIndex;

        ItemSoapObject(final Hashtable hashtable) {
            super(null, null);
            resolvedIndex = -1;
            f897h = hashtable;
            this.addProperty("key", null);
            this.addProperty(EditTextDialog.EDIT_VALUE_KEY, null);
        }
        public void setProperty(final int i2, final Object obj) {
            final int i3 = resolvedIndex;
            if (-1 == i3) {
                super.setProperty(i2, obj);
                resolvedIndex = i2;
                return;
            }
            final Object property = this.getProperty(0 == i3 ? 0 : 1);
            if (0 == i2) {
                f897h.put(obj, property);
            } else {
                f897h.put(property, obj);
            }
        }
    }
    public void register(final SoapSerializationEnvelope soapSerializationEnvelope) {
        envelope = soapSerializationEnvelope;
        soapSerializationEnvelope.addMapping(MarshalHashtable.NAMESPACE, MarshalHashtable.NAME, MarshalHashtable.HASHTABLE_CLASS, this);
    }
}
