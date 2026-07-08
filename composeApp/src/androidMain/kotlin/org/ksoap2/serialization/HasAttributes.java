package org.ksoap2.serialization;

public interface HasAttributes {
    void getAttribute(int i2, AttributeInfo attributeInfo);
    int getAttributeCount();
    void getAttributeInfo(int i2, AttributeInfo attributeInfo);
    void setAttribute(AttributeInfo attributeInfo);
}
