package org.kxml2.kdom;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import java.io.IOException;
import java.util.Vector;

public class Element extends Node {
    protected Vector attributes;
    protected String name;
    protected String namespace;
    protected Node parent;
    protected Vector prefixes;
    public void init() {
    }
    public void clear() {
        attributes = null;
        children = null;
    }
    public Element createElement(final String str, final String str2) {
        final Node node = parent;
        return null == node ? super.createElement (str, str2) : node.createElement (str, str2);
    }
    public int getAttributeCount() {
        final Vector vector = attributes;
        if (null == vector) {
            return 0;
        }
        return vector.size ();
    }
    public String getAttributeNamespace(final int i) {
        return ((String[]) attributes.elementAt (i))[0];
    }
    public String getAttributeName(final int i) {
        return ((String[]) attributes.elementAt (i))[1];
    }
    public String getAttributeValue(final int i) {
        return ((String[]) attributes.elementAt (i))[2];
    }
    public String getAttributeValue(final String str, final String str2) {
        for (int i = 0; i < this.getAttributeCount(); i++) {
            if (str2.equals (this.getAttributeName(i)) && (null == str || str.equals (this.getAttributeNamespace(i)))) {
                return this.getAttributeValue(i);
            }
        }
        return null;
    }
    public Node getRoot() {
        Element element = this;
        while (true) {
            final Node node = element.parent;
            if (null == node) {
                return element;
            }
            if (!(node instanceof Element)) {
                return node;
            }
            element = (Element) node;
        }
    }
    public String getName() {
        return name;
    }
    public void setName(final String str) {
        name = str;
    }
    public String getNamespace() {
        return namespace;
    }
    public void setNamespace(final String str) {
        if (null != str) {
            namespace = str;
            return;
        }
        throw new NullPointerException ("Use \"\" for empty namespace");
    }
    public String getNamespaceUri(final String str) {
        final int namespaceCount = this.getNamespaceCount();
        for (int i = 0; i < namespaceCount; i++) {
            if (str == this.getNamespacePrefix(i) || (null != str && str.equals (this.getNamespacePrefix(i)))) {
                return this.getNamespaceUri(i);
            }
        }
        final Node node = parent;
        if (node instanceof Element) {
            return ((Element) node).getNamespaceUri (str);
        }
        return null;
    }
    public int getNamespaceCount() {
        final Vector vector = prefixes;
        if (null == vector) {
            return 0;
        }
        return vector.size ();
    }
    public String getNamespacePrefix(final int i) {
        return ((String[]) prefixes.elementAt (i))[0];
    }
    public String getNamespaceUri(final int i) {
        return ((String[]) prefixes.elementAt (i))[1];
    }
    public Node getParent() {
        return parent;
    }
    public void setParent(final Node node) {
        parent = node;
    }
    public void parse(final XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        for (int namespaceCount = xmlPullParser.getNamespaceCount (xmlPullParser.getDepth () - 1); namespaceCount < xmlPullParser.getNamespaceCount (xmlPullParser.getDepth ()); namespaceCount++) {
            this.setPrefix(xmlPullParser.getNamespacePrefix (namespaceCount), xmlPullParser.getNamespaceUri (namespaceCount));
        }
        for (int i = 0; i < xmlPullParser.getAttributeCount (); i++) {
            this.setAttribute(xmlPullParser.getAttributeNamespace (i), xmlPullParser.getAttributeName (i), xmlPullParser.getAttributeValue (i));
        }
        this.init();
        if (xmlPullParser.isEmptyElementTag ()) {
            xmlPullParser.nextToken ();
        } else {
            xmlPullParser.nextToken ();
            super.parse (xmlPullParser);
            if (0 == getChildCount ()) {
                this.addChild(7, "");
            }
        }
        xmlPullParser.require (3, this.namespace, this.name);
        xmlPullParser.nextToken ();
    }
    public void setAttribute(String str, final String str2, final String str3) {
        if (null == this.attributes) {
            attributes = new Vector ();
        }
        if (null == str) {
            str = "";
        }
        for (int size = attributes.size () - 1; 0 <= size; size--) {
            final String[] strArr = (String[]) attributes.elementAt (size);
            if (strArr[0].equals (str) && strArr[1].equals (str2)) {
                if (null == str3) {
                    attributes.removeElementAt (size);
                    return;
                } else {
                    strArr[2] = str3;
                    return;
                }
            }
        }
        attributes.addElement (new String[]{str, str2, str3});
    }
    public void setPrefix(final String str, final String str2) {
        if (null == this.prefixes) {
            prefixes = new Vector ();
        }
        prefixes.addElement (new String[]{str, str2});
    }
    public void write(final XmlSerializer xmlSerializer) throws IOException {
        if (null != this.prefixes) {
            for (int i = 0; i < prefixes.size (); i++) {
                xmlSerializer.setPrefix (this.getNamespacePrefix(i), this.getNamespaceUri(i));
            }
        }
        xmlSerializer.startTag (this.namespace, this.name);
        final int attributeCount = this.getAttributeCount();
        for (int i2 = 0; i2 < attributeCount; i2++) {
            xmlSerializer.attribute (this.getAttributeNamespace(i2), this.getAttributeName(i2), this.getAttributeValue(i2));
        }
        this.writeChildren(xmlSerializer);
        xmlSerializer.endTag (this.namespace, this.name);
    }
}
