package org.kxml2.kdom;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import java.io.IOException;

public class Document extends Node {
    protected int rootIndex = -1;
    String encoding;
    Boolean standalone;
    public String getEncoding() {
        return encoding;
    }
    public void setEncoding(final String str) {
        encoding = str;
    }
    public Boolean getStandalone() {
        return standalone;
    }
    public void setStandalone(final Boolean bool) {
        standalone = bool;
    }
    public String getName() {
        return "#document";
    }
    public void addChild(final int i, final int i2, final Object obj) {
        if (2 == i2) {
            rootIndex = i;
        } else {
            final int i3 = rootIndex;
            if (i3 >= i) {
                rootIndex = i3 + 1;
            }
        }
        super.addChild (i, i2, obj);
    }
    public void parse(final XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        xmlPullParser.require (0, null, null);
        xmlPullParser.nextToken ();
        encoding = xmlPullParser.getInputEncoding ();
        standalone = (Boolean) xmlPullParser.getProperty ("http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone");
        super.parse (xmlPullParser);
        if (1 != xmlPullParser.getEventType ()) {
            throw new RuntimeException ("Document end expected!");
        }
    }
    public void removeChild(final int i) {
        final int i2 = rootIndex;
        if (i == i2) {
            rootIndex = -1;
        } else if (i < i2) {
            rootIndex = i2 - 1;
        }
        super.removeChild (i);
    }
    public Element getRootElement() {
        final int i = rootIndex;
        if (-1 != i) {
            return (Element) this.getChild(i);
        }
        throw new RuntimeException ("Document has no root element!");
    }
    public void write(final XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.startDocument (encoding, standalone);
        this.writeChildren(xmlSerializer);
        xmlSerializer.endDocument ();
    }
}
