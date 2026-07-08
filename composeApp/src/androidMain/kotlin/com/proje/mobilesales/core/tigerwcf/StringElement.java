package com.proje.mobilesales.core.tigerwcf;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Text;



public class StringElement {

    @Namespace(prefix = "i", reference = "http://www.w3.org/2001/XMLSchema-sInstance")
    @Attribute(name = "nil", required = false)
    private boolean nil;

    @Namespace(prefix = "i", reference = "http://www.w3.org/2001/XMLSchema-sInstance")
    @Attribute(name = "type", required = false)
    private String type;

    @Text(required = false)
    private String value;

    @Attribute(name = "xmlns:a", required = false)
    private String xmlnsa;

    @Attribute(name = "xmlns:i", required = false)
    private String xmlnsi;

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public StringElement(String str) {
        this.value = str;
    }

    public StringElement() {
        this.value = "";
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public boolean isNil() {
        return this.nil;
    }

    public void setNil(boolean z) {
        this.nil = z;
    }

    public String getXmlnsa() {
        return this.xmlnsa;
    }

    public void setXmlnsa(String str) {
        this.xmlnsa = str;
    }

    public String getXmlnsi() {
        return this.xmlnsi;
    }

    public void setXmlnsi(String str) {
        this.xmlnsi = str;
    }
}
