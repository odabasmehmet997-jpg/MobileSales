package com.proje.mobilesales.core.tigerwcf.edocument;

import androidx.core.app.NotificationCompat;
import com.proje.mobilesales.BuildConfig;
import com.proje.mobilesales.core.tigerwcf.IntElement;
import com.proje.mobilesales.core.tigerwcf.StringElementAppend;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.strategy.Name;

public class SendRecvEDispatchDocuments {
    private IntElement aSend;
    private IntElement bRecv;
    private StringElementAppend cDispatchRefs;
    private StringElementAppend dReceiptRefs;
    private StringElementAppend eMsg;
    private StringElementAppend fLbsLoadPass;
    private StringElementAppend gSecurityCode;
    private String f1218id;
    private String root;
    private String xmlns;
    public String getXmlns() {
        return this.xmlns;
    }
    public void setXmlns(String str) {
        this.xmlns = str;
    }
    public String getId() {
        return this.f1218id;
    }
    public void setId(String str) {
        this.f1218id = str;
    }
    public String getRoot() {
        return this.root;
    }
    public void setRoot(String str) {
        this.root = str;
    }
    public StringElementAppend getLbsLoadPass() {
        return this.fLbsLoadPass;
    }
    public void setLbsLoadPass(StringElementAppend stringElementAppend) {
        this.fLbsLoadPass = stringElementAppend;
    }
    public StringElementAppend getSecurityCode() {
        return this.gSecurityCode;
    }
    public void setSecurityCode(StringElementAppend stringElementAppend) {
        this.gSecurityCode = stringElementAppend;
    }
    public StringElementAppend getMsg() {
        return this.eMsg;
    }
    public void setMsg(StringElementAppend stringElementAppend) {
        this.eMsg = stringElementAppend;
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
    public StringElementAppend getReceiptRefs() {
        return this.dReceiptRefs;
    }
    public void setdReceiptRefs(StringElementAppend stringElementAppend) {
        this.dReceiptRefs = stringElementAppend;
    }
    public StringElementAppend getDispatchRefs() {
        return this.cDispatchRefs;
    }
    public void setDispatchRefs(StringElementAppend stringElementAppend) {
        this.cDispatchRefs = stringElementAppend;
    }
}
