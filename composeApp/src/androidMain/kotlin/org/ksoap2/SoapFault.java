package org.ksoap2;
import okhttp3.HttpUrl;
import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
public class SoapFault extends IOException {
    private static final long serialVersionUID = 1011001;
    public Node detail;
    public String faultactor;
    public String faultcode;
    public String faultstring;
    public int version;
    public SoapFault() {
        version = 110;
    }
    public SoapFault(final int i2) {
        version = i2;
    }
    public void parse(final XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, SoapEnvelope.ENV, "Fault");
        while (2 == xmlPullParser.nextTag()) {
            final String name = xmlPullParser.getName();
            if ("detail".equals(name)) {
                final Node node = new Node();
                detail = node;
                node.parse(xmlPullParser);
                if (xmlPullParser.getNamespace().equals(SoapEnvelope.ENV) && "Fault".equals(xmlPullParser.getName())) {
                    break;
                }
            } else {
                if ("faultcode".equals(name)) {
                    faultcode = xmlPullParser.nextText();
                } else if ("faultstring".equals(name)) {
                    faultstring = xmlPullParser.nextText();
                } else if ("faultactor".equals(name)) {
                    faultactor = xmlPullParser.nextText();
                } else {
                    throw new RuntimeException("unexpected tag:" + name);
                }
                xmlPullParser.require(3, null, name);
            }
        }
        xmlPullParser.require(3, SoapEnvelope.ENV, "Fault");
        xmlPullParser.nextTag();
    }
    public void write(final XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startTag(SoapEnvelope.ENV, "Fault");
        xmlSerializer.startTag(null, "faultcode");
        xmlSerializer.text(HttpUrl.FRAGMENT_ENCODE_SET + faultcode);
        xmlSerializer.endTag(null, "faultcode");
        xmlSerializer.startTag(null, "faultstring");
        xmlSerializer.text(HttpUrl.FRAGMENT_ENCODE_SET + faultstring);
        xmlSerializer.endTag(null, "faultstring");
        xmlSerializer.startTag(null, "detail");
        final Node node = detail;
        if (null != node) {
            node.write(xmlSerializer);
        }
        xmlSerializer.endTag(null, "detail");
        xmlSerializer.endTag(SoapEnvelope.ENV, "Fault");
    }
    public String getMessage() {
        return faultstring;
    }
    public String toString() {
        return "SoapFault - faultcode: '" + faultcode + "' faultstring: '" + faultstring + "' faultactor: '" + faultactor + "' detail: " + detail;
    }
}
