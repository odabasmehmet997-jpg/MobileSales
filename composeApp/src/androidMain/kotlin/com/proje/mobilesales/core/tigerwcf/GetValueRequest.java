package com.proje.mobilesales.core.tigerwcf;

import com.proje.mobilesales.BuildConfig;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.strategy.Name;



public class GetValueRequest {

    @Element(name = "Flag", required = false)
    private IntElement aFlag;

    @Element(name = "isFlag", required = false)
    private StringElement bIsFlag;

    @Element(name = "securityCode", required = false)
    private StringElement cSecurityCode;

    @Element(name = "LbsLoadPass", required = false)
    private StringElement dLbsLoadPass;

    /* renamed from: id */
    @Attribute(empty = "o0", name = Name.MARK, required = false)
    private String f1201id;

    @Attribute(empty = BuildConfig.NETSIS_DEMO_PASSWORD, name = "c:root", required = false)
    private String root;

    @Attribute(empty = "http://tempuri.org/", name = "xmlns", required = false)
    private String xmlns;

    public String getXmlns() {
        return this.xmlns;
    }

    public void setXmlns(String str) {
        this.xmlns = str;
    }

    public String getId() {
        return this.f1201id;
    }

    public void setId(String str) {
        this.f1201id = str;
    }

    public String getRoot() {
        return this.root;
    }

    public void setRoot(String str) {
        this.root = str;
    }

    public StringElement getLbsLoadPass() {
        return this.dLbsLoadPass;
    }

    public void setLbsLoadPass(StringElement stringElement) {
        this.dLbsLoadPass = stringElement;
    }

    public StringElement getSecurityCode() {
        return this.cSecurityCode;
    }

    public void setSecurityCode(StringElement stringElement) {
        this.cSecurityCode = stringElement;
    }

    public IntElement getFlag() {
        return this.aFlag;
    }

    public void setFlag(IntElement intElement) {
        this.aFlag = intElement;
    }

    public StringElement getIsFlag() {
        return this.bIsFlag;
    }

    public void setIsFlag(StringElement stringElement) {
        this.bIsFlag = stringElement;
    }
}
