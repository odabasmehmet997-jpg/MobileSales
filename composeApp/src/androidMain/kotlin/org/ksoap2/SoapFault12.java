package org.ksoap2;
import org.kxml2.kdom.Node;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
public class SoapFault12 extends SoapFault {
    private static final long serialVersionUID = 1012001;
    public Node Code;
    public Node Detail;
    public Node Node;
    public Node Reason;
    public Node Role;
    public SoapFault12() {
        version = SoapEnvelope.VER12;
    }
    public SoapFault12(final int i2) {
        version = i2;
    }
    public void parse(final XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        this.parseSelf(xmlPullParser);
        faultcode = Code.getElement(SoapEnvelope.ENV2003, "Value").getText(0);
        faultstring = Reason.getElement(SoapEnvelope.ENV2003, "Text").getText(0);
        detail = Detail;
        faultactor = null;
    }
    private void parseSelf(final XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require(2, SoapEnvelope.ENV2003, "Fault");
        while (2 == xmlPullParser.nextTag()) {
            final String name = xmlPullParser.getName();
            final String namespace = xmlPullParser.getNamespace();
            xmlPullParser.nextTag();
            if (name.equalsIgnoreCase("Code")) {
                final Node node = new Node();
                Code = node;
                node.parse(xmlPullParser);
            } else if (name.equalsIgnoreCase("Reason")) {
                final Node node2 = new Node();
                Reason = node2;
                node2.parse(xmlPullParser);
            } else if (name.equalsIgnoreCase("Node")) {
                final Node node3 = new Node();
                Node = node3;
                node3.parse(xmlPullParser);
            } else if (name.equalsIgnoreCase("Role")) {
                final Node node4 = new Node();
                Role = node4;
                node4.parse(xmlPullParser);
            } else if (name.equalsIgnoreCase("Detail")) {
                final Node node5 = new Node();
                Detail = node5;
                node5.parse(xmlPullParser);
            } else {
                throw new RuntimeException("unexpected tag:" + name);
            }
            xmlPullParser.require(3, namespace, name);
        }
        xmlPullParser.require(3, SoapEnvelope.ENV2003, "Fault");
        xmlPullParser.nextTag();
    }
    public void write(final XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startTag(SoapEnvelope.ENV2003, "Fault");
        xmlSerializer.startTag(SoapEnvelope.ENV2003, "Code");
        Code.write(xmlSerializer);
        xmlSerializer.endTag(SoapEnvelope.ENV2003, "Code");
        xmlSerializer.startTag(SoapEnvelope.ENV2003, "Reason");
        Reason.write(xmlSerializer);
        xmlSerializer.endTag(SoapEnvelope.ENV2003, "Reason");
        if (null != this.Node) {
            xmlSerializer.startTag(SoapEnvelope.ENV2003, "Node");
            Node.write(xmlSerializer);
            xmlSerializer.endTag(SoapEnvelope.ENV2003, "Node");
        }
        if (null != this.Role) {
            xmlSerializer.startTag(SoapEnvelope.ENV2003, "Role");
            Role.write(xmlSerializer);
            xmlSerializer.endTag(SoapEnvelope.ENV2003, "Role");
        }
        if (null != this.Detail) {
            xmlSerializer.startTag(SoapEnvelope.ENV2003, "Detail");
            Detail.write(xmlSerializer);
            xmlSerializer.endTag(SoapEnvelope.ENV2003, "Detail");
        }
        xmlSerializer.endTag(SoapEnvelope.ENV2003, "Fault");
    }
    public String getMessage() {
        return Reason.getElement(SoapEnvelope.ENV2003, "Text").getText(0);
    }
    public String toString() {
        final String text = Reason.getElement(SoapEnvelope.ENV2003, "Text").getText(0);
        return "Code: " + Code.getElement(SoapEnvelope.ENV2003, "Value").getText(0) + ", Reason: " + text;
    }
}
