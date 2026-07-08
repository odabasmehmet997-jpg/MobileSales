package com.proje.mobilesales.core.tigerwcf.edocument;

import com.proje.mobilesales.core.tigerwcf.StringElementAppend;

public class SendEArchiveDocuments {
    private StringElementAppend cRefs;
    private StringElementAppend dMsg;
    private StringElementAppend eLbsLoadPass;
    private StringElementAppend fSecurityCode;
    private String f1217id;
    private String root;
    private String xmlns;
    public String getXmlns() {
        return this.xmlns;
    }
    public void setXmlns(String str) {
        this.xmlns = str;
    }
    public String getId() {
        return this.f1217id;
    }
    public void setId(String str) {
        this.f1217id = str;
    }
    public String getRoot() {
        return this.root;
    }
    public void setRoot(String str) {
        this.root = str;
    }
    public StringElementAppend getLbsLoadPass() {
        return this.eLbsLoadPass;
    }
    public void setLbsLoadPass(StringElementAppend stringElementAppend) {
        this.eLbsLoadPass = stringElementAppend;
    }
    public StringElementAppend getSecurityCode() {
        return this.fSecurityCode;
    }
    public void setSecurityCode(StringElementAppend stringElementAppend) {
        this.fSecurityCode = stringElementAppend;
    }
    public StringElementAppend getMsg() {
        return this.dMsg;
    }
    public void setMsg(StringElementAppend stringElementAppend) {
        this.dMsg = stringElementAppend;
    }
    public StringElementAppend getRefs() {
        return this.cRefs;
    }
    public void setRefs(StringElementAppend stringElementAppend) {
        this.cRefs = stringElementAppend;
    }
}
