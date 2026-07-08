package org.ksoap2;

import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;


public class SoapEnvelope {
    public static final String ENC = "http://schemas.xmlsoap.org/soap/encoding/";
    public static final String ENC2003 = "http://www.w3.org/2003/05/soap-encoding";
    public static final String ENV = "http://schemas.xmlsoap.org/soap/envelope/";
    public static final String ENV2003 = "http://www.w3.org/2003/05/soap-envelope";
    public static final int VER10 = 100;
    public static final int VER11 = 110;
    public static final int VER12 = 120;
    public static final String XSD = "http://www.w3.org/2001/XMLSchema";
    public static final String XSD1999 = "http://www.w3.org/1999/XMLSchema";
    public static final String XSI = "http://www.w3.org/2001/XMLSchema-instance";
    public static final String XSI1999 = "http://www.w3.org/1999/XMLSchema-instance";
    public Object bodyIn;
    public Object bodyOut;
    public String enc;
    public String encodingStyle;
    public String env;
    public Element[] headerIn;
    public Element[] headerOut;
    public int version;
    public String xsd;
    public String xsi;
    public static boolean stringToBoolean(final String str) {
        if (null == str) {
            return false;
        }
        final String lowerCase = str.trim().toLowerCase();
        return "1".equals(lowerCase) || "true".equals(lowerCase);
    }
    public SoapEnvelope(final int i2) {
        version = i2;
        if (100 == i2) {
            xsi = SoapEnvelope.XSI1999;
            xsd = SoapEnvelope.XSD1999;
        } else {
            xsi = SoapEnvelope.XSI;
            xsd = SoapEnvelope.XSD;
        }
        if (120 > i2) {
            enc = SoapEnvelope.ENC;
            env = SoapEnvelope.ENV;
        } else {
            enc = SoapEnvelope.ENC2003;
            env = SoapEnvelope.ENV2003;
        }
    }
    public void parse(final XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.nextTag();
        xmlPullParser.require(2, env, "Envelope");
        encodingStyle = xmlPullParser.getAttributeValue(env, "encodingStyle");
        xmlPullParser.nextTag();
        if (2 == xmlPullParser.getEventType() && xmlPullParser.getNamespace().equals(env) && "Header".equals(xmlPullParser.getName())) {
            this.parseHeader(xmlPullParser);
            xmlPullParser.require(3, env, "Header");
            xmlPullParser.nextTag();
        }
        xmlPullParser.require(2, env, "Body");
        encodingStyle = xmlPullParser.getAttributeValue(env, "encodingStyle");
        this.parseBody(xmlPullParser);
        xmlPullParser.require(3, env, "Body");
        xmlPullParser.nextTag();
        xmlPullParser.require(3, env, "Envelope");
    }
    public void parseHeader(final XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.nextTag();
        final Node node = new Node();
        node.parse(xmlPullParser);
        int i2 = 0;
        for (int i3 = 0; i3 < node.getChildCount(); i3++) {
            if (null != node.getElement(i3)) {
                i2++;
            }
        }
        headerIn = new Element[i2];
        int i4 = 0;
        for (int i5 = 0; i5 < node.getChildCount(); i5++) {
            final Element element = node.getElement(i5);
            if (null != element) {
                headerIn[i4] = element;
                i4++;
            }
        }
    }
    public void parseBody(final XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        final SoapFault soapFault12;
        xmlPullParser.nextTag();
        if (2 == xmlPullParser.getEventType() && xmlPullParser.getNamespace().equals(env) && "Fault".equals(xmlPullParser.getName())) {
            if (120 > this.version) {
                soapFault12 = new SoapFault(version);
            } else {
                soapFault12 = new SoapFault12(version);
            }
            soapFault12.parse(xmlPullParser);
            bodyIn = soapFault12;
            return;
        }
        final Object obj = bodyIn;
        final Node node = obj instanceof Node ? (Node) obj : new Node();
        node.parse(xmlPullParser);
        bodyIn = node;
    }
    public void write(final XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.setPrefix("i", xsi);
        xmlSerializer.setPrefix("d", xsd);
        xmlSerializer.setPrefix("c", enc);
        xmlSerializer.setPrefix("v", env);
        xmlSerializer.startTag(env, "Envelope");
        xmlSerializer.startTag(env, "Header");
        this.writeHeader(xmlSerializer);
        xmlSerializer.endTag(env, "Header");
        xmlSerializer.startTag(env, "Body");
        this.writeBody(xmlSerializer);
        xmlSerializer.endTag(env, "Body");
        xmlSerializer.endTag(env, "Envelope");
    }
    public void writeHeader(final XmlSerializer xmlSerializer) throws IOException {
        if (null == this.headerOut) {
            return;
        }
        int i2 = 0;
        while (true) {
            final Element[] elementArr = headerOut;
            if (i2 >= elementArr.length) {
                return;
            }
            elementArr[i2].write(xmlSerializer);
            i2++;
        }
    }
    public void writeBody(final XmlSerializer xmlSerializer) throws IOException {
        final String str = encodingStyle;
        if (null != str) {
            xmlSerializer.attribute(env, "encodingStyle", str);
        }
        ((Node) bodyOut).write(xmlSerializer);
    }
    public void setOutputSoapObject(final Object obj) {
        bodyOut = obj;
    }
}
