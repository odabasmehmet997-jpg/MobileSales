package org.ksoap2.serialization;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import org.ksoap2.SoapEnvelope;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import java.io.IOException;


class C3431DM implements Marshal {
    public Object readInstance( XmlPullParser xmlPullParser, String str,  String str2, PropertyInfo propertyInfo) throws IOException, XmlPullParserException {
        String nextText = xmlPullParser.nextText ();
        char charAt = str2.charAt (0);
        if ('b' == charAt) {
            return Boolean.valueOf (SoapEnvelope.stringToBoolean (nextText));
        }
        if ('i' == charAt) {
            return Integer.valueOf (Integer.parseInt (nextText));
        }
        if ('l' == charAt) {
            return Long.valueOf (Long.parseLong (nextText));
        }
        if ('s' == charAt) {
            return nextText;
        }
        throw new RuntimeException ();
    }
    public void writeInstance( XmlSerializer xmlSerializer, Object obj) throws IOException {
        String str;
        String str2;
        int i = 0;
        if (obj instanceof  final AttributeContainer attributeContainer) {
            int attributeCount = attributeContainer.getAttributeCount ();
            while (i < attributeCount) {
                AttributeInfo attributeInfo = new AttributeInfo ();
                attributeContainer.getAttributeInfo (i, attributeInfo);
                try {
                    attributeContainer.getAttribute (i, attributeInfo);
                } catch (Exception e) {
                    e.printStackTrace ();
                }
                if (null != attributeInfo.getValue ()) {
                    String namespace = attributeInfo.getNamespace ();
                    String name = attributeInfo.getName ();
                    if (null != attributeInfo.getValue ()) {
                        str2 = attributeInfo.getValue ().toString ();
                    } else {
                        str2 = "";
                    }
                    xmlSerializer.attribute (namespace, name, str2);
                }
                i++;
            }
        } else if (obj instanceof  final HasAttributes hasAttributes) {
            int attributeCount2 = hasAttributes.getAttributeCount ();
            while (i < attributeCount2) {
                AttributeInfo attributeInfo2 = new AttributeInfo ();
                hasAttributes.getAttributeInfo (i, attributeInfo2);
                try {
                    hasAttributes.getAttribute (i, attributeInfo2);
                } catch (Exception e2) {
                    e2.printStackTrace ();
                }
                if (null != attributeInfo2.getValue ()) {
                    String namespace2 = attributeInfo2.getNamespace ();
                    String name2 = attributeInfo2.getName ();
                    if (null != attributeInfo2.getValue ()) {
                        str = attributeInfo2.getValue ().toString ();
                    } else {
                        str = "";
                    }
                    xmlSerializer.attribute (namespace2, name2, str);
                }
                i++;
            }
        }
        if (obj instanceof ValueWriter) {
            ((ValueWriter) obj).write (xmlSerializer);
        } else {
            xmlSerializer.text (obj.toString ());
        }
    }
    public void register( SoapSerializationEnvelope soapSerializationEnvelope) {
        soapSerializationEnvelope.addMapping (soapSerializationEnvelope.xsd, "int", PropertyInfo.INTEGER_CLASS, this);
        soapSerializationEnvelope.addMapping (soapSerializationEnvelope.xsd, "long", PropertyInfo.LONG_CLASS, this);
        soapSerializationEnvelope.addMapping (soapSerializationEnvelope.xsd, TypedValues.Custom.S_STRING, PropertyInfo.STRING_CLASS, this);
        soapSerializationEnvelope.addMapping (soapSerializationEnvelope.xsd, TypedValues.Custom.S_BOOLEAN, PropertyInfo.BOOLEAN_CLASS, this);
    }
}
