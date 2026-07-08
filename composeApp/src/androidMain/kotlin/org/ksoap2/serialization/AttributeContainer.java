package org.ksoap2.serialization;

import okhttp3.HttpUrl;
import java.util.Vector;

public class AttributeContainer implements HasAttributes {
    protected Vector attributes = new Vector();
    public void getAttribute(final int i2, final AttributeInfo attributeInfo) {
    }
    public void setAttribute(final AttributeInfo attributeInfo) {
    }
    public void getAttributeInfo(final int i2, final AttributeInfo attributeInfo) {
        final AttributeInfo attributeInfo2 = (AttributeInfo) attributes.elementAt(i2);
        attributeInfo.name = attributeInfo2.name;
        attributeInfo.namespace = attributeInfo2.namespace;
        attributeInfo.flags = attributeInfo2.flags;
        attributeInfo.type = attributeInfo2.type;
        attributeInfo.elementType = attributeInfo2.elementType;
        attributeInfo.value = attributeInfo2.getValue();
    }
    public Object getAttribute(final int i2) {
        return ((AttributeInfo) attributes.elementAt(i2)).getValue();
    }
    public String getAttributeAsString(final int i2) {
        return ((AttributeInfo) attributes.elementAt(i2)).getValue().toString();
    }
    public Object getAttribute(final String str) {
        final Integer attributeIndex = this.attributeIndex(str);
        if (null != attributeIndex) {
            return this.getAttribute(attributeIndex.intValue());
        }
        throw new RuntimeException("illegal property: " + str);
    }
    public Object getAttribute(final String str, final String str2) {
        final Integer attributeIndex = this.attributeIndex(str, str2);
        if (null != attributeIndex) {
            return this.getAttribute(attributeIndex.intValue());
        }
        throw new RuntimeException("illegal property: " + str2);
    }
    public String getAttributeAsString(final String str) {
        final Integer attributeIndex = this.attributeIndex(str);
        if (null != attributeIndex) {
            return this.getAttribute(attributeIndex.intValue()).toString();
        }
        throw new RuntimeException("illegal property: " + str);
    }
    public String getAttributeAsString(final String str, final String str2) {
        final Integer attributeIndex = this.attributeIndex(str, str2);
        if (null != attributeIndex) {
            return this.getAttribute(attributeIndex.intValue()).toString();
        }
        throw new RuntimeException("illegal property: " + str2);
    }
    public boolean hasAttribute(final String str) {
        return null != attributeIndex(str);
    }
    public boolean hasAttribute(final String str, final String str2) {
        return null != attributeIndex(str, str2);
    }
    public Object getAttributeSafely(final String str) {
        final Integer attributeIndex = this.attributeIndex(str);
        if (null != attributeIndex) {
            return this.getAttribute(attributeIndex.intValue());
        }
        return null;
    }
    public Object getAttributeSafely(final String str, final String str2) {
        final Integer attributeIndex = this.attributeIndex(str, str2);
        if (null != attributeIndex) {
            return this.getAttribute(attributeIndex.intValue());
        }
        return null;
    }
    public Object getAttributeSafelyAsString(final String str) {
        final Integer attributeIndex = this.attributeIndex(str);
        if (null != attributeIndex) {
            return this.getAttribute(attributeIndex.intValue()).toString();
        }
        return HttpUrl.FRAGMENT_ENCODE_SET;
    }
    public Object getAttributeSafelyAsString(final String str, final String str2) {
        final Integer attributeIndex = this.attributeIndex(str, str2);
        if (null != attributeIndex) {
            return this.getAttribute(attributeIndex.intValue()).toString();
        }
        return HttpUrl.FRAGMENT_ENCODE_SET;
    }
    private Integer attributeIndex(final String str) {
        for (int i2 = 0; i2 < attributes.size(); i2++) {
            if (str.equals(((AttributeInfo) attributes.elementAt(i2)).getName())) {
                return Integer.valueOf(i2);
            }
        }
        return null;
    }
    private Integer attributeIndex(final String str, final String str2) {
        for (int i2 = 0; i2 < attributes.size(); i2++) {
            final AttributeInfo attributeInfo = (AttributeInfo) attributes.elementAt(i2);
            if (str2.equals(attributeInfo.getName()) && str.equals(attributeInfo.getNamespace())) {
                return Integer.valueOf(i2);
            }
        }
        return null;
    }
    public int getAttributeCount() {
        return attributes.size();
    }
    protected boolean attributesAreEqual(final AttributeContainer attributeContainer) {
        final int attributeCount = this.getAttributeCount();
        if (attributeCount != attributeContainer.getAttributeCount()) {
            return false;
        }
        for (int i2 = 0; i2 < attributeCount; i2++) {
            final AttributeInfo attributeInfo = (AttributeInfo) attributes.elementAt(i2);
            final Object value = attributeInfo.getValue();
            if (!attributeContainer.hasAttribute(attributeInfo.getName()) || !value.equals(attributeContainer.getAttributeSafely(attributeInfo.getName()))) {
                return false;
            }
        }
        return true;
    }
    public void addAttribute(final String str, final Object obj) {
        this.addAttribute(null, str, obj);
    }
    public void addAttribute(final String str, final String str2, final Object obj) {
        final AttributeInfo attributeInfo = new AttributeInfo();
        attributeInfo.name = str2;
        attributeInfo.namespace = str;
        attributeInfo.type = null == obj ? PropertyInfo.OBJECT_CLASS : obj.getClass();
        attributeInfo.value = obj;
        this.addAttribute(attributeInfo);
    }
    public void addAttributeIfValue(final String str, final Object obj) {
        if (null != obj) {
            this.addAttribute(str, obj);
        }
    }
    public void addAttributeIfValue(final String str, final String str2, final Object obj) {
        if (null != obj) {
            this.addAttribute(str, str2, obj);
        }
    }
    public void addAttribute(final AttributeInfo attributeInfo) {
        attributes.addElement(attributeInfo);
    }
    public void addAttributeIfValue(final AttributeInfo attributeInfo) {
        if (null != attributeInfo.value) {
            attributes.addElement(attributeInfo);
        }
    }
}
