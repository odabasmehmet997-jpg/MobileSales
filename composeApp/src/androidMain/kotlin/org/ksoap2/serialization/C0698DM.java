package org.ksoap2.serialization;

import okhttp3.HttpUrl;
import org.ksoap2.SoapEnvelope;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import java.io.IOException;

class C0698DM implements Marshal {
    C0698DM() {
    }
    public Object readInstance(final XmlPullParser xmlPullParser, final String str, final String str2, final PropertyInfo propertyInfo) throws IOException, XmlPullParserException {
        final String nextText = xmlPullParser.nextText();
        final char charAt = str2.charAt(0);
        if ('b' == charAt) {
            return Boolean.valueOf(SoapEnvelope.stringToBoolean(nextText));
        }
        if ('i' == charAt) {
            return Integer.valueOf(Integer.parseInt(nextText));
        }
        if ('l' == charAt) {
            return Long.valueOf(Long.parseLong(nextText));
        }
        if ('s' == charAt) {
            return nextText;
        }
        throw new RuntimeException();
    }
    public void writeInstance(final XmlSerializer xmlSerializer, final Object obj) throws IOException {
        int i2 = 0;
        if (obj instanceof AttributeContainer attributeContainer) {
            final int attributeCount = attributeContainer.getAttributeCount();
            while (i2 < attributeCount) {
                final AttributeInfo attributeInfo = new AttributeInfo();
                attributeContainer.getAttributeInfo(i2, attributeInfo);
                try {
                    attributeContainer.getAttribute(i2, attributeInfo);
                } catch (final Exception e2) {
                    e2.printStackTrace();
                }
                if (null != attributeInfo.getValue()) {
                    xmlSerializer.attribute(attributeInfo.getNamespace(), attributeInfo.getName(), null != attributeInfo.getValue() ? attributeInfo.getValue().toString() : HttpUrl.FRAGMENT_ENCODE_SET);
                }
                i2++;
            }
        } else if (obj instanceof HasAttributes hasAttributes) {
            final int attributeCount2 = hasAttributes.getAttributeCount();
            while (i2 < attributeCount2) {
                final AttributeInfo attributeInfo2 = new AttributeInfo();
                hasAttributes.getAttributeInfo(i2, attributeInfo2);
                try {
                    hasAttributes.getAttribute(i2, attributeInfo2);
                } catch (final Exception e3) {
                    e3.printStackTrace();
                }
                if (null != attributeInfo2.getValue()) {
                    xmlSerializer.attribute(attributeInfo2.getNamespace(), attributeInfo2.getName(), null != attributeInfo2.getValue() ? attributeInfo2.getValue().toString() : HttpUrl.FRAGMENT_ENCODE_SET);
                }
                i2++;
            }
        }
        if (obj instanceof ValueWriter) {
            ((ValueWriter) obj).write(xmlSerializer);
        } else {
            xmlSerializer.text(obj.toString());
        }
    }
    public void register(final SoapSerializationEnvelope soapSerializationEnvelope) {
        soapSerializationEnvelope.addMapping(soapSerializationEnvelope.xsd, "int", PropertyInfo.INTEGER_CLASS, this);
        soapSerializationEnvelope.addMapping(soapSerializationEnvelope.xsd, "long", PropertyInfo.LONG_CLASS, this);
        soapSerializationEnvelope.addMapping(soapSerializationEnvelope.xsd, "string", PropertyInfo.STRING_CLASS, this);
        soapSerializationEnvelope.addMapping(soapSerializationEnvelope.xsd, "boolean", PropertyInfo.BOOLEAN_CLASS, this);
    }
}
