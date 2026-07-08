package org.kxml2.kdom;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import java.io.IOException;
import java.util.Vector;

public class Node {
    public static final int CDSECT = 5;
    public static final int COMMENT = 9;
    public static final int DOCDECL = 10;
    public static final int DOCUMENT = 0;
    public static final int ELEMENT = 2;
    public static final int ENTITY_REF = 6;
    public static final int IGNORABLE_WHITESPACE = 7;
    public static final int PROCESSING_INSTRUCTION = 8;
    public static final int TEXT = 4;
    protected Vector children;
    protected StringBuffer types;
    public void addChild(final int i, final int i2, final Object obj) {
        obj.getClass ();
        if (null == this.children) {
            children = new Vector ();
            types = new StringBuffer ();
        }
        if (2 == i2) {
            if (obj instanceof Element) {
                ((Element) obj).setParent (this);
            } else {
                throw new RuntimeException ("Element obj expected)");
            }
        } else if (!(obj instanceof String)) {
            throw new RuntimeException ("String expected");
        }
        children.insertElementAt (obj, i);
        types.insert (i, (char) i2);
    }
    public void addChild(final int i, final Object obj) {
        this.addChild(this.getChildCount(), i, obj);
    }
    public Element createElement(String str, final String str2) {
        final Element element = new Element ();
        if (null == str) {
            str = "";
        }
        element.namespace = str;
        element.name = str2;
        return element;
    }
    public Object getChild(final int i) {
        return children.elementAt (i);
    }
    public int getChildCount() {
        final Vector vector = children;
        if (null == vector) {
            return 0;
        }
        return vector.size ();
    }
    public Element getElement(final int i) {
        final Object child = this.getChild(i);
        if (child instanceof Element) {
            return (Element) child;
        }
        return null;
    }
    public Element getElement(final String str, final String str2) {
        final int indexOf = this.indexOf(str, str2, 0);
        final int indexOf2 = this.indexOf(str, str2, indexOf + 1);
        if (-1 != indexOf && -1 == indexOf2) {
            return this.getElement(indexOf);
        }
        String sb = "Element {" +
                str +
                "}" +
                str2 +
                (-1 == indexOf ? " not found in " : " more than once in ") +
                this;
        throw new RuntimeException (sb);
    }
    public String getText(final int i) {
        if (this.isText(i)) {
            return (String) this.getChild(i);
        }
        return null;
    }
    public int getType(final int i) {
        return types.charAt (i);
    }
    public int indexOf(final String str, final String str2, int i) {
        final int childCount = this.getChildCount();
        while (i < childCount) {
            final Element element = this.getElement(i);
            if (null != element && str2.equals (element.getName ()) && (null == str || str.equals (element.getNamespace ()))) {
                return i;
            }
            i++;
        }
        return -1;
    }
    public boolean isText(final int i) {
        final int type = this.getType(i);
        return 4 == type || 7 == type || 5 == type;
    }
    public void parse(final XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        boolean z = false;
        do {
            int eventType = xmlPullParser.getEventType ();
            if (1 != eventType) {
                if (2 == eventType) {
                    final Element createElement = this.createElement(xmlPullParser.getNamespace (), xmlPullParser.getName ());
                    this.addChild(2, createElement);
                    createElement.parse (xmlPullParser);
                    continue;
                } else if (3 != eventType) {
                    if (null != xmlPullParser.getText ()) {
                        if (6 == eventType) {
                            eventType = 4;
                        }
                        this.addChild(eventType, xmlPullParser.getText ());
                    } else if (6 == eventType && null != xmlPullParser.getName ()) {
                        this.addChild(6, xmlPullParser.getName ());
                    }
                    xmlPullParser.nextToken ();
                    continue;
                }
            }
            z = true;
            continue;
        } while (!z);
    }
    public void removeChild(int i) {
        children.removeElementAt (i);
        final int length = types.length () - 1;
        while (i < length) {
            final StringBuffer stringBuffer = types;
            final int i2 = i + 1;
            stringBuffer.setCharAt (i, stringBuffer.charAt (i2));
            i = i2;
        }
        types.setLength (length);
    }
    public void write(final XmlSerializer xmlSerializer) throws IOException {
        this.writeChildren(xmlSerializer);
        xmlSerializer.flush ();
    }
    public void writeChildren(final XmlSerializer xmlSerializer) throws IOException {
        final Vector vector = children;
        if (null != vector) {
            final int size = vector.size ();
            for (int i = 0; i < size; i++) {
                final int type = this.getType(i);
                final Object elementAt = children.elementAt (i);
                switch (type) {
                    case 2:
                        ((Element) elementAt).write (xmlSerializer);
                        break;
                    case 3:
                    default:
                        throw new RuntimeException ("Illegal type: " + type);
                    case 4:
                        xmlSerializer.text ((String) elementAt);
                        break;
                    case 5:
                        xmlSerializer.cdsect ((String) elementAt);
                        break;
                    case 6:
                        xmlSerializer.entityRef ((String) elementAt);
                        break;
                    case 7:
                        xmlSerializer.ignorableWhitespace ((String) elementAt);
                        break;
                    case 8:
                        xmlSerializer.processingInstruction ((String) elementAt);
                        break;
                    case 9:
                        xmlSerializer.comment ((String) elementAt);
                        break;
                    case 10:
                        xmlSerializer.docdecl ((String) elementAt);
                        break;
                }
            }
        }
    }
}
