package com.proje.mobilesales.core.tigerwcf.edocument;

import androidx.core.app.NotificationCompat;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.core.tigerwcf.IntElement;
import com.proje.mobilesales.core.tigerwcf.StringElementAppend;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.strategy.Name;

public class SendRecvEInvoiceDocuments {
    private IntElement aSend;
    private IntElement bRecv;
    private StringElementAppend cRefs;
    private StringElementAppend dMsg;
    private StringElementAppend eLbsLoadPass;
    private StringElementAppend fSecurityCode;
    private String f1219id;
    private String root;
    private String xmlns;
    public String getXmlns() {
        return this.xmlns;
    }
    public void setXmlns(String str) {
        this.xmlns = str;
    }
    public String getId() {
        return this.f1219id;
    }
    public void setId(String str) {
        this.f1219id = str;
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
    public IntElement getSend() {
        return this.aSend;
    }
    public void setSend(IntElement intElement) {
        this.aSend = intElement;
    }
    public IntElement getRecv() {
        return this.bRecv;
    }
    public void setRecv(IntElement intElement) {
        this.bRecv = intElement;
    }
    public StringElementAppend getRefs() {
        return this.cRefs;
    }
    public void setRefs(StringElementAppend stringElementAppend) {
        this.cRefs = stringElementAppend;
    }
}
