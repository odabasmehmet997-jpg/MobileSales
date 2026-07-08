package com.proje.mobilesales.core.tigerwcf;

import androidx.core.app.NotificationCompat;
import com.proje.mobilesales.BuildConfig;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.strategy.Name;



public class Query {

    @Element(name = "sqlText", required = false)
    private StringElement asqlText;

    @Element(name = "orderByText", required = false)
    private StringElement borderByText;

    @Element(name = "securityCode", required = false)
    private StringElement csecurityCode;

    @Element(name = "resultXML", required = false)
    private StringElement dresultXML;

    @Element(name = "errorString", required = false)
    private StringElement eerrorString;

    @Element(name = NotificationCompat.CATEGORY_STATUS, required = false)
    private IntElement fstatus;

    @Element(name = "LbsLoadPass", required = false)
    private StringElement glbsLoadPass;

    @Attribute(empty = "o0", name = Name.MARK, required = false)
    private String f1202id;

    @Attribute(empty = BuildConfig.NETSIS_DEMO_PASSWORD, name = "c:root", required = false)
    private String root;

    @Attribute(empty = "http://tempuri.org/", name = "xmlns", required = false)
    private String xmlns;

    public StringElement getAsqlText() {
        return this.asqlText;
    }

    public void setAsqlText(StringElement stringElement) {
        this.asqlText = stringElement;
    }

    public StringElement getOrderByText() {
        return this.borderByText;
    }

    public void setOrderByText(StringElement stringElement) {
        this.borderByText = stringElement;
    }

    public StringElement getSecurityCode() {
        return this.csecurityCode;
    }

    public void setSecurityCode(StringElement stringElement) {
        this.csecurityCode = stringElement;
    }

    public StringElement getLbsLoadPass() {
        return this.glbsLoadPass;
    }

    public void setLbsLoadPass(StringElement stringElement) {
        this.glbsLoadPass = stringElement;
    }

    public IntElement getStatus() {
        return this.fstatus;
    }

    public void setStatus(IntElement intElement) {
        this.fstatus = intElement;
    }

    public StringElement getResultXML() {
        return this.dresultXML;
    }

    public void setResultXML(StringElement stringElement) {
        this.dresultXML = stringElement;
    }

    public StringElement getErrorString() {
        return this.eerrorString;
    }

    public void setErrorString(StringElement stringElement) {
        this.eerrorString = stringElement;
    }
}
