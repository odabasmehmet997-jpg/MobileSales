package org.xmlpull.v1;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

public class XmlPullParserFactory {
    public static final String PROPERTY_NAME = "org.xmlpull.v1.XmlPullParserFactory";
    private static final String RESOURCE_NAME = "/META-INF/services/org.xmlpull.v1.XmlPullParserFactory";
    static final Class referenceContextClass;
    protected String classNamesLocation;
    protected Hashtable features = new Hashtable();
    protected Vector parserClasses;
    protected Vector serializerClasses;
    static {
        new XmlPullParserFactory();
        referenceContextClass = XmlPullParserFactory.class;
    }
    protected XmlPullParserFactory() {
    }
    public void setFeature(final String str, final boolean z) throws XmlPullParserException {
        features.put(str, Boolean.valueOf(z));
    }
    public boolean getFeature(final String str) {
        final Boolean bool = (Boolean) features.get(str);
        if (null != bool) {
            return bool.booleanValue();
        }
        return false;
    }
    public void setNamespaceAware(final boolean z) {
        features.put(XmlPullParser.FEATURE_PROCESS_NAMESPACES, Boolean.valueOf(z));
    }
    public boolean isNamespaceAware() {
        return this.getFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES);
    }
    public void setValidating(final boolean z) {
        features.put(XmlPullParser.FEATURE_VALIDATION, Boolean.valueOf(z));
    }
    public boolean isValidating() {
        return this.getFeature(XmlPullParser.FEATURE_VALIDATION);
    }
    public XmlPullParser newPullParser() throws XmlPullParserException {
        final Vector vector = parserClasses;
        if (null == vector) {
            throw new XmlPullParserException("Factory initialization was incomplete - has not tried " + classNamesLocation);
        }
        if (0 == vector.size()) {
            throw new XmlPullParserException("No valid parser classes found in " + classNamesLocation);
        }
        final StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < parserClasses.size(); i2++) {
            final Class cls = (Class) parserClasses.elementAt(i2);
            try {
                final XmlPullParser xmlPullParser = (XmlPullParser) cls.newInstance();
                Iterator iterator = this.features.keySet().iterator();
                while (iterator.hasNext()) {
                    final String str = (String) iterator.next();
                    final Boolean bool = (Boolean) features.get(str);
                    if (null != bool && bool.booleanValue()) {
                        xmlPullParser.setFeature(str, true);
                    }
                }
                return xmlPullParser;
            } catch (final Exception e2) {
                stringBuffer.append(cls.getName() + ": " + e2 + "; ");
            }
        }
        throw new XmlPullParserException("could not create parser: " + stringBuffer);
    }
    public XmlSerializer newSerializer() throws XmlPullParserException {
        final Vector vector = serializerClasses;
        if (null == vector) {
            throw new XmlPullParserException("Factory initialization incomplete - has not tried " + classNamesLocation);
        }
        if (0 == vector.size()) {
            throw new XmlPullParserException("No valid serializer classes found in " + classNamesLocation);
        }
        final StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < serializerClasses.size(); i2++) {
            final Class cls = (Class) serializerClasses.elementAt(i2);
            try {
                return (XmlSerializer) cls.newInstance();
            } catch (final Exception e2) {
                stringBuffer.append(cls.getName() + ": " + e2 + "; ");
            }
        }
        throw new XmlPullParserException("could not create serializer: " + stringBuffer);
    }
    public static XmlPullParserFactory newInstance() throws XmlPullParserException {
        return newInstance(null, null);
    }

    public static XmlPullParserFactory newInstance(String str, Class<?> cls) throws XmlPullParserException {
        final String str2;
        Class<?> cls2;
        Object obj;
        boolean z;
        if (null == cls) {
            cls = XmlPullParserFactory.referenceContextClass;
        }
        if (null == str || 0 == str.length() || "DEFAULT".equals(str)) {
            try {
                final InputStream resourceAsStream = cls.getResourceAsStream(XmlPullParserFactory.RESOURCE_NAME);
                if (null == resourceAsStream) {
                    throw new XmlPullParserException("resource not found: /META-INF/services/org.xmlpull.v1.XmlPullParserFactory make sure that parser implementing XmlPull API is available");
                }
                final StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    final int read = resourceAsStream.read();
                    if (0 > read) {
                        break;
                    }
                    if (32 < read) {
                        stringBuffer.append((char) read);
                    }
                }
                resourceAsStream.close();
                str = stringBuffer.toString();
                str2 = "resource /META-INF/services/org.xmlpull.v1.XmlPullParserFactory that contained '" + str + "'";
            } catch (final Exception e2) {
                throw new XmlPullParserException(null, null, e2);
            }
        } else {
            str2 = "parameter classNames to newInstance() that contained '" + str + "'";
        }
        final var vector = new ArrayList();
        final var vector2 = new ArrayList();
        XmlPullParserFactory xmlPullParserFactory = null;
        int i2 = 0;
        while (i2 < str.length()) {
            int indexOf = str.indexOf(44, i2);
            if (-1 == indexOf) {
                indexOf = str.length();
            }
            final String substring = str.substring(i2, indexOf);
            try {
                cls2 = Class.forName(substring);
            } catch (final Exception unused) {
                cls2 = null;
            }
            try {
                obj = cls2.newInstance();
            } catch (final Exception unused2) {
                obj = null;
            }
            if (null == cls2) {
                boolean z2 = true;
                if (obj instanceof XmlPullParser) {

                    (( Vector<?> )vector).addElement(cls2);
                    z = true;
                } else {
                    z = false;
                }
                if (obj instanceof XmlSerializer) {
                    (( Vector<?> ) vector2).addElement(cls2);
                    z = true;
                }
                if (!(obj instanceof XmlPullParserFactory)) {
                    z2 = z;
                } else if (null == xmlPullParserFactory) {
                    xmlPullParserFactory = (XmlPullParserFactory) obj;
                }
                if (!z2) {
                    throw new XmlPullParserException("incompatible class: " + substring);
                }
            }
            i2 = indexOf + 1;
        }
        if (null == xmlPullParserFactory) {
            xmlPullParserFactory = new XmlPullParserFactory();
        }
        xmlPullParserFactory.parserClasses = vector;
        xmlPullParserFactory.serializerClasses = vector2;
        xmlPullParserFactory.classNamesLocation = str2;
        return xmlPullParserFactory;
    }
}
