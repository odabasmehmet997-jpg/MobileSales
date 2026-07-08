package org.ksoap2.serialization;

import okhttp3.HttpUrl;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.SoapFault12;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

public class SoapSerializationEnvelope extends SoapEnvelope {
    private static final String ANY_TYPE_LABEL = "anyType";
    private static final String ARRAY_MAPPING_NAME = "Array";
    private static final String ARRAY_TYPE_LABEL = "arrayType";
    static final Marshal DEFAULT_MARSHAL = new C0698DM();
    private static final String HREF_LABEL = "href";
    private static final String ID_LABEL = "id";
    private static final String ITEM_LABEL = "item";
    protected static final String NIL_LABEL = "nil";
    protected static final String NULL_LABEL = "null";
    protected static final int QNAME_MARSHAL = 3;
    protected static final int QNAME_NAMESPACE = 0;
    protected static final int QNAME_TYPE = 1;
    private static final String ROOT_LABEL = "root";
    private static final String TYPE_LABEL = "type";
    protected boolean addAdornments;
    public boolean avoidExceptionForUnknownProperty;
    protected Hashtable classToQName;
    public boolean dotNet;
    Hashtable idMap;
    public boolean implicitTypes;
    Vector multiRef;
    public Hashtable properties;
    protected Hashtable qNameToClass;
    public boolean skipNullProperties;
    public SoapSerializationEnvelope(final int i2) {
        super(i2);
        properties = new Hashtable();
        qNameToClass = new Hashtable();
        classToQName = new Hashtable();
        addAdornments = true;
        idMap = new Hashtable();
        this.addMapping(enc, SoapSerializationEnvelope.ARRAY_MAPPING_NAME, PropertyInfo.VECTOR_CLASS);
        SoapSerializationEnvelope.DEFAULT_MARSHAL.register(this);
    }
    public boolean isAddAdornments() {
        return addAdornments;
    }
    public void setAddAdornments(final boolean z) {
        addAdornments = z;
    }
    public void setBodyOutEmpty(final boolean z) {
        if (z) {
            bodyOut = null;
        }
    }
    public void parseBody(final XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        final SoapFault soapFault12;
        bodyIn = null;
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
        while (2 == xmlPullParser.getEventType()) {
            final String attributeValue = xmlPullParser.getAttributeValue(enc, SoapSerializationEnvelope.ROOT_LABEL);
            final Object read = this.read(xmlPullParser, null, -1, xmlPullParser.getNamespace(), xmlPullParser.getName(), PropertyInfo.OBJECT_TYPE);
            if ("1".equals(attributeValue) || null == this.bodyIn) {
                bodyIn = read;
            }
            xmlPullParser.nextTag();
        }
    }
    protected void readSerializable(final XmlPullParser xmlPullParser, final SoapObject soapObject) throws IOException, XmlPullParserException {
        for (int i2 = 0; i2 < xmlPullParser.getAttributeCount(); i2++) {
            soapObject.addAttribute(xmlPullParser.getAttributeName(i2), xmlPullParser.getAttributeValue(i2));
        }
        this.readSerializable(xmlPullParser, (KvmSerializable) soapObject);
    }
    protected void readSerializable(final XmlPullParser xmlPullParser, final KvmSerializable kvmSerializable) throws IOException, XmlPullParserException {
        int nextTag;
        int i2;
        try {
            nextTag = xmlPullParser.nextTag();
        } catch (final XmlPullParserException unused) {
            if (kvmSerializable instanceof HasInnerText) {
                ((HasInnerText) kvmSerializable).setInnerText(null != xmlPullParser.getText() ? xmlPullParser.getText() : HttpUrl.FRAGMENT_ENCODE_SET);
            }
            nextTag = xmlPullParser.nextTag();
        }
        while (3 != nextTag) {
            final String name = xmlPullParser.getName();
            if (!implicitTypes || !(kvmSerializable instanceof SoapObject soapObject)) {
                final PropertyInfo propertyInfo = new PropertyInfo();
                final int propertyCount = kvmSerializable.getPropertyCount();
                boolean z = false;
                int i3 = 0;
                while (i3 < propertyCount && !z) {
                    propertyInfo.clear();
                    kvmSerializable.getPropertyInfo(i3, properties, propertyInfo);
                    if ((name.equals(propertyInfo.name) && null == propertyInfo.namespace) || (name.equals(propertyInfo.name) && xmlPullParser.getNamespace().equals(propertyInfo.namespace))) {
                        i2 = i3;
                        kvmSerializable.setProperty(i2, this.read(xmlPullParser, kvmSerializable, i3, null, null, propertyInfo));
                        z = true;
                    } else {
                        i2 = i3;
                    }
                    i3 = i2 + 1;
                }
                if (!z) {
                    if (!avoidExceptionForUnknownProperty) {
                        throw new RuntimeException("Unknown Property: " + name);
                    }
                    while (true) {
                        if (3 != xmlPullParser.next() || !name.equals(xmlPullParser.getName())) {
                        }
                    }
                } else if (kvmSerializable instanceof HasAttributes hasAttributes) {
                    final int attributeCount = xmlPullParser.getAttributeCount();
                    for (int i4 = 0; i4 < attributeCount; i4++) {
                        final AttributeInfo attributeInfo = new AttributeInfo();
                        attributeInfo.setName(xmlPullParser.getAttributeName(i4));
                        attributeInfo.setValue(xmlPullParser.getAttributeValue(i4));
                        attributeInfo.setNamespace(xmlPullParser.getAttributeNamespace(i4));
                        attributeInfo.setType(xmlPullParser.getAttributeType(i4));
                        hasAttributes.setAttribute(attributeInfo);
                    }
                }
            } else {
                soapObject.addProperty(xmlPullParser.getName(), this.read(xmlPullParser, kvmSerializable, kvmSerializable.getPropertyCount(), soapObject.getNamespace(), name, PropertyInfo.OBJECT_TYPE));
            }
            nextTag = xmlPullParser.nextTag();
        }
        xmlPullParser.require(3, null, null);
    }
    protected Object readUnknown(final XmlPullParser xmlPullParser, final String str, final String str2) throws IOException, XmlPullParserException {
        AttributeContainer attributeContainer;
        final String str3;
        final String name = xmlPullParser.getName();
        final String namespace = xmlPullParser.getNamespace();
        final Vector vector = new Vector();
        for (int i2 = 0; i2 < xmlPullParser.getAttributeCount(); i2++) {
            final AttributeInfo attributeInfo = new AttributeInfo();
            attributeInfo.setName(xmlPullParser.getAttributeName(i2));
            attributeInfo.setValue(xmlPullParser.getAttributeValue(i2));
            attributeInfo.setNamespace(xmlPullParser.getAttributeNamespace(i2));
            attributeInfo.setType(xmlPullParser.getAttributeType(i2));
            vector.addElement(attributeInfo);
        }
        xmlPullParser.next();
        if (4 == xmlPullParser.getEventType()) {
            str3 = xmlPullParser.getText();
            attributeContainer = new SoapPrimitive(str, str2, str3);
            for (int i3 = 0; i3 < vector.size(); i3++) {
                attributeContainer.addAttribute((AttributeInfo) vector.elementAt(i3));
            }
            xmlPullParser.next();
        } else {
            attributeContainer = null;
            if (3 == xmlPullParser.getEventType()) {
                final AttributeContainer soapObject = new SoapObject(str, str2);
                for (int i4 = 0; i4 < vector.size(); i4++) {
                    soapObject.addAttribute((AttributeInfo) vector.elementAt(i4));
                }
                attributeContainer = soapObject;
                str3 = null;
            } else {
                str3 = null;
            }
        }
        if (2 == xmlPullParser.getEventType()) {
            if (null != str3 && 0 != str3.trim().length()) {
                throw new RuntimeException("Malformed input: Mixed content");
            }
            final SoapObject soapObject2 = new SoapObject(str, str2);
            for (int i5 = 0; i5 < vector.size(); i5++) {
                soapObject2.addAttribute((AttributeInfo) vector.elementAt(i5));
            }
            while (3 != xmlPullParser.getEventType()) {
                soapObject2.addProperty(xmlPullParser.getNamespace(), xmlPullParser.getName(), this.read(xmlPullParser, soapObject2, soapObject2.getPropertyCount(), null, null, PropertyInfo.OBJECT_TYPE));
                xmlPullParser.nextTag();
            }
            attributeContainer = soapObject2;
        }
        xmlPullParser.require(3, namespace, name);
        return attributeContainer;
    }
    private int getIndex(final String str, final int i2, final int i3) {
        if (null == str) {
            return i3;
        }
        try {
            return 3 > str.length() - i2 ? i3 : Integer.parseInt(str.substring(i2 + 1, str.length() - 1));
        } catch (final Exception unused) {
            return i3;
        }
    }
    protected void readVector(final XmlPullParser xmlPullParser, final Vector vector, final PropertyInfo propertyInfo) throws IOException, XmlPullParserException {
        final boolean z;
        final String str;
        final String str2;
        int size = vector.size();
        final String attributeValue = xmlPullParser.getAttributeValue(enc, SoapSerializationEnvelope.ARRAY_TYPE_LABEL);
        int i2 = 0;
        if (null != attributeValue) {
            final int indexOf = attributeValue.indexOf(58);
            final int indexOf2 = attributeValue.indexOf('[', indexOf);
            final String substring = attributeValue.substring(indexOf + 1, indexOf2);
            final String namespace = xmlPullParser.getNamespace(-1 == indexOf ? HttpUrl.FRAGMENT_ENCODE_SET : attributeValue.substring(0, indexOf));
            final int index = this.getIndex(attributeValue, indexOf2, -1);
            if (-1 != index) {
                vector.setSize(index);
                str = namespace;
                size = index;
                str2 = substring;
                z = false;
            } else {
                str = namespace;
                size = index;
                z = true;
                str2 = substring;
            }
        } else {
            z = true;
            str = null;
            str2 = null;
        }
        final PropertyInfo propertyInfo2 = null == propertyInfo ? PropertyInfo.OBJECT_TYPE : propertyInfo;
        xmlPullParser.nextTag();
        int index2 = this.getIndex(xmlPullParser.getAttributeValue(enc, "offset"), 0, 0);
        while (3 != xmlPullParser.getEventType()) {
            final int index3 = this.getIndex(xmlPullParser.getAttributeValue(enc, "position"), i2, index2);
            if (z && index3 >= size) {
                size = index3 + 1;
                vector.setSize(size);
            }
            vector.setElementAt(this.read(xmlPullParser, vector, index3, str, str2, propertyInfo2), index3);
            index2 = index3 + 1;
            xmlPullParser.nextTag();
            size = size;
            i2 = 0;
        }
        xmlPullParser.require(3, null, null);
    }
    protected String getIdFromHref(final String str) {
        return str.substring(1);
    }
    public Object read(final XmlPullParser xmlPullParser, final Object obj, final int i2, String str, String str2, final PropertyInfo propertyInfo) throws IOException, XmlPullParserException {
        Object obj2;
        final String name = xmlPullParser.getName();
        final String attributeValue = xmlPullParser.getAttributeValue(null, SoapSerializationEnvelope.HREF_LABEL);
        if (null == attributeValue) {
            String attributeValue2 = xmlPullParser.getAttributeValue(xsi, SoapSerializationEnvelope.NIL_LABEL);
            final String attributeValue3 = xmlPullParser.getAttributeValue(null, SoapSerializationEnvelope.ID_LABEL);
            if (null == attributeValue2) {
                attributeValue2 = xmlPullParser.getAttributeValue(xsi, SoapSerializationEnvelope.NULL_LABEL);
            }
            if (stringToBoolean(attributeValue2)) {
                xmlPullParser.nextTag();
                xmlPullParser.require(3, null, name);
                obj2 = null;
            } else {
                final String attributeValue4 = xmlPullParser.getAttributeValue(xsi, SoapSerializationEnvelope.TYPE_LABEL);
                if (null != attributeValue4) {
                    final int indexOf = attributeValue4.indexOf(58);
                    str2 = attributeValue4.substring(indexOf + 1);
                    str = xmlPullParser.getNamespace(-1 == indexOf ? HttpUrl.FRAGMENT_ENCODE_SET : attributeValue4.substring(0, indexOf));
                } else if (null == str2 && null == str) {
                    if (null != xmlPullParser.getAttributeValue(this.enc, ARRAY_TYPE_LABEL)) {
                        str = enc;
                        str2 = SoapSerializationEnvelope.ARRAY_MAPPING_NAME;
                    } else {
                        final Object[] info = this.getInfo(propertyInfo.type, null);
                        final String str3 = (String) info[0];
                        str2 = (String) info[1];
                        str = str3;
                    }
                }
                if (null == attributeValue4) {
                    implicitTypes = true;
                }
                Object readInstance = this.readInstance(xmlPullParser, str, str2, propertyInfo);
                if (null == readInstance) {
                    readInstance = this.readUnknown(xmlPullParser, str, str2);
                }
                obj2 = readInstance;
            }
            if (null != attributeValue3) {
                this.resolveReference(attributeValue3, obj2);
            }
        } else {
            if (null == obj) {
                throw new RuntimeException("href at root level?!?");
            }
            final String idFromHref = this.getIdFromHref(attributeValue);
            obj2 = idMap.get(idFromHref);
            if (null == obj2 || (obj2 instanceof FwdRef)) {
                final FwdRef fwdRef = new FwdRef();
                fwdRef.next = (FwdRef) obj2;
                fwdRef.obj = obj;
                fwdRef.index = i2;
                idMap.put(idFromHref, fwdRef);
                obj2 = null;
            }
            xmlPullParser.nextTag();
            xmlPullParser.require(3, null, name);
        }
        xmlPullParser.require(3, null, name);
        return obj2;
    }
    protected void resolveReference(final String str, final Object obj) {
        final Object obj2 = idMap.get(str);
        if (obj2 instanceof FwdRef fwdRef) {
            do {
                final Object obj3 = fwdRef.obj;
                if (obj3 instanceof KvmSerializable) {
                    ((KvmSerializable) obj3).setProperty(fwdRef.index, obj);
                } else {
                    ((Vector) obj3).setElementAt(obj, fwdRef.index);
                }
                fwdRef = fwdRef.next;
            } while (null != fwdRef);
        } else if (null != obj2) {
            throw new RuntimeException("double ID");
        }
        idMap.put(str, obj);
    }
    public Object readInstance(final XmlPullParser xmlPullParser, final String str, final String str2, final PropertyInfo propertyInfo) throws IOException, XmlPullParserException {
        final Object newInstance;
        final Object obj = qNameToClass.get(new SoapPrimitive(str, str2, null));
        if (null == obj) {
            return null;
        }
        if (obj instanceof Marshal) {
            return ((Marshal) obj).readInstance(xmlPullParser, str, str2, propertyInfo);
        }
        if (obj instanceof SoapObject) {
            newInstance = ((SoapObject) obj).newInstance();
        } else if (SoapObject.class == obj) {
            newInstance = new SoapObject(str, str2);
        } else {
            try {
                newInstance = ((Class) obj).newInstance();
            } catch (final Exception e2) {
                throw new RuntimeException(e2.toString());
            }
        }
        if (newInstance instanceof HasAttributes hasAttributes) {
            final int attributeCount = xmlPullParser.getAttributeCount();
            for (int i2 = 0; i2 < attributeCount; i2++) {
                final AttributeInfo attributeInfo = new AttributeInfo();
                attributeInfo.setName(xmlPullParser.getAttributeName(i2));
                attributeInfo.setValue(xmlPullParser.getAttributeValue(i2));
                attributeInfo.setNamespace(xmlPullParser.getAttributeNamespace(i2));
                attributeInfo.setType(xmlPullParser.getAttributeType(i2));
                hasAttributes.setAttribute(attributeInfo);
            }
        }
        if (newInstance instanceof SoapObject) {
            this.readSerializable(xmlPullParser, (SoapObject) newInstance);
        } else if (newInstance instanceof KvmSerializable) {
            if (newInstance instanceof HasInnerText) {
                ((HasInnerText) newInstance).setInnerText(null != xmlPullParser.getText() ? xmlPullParser.getText() : HttpUrl.FRAGMENT_ENCODE_SET);
            }
            this.readSerializable(xmlPullParser, (KvmSerializable) newInstance);
        } else if (newInstance instanceof Vector) {
            this.readVector(xmlPullParser, (Vector) newInstance, propertyInfo.elementType);
        } else {
            throw new RuntimeException("no deserializer for " + newInstance.getClass());
        }
        return newInstance;
    }
    public Object[] getInfo(Object obj, final Object obj2) {
        final Object[] objArr;
        if (null == obj) {
            obj = ((obj2 instanceof SoapObject) || (obj2 instanceof SoapPrimitive)) ? obj2 : obj2.getClass();
        }
        if (obj instanceof SoapObject soapObject) {
            return new Object[]{soapObject.getNamespace(), soapObject.getName(), null, null};
        }
        if (!(obj instanceof SoapPrimitive soapPrimitive)) {
            return (!(obj instanceof Class) || obj == PropertyInfo.OBJECT_CLASS || null == (objArr = (Object[]) this.classToQName.get(((Class) obj).getName()))) ? new Object[]{xsd, SoapSerializationEnvelope.ANY_TYPE_LABEL, null, null} : objArr;
        }
        return new Object[]{soapPrimitive.getNamespace(), soapPrimitive.getName(), null, SoapSerializationEnvelope.DEFAULT_MARSHAL};
    }
    public void addMapping(final String str, final String str2, final Class cls, final Marshal marshal) {
        if (str == null || str2 == null || cls == null) {
            throw new IllegalArgumentException("Namespace, name, and class cannot be null");
        }
        classToQName.put(cls.getName(), new Object[]{str, str2, null, marshal});

    }
    public void addMapping(final String str, final String str2, final Class cls) {
        this.addMapping(str, str2, cls, null);
    }
    public void addTemplate(final SoapObject soapObject) {
        if (soapObject == null) {
            throw new IllegalArgumentException("SoapObject cannot be null");
        }
        qNameToClass.put(new SoapPrimitive(soapObject.namespace, soapObject.name, null), soapObject);
    }
    public Object getResponse() throws SoapFault {
        final Object obj = bodyIn;
        if (obj == null) {
            return null;
        }
        if (obj instanceof SoapFault) {
            throw (SoapFault) obj;
        }
        final KvmSerializable kvmSerializable = (KvmSerializable) obj;
        if (kvmSerializable.getPropertyCount() == 0) {
            return null;
        }
        if (kvmSerializable.getPropertyCount() == 1) {
            return kvmSerializable.getProperty(0);
        }
        final Vector vector = new Vector();
        for (int i2 = 0; i2 < kvmSerializable.getPropertyCount(); i2++) {
            vector.add(kvmSerializable.getProperty(i2));
        }
        return vector;
    }
    public void writeBody(final XmlSerializer xmlSerializer) throws IOException {
        if (null != this.bodyOut) {
            final Vector vector = new Vector();
            multiRef = vector;
            vector.addElement(bodyOut);
            final Object[] info = this.getInfo(null, bodyOut);
            final boolean z = dotNet;
            String str = HttpUrl.FRAGMENT_ENCODE_SET;
            xmlSerializer.startTag(z ? HttpUrl.FRAGMENT_ENCODE_SET : (String) info[0], (String) info[1]);
            if (dotNet) {
                xmlSerializer.attribute(null, "xmlns", (String) info[0]);
            }
            if (addAdornments) {
                final Object obj = info[2];
                xmlSerializer.attribute(null, SoapSerializationEnvelope.ID_LABEL, null == obj ? "o0" : (String) obj);
                xmlSerializer.attribute(enc, SoapSerializationEnvelope.ROOT_LABEL, "1");
            }
            this.writeElement(xmlSerializer, bodyOut, null, info[3]);
            if (!dotNet) {
                str = (String) info[0];
            }
            xmlSerializer.endTag(str, (String) info[1]);
        }
    }
    private void writeAttributes(final XmlSerializer xmlSerializer, final HasAttributes hasAttributes) throws IOException {
        final int attributeCount = hasAttributes.getAttributeCount();
        for (int i2 = 0; i2 < attributeCount; i2++) {
            final AttributeInfo attributeInfo = new AttributeInfo();
            hasAttributes.getAttributeInfo(i2, attributeInfo);
            hasAttributes.getAttribute(i2, attributeInfo);
            if (null != attributeInfo.getValue()) {
                xmlSerializer.attribute(attributeInfo.getNamespace(), attributeInfo.getName(), attributeInfo.getValue().toString());
            }
        }
    }
    public void writeArrayListBodyWithAttributes(final XmlSerializer xmlSerializer, final KvmSerializable kvmSerializable) throws IOException {
        if (kvmSerializable instanceof HasAttributes) {
            this.writeAttributes(xmlSerializer, (HasAttributes) kvmSerializable);
        }
        this.writeArrayListBody(xmlSerializer, (ArrayList) kvmSerializable);
    }
    public void writeObjectBodyWithAttributes(final XmlSerializer xmlSerializer, final KvmSerializable kvmSerializable) throws IOException {
        if (kvmSerializable instanceof HasAttributes) {
            this.writeAttributes(xmlSerializer, (HasAttributes) kvmSerializable);
        }
        this.writeObjectBody(xmlSerializer, kvmSerializable);
    }
    public void writeObjectBody(final XmlSerializer xmlSerializer, final KvmSerializable kvmSerializable) throws IOException {
        String str;
        String str2;
        final int propertyCount = kvmSerializable.getPropertyCount();
        final PropertyInfo propertyInfo = new PropertyInfo();
        for (int i2 = 0; i2 < propertyCount; i2++) {
            final Object property = kvmSerializable.getProperty(i2);
            kvmSerializable.getPropertyInfo(i2, properties, propertyInfo);
            if (!(property instanceof SoapObject)) {
                if (0 == (propertyInfo.flags & 1)) {
                    final Object property2 = kvmSerializable.getProperty(i2);
                    if ((null != property || !skipNullProperties) && property2 != SoapPrimitive.NullSkip) {
                        xmlSerializer.startTag(propertyInfo.namespace, propertyInfo.name);
                        this.writeProperty(xmlSerializer, property2, propertyInfo);
                        xmlSerializer.endTag(propertyInfo.namespace, propertyInfo.name);
                    }
                }
            } else {
                final KvmSerializable kvmSerializable2 = (SoapObject) property;
                final Object[] info = this.getInfo(null, kvmSerializable2);
                final String str3 = (String) info[1];
                final String str4 = propertyInfo.name;
                if (null != str4 && 0 < str4.length()) {
                    str = propertyInfo.name;
                } else {
                    str = (String) info[1];
                }
                final String str5 = propertyInfo.namespace;
                if (null != str5 && 0 < str5.length()) {
                    str2 = propertyInfo.namespace;
                } else {
                    str2 = (String) info[0];
                }
                xmlSerializer.startTag(str2, str);
                if (!implicitTypes) {
                    final String prefix = xmlSerializer.getPrefix(str2, true);
                    xmlSerializer.attribute(xsi, SoapSerializationEnvelope.TYPE_LABEL, prefix + ":" + str3);
                }
                this.writeObjectBodyWithAttributes(xmlSerializer, kvmSerializable2);
                xmlSerializer.endTag(str2, str);
            }
        }
        this.writeInnerText(xmlSerializer, kvmSerializable);
    }
    private void writeInnerText(final XmlSerializer xmlSerializer, final KvmSerializable kvmSerializable) throws IOException {
        final Object innerText;
        if (!(kvmSerializable instanceof HasInnerText) || null == (innerText = ((HasInnerText) kvmSerializable).getInnerText())) {
            return;
        }
        if (innerText instanceof ValueWriter) {
            ((ValueWriter) innerText).write(xmlSerializer);
        } else {
            xmlSerializer.cdsect(innerText.toString());
        }
    }
    protected void writeProperty(final XmlSerializer xmlSerializer, final Object obj, final PropertyInfo propertyInfo) throws IOException {
        final StringBuilder sb;
        if (null == obj || obj == SoapPrimitive.NullNilElement) {
            xmlSerializer.attribute(xsi, 120 <= this.version ? SoapSerializationEnvelope.NIL_LABEL : SoapSerializationEnvelope.NULL_LABEL, "true");
            return;
        }
        final Object[] info = this.getInfo(null, obj);
        if (propertyInfo.multiRef || null != info[2]) {
            int indexOf = multiRef.indexOf(obj);
            if (-1 == indexOf) {
                indexOf = multiRef.size();
                multiRef.addElement(obj);
            }
            if (null == info[2]) {
                sb = new StringBuilder();
                sb.append("#o");
                sb.append(indexOf);
            } else {
                sb = new StringBuilder();
                sb.append("#");
                sb.append(info[2]);
            }
            xmlSerializer.attribute(null, SoapSerializationEnvelope.HREF_LABEL, sb.toString());
            return;
        }
        if (!implicitTypes || obj.getClass() != propertyInfo.type) {
            final String prefix = xmlSerializer.getPrefix((String) info[0], true);
            xmlSerializer.attribute(xsi, SoapSerializationEnvelope.TYPE_LABEL, prefix + ":" + info[1]);
        }
        this.writeElement(xmlSerializer, obj, propertyInfo, info[3]);
    }
    protected void writeElement(final XmlSerializer xmlSerializer, final Object obj, final PropertyInfo propertyInfo, final Object obj2) throws IOException {
        if (null != obj2) {
            ((Marshal) obj2).writeInstance(xmlSerializer, obj);
            return;
        }
        if ((obj instanceof KvmSerializable) || obj == SoapPrimitive.NullNilElement || obj == SoapPrimitive.NullSkip) {
            if (obj instanceof ArrayList) {
                this.writeArrayListBodyWithAttributes(xmlSerializer, (KvmSerializable) obj);
                return;
            } else {
                this.writeObjectBodyWithAttributes(xmlSerializer, (KvmSerializable) obj);
                return;
            }
        }
        if (obj instanceof HasAttributes) {
            this.writeAttributes(xmlSerializer, (HasAttributes) obj);
        } else {
            if (obj instanceof Vector) {
                this.writeVectorBody(xmlSerializer, (Vector) obj, propertyInfo.elementType);
                return;
            }
            throw new RuntimeException("Cannot serialize: " + obj);
        }
    }
    protected void writeArrayListBody(final XmlSerializer xmlSerializer, final ArrayList arrayList) throws IOException {
        String str;
        String str2;
        final KvmSerializable kvmSerializable = (KvmSerializable) arrayList;
        final int size = arrayList.size();
        final PropertyInfo propertyInfo = new PropertyInfo();
        for (int i2 = 0; i2 < size; i2++) {
            final Object property = kvmSerializable.getProperty(i2);
            kvmSerializable.getPropertyInfo(i2, properties, propertyInfo);
            if (!(property instanceof SoapObject)) {
                if (0 == (propertyInfo.flags & 1)) {
                    final Object property2 = kvmSerializable.getProperty(i2);
                    if ((null != property || !skipNullProperties) && property2 != SoapPrimitive.NullSkip) {
                        xmlSerializer.startTag(propertyInfo.namespace, propertyInfo.name);
                        this.writeProperty(xmlSerializer, property2, propertyInfo);
                        xmlSerializer.endTag(propertyInfo.namespace, propertyInfo.name);
                    }
                }
            } else {
                final KvmSerializable kvmSerializable2 = (SoapObject) property;
                final Object[] info = this.getInfo(null, kvmSerializable2);
                final String str3 = (String) info[1];
                final String str4 = propertyInfo.name;
                if (null != str4 && 0 < str4.length()) {
                    str = propertyInfo.name;
                } else {
                    str = (String) info[1];
                }
                final String str5 = propertyInfo.namespace;
                if (null != str5 && 0 < str5.length()) {
                    str2 = propertyInfo.namespace;
                } else {
                    str2 = (String) info[0];
                }
                xmlSerializer.startTag(str2, str);
                if (!implicitTypes) {
                    final String prefix = xmlSerializer.getPrefix(str2, true);
                    xmlSerializer.attribute(xsi, SoapSerializationEnvelope.TYPE_LABEL, prefix + ":" + str3);
                }
                this.writeObjectBodyWithAttributes(xmlSerializer, kvmSerializable2);
                xmlSerializer.endTag(str2, str);
            }
        }
        this.writeInnerText(xmlSerializer, kvmSerializable);
    }
    protected void writeVectorBody(final XmlSerializer xmlSerializer, final Vector vector, PropertyInfo propertyInfo) throws IOException {
        String str;
        String str2;
        int size;
        int i2 = 0;
        if (null == propertyInfo) {
            propertyInfo = PropertyInfo.OBJECT_TYPE;
        } else {
            str = propertyInfo.name;
            if (null != str) {
                str2 = propertyInfo.namespace;
                size = vector.size();
                final Object[] info = this.getInfo(propertyInfo.type, null);
                if (implicitTypes) {
                    xmlSerializer.attribute(enc, SoapSerializationEnvelope.ARRAY_TYPE_LABEL, xmlSerializer.getPrefix((String) info[0], false) + ":" + info[1] + "[" + size + "]");
                } else if (null == str2) {
                    str2 = (String) info[0];
                }
                boolean z = false;
                for (i2 = 0; i2 < size; i2++) {
                    if (null == vector.elementAt(i2)) {
                        z = true;
                    } else {
                        xmlSerializer.startTag(str2, str);
                        if (z) {
                            xmlSerializer.attribute(enc, "position", "[" + i2 + "]");
                            z = false;
                        }
                        this.writeProperty(xmlSerializer, vector.elementAt(i2), propertyInfo);
                        xmlSerializer.endTag(str2, str);
                    }
                }
            }
        }
        str = SoapSerializationEnvelope.ITEM_LABEL;
        str2 = null;
        size = vector.size();
        final Object[] info2 = this.getInfo(propertyInfo.type, null);
        if (implicitTypes) {
        }
        final boolean z2 = false;
        while (i2 < size) {
        }
    }
}
